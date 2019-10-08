package br.jus.trt3.observers;

import java.time.LocalDate;

import br.jus.trt3.models.Conta;
import br.jus.trt3.models.TipoOperacao;

public interface Observador {
	void registraEvento(Conta conta, double valor, LocalDate data, TipoOperacao tipoOperacao);
}
