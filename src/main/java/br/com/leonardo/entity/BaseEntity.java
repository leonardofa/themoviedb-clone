package br.com.leonardo.entity;

import java.time.OffsetDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  private OffsetDateTime cadastro;

  private OffsetDateTime atualizacao;

}
