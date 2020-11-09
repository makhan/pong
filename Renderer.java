package pong;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Renderer {

  private List<RenderableObject> objects;
  private int width, height;

  public Renderer() {
    this(640, 480);
  }

  public Renderer(int width, int height) {
    this.width = width;
    this.height = height;
    objects = new ArrayList<RenderableObject>();
  }

  public void add(RenderableObject object) {
    objects.add(object);
  }

  public void draw(Graphics g) {
    // TODO(MAK): Draw background.
    // TODO(MAK): Look into double buffering.
    // Draw each object.
    objects.forEach(object -> object.draw(g));
  }
}
