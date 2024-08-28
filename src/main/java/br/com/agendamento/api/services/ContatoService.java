package br.com.agendamento.api.services;

import br.com.agendamento.api.dtos.ContatoRequestDTO;
import br.com.agendamento.api.entities.Contato;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContatoService {

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
}
