
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

		print();

		nextDay();

		print();

		nextDay();

		print();
	}

	private static void print()
	{
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
		System.out.println();
	}

	private static void setTestPositions() {
		organisms[0][0] = new Herbivore(1, false);
		organisms[0][1] = new Plant(1, false);
		organisms[0][3] = new Herbivore(1, false);
		organisms[0][4] = new Herbivore(1, false);
		organisms[0][0] = new Herbivore(1, false);
		organisms[1][2] = new Plant(1, false);
		organisms[0][0] = new Herbivore(1, false);
		organisms[1][2] = new Plant(1, false);

	}

	public static void nextDay() {
		
		// Set all HasActed on false
		
		for (int i = 0; i < organisms.length; i++) {
			for (int j = 0; j < organisms[i].length; j++) {
				organisms[i][j].setHasActed(false);
			}
		}
		
		
		// Do actions and set HasActed on true
		
		generateNewPlants();
		for (int i = 0; i < organisms.length; i++) {
			for (int j = 0; j < organisms[i].length; j++) {
				if (organisms[i][j] instanceof Herbivore) {
					if ((j == organisms[i].length - 1) || (organisms[i][j + 1] == null) // vermijdt
						// ArrayIndexOutOfBoundsException
						|| organisms[i][j + 1] instanceof Carnivore) {
						move(i,j);
					} else if (organisms[i][j + 1] instanceof Plant) {
						eat(i,j,i,j+1);
					} else if (organisms[i][j + 1] instanceof Herbivore) {
						mate();
					}

				} else if (organisms[i][j] instanceof Carnivore) {
					if ((j == organisms[i].length - 1) || (organisms[i][j + 1] == null)
						|| organisms[i][j + 1] instanceof Plant) {
						move(i,j);
					} else if (organisms[i][j + 1] instanceof Carnivore) {
						fight(i,j);
					} else if (organisms[i][j + 1] instanceof Herbivore) {
						eat(i,j,i,j+1);
					}
				}
				organisms[i][j].setHasActed(true);
			}
		}

	}

	private static void fight(int i, int j) {
		if (organisms[i][j].getLife() < organisms[i][j+1].getLife()){
			eat(i,j+1,i,j);
		} else if (organisms[i][j].getLife() > organisms[i][j+1].getLife()){
			eat(i,j,i,j+1);
		}

	}

	private static void mate() {
		int x;
		int y;
		do {
			x = (int) (Math.random() * 6);
			y = (int) (Math.random() * 6);
		} while (organisms[x][y] != null);
		organisms[x][y] = new Herbivore(0, false);

	}

	private static void eat(int i, int j, int i2, int j2) {
		organisms[i][j].setLife(organisms[i][j].getLife() + organisms[i2][j2].getLife());
		organisms[i2][j2] = null;

	}

	private static void move(int i, int j) {
		// TODO Auto-generated method stub

	}



	private static void generateNewPlants() {
		// TODO Auto-generated method stub

	}
}