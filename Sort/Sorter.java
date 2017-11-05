import java.io.*;
import java.util.*;
import java.lang.*;

public class Sorter {
	int[] randomIntArray;

	public Sorter() {

	}

	public static void main(String []args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("How would you like to input your numbers?:");
		System.out.println("\t 1: Random");
		System.out.println("\t 2: Enter myself");

		int userChoice;

		do {
			System.out.print("Choice: ");
			try {
				int n = scan.nextInt();
				if (n == 1 || n == 2) {
					break;
				} else {
					System.out.println("Please enter a valid option (1 or 2)");
				}
			} catch (Exception e) {
				System.out.println("Could not parse input. Please try again.");
				scan.next();
			}
		} while(true);

		scan.close();
		
	}

}