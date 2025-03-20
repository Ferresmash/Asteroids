package pos;

public class Force {

	private double[] direction = {0d,0d};
	
	public double[] getDirection() {
		return direction;
	}
	public void setDirection(double[] direction) {
		this.direction = direction;
	}
	public void add(double[] newDirection) {
		setX(getX() + newDirection[0]);
		setY(getY() + newDirection[1]);
	}
	public double getX() {
		return direction[0];
	}
	public double getY() {
		return direction[1];
	}
	public void setX(double d) {
		direction[0] = d;
	}
	public void setY(double d) {
		direction[1] = d;
	}
}
