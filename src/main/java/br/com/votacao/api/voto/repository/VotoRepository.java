package br.com.votacao.api.voto.repository;

import br.com.votacao.api.voto.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
}
