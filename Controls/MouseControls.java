package Controls;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.HashMap;

public class MouseControls extends MouseAdapter {

  public static final int LEFT_CLICK = MouseEvent.BUTTON1;

  private static final HashMap<Integer, Long> USE_DELAYS;
  static {
    USE_DELAYS = new HashMap<Integer, Long>();
  }

  private HashMap<Integer, MouseState> clicks;
  public HashMap<Integer, Integer> getX;
  public HashMap<Integer, Integer> getY;

  public MouseControls() {
    clicks = new HashMap<Integer, MouseState>();
    clicks.put(LEFT_CLICK, new MouseState());
    getX = new HashMap<Integer, Integer>();
    getY = new HashMap<Integer, Integer>();
  }

  @Override
  public void mousePressed(MouseEvent e) {
    int code = e.getButton();
    MouseState state = clicks.get(code);
    if (state != null) {
      long currTime = System.currentTimeMillis();
      if (USE_DELAYS.get(code) == null ||
              (USE_DELAYS.get(code) != null && currTime - USE_DELAYS.get(code) > state.lastClick)) {
        state.down = true;
        state.lastClick = currTime;
      }
      getX.put(LEFT_CLICK, e.getX());
      getY.put(LEFT_CLICK, e.getY());
    }

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    int code = e.getButton();
    MouseState state = clicks.get(code);
    if (state != null) {
      state.down = false;
    }
  }

  public boolean left() {
    boolean ret = clicks.get(LEFT_CLICK).down;
    clicks.get(LEFT_CLICK).down = false;
    return ret;
  }

  public int getX() {
    return this.getX();
  }

  public int getY() {
    return this.getY();
  }
}

class MouseState {
  boolean down;
  long lastClick;
}
