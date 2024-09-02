package br.com.agendamento.api.services;

import br.com.agendamento.api.dtos.ContatoRequestDTO;
import br.com.agendamento.api.dtos.ContatoResponseDTO;
import br.com.agendamento.api.entities.Contato;
import br.com.agendamento.api.repositories.ContatoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
                contato.getData_hora().toString()
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
    public ContatoResponseDTO insert(ContatoRequestDTO dto) throws ParseException {

        Optional<Contato> existe = contatoRepository.findByCelular(dto.celular());

        if(existe.isPresent()){
            return this.createContatoResponseDTO(new Contato());
        }

        Contato contato =  new Contato();
        contato.setId(null);

        contato.setNome(dto.nome());
        contato.setEmail(dto.email());
        contato.setCelular(dto.celular());
        contato.setTelefone(dto.telefone());
        contato.setFavorito(dto.favorito() ? "S" : "N");
        contato.setAtivo(dto.ativo() ? "S" : "N");
        contato.setData_hora(LocalDateTime.now());

        contatoRepository.save(contato);

        return this.createContatoResponseDTO(contato);
    }
    @Transactional
    public ContatoResponseDTO update(ContatoRequestDTO dto, Long id) throws ParseException {



        contatoRepository.findByCelular(dto.celular());

        Contato contato =  new Contato();
        contato.setId(id);

        contato.setNome(dto.nome());
        contato.setEmail(dto.email());
//        if(contatoRepository.findByCelular(dto.celular()).isEmpty()){
//            contato.setCelular(dto.celular());
//        }

        contato.setCelular(dto.celular());

        contato.setTelefone(dto.telefone());
        contato.setFavorito(dto.favorito() ? "S" : "N");
        contato.setAtivo(dto.ativo() ? "S" : "N");
        contato.setData_hora(LocalDateTime.now());

        contatoRepository.save(contato);

        return this.createContatoResponseDTO(contato);
    }
    @Transactional
    public void delete(Long id) {
        Optional<Contato> contato = contatoRepository.findById(id);
        contato.ifPresent(value -> this.contatoRepository.delete(value));
    }

    protected String converteData(java.util.Date dtData){
        SimpleDateFormat formatBra;
        formatBra = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            java.util.Date newData = formatBra.parse(dtData.toString());
            return (formatBra.format(newData));
        } catch (ParseException Ex) {
            return "Erro na conversão da data";
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
