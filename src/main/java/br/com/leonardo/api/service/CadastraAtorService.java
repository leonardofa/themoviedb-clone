package br.com.leonardo.api.service;

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
    final var atorEncontrado = repository.findByNome(ator.getNome());
    
    if (atorEncontrado.isPresent()) {
      throw new AtorJaCadastradoException(ator.getNome());
    }

    return repository.save(ator);
  }

}
