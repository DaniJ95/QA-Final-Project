package com.qa.pokemon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.pokemon.domain.Pokemon;
import com.qa.pokemon.repo.PokemonRepo;

@Service
public class PokemonServiceDatabase {

	private PokemonRepo repo;

	public PokemonServiceDatabase(PokemonRepo repo) {
		super();
		this.repo = repo;
	}
	
	public Pokemon create(Pokemon a) {
		return this.repo.save(a);
	}

	public List<Pokemon> read() {
		return this.repo.findAll();
	}

	public Pokemon readOne(Long id) {
		Optional<Pokemon> optRead = this.repo.findById(id);
		return optRead.orElse(null);
	}

	public Pokemon update(Long id, Pokemon y) {
		Optional<Pokemon> optPkm = this.repo.findById(id);
		Pokemon found = optPkm.get();
		found.setName(y.getName());
		found.setType(y.getType());
		found.setHeight(y.getHeight());
		found.setWeight(y.getWeight());
		found.setGender(y.getGender());
		found.setHp(y.getHp());
		found.setAttack(y.getAttack());
		found.setDefence(y.getDefence());
		found.setSpecialattack(y.getSpecialattack());
		found.setSpecialdefence(y.getSpecialdefence());
		return this.repo.save(found);
	}
	
	public boolean remove(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
