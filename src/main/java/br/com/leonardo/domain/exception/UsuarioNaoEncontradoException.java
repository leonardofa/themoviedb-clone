package br.com.leonardo.domain.exception;

import org.springframework.http.HttpStatus;

public class UsuarioNaoEncontradoException extends NegocioException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public UsuarioNaoEncontradoException() {
    super(HttpStatus.NOT_FOUND, "Usuário não encontrado");
  }

}
