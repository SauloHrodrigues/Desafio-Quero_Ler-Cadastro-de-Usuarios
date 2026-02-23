package com.queroler.CadastroDeUsuario.repository;

import com.queroler.CadastroDeUsuario.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro,Long> {

    boolean existsByTituloIgnoreCase(String titulo);
}