package Controls;

import GameObjects.BlackHoles;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.HashMap;

public class KeyControls extends KeyAdapter {

  public static final int KEY_UP = KeyEvent.VK_W;
  public static final int KEY_DOWN = KeyEvent.VK_S;
  public static final int KEY_LEFT = KeyEvent.VK_A;
  public static final int KEY_RIGHT = KeyEvent.VK_D;
  public static final int KEY_BLACKHOLE = KeyEvent.VK_1;

  private static final HashMap<Integer, Long> USE_DELAYS;
  static {
    USE_DELAYS = new HashMap<Integer, Long>();
    USE_DELAYS.put(KEY_BLACKHOLE, BlackHoles.USE_DELAY);
  }

  private HashMap<Integer, KeyState> keys;

  public KeyControls() {
    keys = new HashMap<Integer, KeyState>();
    keys.put(KEY_UP, new KeyState());
    keys.put(KEY_DOWN, new KeyState());
    keys.put(KEY_LEFT, new KeyState());
    keys.put(KEY_RIGHT, new KeyState());
    keys.put(KEY_BLACKHOLE, new KeyState());
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();
    KeyState state = keys.get(code);
    if (state != null) {
      long currTime = System.currentTimeMillis();
      if (USE_DELAYS.get(code) == null ||
              (USE_DELAYS.get(code) != null && currTime - USE_DELAYS.get(code) > state.lastPress)) {
        // System.out.printf("%d %d %d\n", currTime, state.lastPress, USE_DELAYS.get(code));
        state.down = true;
        state.lastPress = currTime;
      }
    }

  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();
    KeyState state = keys.get(code);
    if (state != null) {
      state.down = false;
    }
  }

  public boolean blackHole() {
    boolean ret = keys.get(KEY_BLACKHOLE).down;
    keys.get(KEY_BLACKHOLE).down = false;
    return ret;
  }

  public boolean up() {
    return keys.get(KEY_UP).down;
  }

  public boolean down() {
    return keys.get(KEY_DOWN).down;
  }

  public boolean left() {
    return keys.get(KEY_LEFT).down;
  }

  public boolean right() {
    return keys.get(KEY_RIGHT).down;
  }
}

class KeyState {
  boolean down;
  long lastPress;
}
