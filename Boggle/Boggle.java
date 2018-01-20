import java.util.*;
import java.io.*;

public class Boggle {
	public static final int DIMENSION = 4;
	public static final int MIN_LENGTH = 3;

	private Dictionary dictionary;

	List<List<Character>> board;

	List<String> computerWords;
	List<String> humanWords;

	public Boggle() {
		// Instantiate dictionary class
		dictionary = new Dictionary();

		if (dictionary == null) {
			return;
		}

		// Read + Set Board
		generatePuzzle();

		if (!isGoodPuzzle()) {
			return;
		} else {
			System.out.println("Puzzle successfully read.");
		}

		computerWords = new ArrayList<String>();
		humanWords = new ArrayList<String>();

		printPuzzle();
	}

	private void generatePuzzle() {
		String boardFileName;
		Scanner boardScanner;

		do {
			boardFileName = getBoardFileName();
			boardScanner = getBoardFile(boardFileName);
			if (boardScanner != null) {
				break;
			}
		} while(true);

		this.board = readPuzzle(boardScanner);
		boardScanner.close();

		if (board == null) {
			System.out.println("There was an error reading that puzzle.");
		}
	}

	private String getBoardFileName() {
		Scanner scan = new Scanner(System.in);
		String fileName;

		do {
			System.out.print("Please enter the name of the puzzle file without the .txt extension: "); 
			try {
				fileName = scan.next();
				break;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("There was an error parsing your input. Please try again.");
			}
		} while(true);

		return fileName;
	}

	private Scanner getBoardFile(String fileName) {
		String filePath;
		Scanner scan;

		filePath = "files/puzzles/" + fileName + ".txt";

		try {
			scan = new Scanner(new File(filePath));
		} catch (Exception e) {
			System.out.println("Could not find or open that puzzle. Please try again.");
			return null;
		}

		return scan;
	}

	private List<List<Character>> readPuzzle(Scanner fileScan) {
		// File is empty
		if (!fileScan.hasNextLine()) {
			System.out.println("[Error] Board file is empty.");
			return null;
		}

		List<List<Character>> gameBoard = new ArrayList<List<Character>>();

		String line = fileScan.nextLine();
		List<Character> temp = new ArrayList<Character>();

		try {
			for (int i = 0; i < line.length(); i++) {
				temp.add(Character.toLowerCase(line.charAt(i)));
			}
		} catch (Exception e) {
			System.out.println("There was an error reading the puzzle.");
			return null;
		}

		gameBoard.add(temp);

		if(temp.size() != DIMENSION) {
			System.out.println("[Error] Puzzle is not a 4x4 board.");
			return null;
		}

		int numRows = 1;
 
		try {
			while (fileScan.hasNextLine()) {
				numRows++;

				if (numRows > DIMENSION) {
					System.out.println("[Error] Puzzle is not a 4x4 board.");
					return null;
				}

				line = fileScan.nextLine();
				List<Character> nextLine = readNextLine(line);
				
				if (nextLine == null) {
					return null;
				}

				gameBoard.add(nextLine);
			}
		} catch (Exception e) {
			System.out.println("There was an error reading the puzzle.");
			return null;
		}

		if (numRows != DIMENSION) {
			System.out.println("[Error] Puzzle is not a 4x4 board.");
			return null;
		}

		return gameBoard;
	}

	private List<Character> readNextLine(String line) {
		int rowIndex = 0;
		List<Character> row = new ArrayList<Character>();
		try {
			for (int i = 0; i < line.length(); i++) {
				row.add(Character.toLowerCase(line.charAt(i)));
			}
		} catch (Exception e) {
			System.out.println("There was an error reading the puzzle.");
			return null;
		}

		if (row.size() != DIMENSION) {
			System.out.println("[Error] Puzzle is not a 4x4 board.");
			return null;
		}

		return row;
	}

	public void printPuzzle() {
		int rowNum = 0;

		System.out.println();

		System.out.println("Board:");
		for (List<Character> row : board) {
			for (Character letter : row) {
				System.out.print(Character.toUpperCase(letter) + "   ");
			}
			System.out.println();
			rowNum++;
		}

		System.out.println();
	}

	public boolean isGoodPuzzle() {
		return board != null;
	}

	public void play() {
		computerPlay();

		if (getPlayerChoice()) {
			humanPlay();
			System.out.println();
			printHuman();
		}

		System.out.println();
		printComputer();
	}

	private void computerPlay() {
		String build = "";

		for (int r = 0; r < DIMENSION; r++) {
			for (int c = 0; c < DIMENSION; c++) {
				computerFindWords(r, c, build);
			}
		}

		Collections.sort(computerWords);
	}

	private void computerFindWords(int row, int col, String build) {
		if (!validPosition(row, col)) {
			return;
		}

		Character aux = board.get(row).get(col);
		board.get(row).set(col, ' ');

		build += aux;

		if (aux == 'q') {
			build += "u";
		}

		if (build.length() >= MIN_LENGTH && !computerWords.contains(build) && dictionary.binarySearch(build) ) {
			computerWords.add(build);
		} else if (!dictionary.isPrefix(build)) {
			board.get(row).set(col, aux);
			return;
		}

		for (int rowDir = -1; rowDir <= 1; rowDir++) {
			for (int colDir = -1; colDir <= 1; colDir++) {
				if (rowDir != 0 || colDir != 0) {
					computerFindWords(row + rowDir, col + colDir, build);
				}
			}
		}

		board.get(row).set(col, aux);
	}

	private boolean validPosition(int row, int col) {
		return row >= 0 && col >= 0 && row < DIMENSION && col < DIMENSION && board.get(row).get(col) != ' ';
	}

	private boolean getPlayerChoice() {
		Scanner scan = new Scanner(System.in);
		String choice;

		do {
			System.out.print("Would you like to play against the computer (Y/N)?: "); 
			try {
				choice = scan.next();

				if (choice.equals("Y") || choice.equals("N")) {
					break;
				} else {
					System.out.println("Please enter a valid option (Y or N)");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("There was an error parsing your input. Please try again.");
			}
		} while(true);

		System.out.println();

		return choice.equals("Y");
	}

	private void humanPlay() {
		Scanner scan = new Scanner(System.in);
		String input;

		System.out.println("Enter as many words as you want, one to a line.");
		System.out.println("Enter an exclamation point when you are done.");
		System.out.println("");

		do {
			System.out.print("Enter Word: "); 
			try {
				input = scan.next();

				if (input.equals("!")) {
					break;
				}

				checkWord(input);

				System.out.println();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("There was an error parsing your input. Please try again.");
			}
		} while(true);

		Collections.sort(humanWords);
	}

	private void checkWord(String word) {
		if (word.length() < MIN_LENGTH) {
			System.out.println("Invalid word: words must have at least 3 characters.");
		} else if (humanWords.contains(word)) {
			System.out.println("Invalid word: already entered.");
		} else if (!dictionary.binarySearch(word)) {
			System.out.println("Invalid word: not in dictionary.");
		} else if (!computerWords.contains(word)) {
			System.out.println("Invalid word: not in board.");
		} else {
			System.out.println("Valid word.");
			humanWords.add(word);
		}
	}

	private void printComputer() {
		System.out.println("Computer Words:");
		System.out.println("===============");
		printComputerWords();
		System.out.println();
		calculateComputerScore();
	}

	private void printComputerWords() {
		for (String word : computerWords) {
			if (humanWords.contains(word)) {
				System.out.println(word + "\t disallowed: found by human.");
			} else {
				System.out.println(word);
			}
		}
	}

	private void calculateComputerScore() {
		int score = 0;

		for (String word : computerWords) {
			if (humanWords.contains(word)) {
				continue;
			}

			int wordLength = word.length();

			if (wordLength < 3) {
				// Invalid word length
			} else if (wordLength <= 4) {
				score += 1;
			} else if (wordLength == 5) {
				score += 2;
			} else if (wordLength == 6) {
				score += 3;
			} else if (wordLength == 7) {
				score += 5;
			} else {
				score += 11;
			}
		}

		System.out.println("Total Score: " + score);
	}

	private void printHuman() {
		System.out.println("Human Words:");
		System.out.println("===============");

		for (String word : humanWords) {
			System.out.println(word);
		}

		calculateHumanScore();
	}

	private void calculateHumanScore() {
		int score = 0;

		for (String word : humanWords) {
			int wordLength = word.length();

			if (wordLength < 3) {
				// Invalid word length
			} else if (wordLength <= 4) {
				score += 1;
			} else if (wordLength == 5) {
				score += 2;
			} else if (wordLength == 6) {
				score += 3;
			} else if (wordLength == 7) {
				score += 5;
			} else {
				score += 11;
			}
		}

		System.out.println("Total Score: " + score);
	}
	
}