package br.com.votacao.api.sessao.repository;

import br.com.votacao.api.sessao.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
}
