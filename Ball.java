package pong;

import java.awt.geom.Ellipse2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

public class Ball extends AbstractBasicObject implements RenderableObject {
  private double radius;
  
  public Ball(double radius, Point2d position) {
    this.radius = radius;
    setPosition(position);
    setVelocity(new Vector2d(0,0));
    updateCollisionShape();
  }

  // TODO(MAK): Have a Builder or Factory for this.
  public Ball() {
    this(10.0, new Point2d(0,0));
  }

  @Override
  public void updatePosition() {
    super.updatePosition();
    updateCollisionShape();
  }


  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D)g;
    g2.draw(getCollisionShape());
  }

  private void updateCollisionShape() {
    double[] p = new double[2];
    position.get(p);
    Point2d upperLeft = new Point2d(p[0] - radius, p[1] - radius);
    setCollisionShape(new Ellipse2D.Double(p[0] - radius, p[1] - radius, 2*radius, 2*radius));
  }
}
