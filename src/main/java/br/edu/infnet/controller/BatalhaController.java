package br.edu.infnet.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.edu.infnet.service.BatalhaService;

@RestController
@RequestMapping("/api/batalha")
public class BatalhaController {
	
	@Autowired
	RestTemplate restTemplate; 
	
	@Autowired
	BatalhaService batalhaService;

	@Autowired
	CircuitBreakerFactory circuitBreakerFactory;
    
    @GetMapping(value = "/iniciativa", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> iniciativa(@RequestParam(name = "idHero") Integer idHero,
																@RequestParam(name = "agilityHero") Integer agilityHero,
																@RequestParam(name = "idMonster") Integer idMonster,
																@RequestParam(name = "agilityMonster") Integer agilityMonster) {

		try {

			org.springframework.cloud.client.circuitbreaker.CircuitBreaker
			circuitBreaker = circuitBreakerFactory.create("rollDicesCB");

			String firstPlayer = circuitBreaker.run( () -> batalhaService.getFirstPlayer(agilityHero, agilityMonster),
					throwable -> batalhaService.getFirstPlayerFallback());

			batalhaService.saveBattle(idHero, idMonster, firstPlayer);
			
			String winner = null;
			winner = this.batalha(idHero, idMonster, firstPlayer);
    		
//    		Map<String, Object> payload = new HashMap<>();
//    		payload.put("firstPlayer", firstPlayer);
			
			Map<String, Object> payload = new HashMap<>();
			payload.put("winner", winner);
    		
    		return ResponseEntity.ok(payload);
    	} catch(IllegalArgumentException ex) {
    		return (ResponseEntity) ResponseEntity.internalServerError();
    	}
		
	}
    
    @GetMapping(value = "/ataque")
	public String batalha(Integer idHero, Integer idMonster, String firstPlayer){

			String winner = null;
	    	return winner = batalhaService.getBattle(idHero, idMonster, firstPlayer);
	    
    }
  
}
