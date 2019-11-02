package br.jus.trt3.homebanking.tests;

import static org.junit.Assert.assertTrue;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import br.jus.trt3.homebanking.controllers.ClienteController;
import br.jus.trt3.homebanking.models.Cliente;
import br.jus.trt3.homebanking.repositories.ClienteRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ClienteControllerTest {

    @MockBean
    private ClienteRepositorio clienteRepositorio;
    
    @Autowired
    ClienteController clienteController;

    @Autowired
    private MockMvc mockMvc;
    
    private List<Cliente> listaClientes;
    
    private void criaListaClientes() {
    	listaClientes = new ArrayList<Cliente>(
			Arrays.asList(
				new Cliente.ClienteBuilder("Rodrigo","Possa")
					.setId(1L)
			    	.setTelefone("12323-0998")
			    	.build(),
	        	new Cliente.ClienteBuilder("Tarcisio","Brandao")
					.setId(2L)
		        	.setEndereco("Av. A, N 1 - Serra")
		        	.build()
        	)
		);
    }

    @Before
    public void init() {
    	criaListaClientes();
        when(clienteRepositorio.findById(1L)).thenReturn(Optional.of(listaClientes.get(0)));
        when(clienteRepositorio.findById(2L)).thenReturn(Optional.of(listaClientes.get(1)));
        when(clienteRepositorio.findAll()).thenReturn(Arrays.asList(listaClientes.get(0), listaClientes.get(1)));
    }
    
    public List<Cliente> adicionaClientes() {
    	Cliente cliente1 = new Cliente.ClienteBuilder("Rodrigo","Possa")
			.setId(1L)
        	.setTelefone("12323-0998")
        	.build();
    	Cliente cliente2 = new Cliente.ClienteBuilder("Tarcisio","Brandao")
			.setId(2L)
        	.setEndereco("Av. A, N 1 - Serra")
        	.build();
    	List<Cliente> clientes = Arrays.asList(cliente1, cliente2);
    	return clientes;
    }
    
    @Test
    public void clienteControllerInjectedNotNull() throws Exception {
        assertTrue(clienteController != null);
    }

    @Test
    public void getTodosClientesOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clienteController")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
    		.andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value(listaClientes.get(0).getNome()))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value(listaClientes.get(1).getNome()))
        	.andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getClientePorIdOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clienteController/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
    		.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(listaClientes.get(0).getNome()))
        	.andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void postClienteOk() throws Exception {
        String strCliente = new ObjectMapper().writeValueAsString(listaClientes.get(0));
        mockMvc.perform(MockMvcRequestBuilders.post("/clienteController")
            .content(strCliente)
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        	.andExpect(MockMvcResultMatchers.status().isCreated())
        	.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(listaClientes.get(0).getNome()))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void putClienteOk() throws Exception {
    	Cliente cliente = listaClientes.get(0);
    	cliente.setEndereco("Endereco alterado");
    	cliente.setTelefone("2222-2222");
        String strCliente = new ObjectMapper().writeValueAsString(cliente);
        mockMvc.perform(MockMvcRequestBuilders.put("/clienteController/1")
            .content(strCliente)
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        	.andExpect(MockMvcResultMatchers.status().isOk())
        	.andExpect(MockMvcResultMatchers.jsonPath("$.endereco").value("Endereco alterado"))
        	.andExpect(MockMvcResultMatchers.jsonPath("$.telefone").value("2222-2222"))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteClienteOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/clienteController/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        	.andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

}
