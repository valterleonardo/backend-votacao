package br.com.votacao.api.sessao.dto;

import br.com.votacao.api.pauta.dto.PautaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessaoDTO {
    private Long id;
    private String descricao;
    private Date data;
    private Integer periodo;
    private PautaDTO pautaDTO;
}
