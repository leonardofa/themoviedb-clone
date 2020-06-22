package br.com.leonardo.integration.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import br.com.leonardo.api.handler.Error;
import br.com.leonardo.domain.usuario.UsuarioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestsUsuarioExclusao {

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
  void deveExcluirUmUsuario() {
    final var id = usuarioRepository.findAll().get(0).getId();
    final var response = restTemplate.exchange(getPath("{id}"), HttpMethod.DELETE, null, ResponseEntity.class, id);

    assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);

    final var usuario = usuarioRepository.findById(id);
    assertFalse(usuario.isPresent());
  }

  @Test
  void naoDeveEscluirUsuarioNaoEncontrado() {
    final var id = "identificador";
    final var response = restTemplate.exchange(getPath("{id}"), HttpMethod.DELETE, null, Error.class, id);
    assertNotNull(response);
    assertTrue(response.getBody().getStatus().equals(HttpStatus.NOT_FOUND)
        && response.getBody().getTitulo().contains("Usuário não encontrado"));
  }

}
