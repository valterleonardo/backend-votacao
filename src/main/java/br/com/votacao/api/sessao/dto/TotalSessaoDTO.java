package br.com.votacao.api.sessao.dto;

import br.com.votacao.api.voto.enums.Escolha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalSessaoDTO {

    private long totalVotos;
    private long totalNao;
    private long TotalSim;
    private Escolha vencedor;

}
