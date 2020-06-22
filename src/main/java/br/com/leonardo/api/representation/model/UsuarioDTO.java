package br.com.leonardo.api.representation.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.leonardo.api.representation.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO extends BaseDTO {

  @ApiModelProperty(value = "Nome do usuário")
  @NotBlank
  @Size(min = 3, max = 128)
  private String nome;

  @ApiModelProperty(value = "Email do usuário")
  @NotBlank
  @Email
  @Size(min = 3, max = 128)
  private String email;

}
