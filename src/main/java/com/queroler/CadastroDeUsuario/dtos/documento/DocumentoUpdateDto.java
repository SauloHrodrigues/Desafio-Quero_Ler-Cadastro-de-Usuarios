package com.queroler.CadastroDeUsuario.dtos.documento;

import com.queroler.CadastroDeUsuario.enuns.DocumentoTipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "DocumentoUpdateDto", description = "DTO para atualização de um documento")
public record DocumentoUpdateDto(

        @Schema(description = "Título do documento", example = "Contrato Atualizado",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "O título é obrigatório")
        @Size(max = 150, message = "O título deve ter no máximo 150 caracteres")
        String titulo,

        @Schema(description = "Tipo do documento", example = "ADITIVO",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "O tipo do documento é obrigatório")
        DocumentoTipo tipo,

        @Schema(description = "Conteúdo atualizado do documento", example = "Conteúdo revisado do contrato...",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "O conteúdo é obrigatório")
        String conteudo
) {}