package br.jus.trt3.homebanking.observers;

import java.time.LocalDate;

import br.jus.trt3.homebanking.models.Conta;
import br.jus.trt3.homebanking.models.TipoOperacao;

public interface Observador {
	void registraEvento(Conta conta, double valor, LocalDate data, TipoOperacao tipoOperacao);
}
