package br.edu.infnet.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @GetMapping(value = "/iniciativa", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> start(@RequestParam(name = "idHero") Integer idHero,
																@RequestParam(name = "agilityHero") Integer agilityHero,
																@RequestParam(name = "idMonster") Integer idMonster,
																@RequestParam(name = "agilityMonster") Integer agilityMonster) {
    	
    	
    	
    	String firstPlayer = batalhaService.start(agilityHero, agilityMonster);
    	
    	try {
    		String resultDices = "2";
    		
    		Map<String, Object> payload = new HashMap<>();
    		payload.put("value", resultDices);
    		
    		return ResponseEntity.ok(payload);
    	} catch(IllegalArgumentException ex) {
    		return (ResponseEntity) ResponseEntity.internalServerError();
    	}
		
	}
   
}
