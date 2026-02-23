package com.queroler.CadastroDeUsuario.dtos;
import com.queroler.CadastroDeUsuario.enuns.UsuarioRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Schema(name = "UsuarioRequest", description = "Dados para cadastro de usuário")
public record UsuarioRequestDTO(

        @NotBlank
        @Size(max = 80)
        @Schema(description = "Nome completo do usuário", example = "Saulo Rodrigues", maxLength = 80)
        String nome,

        @NotBlank
        @Email
        @Size(max = 150)
        @Schema(description = "Email do usuário", example = "saulo@email.com", maxLength = 150)
        String email,

        @NotBlank
        @Size(min = 11, max = 14)
        @Schema(description = "CPF do usuário (somente números ou formatado)", example = "12345678901")
        String cpf,

        @Past
        @Schema(description = "Data de nascimento", example = "1995-08-21")
        LocalDate dataDeNascimento,

        @NotNull
        @AssertTrue(message = "É necessário aceitar os termos")
        @Schema(description = "Confirmação de aceite dos termos", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
        Boolean aceitarTermos,

        @Size(max = 80)
        @Schema(description = "Cidade do usuário", example = "Campinas", maxLength = 80)
        String cidade,

        @Size(max = 100)
        @Schema(description = "Estado do usuário", example = "São Paulo", maxLength = 100)
        String estado,

        @Size(max = 100)
        @Schema(description = "País do usuário", example = "Brasil", maxLength = 100)
        String pais,

        @NotBlank
        @Size(min = 6)
        @Schema(description = "Senha do usuário", example = "123456", minLength = 6)
        String senha,
        String login
) {}