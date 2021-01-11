package br.com.votacao.api.usuario.http;

import br.com.votacao.api.usuario.dto.UsuarioDTO;
import br.com.votacao.api.usuario.service.UsuarioService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UsuarioController {

	public static final String ALGUMA_COISA_SAIU_ERRADO = "Alguma coisa está errado.";
	public static final String USUARIO_OU_SENHA_INVALIDOS = "Usuário/Senha estão inválidos.";
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/signin")
	@ApiOperation(value = "${UserController.signin}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = ALGUMA_COISA_SAIU_ERRADO),
			@ApiResponse(code = 422, message = USUARIO_OU_SENHA_INVALIDOS)})
	public String login(@ApiParam("Signup User") @RequestBody UsuarioDTO user) {
		return usuarioService.signin(user.getUsername(), user.getPassword());
	}

	@GetMapping("/refresh")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
	public String refresh(HttpServletRequest req) {
	    return usuarioService.refresh(req.getRemoteUser());
	}
}