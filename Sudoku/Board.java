import java.util.*;
import java.io.*;

public class Board {
	public static final int BLANK_SPACE = 0;
	public static final int SUDOKU_MIN = 1;
	public static final int MOVE_INVALID = -1;
	public static final int BOARD_DIMENSION = 9;
	public static final int BLOCK_SIZE = 3;

	private int[][] board;
	private int[][] solution;

	public Board() {
		generatePuzzle();
	}

	private void generatePuzzle() {
		String boardFileName, solutionFileName;
		Scanner boardScanner, solutionScanner;

		do {
			boardFileName = getBoardFileName();
			boardScanner = getBoardFile(boardFileName);
			if (boardScanner != null) {
				break;
			}
		} while(true);

		board = readPuzzle(boardScanner);
		boardScanner.close();

		if (board == null) {
			System.out.println("There was an error reading that puzzle.");
			return;
		}

		if (!validBoard()) {
			board = null;
			System.out.println("The provided Sudoku game board is invalid. No solution can be found for this puzzle.");
			return;
		}

		solutionFileName = boardFileName + "-solution";
		try {
			solutionScanner = getBoardFile(solutionFileName);
			solution = readPuzzle(solutionScanner);
			solutionScanner.close();
			System.out.println("The solution to this puzzle was successfully read and will be used to test the solution.");
		} catch (Exception e) {
			System.out.println("There was an error reading the solution puzzle.");
			System.out.println("The final puzzle will not be tested against the solution.");
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

		filePath = "puzzles/" + fileName + ".txt";

		try {
			scan = new Scanner(new File(filePath));
		} catch (Exception e) {
			System.out.println("Could not find or open that puzzle. Please try again.");
			return null;
		}

		return scan;
	}

	private int[][] readPuzzle(Scanner fileScan) {
		// File is empty
		if (!fileScan.hasNextLine()) {
			System.out.println("Error: Sudoku file is empty.");
			return null;
		}

		Scanner lineScanner = new Scanner(fileScan.nextLine());
		List<Integer> temp = new ArrayList<Integer>();

		while (lineScanner.hasNextInt()) {
			int nextNum = lineScanner.nextInt();
			if (nextNum < 0 || nextNum > BOARD_DIMENSION) {
				System.out.println("[Error] All numbers in puzzle must be between 0 and 9.");
				return null;
			}
			temp.add(nextNum);
		}

		int numCols = temp.size();

		if (numCols != BOARD_DIMENSION) {
			System.out.println("[Error] The puzzle must be a 9x9 board.");
			return null;
		}

		int[][] board = new int[numCols][numCols];

		for (int i = 0; i < numCols; i++) {
			board[0][i] = temp.get(i);
		}

		int numRows = 1;

		try {
			while (fileScan.hasNextLine()) {
				numRows++;

				if (numRows > numCols) {
					System.out.println("[Error] The puzzle is not square.");
					return null;
				}

				lineScanner = new Scanner(fileScan.nextLine());
				int[] line = readNextLine(lineScanner);
				
				if (line == null) {
					return null;
				}

				board[numRows - 1] = line;
			}

			if (numRows != numCols) {
				System.out.println("[Error] The puzzle is not square.");
				return null;
			}
		} catch (Exception e) {
			System.out.println("There was an error reading the puzzle.");
			return null;
		}

		return board;
	}

	private int[] readNextLine(Scanner lineScanner) {
		int rowIndex = 0;
		int[] row = new int[BOARD_DIMENSION];
		while (lineScanner.hasNextInt()) {
			int nextNum = lineScanner.nextInt();

			if (nextNum < 0 || nextNum > BOARD_DIMENSION) {
				System.out.println("[Error] All numbers in puzzle must be between 0 and 9.");
				return null;
			}

			try {
				row[rowIndex] = nextNum;
			} catch (Exception e) {
				System.out.println("[Error] The puzzle is not square.");
				return null;
			}

			rowIndex++;
		}

		if (rowIndex != BOARD_DIMENSION) {
			System.out.println("[Error] The puzzle is not square.");
			return null;
		}

		return row;
	}

	public boolean validGame() {
		return board != null;
	}

	public boolean validSolution() {
		return solution != null;
	}

	public int getValue(int row, int col) {
		if (row < 0 || row >= BOARD_DIMENSION || col < 0 || col >= BOARD_DIMENSION) {
			return -1;
		}

		return board[row][col];
	}

	public void setValue(int row, int col, int val) {
		if (row < 0 || row >= BOARD_DIMENSION || col < 0 || col >= BOARD_DIMENSION) {
			return;
		}

		board[row][col] = val;
	}

	public Move nextMove() {
		for (int r = 0; r < BOARD_DIMENSION; r++) {
			for (int c = 0; c < BOARD_DIMENSION; c++) {
				if (board[r][c] == BLANK_SPACE) {
					for (int i = SUDOKU_MIN; i <= BOARD_DIMENSION; i++) {
						Move nextMove = new Move(r, c, i);
						if (validMove(nextMove)) {
							return nextMove;
						}
					}

					return new Move(Move.MOVE_INVALID, Move.MOVE_INVALID, Move.MOVE_INVALID);
				}	
			}
		}

		return null;
	}

	public Move changeMove(Move currentMove) {
		for (int i = currentMove.getVal() + 1; i <= BOARD_DIMENSION; i++) {
			currentMove.setValue(i);
			if (validMove(currentMove)) {
				return currentMove;
			}
		}

		currentMove.setValue(Move.MOVE_INVALID);
		return currentMove;
	}

	private boolean validMove(Move nextMove) {
		int row = nextMove.getRow();
		int col = nextMove.getCol();
		int value = nextMove.getVal();

		//Check row
		for (int i = 0; i < BOARD_DIMENSION; i++) {
			if (i != row && board[i][col] == value) {
				return false;
			}
		}

		//Check column
		for (int i = 0; i < BOARD_DIMENSION; i++) {
			if (i != col && board[row][i] == value) {
				return false;
			}
		}

		//Check square
		int blockRow = row / BLOCK_SIZE;
		int blockCol = col / BLOCK_SIZE;

		int rowMin = blockRow * BLOCK_SIZE;
		int rowMax = (blockRow * BLOCK_SIZE) + BLOCK_SIZE;
		int colMin = blockCol * BLOCK_SIZE;
		int colMax = (blockCol * BLOCK_SIZE) + BLOCK_SIZE;

		for (int i = rowMin; i < rowMax; i++) {
			for (int j = colMin; j < colMax; j++) {
				if (i != row && j != col && board[i][j] == value) {
					return false;
				}
			}
		}

		return true;
	}

	public boolean validBoard() {
		for (int r = 0; r < BOARD_DIMENSION; r++) {
			for (int c = 0; c < BOARD_DIMENSION; c++) {
				if (board[r][c] != BLANK_SPACE) {
					Move nextMove = new Move(r, c, board[r][c]);
					if (!validMove(nextMove)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public boolean solved() {
		for (int i = 0; i < BOARD_DIMENSION; i++) {
			for (int j = 0; j < BOARD_DIMENSION; j++) {
				if (board[i][j] == 0) {
					return false;
				}
			}
		}

		return true;
	}

	public void printBoard() {
		int numRows = board.length;
		int numCols = board[0].length;

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public boolean equalToSolution() {
		if (solution == null) {
			return false;
		}

		return Arrays.deepEquals(board, solution);
	}
}