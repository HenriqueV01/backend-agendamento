package br.com.agendamento.api.dtos;

import java.util.Date;

public record ContatoRequestDTO(
        String nome,
        String email,
        String celular,
        String telefone,
        Boolean favorito,
        Boolean ativo,
        Date data_hora

) {
}
