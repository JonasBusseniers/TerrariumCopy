package be.tomvdab.terrarium.utility;

import static org.junit.Assert.*;

import be.vdab.terrarium.util.Gambler;
import org.junit.Test;

public class GamblerTest {

  private static final int MAX_TRY_TIME_MILLISECONDS = 500;

  @Test
  public void shouldBePossibleToGenerateBoundary() {
    assertTrue(tryToGenerateBetweenInclusive(1, 100, 100));
    assertTrue(tryToGenerateBetweenInclusive(1, 100, 1));
  }

  @Test
  public void shouldBePossibleToGenerateInBetween() {
    assertTrue(tryToGenerateBetweenInclusive(1, 100, 50));
  }

  @Test
  public void shouldNotBePossibleToExceedBoundaries() {
    assertFalse(tryToGenerateBetweenInclusive(5, 30, 2));
    assertFalse(tryToGenerateBetweenInclusive(5, 30, 101));
  }

  private boolean tryToGenerateBetweenInclusive(int min, int max, int toBeGenerated) {
    boolean numberGenerated = false;

    long currentTimeMillis = System.currentTimeMillis();

    while ( numberGenerated == false && System.currentTimeMillis() - currentTimeMillis < MAX_TRY_TIME_MILLISECONDS) {
      if (Gambler.guessNumberBetweenInclusive(min, max) == toBeGenerated) {
        numberGenerated = true;
      }
    }
    return numberGenerated;
  }
}
