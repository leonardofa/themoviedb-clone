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

import br.com.leonardo.api.service.CadastraAtorService;
import br.com.leonardo.domain.ator.Ator;
import br.com.leonardo.domain.ator.AtorRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = { "Operações de Atores" })
@RestController
@RequestMapping("atores")
@RequiredArgsConstructor
public class AtorRest {

  private final AtorRepository repository;

  private final CadastraAtorService cadastraAtorService;

  @ApiOperation(value = "Cria um Ator")
  @ApiResponses(value = { @ApiResponse(code = 201, message = "Cria e retorna um Ator"),
      @ApiResponse(code = 400, message = "Erros negociais: validações da dados e fluxo"),
      @ApiResponse(code = 500, message = "Erros não experados"), })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Ator create(@Valid @RequestBody Ator ator) {
    return cadastraAtorService.execute(ator);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Ator> readAll() {
    return repository.findAll();
  }

}
