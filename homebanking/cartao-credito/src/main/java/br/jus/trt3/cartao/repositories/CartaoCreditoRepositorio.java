package br.jus.trt3.cartao.repositories;

import org.springframework.data.repository.CrudRepository;

import br.jus.trt3.cartao.models.CartaoCredito;

public interface CartaoCreditoRepositorio extends CrudRepository<CartaoCredito, Long>{
	
	CartaoCredito findByNumeroCartao(String numeroCartao);

}