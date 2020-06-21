package br.com.leonardo.domain.serie;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.leonardo.domain.episodio.Episodio;
import br.com.leonardo.entity.EntityBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Serie extends EntityBase {

  @ApiModelProperty(value = "Nome do Série")
  @NotBlank
  @Size(min = 3, max = 60)
  private String nome;

  @ApiModelProperty(value = "Temporada da Série")
  @NotBlank
  @Min(1)
  private Integer temporada;

  @ApiModelProperty(value = "Data de lançamento da Série")
  @NotNull
  private LocalDate lancamento;

  @OneToMany(mappedBy = "serie")
  private List<Episodio> episodios;

}
