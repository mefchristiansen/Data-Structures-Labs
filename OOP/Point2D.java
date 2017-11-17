public class Point2D implements Point2DInterface {

	// Private instance variables
	// Set to private so that they can only be accessed through the class methods
	protected double x;
	protected double y;

	// Default Constructor
	public Point2D() {
		this.x = 0.0;
		this.y = 0.0;
	}

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "Point2D[x = " + x + ", y = " + y + "]";
	}

}