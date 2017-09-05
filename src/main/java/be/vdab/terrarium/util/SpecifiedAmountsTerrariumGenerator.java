package be.vdab.terrarium.util;

import be.vdab.entities.Organism;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpecifiedAmountsTerrariumGenerator implements TerrariumGenerator {
  private int rows, cols, totalOrganisms;
  private HashMap<Class<? extends Organism>, Integer> amounts = new HashMap<>();
  private HashMap<Class<? extends Organism>, Pair> lifeForceRanges = new HashMap<>();


  public void setSize(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
  }

  public void setAmountForType(Class<? extends Organism> organismType, int amount) {
    if (totalOrganisms + amount > rows * cols) {
      throw new RuntimeException("You are trying to put too many organisms in the terrarium :(");
    }
    amounts.put(organismType, amount);
    totalOrganisms += amount;
  }

  public void setLifeForceRangeForType(Class<? extends Organism> organismType, int minLifeForce, int maxLifeForce) {
    lifeForceRanges.put(organismType, new Pair(minLifeForce, maxLifeForce));
  }

  @Override
  public Organism[][] generateTerrarium() {
    if (rows == 0 || cols == 0) {
      throw new RuntimeException("Sorry, can't create a terrarium with dimensions " + rows + "x" + cols + "!");
    }
    Organism[][] terrarium = new Organism[rows][cols];

    List<Pair> emptyPositions = createPositionListForArray(terrarium);

    for (Class<? extends Organism> organismType : amounts.keySet()) {
      for (int i = 0; i < amounts.get(organismType); i++) {
        int minLifeForce = lifeForceRanges.get(organismType).x;
        int maxLifeFoce = lifeForceRanges.get(organismType).y;

        int lifeForce = Gambler.guessNumberBetweenInclusive(minLifeForce, maxLifeFoce);
        Organism organism = null;
        try {
          organism = organismType.getDeclaredConstructor(Integer.TYPE, Boolean.TYPE).newInstance(lifeForce ,false);
        } catch (InstantiationException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (InvocationTargetException e) {
          e.printStackTrace();
        } catch (NoSuchMethodException e) {
          e.printStackTrace();
        }

        Pair emptyPosition = Gambler.removeRandomFromList(emptyPositions);
        terrarium[emptyPosition.x][emptyPosition.y] = organism;
      }
    }
    return terrarium;
  }

  private List<Pair> createPositionListForArray(Object[][] array) {
    List<Pair> foundPositions = new ArrayList<>();
    for (int row = 0; row < array.length; row++) {
      for (int col = 0; col < array[0].length; col++) {
        foundPositions.add(new Pair(row, col));
      }
    }
    return foundPositions;
  }

  private class Pair {
    int x ,y;
    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
