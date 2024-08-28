package br.com.agendamento.api.dtos;

import java.util.Date;

public record ContatoResponseDTO(
        Long id,
        String nome,
        String email,
        String celular,
        String telefone,
        Boolean favorito,
        Boolean ativo,
        Date data_hota
) {

}
