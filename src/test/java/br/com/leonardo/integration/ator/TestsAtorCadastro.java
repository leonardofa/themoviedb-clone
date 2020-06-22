package br.com.leonardo.integration.ator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import br.com.leonardo.api.handler.Error;
import br.com.leonardo.api.representation.model.AtorDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestsAtorCadastro {

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
    response.getBody().getCampos().forEach(campo -> log.info(campo.toString()));
    assertTrue(response.getBody().getCampos().stream().allMatch(campo -> campo.getDescricao().contains("obrigatório")));
  }

  @Test
  void deveCadastrarNovoAtor() {
    assertTrue(false);
  }

  @Test
  void naoDeveCadastraAtorComNomeeDataNascimentoJaCadastrado() {
    assertTrue(false);
  }

}
