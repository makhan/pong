package pong;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;


public class GameScreen extends JPanel {

  private Renderer renderer;
  public GameScreen(Renderer renderer) {
    super();
    this.renderer = renderer;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    renderer.draw(g);
    Toolkit.getDefaultToolkit().sync();
  }
}
