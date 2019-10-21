package br.jus.trt3.homebanking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.jus.trt3.homebanking.exceptions.ClienteNaoEncontradoException;
import br.jus.trt3.homebanking.models.Cliente;
import br.jus.trt3.homebanking.repositories.ClienteRepositorio;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

	@GetMapping(path="clientes")
    @ApiOperation(value="Recupera todos os clientes.")
    public Iterable<Cliente> recuperaTodosClientes() {
        return clienteRepositorio.findAll();
    }

    @PostMapping(path="clientes")
    @ApiOperation(value="Cria um novo cliente.")
    public Cliente criaCliente(@ApiParam(value="nome do cliente") @RequestParam(value="nome", required=true) String nome,
    	@ApiParam(value="sobrenome do cliente") @RequestParam(value="sobrenome", required=true) String sobrenome,
    	@ApiParam(value="endereço do cliente") @RequestParam(value="endereco") String endereco,
    	@ApiParam(value="telefone do cliente") @RequestParam(value="telefone") String telefone) {
        return clienteRepositorio.save(new Cliente.ClienteBuilder(nome,sobrenome)
        	.setEndereco(endereco)
        	.setTelefone(telefone)
        	.build());
    }

    @PutMapping("/clientes/{nomeCliente}")
    public Cliente atualizaCliente(@PathVariable String nomeCliente, 
    	@ApiParam(value="sobrenome do cliente") @RequestParam(value="sobrenome") String sobrenome,
    	@ApiParam(value="endereço do cliente") @RequestParam(value="endereco") String endereco,
    	@ApiParam(value="telefone do cliente") @RequestParam(value="telefone") String telefone) {
		String nomeOperacao = "atualizaCliente";
        return clienteRepositorio.findByNome(nomeCliente).map(cliente -> {
        	cliente.setSobrenome(sobrenome);
        	cliente.setEndereco(endereco);
        	cliente.setTelefone(telefone);
            return clienteRepositorio.save(cliente);
        }).orElseThrow(() -> new ClienteNaoEncontradoException(nomeOperacao,nomeCliente));
    }

    @DeleteMapping("/clientes/{nomeCliente}")
    public ResponseEntity<?> excluiCliente(@PathVariable String nomeCliente) {
		String nomeOperacao = "excluiCliente";
        return clienteRepositorio.findByNome(nomeCliente).map(cliente -> {
        	clienteRepositorio.delete(cliente);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ClienteNaoEncontradoException(nomeOperacao,nomeCliente));
    }

}
