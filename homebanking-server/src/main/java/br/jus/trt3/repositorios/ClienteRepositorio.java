package br.jus.trt3.repositorios;

import org.springframework.data.repository.CrudRepository;

import br.jus.trt3.model.Cliente;

public interface ClienteRepositorio extends CrudRepository<Cliente, Long>{

}
