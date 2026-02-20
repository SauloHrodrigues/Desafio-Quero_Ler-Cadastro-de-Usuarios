package com.queroler.CadastroDeUsuario.service.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.UsuarioRequestDTO;
import com.queroler.CadastroDeUsuario.mappers.UsuarioMapper;
import com.queroler.CadastroDeUsuario.model.Usuario;
import com.queroler.CadastroDeUsuario.repository.UsuarioRepository;
import com.queroler.CadastroDeUsuario.service.UsuarioService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    @Override
    public void criar(UsuarioRequestDTO registroDto) {
        if (repository.findByLogin(registroDto.login()) != null) throw new RuntimeException("Usuario Ja cadastrado");
        String encryptedPassword = new BCryptPasswordEncoder().encode(registroDto.senha());
        Usuario usuario = Usuario.builder()
                .nome(registroDto.nome())
                .email(registroDto.email())
                .cpf(registroDto.cpf())
                .dataDeNascimento(registroDto.dataDeNascimento())
                .aceitarTermos(registroDto.aceitarTermos())
                .cidade(registroDto.cidade())
                .estado(registroDto.estado())
                .pais(registroDto.pais())
                .role(registroDto.role())
                .login(registroDto.login())
                .senha(encryptedPassword)
                .build();
        System.out.println("senha= " + encryptedPassword);
        repository.save(usuario);

    }

    protected void validarRequestDto(UsuarioRequestDTO dto){
        if(repository.findByLogin(mapper.loginTratado(dto.email())) != null){
            throw new RuntimeException("Usuario ja cadastrado ");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}
