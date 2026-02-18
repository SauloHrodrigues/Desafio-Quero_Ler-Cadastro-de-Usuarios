package com.queroler.CadastroDeUsuario.service.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.UsuarioRequestDTO;
import com.queroler.CadastroDeUsuario.mappers.UsuarioMapper;
import com.queroler.CadastroDeUsuario.model.Usuario;
import com.queroler.CadastroDeUsuario.repository.UsuarioRepository;
import com.queroler.CadastroDeUsuario.service.UsuarioService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    @Override
    public void criar(UsuarioRequestDTO request) {
        validarRequestDto(request);

        Usuario usuario = mapper.toEntity(request);
//                new Usuarios(registroDto.login(), encryptedPassword, registroDto.role() );
        System.out.println("ROLE = "+usuario.getRole());
        repository.save(usuario);
//        return ResponseEntity.ok().build();
    }

    protected void validarRequestDto(UsuarioRequestDTO dto){
        if(repository.findByLogin(mapper.loginTratado(dto.email())) != null){
            throw new RuntimeException("Usuario ja cadastrado ");
        }
    }
}
