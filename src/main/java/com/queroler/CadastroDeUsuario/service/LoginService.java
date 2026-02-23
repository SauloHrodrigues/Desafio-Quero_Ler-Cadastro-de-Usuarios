package com.queroler.CadastroDeUsuario.service;

import com.queroler.CadastroDeUsuario.dtos.login.LoginRequestDto;
import com.queroler.CadastroDeUsuario.dtos.login.LoginResponseDto;

public interface LoginService {
    LoginResponseDto login(LoginRequestDto request);
}
