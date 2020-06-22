package br.com.leonardo.api.representation;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.leonardo.api.handler.ValidationGroups;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class BaseDTO {

  @NotBlank(groups = ValidationGroups.Id.class)
  @ApiModelProperty(value = "Identificador único")
  private String id;
  
  @JsonProperty(access = Access.READ_ONLY)
  @ApiModelProperty(value = "Data de cadastro")
  private OffsetDateTime cadastro;

  @JsonProperty(access = Access.READ_ONLY)
  @ApiModelProperty(value = "Data de atualizacao")
  private OffsetDateTime atualizacao;

}
