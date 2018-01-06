public class TestSudoku {

	public static void main(String[] args) {
		Sudoku sudokuGame = new Sudoku();

		if (!sudokuGame.validGame()) {
			return;
		}

		System.out.println();

		System.out.println("The Puzzle:");
		sudokuGame.printBoard();
		System.out.println();

		sudokuGame.solveBoard();

		System.out.println("The Solution:");
		sudokuGame.printBoard();
		System.out.println();

		sudokuGame.solutionCorrect();
	}
}