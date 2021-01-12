package br.com.votacao.api.cpf;

import br.com.votacao.api.cpf.dto.CpfDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConsultaCPFClient {

    public CpfDTO buscaCpf(String cpf){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://user-info.herokuapp.com/users/{cpf}", CpfDTO.class, cpf);
    }
}
