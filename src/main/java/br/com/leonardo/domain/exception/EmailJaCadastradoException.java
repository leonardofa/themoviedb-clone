package br.com.leonardo.domain.exception;

import org.springframework.http.HttpStatus;

public class EmailJaCadastradoException extends NegocioException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public EmailJaCadastradoException() {
    super(HttpStatus.BAD_REQUEST, "Email já está cadastrado");
  }

}
