package br.com.leonardo.integration.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import br.com.leonardo.api.representation.model.UsuarioDTO;
import br.com.leonardo.domain.usuario.UsuarioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TestsUsuarioAtualizacao {
  
  @Autowired
  private UsuarioRepository repository;

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int port;

  private String getPath() {
    return "http://localhost:" + port + "/usuarios";
  }

  @Test
  void deveValidarCamposUsuarioAtualizacao() {
    deveValidarCamposObrigatorios();
  }
  
  private void deveValidarCamposObrigatorios() {
    var response = restTemplate.exchange(getPath(), HttpMethod.PUT, new HttpEntity<>(new UsuarioDTO()), Error.class);
    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(response.getBody().getStatus(), HttpStatus.BAD_REQUEST);
    assertTrue(response.getBody().getCampos().stream().allMatch(campo -> campo.getDescricao().contains("obrigatório")));
  }
  
  @Test
  void naoDeveAtualizarUsuarioNaoEncontrado() {
    Faker faker = new Faker();
    final var usuario = new UsuarioDTO();
    usuario.setId(faker.chuckNorris().fact());
    usuario.setEmail(faker.internet().emailAddress());
    usuario.setNome(faker.funnyName().name());
    var response = restTemplate.exchange(getPath(), HttpMethod.PUT, new HttpEntity<>(usuario), Error.class);
    assertTrue(response.getBody().getStatus().equals(HttpStatus.NOT_FOUND)
        && response.getBody().getTitulo().contains("Usuário não encontrado"));
  }
  
  @Test
  void deveAtualizarUmUsuario() {
    Faker faker = new Faker();
    final var name = faker.name().fullName();
    
    final var usuario = repository.findAll().get(0);
    
    final var usuarioModificado = new UsuarioDTO();
    usuarioModificado.setId(usuario.getId());
    usuarioModificado.setNome(name);
    usuarioModificado.setEmail(usuario.getEmail());
    
    var response = restTemplate.exchange(getPath(), HttpMethod.PUT, new HttpEntity<>(usuarioModificado), UsuarioDTO.class);
    
    assertTrue(response.getStatusCode().equals(HttpStatus.OK) && response.getBody().getNome().equals(name));
  }
  
  

}
