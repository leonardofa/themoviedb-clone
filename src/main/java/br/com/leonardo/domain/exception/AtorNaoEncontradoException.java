package br.com.leonardo.domain.exception;

import org.springframework.http.HttpStatus;

public class AtorNaoEncontradoException extends NegocioException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public AtorNaoEncontradoException() {
    super(HttpStatus.NOT_FOUND, "Ator não encontrado");
  }

}
