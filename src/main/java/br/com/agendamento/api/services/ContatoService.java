package br.com.agendamento.api.services;

import br.com.agendamento.api.dtos.ContatoRequestDTO;
import br.com.agendamento.api.entities.Contato;
import br.com.agendamento.api.repositories.ContatoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato createContato(ContatoRequestDTO dto){
        Contato novoContato = new Contato();

        novoContato.setNome(dto.nome());
        novoContato.setEmail(dto.email());
        novoContato.setCelular(dto.celular());
        novoContato.setTelefone(dto.telefone());
        novoContato.setFavorito(dto.favorito());
        novoContato.setAtivo(dto.ativo());
        novoContato.setData_hora(new Date(dto.data_hota()));

        return novoContato;
    }

    public List<Contato> findAll(){
        return contatoRepository.findAll();
    }

    public Optional<Contato> findById(Long id) {
        Optional<Contato> obj = contatoRepository.findById(id);
        return obj;
    }

    @Transactional
    public Contato insert(Contato obj) {
        obj.setId(null);
        obj = contatoRepository.save(obj);

        return obj;
    }

    public Contato update(Contato obj) {
        //Optional<Contato> newContato = ContatoRepository.findById(obj.getId());
        return contatoRepository.save(obj);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
