package br.com.leonardo.entity;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
  
  @JsonProperty(access = Access.READ_ONLY)
  @ApiModelProperty(value = "Data de cadastro")
  private LocalDateTime cadastro;
  
  @JsonProperty(access = Access.READ_ONLY)
  @ApiModelProperty(value = "Data de atualizacao")
  private LocalDateTime atualizacao;
  

}
