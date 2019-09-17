package laboratorioDia02;

import java.time.LocalDate;

public class Coaf implements Observador{
//	Notice the private inner static class that contains the instance of the singleton class. 
//	When the singleton class is loaded, SingletonHelper class is not loaded into memory and 
//	only when someone calls the getInstance method, this class gets loaded and creates the 
//	Singleton class instance. This is the most widely used approach for Singleton class as 
//	it doesn’t require synchronization.
	
	private Coaf(){}
    
    private static class CoafHelper{
        private static final Coaf INSTANCE = new Coaf();
    }
    
    public static Coaf getInstance(){
        return CoafHelper.INSTANCE;
    }	

	@Override
	public void registraEvento(Conta conta, double valor, LocalDate data, TipoMovimentacao tipoMovimentacao) {
		System.out.println("COAF notificado");
		
	}

}
