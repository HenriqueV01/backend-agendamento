package br.com.agendamento.api.services;

import br.com.agendamento.api.dtos.ContatoRequestDTO;
import br.com.agendamento.api.dtos.ContatoResponseDTO;
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

    public ContatoResponseDTO createContatoResponseDTO(Contato contato){
        return new ContatoResponseDTO(
                contato.getId(),
                contato.getNome(),
                contato.getEmail(),
                contato.getCelular(),
                contato.getTelefone(),
                contato.getFavorito().equals("S"),
                contato.getAtivo().equals("S"),
                contato.getData_hora()
        );
    }

    public List<ContatoResponseDTO> findAll(){
        List<Contato> listContatos = contatoRepository.findAll();
        return listContatos.stream().map(this::createContatoResponseDTO
        ).toList();
    }

    public ContatoResponseDTO findById(Long id) {
        Optional<Contato> contato = contatoRepository.findById(id);
        return this.createContatoResponseDTO(contato.get());
    }

    @Transactional
    public ContatoResponseDTO insert(ContatoRequestDTO dto) {
        Contato contato =  new Contato();
        contato.setId(null);

        contato.setNome(dto.nome());
        contato.setEmail(dto.email());
        contato.setCelular(dto.celular());
        contato.setTelefone(dto.telefone());
        contato.setFavorito(dto.favorito() ? "S" : "N");
        contato.setAtivo(dto.ativo() ? "S" : "N");
        contato.setData_hora(new Date());

        return this.createContatoResponseDTO(contatoRepository.save(contato));
    }

    public void update(ContatoRequestDTO dto, Long id) {
        Contato contato =  new Contato();
        contato.setId(id);

        contato.setNome(dto.nome());
        contato.setEmail(dto.email());
        contato.setCelular(dto.celular());
        contato.setTelefone(dto.telefone());
        contato.setFavorito(dto.favorito() ? "S" : "N");
        contato.setAtivo(dto.ativo() ? "S" : "N");
        contato.setData_hora(new Date());

        contatoRepository.save(contato);
    }

    public void delete(Long id) {
        Optional<Contato> contato = contatoRepository.findById(id);
        contato.ifPresent(value -> this.contatoRepository.delete(value));
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
