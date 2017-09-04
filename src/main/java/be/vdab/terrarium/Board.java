
package be.vdab.terrarium;

import be.vdab.entities.Herbivore;
import be.vdab.entities.Organism;

public enum Board {

	INSTANCE;
	private static Organism[][] organisms;
	
	public static void main(String[] args) 
	{
		for (int i = 0; i < organisms.length; i++) {
			for (int j = 0; j < organisms[i].length; j++) {
				if (organisms[i][j] instanceof Herbivore) {
					
				}
			}
		}
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
