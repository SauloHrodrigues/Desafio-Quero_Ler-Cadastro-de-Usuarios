package com.queroler.CadastroDeUsuario.mappers;

import com.queroler.CadastroDeUsuario.dtos.UsuarioRequestDTO;
import com.queroler.CadastroDeUsuario.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioMapper {
    public Usuario toEntity(UsuarioRequestDTO request){
        String senhaCriptografada = new BCryptPasswordEncoder().encode(request.senha());
        Usuario usuario = Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .cpf(request.cpf())
                .dataDeNascimento(request.dataDeNascimento())
                .aceiteTermos(request.aceiteTermos())
                .cidade(request.cidade())
                .estado(request.estado())
                .pais(request.pais())
                .role(request.role())
                .login(loginTratado(request.email()))
                .senha(senhaCriptografada)
                .build();
        return null;
    }

    public String loginTratado(String login){
        String loginGerado;
        if(login.contains("@")){
            loginGerado = login.substring(0, login.indexOf("@"));
        } else {
            loginGerado = login;
        }
        return loginGerado.toLowerCase();
    }
}