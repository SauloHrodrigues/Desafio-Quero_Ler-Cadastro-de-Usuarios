package com.queroler.CadastroDeUsuario.service.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.AutenticacaoDto;
import com.queroler.CadastroDeUsuario.dtos.LoginRequestDto;
import com.queroler.CadastroDeUsuario.dtos.LoginResponseDto;
import com.queroler.CadastroDeUsuario.model.Usuario;
import com.queroler.CadastroDeUsuario.service.LoginService;
import com.queroler.CadastroDeUsuario.service.TokenServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginSeviceImpl implements LoginService {
    private final TokenServiceI tokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponseDto login(AutenticacaoDto request) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(request.login(), request.password());
        var autenticacao = authenticationManager.authenticate(userNamePassword);
        var token = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());
        return new LoginResponseDto(token);
    }
}
