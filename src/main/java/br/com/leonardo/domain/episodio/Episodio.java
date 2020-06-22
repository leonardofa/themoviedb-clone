package br.com.leonardo.domain.episodio;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.com.leonardo.domain.serie.Serie;
import br.com.leonardo.entity.FilmeBaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Episodio extends FilmeBaseEntity {

  @ManyToOne
  private Serie serie;

}
