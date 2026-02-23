package com.queroler.CadastroDeUsuario.service.implementacoes;

import com.queroler.CadastroDeUsuario.dtos.livro.LivroRequestDto;
import com.queroler.CadastroDeUsuario.dtos.livro.LivroResponseDto;
import com.queroler.CadastroDeUsuario.mappers.LivroMapper;
import com.queroler.CadastroDeUsuario.model.Livro;
import com.queroler.CadastroDeUsuario.model.Usuario;
import com.queroler.CadastroDeUsuario.repository.LivroRepository;
import com.queroler.CadastroDeUsuario.service.LivroService;
import com.queroler.CadastroDeUsuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository repository;
    private final LivroMapper mapper;
    private final UsuarioService usuarioService;

    @Override
    public LivroResponseDto cadastrar(LivroRequestDto requestDto) {
        validarCadastro(requestDto.titulo());
        Usuario usuario = usuarioService.getUsuarioLogado();

        Livro livro = mapper.toEntity(requestDto);

        livro.setUsuario(usuario);
        livro.setNomeUsuarioCadastro(usuario.getNome());

        Livro salvo = repository.save(livro);

        return mapper.toResponse(salvo);
    }

    @Override
    public LivroResponseDto buscarLivro(Long id) {
        Optional<Livro> livro = repository.findById(id);

        if (livro.isEmpty()) {
            throw new RuntimeException("Livro não encontrado");
        }
        return mapper.toResponse(livro.get());
    }

    protected void validarCadastro(String titulo) {
        if (repository.existsByTituloIgnoreCase(titulo)) {
            throw new RuntimeException("Livro já cadastrado");
        }
    }
}