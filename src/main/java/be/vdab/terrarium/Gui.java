package be.vdab.terrarium;

import java.util.Scanner;

import be.vdab.entities.Carnivore;
import be.vdab.entities.Herbivore;
import be.vdab.entities.Plant;
import be.vdab.terrarium.util.SpecifiedAmountsTerrariumGenerator;
import be.vdab.terrarium.util.TerrariumRenderer;

public class Gui {

	public static void main(String[] args) {

		SpecifiedAmountsTerrariumGenerator generator = new SpecifiedAmountsTerrariumGenerator();
		generator.setSize(6, 6);
		generator.setAmountForType(Plant.class, 2);
		generator.setAmountForType(Herbivore.class, 3);
		generator.setAmountForType(Carnivore.class, 2);
		generator.setLifeForceRangeForType(Plant.class, 1, 1);
		generator.setLifeForceRangeForType(Herbivore.class, 0, 10);
		generator.setLifeForceRangeForType(Carnivore.class, 0, 10);

		// Board.setOrganisms(new
		// PropertiesFileTerrariumGenerator("terrariumGame.properties").generateTerrarium());
		Board.setOrganisms(generator.generateTerrarium());
		Board.setAANTALPLANTENPERBEURT(1);

		TerrariumRenderer renderer = new TerrariumRenderer(Board.getOrganisms());

		// Board.print();
		renderer.render();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Druk op <ENTER> voor een volgende dag,\nDruk op <S> + <ENTER> om te stoppen.");
		String invoer = scanner.nextLine();

		while (!invoer.equals("S")) {
			try {
				Board.nextDay();
			} catch (BoardException ex) {
				System.out.println(ex.getMessage());
				break;
			}

			// Board.print();
			renderer.render();

			System.out.println("Druk op <ENTER> voor een volgende dag,\nDruk op <S> + <ENTER> om te stoppen.");
			invoer = scanner.nextLine();
		}
	}
}
