package br.com.votacao.api.pauta.repository;


import br.com.votacao.api.pauta.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
