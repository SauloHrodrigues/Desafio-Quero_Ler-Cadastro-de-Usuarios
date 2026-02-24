package com.queroler.CadastroDeUsuario.dtos.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(
   String login,

   @NotBlank
   @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
   @Pattern(regexp = ".*[A-Z].*", message = "Deve conter pelo menos uma letra maiúscula")
   @Pattern(regexp = ".*[a-z].*", message = "Deve conter pelo menos uma letra minúscula")
   @Pattern(regexp = ".*\\d.*", message = "Deve conter pelo menos um número")
   @Pattern(regexp = ".*[@$!%*?&.#_-].*", message = "Deve conter pelo menos um caractere especial")
   String senha
) {}
