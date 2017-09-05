package be.vdab.terrarium.util;

import be.vdab.entities.Carnivore;
import be.vdab.entities.Herbivore;
import be.vdab.entities.Organism;
import be.vdab.entities.Plant;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ByExampleTerrariumGenerator implements TerrariumGenerator {
  private String fileName;
  private ArrayList<Organism> organisms = new ArrayList<>();
  private Organism[][] terrarium;
  private int rowsCounted = 0;
  private int colsCounted = 0;

  @Override
  public Organism[][] generateTerrarium() {
    parseExampleFile(fileName);
    terrarium = new Organism[rowsCounted][colsCounted];
    int row = 0;
    int col = 0;

    for (Organism organism : organisms) {
      terrarium[row][col] = organism;
      col++;
      if (col >= colsCounted) {
        col = 0;
        row++;
      }
    }
    return terrarium;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  private void parseExampleFile(String filename) {
    try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName) ){
      Scanner scanner = new Scanner(is);

      while (scanner.hasNextLine()) {
        rowsCounted++;
        String line = scanner.nextLine();
        String[] organismStrings = line.split(" ");
        colsCounted = organismStrings.length;
        for (String organismString : organismStrings) {
          Organism organism = createOrganismFromText(organismString);
          organisms.add(organism);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Organism createOrganismFromText(String text) {
    Organism organism = null;

    char organismType = text.charAt(0);
    int life = 0;
    if (organismType != '.') {
      life = Integer.parseInt(String.valueOf(text.charAt(1)));
    }

    switch(organismType) {
      case '.':break;
      case 'P':organism = new Plant(life, false);
               break;
      case 'H':organism = new Herbivore(life, false);
               break;
      case 'C':organism = new Carnivore(life, false);
               break;
    }
    return organism;
  }
}
