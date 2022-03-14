package com.qa.pokemon.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.pokemon.domain.Pokemon;

@Repository
public interface PokemonRepo extends JpaRepository<Pokemon, Long> {
	
}