
package GameObjects;

import Environment.Environment;

import java.awt.*;
import java.util.HashSet;

public class Asteroids extends GameObject {
  // Asteroid damage and health at level 1.
  private int dmg = 1;
  private int hp = 1;
  private int id;

  private static int idCounter = 0;

  public Asteroids(double x, double y, double speedx, double speedy) {
    super(x, y, speedx, speedy);

    id = idCounter;
    idCounter++;
  }

  public void updatePos(int ms) {
    double vx = getSpeedX(), vy = getSpeedY();
    double x = getX() + (ms * vx / 1000), y = getY() + (ms * vy / 1000);

    // have environment wrap around
    setX( Environment.wrapX(x) );
    setY( Environment.wrapY(y) );
  }

  public Laser collideWithLaser(HashSet<Laser> laser) {
    for (Laser las : laser) {
      if (distanceFormula(las.getX(), this.getX(), las.getY(), this.getY()) < 31) {
        return las;
      }
    }
    return null;
  }

  @Override
  public int hashCode() {
    return id;
  }

}
