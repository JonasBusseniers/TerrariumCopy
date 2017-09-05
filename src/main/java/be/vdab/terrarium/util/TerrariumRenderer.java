package be.vdab.terrarium.util;

import be.vdab.entities.Organism;

public class TerrariumRenderer {
	private Organism[][] terrarium;

	public TerrariumRenderer(Organism[][] terrarium) {
		this();
		this.terrarium = terrarium;
	}

	public TerrariumRenderer() {

	}

	public void setTerrarium(Organism[][] terrarium) {
		this.terrarium = terrarium;
	}

	public void render() {
		for (int i = 0; i < terrarium.length; i++) {
			for (int j = 0; j < terrarium[i].length; j++) {
				if (terrarium[i][j] == null) {
					System.out.print(".\t");
				} else {
					System.out.print(terrarium[i][j].toString() + terrarium[i][j].getLife() + "\t");
				}
			}
			System.out.println();
		}
	}

}
