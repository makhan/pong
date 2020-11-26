package pong;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.vecmath.Point2d;


public class TextObject implements RenderableObject {

  private String text = "";
  private Point2d location;
  

  public TextObject(Point2d location) {
    this.location = location;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  @Override
  public void draw(Graphics g) {
    FontMetrics fm = g.getFontMetrics(g.getFont());
    int yOffset = fm.getAscent();
    Graphics2D g2 = (Graphics2D)g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    double[] p = new double[2];
    location.get(p);
    g2.drawString(text, (int)p[0], (int)p[1] - yOffset);
  }

}
