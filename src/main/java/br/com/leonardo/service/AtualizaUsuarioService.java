package br.com.leonardo.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import br.com.leonardo.domain.exception.UsuarioNaoEncontradoException;
import br.com.leonardo.domain.usuario.Usuario;
import br.com.leonardo.domain.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequestScope
@RequiredArgsConstructor
public class AtualizaUsuarioService {

  private final UsuarioRepository repository;

  public Usuario execute(final Usuario usuario) {
    final var usuarioEncontrado = repository.findById(usuario.getId())
        .orElseThrow(() -> new UsuarioNaoEncontradoException());
    usuario.setCadastro(usuarioEncontrado.getCadastro());
    usuario.setAtualizacao(OffsetDateTime.now());
    return repository.save(usuario);
  }

}
