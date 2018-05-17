import java.util.*;

public class RNG {

  public int getRandomNum(int a, int b) {
    double x = (int)(Math.random()*((b-a)+1))+a;
    return (int)x;
  }

  public int[] getDiceRolls(int budget) {
    int[] rolls = new int[budget];
    for (int i=0; i<budget; i++) {
      rolls[i] = getRandomNum(0, 6);
    }
    Arrays.sort(rolls, 0, budget);
    return rolls;

}
