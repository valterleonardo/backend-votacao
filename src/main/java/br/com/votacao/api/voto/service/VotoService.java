package br.com.votacao.api.voto.service;

import br.com.votacao.api.cpf.ConsultaCPFClient;
import br.com.votacao.api.cpf.dto.CpfDTO;
import br.com.votacao.api.cpf.enums.StatusCpf;
import br.com.votacao.api.pauta.dto.PautaDTO;
import br.com.votacao.api.sessao.dto.SessaoDTO;
import br.com.votacao.api.sessao.entity.Sessao;
import br.com.votacao.api.sessao.service.SessaoService;
import br.com.votacao.api.usuario.dto.UsuarioDTO;
import br.com.votacao.api.usuario.entity.Usuario;
import br.com.votacao.api.voto.dto.VotoDTO;
import br.com.votacao.api.voto.entity.Voto;
import br.com.votacao.api.voto.exception.VotoException;
import br.com.votacao.api.voto.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private SessaoService sessaoService;
    @Autowired
    private ConsultaCPFClient consultaCPFClient;

    public VotoDTO votar(VotoDTO votoDTO) {
        Voto voto = toVoto(votoDTO);

        validarSessaoAtiva(votoDTO.getSessaoDTO().getId());
        validarCpf(votoDTO.getCpf());
        validarVotoDuplicado(voto);

        return toVotoDTO(votoRepository.save(voto));
    }

    private void validarVotoDuplicado(Voto voto) {
        if(votoRepository.exists(Example.of(voto)))
            throw new VotoException("Você já votou nesta sessão.", HttpStatus.BAD_REQUEST);
    }

    private void validarSessaoAtiva(Long id) {
        Sessao sessao = sessaoService.getSessao(id);
        if(sessao == null)
            throw new VotoException("Sessao não existe.", HttpStatus.BAD_REQUEST);
        validarPeriodo(sessao);
    }

    private void validarPeriodo(Sessao sessao) {
        Date dataCricao = sessao.getData();
        System.out.println("DataCriacao" + dataCricao);
        Integer periodo = sessao.getPeriodo();
        System.out.println("Periodo: " + periodo);
        Date dataLimite = adicionarPeriodoEmSegundos(dataCricao, periodo);
        System.out.println("DataLimite: " + dataLimite);
        Date atual = new Date();
        System.out.println("Atual: " + atual);
        if(atual.after(dataLimite))
            throw new VotoException("Sessao encerrada, não poderá mais votar nessa sessão!", HttpStatus.BAD_REQUEST);
    }

    private Date adicionarPeriodoEmSegundos(Date data, Integer periodo){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(data);
        calendario.add(Calendar.SECOND, periodo);
        return calendario.getTime();
    }

    private void validarCpf(String cpf) {
        if(cpf == null || cpf.length() < 11)
            throw new VotoException("CPF inválido", HttpStatus.BAD_REQUEST);

        CpfDTO cpfDTO = consultaCPFClient.buscaCpf(cpf);

        if(cpfDTO.getStatus().equals(StatusCpf.UNABLE_TO_VOTE)){
            throw new VotoException("CPF não está apto a votar", HttpStatus.BAD_REQUEST);
        }
    }

    private VotoDTO toVotoDTO(Voto voto){
        VotoDTO votoDTO = new VotoDTO();
        votoDTO.setData(voto.getData());
        votoDTO.setCpf(voto.getCpf());
        votoDTO.setEscolha(voto.getEscolha());
        votoDTO.setId(voto.getId());
        votoDTO.setUsuarioDTO(new UsuarioDTO());
        votoDTO.getUsuarioDTO().setEmail(voto.getUsuario().getEmail());
        votoDTO.getUsuarioDTO().setUsername(voto.getUsuario().getUsername());
        votoDTO.getUsuarioDTO().setId(voto.getUsuario().getId());
        votoDTO.setSessaoDTO(new SessaoDTO());
        votoDTO.getSessaoDTO().setId(voto.getSessao().getId());
        votoDTO.getSessaoDTO().setDescricao(voto.getSessao().getDescricao());
        votoDTO.getSessaoDTO().setData(voto.getSessao().getData());
        votoDTO.getSessaoDTO().setPautaDTO(new PautaDTO());
        votoDTO.getSessaoDTO().getPautaDTO().setDescricao(voto.getSessao().getDescricao());
        votoDTO.getSessaoDTO().getPautaDTO().setId(voto.getSessao().getId());
        return votoDTO;
    }

    private Voto toVoto(VotoDTO votoDTO){
        Voto voto = new Voto();
        voto.setCpf(votoDTO.getCpf());
        voto.setData(new Date());
        voto.setEscolha(votoDTO.getEscolha());
        voto.setSessao(new Sessao());
        voto.getSessao().setId(votoDTO.getSessaoDTO().getId());
        voto.setUsuario(new Usuario());
        voto.getUsuario().setId(votoDTO.getUsuarioDTO().getId());
        return voto;
    }
}
