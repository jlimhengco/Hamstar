//mbp push
package Environment;

import Controls.*;
import GameObjects.*;
import SoundPlayer.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashSet;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Environment extends JPanel {

  public static final int WIDTH = 700, HEIGHT = 500;
  private static final int DELAY = 40;
  private static final int INIT_ASTEROIDS = 30;
  private static final int LASER_SPEED = 200;

  public static final int LEFT_CLICK = MouseEvent.BUTTON1;

  private static BufferedImage imgHamstar = null;
  private static BufferedImage imgBlackHole = null;
  private static BufferedImage imgAsteroid = null;
  private static BufferedImage imgLaser = null;

  private Hamstar hamstar;

  private HashSet<BlackHoles> setBlackHoles;
  private HashSet<Asteroids> setAsteroids;
  private HashSet<Laser> setLaser;
  private int[] idStore;

  static {
    try {
      imgHamstar = ImageIO.read(new File("Images/hamster.png"));
      imgBlackHole = ImageIO.read(new File("Images/seed.png"));
      imgAsteroid = ImageIO.read(new File("Images/asteroid.png"));
      imgLaser = ImageIO.read(new File("Images/laser.png"));
    } catch (IOException e) {
    }
  }

  public static BufferedImage resize(BufferedImage img, int newW, int newH) {
    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
  }

  public void initAsteroids() {
    setAsteroids = new HashSet<Asteroids>(INIT_ASTEROIDS);
    for (int i=0; i<INIT_ASTEROIDS; i++) {
      Asteroids asteroid = new Asteroids(Math.random()*WIDTH, Math.random()*HEIGHT, Math.random()*100, Math.random()*100);
      setAsteroids.add(asteroid);
    }
  }

  public Environment() {
    init();

    setSize(WIDTH, HEIGHT);
    setPreferredSize(getSize());
    setFocusable(true);

    final KeyControls keyControls = new KeyControls();
    addKeyListener(keyControls);

    final MouseControls mouseControls = new MouseControls();
    addMouseListener(mouseControls);

    Timer timer = new Timer(DELAY, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        hamstar.updatePos(DELAY, keyControls.up(), keyControls.down(), keyControls.left(), keyControls.right());
        HashSet<Asteroids> asteroidsClone = new HashSet();
        HashSet<Laser> laserClone = new HashSet();

        if (mouseControls.left()) {
          double deltaX = mouseControls.getX.get(LEFT_CLICK)-hamstar.getX();
          double deltaY = mouseControls.getY.get(LEFT_CLICK)-hamstar.getY();
          double factor = LASER_SPEED/Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
          double vx = deltaX * factor;
          double vy = deltaY * factor;
          Laser laser = new Laser(hamstar.getX(), hamstar.getY(), vx, vy);
          setLaser.add(laser);
        }

        for (Asteroids asteroid : setAsteroids){
          Laser las;
          if ((las = asteroid.collideWithLaser(setLaser)) != null) {
            asteroidsClone.add(asteroid);
            laserClone.add(las);
          }
          asteroid.updatePos(DELAY);
        }

        for (Laser laser : setLaser) {
          laser.updatePos(DELAY);
        }

        if (keyControls.blackHole()) {
          BlackHoles newBlackHole = new BlackHoles(hamstar.getX(), hamstar.getY());
          Sound.playBlackHole();
          setBlackHoles.add(newBlackHole);
        }

        setAsteroids.removeAll(asteroidsClone);
        setLaser.removeAll(laserClone);
        repaint();
      }
    });
    timer.start();

    requestFocus();

    // start music
    Sound.playBgMusic();
  }

  // most elegant way to deal with negative numbers in Java, see
  // http://stackoverflow.com/questions/4412179/best-way-to-make-javas-modulus-behave-like-it-should-with-negative-numbers/4412200#4412200
  public static double wrapX(double x) {
    return (x % WIDTH + WIDTH) % WIDTH;
  }

  public static double wrapY(double y) {
    return (y % HEIGHT + HEIGHT) % HEIGHT;
  }

  private void init() {
    // initial position is middle of screen
    hamstar = new Hamstar(WIDTH/2, HEIGHT/2);
    setBlackHoles = new HashSet<BlackHoles>();
    setLaser = new HashSet<Laser>();

    // init items, asteroids, etc.
    initAsteroids();
  }

  public void paintComponent(Graphics g) {
    // for now, use solid color for background
    super.paintComponent(g);
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    imgHamstar = resize(imgHamstar, 50, 50);
    imgBlackHole = resize(imgBlackHole, 30, 30);
    imgAsteroid = resize(imgAsteroid, 30, 30);
    imgLaser = resize(imgLaser, 30, 30);
    g.drawImage(imgHamstar, (int)hamstar.getX(), (int)hamstar.getY(), null);
    // if ((blackhole.x != 0) && (blackhole.y != 0)){
    for (BlackHoles blackHole : setBlackHoles) {
      g.drawImage(imgBlackHole, (int) blackHole.getX(), (int) blackHole.getY(), null);
    }
    for (Asteroids asteroid : setAsteroids) {
     g.drawImage(imgAsteroid, (int) asteroid.getX(), (int) asteroid.getY(), null);
    }
    for (Laser laser : setLaser) {
      g.drawImage(imgLaser, (int) laser.getX(), (int) laser.getY(), null);
    }
  }

}
