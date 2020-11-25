package pong;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.concurrent.Callable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;
import java.util.List;
import java.util.ArrayList;


public class Game {
  static List<PhysicsObject> physicsObjects = new ArrayList<PhysicsObject>();
  public static void main(String args[]) {
    System.out.println("Here");
    JFrame frame = new JFrame("Pong");
    frame.setSize(640, 480);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);

    Renderer renderer = new Renderer(640, 480);
    Ball ball = new Ball(5, new Point2d(200, 300));
    ball.setVelocity(new Vector2d(1, 1));
    ball.addCollisionHandler(new Callable() {
      public Object call() {
        ball.getVelocity().negate();
        return null;
      }
    });
    Paddle paddle = new Paddle(100, 15, new Point2d(270, 420));
    renderer.add(ball);
    renderer.add(paddle);
    physicsObjects.add(ball);
    physicsObjects.add(paddle);

    JPanel screen = new GameScreen(renderer);
    frame.add(screen);

    Runnable gameThread = new Runnable() {
      public void run() {

        System.out.println("Starting main loop");
        while(true) {
          updatePhysics();
          screen.repaint();
          try {
            Thread.sleep(7);
          } catch (InterruptedException ex) {
            return;
          }
        }
      }
    };
    new Thread(gameThread).start();
  }

  public static void updatePhysics() {
    //TODO(MAK): Physics should be its own class
    for (int i = 0; i < physicsObjects.size(); i++) {
      for (int j = i + 1; j < physicsObjects.size(); j++) {
        if (physicsObjects.get(i).collides(physicsObjects.get(j))) {
          physicsObjects.get(i).handleCollisions();
          physicsObjects.get(j).handleCollisions();
        }
      }
    }
    for (PhysicsObject o : physicsObjects) {
      System.out.println("Updating " + o);
      o.updatePosition();
    }
  }

  class GameThread implements Runnable {
    public void run() {
      while(true) {
        updatePhysics();
      }
    }
  }
}

