package com.queroler.CadastroDeUsuario.repository;

import com.queroler.CadastroDeUsuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    UserDetails findByLogin(String login);
    Optional<Usuario> findByEmailIgnoreCase(String email);
}
