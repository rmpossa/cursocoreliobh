package br.jus.trt3.homebanking.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.jus.trt3.homebanking.models.Cliente;


public interface ClienteRepositorio extends CrudRepository<Cliente, Long>{
	public Optional<Cliente> findByNome(String nome);
	public Optional<Cliente> findById(Long id);

}
