package br.jus.trt3.homebanking.repositories;

import org.springframework.data.repository.CrudRepository;

import br.jus.trt3.homebanking.models.Movimentacao;

public interface MovimentacaoRepositorio extends CrudRepository<Movimentacao, Long>{
}
