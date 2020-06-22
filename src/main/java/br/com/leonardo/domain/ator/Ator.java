package br.com.leonardo.domain.ator;

import java.time.LocalDate;

import javax.persistence.Entity;

import br.com.leonardo.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Ator extends BaseEntity {

  private String nome;

  private LocalDate nascimento;

}
