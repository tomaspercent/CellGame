package model;

import java.awt.Color;
import java.util.Random;

/* This class must extend Game */
public class ClearCellGame extends Game {
	// We define an empty cell as BoardCell.EMPTY.
	// An empty row is defined as one where every cell corresponds to
	// BoardCell.EMPTY.

	Random random;
	// will always be 1 for this project
	int strategy;
	int score;

	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		this.strategy = strategy;
	}

	@Override
	public boolean isGameOver() {
		// makes a loop that goes through the last row and return
		// true if a cell is not empty
		for (int i = 0; i < maxCols; i++) {
			if (board[maxRows - 1][i] != BoardCell.EMPTY) {
				return true;
			}
		}
		// The game is over when the last board row
		// (row with index board.length -1) is different from empty row.
		return false;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void nextAnimationStep() {
	

		// insert a random row of BoardCell
		// as long as isGameOver != true

		// need to move the highest row one down?
		// insert new rows above that highest row
		if (isGameOver() != true) {

			for (int i = maxRows - 1; i >= 0; i--) {
				// rotate a whole row down since their is space
				// make a holder row?

				if (i == 0) {
					// if it is on the top row make a new random line of cells
					for (int j = 0; j < maxCols; j++) {
						setBoardCell(i, j, BoardCell.getNonEmptyRandomBoardCell(random));
					}
				} else {
					// start at bottom row and bring bring the one above it down
					for (int k = 0; k < board[i].length; k++) {
						board[i][k] = board[i - 1][k];
					}

				}

			}
		}

	}

	@Override
	public void processCell(int rowIndex, int colIndex) {

		Color cell = board[rowIndex][colIndex].getColor();


		// have different loops that trans verse the board differently


		if (isGameOver() == false && board[rowIndex][colIndex] != BoardCell.EMPTY) {

			// clear the cell that is clicked

			setBoardCell(rowIndex, colIndex, BoardCell.EMPTY);
			score++;

			// above cells
			// check if the above cell color is the same as the selected cell

			// check for out of bounds errors on the first one that is up
			if (rowIndex - 1 >= 0 && cell == board[rowIndex - 1][colIndex].getColor()) {
				int upCounter = 1;
				// check to see if the row above is still in bounds
				while (rowIndex - upCounter >= 0 && board[rowIndex - upCounter][colIndex].getColor() == cell) {
					setBoardCell(rowIndex - upCounter, colIndex, BoardCell.EMPTY);
					score++;
					upCounter++;
				}
			}

			// below cells

			if (rowIndex + 1 < maxRows && cell == board[rowIndex + 1][colIndex].getColor()) {
				int downCounter = 1;
				while (rowIndex + downCounter < maxRows && board[rowIndex + downCounter][colIndex].getColor() == cell) {
					setBoardCell(rowIndex + downCounter, colIndex, BoardCell.EMPTY);
					score++;
					downCounter++;
				}
			}

			// left cells

			if (colIndex - 1 >= 0 && cell == board[rowIndex][colIndex - 1].getColor()) {
				int leftCounter = 1;
				while (colIndex - leftCounter >= 0 && board[rowIndex][colIndex - leftCounter].getColor() == cell) {
					setBoardCell(rowIndex, colIndex - leftCounter, BoardCell.EMPTY);
					score++;
					leftCounter++;
				}
			}

			// right cells
			if (colIndex + 1 < maxCols && cell == board[rowIndex][colIndex + 1].getColor()) {
				int rightCounter = 1;
				while (colIndex + rightCounter < maxCols
						&& board[rowIndex][colIndex + rightCounter].getColor() == cell) {
					setBoardCell(rowIndex, colIndex + rightCounter, BoardCell.EMPTY);
					score++;
					rightCounter++;
				}
			}

			// up-left cells

			if (rowIndex - 1 >= 0 && colIndex - 1 >= 0 && cell == board[rowIndex - 1][colIndex - 1].getColor()) {
				int upLeftCounter = 1;
				while (rowIndex - upLeftCounter >= 0 && colIndex - upLeftCounter >= 0
						&& board[rowIndex - upLeftCounter][colIndex - upLeftCounter].getColor() == cell) {

					setBoardCell(rowIndex - upLeftCounter, colIndex - upLeftCounter, BoardCell.EMPTY);
					score++;
					upLeftCounter++;
				}
			}

			// up-right cells

			if (rowIndex - 1 >= 0 && colIndex + 1 < maxCols && cell == board[rowIndex - 1][colIndex + 1].getColor()) {
				int upRightCounter = 1;
				while (rowIndex - upRightCounter >= 0 && colIndex + upRightCounter < maxCols
						&& board[rowIndex - upRightCounter][colIndex + upRightCounter].getColor() == cell) {

					setBoardCell(rowIndex - upRightCounter, colIndex + upRightCounter, BoardCell.EMPTY);
					score++;
					upRightCounter++;
				}
			}

			// down-left cells

			if (rowIndex + 1 < maxRows && colIndex - 1 >= 0 && cell == board[rowIndex + 1][colIndex - 1].getColor()) {
				int downLeftCounter = 1;
				while (rowIndex + downLeftCounter < maxRows && colIndex - downLeftCounter >= 0
						&& board[rowIndex + downLeftCounter][colIndex - downLeftCounter].getColor() == cell) {

					setBoardCell(rowIndex + downLeftCounter, colIndex - downLeftCounter, BoardCell.EMPTY);
					score++;
					downLeftCounter++;
				}
			}

			// down-right cells

			if (rowIndex + 1 < maxRows && colIndex + 1 < maxCols
					&& cell == board[rowIndex + 1][colIndex + 1].getColor()) {
				int downRightCounter = 1;
				while (rowIndex + downRightCounter < maxRows && colIndex + downRightCounter < maxCols
						&& board[rowIndex + downRightCounter][colIndex + downRightCounter].getColor() == cell) {

					setBoardCell(rowIndex + downRightCounter, colIndex + downRightCounter, BoardCell.EMPTY);
					score++;
					downRightCounter++;
				}
			}

			// collapse
			// make a counter for the length of a row and if row reaches that counter then
			// collapse it

			// make a copy array and copy things over only if the row is not empty

			for (int i = 0; i < board.length; i++) {

				int emptyCells = 0;
				for (int j = 0; j < board[i].length; j++) {
					if (board[i][j] == BoardCell.EMPTY) {
						emptyCells++;
					}

				}

				// count how many empty rows there are
				// decrement
				if (emptyCells == maxCols) {

					for (int k = i; k < board.length; k++) {

						for (int t = 0; t < board[k].length; t++) {

							if (k + 1 < maxRows) {
								board[k][t] = board[k + 1][t];

							}
						}
					}
				}
			}
			// check if game is not over at the top
		}
	}

}