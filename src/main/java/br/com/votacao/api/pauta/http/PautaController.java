package br.com.votacao.api.pauta.http;

import br.com.votacao.api.pauta.dto.PautaDTO;
import br.com.votacao.api.pauta.service.PautaService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pautas")
@Api(tags = "pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    public static final String ALGUMA_COISA_SAIU_ERRADO = "Alguma coisa está errado.";
    public static final String USUARIO_OU_SENHA_INVALIDOS = "Usuário/Senha estão inválidos.";

    @PostMapping("/cadastrar")
    @ApiOperation(value = "${PautaController.cadastrar}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = ALGUMA_COISA_SAIU_ERRADO),
            @ApiResponse(code = 422, message = USUARIO_OU_SENHA_INVALIDOS)})
    public PautaDTO cadastrar(@ApiParam("Pauta DTO") @RequestBody PautaDTO pauta){
        return pautaService.cadastrar(pauta);
    }

    @GetMapping("/listar")
    @ApiOperation(value = "${PautaController.listar}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = ALGUMA_COISA_SAIU_ERRADO),
            @ApiResponse(code = 422, message = USUARIO_OU_SENHA_INVALIDOS)})
    public List<PautaDTO> listar(){
        return pautaService.listar();
    }
}
