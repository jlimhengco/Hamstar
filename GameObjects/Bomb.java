package GameObjects;

import java.awt.*;

public class Bomb extends Items {

  // Lowest lvl bombs = 2 dmg but increases by 1 dmg per level.
  private int dmg = 2;

  // Cost 5 space crystals + 5 per level;
  private int cost = 5 ;

  private int lvl;

  private void levelUpBomb() {
    this.lvl += 1;
    this.dmg += 1;
    this.cost += 5;
  }

  public Bomb(int x, int y) {
    super(x, y);
  }

  // Hamstar sets down a bomb that explodes on impact with an enemy or asteroid.
  public void useItem() {
    
  }

  public void draw(Graphics g) {
    // for now, just draw a rectangle
    g.setColor(Color.YELLOW);
    g.drawRect(150, 150, 20, 20);
  }

}
