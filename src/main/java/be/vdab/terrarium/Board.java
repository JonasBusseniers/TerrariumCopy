package be.vdab.terrarium;

import be.vdab.enteties.Herbivore;
import be.vdab.enteties.Organism;

public enum Board {

	INSTANCE;
	private Organism[][] organisms;

	private Board() {
	}

	private Board(Organism[][] organisms) {
		this.organisms = organisms;
	}

	public void nextDay() {
		generateNewPlants();

		for (int i = 0; i < organisms.length; i++) {
			for (int j = 0; j < organisms[i].length; j++) {
				if (organisms[i][j] instanceof Herbivore) {

				}

			}
		}
	}

	private void generateNewPlants() {
		// TODO Auto-generated method stub

	}
}
