package br.com.leonardo.domain.filme;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.leonardo.domain.ator.Ator;
import br.com.leonardo.entity.EntityBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Filme extends EntityBase {

  @ApiModelProperty(value = "Título do Filme")
  @NotBlank
  @Size(min = 3, max = 60)
  private String titulo;

  @ManyToMany
  private List<Ator> atores;

}
