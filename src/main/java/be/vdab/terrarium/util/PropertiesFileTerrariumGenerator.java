package be.vdab.terrarium.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import be.vdab.entities.Carnivore;
import be.vdab.entities.Herbivore;
import be.vdab.entities.Organism;
import be.vdab.entities.Plant;

public class PropertiesFileTerrariumGenerator implements TerrariumGenerator {
	Properties properties = new Properties();
	int rows, cols;

	public PropertiesFileTerrariumGenerator(String fileName) {
		try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
			properties.load(is);
			rows = Integer.parseInt(properties.getProperty("rows"));
			cols = Integer.parseInt(properties.getProperty("cols"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Organism[][] generateTerrarium() {
		Organism[][] terrarium = new Organism[rows][cols];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				terrarium[row][col] = generateOrganism();
			}
		}
		return terrarium;
	}

	private Organism generateOrganism() {
		float probabilityEmpty = Float.parseFloat(properties.getProperty("probabilityEmpty"));
		float probabilityPlant = Float.parseFloat(properties.getProperty("probabilityPlant"));
		float probabilityHerbivore = Float.parseFloat(properties.getProperty("probabilityHerbivore"));
		float probabilityCarnivore = Float.parseFloat(properties.getProperty("probabilityCarnivore"));
		float total = probabilityEmpty + probabilityPlant + probabilityHerbivore + probabilityCarnivore;
		if (Math.round(total) != 1) {
			throw new RuntimeException("probabilities should add to 1");
		}
		Organism organism;
		double random = Math.random();
		if (random < probabilityEmpty) {
			organism = null;
		} else if (random < probabilityEmpty + probabilityPlant) {
			organism = generatePlant();
		} else if (random < probabilityEmpty + probabilityPlant + probabilityHerbivore) {
			organism = generateHerbivore();
		} else {
			organism = generateCarnivore();
		}

		return organism;
	}

	private Plant generatePlant() {
		int life = Integer.parseInt(properties.getProperty("lifePlant"));
		Plant plant = new Plant(life, false);
		return plant;
	}

	private Herbivore generateHerbivore() {
		Herbivore herbivore = new Herbivore(generateLife(), false);
		return herbivore;
	}

	private Carnivore generateCarnivore() {
		Carnivore carnivore = new Carnivore(generateLife(), false);
		return carnivore;
	}

	private int generateLife() {
		int minLife = Integer.parseInt(properties.getProperty("minLife"));
		int maxLife = Integer.parseInt(properties.getProperty("maxLife"));
		return Gambler.guessNumberBetweenInclusive(minLife, maxLife);
	}
}
