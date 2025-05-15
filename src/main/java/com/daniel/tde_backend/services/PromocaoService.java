package com.daniel.tde_backend.services;

import com.daniel.tde_backend.dto.PromocaoDTO;
import com.daniel.tde_backend.exceptions.InvalidPromotionException;
import com.daniel.tde_backend.exceptions.ResourceNotFoundException;
import com.daniel.tde_backend.models.Promocao;
import com.daniel.tde_backend.models.Usuario;
import com.daniel.tde_backend.models.enums.PromocaoStatus;
import com.daniel.tde_backend.models.enums.UsuarioTipo;
import com.daniel.tde_backend.repositories.PromocaoRepository;
import com.daniel.tde_backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PromocaoService {

    @Autowired
    private PromocaoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public PromocaoDTO solicitarPromocao(String idJogador) {
        Optional<Promocao> solicitacao = repository.findByIdJogadorAndStatus(idJogador, PromocaoStatus.PENDENTE);
        if (solicitacao.isPresent()) {
            throw new InvalidPromotionException("Já existe uma solicitação pendente para o usuário");
        }

        Usuario usuario = usuarioRepository.findById(idJogador).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        Promocao promocao = new Promocao();
        promocao.setIdJogador(usuario.getId());
        promocao.setNome(usuario.getNome());
        promocao.setDataCriacao(LocalDateTime.now());
        promocao.setStatus(PromocaoStatus.PENDENTE);

        promocao = repository.save(promocao);
        return new PromocaoDTO(promocao);
    }

    public PromocaoDTO atualizarStatus(String idJogador, PromocaoDTO dto) {
        Optional<Promocao> solicitacao = repository.findByIdJogadorAndStatus(idJogador, PromocaoStatus.PENDENTE);
        if (solicitacao.isEmpty()) {
            throw new InvalidPromotionException("Não existe uma solicitação pendente para o usuário");
        }

        Promocao promocao = solicitacao.get();
        promocao.setStatus(dto.getStatus());
        promocao.setDescricao(dto.getDescricao());
        if (dto.getStatus() == PromocaoStatus.APROVADO) {
            Usuario usuario = usuarioRepository.findById(idJogador).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
            usuario.setTipo(UsuarioTipo.PROMOTOR);
            usuarioRepository.save(usuario);
        }
        repository.save(promocao);
        return new PromocaoDTO(promocao);
    }

    /*
    @Transactional
    public void removerSolicitacao(String idJogador) {
        Promocao promocao = repository.findByIdJogadorAndStatus(idJogador, PromocaoStatus.PENDENTE).orElseThrow(() -> new ResourceNotFoundException("Solicitação não encontrada"));

        if (!promocao.getIdJogador().equals(idJogador)) {
            throw new InvalidAccessException("Solicitação não encontrada");
        }
        repository.deleteById(idJogador);
    }

     */
}
