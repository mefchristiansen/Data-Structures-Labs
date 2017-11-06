import java.io.*;
import java.util.*;
import java.lang.*;

public class Sorter {
	private int[] intArray;

	public static final int NUM_MIN                = -100;
	public static final int NUM_MAX                = 100;
	public static final int NUM_RANDOM_NUMBERS     = 10;
	public static final int NUM_USER_INPUT_NUMBERS = 10;

	public Sorter(int numInts) {
		intArray = new int[numInts];
	}

	public static void main(String []args) {

		Scanner scan = new Scanner(System.in);

		int userInputChoice = getUserInputChoice(scan);

		Sorter sorter;

		if (userInputChoice == 1) {
			sorter = new Sorter(NUM_RANDOM_NUMBERS);
			sorter.initRandomArray();
		} else {
			sorter = new Sorter(NUM_USER_INPUT_NUMBERS);
			sorter.initUserArray(scan);
		}

		System.out.println();
		System.out.print("Unsorted Array: ");
		sorter.printArray();

		int userSortChoice = getUserSortChoice(scan);

		switch (userSortChoice) {
			case 1:
				sorter.bubbleSort();
				break;
			case 2:
				sorter.selectionSort();
				break;
			case 3:
				sorter.insertionSort();
				break;
			case 4:
				sorter.mergeSort();
				break;
			case 5:
				sorter.quickSort();
				break;
			default:
				System.out.println("Invalid option");
				System.exit(1);
		}

		System.out.println();
		System.out.print("Sorted Array: ");
		sorter.printArray();

		scan.close();
	}

	public static final int getUserInputChoice(Scanner scan) {
		int userChoice;

		System.out.println("How would you like to input your numbers?:");
		System.out.println("\t 1: Random");
		System.out.println("\t 2: Enter myself");

		do {
			System.out.print("Choice: ");
			try {
				userChoice = scan.nextInt();
				if (userChoice == 1 || userChoice == 2) {
					break;
				} else {
					System.out.println("Please enter a valid option (1 or 2)");
				}
			} catch (Exception e) {
				System.out.println("Could not parse input. Please try again.");
				scan.next();
			}
		} while(true);


		return userChoice;
	}

	public static final int getUserSortChoice(Scanner scan) {
		int userChoice = 1;

		System.out.println("How would you like to sort your numbers?:");
		System.out.println("\t1: Bubble Sort");
		System.out.println("\t2: Selection Sort");
		System.out.println("\t3: Insertion Sort");
		System.out.println("\t4: Merge Sort");
		System.out.println("\t5: Quick Sort");

		do {
			System.out.print("Choice: ");
			try {
				userChoice = scan.nextInt();
				if (userChoice >= 1 && userChoice <= 5) {
					break;
				} else {
					System.out.println("Please enter a valid option (1 through 5)");
				}
			} catch (Exception e) {
				System.out.println("Could not parse input. Please try again.");
				scan.next();
			}
		} while(true);

		return userChoice;
	}

	public final void printArray() {
		for (int i = 0; i < intArray.length; i++) {
				System.out.print(intArray[i]);
			if (i != intArray.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
		System.out.println();
	}

	public final void initRandomArray() {
		Random rand = new Random();

		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = rand.nextInt((NUM_MAX - NUM_MIN) + 1) + NUM_MIN;
		}
	}

	public final void initUserArray(Scanner scan) {
		System.out.println("Enter " + NUM_USER_INPUT_NUMBERS + " comma separated numbers: ");
		do {
			try {
				String input = scan.next();
				String[] numbers = input.split("\\s*,\\s*");

				if (numbers.length == NUM_USER_INPUT_NUMBERS) {
					for (int i = 0; i < numbers.length; i++) {
						intArray[i] = Integer.parseInt(numbers[i]);
					}
					break;
				} else {
					System.out.println("Please enter " + NUM_USER_INPUT_NUMBERS + " numbers.");
				}

			} catch(Exception e) {
				System.out.println("Could not parse input. Please try again.");
				scan.next();
			}
		} while (true);
	}

	public void bubbleSort() {
		int aux;
		int numSwaps = 0;

		for (int i = 0; i < intArray.length; i++) {
			for (int j = 0; j < intArray.length - i - 1; j++) {
				if (intArray[j] > intArray[j+1]) {
					numSwaps++;
					aux = intArray[j+1];
					intArray[j+1] = intArray[j];
					intArray[j] = aux;
				}
			}

			if (numSwaps == 0) {
				// Array is now sorted
				return;
			}

			numSwaps = 0;

		}
	}

	public void selectionSort() {
		int aux;
		int minIndex;

		for (int i = 0; i < intArray.length; i++) {
			minIndex = minIndex(i);
			aux = intArray[i];
			intArray[i] = intArray[minIndex];
			intArray[minIndex] = aux;
		}
	}

	public int minIndex(int currMin) {
		int minIndex = currMin;
		for (int i = currMin + 1; i < intArray.length; i++) {
			if (intArray[i] < intArray[minIndex]) {
				minIndex = i;
			}
		}

		return minIndex;
	}

	public void insertionSort() {
		int aux;

		for (int i = 1; i < intArray.length; i++) {
			aux = intArray[i];
			int j = i - 1;
			while (j >= 0 && aux < intArray[j]) {
				intArray[j+1] = intArray[j];
				j--;
			}
			intArray[j + 1] = aux;
		}
	}

	public void mergeSort() {

	}

	public void quickSort() {

	}

}