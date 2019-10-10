package laboratorioDia02;

public class ClienteAdapterImpl implements ClienteAdapter {
	
	private Cliente cliente;
	
	public ClienteAdapterImpl(Cliente cliente) {
		this.cliente = cliente;
	}
	

	@Override
	public String getNomeClientePadrao() {
		return cliente.getNomeCompleto();
	}

	@Override
	public String getNomeClienteFormatoAmericano() {
		return cliente.getSobrenome() + " " + cliente.getNome();
	}

}
