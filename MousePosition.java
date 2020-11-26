package pong;

import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.SwingUtilities;

public class MousePosition {
  private static MousePosition instance;
  private Component gameArea;

  private MousePosition(Component gameArea) {
    this.gameArea = gameArea;
  }

  public static MousePosition createMousePosition(Component gameArea) {
    if (instance == null) {
      instance = new MousePosition(gameArea);
    }
    return instance;
  }

  public Point getPosition() {
    Point p = new Point(MouseInfo.getPointerInfo().getLocation());
    SwingUtilities.convertPointFromScreen(p, gameArea);
    return p;
  }
}
