package be.vdab.terrarium.util;

public class Gambler {
  public static boolean gambleWithPropability(float probability) {
    if (probability == 0) {
      return false;
    }
    if (probability == 1) {
      return true;
    }
    return (Math.random() < probability);
  }

  public static int guessNumberBetweenInclusive(int min, int max) {
    return min + (int) Math.round((max - min) * Math.random());
  }
}
