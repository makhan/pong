package pong;

import java.awt.geom.Rectangle2D;
import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

public class Paddle extends AbstractBasicObject {
  private double length;
  private double height;

  public Paddle(double length, double height, Point2d position) {
    this.length = length;
    this.height = height;
    setPosition(position);
    setVelocity(new Vector2d());
    double p[] = new double[2];
    position.get(p);
    Point2d upperLeft = new Point2d(p[0], p[1]);
    setCollisionShape(new Rectangle2D.Double(p[0], p[1], /* width = */ length, /* height = */ height));
  }

  public Paddle() {
    this(20, 5, new Point2d(100, 100));
  }

}
