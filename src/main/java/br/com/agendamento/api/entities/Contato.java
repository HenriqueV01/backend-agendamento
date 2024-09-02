package br.com.agendamento.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(schema = "desafio", name = "contato")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contato_id")
    private Long id;
    @Column(name = "contato_nome")
    private String nome;
    @Column(name = "contato_email")
    private String email;
    @Column(name = "contato_celular")
    private String celular;
    @Column(name = "contato_telefone")
    private String telefone;
    @Column(name = "contato_sn_favorito")
    private String favorito;
    @Column(name = "contato_sn_ativo")
    private String ativo;
    @Column(name = "contato_dh_cad")
    private LocalDateTime data_hora;




}
