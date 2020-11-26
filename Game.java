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
  enum Axis { X, Y };
  enum GameState {
    START,
    PLAYING,
    END,
  };
  private static final int WIDTH = 640;
  private static final int HEIGHT = 480;
  private static final Vector2d INITIAL_BALL_VELOCITY = new Vector2d(1, -1);

  private static List<PhysicsObject> physicsObjects = new ArrayList<PhysicsObject>();
  private static int score;
  private static GameState gameState = GameState.START;
  
  public static void main(String args[]) {
    // Main window.
    JFrame frame = new JFrame("Pong");
    frame.setSize(WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);

    // Gameplay area within the window.
    Renderer renderer = new Renderer(WIDTH, HEIGHT);
    JPanel screen = new GameScreen(renderer);
    frame.add(screen);

    // Score widget.
    score = 0;
    TextObject scoreDisplay = new TextObject(new Point2d(560, 30));
    scoreDisplay.setText("Score: " + score);

    //Ball
    Ball ball = new Ball(5, new Point2d(200, 300));
    ball.setVelocity(INITIAL_BALL_VELOCITY);

    // Paddle
    Paddle paddle = new Paddle(
        100, 15, // size
        new Point2d(270, 420), // initial location on screen.
        MousePosition.createMousePosition(frame) // Mouse
    );
    paddle.addCollisionHandler(
        other -> {
          if (other == ball) {
            other.setVelocity(bounce(other.getVelocity(), Axis.Y)); // bounce on the Y axis
            score++;
            scoreDisplay.setText("Score: " + score);
          }
        });

    // Set up walls on the top, left and right of the playing area.
    InvisibleBarrier ceiling = new InvisibleBarrier(new Point2d(0, 0), WIDTH, 5);
    ceiling.addCollisionHandler(
        other -> {
          if (other == ball) other.setVelocity(bounce(other.getVelocity(), Axis.Y));
        });
    InvisibleBarrier leftWall = new InvisibleBarrier(new Point2d(0, 0), 5, HEIGHT);
    leftWall.addCollisionHandler(
        other -> {
          if (other == ball) other.setVelocity(bounce(other.getVelocity(), Axis.X));
        });
    InvisibleBarrier rightWall = new InvisibleBarrier(new Point2d(WIDTH, 0), 5, HEIGHT);
    rightWall.addCollisionHandler(
        other -> {
          if (other == ball) other.setVelocity(bounce(other.getVelocity(), Axis.X));
        });

    InvisibleBarrier floor = new InvisibleBarrier(new Point2d(0, HEIGHT), WIDTH, 5);
    floor.addCollisionHandler(
        other -> {
          if (other == ball) {
            other.setVelocity(new Vector2d(0, 0));
            System.out.println("Ball fell");
            gameState = GameState.END;
          }
        });

    // All displayable objects.
    renderer.add(ball);
    renderer.add(paddle);
    renderer.add(scoreDisplay);

    // All objects that may move/collide.
    physicsObjects.add(ball);
    physicsObjects.add(paddle);
    physicsObjects.add(ceiling);
    physicsObjects.add(leftWall);
    physicsObjects.add(rightWall);
    physicsObjects.add(floor);

    // Main game loop
    Runnable gameThread = new Runnable() {
      public void run() {
        while(true) {
          switch(gameState) {
            case START:
            case PLAYING:
              updatePhysics();
              screen.repaint();
              break;
            case END:
              return;
            default:
              screen.repaint();
          }
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

  /** Computes collisions and movement for all physics objects */
  public static void updatePhysics() {
    //TODO(MAK): Physics should be its own class
    // Check all pairs of objects for collisions.
    for (int i = 0; i < physicsObjects.size(); i++) {
      for (int j = i + 1; j < physicsObjects.size(); j++) {
        if (physicsObjects.get(i).collides(physicsObjects.get(j))) {
          physicsObjects.get(i).handleCollisions(physicsObjects.get(j));
          physicsObjects.get(j).handleCollisions(physicsObjects.get(i));
        }
      }
    }
    // Update the position of every object after collisions.
    for (PhysicsObject o : physicsObjects) {
      o.updatePosition();
    }
  }

  /**
   * Computes the "bounce" vector.
   *
   * Args:
   * @param velocity The current velocity of the object to bounce.
   * @param axis     The axis (X or Y) on which to bounce.
   * @return         The velocity of the object after a bounce.
   */
  public static Vector2d bounce(Vector2d velocity, Axis axis) {
    double[] tuple = new double[2];
    velocity.get(tuple);
    tuple[axis.ordinal()] = -tuple[axis.ordinal()];
    return new Vector2d(tuple);
  }

}

