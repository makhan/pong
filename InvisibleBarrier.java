package pong;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

class InvisibleBarrier extends AbstractBasicObject {
  
  public InvisibleBarrier(Point2d position, int width, int height) {
    super();
    setPosition(position);
    setVelocity(new Vector2d(0, 0));
    setCollisionShape(new Rectangle2D.Double(position.getX(), position.getY(), width, height));
  }

  @Override
  public void updatePosition() {
    // Do nothing.
  }


}
