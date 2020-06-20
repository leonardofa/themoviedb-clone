package br.com.leonardo.domain.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

  Optional<Usuario> findByEmail(String email);
  
}