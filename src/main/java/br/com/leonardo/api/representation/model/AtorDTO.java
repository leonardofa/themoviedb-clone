package br.com.leonardo.api.representation.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import br.com.leonardo.api.representation.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtorDTO extends BaseDTO {

  @NotBlank
  @Size(min = 3, max = 128)
  private String nome;

  @NotNull
  @Past
  private LocalDate nascimento;

}
