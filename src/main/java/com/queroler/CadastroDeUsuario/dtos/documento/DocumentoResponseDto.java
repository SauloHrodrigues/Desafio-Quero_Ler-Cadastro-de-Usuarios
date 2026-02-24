package com.queroler.CadastroDeUsuario.dtos.documento;

import com.queroler.CadastroDeUsuario.enuns.DocumentoTipo;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "DocumentoResponseDto", description = "DTO de resposta do documento")
public record DocumentoResponseDto(

        @Schema(description = "Identificador do documento", example = "1")
        Long id,

        @Schema(description = "Título do documento", example = "Contrato de Prestação de Serviço")
        String titulo,

        @Schema(description = "Tipo do documento", example = "CONTRATO")
        DocumentoTipo tipo,

        @Schema(description = "Conteúdo do documento", example = "Este contrato estabelece as seguintes condições...")
        String conteudo
) {}