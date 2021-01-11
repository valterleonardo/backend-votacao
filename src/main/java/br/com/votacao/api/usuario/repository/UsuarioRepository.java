package br.com.votacao.api.usuario.repository;

import br.com.votacao.api.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	boolean existsByUsername(String username);

	Usuario findByUsername(String username);

}