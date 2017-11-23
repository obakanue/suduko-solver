public class Solver {
	private int[][] sudoku;
	private boolean[][] isInput;
	private int currentRow;
	private int currentCol;

	public Solver(int[][] sudoku) {
		this.sudoku = sudoku;
		currentRow = 0;
		currentCol = 0;
		ifInput();
	}

	private void ifInput() {
		isInput = new boolean[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sudoku[i][j] != 0) {
					isInput[i][j] = true;
				} else {
					isInput[i][j] = false;
				}
			}
		}
	}

	private void nextCell() {
		if (currentCol < 8) {
			currentCol++;
		} else {
			currentCol = 0;
			currentRow++;
		}
		if (currentRow < 8) {
			if (isInput[currentRow][currentCol]) {
				nextCell();
			}
		}
	}

	private void priorCell() {
		if (currentCol == 0) {
			currentCol = 8;
			currentRow--;
		} else {
			currentCol--;
		}
		if (currentRow > -1) {
			if (isInput[currentRow][currentCol]) {
				priorCell();
			}
		}
	}

	public boolean solve() {
		if (currentRow == 9) {
			return true;
		} else if (currentRow == -1) {
			return false;
		} else {
			if (!isInput[currentRow][currentCol]) {
				sudoku[currentRow][currentCol]++;
			}
			if (checkRules(currentRow, currentCol) && sudoku[currentRow][currentCol] != 10) {
				nextCell();
				return solve();
			} else {
				if (sudoku[currentRow][currentCol] == 10) {
					sudoku[currentRow][currentCol] = 0;
					priorCell();
				}
				return solve();
			}
		}

	}

	/*
	 * / Checks all rules for a spot in a sudoku.
	 */
	public boolean checkRules(int rad, int kolum) {
		return checkColumn(rad, kolum) && checkRow(rad, kolum) && checkBox(rad, kolum);
	}

	public boolean checkRow(int rad, int kolum) {
		for (int i = 0; i < 9; i++) {
			if (rad != i) {
				if (sudoku[rad][kolum] == sudoku[i][kolum]) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkColumn(int rad, int kolum) {
		for (int i = 0; i < 9; i++) {
			if (kolum != i) {
				if (sudoku[rad][kolum] == sudoku[rad][i]) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkBox(int rad, int kolum) {
		for (int i = 3 * (rad / 3); i <= 3 * (rad / 3) + 2; i++) {
			for (int j = 3 * (kolum / 3); j <= 3 * (kolum / 3) + 2; j++) {
				if (rad != i && kolum != j) {
					if (sudoku[i][j] == sudoku[rad][kolum]) {
						return false;
					}
				}
			}
		}
		return true;
	}

}
