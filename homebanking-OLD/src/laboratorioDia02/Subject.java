package laboratorioDia02;

public interface Subject {

    void registerObserver(Observador o);
    void notifyObservers(Conta conta, double valor, TipoOperacao tipoOperacao);

}