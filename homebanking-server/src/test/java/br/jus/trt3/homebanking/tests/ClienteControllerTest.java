package br.jus.trt3.homebanking.tests;

import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.jus.trt3.homebanking.controllers.ClienteController;
import br.jus.trt3.homebanking.repositories.ClienteRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @MockBean
    private ClienteRepositorio clienteRepositorio;
    
    @Autowired
    ClienteController clienteController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenClienteControllerInjected_thenNotNull() throws Exception {
        assertTrue(clienteController != null);
    }
    
    @Test
    public void whenPostRequestToUsersAndValidUser_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        String strCliente = "{ \"nome\": \"Rodrigo\", \"sobrenome\": \"Possa\", \"telefone\": \"12323-0998\", \"endereco\": \"Av. A, N 1 - Serra\"}";
//        String strCliente = "nome=Rodrigo&sobrenome=Possa&telefone=12323-0998&endereco=Av. A, N 1 - Serra";
        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
//            .content(strCliente)
            .param("nome","Rodrigo")
            .param("sobrenome","Possa")
            .param("endereco","Av. A, N 1 - Serra")
            .param("telefone","12323-0998")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(textPlainUtf8));
    }

}
