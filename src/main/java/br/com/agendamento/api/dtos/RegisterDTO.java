package br.com.agendamento.api.dtos;

import br.com.agendamento.api.entities.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
