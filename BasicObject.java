package pong;

import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;
import java.util.List;
import java.util.ArrayList;
import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

public class BasicObject implements PhysicsObject {
  protected Point2d position;
  protected Vector2d velocity;
  protected RectangularShape collisionShape;
  protected List<CollsionHandler> collisionHandlers;

  BasicObject() {
    collisionHandlers = new ArrayList<CollsionHandler>();
  }


  @Override
  public void setPosition(Point2d position) {
    this.position = position;
  }

  @Override
  public Point2d getPosition() {
    return position;
  }

  @Override
  public void setVelocity(Vector2d velocity) {
    this.velocity = velocity;
  }

  @Override
  public Vector2d getVelocity() {
    return velocity;
  }

  @Override
  public RectangularShape getCollisionShape() {
    return collisionShape;
  }

  @Override
  public void setCollisionShape(RectangularShape shape) {
    this.collisionShape = shape;
  }

  @Override
  public boolean collides(PhysicsObject obj) {
    return collisionShape.intersects(obj.getCollisionShape().getFrame());
  }

  @Override
  public void updatePosition() {
    this.position.add(this.velocity);
    System.out.println(this.position);
  }

  @Override
  public void addCollisionHandler(CollsionHandler c) {
    collisionHandlers.add(c); 
  }

  @Override
  public void handleCollisions(PhysicsObject other) {
    try {
      for (CollsionHandler handler : collisionHandlers) {
        handler.handle(other);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
