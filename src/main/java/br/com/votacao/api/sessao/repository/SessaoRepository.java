package br.com.votacao.api.sessao.repository;

import br.com.votacao.api.sessao.entity.Sessao;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    @Cacheable(value = "sessao")
    Optional<Sessao> findById(Long id);
}
