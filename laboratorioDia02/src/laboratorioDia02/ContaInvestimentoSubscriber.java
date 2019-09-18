package laboratorioDia02;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class ContaInvestimentoSubscriber implements Subscriber<Movimentacao> {

    private Subscription subscription;

    private int counter = 0;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
        
    }

    @Override
    public void onNext(Movimentacao movimentacao) {
    	System.out.println("Operação no portifólio de contas de investimento");
		System.out.println("\tData: " + movimentacao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " ; Tipo Operação: " + movimentacao.getTipoOperacao() +" ; Valor: " + movimentacao.getValor());
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