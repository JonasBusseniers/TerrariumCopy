package be.vdab.terrarium.util;

import org.junit.Test;

public class ByExampleTerrariumGeneratorTest {
  @Test
  public void smokeTest() {

    ByExampleTerrariumGenerator generator = new ByExampleTerrariumGenerator();
    generator.setFileName("terrariumExample");
    TerrariumRenderer renderer = new TerrariumRenderer();
    renderer.setTerrarium(generator.generateTerrarium());
    renderer.render();
  }
}
