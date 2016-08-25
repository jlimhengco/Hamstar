package Launcher;

import Environment.Environment;

import java.awt.*;
import javax.swing.*;

public class Launcher {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Hamstar");

    frame.setContentPane(new Environment());
    frame.setResizable(false);
    frame.pack();
    frame.setLocationRelativeTo(null);  // center on screen
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}