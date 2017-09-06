package be.vdab.terrarium.util;

import be.vdab.entities.Carnivore;
import be.vdab.entities.Herbivore;
import be.vdab.entities.Plant;
import org.junit.Test;

public class SpecifiedProbabilitiesTerrariumGeneratorTest {
  @Test
  public void smokeTest() {
    SpecifiedProbabilitiesTerrariumGenerator generator =  new SpecifiedProbabilitiesTerrariumGenerator();
    generator.setSize(4,6);

    generator.setLifeForceRangeForType(Plant.class, 1, 1);
    generator.setLifeForceRangeForType(Herbivore.class, 0, 10);
    generator.setLifeForceRangeForType(Carnivore.class, 4, 5);

    generator.setProbabilityForType(Plant.class, 0.9);
    generator.setProbabilityForType(Herbivore.class, 0.1);

    TerrariumRenderer renderer = new TerrariumRenderer();
    renderer.setTerrarium(generator.generateTerrarium());

    renderer.render();
  }
}
