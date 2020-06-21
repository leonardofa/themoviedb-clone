package br.com.leonardo.api.handler;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "code", "status", "titulo", "momento" })
public class Error {

  private final HttpStatus status;

  @Builder.Default private final LocalDateTime momento = LocalDateTime.now();

  private final String titulo; 
  
  private Collection<ErrorCampo> campos;

  public int getCode() {
    return status.value();
  }

}
