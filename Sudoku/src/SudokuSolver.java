public class SudokuSolver {
	private int[][] sudoku;
	private boolean[][] isInput;
	private boolean illegalInput;

	/*
	 * Creates a matrix with 9x9 integers null and illegalInput is false.
	 */
	public SudokuSolver() {
		sudoku = new int[9][9];
		illegalInput = false;
	}

	/*
	 * Checks if inputed values from user is legal (only numbers from 1 - 9) and
	 * puts the value in suduko[][] matrix and position of said legal input for
	 * boolean matrix isInput that keeps track of users input. If there is an
	 * illegal input illegalInput will be set to true.
	 * 
	 * @param Matrix of OnlyNumbersTextField[][] with start numbers for sudoku
	 * solver.
	 */
	public void checkInput(OnlyNumbersTextField[][] field) {
		isInput = new boolean[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (field[i][j].getText().equals("")) {
					sudoku[i][j] = 0;
					isInput[i][j] = false;
				} else {
					int input = Integer.parseInt(field[i][j].getText());
					sudoku[i][j] = input;
					isInput[i][j] = true;
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (isInput[i][j] == true) {
					if (!checkRules(i, j)) {
						System.out.println("ja");
						illegalInput = true;
					}
				}
			}
		}
	}

	/*
	 * Method that attempts to solve sudoku puzzle sudoku[][].
	 * 
	 * @return boolean true if it was a solvable puzzle and false if unsolvable
	 * or illegal input.
	 */
	public boolean solve(int row, int column) {
		if (illegalInput == true) {
			return false;
		}
		if (row == 9) {
			return true;
		}
		if (sudoku[row][column] == 0) {
			for (int i = 1; i <= 9; i++) {
				sudoku[row][column] = i;
				if (checkRules(row, column)) {
					if (column != 8) {
						if (solve(row, column + 1)) {
							return true;
						}
					} else {
						if (solve(row + 1, 0)) {
							return true;
						}
					}
				}
			}
			sudoku[row][column] = 0;
			return false;
		} else {
			if (checkRules(row, column)) {
				if (column != 8) {
					if (solve(row, column + 1)) {
						return true;
					}
				} else {
					if (solve(row + 1, 0)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/*
	 * Returns the state of illegalInput which determins if the users input is
	 * illegal or not.
	 * 
	 * @return illegalInput.
	 */
	public boolean getIllegalInputState() {
		return illegalInput;
	}

	/*
	 * Returns the finnished sudoku matrix sudoku[][].
	 * 
	 * @return the matrix sudoku[][].
	 */
	public int[][] getSolvedSudoku() {
		return sudoku;
	}

	/*
	 * Resets the solvers attribute illegalInput to false.
	 */
	public void resetIllegalInput() {
		illegalInput = false;
	}

	private boolean checkRules(int row, int col) {
		if (checkColumn(row, col) && checkRow(row, col) && checkBox(row, col)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkRow(int row, int col) {
		for (int i = 0; i < 9; i++) {
			if (row != i) {
				if (sudoku[row][col] == sudoku[i][col] && sudoku[i][col] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean checkColumn(int row, int col) {
		for (int i = 0; i < 9; i++) {
			if (col != i) {
				if (sudoku[row][col] == sudoku[row][i] && sudoku[row][i] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean checkBox(int row, int col) {
		for (int i = 3 * (row / 3); i <= 3 * (row / 3) + 2; i++) {
			for (int j = 3 * (col / 3); j <= 3 * (col / 3) + 2; j++) {
				if (row != i && col != j) {
					if (sudoku[i][j] == sudoku[row][col] && sudoku[i][j] != 0) {
						return false;
					}
				}
			}
		}
		return true;
	}

}
