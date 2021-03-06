package br.com.leonardo.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import br.com.leonardo.domain.ator.Ator;
import br.com.leonardo.domain.ator.AtorRepository;
import br.com.leonardo.domain.exception.AtorNaoEncontradoException;
import lombok.RequiredArgsConstructor;

@Service
@RequestScope
@RequiredArgsConstructor
public class AtualizaAtorService {

  private final AtorRepository repository;

  public Ator execute(final Ator ator) {
    final var atorEncontrado = repository.findById(ator.getId())
        .orElseThrow(() -> new AtorNaoEncontradoException());
    ator.setCadastro(atorEncontrado.getCadastro());
    ator.setAtualizacao(OffsetDateTime.now());
    return repository.save(ator);
  }
}
