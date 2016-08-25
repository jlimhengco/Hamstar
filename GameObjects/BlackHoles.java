package GameObjects;

import java.awt.*;
import java.util.ArrayList;


public class BlackHoles extends GameObject {
  // Blackholes don't die they are based on timers in seconds.
  private int timer = 5;
  private double cost = 10;
  private int lvl;
  private int id;

  private static int idCounter = 0;

  public static final long USE_DELAY = 1000;


  public BlackHoles(double x, double y) {
    super(x, y);

    id = idCounter;
    idCounter++;
  }

  @Override
  public int hashCode() {
    return id;
  }

}
