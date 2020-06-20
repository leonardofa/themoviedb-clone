package br.com.leonardo.api.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.leonardo.domain.exception.UsuarioNaoEncontradoException;
import br.com.leonardo.domain.usuario.Usuario;
import br.com.leonardo.domain.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioRest {

  private final UsuarioRepository usuarioRepository;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Usuario create(@Valid @RequestBody Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Usuario> readAll() {
    return usuarioRepository.findAll();
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public Usuario readOne(@PathVariable("id") String id) {
    return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException());
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public Usuario update(@Valid @RequestBody Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@Valid @RequestBody Usuario usuario) {
  }

}
