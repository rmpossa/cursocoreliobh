package laboratorioDia02;

import java.time.LocalDate;

public class Coaf implements Observador{
	

	@Override
	public void registraEvento(Conta conta, double valor, LocalDate data, TipoMovimentacao tipoMovimentacao) {
		System.out.println("COAF notificado");
		
	}

}
