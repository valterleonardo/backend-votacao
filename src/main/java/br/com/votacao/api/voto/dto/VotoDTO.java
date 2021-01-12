package br.com.votacao.api.voto.dto;

import br.com.votacao.api.sessao.dto.SessaoDTO;
import br.com.votacao.api.usuario.dto.UsuarioDTO;
import br.com.votacao.api.voto.enums.Escolha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoDTO {

    private Long id;
    private Escolha escolha;
    private Date data;
    private UsuarioDTO usuarioDTO;
    private SessaoDTO sessaoDTO;
}
