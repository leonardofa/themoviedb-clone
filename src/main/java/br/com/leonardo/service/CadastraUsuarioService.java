package br.com.leonardo.service;

import java.time.OffsetDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import br.com.leonardo.api.representation.model.UsuarioDTO;
import br.com.leonardo.domain.exception.EmailJaCadastradoException;
import br.com.leonardo.domain.usuario.Usuario;
import br.com.leonardo.domain.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequestScope
@RequiredArgsConstructor
public class CadastraUsuarioService {

  private final UsuarioRepository repository;

  private final ModelMapper modelMapper;

  public UsuarioDTO execute(final UsuarioDTO usuario) {
    final var usuarioEncontrado = repository.findByEmail(usuario.getEmail());

    if (usuarioEncontrado.isPresent()) {
      throw new EmailJaCadastradoException(usuario.getEmail());
    }

    final var usuarioModel = modelMapper.map(usuario, Usuario.class);
    usuarioModel.setId(null);
    usuarioModel.setCadastro(OffsetDateTime.now());

    return modelMapper.map(repository.save(usuarioModel), UsuarioDTO.class);
  }

}
