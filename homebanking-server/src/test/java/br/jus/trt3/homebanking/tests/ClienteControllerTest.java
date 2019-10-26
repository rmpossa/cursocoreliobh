package br.jus.trt3.homebanking.tests;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void getClientesOk() throws Exception {
       	Cliente cliente1 = new Cliente.ClienteBuilder("Rodrigo","Possa")
			.setId(1L)
        	.setTelefone("12323-0998")
        	.build();
    	Cliente cliente2 = new Cliente.ClienteBuilder("Tarcisio","Brandao")
			.setId(2L)
        	.setEndereco("Av. A, N 1 - Serra")
        	.build();
       	List<Cliente> clientes = Arrays.asList(cliente1, cliente2);
    	given(clienteController.recuperaTodosClientes()).willReturn(clientes);
//    	given(clienteController.recuperaTodosClientes()).willReturn(adicionaClientes());
        mockMvc.perform(MockMvcRequestBuilders.get("/clienteController")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
    		.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Rodrigo"))
//			.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Tarcisio"))
        	.andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void postClienteOk() throws Exception {
        String strCliente = new ObjectMapper().writeValueAsString(
    		new Cliente.ClienteBuilder("Rodrigo","Possa")
    			.setId(1L)
            	.setEndereco("Av. A, N 1 - Serra")
            	.setTelefone("12323-0998")
            	.build());
        mockMvc.perform(MockMvcRequestBuilders.post("/clienteController")
            .content(strCliente)
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        	.andExpect(MockMvcResultMatchers.status().isCreated())
        	.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Rodrigo"))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void putClienteOk() throws Exception {
    	Cliente cliente = new Cliente.ClienteBuilder("Rodrigo","Possa")
    			.setId(1L)
            	.setEndereco("Av. A, N 1 - Serra")
            	.setTelefone("12323-0998")
            	.build();
        clienteRepositorio.save(cliente);
        System.out.println("id cliente:" + cliente.getId());
        String strCliente = new ObjectMapper().writeValueAsString(
    		new Cliente.ClienteBuilder("Tarcisio","Brandao")
    			.setId(1L)
            	.setEndereco("Endereco alterado")
            	.setTelefone("2222-2222")
            	.build());
        mockMvc.perform(MockMvcRequestBuilders.put("/clienteController/{id}",1)
            .content(strCliente)
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        	.andExpect(MockMvcResultMatchers.status().isOk())
        	.andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Tarcisio"))
        	.andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value("Brandao"))
        	.andExpect(MockMvcResultMatchers.jsonPath("$.endereco").value("Endereco alterado"))
        	.andExpect(MockMvcResultMatchers.jsonPath("$.telefone").value("2222-2222"))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andDo(MockMvcResultHandlers.print());
    }

}
