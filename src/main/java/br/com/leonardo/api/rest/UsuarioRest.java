package br.com.leonardo.api.rest;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.leonardo.api.representation.model.UsuarioDTO;
import br.com.leonardo.domain.exception.UsuarioNaoEncontradoException;
import br.com.leonardo.domain.usuario.UsuarioRepository;
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
public class UsuarioRest {

  private final UsuarioRepository usuarioRepository;

  private final CadastraUsuarioService cadastraUsuarioService;

  private final ModelMapper modelMapper;

  @ApiOperation(value = "Cria um usuário para a API")
  @ApiResponses(value = { @ApiResponse(code = 201, message = "Cria e retorna um Usuário"),
      @ApiResponse(code = 400, message = "Erros negociais: validações da dados e fluxo"),
      @ApiResponse(code = 500, message = "Erros não experados"), })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UsuarioDTO create(@Valid @RequestBody UsuarioDTO usuario) {
    return cadastraUsuarioService.execute(usuario);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public UsuarioDTO[] readAll() {
    return modelMapper.map(usuarioRepository.findAll(), UsuarioDTO[].class);
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public UsuarioDTO readOne(@PathVariable("id") String id) {
    final var usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException());
    return modelMapper.map(usuario, UsuarioDTO.class);
  }

}
