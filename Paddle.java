package pong;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

public class Paddle extends AbstractBasicObject implements RenderableObject {
  private double length;
  private double height;
  private MousePosition mouse;

  public Paddle(double length, double height, Point2d position, MousePosition mouse) {
    this.length = length;
    this.height = height;
    this.mouse = mouse;
    setPosition(position);
    setVelocity(new Vector2d(0,0));
    updateCollisionShape();
  }

  @Override
  public void updatePosition() {
    Point mousePosition = mouse.getPosition();
    System.out.println("Position: " + mousePosition);
    double[] p = new double[2];
    position.get(p);
    this.setPosition(new Point2d(mousePosition.getX(), p[1]));
    updateCollisionShape();
  }

  @Override
  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D)g;
    g2.draw(getCollisionShape());
  }

  private void updateCollisionShape() {
    double p[] = new double[2];
    position.get(p);
    Point2d upperLeft = new Point2d(p[0], p[1]);
    setCollisionShape(new Rectangle2D.Double(p[0], p[1], /* width = */ length, /* height = */ height));
  }

}
