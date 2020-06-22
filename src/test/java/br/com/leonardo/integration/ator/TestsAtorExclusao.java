package br.com.leonardo.integration.ator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

import br.com.leonardo.domain.ator.AtorRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestsAtorExclusao {
  
  @Autowired
  private AtorRepository repository;

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int port;

  private String getPath(String... rest) {
    final var path = "http://localhost:" + port + "/atores";
    try {
      return path + "/" + Arrays.asList(rest).stream().map(Object::toString).collect(Collectors.joining("/"));
    } catch (Exception e) {
      return path;
    }
  }

  @Test
  void deveExcluirUmAtor() {
    final var id = repository.findAll().get(0).getId();
    final var response = restTemplate.exchange(getPath("{id}"), HttpMethod.DELETE, null, ResponseEntity.class, id);

    assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);

    final var ator = repository.findById(id);
    assertFalse(ator.isPresent());
  }

}
