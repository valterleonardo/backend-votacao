package br.com.votacao.api.voto.repository;

import br.com.votacao.api.voto.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    @Query("SELECT v FROM Voto v WHERE v.sessao.id = ?1")
    List<Voto> buscarVotosPorSessaoId(Long sessaoId);
}
