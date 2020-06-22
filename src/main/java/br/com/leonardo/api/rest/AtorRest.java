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
import br.com.leonardo.api.representation.model.AtorDTO;
import br.com.leonardo.domain.ator.Ator;
import br.com.leonardo.domain.ator.AtorRepository;
import br.com.leonardo.domain.exception.AtorNaoEncontradoException;
import br.com.leonardo.service.AtualizaAtorService;
import br.com.leonardo.service.CadastraAtorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = { "Operações de Atores" })
@RestController
@RequestMapping("atores")
@RequiredArgsConstructor
public class AtorRest extends MapperRest<Ator, AtorDTO> {

  private final AtorRepository repository;

  private final CadastraAtorService cadastraAtorService;
  
  private final AtualizaAtorService atualizaAtorService;

  @ApiOperation(value = "Cria um ator")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Cria e retorna um Ator", response = AtorDTO.class),
      @ApiResponse(code = 400, message = "Erros negociais: validações da dados e fluxo", response = Error.class),
      @ApiResponse(code = 500, message = "Erros não experados")
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AtorDTO create(@Valid @RequestBody AtorDTO ator) {
    final var atorCriado = cadastraAtorService.execute(toModel(ator));
    return fromModel(atorCriado);
  }

  @ApiOperation(value = "Retorna todos os atores")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Retorna a lista de atores", response = AtorDTO[].class),
      @ApiResponse(code = 400, message = "Erros negociais: validações da dados e fluxo", response = Error.class),
      @ApiResponse(code = 500, message = "Erros não experados")
  })
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<AtorDTO> readAll() {
    return fromModel(repository.findAll());
  }

  @ApiOperation(value = "Retorna um ator")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Retorna um ator", response = AtorDTO.class),
      @ApiResponse(code = 400, message = "Erros negociais: validações da dados e fluxo", response = Error.class),
      @ApiResponse(code = 500, message = "Erros não experados")
  })
  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public AtorDTO readOne(@PathVariable("id") String id) {
    return fromModel(repository.findById(id).orElseThrow(() -> new AtorNaoEncontradoException()));
  }

  @ApiOperation(value = "Atualiza um ator")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Ator atualizado", response = AtorDTO.class),
      @ApiResponse(code = 400, message = "Erros negociais: validações da dados e fluxo", response = Error.class),
      @ApiResponse(code = 500, message = "Erros não experados")
  })
  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public AtorDTO update(@Valid @RequestBody AtorDTO ator) {
    return fromModel(atualizaAtorService.execute(toModel(ator)));
  }

  @ApiOperation(value = "Deleta um ator")
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "Resposta sem conteúdo"),
      @ApiResponse(code = 400, message = "Erros negociais: validações da dados e fluxo", response = Error.class),
      @ApiResponse(code = 500, message = "Erros não experados")
  })
  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") String id) {
    repository.delete(repository.findById(id).orElseThrow(() -> new AtorNaoEncontradoException()));
  }


}
