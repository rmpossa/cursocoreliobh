package br.jus.trt3.homebanking.repositories;

import org.springframework.data.repository.CrudRepository;

import br.jus.trt3.homebanking.models.Conta;

public interface ContaRepositorio extends CrudRepository<Conta, Long>{
	public Conta findByCodigoConta(String codigoConta);
}
