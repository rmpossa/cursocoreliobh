package laboratorioDia02;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtratoMovimentacoes implements Observador{
	private Map<Conta,List<Movimentacao>> extratoContas;
	
	public ExtratoMovimentacoes() {
		extratoContas = new HashMap<Conta,List<Movimentacao>>();	
	}

	@Override
	public void registraEvento(Conta conta, double valor, LocalDate data, TipoMovimentacao tipoMovimentacao) {
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setConta(conta);
		movimentacao.setData(data);
		movimentacao.setValor(valor);
		movimentacao.setTipoMovimentacao(tipoMovimentacao);
		
		if(!extratoContas.containsKey(conta)) {
			extratoContas.put(conta, new ArrayList<Movimentacao>());
		}
		
		List<Movimentacao> extratoContaEspecifica = extratoContas.get(conta);
		extratoContaEspecifica.add(movimentacao);
		
	}
	
	public void imprimirExtrato(Conta conta) {
		List<Movimentacao> extratoContaEspecifica = extratoContas.get(conta);
		
		System.out.println("Conta Id: " + conta.getId());
		
		extratoContaEspecifica.forEach(
				m -> System.out.println("Data: " + m.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " ; Tipo Movimentacao: " + m.getTipoMovimentacao() +" ; Valor: " + m.getValor()));	
	}
	
	
}
