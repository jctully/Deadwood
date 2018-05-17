import java.util.Random;

public class RNG {

  public int getRandomNum(int a, int b) {
    double x = (int)(Math.random()*((b-a)+1))+a;
    return (int)x;
  }

}
