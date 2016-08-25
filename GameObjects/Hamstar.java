package GameObjects;

import Environment.Environment;

import java.awt.*;

public class Hamstar extends GameObject {
  private static double BASE_ACCEL = 800.0;
  private static double BASE_MAX_SPEED = 400.0;

  // velocity and acceleration
  // acceleration / max velocity may be upgraded in the future
  private double accel;
  private double maxv;

  public Hamstar(int x, int y) {
    super(x, y);

    accel = BASE_ACCEL;
    maxv = BASE_MAX_SPEED;
  }

  public double getMaxSpeed() {
    return maxv;
  }

  public double getAcceleration() {
    return accel;
  }

  public void updatePos(int ms, boolean up, boolean down, boolean left, boolean right) {
    double vx = getSpeedX(), vy = getSpeedY();
    double x = getX() + (ms * vx / 1000), y = getY() + (ms * vy / 1000);

    // bound position by environment edges
    // setX( clipRange(x, 0, Environment.WIDTH) );
    // setY( clipRange(y, 0, Environment.HEIGHT) );

    // have environment wrap around
    setX( Environment.wrapX(x) );
    setY( Environment.wrapY(y) );

    if (up)
      vy -= accel * ms / 1000;
    if (down)
      vy += accel * ms / 1000;
    if (left)
      vx -= accel * ms / 1000;
    if (right)
      vx += accel * ms / 1000;

    // bound velocities by max speed
    setSpeedX( clipRange(vx, -maxv, maxv) );
    setSpeedY( clipRange(vy, -maxv, maxv) );
  }
  public static double clipRange(double val, double min, double max) {
    return Math.max(min, Math.min(val, max));
  }

  // Pressing "j" will make Hamstar attack.
  private void attack(boolean j) {
    if (j) {

    }
      //draw laser and projection
      //obj.hp = obj.hp - this.atk; 
  }

  // Pressing "k" will make Hamstar activate ability 1.
  private void abilityOne(boolean k) {
    if (k) {

    }
      //activate ability 1
  }

  // Pressing "l" will make Hamstar activate ability 2.
  private void abilityTwo(boolean l) {
    if (l) {

    }
      //activate ability 2
  }

  // Pressing "u" will make Hamstar use item 1.
  private void itemOne(boolean u) {
    if (u) {

    }
      //use item 1
  }

  // Pressing "i" will make Hamstar use item 2.
  private void itemTwo(boolean i) {
    if (i) {

    }
      //use item 2
  }

  // Pressing "o" will make Hamstar use item 3.
  private void itemThree(boolean o) {
    if (o) {
      
    }
      //use item 3
  }

  public void draw(Graphics g) {
    // for now, just draw a rectangle
    g.setColor(Color.RED);
    g.drawRect((int)(getX()-10), (int)(getY()-10), 20, 20);
  }

}
