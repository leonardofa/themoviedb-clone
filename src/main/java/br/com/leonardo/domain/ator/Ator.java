package br.com.leonardo.domain.ator;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.leonardo.entity.EntityBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Ator extends EntityBase {

  @ApiModelProperty(value = "Nome do Ator")
  @NotBlank
  @Size(min = 3, max = 60)
  private String nome;

  @ApiModelProperty(value = "Data de nascimento do Ator")
  @NotNull
  private LocalDate nascimento;

}
