package br.com.votacao.api.sessao.service;

import br.com.votacao.api.pauta.dto.PautaDTO;
import br.com.votacao.api.pauta.entity.Pauta;
import br.com.votacao.api.sessao.dto.SessaoDTO;
import br.com.votacao.api.sessao.entity.Sessao;
import br.com.votacao.api.sessao.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;
    private static final Integer defaultPeriodo = 60;

    public SessaoDTO cadastrar(SessaoDTO sessaoDTO) {
        return toSessaoDTO(sessaoRepository.save(toSessao(sessaoDTO)));
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
        return sessoesDTO;
    }

    public Sessao getSessao(Long id) {
        Optional<Sessao> sessao = sessaoRepository.findById(id);
        return sessao.isPresent() ? sessao.get() : null;
    }
}
