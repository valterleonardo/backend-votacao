package br.com.votacao.api.sessao.http;

import br.com.votacao.api.sessao.dto.SessaoDTO;
import br.com.votacao.api.sessao.service.SessaoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessoes")
@Api(tags = "sessao")
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;

    public static final String ALGUMA_COISA_SAIU_ERRADO = "Alguma coisa está errado.";
    public static final String USUARIO_OU_SENHA_INVALIDOS = "Usuário/Senha estão inválidos.";

    @PostMapping("/cadastrar")
    @ApiOperation(value = "${SessaoController.cadastrar}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = ALGUMA_COISA_SAIU_ERRADO),
            @ApiResponse(code = 422, message = USUARIO_OU_SENHA_INVALIDOS)})
    public SessaoDTO cadastrar(@ApiParam("SessaoDTO") @RequestBody SessaoDTO sessaoDTO){
        return sessaoService.cadastrar(sessaoDTO);
    }

    @GetMapping("/listar")
    @ApiOperation(value = "${SessaoController.lista}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = ALGUMA_COISA_SAIU_ERRADO),
            @ApiResponse(code = 422, message = USUARIO_OU_SENHA_INVALIDOS)})
    public List<SessaoDTO> listar(){
        return sessaoService.listar();
    }

    //TODO contar votos sessao
}
