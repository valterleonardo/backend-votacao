package br.com.votacao.api.usuario.service;

import br.com.votacao.api.usuario.entity.Usuario;
import br.com.votacao.api.usuario.repository.UsuarioRepository;
import br.com.votacao.core.security.JwtTokenProvider;
import br.com.votacao.core.security.exception.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	public String signin(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			String token = jwtTokenProvider.createToken(username, usuarioRepository.findByUsername(username).getRoles());
			log.info("\"" + username + "\"" + " login realizado com sucesso");
			return token;
	    } catch (AuthenticationException e) {
	    	throw new SecurityException("Username/Senha inválidos.", HttpStatus.UNPROCESSABLE_ENTITY);
	    }

  	}

	public String signup(Usuario usuario) {
		if (!usuarioRepository.existsByUsername(usuario.getUsername())) {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			usuarioRepository.save(usuario);
			return jwtTokenProvider.createToken(usuario.getUsername(), usuario.getRoles());
	    } else {
	    	throw new SecurityException("Username já está em uso.", HttpStatus.UNPROCESSABLE_ENTITY);
	    }
	}

	public String refresh(String username) {
		String token = jwtTokenProvider.createToken(username, usuarioRepository.findByUsername(username).getRoles());
		log.info("\"" + username + "\"" + " Token atualizado com sucesso.");
	    return token;
	}
}
