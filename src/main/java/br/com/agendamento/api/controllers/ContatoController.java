package br.com.agendamento.api.controllers;

import br.com.agendamento.api.dtos.ContatoRequestDTO;
import br.com.agendamento.api.dtos.ContatoResponseDTO;
import br.com.agendamento.api.entities.Contato;
import br.com.agendamento.api.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

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

    @PostMapping("/")
    public ResponseEntity<Void> insert(@Validated @RequestBody ContatoRequestDTO dto) {
        ContatoResponseDTO contatoDTO = contatoService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contatoDTO.id()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Validated @RequestBody ContatoRequestDTO dto, @PathVariable Long id) {
        ContatoResponseDTO contatoDTO = this.contatoService.findById(id);
        if(contatoDTO.id() != null){
            contatoService.update(dto,id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        ContatoResponseDTO contatoDTO = this.contatoService.findById(id);
        if(contatoDTO.id() != null){
            this.contatoService.delete(contatoDTO.id());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }











}
