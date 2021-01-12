package br.com.votacao.api.voto.entity;

import br.com.votacao.api.sessao.entity.Sessao;
import br.com.votacao.api.usuario.entity.Usuario;
import br.com.votacao.api.voto.enums.Escolha;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @ManyToOne(fetch =FetchType.EAGER)
    private Sessao sessao;

    @Enumerated
    private Escolha escolha;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
}