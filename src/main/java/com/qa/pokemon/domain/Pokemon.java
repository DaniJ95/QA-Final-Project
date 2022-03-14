package com.qa.pokemon.domain;

import java.util.Objects;

import javax.persistence.Entity;

@Entity //Marking this class for SQL
public class Pokemon {
	/* Create table pokemon
	 * id int not null auto increment
	 * name varchar
	 * type varchar(255)
	 * height varchar(255)
	 * weight varchar(255)
	 * gender varchar(255)
	 * hp int
	 * attack int
	 * defence int
	 * special attack int
	 * special Defence int
	 */

	private Long id;
	private String name;
	private String type;
	private String height;
	private String weight;
	private String gender;
	private int hp;
	private int attack; 
	private int defence;
	private int specialattack;
	private int specialdefence; 

	//Default empty constructor

	public Pokemon() {
		super();
	}

	//Constructor with id 

	public Pokemon(Long id, String name, String type, String height, String weight, String gender, int hp, int attack, int defence, int specialattack, int specialdefence) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.hp = hp;
		this.attack = attack;
		this.defence = defence;
		this.specialattack = specialattack;
		this.specialdefence = specialdefence;
	}

	//Constructor without id

	public Pokemon(String name, String type, String height, String weight, String gender, int hp, int attack, int defence, int specialattack, int specialdefence) {
		super();
		this.name = name;
		this.type = type;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.hp = hp;
		this.attack = attack;
		this.defence = defence;
		this.specialattack = specialattack;
		this.specialdefence = specialdefence;
	}

	//Getters

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getHeight() {
		return height;
	}

	public String getWeight() {
		return weight;
	}

	public String getGender() {
		return gender;
	}

	public int getHp() {
		return hp;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefence() {
		return defence;
	}

	public int getSpecialattack() {
		return specialattack;
	}

	public int getSpecialdefence() {
		return specialdefence;
	}


	//Setters

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public void setSpecialattack(int specialattack) {
		this.specialattack = specialattack;
	}

	public void setSpecialdefence(int specialdefence) {
		this.specialdefence = specialdefence;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, type, height, weight, gender, hp, attack, defence, specialattack, specialdefence);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		return name == other.name && Objects.equals(name, other.name) && Objects.equals(type, other.type) && Objects.equals(height, other.height) && Objects.equals(weight, other.weight) && Objects.equals(gender, other.gender) && Objects.equals(hp,  other.hp) && Objects.equals(attack, other.attack) && Objects.equals(defence, other.defence) && Objects.equals(specialattack, other.specialattack) && Objects.equals(specialdefence, other.specialdefence) 
				&& Objects.equals(id, other.id);

	}
}