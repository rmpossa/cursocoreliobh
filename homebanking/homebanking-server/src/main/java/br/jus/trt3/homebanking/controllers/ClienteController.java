package br.jus.trt3.homebanking.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.jus.trt3.homebanking.exceptions.ClienteNaoEncontradoException;
import br.jus.trt3.homebanking.models.Cliente;
import br.jus.trt3.homebanking.repositories.ClienteRepositorio;
import io.swagger.annotations.ApiOperation;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

	@GetMapping(path="clienteController")
    @ApiOperation(value="Recupera todos os clientes.")
    public Iterable<Cliente> recuperaTodosClientes() {
        return clienteRepositorio.findAll();
    }

	@GetMapping(path="clienteController/{id}")
    @ApiOperation(value="Recupera cliente por id.")
    public ResponseEntity<Cliente> recuperaClientePorId(@PathVariable("id") Long id) {
		String nomeOperacao = "recuperaClientePorId";
        Cliente cliente = clienteRepositorio.findById(id)
    		.orElseThrow(() -> new ClienteNaoEncontradoException(nomeOperacao,"id = " + id));
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @PostMapping(path="clienteController")
    @ApiOperation(value="Cria um novo cliente.")
    public ResponseEntity<Cliente> criaCliente(@Valid @RequestBody Cliente cliente) {
        clienteRepositorio.save(cliente);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }

    @PutMapping("/clienteController/{id}")
    @ApiOperation(value="Altera cliente.")
    public ResponseEntity<Cliente> atualizaCliente(@Valid @RequestBody Cliente cliente, @PathVariable("id") Long id) {
		String nomeOperacao = "atualizaCliente";
        return clienteRepositorio.findById(id).map(c -> {
        	c.setNome(cliente.getNome());
        	c.setSobrenome(cliente.getSobrenome());
        	c.setEndereco(cliente.getEndereco());
        	c.setTelefone(cliente.getTelefone());
        	clienteRepositorio.save(c);
            return new ResponseEntity<Cliente>(c, HttpStatus.OK);
        }).orElseThrow(() -> new ClienteNaoEncontradoException(nomeOperacao,cliente.getNome()));
        
    }

    @DeleteMapping("/clienteController/{id}")
    public ResponseEntity<?> excluiCliente(@PathVariable("id") Long id) {
		String nomeOperacao = "excluiCliente";
        return clienteRepositorio.findById(id).map(cliente -> {
        	clienteRepositorio.delete(cliente);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ClienteNaoEncontradoException(nomeOperacao,"id = " + id));
    }

}
