package br.com.leonardo.integration.ator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import com.github.javafaker.Faker;

import br.com.leonardo.api.handler.Error;
import br.com.leonardo.api.representation.model.AtorDTO;
import br.com.leonardo.domain.ator.AtorRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestsAtorCadastro {
  
  @Autowired
  private AtorRepository repository;

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int port;

  private String getPath() {
    return "http://localhost:" + port + "/atores";
  }

  @Test
  void deveValidarCamposAtorCadastro() {
    deveValidarCamposObrigatorios();
    deveValidarCampoNascimentoAtorCadastro();
    deveValidarCampoNomeTamanhoAtorCadastro();
  }

  private void deveValidarCampoNomeTamanhoAtorCadastro() {
    final var ator = new AtorDTO();
    ator.setNome(StringUtils.leftPad("0", 129));
    var response = restTemplate.postForEntity(getPath(), ator, Error.class);
    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(response.getBody().getStatus(), HttpStatus.BAD_REQUEST);
    response.getBody().getCampos().stream().filter(
        campo -> campo.getNome().equals("nome")
        && campo.getDescricao().contains("Tamanho inválido")
    ).findFirst().orElseThrow(() -> new AssertionError());

  }

  private void deveValidarCampoNascimentoAtorCadastro() {
    final var ator = new AtorDTO();
    ator.setNascimento(LocalDate.now());
    var response = restTemplate.postForEntity(getPath(), ator, Error.class);
    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(response.getBody().getStatus(), HttpStatus.BAD_REQUEST);
    response.getBody().getCampos().stream().filter(
        campo -> campo.getNome().equals("nascimento")
        && campo.getDescricao().equals("A data deve ser posterior à hoje")
    ).findFirst().orElseThrow(() -> new AssertionError());
  }

  private void deveValidarCamposObrigatorios() {
    final var response = restTemplate.postForEntity(getPath(), new AtorDTO(), Error.class);
    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(response.getBody().getStatus(), HttpStatus.BAD_REQUEST);
    assertTrue(
      response.getBody().getCampos().stream().allMatch(
          campo -> campo.getDescricao().contains("obrigatório")
      )
    );
  }

  @Test
  void deveCadastrarNovoAtor() {
    final Faker faker = new Faker();
    final var ator = new AtorDTO();
    ator.setNome(faker.name().fullName());
    ator.setNascimento(LocalDate.ofInstant(faker.date().birthday().toInstant(), ZoneId.systemDefault()));
    var response = restTemplate.postForEntity(getPath(), ator, AtorDTO.class);
    assertNotNull(response);
    assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    assertNotNull(response.getBody().getId());
  }

  @Test
  void naoDeveCadastraAtorComNomeeDataNascimentoJaCadastrado() {
    final var ator = repository.findAll().get(0);
    var response = restTemplate.postForEntity(getPath(), ator, Error.class);
    assertNotNull(response);
    assertTrue(
        response.getBody().getStatus().equals(HttpStatus.BAD_REQUEST)
        && response.getBody().getTitulo().contains("Ator já está cadastrado")
    );
  }

}
