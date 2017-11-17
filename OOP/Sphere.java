public class Sphere extends Circle {
	private double centerZ;

	public Sphere() {
		super();
		centerZ = 0.0;
	}

	public Sphere(double radius, double centerX, double centerY, double centerZ) {
		super(radius, centerX, centerY);
		this.centerZ = centerZ;
	}

	public double getCenterZ() {
		return centerZ;
	}

	public void setCenterZ(double centerZ) {
		this.centerZ = centerZ;
	}

	public void setCenter(Point3D centerPoint) {
		super.setCenter(new Point2D(centerPoint.getX(), centerPoint.getY()));
		centerZ = centerPoint.getZ();
	}

	public Point3D getCenter() {
		return new Point3D(centerX, centerY, centerZ);
	}

	public double getArea() {
		return 4.0 * Math.PI * radius * radius;
	}
	
	public double getVolume() {
		return 4.0 / 3.0 * Math.PI * radius * radius * radius;
	}
	
	public String toString() {
		return "Sphere[" + super.toString() + ", centerZ = " + centerZ + "]";
	}
}