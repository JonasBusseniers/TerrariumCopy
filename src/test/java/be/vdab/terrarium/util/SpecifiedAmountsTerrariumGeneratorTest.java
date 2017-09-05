package be.vdab.terrarium.util;

import be.vdab.entities.Carnivore;
import be.vdab.entities.Herbivore;
import be.vdab.entities.Plant;
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
}
