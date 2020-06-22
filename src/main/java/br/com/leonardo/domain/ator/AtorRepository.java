package br.com.leonardo.domain.ator;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AtorRepository extends JpaRepository<Ator, String> {

   Optional<Ator> findByNomeAndNascimento(String nome, LocalDate nascimento);
  
}
