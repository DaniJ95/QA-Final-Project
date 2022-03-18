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
		
		String name;
		String type;
		String height;
		String weight;
		String gender;
		int hp;
		int attack;
		int defence;
		int specialattack;
		int specialdefence;
		
		if (y.getName() == null) {
			name = optPkm.get().getName();
		} else {
			name = y.getName();
		}
		if (y.getType() == null) {
			type = optPkm.get().getType();
		} else {
			type = y.getType();
		}
		if (y.getHeight() == null) {
			height = optPkm.get().getHeight();
		} else {
			height = y.getHeight();
		}
		if (y.getWeight() == null) {
			weight = optPkm.get().getWeight();
		} else {
			weight = y.getWeight();
		}
		if (y.getGender() == null) {
			gender = optPkm.get().getGender();
		} else {
			gender = y.getGender();
		}
		if (y.getHp() == 0) {
			hp = optPkm.get().getHp();
		} else {
			hp = y.getHp();
		}
		if (y.getAttack() == 0) {
			attack = optPkm.get().getAttack();
		} else {
			attack = y.getAttack();
		}
		if (y.getDefence() == 0) {
			defence = optPkm.get().getDefence();
		} else {
			defence = y.getDefence();
		}
		if (y.getSpecialattack() == 0) {
			specialattack = optPkm.get().getSpecialattack();
		} else {
			specialattack = y.getSpecialattack();
		}
		if (y.getSpecialdefence() == 0) {
			specialdefence = optPkm.get().getSpecialdefence();
		} else {
			specialdefence = y.getSpecialdefence();
		}
		
		found.setName(name);
		found.setType(type);
		found.setHeight(height);
		found.setWeight(weight);
		found.setGender(gender);
		found.setHp(hp);
		found.setAttack(attack);
		found.setDefence(defence);
		found.setSpecialattack(specialattack);
		found.setSpecialdefence(specialdefence);
		return this.repo.save(found);
	}
	
	public boolean remove(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
