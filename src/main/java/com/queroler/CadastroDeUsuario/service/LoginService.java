package com.queroler.CadastroDeUsuario.service;

import com.queroler.CadastroDeUsuario.dtos.AutenticacaoDto;
import com.queroler.CadastroDeUsuario.dtos.LoginRequestDto;
import com.queroler.CadastroDeUsuario.dtos.LoginResponseDto;

public interface LoginService {
    LoginResponseDto login(AutenticacaoDto request);
}
