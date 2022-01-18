package br.edu.infnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@RestController
@RequestMapping("/api/batalha")
public class BatalhaController {
	
	@Autowired
	RestTemplate restTemplate;

//    @GetMapping
//    public void teste(){
//        RestTemplate restTemplate = new RestTemplate();
//        String forObject = restTemplate.getForObject("http://localhost:8080/api/rolldices/iniciativa", String.class);
//        
//        Gson gson = new Gson();
//
//    	String json = gson.toJson(forObject);
//    	
//        System.out.println(json);
//    }
//    
    @GetMapping
    public ResponseEntity getResultDices() {
    	try {
    		//RestTemplate restTemplate = new RestTemplate();
    		String forObject = restTemplate.getForObject("http://DICE-API/api/rolldices/iniciativa", String.class);
        
    		Gson gson = new Gson();
        
    		String json = gson.toJson(forObject);
    		return ResponseEntity.ok(json);
    	} catch(IllegalArgumentException ex) {
    		return (ResponseEntity) ResponseEntity.internalServerError();
    	}
	}
}
