import java.io.*;
import java.util.*;
import java.lang.*;

public class Sorter {
	private int[] intArray;

	public static final int NUM_MIN                = -100;
	public static final int NUM_MAX                = 100;
	public static final int NUM_RANDOM_NUMBERS     = 10;
	public static final int NUM_USER_INPUT_NUMBERS = 10;

	public Sorter() {
		
	}

	public static void main(String []args) {

		Scanner scan = new Scanner(System.in);

		int userInputChoice = getUserInputChoice(scan);

		Sorter sorter;

		if (userInputChoice == 1) {
			sorter = new Sorter();
			sorter.initRandomArray(scan);
		} else {
			sorter = new Sorter();
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
				sorter.mergeSortRecursiveLauncher();
				break;
			case 5:
				sorter.mergeSortIterative();
				break;
			case 6:
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
		System.out.println("\t4: Merge Sort Recursive");
		System.out.println("\t5: Merge Sort Iterative");
		System.out.println("\t6: Quick Sort");

		do {
			System.out.print("Choice: ");
			try {
				userChoice = scan.nextInt();
				if (userChoice >= 1 && userChoice <= 6) {
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

	public final void initRandomArray(Scanner scan) {
		int userChoice;

		System.out.println("Enter how many numbers you want to sort: ");
		do {
			try {
				userChoice = scan.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Could not parse input. Please try again.");
				scan.next();
			}
		} while(true);

		intArray = new int[userChoice];

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

	public void mergeSortRecursiveLauncher() {
		if (intArray == null) {
			return;
		}

		mergeSortRecursive(0, intArray.length - 1);
	}

	public void mergeSortRecursive(int startIndex, int endIndex) {
		if (startIndex >= endIndex) {
			return;
		}

		int midIndex = (startIndex + endIndex) / 2;

		mergeSortRecursive(startIndex, midIndex); //Left side of array
		mergeSortRecursive(midIndex + 1, endIndex); //Right side of array

		merge(startIndex, midIndex, endIndex);
	}

	// The above function is recursive, so uses function call stack to store intermediate values of l and h.
	// The function call stack stores other bookkeeping information together with parameters.
	// Also, function calls involve overheads like storing activation record of the caller function and then resuming execution.
	// Unlike Iterative QuickSort, the iterative MergeSort doesn’t require explicit auxiliary stack.

	public void mergeSortIterative() {
		int midIndex, endIndex;

		int arrayLength = intArray.length;

		for (int currSize = 1; currSize < arrayLength; currSize *= 2) {
			for (int startIndex = 0; startIndex < arrayLength - currSize; startIndex += currSize * 2) {
				midIndex = startIndex + currSize - 1;
				endIndex = Math.min(startIndex + (currSize * 2) - 1, arrayLength - 1);
				merge(startIndex, midIndex, endIndex);
			}
		}
	}

	public void merge(int startIndex, int midIndex, int endIndex) {
		int leftLength, rightLength;
		leftLength = midIndex - startIndex + 1;
		rightLength = endIndex - midIndex;

		int[] leftArray = new int[leftLength];
		int[] rightArray = new int[rightLength];

		for (int i = 0; i < leftLength; i++) {
			leftArray[i] = intArray[i + startIndex];
		}
		for (int i = 0; i < rightLength; i++) {
			rightArray[i] = intArray[i + midIndex + 1];
		}

		int left, right;
		left = right = 0;

		while (left < leftLength && right < rightLength) {
			if (leftArray[left] < rightArray[right]) {
				intArray[startIndex] = leftArray[left];
				left++;
			} else {
				intArray[startIndex] = rightArray[right];
				right++;
			}
			startIndex++;
		}

		while (left < leftLength) {
			intArray[startIndex] = leftArray[left];
			left++;
			startIndex++;
		}

		while (right < rightLength) {
			intArray[startIndex] = rightArray[right];
			right++;
			startIndex++;
		}
	}

	public void quickSort() {

	}

}