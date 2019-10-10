package br.jus.trt3.homebanking.observers;

import br.jus.trt3.homebanking.models.Conta;
import br.jus.trt3.homebanking.models.TipoOperacao;

public interface Subject {

    void registerObserver(Observador o);
    void notifyObservers(Conta conta, double valor, TipoOperacao tipoOperacao);

}