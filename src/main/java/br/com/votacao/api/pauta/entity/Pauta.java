package br.com.votacao.api.pauta.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;
}
