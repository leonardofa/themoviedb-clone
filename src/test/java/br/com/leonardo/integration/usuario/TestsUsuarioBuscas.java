package br.com.leonardo.integration.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.leonardo.api.handler.Error;
import br.com.leonardo.api.representation.model.UsuarioDTO;
import br.com.leonardo.domain.usuario.UsuarioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestsUsuarioBuscas {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int port;

  private String getPath(String... rest) {
    final var path = "http://localhost:" + port + "/usuarios";
    try {
      return path + "/" + Arrays.asList(rest).stream().map(Object::toString).collect(Collectors.joining("/"));
    } catch (Exception e) {
      return path;
    }
  }

  @Test
  void deveRecuperarTodosUsuarios() {
    final var response = restTemplate.getForEntity(getPath(), UsuarioDTO[].class);
    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(response.getBody().length, 2);
  }

  @Test
  void deveRecuperarSomenteUmUsuarios() {
    final var id = usuarioRepository.findAll().get(0).getId();
    final var response = restTemplate.getForEntity(getPath("{id}"), JsonNode.class, id);
    assertNotNull(response);
    assertNotNull(response.getBody().get("cadastro"));
    assertEquals(response.getBody().get("id").asText(), id);
  }

  @Test
  void deveRetornarErroQuandoBuscarUsuarioInexistente() {
    final var id = "identificador";
    final var response = restTemplate.getForEntity(getPath("{id}"), Error.class, id);
    assertNotNull(response);
    assertTrue(response.getBody().getStatus().equals(HttpStatus.NOT_FOUND)
        && response.getBody().getTitulo().contains("Usuário não encontrado"));
  }

}
