package br.com.votacao.api.voto.service;

import br.com.votacao.api.pauta.dto.PautaDTO;
import br.com.votacao.api.sessao.dto.SessaoDTO;
import br.com.votacao.api.sessao.entity.Sessao;
import br.com.votacao.api.usuario.dto.UsuarioDTO;
import br.com.votacao.api.usuario.entity.Usuario;
import br.com.votacao.api.voto.dto.VotoDTO;
import br.com.votacao.api.voto.entity.Voto;
import br.com.votacao.api.voto.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    public VotoDTO votar(VotoDTO votoDTO) {
        return toVotoDTO(votoRepository.save(toVoto(votoDTO)));
    }

    private VotoDTO toVotoDTO(Voto voto){
        VotoDTO votoDTO = new VotoDTO();
        votoDTO.setData(voto.getData());
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
        voto.setData(votoDTO.getData());
        voto.setEscolha(votoDTO.getEscolha());
        voto.setSessao(new Sessao());
        voto.getSessao().setId(votoDTO.getSessaoDTO().getId());
        voto.setUsuario(new Usuario());
        voto.getUsuario().setId(votoDTO.getUsuarioDTO().getId());
        return voto;
    }
}
