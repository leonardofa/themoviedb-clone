package br.com.leonardo;

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
import br.com.leonardo.domain.usuario.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestsUsuarioCadastro {
  
  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @LocalServerPort
  private int port;

  private String getPath() {
    return "http://localhost:" + port + "/usuarios";
  }

  @Test
  void deveValidarCamposUsuarioCadastro() {
    deveValidarCamposObrigatorios();
    deveValidarCampoEmailUsuarioCadastro();
    deveValidarCampoNomeTamanhoUsuarioCadastro();
  }

  private void deveValidarCampoNomeTamanhoUsuarioCadastro() {
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

  private void deveValidarCampoEmailUsuarioCadastro() {
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
  void deveCadastrarNovoUsuario() {
    Faker faker = new Faker();
    var usuario = new UsuarioDTO();
    usuario.setEmail(faker.internet().emailAddress());
    usuario.setNome(faker.funnyName().name());
    var response = restTemplate.postForEntity(getPath(), usuario, UsuarioDTO.class);
    assertNotNull(response);
    assertNotNull(response.getBody());
    assertNotNull(response.getBody().getId());
  }

  @Test
  void naoDeveCadastrarUsuarioComEmailJaCadastrado() {
    final var usuario = usuarioRepository.findByEmail("teste0001@teste.com").get();

    Faker faker = new Faker();
    var usuarioInvalido = new UsuarioDTO();
    usuarioInvalido.setEmail(usuario.getEmail());
    usuarioInvalido.setNome(faker.funnyName().name());
    var responseInvalid = restTemplate.postForEntity(getPath(), usuarioInvalido, Error.class);

    assertTrue(responseInvalid.getBody().getStatus().equals(HttpStatus.BAD_REQUEST)
        && responseInvalid.getBody().getTitulo().contains("Email já está cadastrado"));

  }

}
