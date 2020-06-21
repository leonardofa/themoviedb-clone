package br.com.leonardo.api.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.leonardo.api.service.CadastraUsuarioService;
import br.com.leonardo.domain.usuario.Usuario;
import br.com.leonardo.domain.usuario.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = { "Operações de Usuários" })
@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioRest {

  private final UsuarioRepository usuarioRepository;
  private final CadastraUsuarioService cadastraUsuarioService;

  @ApiOperation(value = "Cria um usuário para a API")
  @ApiResponses(value = { @ApiResponse(code = 201, message = "Cria e retorna um Usuário"),
      @ApiResponse(code = 400, message = "Erros negociais: validações da dados e fluxo"),
      @ApiResponse(code = 500, message = "Erros não experados"), })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Usuario create(@Valid @RequestBody Usuario usuario) {
    return cadastraUsuarioService.execute(usuario);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Usuario> readAll() {
    return usuarioRepository.findAll();
  }
//
//  @GetMapping("{id}")
//  @ResponseStatus(HttpStatus.OK)
//  public Usuario readOne(@PathVariable("id") String id) {
//    return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException());
//  }
//
//  @PutMapping
//  @ResponseStatus(HttpStatus.OK)
//  public Usuario update(@Valid @RequestBody Usuario usuario) {
//    return usuarioRepository.save(usuario);
//  }
//
//  @DeleteMapping
//  @ResponseStatus(HttpStatus.NO_CONTENT)
//  public void delete(@Valid @RequestBody Usuario usuario) {
//  }

}
