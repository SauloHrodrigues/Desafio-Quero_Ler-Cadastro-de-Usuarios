package com.queroler.CadastroDeUsuario.service.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.notificacao.NotificacaoResponseDto;
import com.queroler.CadastroDeUsuario.exceptions.especies.NotificacaoNaoEncontradaException;
import com.queroler.CadastroDeUsuario.mappers.NotificacaoMapper;
import com.queroler.CadastroDeUsuario.model.Documento;
import com.queroler.CadastroDeUsuario.model.Notificacao;
import com.queroler.CadastroDeUsuario.model.Usuario;
import com.queroler.CadastroDeUsuario.repository.NotificacaoRepository;
import com.queroler.CadastroDeUsuario.service.NotificacaoService;
import com.queroler.CadastroDeUsuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NotificacaoServiceImpl implements NotificacaoService {

    private final UsuarioService usuarioService;
    private final NotificacaoRepository repository;
    private final NotificacaoMapper mapper;

    @Override
    public void gerar(Documento documento) {
        List<Usuario> usuarios = usuarioService.getUsuarioAll();
        List<Notificacao> notificacoes = new ArrayList<>();
        if (usuarios.isEmpty()) return;
        LocalDateTime agora = LocalDateTime.now();

        for (Usuario usuario : usuarios) {
            Notificacao notificacao = new Notificacao();
            notificacao.setDocumento(documento);
            notificacao.setUsuario(usuario);
            notificacao.setDataInicio(agora);
            notificacao.setDataFinal(agora.plusDays(30));
            notificacoes.add(notificacao);
        }
        repository.saveAll(notificacoes);
    }

    @Override
    public List<NotificacaoResponseDto> buscarNotificacoesNaoLidas() {
        Usuario usuario = usuarioService.getUsuarioLogado();
        LocalDateTime agora = LocalDateTime.now();
        List<Notificacao> notificacoes = repository.findByUsuarioIdAndLidaEmIsNullAndDataFinalAfter(usuario.getId(), agora);
        return mapper.toResponse(notificacoes);
    }

    @Override
    public void marcarComoVisualizado(Long id) {
        Optional<Notificacao> notificacao = repository.findById(id);
        LocalDateTime agora = LocalDateTime.now();
        if (notificacao.isEmpty()) {
            new NotificacaoNaoEncontradaException("Não há notificação com o ID: '" + id + "'.");
        }
        notificacao.get().setLidaEm(agora);
        repository.save(notificacao.get());
    }
}