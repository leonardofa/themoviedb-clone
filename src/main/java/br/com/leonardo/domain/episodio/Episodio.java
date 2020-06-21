package br.com.leonardo.domain.episodio;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.leonardo.domain.ator.Ator;
import br.com.leonardo.domain.serie.Serie;
import br.com.leonardo.entity.EntityBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Episodio extends EntityBase {

  @ApiModelProperty(value = "Nome do Episódio")
  @NotBlank
  @Size(min = 3, max = 60)
  private String nome;

  @ApiModelProperty(value = "Data de lançamento do Episódio")
  @NotNull
  private LocalDate lancamento;

  @JsonIgnore
  @ManyToOne
  private Serie serie;

  @ManyToMany
  private List<Ator> atores;

}
