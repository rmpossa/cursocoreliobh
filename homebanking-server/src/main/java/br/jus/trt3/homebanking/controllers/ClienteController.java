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

    @PostMapping(path="clienteController")
    @ApiOperation(value="Cria um novo cliente.")
    public ResponseEntity<Cliente> criaCliente(@Valid @RequestBody Cliente cliente) {
        clienteRepositorio.save(cliente);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }

//    @PutMapping("/clienteController/{nomeCliente}")
//    public Cliente atualizaCliente(@PathVariable String nomeCliente, 
//    	@ApiParam(value="sobrenome do cliente") @RequestParam(value="sobrenome") String sobrenome,
//    	@ApiParam(value="endereço do cliente") @RequestParam(value="endereco") String endereco,
//    	@ApiParam(value="telefone do cliente") @RequestParam(value="telefone") String telefone) {
//		String nomeOperacao = "atualizaCliente";
//        return clienteRepositorio.findByNome(nomeCliente).map(cliente -> {
//        	cliente.setSobrenome(sobrenome);
//        	cliente.setEndereco(endereco);
//        	cliente.setTelefone(telefone);
//            return clienteRepositorio.save(cliente);
//        }).orElseThrow(() -> new ClienteNaoEncontradoException(nomeOperacao,nomeCliente));
//    }

    @PutMapping("/clienteController/{id}")
    public ResponseEntity<Cliente> atualizaCliente(@PathVariable("id") long id, @Valid @RequestBody Cliente cliente) {
		String nomeOperacao = "atualizaCliente";
        return clienteRepositorio.findById(id).map(c -> {
        	c.setSobrenome(cliente.getNome());
        	c.setSobrenome(cliente.getSobrenome());
        	c.setEndereco(cliente.getEndereco());
        	c.setTelefone(cliente.getTelefone());
            return new ResponseEntity<Cliente>(clienteRepositorio.save(c), HttpStatus.OK);
        }).orElseThrow(() -> new ClienteNaoEncontradoException(nomeOperacao,cliente.getNome()));
        
    }

    @DeleteMapping("/clienteController/{nomeCliente}")
    public ResponseEntity<?> excluiCliente(@PathVariable String nomeCliente) {
		String nomeOperacao = "excluiCliente";
        return clienteRepositorio.findByNome(nomeCliente).map(cliente -> {
        	clienteRepositorio.delete(cliente);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ClienteNaoEncontradoException(nomeOperacao,nomeCliente));
    }

}