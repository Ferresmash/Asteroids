package legacy;

import java.awt.Graphics;

public class Rectangle implements Shape {
  private Point center;
  private double w, h;
  
  public Rectangle(double x, double y, double w, double h)
    {
    center = new Point(x,y);
    this.w = w;
    this.h = h;
    }

  public Rectangle(Point point, double w, double h)
    {
    this(point.getX(), point.getY(), w, h);
    }

  @Override
  public void draw(Graphics g)
    {
    g.drawRect((int)(center.getX()-w/2), (int)(center.getY()-h/2), (int)w, (int)h);
    }
  
  @Override
  public void fill(Graphics g)
    {
    g.fillRect((int)(center.getX()-w/2), (int)(center.getY()-h/2), (int)w, (int)h);
    }

  @Override
  public Point getPosition()
    {
    return center;
    }
  
  @Override
  public double getWidth()
    {
    return w;
    }

  @Override
  public double getHeight()
    {
    return h;
    }

  @Override
  public boolean intersects(Point point)
    {
	  return point.getX() >= center.getX()-w/2 && point.getX() <= center.getX()-w/2 + w &&
			  point.getY() >= center.getY()-h/2 && point.getY() <= center.getY()-h/2 + h;
    }

  @Override
  public void moveTo(Point point)
    {
    center.moveTo(point);
    }

  @Override
  public void move(double dx, double dy)
    {
    center.move(dx, dy);
    }
  
  @Override
  public void resizeTo(Point point)
    {
	  w = Math.abs((center.getX() - point.getX())*2);
	  h = Math.abs((center.getY() - point.getY())*2);
    }

  @Override
  public Shape peel()
    {
    return this;
    }

  }
