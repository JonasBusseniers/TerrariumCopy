package be.vdab.terrarium.util;

import java.util.List;

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

  public static <T> T chooseRandomFromList(List<T> list) {
    int position = guessNumberBetweenInclusive(0, list.size() - 1);
    return list.get(position);
  }

  public static <T> T removeRandomFromList(List<T> list) {
    int position = guessNumberBetweenInclusive(0, list.size() - 1);
    T toReturn = list.get(position);
    list.remove(position);
    return toReturn;
  }
}
