package laboratorioDia02;

import java.time.LocalDate;

public class PortifolioObserver implements Observador{
//	Notice the private inner static class that contains the instance of the singleton class. 
//	When the singleton class is loaded, SingletonHelper class is not loaded into memory and 
//	only when someone calls the getInstance method, this class gets loaded and creates the 
//	Singleton class instance. This is the most widely used approach for Singleton class as 
//	it doesn’t require synchronization.
	
	private PortifolioObserver(){}
    
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
   			
   		} else if (conta instanceof ContaInvestimento) {
   			
   		}

	}

}
