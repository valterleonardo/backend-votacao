package br.com.votacao.api.usuario.dto;

import br.com.votacao.core.security.enums.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UsuarioDTO {

	@ApiModelProperty(position = 0)
	private Integer id;

	@ApiModelProperty(position = 1)
	private String username;
	
	@ApiModelProperty(position = 2)
	private String email;
	
	@ApiModelProperty(position = 3)
	private String password;
	
	@ApiModelProperty(position = 4)
	List<Role> roles;
}