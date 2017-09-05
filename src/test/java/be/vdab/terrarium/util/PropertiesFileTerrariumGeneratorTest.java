package be.vdab.terrarium.util;

import be.vdab.entities.Organism;
import org.junit.Test;

public class PropertiesFileTerrariumGeneratorTest {
  @Test
  public void smokeTest() {
    PropertiesFileTerrariumGenerator propertiesFileTerrariumGenerator = new PropertiesFileTerrariumGenerator("terrariumGame.properties");
    Organism[][] terrarium = propertiesFileTerrariumGenerator.generateTerrarium();
    TerrariumRenderer renderer = new TerrariumRenderer();
    renderer.setTerrarium(terrarium);
    renderer.render();
  }
}
