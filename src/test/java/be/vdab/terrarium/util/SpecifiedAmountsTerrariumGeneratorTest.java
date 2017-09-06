package be.vdab.terrarium.util;

import be.vdab.entities.Carnivore;
import be.vdab.entities.Herbivore;
import be.vdab.entities.Organism;
import be.vdab.entities.Plant;
import org.junit.Assert;
import org.junit.Test;

public class SpecifiedAmountsTerrariumGeneratorTest {
  @Test
  public void smokeTest() {
    SpecifiedAmountsTerrariumGenerator generator = new SpecifiedAmountsTerrariumGenerator();

    generator.setSize(6,6);

    generator.setAmountForType(Plant.class, 4);
    generator.setAmountForType(Herbivore.class, 10);
    generator.setAmountForType(Carnivore.class, 1);

    generator.setLifeForceRangeForType(Plant.class, 1, 1);
    generator.setLifeForceRangeForType(Herbivore.class, 0, 4);
    generator.setLifeForceRangeForType(Carnivore.class, 3, 4);

    TerrariumRenderer renderer = new TerrariumRenderer(generator.generateTerrarium());
    renderer.render();
  }

  @Test
  public void shouldGenerateCorrectAmounts() {
    final int amountOfPlants = 4;
    final int amountOfCarnivores = 2;
    final int amountOfHerbivores = 1;
    SpecifiedAmountsTerrariumGenerator generator = new SpecifiedAmountsTerrariumGenerator();

    generator.setSize(6,6);

    generator.setAmountForType(Plant.class, amountOfPlants);
    generator.setAmountForType(Herbivore.class, amountOfHerbivores);
    generator.setAmountForType(Carnivore.class, amountOfCarnivores);

    generator.setLifeForceRangeForType(Plant.class, 1, 1);
    generator.setLifeForceRangeForType(Herbivore.class, 0, 4);
    generator.setLifeForceRangeForType(Carnivore.class, 3, 4);

    Organism[][] terrarium = generator.generateTerrarium();
    Assert.assertEquals(amountOfPlants, countAmountOfTypeInTerrarium(terrarium, Plant.class));
    Assert.assertEquals(amountOfHerbivores, countAmountOfTypeInTerrarium(terrarium, Herbivore.class));
    Assert.assertEquals(amountOfCarnivores, countAmountOfTypeInTerrarium(terrarium, Carnivore.class));

  }

  private int countAmountOfTypeInTerrarium(Organism[][] terrarium, Class<? extends Organism> organismType) {
    int count = 0;
    for (int row = 0; row < terrarium.length; row++)  {
      for (int col = 0; col < terrarium[0].length; col++) {
        if (organismType.isInstance(terrarium[row][col])) {
          count++;
        }
      }
    }
    return count;
  }
}
