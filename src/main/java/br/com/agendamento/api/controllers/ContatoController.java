package br.com.agendamento.api.controllers;

import br.com.agendamento.api.dtos.ContatoRequestDTO;
import br.com.agendamento.api.dtos.ContatoResponseDTO;
import br.com.agendamento.api.entities.Contato;
import br.com.agendamento.api.services.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contatos")
//@CrossOrigin(origins = "http://localhost:8080")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @Operation(summary = "Retorna uma lista com todos os contatos")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/")
    public ResponseEntity<List<ContatoResponseDTO>> findAll() {
        List<ContatoResponseDTO> listContatos = contatoService.findAll();
        return ResponseEntity.ok().body(listContatos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ContatoResponseDTO> findById(@PathVariable Long id) {
        ContatoResponseDTO contato = contatoService.findById(id);
        return ResponseEntity.ok().body(contato);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<ContatoResponseDTO> insert(@Validated @RequestBody ContatoRequestDTO dto) throws ParseException {
        ContatoResponseDTO contatoDTO = contatoService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contatoDTO.id()).toUri();
        return ResponseEntity.created(uri).body(contatoDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ContatoResponseDTO> update(@PathVariable Long id, @Validated @RequestBody ContatoRequestDTO dto) throws ParseException {
        ContatoResponseDTO contatoDTO = this.contatoService.findById(id); // Verificar se existe o contato.
        if(contatoDTO.id() != null){
            return ResponseEntity.ok().body(contatoService.update(dto,id));
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContatoResponseDTO> delete(@PathVariable("id") Long id){
        ContatoResponseDTO contatoDTO = this.contatoService.findById(id);
        if(contatoDTO.id() != null){
            this.contatoService.delete(contatoDTO.id());
            return ResponseEntity.ok().body(contatoDTO);
        }
        return ResponseEntity.notFound().build();
    }











}
