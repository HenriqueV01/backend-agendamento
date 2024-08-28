package br.com.agendamento.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contato {
    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private String email;
    private String celular;
    private String telefone;
    private Boolean favorito;
    private Boolean ativo;
    private Date data_hora;


}
