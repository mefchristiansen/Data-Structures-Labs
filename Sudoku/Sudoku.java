public class Sudoku {
	public static final int BLANK_SPACE = 0;
	public static final int SUDOKU_MIN = 1;
	public static final int SUDOKU_MAX = 9;

	private Board sudokuBoard;

	public Sudoku() {
		sudokuBoard = new Board();
	}

	public boolean validGame() {
		return sudokuBoard.validGame();
	}

	public void solveBoard() {
		if (!sudokuBoard.validGame()) {
			return;
		}

		Stack<Move> moveStack = new Stack<Move>();

		while(!sudokuBoard.solved()) {
			Move nextMove = sudokuBoard.nextMove();
			if (nextMove.getVal() != Move.MOVE_INVALID) {
				//Valid move
				moveStack.push(nextMove);
				sudokuBoard.setValue(nextMove.getRow(), nextMove.getCol(), nextMove.getVal());
			} else {
				if (moveStack.isEmpty()) {
					System.out.println("There is no valid solution to this puzzle.");
					return;
				}

				do {
					Move stackMove = moveStack.pop();
					// Remove move from board.
					sudokuBoard.setValue(stackMove.getRow(), stackMove.getCol(), Sudoku.BLANK_SPACE);
					// Change move at current spot in board. If no valid move exists, we need to continue to backtrack
					Move changeMove = sudokuBoard.changeMove(stackMove);
					if (changeMove.getVal() != Move.MOVE_INVALID) {
						moveStack.push(changeMove);
						sudokuBoard.setValue(changeMove.getRow(), changeMove.getCol(), changeMove.getVal());
						break;
					} else if (moveStack.isEmpty()) {
						System.out.println("There is no valid solution to this puzzle.");
						return;
					}
				} while(true);
			}
		}
	}

	public void solutionCorrect() {
		if (sudokuBoard.validSolution()) {
			if (sudokuBoard.equalToSolution()) {
				System.out.println("The solved board matches the provided solution!");
			} else {
				System.out.println("The solved board does not match the provided solution.");
			}
		}
	}

	public void printBoard() {
		sudokuBoard.printBoard();
	}
}