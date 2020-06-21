package br.com.leonardo.domain.exception;

import org.springframework.http.HttpStatus;

public class AtorJaCadastradoException extends NegocioException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public AtorJaCadastradoException(String nome) {
    super(HttpStatus.BAD_REQUEST, "Ator já está cadastrado: " + nome);
  }

}
