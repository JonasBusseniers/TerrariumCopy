
package be.vdab.terrarium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import be.vdab.entities.Carnivore;
import be.vdab.entities.Herbivore;
import be.vdab.entities.Organism;
import be.vdab.entities.Plant;

public class Board {

	private static int ROW = 6;
	private static int COL = 6;
	private int aantalPlantenPerBeurt = 2;
	private Organism[][] organisms = new Organism[ROW][COL];
	private Organism[][] organismsTemp = new Organism[ROW][COL];
	private int aantalOrganism = 0;
	private int aantalDagen = 0;

	public void setOrganisms(Organism[][] organisms) {
		for (int i = 0; i < organisms.length; i++) {
			for (int j = 0; j < organisms[i].length; j++) {
				if (organisms[i][j] != null) {
					aantalOrganism++;
				}
			}
		}
		this.organisms = organisms;
		this.organismsTemp = new Organism[organisms.length][organisms[0].length];

	}

	protected void setTestPositions() {
		organisms[0][0] = new Herbivore(1, false);
		aantalOrganism++;
		organisms[0][1] = new Plant(1, false);
		aantalOrganism++;
		organisms[0][3] = new Herbivore(1, false);
		aantalOrganism++;
		organisms[0][4] = new Herbivore(1, false);
		aantalOrganism++;
		organisms[2][3] = new Carnivore(1, false);
		aantalOrganism++;
		organisms[3][0] = new Carnivore(1, false);
		aantalOrganism++;
		organisms[3][1] = new Herbivore(1, false);
		aantalOrganism++;
		organisms[4][4] = new Herbivore(1, false);
		aantalOrganism++;
		// organisms[0][0] = new Herbivore(1, false); aantalOrganism++;
		// organisms[0][1] = new Carnivore(1, false); aantalOrganism++;
		// organisms[1][0] = new Herbivore(1, false); aantalOrganism++;
	}

	public void nextDay() throws BoardException {

		// Set all HasActed on false
		for (int i = 0; i < organisms.length; i++) {
			for (int j = 0; j < organisms[i].length; j++) {
				if (organisms[i][j] instanceof Organism) {
					organisms[i][j].setHasActed(false);
				}
			}
		}

		// make organismsTemp empty
		// Arrays.fill(organismsTemp, null);
		for (int i = 0; i < organismsTemp.length; i++) {
			for (int j = 0; j < organismsTemp[i].length; j++) {
				if (organismsTemp[i][j] instanceof Organism) {
					organismsTemp[i][j] = null;
				}
			}
		}

		// Do actions and set HasActed on true

		generateNewPlants();
		for (int i = 0; i < organisms.length; i++) {
			for (int j = 0; j < organisms[i].length; j++) {

				if (organisms[i][j] instanceof Organism) {
					if (!organisms[i][j].isHasActed()) {
						// No Actions performed.. DO ACTION
						organisms[i][j].setHasActed(true);
						if (organisms[i][j] instanceof Herbivore) {
							if ((j == organisms[i].length - 1) || (organisms[i][j + 1] == null)
									|| organisms[i][j + 1] instanceof Carnivore) // vermijdt
							// ArrayIndexOutOfBoundsException
							{
								move(i, j);
							} else if (organisms[i][j + 1] instanceof Plant) {
								eat(i, j, i, j + 1);
							} else if (organisms[i][j + 1] instanceof Herbivore) {
								mate();
							}
						} else if (organisms[i][j] instanceof Carnivore) {
							if ((j == organisms[i].length - 1) || (organisms[i][j + 1] == null)
									|| organisms[i][j + 1] instanceof Plant) {
								move(i, j);
							} else if (organisms[i][j + 1] instanceof Carnivore) {
								fight(i, j);
							} else if (organisms[i][j + 1] instanceof Herbivore) {
								eat(i, j, i, j + 1);
							}
						}
					}
				}
			}
			aantalDagen++;
		}
		// Merge organisms and organismsTemp
		for (int i = 0; i < organisms.length; i++) {
			for (int j = 0; j < organisms[i].length; j++) {
				if ((organisms[i][j] == null) && (organismsTemp[i][j] instanceof Organism)) {
					organisms[i][j] = organismsTemp[i][j];
				}
			}
		}
		// System.out.println("aantalOrg: " + aantalOrganism);
	}

	protected void fight(int i, int j) {
		if (organisms[i][j].getLife() < organisms[i][j + 1].getLife()) {
			eat(i, j + 1, i, j);
		} else if (organisms[i][j].getLife() > organisms[i][j + 1].getLife()) {
			eat(i, j, i, j + 1);
		}

	}

	protected void mate() throws BoardException {

		if (aantalOrganism < organisms[0].length * organisms.length) {
			int x;
			int y;
			do {
				x = (int) (Math.random() * organisms[0].length);
				y = (int) (Math.random() * organisms.length);
			} while (!isEmptyPosition(x, y));
			organismsTemp[x][y] = new Herbivore(0, true);
			aantalOrganism++;
		} else {
			throw new BoardException("Organism overload!");
		}
	}

	protected void eat(int i, int j, int i2, int j2) {
		organisms[i][j].setLife(organisms[i][j].getLife() + organisms[i2][j2].getLife());
		organisms[i2][j2] = null;
		aantalOrganism--;
	}

	protected void move(int i, int j) {
		List<Direction> directionsToTry = new ArrayList<>(
				Arrays.asList(Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN));
		int x;
		int y;
		do {

			x = i;
			y = j;

			int randomNum = ThreadLocalRandom.current().nextInt(0, directionsToTry.size());
			switch (directionsToTry.get(randomNum)) {
			case LEFT:
				y--;
				break;
			case RIGHT:
				y++;
				break;
			case UP:
				x--;
				break;
			case DOWN:
				x++;
				break;
			}
			if (isEmptyPosition(x, y)) {
				organismsTemp[x][y] = organisms[i][j];
				organisms[i][j] = null;

				break;
			} else {
				directionsToTry.remove(randomNum);

			}
		} while (!directionsToTry.isEmpty());
	}

	protected boolean isEmptyPosition(int x, int y) {
		try {
			if (organisms[x][y] == null && organismsTemp[x][y] == null) {
				return true;
			} else {
				return false;
			}
		} catch (ArrayIndexOutOfBoundsException ex) {
			return false;
		}
	}

	protected void generateNewPlants() throws BoardException {

		for (int i = 0; i < aantalPlantenPerBeurt; i++)

		{
			if (aantalOrganism < organisms[0].length * organisms.length) {
				int x;
				int y;
				do {
					x = (int) (Math.random() * organisms[0].length);
					y = (int) (Math.random() * organisms.length);
				} while (organisms[x][y] != null);
				organismsTemp[x][y] = new Plant(1, true);
				aantalOrganism++;
			} else {
				throw new BoardException("Plant overload!");
			}
		}

	}

	public Organism[][] getOrganisms() {
		return organisms;
	}

	public void setAantalPlantenPerBeurt(int aantalPlantenPerBeurt) {
		this.aantalPlantenPerBeurt = aantalPlantenPerBeurt;
	}
	
	public int getRow () {
		return organisms.length;
	}

	public int getColumn () {
		return organisms[0].length;
	} 
	public int getAantalDagen(){
		return aantalDagen;
	}

}