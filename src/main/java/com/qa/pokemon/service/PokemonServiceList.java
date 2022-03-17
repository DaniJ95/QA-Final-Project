package com.qa.pokemon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.pokemon.domain.Pokemon;

@Service
public class PokemonServiceList implements PokemonInterface<Integer> {
	// Store info in, alternative to db:
	private List<Pokemon> pokemon = new ArrayList<>();

	@Override
	public Pokemon create(Pokemon x) {
		x.setId((long) this.pokemon.indexOf(x));// added this to update id variable with array index
		this.pokemon.add(x);
		Pokemon created = this.pokemon.get(this.pokemon.size() - 1);
		return created;
	}
	@Override
	public List<Pokemon> read() {
		return this.pokemon;
	}

	public Pokemon readOne(Integer id) {
		return this.pokemon.get(id);
	}

	@Override
	public Pokemon update(Integer id, Pokemon y) {
		this.pokemon.set(id, y);
		return this.pokemon.get(id);
	}

	@Override
	public Pokemon delete(Integer id) {
		return this.pokemon.remove((int)id);
	}

}