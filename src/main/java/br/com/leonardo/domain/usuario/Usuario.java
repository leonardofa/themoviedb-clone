package br.com.leonardo.domain.usuario;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.leonardo.entity.EntityBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Usuario extends EntityBase {

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
