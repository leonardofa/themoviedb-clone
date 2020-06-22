package br.com.leonardo.api.representation.model;

import java.time.LocalDate;

import br.com.leonardo.api.representation.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AtorDTO extends BaseDTO{
  
  private String id;
  
  private String nome;
  
  private LocalDate nascimento;
  

}
