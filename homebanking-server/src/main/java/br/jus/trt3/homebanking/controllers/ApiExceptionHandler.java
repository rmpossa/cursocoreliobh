package br.jus.trt3.homebanking.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.jus.trt3.homebanking.exceptions.ApiErrorResponse;
import br.jus.trt3.homebanking.exceptions.ClienteNaoEncontradoException;
import br.jus.trt3.homebanking.exceptions.ContaNaoEncontradaException;
import br.jus.trt3.homebanking.exceptions.LimiteDeSaquePorHorarioException;
import br.jus.trt3.homebanking.exceptions.NaoEContaInvestimentoException;
import br.jus.trt3.homebanking.exceptions.SaldoInsuficienteException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ContaNaoEncontradaException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ContaNaoEncontradaException ex) {
        ApiErrorResponse response = 
            new ApiErrorResponse("Erro na operação " + ex.getNomeOperacao(),
                "Conta com o código " + ex.getCodigoConta() + " não encontrada");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ClienteNaoEncontradoException ex) {
        ApiErrorResponse response = 
            new ApiErrorResponse("Erro na operação " + ex.getNomeOperacao(),
                "Cliente " + ex.getNome() + " não encontrado");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NaoEContaInvestimentoException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(NaoEContaInvestimentoException ex) {
        ApiErrorResponse response = 
            new ApiErrorResponse("Erro na operação " + ex.getNomeOperacao(),
                "Conta com o código " + ex.getCodigoConta() + " não é uma conta investimento");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(SaldoInsuficienteException ex) {
        ApiErrorResponse response = 
            new ApiErrorResponse("Erro na operação " + ex.getNomeOperacao(),
                "Conta com o código " + ex.getCodigoConta() + " não possui saldo suficiente");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LimiteDeSaquePorHorarioException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(LimiteDeSaquePorHorarioException ex) {
        ApiErrorResponse response = 
            new ApiErrorResponse("Erro na operação " + ex.getNomeOperacao(),
            	"Não é possível sacar mais de 1000 reais antes das 6h e depois das 22h para a conta " + ex.getCodigoConta());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}