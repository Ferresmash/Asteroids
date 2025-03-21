package pos;

public class Force {

	private double[] direction = {0d,0d};
	
	public Force(double x, double y) {
		setX(x);
		setY(y);
	}
	
	
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
	public double getAngle() {
		return Math.atan2(getY(), getX());
	}
	public double getSpeed() {
		return Math.sqrt(Math.pow(getX(), 2)+ Math.pow(getY(), 2));
	}
	
    public void setAngle(double angle) {
        double speed = getSpeed();
        setX(speed * Math.cos(angle));
        setY(speed * Math.sin(angle));
    }
    
    public void setSpeed(double speed) {
        double angle = getAngle();
        setX(speed * Math.cos(angle));
        setY(speed * Math.sin(angle));
    }
    
    public void rotate(double angle) {
    	
    }
	
}
