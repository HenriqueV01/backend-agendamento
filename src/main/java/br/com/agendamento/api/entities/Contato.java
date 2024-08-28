package br.com.agendamento.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "contato")
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
    private Boolean favorito;
    @Column(name = "contato_sn_ativo")
    private Boolean ativo;
    @Column(name = "contato_dh_cad")
    private Date data_hora;




}
