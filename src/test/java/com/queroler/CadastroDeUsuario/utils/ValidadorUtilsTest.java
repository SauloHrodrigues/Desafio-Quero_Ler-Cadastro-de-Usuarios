package com.queroler.CadastroDeUsuario.utils;

import com.queroler.CadastroDeUsuario.exceptions.EmailInvalidoException;
import com.queroler.CadastroDeUsuario.exceptions.SenhaInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorUtilsTest {

    private ValidadorUtils validador;

    @BeforeEach
    void setup() {
        validador = new ValidadorUtils();
    }

    @Test
    void deveAceitarSenhaValida() {
        assertDoesNotThrow(() ->
                validador.senha("Senha123@")
        );
    }

    @Test
    void deveLancarExcecaoQuandoSenhaForNull() {
        SenhaInvalidaException ex = assertThrows(
                SenhaInvalidaException.class,
                () -> validador.senha(null)
        );

        assertEquals("Senha é obrigatória.", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoSenhaMenorQue8() {
        SenhaInvalidaException ex = assertThrows(
                SenhaInvalidaException.class,
                () -> validador.senha("S1@a")
        );

        assertEquals("A senha deve ter no mínimo 8 caracteres.", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoNaoTemMaiuscula() {
        SenhaInvalidaException ex = assertThrows(
                SenhaInvalidaException.class,
                () -> validador.senha("senha123@")
        );

        assertEquals("A senha deve conter pelo menos uma letra maiúscula.", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoNaoTemMinuscula() {
        SenhaInvalidaException ex = assertThrows(
                SenhaInvalidaException.class,
                () -> validador.senha("SENHA123@")
        );

        assertEquals("A senha deve conter pelo menos uma letra minúscula.", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoNaoTemNumero() {
        SenhaInvalidaException ex = assertThrows(
                SenhaInvalidaException.class,
                () -> validador.senha("Senha@@@")
        );

        assertEquals("A senha deve conter pelo menos um número.", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoNaoTemEspecial() {
        SenhaInvalidaException ex = assertThrows(
                SenhaInvalidaException.class,
                () -> validador.senha("Senha123")
        );

        assertEquals("A senha deve conter pelo menos um caractere especial.", ex.getMessage());
    }

    @Test
    void deveAceitarEmailValido() {
        assertDoesNotThrow(() ->
                validador.email("teste@email.com")
        );
    }

    @Test
    void deveLancarExcecaoQuandoEmailForNull() {
        EmailInvalidoException ex = assertThrows(
                EmailInvalidoException.class,
                () -> validador.email(null)
        );

        assertEquals("Email é obrigatório.", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoEmailInvalido() {
        EmailInvalidoException ex = assertThrows(
                EmailInvalidoException.class,
                () -> validador.email("email-invalido")
        );

        assertEquals("Email inválido.", ex.getMessage());
    }
}
