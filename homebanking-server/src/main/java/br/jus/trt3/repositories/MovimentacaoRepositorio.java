package br.jus.trt3.repositories;

import org.springframework.data.repository.CrudRepository;

import br.jus.trt3.models.Cliente;
import br.jus.trt3.models.Movimentacao;

public interface MovimentacaoRepositorio extends CrudRepository<Movimentacao, Long>{
}
