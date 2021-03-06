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

import br.com.leonardo.api.handler.Error;
import br.com.leonardo.api.representation.model.UsuarioDTO;
import br.com.leonardo.domain.exception.UsuarioNaoEncontradoException;
import br.com.leonardo.domain.usuario.Usuario;
import br.com.leonardo.domain.usuario.UsuarioRepository;
import br.com.leonardo.service.AtualizaUsuarioService;
import br.com.leonardo.service.CadastraUsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = { "Operações de Usuários" })
@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioRest extends MapperRest<Usuario, UsuarioDTO> {

  private final UsuarioRepository repository;

  private final CadastraUsuarioService cadastraUsuarioService;

  private final AtualizaUsuarioService atualizaUsuarioService;

  @ApiOperation(value = "Cria um usuário da API")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Cria e retorna um Usuário", response = UsuarioDTO.class),
      @ApiResponse(code = 400, message = "Erros negociais: validações da dados e fluxo", response = Error.class),
      @ApiResponse(code = 500, message = "Erros não experados")
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UsuarioDTO create(@Valid @RequestBody UsuarioDTO usuario) {
    final var usuarioCriado = cadastraUsuarioService.execute(toModel(usuario));
    return fromModel(usuarioCriado);
  }

  @ApiOperation(value = "Retorna todos os usuários")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Retorna uma lista de usuários", response = UsuarioDTO[].class),
      @ApiResponse(code = 400, message = "Erros negociais: validações da dados e fluxo", response = Error.class),
      @ApiResponse(code = 500, message = "Erros não experados")
  })
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<UsuarioDTO> readAll() {
    return fromModel(repository.findAll());
  }

  @ApiOperation(value = "Retorna um usuário")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Retorna um usuário", response =  UsuarioDTO.class),
      @ApiResponse(code = 400, message = "Erros negociais: validações da dados e fluxo", response = Error.class),
      @ApiResponse(code = 500, message = "Erros não experados")
  })
  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public UsuarioDTO readOne(@PathVariable("id") String id) {
    final var usuario = repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException());
    return fromModel(usuario);
  }

  @ApiOperation(value = "Atualiza um usuário")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Retorna o usuário atualizado", response = UsuarioDTO.class),
      @ApiResponse(code = 400, message = "Erros negociais: validações da dados e fluxo", response = Error.class),
      @ApiResponse(code = 500, message = "Erros não experados")
  })
  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public UsuarioDTO update(@Valid @RequestBody UsuarioDTO usuario) {
    final var usuarioSalvo = atualizaUsuarioService.execute(toModel(usuario));
    return fromModel(usuarioSalvo);
  }

  @ApiOperation(value = "Deleta um usuário")
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "Resposta sem conteúdo"),
      @ApiResponse(code = 400, message = "Erros negociais: validações da dados e fluxo", response = Error.class),
      @ApiResponse(code = 500, message = "Erros não experados")
  })
  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") String id) {
    final var usuario = repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException());
    repository.delete(usuario);
  }

}
