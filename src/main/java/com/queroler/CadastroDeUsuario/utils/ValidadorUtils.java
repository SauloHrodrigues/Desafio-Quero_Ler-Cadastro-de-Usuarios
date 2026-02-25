package com.queroler.CadastroDeUsuario.utils;

import com.queroler.CadastroDeUsuario.exceptions.especies.EmailInvalidoException;
import com.queroler.CadastroDeUsuario.exceptions.especies.SenhaInvalidaException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUtils {
    public void senha(String senha) {

        if (senha == null || senha.isBlank()) {
            throw new SenhaInvalidaException("Senha é obrigatória.");
        }

        if (senha.length() < 8) {
            throw new SenhaInvalidaException("A senha deve ter no mínimo 8 caracteres.");
        }

        if (!senha.matches(".*[A-Z].*")) {
            throw new SenhaInvalidaException("A senha deve conter pelo menos uma letra maiúscula.");
        }

        if (!senha.matches(".*[a-z].*")) {
            throw new SenhaInvalidaException("A senha deve conter pelo menos uma letra minúscula.");
        }

        if (!senha.matches(".*\\d.*")) {
            throw new SenhaInvalidaException("A senha deve conter pelo menos um número.");
        }

        if (!senha.matches(".*[@$!%*?&.#_-].*")) {
            throw new SenhaInvalidaException("A senha deve conter pelo menos um caractere especial.");
        }
    }

    public void email(String email) {
        if (email == null || email.isBlank()) {
            throw new EmailInvalidoException("Email é obrigatório.");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new EmailInvalidoException("Email inválido.");
        }
    }
}

