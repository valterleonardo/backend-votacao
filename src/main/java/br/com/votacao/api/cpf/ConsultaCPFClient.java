package br.com.votacao.api.cpf;

import br.com.votacao.api.cpf.dto.CpfDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ConsultaCPFClient {

    public CpfDTO buscaCpf(String cpf){
        RestTemplate restTemplate = new RestTemplate();
        CpfDTO cpfDTO = restTemplate.getForObject("https://user-info.herokuapp.com/users/{cpf}", CpfDTO.class, cpf);
        log.info("Consulta CPF: " + cpfDTO.toString());
        return cpfDTO;
    }
}
