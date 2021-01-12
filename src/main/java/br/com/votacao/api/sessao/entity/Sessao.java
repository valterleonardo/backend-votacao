package br.com.votacao.api.sessao.entity;

import br.com.votacao.api.pauta.entity.Pauta;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descricao;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @ManyToOne
    private Pauta pauta;
}
