
package be.vdab.terrarium;

import be.vdab.entities.Carnivore;
import be.vdab.entities.Herbivore;
import be.vdab.entities.Organism;
import be.vdab.entities.Plant;

public enum Board {

	INSTANCE;
	private static Organism[][] organisms = new Organism[6][6];

	public static void main(String[] args) {
		setTestPositions();

		for (int i = 0; i < organisms.length; i++) {
			for (int j = 0; j < organisms[i].length; j++) {
				if (organisms[i][j] == null) {
					System.out.print(".\t");
				} else {
					System.out.print(organisms[i][j].toString() + "\t");
				}
			}
			System.out.println();
		}
	}

	private static void setTestPositions() {
		organisms[0][0] = new Herbivore(1, true);
		organisms[0][1] = new Plant(1, true);
		organisms[0][3] = new Herbivore(1, true);
		organisms[0][4] = new Herbivore(1, true);
		organisms[0][0] = new Herbivore(1, true);
		organisms[1][2] = new Plant(1, true);
		organisms[0][0] = new Herbivore(1, true);
		organisms[1][2] = new Plant(1, true);

	}

	public void nextDay() {
		generateNewPlants();
		for (int i = 0; i < organisms.length; i++) {
			for (int j = 0; j < organisms[i].length; j++) {
				if (organisms[i][j] instanceof Herbivore) {
					if ((j == organisms[i].length - 1) || (organisms[i][j + 1] == null) // vermijdt
																						// ArrayIndexOutOfBoundsException
							|| organisms[i][j + 1] instanceof Carnivore) {
						move(organisms[i][j]);
					} else if (organisms[i][j + 1] instanceof Plant) {
						eat(organisms[i][j], organisms[i][j + 1]);
					} else if (organisms[i][j + 1] instanceof Herbivore) {
						mate(organisms[i][j], organisms[i][j + 1]);
					}

				} else if (organisms[i][j] instanceof Carnivore) {
					if ((j == organisms[i].length - 1) || (organisms[i][j + 1] == null)
							|| organisms[i][j + 1] instanceof Plant) {
						move(organisms[i][j]);
					} else if (organisms[i][j + 1] instanceof Carnivore) {
						fight(organisms[i][j], organisms[i][j + 1]);
					} else if (organisms[i][j + 1] instanceof Herbivore) {
						eat(organisms[i][j], organisms[i][j + 1]);
					}
				}
			}
		}

	}

	private void mate(Organism organism, Organism organism2) {
		// TODO Auto-generated method stub

	}

	private void eat(Organism organism, Organism organism2) {
		// TODO Auto-generated method stub

	}

	private void fight(Organism organism, Organism organism2) {
		// TODO Auto-generated method stub

	}

	private void move(Organism organism) {
		// TODO Auto-generated method stub

	}

	private void generateNewPlants() {
		// TODO Auto-generated method stub

	}
}
