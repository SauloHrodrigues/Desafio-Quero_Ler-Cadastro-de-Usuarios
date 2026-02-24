package com.queroler.CadastroDeUsuario.model;

import com.queroler.CadastroDeUsuario.enuns.UsuarioRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@Entity(name = "tb_usuarios")
@Table(name = "tb_usuarios")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 80)
    @Column(name = "nome", nullable = false, length = 80)
    private String nome;

    @Email
    @Size(max = 150)
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Size(min = 11, max = 14)
    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @Past
    @Column(name = "data_nascimento")
    private LocalDate dataDeNascimento;


    @Column(name = "aceite_termos", nullable = false)
    private Boolean aceitarTermos;

    @Size(max = 80)
    @Column(name = "cidade", length = 80)
    private String cidade;

    @Size(max = 100)
    @Column(name = "estado", length = 100)
    private String estado;

    @Size(max = 100)
    @Column(name = "pais", length = 100)
    private String pais;

//    @Lob
//    @Basic(fetch = FetchType.LAZY)
//    @Column(name = "foto", columnDefinition = "BYTEA")
//    private byte[] foto;


    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private UsuarioRole role;


    @Size(max = 80)
    @Column(name = "login", nullable = false, unique = true, length = 80)
    private String login;


    @Size(min = 6)
    @Column(name = "senha", nullable = false)
    private String senha;

    public Usuario(String login, String password, UsuarioRole role) {
        this.login = login;
        this.role = role;
        this.senha = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return switch (this.role) {
            case ADMINISTRADOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"),
                    new SimpleGrantedAuthority("ROLE_MODERADOR"),
                    new SimpleGrantedAuthority("ROLE_LEITOR")
            );

            case MODERADOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_MODERADOR"),
                    new SimpleGrantedAuthority("ROLE_LEITOR")
            );

            case LEITOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_LEITOR")
            );
        };
    }


    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void gerarLogin() {
      this.login = this.email.toLowerCase();
    }
}