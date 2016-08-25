package GameObjects;

import java.awt.*;
import java.util.HashSet;

public class Laser extends GameObject {

  private int id;
  private static int idCounter;

  public Laser(double x, double y, double speedx, double speedy) {
    super(x, y, speedx, speedy);

    id = idCounter;
    idCounter++;
  }

  public void updatePos(int ms) {
    double vx = getSpeedX(), vy = getSpeedY();
    double x = getX() + (ms * vx / 1000), y = getY() + (ms * vy / 1000);
    setX(x);
    setY(y);
  }

  @Override
  public int hashCode() {
    return id;
  }
}
