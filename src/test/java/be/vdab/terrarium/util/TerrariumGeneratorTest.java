package be.vdab.terrarium.util;

import be.vdab.entities.Organism;
import org.junit.Test;

import javax.swing.*;

public class TerrariumGeneratorTest {
  @Test
  public void smokeTest() {
    TerrariumGenerator terrariumGenerator = new TerrariumGenerator("terrariumGame.properties");
    Organism[][] terrarium = terrariumGenerator.generateTerrarium();
    TerrariumRenderer renderer = new TerrariumRenderer();
    renderer.setTerrarium(terrarium);
    renderer.render();
  }
}
