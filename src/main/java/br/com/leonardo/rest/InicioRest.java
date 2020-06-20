package br.com.leonardo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class InicioRest {

  @GetMapping
  public String teste() {
    return "teste";
  }

}
