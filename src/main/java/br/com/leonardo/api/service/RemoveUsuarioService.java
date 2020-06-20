package br.com.leonardo.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import br.com.leonardo.domain.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequestScope
@RequiredArgsConstructor
public class RemoveUsuarioService {

  private final UsuarioRepository usuarioRepository;

  public void execute(final String id) {
    usuarioRepository.deleteById(id);
  }

}
