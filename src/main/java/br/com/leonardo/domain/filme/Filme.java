package br.com.leonardo.domain.filme;

import javax.persistence.Entity;

import br.com.leonardo.entity.FilmeBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Filme extends FilmeBaseEntity {
}
