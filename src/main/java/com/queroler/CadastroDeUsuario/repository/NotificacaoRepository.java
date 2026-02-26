package com.queroler.CadastroDeUsuario.repository;

import com.queroler.CadastroDeUsuario.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao,Long> {
    List<Notificacao> findByUsuarioIdAndLidaEmIsNullAndDataFinalAfter(Long usuarioId, LocalDateTime agora);
}
