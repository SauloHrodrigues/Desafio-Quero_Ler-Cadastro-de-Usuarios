package com.queroler.CadastroDeUsuario.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity(name = "tb_notificacoes")
@Table(name = "tb_notificacoes")
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFinal;
    private LocalDateTime lidaEm;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "documento_id", nullable = false)
    private Documento documento;
}
