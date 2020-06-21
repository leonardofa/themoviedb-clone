package br.com.leonardo.domain.episodio;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.com.leonardo.domain.serie.Serie;
import br.com.leonardo.entity.FilmeBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Episodio extends FilmeBaseEntity {

  @ManyToOne
  private Serie serie;

}
