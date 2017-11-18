import java.util.Scanner;


public class LinkedListsTest {

	public static final int SENTINEL = -1;

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		// Creating linked list as long as the user enters numbers
		System.out.println("Enter some integers and strings (-1 to stop)\n");

		// head of the list
		LinkedList list = new LinkedList();

		// get the first number
		int nextNum;
		System.out.print("Enter a number: ");
		nextNum = scan.nextInt();

		String someString = "";
		if (nextNum != SENTINEL) {
			System.out.print("Enter a string: ");
			someString = scan.next();
		}

		// SENTINEL allows the user to signal when they are done
		// entering numbers
		while (nextNum != SENTINEL) {

			Data data = new Data(nextNum, someString);
			Node node = new Node(data);
			list.insertNode(node);
			list.print();

			// get another number
			System.out.print("Enter a number: ");
			nextNum = scan.nextInt();

			if (nextNum != SENTINEL) {
				System.out.print("Enter a string: ");
				someString = scan.next();
			}

		}
	}

}
