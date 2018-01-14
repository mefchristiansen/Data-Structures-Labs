import java.util.*;
import java.io.*;

public class WordSearch {
	public static enum SolutionMethod {
		NAIVE, BINARY_SEARCH, USE_PREFIXES
	}

	private static final int MAX_ROWS = 50;
	private static final int MAX_COLS = 50;

	List<List<Character>> board;
	private int numRows;
	private int numCols;
	private int minLength;

	private Dictionary dictionary;

	public static void main(String[] args) {
		WordSearch w = new WordSearch();
	}

	public WordSearch() {
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

		printPuzzle();

		// Set min length
		setMinLength();
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
			this.numRows = 0;
			this.numCols = 0;
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

		if (temp.size() > MAX_COLS) {
			System.out.println("[Error] The number of columns in the board must be less than or equal to 50.");
			return null;
		}

		gameBoard.add(temp);

		this.numCols = temp.size();
		int numRows = 1;

		try {
			while (fileScan.hasNextLine()) {
				numRows++;

				if (numRows > MAX_ROWS) {
					System.out.println("[Error] The number of rows in the board must be less than or equal to 50.");
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

		this.numRows = numRows;

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

		if (row.size() > MAX_COLS) {
			System.out.println("[Error] The number of columns in the board must be less than or equal to 50.");
			return null;
		} else if (row.size() != this.numCols) {
			System.out.println("[Error] The board is not rectangular.");
			return null;
		}

		return row;
	}

	private void setMinLength() {		
		Scanner scan = new Scanner(System.in);

		do {
			System.out.print("Please enter minimum word length to search for: ");
			try {
				int minLength = scan.nextInt();
				if (minLength > 0) {
					this.minLength = minLength;
					break;
				} else {
					System.out.println("Please enter a positive integer.");
				}
			} catch (Exception e) {
				System.out.println("Could not parse input. Please try again.");
				scan.next();
			}
		} while(true);

		System.out.println();
	}

	public void printPuzzle() {
		int rowNum = 0;

		System.out.println();
		System.out.print(" ");

		for (int i = 0; i < board.get(0).size(); i++) {
			System.out.print("   " + i % 10);
		}

		System.out.println();

		for (List<Character> row : board) {
			System.out.print(rowNum % 10 + "   ");
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

	public double solve(SolutionMethod method) {
		int numMatches = 0;

		long startTime = System.nanoTime();

		if (method == SolutionMethod.NAIVE) {
			numMatches = solveNaively();
		} else if (method == SolutionMethod.BINARY_SEARCH) {
			numMatches = solveWithBinarySearch();
		} else if (method == SolutionMethod.USE_PREFIXES) {
			numMatches = solveWithPrefixes();
		}

		long endTime = System.nanoTime();
		double timeInSeconds = (endTime - startTime) / 1e9;

		System.out.println("Found " + numMatches + " matches");

		return timeInSeconds;
	}

	public int solveNaively() {
		int numMatches = 0;

		for (int w = 0; w < dictionary.size(); w++) {
			String word = dictionary.get(w);
			if (dictionary.get(w).length() >= minLength) {
				for (int r = 0; r < numRows; r++) {
					for (int c = 0; c < numCols; c++) {
						if (board.get(r).get(c) == word.charAt(0)) {
							for (int rowDir = -1; rowDir <= 1; rowDir++) {
								for (int colDir = -1; colDir <= 1; colDir++) {
									if (rowDir != 0 || colDir != 0) {
										numMatches += checkForWord(word, r, c, rowDir, colDir);
									}
								}
							}
						}
					}
				}
			}
		}

		return numMatches;
	}

	private int checkForWord(String word, int row, int col, int rowDir, int colDir) {
		int sequenceLength = 0;
		int rowStart = row;
		int colStart = col;
		int wordLength = word.length();

		while (inBounds(row, col) && sequenceLength < wordLength) {
			if (board.get(row).get(col) != word.charAt(sequenceLength)) {
				return 0;
			}

			row += rowDir;
			col += colDir;
			sequenceLength++;
		}

		if (sequenceLength != wordLength) {
			return 0;
		}

		printWord(word, rowStart, row, colStart, col);

		return 1;
	}


	public int solveWithBinarySearch() {
		int numMatches = 0;

		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numCols; c++) {
				for (int rowDir = -1; rowDir <= 1; rowDir++) {
					for (int colDir = -1; colDir <= 1; colDir++) {
						if (rowDir != 0 || colDir != 0) {
							numMatches += checkForWordsWithBinarySearch(r, c, rowDir, colDir);
						}
					}
				}
			}
		}

		return numMatches;
	}

	private int checkForWordsWithBinarySearch(int row, int col, int rowDir, int colDir) {
		String build = "";
		int rowStart = row;
		int colStart = col;
		int numMatches = 0;

		while (inBounds(row, col)) {
			build += board.get(row).get(col);

			if (build.length() >= minLength && dictionary.binarySearch(build)) {
				printWord(build, rowStart, row, colStart, col);
				numMatches += 1;
			}

			row += rowDir;
			col += colDir;
		}

		return numMatches;
	}

	public int solveWithPrefixes() {
		int numMatches = 0;

		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numCols; c++) {
				for (int rowDir = -1; rowDir <= 1; rowDir++) {
					for (int colDir = -1; colDir <= 1; colDir++) {
						if (rowDir != 0 || colDir != 0) {
							numMatches += checkForWordsWithPrefixes(r, c, rowDir, colDir);
						}
					}
				}
			}
		}

		return numMatches;
	}

	private int checkForWordsWithPrefixes(int row, int col, int rowDir, int colDir) {
		String build = "";
		int rowStart = row;
		int colStart = col;
		int numMatches = 0;

		while (inBounds(row, col)) {
			build += board.get(row).get(col);

			if (build.length() >= minLength && dictionary.binarySearch(build)) {
				printWord(build, rowStart, row, colStart, col);
				numMatches += 1;
			} else if (!dictionary.isPrefix(build)) {
				return numMatches;
			}

			row += rowDir;
			col += colDir;
		}

		return numMatches;
	}

	private boolean inBounds(int row, int col) {
		return row >= 0 && col >= 0 && row < numRows && col < numCols;
	}

	private void printWord(String word, int rowStart, int rowEnd, int colStart, int colEnd) {
		System.out.println("Found \"" + word + "\" at (" + rowStart + ", " + colStart + ") to (" + rowEnd + ", " + colEnd + ")");
	}

	
}