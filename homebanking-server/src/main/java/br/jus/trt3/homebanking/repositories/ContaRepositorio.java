package br.jus.trt3.homebanking.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.jus.trt3.homebanking.models.Conta;

public interface ContaRepositorio extends CrudRepository<Conta, Long>{
	public Optional<Conta> findByCodigoConta(String codigoConta);
}
