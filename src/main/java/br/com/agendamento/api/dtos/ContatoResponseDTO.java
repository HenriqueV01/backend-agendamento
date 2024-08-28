package br.com.agendamento.api.dtos;

public record ContatoResponseDTO(
        Long id,
        String nome,
        String email,
        String celular,
        String telefone,
        Boolean favorito,
        Boolean ativo,
        Long data_hota
) {

}
