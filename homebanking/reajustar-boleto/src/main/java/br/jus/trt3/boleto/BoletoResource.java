package br.jus.trt3.boleto;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/boleto")
public class BoletoResource {

    @GetMapping
    public ResponseEntity<Double> reajustarBoleto(
    		@RequestParam(value="valorOriginal") double valorOriginal, 
    		@RequestParam(value="dataPagamento") Date dataPagamento, 
    		@RequestParam(value="dataVencimento") Date dataVencimento) {
    	long diffMilissegundos = dataPagamento.getTime() - dataVencimento.getTime();
    	long diffDays = TimeUnit.DAYS.convert(diffMilissegundos, TimeUnit.MILLISECONDS);
    	
    	if(diffDays > 0) {
    		return new ResponseEntity<Double>(valorOriginal * (1+0.001*diffDays), HttpStatus.OK) ;
    	}
        return new ResponseEntity<Double>(valorOriginal, HttpStatus.OK);
    }
    
    /*public static void main(String[] args) {
    	Date dataHoje = Calendar.getInstance().getTime();
    	Calendar calendarAmanha = Calendar.getInstance();
    	calendarAmanha.add(Calendar.DAY_OF_MONTH, 5);
    	Date dataAmanha = calendarAmanha.getTime();
    	long diff = dataAmanha.getTime() - dataHoje.getTime();
    	
    	System.out.println(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
    	
	}*/
}