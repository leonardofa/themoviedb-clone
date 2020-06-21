package br.com.leonardo.domain.ator;

import java.time.LocalDate;

import javax.persistence.Entity;

import br.com.leonardo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Ator extends BaseEntity {

  private String nome;

  private LocalDate nascimento;

}
