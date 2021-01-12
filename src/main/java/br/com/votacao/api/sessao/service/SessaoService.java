package br.com.votacao.api.sessao.service;

import br.com.votacao.api.pauta.dto.PautaDTO;
import br.com.votacao.api.pauta.entity.Pauta;
import br.com.votacao.api.sessao.dto.SessaoDTO;
import br.com.votacao.api.sessao.dto.TotalSessaoDTO;
import br.com.votacao.api.sessao.entity.Sessao;
import br.com.votacao.api.sessao.repository.SessaoRepository;
import br.com.votacao.api.voto.entity.Voto;
import br.com.votacao.api.voto.enums.Escolha;
import br.com.votacao.api.voto.service.VotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;
    @Autowired
    private VotoService votoService;

    private static final Integer defaultPeriodo = 60;

    public SessaoDTO cadastrar(SessaoDTO sessaoDTO) {
        Sessao sessao = sessaoRepository.save(toSessao(sessaoDTO));
        log.info("Sessao cadastrada: " + sessao.toString());
        return toSessaoDTO(sessao);
    }

    private Sessao toSessao(SessaoDTO sessaoDTO){
        Sessao sessao = new Sessao();
        sessao.setData(new Date());
        sessao.setPeriodo(sessaoDTO.getPeriodo() == null ? defaultPeriodo : sessaoDTO.getPeriodo());
        sessao.setDescricao(sessaoDTO.getDescricao());
        sessao.setPauta(new Pauta());
        sessao.getPauta().setId(sessaoDTO.getPautaDTO().getId());
        return sessao;
    }

    private SessaoDTO toSessaoDTO(Sessao sessao){
        SessaoDTO sessaoDTO = new SessaoDTO();
        sessaoDTO.setData(sessao.getData());
        sessaoDTO.setPeriodo(sessao.getPeriodo());
        sessaoDTO.setDescricao(sessao.getDescricao());
        sessaoDTO.setId(sessao.getId());
        sessaoDTO.setPautaDTO(new PautaDTO(sessao.getPauta().getId(), sessao.getPauta().getDescricao()));
        return sessaoDTO;
    }

    public List<SessaoDTO> listar() {
        List<Sessao> sessoes = sessaoRepository.findAll();
        List<SessaoDTO> sessoesDTO = new ArrayList<>();
        for (Sessao sessao : sessoes){
            sessoesDTO.add(new SessaoDTO(
                                sessao.getId(),
                                sessao.getDescricao(),
                                sessao.getData(),
                                sessao.getPeriodo(),
                                new PautaDTO(sessao.getPauta().getId(), sessao.getPauta().getDescricao())
                                ));
        }
        log.info("Sessao listadas: " + sessoes.toString());
        return sessoesDTO;
    }

    public Sessao getSessao(Long id) {
        Optional<Sessao> sessao = sessaoRepository.findById(id);
        return sessao.isPresent() ? sessao.get() : null;
    }

    public TotalSessaoDTO totalizar(Long sessaoId) {
        List<Voto> votos = votoService.buscarVotosPorSessaoId(sessaoId);
        TotalSessaoDTO totalSessaoDTO = new TotalSessaoDTO();
        totalSessaoDTO.setTotalVotos(votos.stream().count());
        totalSessaoDTO.setTotalSim(votos.stream().filter(t -> t.getEscolha() == Escolha.SIM).count());
        totalSessaoDTO.setTotalNao(votos.stream().filter(t -> t.getEscolha() == Escolha.NAO).count());
        totalSessaoDTO.setVencedor(totalSessaoDTO.getTotalNao() > totalSessaoDTO.getTotalSim() ? Escolha.NAO : Escolha.SIM);
        log.info("Totalizador: " + totalSessaoDTO.toString());
        return totalSessaoDTO;
    }
}
