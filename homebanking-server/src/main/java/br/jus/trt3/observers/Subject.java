package br.jus.trt3.observers;

import br.jus.trt3.models.Conta;
import br.jus.trt3.models.TipoOperacao;

public interface Subject {

    void registerObserver(Observador o);
    void notifyObservers(Conta conta, double valor, TipoOperacao tipoOperacao);

}