
package be.vdab.terrarium;

import be.vdab.entities.Herbivore;
import be.vdab.entities.Organism;
import be.vdab.entities.Plant;

public enum Board {

	INSTANCE;
	private static Organism[][] organisms = new Organism[6][6];
	
	public static void main(String[] args) 
	{
		setTestPositions();
		
		for (int i = 0; i < organisms.length; i++) {
			for (int j = 0; j < organisms[i].length; j++) {
				if (organisms[i][j]==null) 
				{
					System.out.print(".\t");
				}
				else
				{
					System.out.print(organisms[i][j].toString() + "\t");
				}
			}
			System.out.println();
		}
	}
	
	private static void setTestPositions ()
	{
		organisms [0][0] = new Herbivore (1, true);
		organisms [0][1] = new Plant (1, true);
		organisms [0][3] = new Herbivore (1, true);
		organisms [0][4] = new Herbivore (1, true);
		organisms [0][0] = new Herbivore (1, true);
		organisms [1][2] = new Plant (1, true);
		organisms [0][0] = new Herbivore (1, true);
		organisms [1][2] = new Plant (1, true);

		
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
