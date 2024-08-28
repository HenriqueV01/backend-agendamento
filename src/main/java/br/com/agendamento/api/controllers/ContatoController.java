package br.com.agendamento.api.controllers;

import br.com.agendamento.api.dtos.ContatoRequestDTO;
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
    public ResponseEntity<List<Contato>> findAll() {
        List<Contato> listContatos = contatoService.findAll();
        return ResponseEntity.ok().body(listContatos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Contato> findById(@PathVariable Long id) {
        Optional<Contato> Contato = contatoService.findById(id);
        return ResponseEntity.ok().body(Contato.get());
    }

    @PostMapping("/")
    public ResponseEntity<Void> insert(@Validated @RequestBody Contato Contato) {
        Contato objContato = contatoService.insert(Contato);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objContato.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Validated @RequestBody Contato Contato, @PathVariable Integer id) {
        contatoService.update(Contato);
        return ResponseEntity.noContent().build();
    }











}
