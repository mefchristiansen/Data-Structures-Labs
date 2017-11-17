
/*
	   Author: Stephen Majercik
	   Lab 2: Object Oriented Design
	   Created on: Feb 2, 2017

	  This program tests the Point2D, Point3D, Circle, and Sphere classes.

 */

public class TestLab2 {

	public static void main(String[] args) {

		System.out.println("****************");
		System.out.println("    CIRCLES");
		System.out.println("****************");
		
		// Create Circle using default constructor.
		System.out.println("Default Circle:");
		Circle c1 = new Circle();
		System.out.println(c1.toString());
		System.out.println();

		// Try to set radius to -8.
		System.out.println("Try to set radius to -8:");
		c1.setRadius(-8);
		System.out.println(c1.toString());
		System.out.println();
		
		// Set radius to 15.
		System.out.println("Set radius to 15:");
		c1.setRadius(15);
		System.out.println(c1.toString());
		System.out.println();
		
		// Set center coordinates to (-2, 3).
		System.out.println("Set center coordinates to (-2, 3):");
		c1.setCenterX(-2);
		c1.setCenterY(3);
		System.out.println(c1.toString());
		System.out.println();
		
		// Get the center coordinates as a 2DPoint object.
		System.out.println("Get the center coordinates as a 2DPoint object:");
		Point2D c1Center= c1.getCenter();
		System.out.println(c1Center.toString());
		System.out.println();

		// Change the coordinates of the 2DPoint to (55, 77).
		System.out.println("Change the coordinates of the 2DPoint to (55, 77):");
		c1Center.setX(55);
		c1Center.setY(77);
		System.out.println(c1Center.toString());
		System.out.println();

		// Use the 2DPoint object to reset center coordinates.
		System.out.println("Use the 2DPoint object to reset center coordinates:");
		c1.setCenter(c1Center);
		System.out.println(c1.toString());
		System.out.println();
		
		// Try the other Circle Constructor to construct circle with radius 1 
		// and center (100, 200);
		System.out.println("Try the other Circle Constructor to construct circle " + 
				"with radius 1 and center (100, 200):");
		Circle c2 = new Circle(1, 100, 200);
		System.out.println(c2.toString());
		System.out.println();
		
		// Find the area of the new circle
		System.out.printf("Area of new circle = %7.4f\n", c2.getArea());
		System.out.println();
		
		
		System.out.println("****************");
		System.out.println("    SPHERES");
		System.out.println("****************");

		// Create Sphere using default constructor.
		System.out.println("Default Sphere:");
		Sphere s1 = new Sphere();
		System.out.println(s1.toString());
		System.out.println();

		// Try to set radius to -80.
		System.out.println("Try to set radius to -80:");
		s1.setRadius(-80);
		System.out.println(s1.toString());
		System.out.println();
		
		// Set radius to 15.
		System.out.println("Set radius to 15:");
		s1.setRadius(15);
		System.out.println(s1.toString());
		System.out.println();
		
		// Set center coordinates to (-2, 3, 4).
		System.out.println("Set center coordinates to (-2, 3, 4):");
		s1.setCenterX(-2);
		s1.setCenterY(3);
		s1.setCenterZ(4);
		System.out.println(s1.toString());
		System.out.println();
		
		// Get the center coordinates as a 3DPoint object.
		System.out.println("Get the center coordinates as a 3DPoint object:");
		Point3D s1Center= s1.getCenter();
		System.out.println(s1Center.toString());
		System.out.println();

		// Change the coordinates of the 3DPoint to (55, 77, 99).
		System.out.println("Change the coordinates of the 3DPoint to (55, 77, 99):");
		s1Center.setX(55);
		s1Center.setY(77);
		s1Center.setZ(99);
		System.out.println(s1Center.toString());
		System.out.println();

		// Use the 3DPoint object to reset center coordinates.
		System.out.println("Use the 3DPoint object to reset center coordinates:");
		s1.setCenter(s1Center);
		System.out.println(s1.toString());
		System.out.println();
		
		// Try the other Sphere Constructor to construct circle with radius 1 
		// and center (99, 100, 200);
		System.out.println("Try the other Sphere Constructor to construct sphere " + 
				"with radius 1 and center (99, 100, 200):");
		Sphere s2 = new Sphere(1, 99, 100, 200);
		System.out.println(s2.toString());
		System.out.println();
		
		// Find the area of the new sphere
		System.out.printf("Area of new sphere = %7.4f\n", s2.getArea());
		System.out.println();
		
		// Find the volume of the new sphere
		System.out.printf("Volume of new sphere = %7.4f\n", s2.getVolume());
		System.out.println();
		
	}

}
