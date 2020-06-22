package br.com.leonardo.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import br.com.leonardo.domain.ator.Ator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class FilmeBaseEntity extends BaseEntity {

  private String titulo;
  
  private String descricao;
  
  private String resumo;
  
  private LocalDate lancamento;
  
  @ManyToMany
  private List<Ator> atores;

}
