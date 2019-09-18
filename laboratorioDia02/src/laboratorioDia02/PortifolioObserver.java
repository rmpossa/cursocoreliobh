package laboratorioDia02;

import java.time.LocalDate;
import java.util.concurrent.SubmissionPublisher;

public class PortifolioObserver implements Observador{
	private ContaCorrenteSubscriber contaCorrenteSubscriber;
	private ContaInvestimentoSubscriber contaInvestimentoSubscriber;
	
	private SubmissionPublisher<Movimentacao> publisherContaCorrente;
	private SubmissionPublisher<Movimentacao> publisherContaInvestimento;
	
//	Notice the private inner static class that contains the instance of the singleton class. 
//	When the singleton class is loaded, SingletonHelper class is not loaded into memory and 
//	only when someone calls the getInstance method, this class gets loaded and creates the 
//	Singleton class instance. This is the most widely used approach for Singleton class as 
//	it doesn’t require synchronization.
	
	private PortifolioObserver(){
		this.contaCorrenteSubscriber = new ContaCorrenteSubscriber();
		this.contaInvestimentoSubscriber = new ContaInvestimentoSubscriber();
		
		this.publisherContaCorrente = new SubmissionPublisher<Movimentacao>();
		this.publisherContaInvestimento = new SubmissionPublisher<Movimentacao>();
		
		this.publisherContaCorrente.subscribe(contaCorrenteSubscriber);
		this.publisherContaInvestimento.subscribe(contaInvestimentoSubscriber);
	}
    
    private static class PortifolioObserverHelper{
        private static final PortifolioObserver INSTANCE = new PortifolioObserver();
    }
    
    public static PortifolioObserver getInstance(){
        return PortifolioObserverHelper.INSTANCE;
    }	

   	@Override
	public void registraEvento(Conta conta, double valor, LocalDate data, TipoOperacao tipoOperacao) {
   		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setConta(conta);
		movimentacao.setValor(valor);
		movimentacao.setData(data);
		movimentacao.setTipoOperacao(tipoOperacao);
   		
   		if(conta instanceof ContaCorrente) {
   			publisherContaCorrente.submit(movimentacao);
   			
   		} else if (conta instanceof ContaInvestimento) {
   			publisherContaInvestimento.submit(movimentacao);
   		}
	}
   	
   	public void finalize() {
   		publisherContaCorrente.close();
   		publisherContaInvestimento.close();
   	}

   	
}
