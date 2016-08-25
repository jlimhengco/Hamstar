package GameObjects;

import java.awt.*;

public abstract class Items extends GameObject {
  // Add things that all items should have.

  // Position is x, y
  // private int x;
  // private int y;

  //All items have at least 1 hp.
  private int hp = 1;

  //All items have do at least 1 dmg.
  private int dmg = 1;

  //All items start at lvl 1.
  private int lvl = 1;

  //All items have a minimum cost of 5 space crystals.
  private int cost = 5;

  public Items(double x, double y) {
    super(x, y);
  }

  // Use the item.
  public abstract void useItem();

  public abstract void draw(Graphics g);

}
