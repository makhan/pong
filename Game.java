package pong;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Game {
  public static void main(String args[]) {
    JFrame frame = new JFrame("Pong");
    frame.setSize(640, 480);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);

    Renderer renderer = new Renderer(640, 480);
    JPanel screen = new GameScreen(renderer);
    frame.add(screen);
  }
}

