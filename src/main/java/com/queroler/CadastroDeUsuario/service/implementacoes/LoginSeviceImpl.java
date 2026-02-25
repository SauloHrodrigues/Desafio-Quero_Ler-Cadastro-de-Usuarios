package com.queroler.CadastroDeUsuario.service.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.login.LoginRequestDto;
import com.queroler.CadastroDeUsuario.dtos.login.LoginResponseDto;
import com.queroler.CadastroDeUsuario.model.Usuario;
import com.queroler.CadastroDeUsuario.service.LoginService;
import com.queroler.CadastroDeUsuario.service.TokenServiceI;
import com.queroler.CadastroDeUsuario.service.UsuarioService;
import com.queroler.CadastroDeUsuario.utils.ValidadorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginSeviceImpl implements LoginService {
    private final TokenServiceI tokenService;
    private final AuthenticationManager authenticationManager;
    private final ValidadorUtils validar;
    private final UsuarioService usuarioService;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        validar.senha(request.senha());
        usuarioService.validaLogin(request.login());
        var userNamePassword = new UsernamePasswordAuthenticationToken(request.login(), request.senha());
        var autenticacao = authenticationManager.authenticate(userNamePassword);
        var token = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());
        return new LoginResponseDto(token);
    }
}
