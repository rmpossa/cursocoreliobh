package br.jus.trt3.repositorios;

import org.springframework.data.repository.CrudRepository;

import br.jus.trt3.model.Conta;

public interface ContaRepositorio extends CrudRepository<Conta, Long>{
	public Conta findByCodigoConta(String codigoConta);
}
