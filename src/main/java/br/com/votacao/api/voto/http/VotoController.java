package br.com.votacao.api.voto.http;

import br.com.votacao.api.voto.dto.VotoDTO;
import br.com.votacao.api.voto.service.VotoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/votos")
@Api(tags = "voto")
public class VotoController {

    @Autowired
    private VotoService votoService;

    public static final String ALGUMA_COISA_SAIU_ERRADO = "Alguma coisa está errado.";
    public static final String USUARIO_OU_SENHA_INVALIDOS = "Usuário/Senha estão inválidos.";

    @PostMapping("/votar")
    @ApiOperation(value = "${VotoController.votar}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = ALGUMA_COISA_SAIU_ERRADO),
            @ApiResponse(code = 422, message = USUARIO_OU_SENHA_INVALIDOS)})
    public VotoDTO votar(@ApiParam("VotoDTO") @RequestBody VotoDTO votoDTO){
        return votoService.votar(votoDTO);
    }
}
