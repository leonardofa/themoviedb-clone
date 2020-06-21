package br.com.leonardo.domain.serie;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.com.leonardo.domain.episodio.Episodio;
import br.com.leonardo.entity.FilmeBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Serie extends FilmeBaseEntity {

  private Integer temporada;

  @OneToMany(mappedBy = "serie")
  private List<Episodio> episodios;

}
