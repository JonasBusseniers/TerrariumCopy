package be.vdab.terrarium;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.vdab.entities.Carnivore;
import be.vdab.entities.Herbivore;
import be.vdab.entities.Organism;
import be.vdab.entities.Plant;

public class BoardTest {
//Ze moeten nog opgevuld
	
	@Test
	public void herbivoreEatsPlantOnRight() {
		Plant plant = new Plant(1, false);
		Herbivore herbivore = new Herbivore(3, false);
		Organism [][]organisms = Board.getOrganisms();
		organisms[1][2] = herbivore;
		Board.Organisms[1][3] = plant;
		Board.eat(1,2,1,3);
		assertEquals(null, Board.Organisms[1][3]);
	}
	
	@Test
	public void herbivoreDoesNotEatPlantOnLeft() {
		Plant plant = new Plant(1, false);
		Herbivore herbivore = new Herbivore(3, false);
		Board.Organisms[1][2] = herbivore;
		Board.Organisms[1][1] = plant;
		Board.eat(1,2,1,3);
		assertEquals(plant, Board.Organisms[1][1]);
	}
	
	@Test
	public void herbivoreDoesNotEatPlantOnTop() {
		Plant plant = new Plant(1, false);
		Herbivore herbivore = new Herbivore(3, false);
		Board.Organisms[1][2] = herbivore;
		Board.Organisms[0][2] = plant;
		Board.eat(1,2,1,3);
		assertEquals(plant, Board.Organisms[0][1]);
	}
	
	@Test
	public void herbivoreDoesNotEatPlantOnBottom() {
		Plant plant = new Plant(1, false);
		Herbivore herbivore = new Herbivore(3, false);
		Board.Organisms[1][2] = herbivore;
		Board.Organisms[2][2] = plant;
		Board.eat(1,2,1,3);
		assertEquals(plant, Board.Organisms[2][1]);
	}
	
	@Test
	public void herbivoreAfterEatingRaiseslevelWithPlantLevel() {
		Plant plant = new Plant(1, false);
		Herbivore herbivore = new Herbivore(3, false);
		Board.Organisms[1][2] = herbivore;
		Board.Organisms[1][3] = plant;
		Board.eat(1,2,1,3);
		assertEquals(4, Board.Organisms[1][2].life);
	}
	
	@Test
	public void herbivoreMatesWithHerbivoreOnRight() {

	}
	
	@Test
	public void herbivoreDoesNotMateWithCarnivoreOnRight() {
		
	}
	
	@Test
	public void herbivoreDoesNotMateWithHerbivoreOnLeft() {

	}
	
	@Test
	public void herbivoreDoesNotMateWithHerbivoreOnTop() {

	}
	
	@Test
	public void herbivoreDoesNotMateWithHerbivoreOnBottom() {

	}
	
	@Test
	public void afterMatingNewHerbivorePopsUp () {

	}
	
	@Test
	public void herbivoreMovesWhenRightIsEmpty() {
		Herbivore herbivore = new Herbivore(3, false);
		Board.Organisms[1][2] = herbivore;
		Board.Organisms[1][3] = null;
		Board.move(1,2);
		assertEquals(null, Board.Organisms[1][2]);
	}
	
	@Test
	public void carnivoreEatsHerbivoreOnRight() {
		Carnivore carnivore = new Carnivore(1, false);
		Herbivore herbivore = new Herbivore(3, false);
		Board.Organisms[1][2] = carnivore;
		Board.Organisms[1][3] = herbivore;
		Board.eat(1,2,1,3);
		assertEquals(null, Board.Organisms[1][3]);
	}
	
	@Test
	public void carnivoreDoesNotEatPlantOnRight() {
		Carnivore carnivore = new Carnivore(1, false);
		Plant plant = new Plant(3, false);
		Board.Organisms[1][2] = carnivore;
		Board.Organisms[1][3] = plant;
		Board.nextDay();
		assertEquals(plant, Board.Organisms[1][3]);
	}
	
	@Test
	public void carnivoreDoesNotEatHerbivoreOnLeft() {
		Carnivore carnivore = new Carnivore(1, false);
		Herbivore herbivore = new Herbivore(3, false);
		Board.Organisms[1][1] = herbivore;
		Board.Organisms[1][2] = carnivore;
		Board.eat(1,2,1,3);
		assertEquals(herbivore, Board.Organisms[1][1]);
	}
	
	@Test
	public void carnivoreDoesNotEatHerbivoreOnTop() {
		Carnivore carnivore = new Carnivore(1, false);
		Herbivore herbivore = new Herbivore(3, false);
		Board.Organisms[1][1] = herbivore;
		Board.Organisms[2][1] = carnivore;
		Board.eat(1,2,1,3);
		assertEquals(herbivore, Board.Organisms[1][1]);
	}
	
	@Test
	public void carnivoreDoesNotEatHerbivoreOnBottom() {
		Carnivore carnivore = new Carnivore(1, false);
		Herbivore herbivore = new Herbivore(3, false);
		Board.Organisms[1][1] = herbivore;
		Board.Organisms[0][1] = carnivore;
		Board.eat(1,2,1,3);
		assertEquals(herbivore, Board.Organisms[1][1]);
	}
	
	@Test
	public void carnivoreAfterEatingHerbivoreRaisesLevelCarnivoreWithLevelHerbivore() {
		Carnivore carnivore = new Carnivore(1, false);
		Herbivore herbivore = new Herbivore(3, false);
		Board.Organisms[1][2] = herbivore;
		Board.Organisms[1][1] = carnivore;
		Board.eat(1,2,1,3);
		assertEquals(4, Board.Organisms[1][1].life);
	}
	
	@Test
	public void carnivoreFightsCarnivoreOnRight() {
		Carnivore carnivore = new Carnivore(1, false);
		Carnivore carnivore2 = new Carnivore(3, false);
		Board.Organisms[1][1] = carnivore;
		Board.Organisms[1][2] = carnivore2;
		Board.fight(1,1);
		assertEquals(null, Board.Organisms[1][1]);
	}
	
	@Test
	public void whenCarnivoreFightsAndIsOnLeftThenHasActedIsFalse() {
		Carnivore carnivore = new Carnivore(1, false);
		Carnivore carnivore2 = new Carnivore(3, false);
		Board.Organisms[1][1] = carnivore;
		Board.Organisms[1][2] = carnivore2;
		Board.fight(1,1);
		assertEquals(false, Board.Organisms[1][2].hasActed);
	}
	
	@Test
	public void carnivoreDoesNotFightCarnivoreOnLeft() {
		Carnivore carnivore = new Carnivore(1, false);
		Carnivore carnivore2 = new Carnivore(3, false);
		Board.Organisms[1][1] = carnivore;
		Board.Organisms[1][2] = carnivore2;
		Board.Organisms[1][3] = null;
		Board.fight(1,2);
		assertEquals(3, Board.Organisms[1][1].life);
	}
	
	@Test
	public void carnivoreDoesNotFightCarnivoreOnTop() {
		Carnivore carnivore = new Carnivore(1, false);
		Carnivore carnivore2 = new Carnivore(3, false);
		Board.Organisms[0][2] = carnivore;
		Board.Organisms[1][2] = carnivore2;
		Board.Organisms[1][3] = null;
		Board.fight(1,2);
		assertEquals(3, Board.Organisms[1][1].life);
	}
	
	@Test
	public void carnivoreDoesNotFightCarnivoreOnBottom() {
		Carnivore carnivore = new Carnivore(1, false);
		Carnivore carnivore2 = new Carnivore(3, false);
		Board.Organisms[2][2] = carnivore;
		Board.Organisms[1][2] = carnivore2;
		Board.Organisms[1][3] = null;
		Board.fight(1,2);
		assertEquals(3, Board.Organisms[1][1].life);
	}
	
	@Test
	public void carnivoreDoesNotFightHerbivoreOnRight() {
		Carnivore carnivore = new Carnivore(1, false);
		Herbivore herbivore = new Herbivore(3, false);
		Herbivore herbivore2 = new Herbivore(3, false);
		Board.getOrganisms();
		organisms.Organisms[0][0] = carnivore;
		Board.organisms[0][1] = herbivore;
		Board.organisms[0][2] = herbivore2;
		Board.nextDay();
		assertEquals(herbivore, Board.Organisms[0][1].life);
	}
	
	@Test
	public void whenCarnivoresFightStrongestWins() {

	}
	
	@Test
	public void whenCarnivoresFightWeakestLoses() {

	}
	
	@Test
	public void whenCarnivoreWinsFightLevelRaisesWithLevelOther() {

	}
	
	@Test
	public void losingCarnivoreGetsEliminated() {

	}
	
	@Test
	public void carnivoresWithEqualStrengthDoNotFight() {

	}
	
	@Test
	public void carnivoreMovesWhenRightIsEmpty() {

	}
	
	@Test
	public void carnivoreDoesNotMoveWhenEqualCarnivoreOnRight() {

	}
	
	@Test
	public void whenAnimalActsThenHasActedIsTrue() {

	}
	
	@Test
	public void whenHasActedIsTrueNoActionOccurs() {

	}
}
