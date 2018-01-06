public class Move {
	public static final int MOVE_INVALID = -1;

	private int row;
	private int column;
	private int value;

	public Move(int row, int column, int value) {
		this.row = row;
		this.column = column;
		this.value = value;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return column;
	}

	public int getVal() {
		return value;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int column) {
		this.column = column;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void print() {
		System.out.println("Row: " + row + ", Column: " + column + ", Value: " + value);
	}

}