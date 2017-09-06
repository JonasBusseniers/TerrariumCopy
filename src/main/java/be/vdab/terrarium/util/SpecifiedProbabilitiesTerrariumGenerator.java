package be.vdab.terrarium.util;

import be.vdab.entities.Organism;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpecifiedProbabilitiesTerrariumGenerator implements TerrariumGenerator {

  private int rows, cols, totalOrganisms;
  private HashMap<Class<? extends Organism>, Double> probabilities = new HashMap<>();
  private HashMap<Class<? extends Organism>, Pair> lifeForceRanges = new HashMap<>();


  public void setSize(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
  }

  public void setProbabilityForType(Class<? extends Organism> organismType, double probability) {
    probabilities.put(organismType, probability);
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

//              A               B             C                   D
//    |--------------------|---------|---------------|-------------------------|
//    ^                    ^         ^               ^                         ^
//    0                  probA  probA+probB  probA+probB+probC      probA+probB+probC+probD = 1
//
//    Smart? strategy to pick A with probability probA, pick B with probability probB, etc :
//    Pick a double from zero to one. Pick A/B/C/D if the number is chosen in the corresponding interval.

    List<OrganismInterval> organismIntervalList = createOrganismIntervalList();
    for (int row = 0; row < terrarium.length; row++) {
      for (int col = 0; col < terrarium[0].length; col++) {
        double random = Math.random();
        for (OrganismInterval organismInterval : organismIntervalList) {
          if (organismInterval.contains(random)) {
            int minLifeForce = lifeForceRanges.get(organismInterval.organismType).x;
            int maxLifeForce = lifeForceRanges.get(organismInterval.organismType).y;
            int lifeForce = Gambler.guessNumberBetweenInclusive(minLifeForce, maxLifeForce);
            Organism organism = null;
            try {
              organism = organismInterval.organismType.getDeclaredConstructor(Integer.TYPE, Boolean.TYPE).newInstance(lifeForce, false);
            } catch (InstantiationException e) {
              e.printStackTrace();
            } catch (IllegalAccessException e) {
              e.printStackTrace();
            } catch (InvocationTargetException e) {
              e.printStackTrace();
            } catch (NoSuchMethodException e) {
              e.printStackTrace();
            }
            terrarium[row][col] = organism;
            break;
          }
        }
      }
    }

    return terrarium;
  }

  private List<OrganismInterval> createOrganismIntervalList() {
    List<OrganismInterval> organismIntervalList = new ArrayList<>();
    double lastIntervalMaxExclusive = 0;
    for (Class<? extends Organism> organismType : probabilities.keySet()) {
      double probability = probabilities.get(organismType);
      OrganismInterval organismInterval = new OrganismInterval(organismType, lastIntervalMaxExclusive, lastIntervalMaxExclusive + probability);
      lastIntervalMaxExclusive = lastIntervalMaxExclusive + probability;
      organismIntervalList.add(organismInterval);
    }
    return organismIntervalList;
  }

  private class Pair {
    int x ,y;
    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private class OrganismInterval {
    double minInclusive ,maxExclusive;
    Class<? extends Organism> organismType;
    OrganismInterval(Class<? extends Organism> organismType, double minInclusive, double maxExclusive) {
      this.organismType = organismType;
      this.minInclusive = minInclusive;
      this.maxExclusive = maxExclusive;
    }

    boolean contains(double point) {
      return (point >= minInclusive && point < maxExclusive);
    }
  }
}