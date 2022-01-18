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

import com.google.gson.Gson;

@RestController
@RequestMapping("/api/batalha")
public class BatalhaController {
	
	@Autowired
	RestTemplate restTemplate; 
    
    @GetMapping(value = "/iniciativa", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getStart(@RequestParam(name = "idHero") Integer idHero,
																@RequestParam(name = "agilityHero") Integer agilityHero,
																@RequestParam(name = "idMonster") Integer idMonster,
																@RequestParam(name = "agilityMonster") Integer agilityMonster) {
    	
    	int type = 10;
    	int amount = 1;
    	
    	try {
    		
    		int resultDice = 0;
	    	int resultHero = 0;
	    	int resultMonster = 0;
	    	boolean hasResult = false;

	    	do {
    			
    			resultDice = getResultRollDices(type, amount);
    			
    			resultHero = resultDice + agilityHero;
    			resultMonster = resultDice + agilityMonster;

    			if ((resultHero > resultMonster) || (resultMonster > resultHero)) hasResult = true;

			} while (!hasResult);
    		    		
    		String resultDices = "1";
    		
    		Map<String, Object> payload = new HashMap<>();
    		payload.put("value", resultDices);
    		
    		return ResponseEntity.ok(payload);
    	} catch(IllegalArgumentException ex) {
    		return (ResponseEntity) ResponseEntity.internalServerError();
    	}
		
	}
    
    public Integer getResultRollDices(Integer type, Integer amount) {
    	
    	//RestTemplate restTemplate = new RestTemplate();
		String forObject = restTemplate.getForObject("http://DICE-API/api/rolldices?type="+type+"&amount="+amount, String.class);
		
		Gson gson = new Gson();
    
		String resultDices = gson.toJson(forObject);
		int result;
		
		if(resultDices.substring(13,14).equals("}")) {
			result = Integer.parseInt(resultDices.substring(12,13));
		} else{
			result = Integer.parseInt(resultDices.substring(12,14));
		} 
    	
    	return result;
    }
}
