package br.com.votacao;

import br.com.votacao.api.usuario.entity.Usuario;
import br.com.votacao.api.usuario.service.UsuarioService;
import br.com.votacao.core.security.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class VotacaoApplication implements CommandLineRunner {
	
	@Autowired
    UsuarioService usuarioService;

	public static void main(String[] args) {
		new SpringApplicationBuilder(VotacaoApplication.class).run(args);
	}

	@Override
	public void run(String... params) throws Exception {
		Usuario admin = new Usuario();
	    admin.setUsername("admin");
	    admin.setPassword("admin");
	    admin.setEmail("admin@email.com");
	    admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

		System.out.println("inserting: " + admin);
	    usuarioService.signup(admin);

	    Usuario client = new Usuario();
	    client.setUsername("client");
	    client.setPassword("client");
	    client.setEmail("client@email.com");
	    client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));

		System.out.println("inserting: "+ client);
	    usuarioService.signup(client);
  	}
}