package GameObjects;

import java.awt.*;

public class GameObject {

  private int hp = 1;
  private int atk = 1;

  // position of this gameobject
  // use double so we can have higher framerate
  // e.g. we can have very frequent updates of < 0.5 pixels of position
  private double x, y;
  private double vx, vy;

  public GameObject() {
    this(0, 0);
  }

  public GameObject(double x, double y) {
    this (x, y, 0, 0);
  }

  public GameObject(double x, double y, double vx, double vy) {
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void setX(double newX) {
    x = newX;
  }

  public void setY(double newY) {
    y = newY;
  }

  public double getSpeedX() {
    return vx;
  }

  public double getSpeedY() {
    return vy;
  }

  public void setSpeedX(double newVx) {
    vx = newVx;
  }

  public void setSpeedY(double newVy) {
    vy = newVy;
  }

  public double distanceFormula(double x1, double x2, double y1, double y2) {
    return Math.sqrt(Math.pow((x1-x2),2)+(Math.pow((y1-y2), 2)));
  }

  public void draw(Graphics g) {
    // by default draw nothing
    g.setColor(Color.BLUE);
    g.drawRect((int)getX()-10, (int)getY()-10, 20, 20);
  }
}
