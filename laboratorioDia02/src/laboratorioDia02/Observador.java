package laboratorioDia02;

import java.time.LocalDate;

public interface Observador {
	void registraEvento(Conta conta, double valor, LocalDate data, TipoMovimentacao tipoMovimentacao);
}
