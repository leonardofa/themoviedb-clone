package br.com.leonardo.api.representation.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.leonardo.api.representation.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class UsuarioDTO extends BaseDTO{

  @ApiModelProperty(value = "Nome do usuário")
  @NotBlank
  @Size(min = 3, max = 60)
  private String nome;

  @ApiModelProperty(value = "Email do usuário")
  @NotBlank
  @Email
  @Size(min = 3, max = 255)
  private String email;

}
