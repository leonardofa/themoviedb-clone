package br.com.leonardo.integration.ator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import br.com.leonardo.api.representation.model.UsuarioDTO;
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
    var usuario = new UsuarioDTO();
    usuario.setNome(StringUtils.leftPad("0", 61));
    var response = restTemplate.postForEntity(getPath(), usuario, Error.class);
    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(response.getBody().getStatus(), HttpStatus.BAD_REQUEST);
    response.getBody().getCampos().stream()
        .filter(campo -> campo.getNome().equals("nome") && campo.getDescricao().contains("Tamanho inválido"))
        .findFirst().orElseThrow(() -> new AssertionError());

  }

  private void deveValidarCampoNascimentoAtorCadastro() {
    var usuario = new UsuarioDTO();
    usuario.setEmail("emailinvalido");
    var response = restTemplate.postForEntity(getPath(), usuario, Error.class);
    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(response.getBody().getStatus(), HttpStatus.BAD_REQUEST);
    response.getBody().getCampos().stream()
        .filter(campo -> campo.getNome().equals("email") && campo.getDescricao().contains("inválido")).findFirst()
        .orElseThrow(() -> new AssertionError());
  }

  private void deveValidarCamposObrigatorios() {
    var response = restTemplate.postForEntity(getPath(), new UsuarioDTO(), Error.class);
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
