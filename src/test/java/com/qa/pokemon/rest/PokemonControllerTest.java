package com.qa.pokemon.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.pokemon.domain.Pokemon;

@SpringBootTest
@AutoConfigureMockMvc //acts like your postman - makes the test requests
@Sql(scripts = {"classpath:pokemon-schema.sql","classpath:pokemon-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)// PRE-POPULATE OUT H2 DB
@ActiveProfiles("test")//run this test class on the db in the application-test.properties
public class PokemonControllerTest {

	@Autowired
	private MockMvc mock; //Mock Requests

	@Autowired
	private ObjectMapper map = new ObjectMapper(); //Interprets JSON

	@Test //Create
	void testCreate() throws Exception {

		Pokemon create = new Pokemon(1L, "Gengar", "Ghost", "5ft", "0kg", "Male", 200, 100, 70, 120, 75); 
		//Convert into JSON 
		String createJSON = this.map.writeValueAsString(create);
		//Build up mock request
		RequestBuilder mockRequest = post("/create").contentType(MediaType.APPLICATION_JSON).content(createJSON);
		//Response
		//Body
		Pokemon saved = new Pokemon(1L, "Gengar", "Ghost", "5ft", "0kg", "Male", 200, 100, 70, 120, 75);
		//Convert into JSON
		String savedJSON = this.map.writeValueAsString(saved);
		//Test response is correct
		//Status = 201 created
		ResultMatcher matchStatus = status().isCreated();
		//Test response 
		ResultMatcher matchBody = content().json(savedJSON);
		//Perform test
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}


	@Test
	void readTest() throws Exception {

		Pokemon readPokemon = new Pokemon(1L, "Bulbasaur", "Grass", "3ft", "1tonne", "female", 1000, 300, 213, 432, 300);
		String readPokemonJSON = "[" + this.map.writeValueAsString(readPokemon) + "]";
		
		RequestBuilder mockRequest = get("/readAll");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(readPokemonJSON);
				
		this.mock.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void readOneTest() throws Exception {
		Pokemon readAPokemon = new Pokemon(1L, "Bulbasaur", "Grass", "3ft", "1tonne", "female", 1000, 300, 213, 432, 300);
		String readAPokemonJSON = this.map.writeValueAsString(readAPokemon);

		Long idIn = 1L;

		RequestBuilder mockRequest = get("/readById/" + idIn);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(readAPokemonJSON);

		this.mock.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void updateTest() throws Exception {
		Pokemon updatePokemon = new Pokemon("Gengar", "Ghost", "5ft", "0kg", "Male", 200, 100, 70, 120, 75);
		String updatePokemonJSON = this.map.writeValueAsString(updatePokemon);
		Long id4URL = 1L;

		RequestBuilder mockRequest = put("/update/" + id4URL).contentType(MediaType.APPLICATION_JSON)
				.content(updatePokemonJSON);

		Pokemon retUpdatedPokemon = new Pokemon(1L, "Gengar", "Ghost", "5ft", "0kg", "Male", 200, 100, 70, 120, 75);
		String retUpdatedPokemonJSON = this.map.writeValueAsString(retUpdatedPokemon);

		ResultMatcher retStatus = status().isOk();
		ResultMatcher retBody = content().json(retUpdatedPokemonJSON);

		this.mock.perform(mockRequest).andExpect(retStatus).andExpect(retBody);

	}

	@Test //Remove
	void testRemove() throws Exception {
		//--request - type-delete, path param-id, url
		Long id = 1L;
		RequestBuilder mockRequest = delete("/remove/?id="+id);
		//--response - status, boolean-body
		ResultMatcher status = status().isOk();
		ResultMatcher body = content().string("true");
		//---test
		this.mock.perform(mockRequest).andExpect(body).andExpect(status);
	}
}