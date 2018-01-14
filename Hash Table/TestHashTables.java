/*  
 * 
 * YOUR PROGRAM SUMMARY GOES HERE
 * 
 */


public class TestHashTables {

	// Constants for Part 1
	private static final int PART_1_MAX_LOAD_FACTOR = 40;
	private static final int PART_1_LOAD_FACTOR_DECREMENT = 4;

	// Constants for Part 2
	private static final int PART_2_HASH_TABLE_SIZE = 100;
	private static final int PART_2_NUM_BOARDS = 4;


	public static void main(String[] args) {

		int numBoards = 100000;
		int numSearches = 10000000;
		System.out.println(numBoards + " GameBoards inserted; " 
				+ numSearches + " searches\n");

		// OUTPUT: PART 1
		// --------------
		//
		// *** Create required table of statistics with varying load factors.

		for (int loadFactor = PART_1_MAX_LOAD_FACTOR; loadFactor > 0; loadFactor -= PART_1_LOAD_FACTOR_DECREMENT) {
			int numBuckets = numBoards / loadFactor;
			HashTable hashTable = new HashTable(numBuckets);
			initTable(hashTable, numBoards);
			double variance = hashTable.calcVariance();

			long startTime = System.nanoTime();

			for (int i = 0; i < numSearches; i++) {
				Entry entry = hashTable.find(new GameBoard());
			}

			long endTime = System.nanoTime();
			double timeInSeconds = (endTime - startTime) / 1e9;

			// Print out the required information.
			System.out.printf("%6d buckets     LF = %3d     var = %6.3f     time = %6.3f secs\n",
					numBuckets, loadFactor, variance, timeInSeconds);
		}

		// OUTPUT: PART 2
		// --------------

		// *** Make a hash table called "table" of size PART_2_HASH_TABLE_SIZE.
		HashTable hashTable = new HashTable(PART_2_HASH_TABLE_SIZE);
		
		// Make an array of PART_2_NUM_BOARDS GameBoards, all of which are different.
		GameBoard[] boards = new GameBoard[PART_2_NUM_BOARDS];
		for (int i = 0; i < PART_2_NUM_BOARDS; ++i) {
			boards[i] = new GameBoard();
		}
		while (!allDiff(boards)) {
			for (int i = 0; i < PART_2_NUM_BOARDS; ++i) {
				boards[i] = new GameBoard();
			}
		}

		// *** Insert the first two boards into the hash table.
		hashTable.insert(boards[0], boards[0].boardValue());
		hashTable.insert(boards[1], boards[1].boardValue());

		System.out.println("———————————————————------------------------————————\n");
		System.out.println("ADDED BOARDS 1 AND 2 TO THE HASH TABLE\n");
		System.out.println("———————————————————------------------------————————\n");
		System.out.println("SEARCHES AFTER INSERTIONS BUT BEFORE REMOVALS\n");
		System.out.println("———————————————————------------------------————————");

		System.out.println("Searching for Board 1 (is in the hash table).  Board 1:\n");
		boards[0].print();
		Entry e = hashTable.find(boards[0]);
		System.out.println("Here’s what was returned from the find:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}
		System.out.println("———————————————————------------------------————————\n");

		System.out.println("Searching for Board 2 (is in the hash table).  Board 2:\n");
		boards[1].print();
		e = hashTable.find(boards[1]);
		System.out.println("Here’s what was returned from the find:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}
		System.out.println("———————————————————------------------------————————\n");


		System.out.println("Searching for Board 3 (is NOT in the hash table).  Board 3:\n");
		boards[1].print();
		e = hashTable.find(boards[2]);
		System.out.println("Here’s what was returned from the find:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}
		System.out.println("———————————————————------------------------————————\n");


		System.out.println("Searching for Board 4 (is NOT in the hash table).  Board 4:\n");
		boards[1].print();
		e = hashTable.find(boards[3]);
		System.out.println("Here’s what was returned from the find:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}
		System.out.println("———————————————————------------------------————————\n");

		System.out.println("REMOVALS\n");
		System.out.println("———————————————————------------------------————————");
		System.out.println("Removing Board 1 (which is in the hash table):\n");
		boards[0].print();
		e =  hashTable.remove(boards[0]);
		System.out.println("Here’s what was returned from the remove:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}
		System.out.println("———————————————————------------------------————————\n");

		System.out.println("Removing Board 2 (which is in the hash table):\n");
		boards[1].print();
		e =  hashTable.remove(boards[1]);
		System.out.println("Here’s what was returned from the remove:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}
		System.out.println("———————————————————------------------------————————\n");

		System.out.println("Removing Board 3 (which is NOT in the hash table):\n");
		boards[2].print();
		e =  hashTable.remove(boards[2]);
		System.out.println("Here’s what was returned from the remove:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}
		System.out.println("———————————————————------------------------————————\n");

		System.out.println("Removing Board 4 (which is NOT in the hash table):\n");
		boards[3].print();
		e =  hashTable.remove(boards[3]);
		System.out.println("Here’s what was returned from the remove:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}
		System.out.println("———————————————————------------------------————————\n");


		System.out.println("SEARCHES AFTER REMOVALS\n");
		System.out.println("———————————————————------------------------————————");

		System.out.println("Searching for Board 1 (no longer in the hash table).  Board 1:\n");
		boards[0].print();
		e =  hashTable.find(boards[0]);
		System.out.println("Here’s what was returned from the find:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}
		System.out.println("———————————————————------------------------————————\n");

		System.out.println("Searching for Board 2 (no longer in the hash table).  Board 2:\n");
		boards[1].print();
		e =  hashTable.find(boards[1]);
		System.out.println("Here’s what was returned from the find:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}
		System.out.println("———————————————————------------------------————————\n");

		System.out.println("Call to isEmpty function returns:  " + hashTable.isEmpty() + "\n");

		System.out.println("———————————————————------------------------————————\n");
	}

	// Purpose: Create and insert specified number of GameBoards
	//          into hash table.
	// Parameters: The hash table and the number of GameBoards to
	//             insert.
	// Return Value: None.
	//
	// NOTE: The value associated with each board is computed by 
	//       the boardValue function in the GameBoard class.
	//
	public static void initTable(HashTable table, int numBoards) {
		for (int i = 0; i < numBoards; i++) {
			GameBoard gameBoard = new GameBoard();
			table.insert(gameBoard, gameBoard.boardValue());
		}
	}

	// Purpose: To check if all the GameBoards in a given array of
	//          GameBoards are different.
	// Parameters: The array of GameBoards.
	// Return Value: True, if they are all different; otherwise,
	//               false.
	//
	public static boolean allDiff(GameBoard[] boards) {
		return
				!boards[0].equals(boards[1]) &&
				!boards[0].equals(boards[2]) &&
				!boards[0].equals(boards[3]) &&
				!boards[1].equals(boards[2]) &&
				!boards[1].equals(boards[3]) &&
				!boards[2].equals(boards[3]);
	}
}


