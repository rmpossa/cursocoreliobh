package laboratorioDia02;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class ContaCorrenteSubscriber implements Subscriber<Movimentacao> {

    private Subscription subscription;

    private int counter = 0;

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("Inscrito!");
        this.subscription = subscription;
        this.subscription.request(1);
        System.out.println("onSubscribe requisitou 1 item");
    }

    @Override
    public void onNext(Movimentacao movimentacao) {
    	System.out.println("Operação no portifólio de contas correntes");
		System.out.println("Data: " + movimentacao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " ; Tipo Operação: " + movimentacao.getTipoOperacao() +" ; Valor: " + movimentacao.getValor());
        counter++;
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Finalizando inscrição!");
    }

    public int getCounter() {
        return counter;
    }

}