package br.jus.trt3.homebanking.repositories;

import org.springframework.data.repository.CrudRepository;

import br.jus.trt3.homebanking.models.Cliente;


public interface ClienteRepositorio extends CrudRepository<Cliente, Long>{
	public Cliente findByNome(String nome);

}
