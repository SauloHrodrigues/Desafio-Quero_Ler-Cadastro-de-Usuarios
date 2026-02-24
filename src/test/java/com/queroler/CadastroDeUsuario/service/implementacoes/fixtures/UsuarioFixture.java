package com.queroler.CadastroDeUsuario.service.implementacoes.fixtures;

import com.queroler.CadastroDeUsuario.dtos.UsuarioRequestDTO;
import com.queroler.CadastroDeUsuario.dtos.UsuarioResponseDto;
import com.queroler.CadastroDeUsuario.dtos.administrador.AdministradorRequestDTO;
import com.queroler.CadastroDeUsuario.enuns.UsuarioRole;
import com.queroler.CadastroDeUsuario.model.Usuario;

import java.time.LocalDate;

public class UsuarioFixture {
    private final static Long ID = 1L;
    private final static String NOME = "José da Silva";
    private final static String EMAIL = "queroler@queroler.com.br";
    private static final String CPF = "98638028083";
    private static final LocalDate DATA_NASCIMENTO = LocalDate.of(1978, 9, 13);
    private static final boolean ACEITE_TERMOS = true;
    private static final String CIDADE = "São Paulo";
    private static final String ESTADO = "SP";
    private static final String PAIS = "Brasil";
    private static final UsuarioRole PERFIL = UsuarioRole.LEITOR;
    private static final String LOGIN = EMAIL;
    private static final String SENHA = "Teste123&";

    public static UsuarioRequestDTO requestDTO(){
        return new UsuarioRequestDTO(NOME,EMAIL,CPF,DATA_NASCIMENTO,ACEITE_TERMOS,CIDADE,ESTADO,PAIS,SENHA);
    }

     public static AdministradorRequestDTO requestAdmDTO(){
        return new AdministradorRequestDTO(NOME,EMAIL,CPF,DATA_NASCIMENTO,ACEITE_TERMOS,CIDADE,
                ESTADO,PAIS,UsuarioRole.ADMINISTRADOR,SENHA);
    }

    public static Usuario entity(){
        return entity(UsuarioRole.LEITOR);
    }
    public static Usuario entity(UsuarioRole role){
        return new Usuario(
                ID,NOME,EMAIL,CPF,DATA_NASCIMENTO,
                ACEITE_TERMOS,CIDADE,ESTADO,PAIS,role,LOGIN,SENHA
        );
    }

    public static UsuarioResponseDto response(Usuario usuario){
        return new UsuarioResponseDto(
                usuario.getId(), usuario.getNome(), usuario.getEmail(),
                usuario.getCpf(), usuario.getDataDeNascimento(), usuario.getCidade(),
                usuario.getCidade(), usuario.getPais(), usuario.getRole(), entity().getLogin()
        );
    }

}