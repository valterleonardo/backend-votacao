package br.com.votacao.api.sessao.entity;

import br.com.votacao.api.pauta.entity.Pauta;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descricao;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Column
    private Integer periodo;

    @ManyToOne
    private Pauta pauta;
}
