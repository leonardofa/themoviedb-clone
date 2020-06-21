package br.com.leonardo.api.representation;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class BaseDTO {
  
  @ApiModelProperty(value = "Identificador único")
  private String id;

  @JsonProperty(access = Access.READ_ONLY)
  @ApiModelProperty(value = "Data de cadastro")
  private OffsetDateTime cadastro;

  @JsonProperty(access = Access.READ_ONLY)
  @ApiModelProperty(value = "Data de atualizacao")
  private OffsetDateTime atualizacao;

}
