package br.com.leonardo.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import br.com.leonardo.domain.exception.EmailJaCadastradoException;
import br.com.leonardo.domain.usuario.Usuario;
import br.com.leonardo.domain.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequestScope
@RequiredArgsConstructor
public class CadastraUsuarioService {

  private final UsuarioRepository repository;

  public Usuario execute(final Usuario usuario) {
    final var usuarioEncontrado = repository.findByEmail(usuario.getEmail());

    if (usuarioEncontrado.isPresent()) {
      throw new EmailJaCadastradoException(usuario.getEmail());
    }

    usuario.setId(null);
    usuario.setCadastro(OffsetDateTime.now());

    return repository.save(usuario);
  }

}
