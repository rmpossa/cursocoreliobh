package br.jus.trt3.repositories;

import org.springframework.data.repository.CrudRepository;

import br.jus.trt3.models.Conta;

public interface ContaRepositorio extends CrudRepository<Conta, Long>{
	public Conta findByCodigoConta(String codigoConta);
}
