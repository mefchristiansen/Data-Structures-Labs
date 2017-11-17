public class Circle {
	// These variables are protected so that the subclasses of the class (i.e the sphere class) can access these variables
	protected double radius;
	protected double centerX, centerY;

	public Circle() {
		radius = 0.0;
	}

	public Circle(double radius, double centerX, double centerY) {
		this.radius = radius;
		this.centerX = centerX;
		this.centerY = centerY;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		if (radius >= 0.0) {
			this.radius = radius;
		} else {
			System.out.println("The radius of a circle cannot be negative");
		}
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public Point2D getCenter() {
		return new Point2D(centerX, centerY);
	}

	public void setCenter(Point2D centerPoint) {
		this.centerX = centerPoint.getX();
		this.centerY = centerPoint.getY();
	}

	public double getArea() {
		return Math.PI * radius * radius;
	}

	public String toString() {
		return "Circle[radius = " + radius + 
				", centerX = " + centerX + 
				", centerY = " + centerY + "]";
	}
}