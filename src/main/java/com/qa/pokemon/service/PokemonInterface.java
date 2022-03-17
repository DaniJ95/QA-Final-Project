package com.qa.pokemon.service;

import java.util.List;

import com.qa.pokemon.domain.Pokemon;


public interface PokemonInterface<T> {

	//abstract methods for each of the CRUD

	Pokemon create(Pokemon x);

	List<Pokemon> read();

	Pokemon update(T id, Pokemon y);

	Pokemon delete(T id);

}