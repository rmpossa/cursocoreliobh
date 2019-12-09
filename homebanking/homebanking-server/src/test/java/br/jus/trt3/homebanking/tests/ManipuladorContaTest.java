package br.jus.trt3.homebanking.tests;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.jus.trt3.homebanking.models.Cliente;
import br.jus.trt3.homebanking.models.Conta;
import br.jus.trt3.homebanking.models.ContaCorrente;
import br.jus.trt3.homebanking.repositories.ContaRepositorio;
import br.jus.trt3.homebanking.repositories.MovimentacaoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ManipuladorContaTest {

	@MockBean
	private ContaRepositorio contaRepositorio;

	@MockBean
	private MovimentacaoRepositorio movimentacaoRepositorio;

//	@Autowired
//	ContaController contaController;

	@Autowired
	private MockMvc mockMvc;

	private List<Conta> listaContas;
	
	@Before
	public void init() {
		criaListaContas();
        when(contaRepositorio.findByCodigoConta("1111-1")).thenReturn(Optional.of(listaContas.get(0)));
        when(contaRepositorio.findByCodigoConta("2222-2")).thenReturn(Optional.of(listaContas.get(1)));
//        when(movimentacaoRepositorio.findByCodigoConta("2222-2")).thenReturn(Optional.of(listaContas.get(1)));
	}

	private void criaListaContas() {
		Cliente cliente1 = new Cliente.ClienteBuilder("Rodrigo","Possa")
		.setId(1L)
    	.setTelefone("12323-0998")
    	.build();
		Cliente cliente2 = new Cliente.ClienteBuilder("Tarcisio","Brandao")
			.setId(2L)
	    	.setEndereco("Av. A, N 1 - Serra")
	    	.build();
		Conta conta1 = new ContaCorrente("1111-1",1l, 1000.0, 10000.0);
		conta1.setCliente(cliente1);
		Conta conta2 = new ContaCorrente("2222-2",2l, 800.0, 5000.0);
		conta2.setCliente(cliente2);
		listaContas = new ArrayList<Conta>(Arrays.asList(conta1,conta2));
	}

    @Test
    public void deveTransferirValor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/transferir?codigoContaOrigem=1111-1&codigoContaDestino=2222-2&valor=300.0")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk())
    		.andExpect(MockMvcResultMatchers.jsonPath("$[0].saldo").value(699.97))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].saldo").value(1100.0))
        	.andDo(MockMvcResultHandlers.print());
    }

}
