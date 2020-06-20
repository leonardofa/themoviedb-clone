package br.com.leonardo.api.service;

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

  private final UsuarioRepository usuarioRepository;

  public Usuario execute(final Usuario usuario) {
    final var usuarioEncontrado = usuarioRepository.findByEmail(usuario.getEmail());
    if (usuarioEncontrado.isPresent()) {
      throw new EmailJaCadastradoException();
    }
    
    return usuarioRepository.save(usuario);
  }

}
