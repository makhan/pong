package pong;

import java.awt.geom.RectangularShape;
import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

public interface PhysicsObject {
  public void setPosition(Point2d position);
  public Point2d getPosition();
  public boolean collides(PhysicsObject obj);
  public void setVelocity(Vector2d velocity);
  public Vector2d getVelocity();
  public RectangularShape getCollisionShape();
  public void setCollisionShape(RectangularShape shape);
  public void updatePosition();
  public void addCollisionHandler(CollsionHandler c);
  public void handleCollisions(PhysicsObject other);
}
