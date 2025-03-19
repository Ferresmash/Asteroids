package pos;

public class Force {

	private double speed = 1;
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
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
}
