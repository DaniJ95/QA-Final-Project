package com.qa.pokemon.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.pokemon.domain.Pokemon;
import com.qa.pokemon.repo.PokemonRepo;



@SpringBootTest
@ActiveProfiles("test")
public class PokemonServiceDBTest {

	// data
	private Pokemon input;
	private Pokemon returned;

	@Autowired // the class we are testing
	private PokemonServiceDatabase serv;

	@MockBean // this is the class we are dependent on so have to mock
	private PokemonRepo rep;

	@BeforeEach
	void setUp() {
		//		System.out.println("before");
		input = new Pokemon("Gengar", "Ghost", "5ft", "0kg", "Male", 200, 100, 70, 120, 75);
		returned = new Pokemon(1L, "Gengar", "Ghost", "5ft", "0kg", "Male", 200, 100, 70, 120, 75);
	}

	@Test
	void testCreate() {
		//		System.out.println("test 1");
		// WHEN
		Mockito.when(this.rep.save(input)).thenReturn(returned);
		// THEN
		assertThat(this.serv.create(input)).isEqualTo(returned);

		// Verification
		Mockito.verify(this.rep, Mockito.times(1)).save(input);
	}

	@Test
	void testRead() {
		//		System.out.println("test 2");
		// GIVEN
		List<Pokemon> readList = new ArrayList<>();
		readList.add(input);
		// WHEN
		Mockito.when(this.rep.findAll()).thenReturn(readList);
		// THEN
		assertThat(this.serv.read()).isEqualTo(readList);
		// VERIFY
		Mockito.verify(this.rep, Mockito.times(1)).findAll();
	}



	@Test
	void testReadOne() {
		// GIVEN
		Long id = 1L;// method input
		// variables setup in method
		Optional<Pokemon> optPkmn = Optional.of(returned);
		// WHEN a certain method is called, return ....
		Mockito.when(this.rep.findById(id)).thenReturn(optPkmn);
		// THEN check what was returned is equal to what was expected
		assertThat(this.serv.readOne(id)).isEqualTo(returned);
		// VERIFY
		Mockito.verify(this.rep, Mockito.times(1)).findById(id);
	}


	@Test
	void testUpdate() {
		// GIVEN - id, object
		Long id = 1L;
		// NEW POKEMON OBJECT FOR INPUT TO UPDATE METHOD
		Pokemon toUpdate = new Pokemon("Gengar", "Ghost", "5ft", "0kg", "Male", 200, 100, 70, 120, 75);
		// METHOD USES AN OPTIONAL VERSION OF THE POKEMON OBJECT
		Optional<Pokemon> opt = Optional.of(returned);
		// UPDATED VERSION:
		Pokemon updated = new Pokemon(id, toUpdate.getName(), toUpdate.getType(), toUpdate.getHeight(), toUpdate.getWeight(), toUpdate.getGender(), toUpdate.getHp(), toUpdate.getAttack(), toUpdate.getDefence(), toUpdate.getSpecialattack(), toUpdate.getSpecialdefence());
		// WHEN
		Mockito.when(this.rep.findById(id)).thenReturn(opt);
		Mockito.when(this.rep.save(updated)).thenReturn(updated);
		// THEN
		assertThat(this.serv.update(id, toUpdate)).isEqualTo(updated);
		// VERIFY
		Mockito.verify(this.rep, Mockito.times(1)).findById(id);
		Mockito.verify(this.rep, Mockito.times(1)).save(updated);
	}

	@Test
	void testRemove() {
		// GIVEN
		Long id = 1L;
		// WHEN
		Mockito.when(this.rep.existsById(id)).thenReturn(false);
		// THEN
		assertThat(this.serv.remove(id)).isTrue();
		// VERIFY
		Mockito.verify(this.rep, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.rep, Mockito.times(1)).existsById(id);
	}

	@AfterEach
	void clear() {
		//		System.out.println("after");
	}
}