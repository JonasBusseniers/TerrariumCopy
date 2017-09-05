package be.vdab.terrarium;

import java.util.Scanner;

import be.vdab.terrarium.util.PropertiesFileTerrariumGenerator;
import be.vdab.terrarium.util.TerrariumRenderer;

public class Gui {

	public static void main(String[] args) {
		Board.setOrganisms(new PropertiesFileTerrariumGenerator("terrariumGame.properties").generateTerrarium());

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
