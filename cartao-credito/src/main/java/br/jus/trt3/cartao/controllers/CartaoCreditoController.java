package br.jus.trt3.cartao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.jus.trt3.cartao.models.CartaoCredito;
import br.jus.trt3.cartao.repositories.CartaoCreditoRepositorio;

@RestController
public class CartaoCreditoController {
	
	@Autowired
	CartaoCreditoRepositorio cartaoCreditoRepositorio;
	
	@RequestMapping("/cartaoapp/debitaCartao")
	public void debitaCartao(
			@RequestParam(value="numeroCartao", defaultValue="") String numeroCartao,
			@RequestParam(value="valorDebitar", defaultValue="") double valorDebitar
			) {
		
		CartaoCredito cartaoCredito = cartaoCreditoRepositorio.findByNumeroCartao(numeroCartao);
		
		double limiteDebito = cartaoCredito.getLimite() + cartaoCredito.getSaldo(); 
		
		if (valorDebitar <= limiteDebito) {
			
				cartaoCredito.setSaldo(cartaoCredito.getSaldo() - valorDebitar);
			
		}
		
		cartaoCreditoRepositorio.save(cartaoCredito);
		
		//TODO: retornar mensagem de erro quando valor a debitar for maior do que o saldo
		
	}
	
	@RequestMapping("/cartaoapp/abasteceCartao")
	public void abasteceCartao(
			@RequestParam(value="numeroConta", defaultValue="") String numeroConta,
			@RequestParam(value="numeroCartao", defaultValue="") String numeroCartao,
			@RequestParam(value="valorAbastecer", defaultValue="") double valorAbastecer
			) {
		
		//TODO: Aplique o padrão SAGA ao seu código para garantir a consistência de saldo do correntista dos seus microsserviços.
		
		//TODO: chama api gateway para debitar conta
		
		//TODO: credita cartão com o valor debitado da conta
		
	}

}
