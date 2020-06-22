package br.com.leonardo.domain.usuario;

import javax.persistence.Entity;

import br.com.leonardo.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario extends BaseEntity {

  private String nome;

  private String email;

}
