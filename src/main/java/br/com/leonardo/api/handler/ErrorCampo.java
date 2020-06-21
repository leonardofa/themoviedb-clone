package br.com.leonardo.api.handler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorCampo {

  private final String nome;

  private final String descricao;

}
