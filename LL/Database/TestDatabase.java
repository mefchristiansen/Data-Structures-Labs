import java.util.Random;
import java.util.Scanner;

public class TestDatabase {

    // Range of valid ages.
	private static final int MIN_AGE = 1;
	private static final int MAX_AGE = 120;

    // Array of first names for generating random first names.
	private static String[] firstNames = 		
		{ "Abir", "Angela", "Kanye", "Sonya", "DeRay", "Robert", "Svetlana", "Karl",
				"Sujit", "Niejls", "Alejandro", "Itzhak", "Emily", "Terik", "Nevin", "Linus", "Ebba", 
				"Gwyneth", "Clarissa", "Abbud", "Eric", "Alexei", "Michele", "Artemisia", "Ansari" };
    
    // Array of last names for generating random last names.
	private static String[] lastNames =
		{ "Borisov", "Johnson", "Ben-Haim", "Kourakis", "Elfasi", "Newton", "Shammas",
				"Kuznetsov", "Marinescu", "Voltaire", "Mckesson", "Ross", "Yang", "Awad", "Lopez",
				"Kraft", "Roitman", "Christakos", "Karlsson", "Freidhof", "Schumacher", "Faure" };

	private static Random rand = new Random();
    // To get input and generate random values.
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		int numEntries = getUserNumEntries();
		Database testDatabase = createDatabase(numEntries);

		System.out.println("----------- Printing Database -----------");
		testDatabase.print();
		System.out.println();

		System.out.println("----------- Searching Entries for Last Name -----------");
		String userLastName = getUserLastName();
		testDatabase.findLastName(userLastName);
		System.out.println();

		System.out.println("----------- Searching Entries in Age Range -----------");
		int lowAgeRange = getUserLowAgeRange();
		int highAgeRange = getUserHighAgeRange();
		testDatabase.searchAgeRange(lowAgeRange, highAgeRange);
		System.out.println();
		
		System.out.println("----------- Searching for Student Entries -----------");
        testDatabase.printStudents();
        System.out.println();
	}

	public static Database createDatabase(int numEntries) {
		Database newDatabase = new Database();
		for (int i = 0; i < numEntries; i++) {
			Node newEntry = initRandomNode();
			newDatabase.insertNode(newEntry);
		}

		return newDatabase;
	}

	public static Node initRandomNode() {
		String dataFirstName = firstNames[rand.nextInt(firstNames.length)];
		String dataLastName = lastNames[rand.nextInt(lastNames.length)];
		int dataAge = rand.nextInt((MAX_AGE - MIN_AGE) + 1) + MIN_AGE;
		boolean dataIsStudent = rand.nextBoolean();
		Data newData = new Data(dataFirstName, dataLastName, dataAge, dataIsStudent);
		Node newNode = new Node(newData);

		return newNode;
	}

	public static int getUserNumEntries() {
		int numEntries;

		do {
			System.out.print("Enter your desired number of entries: ");
			try {
				numEntries = scan.nextInt();
				if (numEntries > 0) {
					break;
				} else {
					System.out.println("Please enter a positive integer.");
				}
			} catch (Exception e) {
				System.out.println("Could not parse input. Please try again.");
				scan.next();
			}
		} while(true);

		return numEntries;
	}

	public static String getUserLastName() {
		String userLastName;

		do {
			System.out.print("Name to search for: ");
			try {
				userLastName = scan.next();
				break;
			} catch (Exception e) {
				System.out.println("Could not parse input. Please try again.");
				scan.next();
			}
		} while(true);

		return userLastName;
	}

	public static int getUserLowAgeRange() {
		int lowAge;

		do {
			System.out.print("Enter an starting age: ");
			try {
				lowAge = scan.nextInt();
				if (lowAge >= MIN_AGE) {
					break;
				} else {
					System.out.println("The age must be at least " + MIN_AGE);
				}
			} catch (Exception e) {
				System.out.println("Could not parse input. Please try again.");
				scan.next();
			}
		} while(true);

		return lowAge;
	}

	public static int getUserHighAgeRange() {
		int highAge;

		do {
			System.out.print("Enter a ending age: ");
			try {
				highAge = scan.nextInt();
				if (highAge <= MAX_AGE) {
					break;
				} else {
					System.out.println("The age must be no greater than " + MAX_AGE);
				}
			} catch (Exception e) {
				System.out.println("Could not parse input. Please try again.");
				scan.next();
			}
		} while(true);

		return highAge;	
	}
}
