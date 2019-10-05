package br.jus.trt3.repositorios;

import org.springframework.data.repository.CrudRepository;

import br.jus.trt3.model.Cliente;
import br.jus.trt3.model.Movimentacao;

public interface MovimentacaoRepositorio extends CrudRepository<Movimentacao, Long>{
}
