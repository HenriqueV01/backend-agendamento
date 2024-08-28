package br.com.agendamento.api.repositories;

import br.com.agendamento.api.entities.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContatoRepository extends JpaRepository<Contato, UUID> {
}
