package br.edu.infnet.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController {
	@Value("${microservice.example.greeting}")
	private String hello;
	
	@GetMapping
	public ResponseEntity<String> hello(){
		return ResponseEntity.ok(hello);
	}
}
