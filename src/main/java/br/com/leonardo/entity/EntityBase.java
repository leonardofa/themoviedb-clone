package br.com.leonardo.entity;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class EntityBase {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @ApiModelProperty(value = "Identificador único")
  private String id;
  
  @ApiModelProperty(value = "Data de cadastro")
  private LocalDateTime cadastro;
  
  @ApiModelProperty(value = "Data de atualizacao")
  private LocalDateTime atualizacao;
  

}
