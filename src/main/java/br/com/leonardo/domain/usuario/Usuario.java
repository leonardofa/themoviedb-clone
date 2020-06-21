package br.com.leonardo.domain.usuario;

import javax.persistence.Entity;

import br.com.leonardo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Usuario extends BaseEntity {

  private String nome;

  private String email;

}
