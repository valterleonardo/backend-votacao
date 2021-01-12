package br.com.votacao.api.cpf.dto;

import br.com.votacao.api.cpf.enums.StatusCpf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CpfDTO {

    private StatusCpf status;
}
