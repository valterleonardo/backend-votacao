package br.com.votacao.api.pauta.service;

import br.com.votacao.api.pauta.dto.PautaDTO;
import br.com.votacao.api.pauta.entity.Pauta;
import br.com.votacao.api.pauta.repository.PautaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    public PautaDTO cadastrar(PautaDTO pautaDTO) {
        Pauta pauta = new Pauta();
        pauta.setDescricao(pautaDTO.getDescricao());
        pautaRepository.save(pauta);
        pautaDTO.setId(pauta.getId());
        log.info("Pauta cadastrada: " + pauta.toString());
        return pautaDTO;
    }

    public List<PautaDTO> listar() {
        List<Pauta> pautas = pautaRepository.findAll();
        List<PautaDTO> pautasDTO = new ArrayList<>();
        for (Pauta pauta : pautas) {
            pautasDTO.add(new PautaDTO(pauta.getId(), pauta.getDescricao()));
        }
        log.info("Pautas listadas: " + pautas.toString());
        return pautasDTO;
    }
}
