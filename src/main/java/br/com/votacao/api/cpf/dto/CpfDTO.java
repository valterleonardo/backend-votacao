package br.com.votacao.api.cpf.dto;

import br.com.votacao.api.cpf.enums.StatusCpf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CpfDTO {

    private StatusCpf status;
}
