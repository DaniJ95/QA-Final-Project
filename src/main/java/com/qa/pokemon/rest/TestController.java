package com.qa.pokemon.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD

@RestController
public class TestController {

	@GetMapping("/test")
	public String hello() {
		return "Hello World";

	}

}
=======


@RestController
public class TestController {
	
	@GetMapping("/test")
	public String hello() {
		return "Hello world!";
	}

}
>>>>>>> Feature3
