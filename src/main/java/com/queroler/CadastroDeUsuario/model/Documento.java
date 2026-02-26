package com.queroler.CadastroDeUsuario.model;

import com.queroler.CadastroDeUsuario.enuns.DocumentoTipo;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity(name = "tb_documentos")
@Table(name = "tb_documentos")
@NoArgsConstructor
@AllArgsConstructor
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private DocumentoTipo tipo;
    private String conteudo;
    @UpdateTimestamp
    @Column(name = "ultima_alteracao")
    private LocalDateTime ultimaAlteracao;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}