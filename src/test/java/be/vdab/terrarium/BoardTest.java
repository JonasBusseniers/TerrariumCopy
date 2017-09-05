package be.vdab.terrarium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import be.vdab.entities.Carnivore;
import be.vdab.entities.Herbivore;
import be.vdab.entities.Organism;
import be.vdab.entities.Plant;

public class BoardTest {

	@Test
	public void herbivoreEatsPlantOnRight() {
		Plant plant = new Plant(1, false);
		Herbivore herbivore = new Herbivore(3, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[1][2] = herbivore;
		organisms[1][3] = plant;
		Board.eat(1, 2, 1, 3);
		assertEquals(null, organisms[1][3]);
	}

	@Test
	public void herbivoreAfterEatingRaiseslevelWithPlantLevel() {
		Plant plant = new Plant(1, false);
		Herbivore herbivore = new Herbivore(3, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[1][2] = herbivore;
		organisms[1][3] = plant;
		Board.eat(1, 2, 1, 3);
		assertEquals(4, organisms[1][2].getLife());
	}

	@Test
	public void herbivoreMatesWithHerbivoreOnRight() throws BoardException {
		Herbivore herbivore = new Herbivore(3, false);
		Herbivore herbivore2 = new Herbivore(3, false);
		Organism[][] organisms = Board.getOrganisms();
		for (int i = 0; i < organisms.length; i++) {
			for (int j = 0; j < organisms[i].length; j++) {
				organisms[i][j] = new Plant(1, false);
			}
		}
		organisms[0][0] = herbivore;
		organisms[0][1] = herbivore2;
		organisms[0][2] = null;
		Board.mate();
		assertTrue(organisms[0][2] instanceof Herbivore);
	}

	@Test
	public void herbivoreMovesWhenRightIsEmpty() {
		Herbivore herbivore = new Herbivore(3, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[1][2] = herbivore;
		organisms[1][3] = null;
		Board.move(1, 2);
		assertEquals(null, organisms[1][2]);
	}

	@Test
	public void carnivoreEatsHerbivoreOnRight() {
		Carnivore carnivore = new Carnivore(1, false);
		Herbivore herbivore = new Herbivore(3, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[1][2] = carnivore;
		organisms[1][3] = herbivore;
		Board.eat(1, 2, 1, 3);
		assertEquals(null, organisms[1][3]);
	}

	@Test
	public void carnivoreDoesNotEatPlantOnRight() throws BoardException {
		Carnivore carnivore = new Carnivore(1, false);
		Plant plant = new Plant(3, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[1][2] = carnivore;
		organisms[1][3] = plant;
		Board.nextDay();
		assertEquals(plant, organisms[1][3]);
	}

	@Test
	public void carnivoreAfterEatingHerbivoreRaisesLevelCarnivoreWithLevelHerbivore() {
		Carnivore carnivore = new Carnivore(1, false);
		Herbivore herbivore = new Herbivore(3, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[1][2] = herbivore;
		organisms[1][1] = carnivore;
		Board.eat(1, 1, 1, 2);
		assertEquals(4, organisms[1][1].getLife());
	}

	@Test
	public void carnivoreFightsCarnivoreOnRight() {
		Carnivore carnivore = new Carnivore(1, false);
		Carnivore carnivore2 = new Carnivore(3, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[1][1] = carnivore;
		organisms[1][2] = carnivore2;
		Board.fight(1, 1);
		assertEquals(null, organisms[1][1]);
	}

	@Test
	public void whenCarnivoreFightsAndIsOnLeftThenHasActedIsFalse() {
		Carnivore carnivore = new Carnivore(1, false);
		Carnivore carnivore2 = new Carnivore(3, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[1][1] = carnivore;
		organisms[1][2] = carnivore2;
		Board.fight(1, 1);
		assertEquals(false, organisms[1][2].isHasActed());
	}

	@Test
	public void carnivoreDoesNotFightCarnivoreOnTop() {
		Carnivore carnivore = new Carnivore(1, false);
		Carnivore carnivore2 = new Carnivore(3, false);
		Herbivore herbivore = new Herbivore(3, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[0][2] = carnivore;
		organisms[1][2] = carnivore2;
		organisms[1][3] = herbivore;
		Board.fight(1, 2);
		assertEquals(carnivore, organisms[0][2]);
	}

	@Test
	public void carnivoreDoesNotFightCarnivoreOnBottom() {
		Carnivore carnivore = new Carnivore(1, false);
		Carnivore carnivore2 = new Carnivore(3, false);
		Herbivore herbivore = new Herbivore(3, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[2][2] = carnivore;
		organisms[1][2] = carnivore2;
		organisms[1][3] = herbivore;
		Board.fight(1, 2);
		assertEquals(carnivore2, organisms[1][2]);
	}

	@Test
	public void carnivoreDoesNotFightHerbivoreOnRight() throws BoardException {
		Carnivore carnivore = new Carnivore(1, false);
		Herbivore herbivore = new Herbivore(3, false);
		Herbivore herbivore2 = new Herbivore(3, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[0][0] = carnivore;
		organisms[0][1] = herbivore;
		organisms[0][2] = herbivore2;
		Board.nextDay();
		assertEquals(herbivore, organisms[0][1]);
	}

	@Test
	public void whenCarnivoresFightStrongestWins() {
		Carnivore carnivore = new Carnivore(1, false);
		Carnivore carnivore2 = new Carnivore(3, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[0][0] = carnivore;
		organisms[0][1] = carnivore2;
		Board.fight(0, 0);
		assertEquals(carnivore2, organisms[0][1]);
	}

	@Test
	public void whenCarnivoresFightWeakestLoses() {
		Carnivore carnivore = new Carnivore(1, false);
		Carnivore carnivore2 = new Carnivore(3, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[0][0] = carnivore;
		organisms[0][1] = carnivore2;
		Board.fight(0, 0);
		assertEquals(null, organisms[0][0]);
	}

	@Test
	public void whenCarnivoreWinsFightLevelRaisesWithLevelOther() {
		Carnivore carnivore = new Carnivore(1, false);
		Carnivore carnivore2 = new Carnivore(3, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[0][0] = carnivore;
		organisms[0][1] = carnivore2;
		Board.fight(0, 0);
		assertEquals(4, carnivore2.getLife());
	}

	@Test
	public void carnivoresWithEqualStrengthDoNotFight() {
		Carnivore carnivore = new Carnivore(3, false);
		Carnivore carnivore2 = new Carnivore(3, false);
		Plant plant = new Plant(1, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[0][0] = carnivore;
		organisms[0][1] = carnivore2;
		organisms[0][2] = plant;
		Board.fight(0, 1);
		assertEquals(3, carnivore.getLife());
	}

	@Test
	public void carnivoreMovesWhenRightIsEmpty() throws BoardException {
		Carnivore carnivore = new Carnivore(3, false);
		Plant plant = new Plant(1, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[0][0] = carnivore;
		organisms[0][1] = null;
		organisms[1][0] = plant;
		Board.nextDay();
		assertEquals(carnivore, organisms[0][1]);
	}

	@Test
	public void carnivoreDoesNotMoveWhenPlantOnRight() throws BoardException {
		Carnivore carnivore = new Carnivore(3, false);
		Plant plant = new Plant(1, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[0][0] = carnivore;
		organisms[0][1] = plant;
		Board.nextDay();
		assertEquals(carnivore, organisms[0][0]);

	}

	@Test
	public void whenAnimalActsThenHasActedIsTrue() throws BoardException {
		Carnivore carnivore = new Carnivore(3, false);
		Organism[][] organisms = Board.getOrganisms();
		organisms[0][0] = carnivore;
		Board.nextDay();
		assertEquals(true, carnivore.isHasActed());
	}
}
