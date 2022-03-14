package com.qa.pokemon.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.pokemon.domain.Pokemon;
import com.qa.pokemon.service.PokemonServiceDatabase;

@RestController
public class PokemonController {

	private PokemonServiceDatabase service;

	public PokemonController(PokemonServiceDatabase service) {
		super();
		this.service = service;
	}

	//Create

	@PostMapping("/create")
	public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon a) {
		return new ResponseEntity<Pokemon>(this.service.create(a), HttpStatus.CREATED);
	}
	
	//Read
	
	@GetMapping("/readAll")
	public List<Pokemon> readPokemon() {
		return this.service.read();
	}
	
	@GetMapping("/readById/{id}")
	public Pokemon getById(@PathVariable Long id) {
		return this.service.readOne(id);
	}
	
	//Update
	
	@PutMapping("/update/{id}")
	public Pokemon update(@PathVariable Long id, @RequestBody Pokemon updated) {
		return this.service.update(id, updated);
	}
	
	//Delete
	
	@DeleteMapping("/delete")
	public Pokemon delete(@PathParam("id") Long id) {
		return this.service.delete(id);
	}
	
	@DeleteMapping("/remove")
	public boolean remove(@PathParam("id") Long id) {
		return this.service.remove(id);
	}
}
