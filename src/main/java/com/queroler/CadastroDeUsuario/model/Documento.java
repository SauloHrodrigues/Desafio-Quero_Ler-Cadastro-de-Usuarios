package com.queroler.CadastroDeUsuario.model;

import com.queroler.CadastroDeUsuario.enuns.DocumentoTipo;
import jakarta.persistence.*;
import lombok.*;

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
}