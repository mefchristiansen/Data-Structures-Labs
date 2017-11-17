public class Point3D extends Point2D {

	// Private instance variables
	private double z;

	public Point3D() {
		super();
		this.z = 0.0;
	}

	public Point3D(double x, double y, double z) {
		super(x, y);
		this.z = z;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public void setPoint(double x, double y, double z) {
		super.setX(x);
		super.setY(y);
		this.z = z;
	}

	public String toString() {
		return "Point3D[x = " + x + ", y = " + y + ", z = " + z + "]";
	}

}