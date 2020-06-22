package br.com.leonardo.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import br.com.leonardo.domain.ator.Ator;
import br.com.leonardo.domain.ator.AtorRepository;
import br.com.leonardo.domain.exception.AtorJaCadastradoException;
import lombok.RequiredArgsConstructor;

@Service
@RequestScope
@RequiredArgsConstructor
public class CadastraAtorService {

  private final AtorRepository repository;

  public Ator execute(final Ator ator) {
    final var atorEncontrado = repository.findByNomeAndNascimento(ator.getNome(), ator.getNascimento());

    if (atorEncontrado.isPresent()) {
      throw new AtorJaCadastradoException(ator.getNome());
    }

    ator.setId(null);
    ator.setCadastro(OffsetDateTime.now());

    return repository.save(ator);
  }

}
