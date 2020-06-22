package br.com.leonardo.integration.ator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import com.github.javafaker.Faker;

import br.com.leonardo.api.handler.Error;
import br.com.leonardo.api.representation.model.AtorDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestsAtorAtualizacao {

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int port;

  private String getPath() {
    return "http://localhost:" + port + "/atores";
  }
  
  @Test
  void naoDeveAtualizarAtorNaoEncontrado() {
    Faker faker = new Faker();
    final var ator = new AtorDTO();
    ator.setId(faker.chuckNorris().fact());
    ator.setNome(faker.funnyName().name());
    ator.setNascimento(LocalDate.ofInstant(faker.date().birthday().toInstant(), ZoneId.systemDefault()));
    var response = restTemplate.exchange(getPath(), HttpMethod.PUT, new HttpEntity<>(ator), Error.class);
    assertTrue(
        response.getBody().getStatus().equals(HttpStatus.NOT_FOUND)
        && response.getBody().getTitulo().contains("Ator não encontrado")
    );
  }

}
