import java.util.Scanner;

public class SudokuSolver
{
	public static void main(String[] args)
	{

		// The main menthod demonstrates my program in use. Here are 2 examples.
		// input for the puzzle
		// all original values are 1-9, if there is no preset value it will be 0
		// int[][] puzzle = new int[][] {{1,0,0,0,0,7,0,9,0},
		// 							{0,3,0,0,2,0,0,0,8},
		// 							{0,0,9,6,0,0,5,0,0},
		// 							{0,0,5,3,0,0,9,0,0},
		// 							{0,1,0,0,8,0,0,0,2},
		// 							{6,0,0,0,0,4,0,0,0},
		// 							{3,0,0,0,0,0,0,1,0},
		// 							{0,4,0,0,0,0,0,0,7},
		// 							{0,0,7,0,0,0,3,0,0}};
		int[][] puzzle = new int[9][9];
		for (int i = 0; i < 9; i++)
		{
			System.out.println("Line " + (i + 1) + ": ");
			for (int j = 0; j < 9; j++)
			{
				Scanner keyboard = new Scanner(System.in);
				int input = keyboard.nextInt();
				puzzle[i][j] = input;
			}
		}
		// Print the original puzzle
		System.out.println("Original puzzle: ");
		printPuzzle(puzzle);
		if (solver(puzzle))
		{
			// Print the solved puzzle
			System.out.println("Solution: ");
			printPuzzle(puzzle);
		}
		else
		{
			// if it cannot be solved
			System.out.println("No solution");
		}
		// another example
		// int[][] puzzle2 = new int[][] {{0,0,0,2,6,0,7,0,1},
		// 							{6,8,0,0,7,0,0,9,0},
		// 							{1,9,0,0,0,4,5,0,0},
		// 							{8,2,0,1,0,0,0,4,0},
		// 							{0,0,4,6,0,2,9,0,0},
		// 							{0,5,0,0,0,3,0,2,8},
		// 							{0,0,9,3,0,0,0,7,4},
		// 							{0,4,0,0,5,0,0,3,6},
		// 							{7,0,3,0,1,8,0,0,0}};
		// // Print the original puzzle
		// System.out.println("Original puzzle: ");
		// printPuzzle(puzzle2);
		// if (solver(puzzle2))
		// {
		// 	// Print the solved puzzle
		// 	System.out.println("Solution: ");
		// 	printPuzzle(puzzle2);
		// }
		// else
		// {
		// 	// if it cannot be solved
		// 	System.out.println("No solution");
		// }
	}
	// helper method to print puzzles
	public static void printPuzzle(int[][] puzzle)
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				System.out.print(puzzle[i][j] + "  ");
			}
			System.out.println();
		}
	}
	// method to check what values are in a given box's row. It will update the boolean arrar usedVal
	// based on what it finds
	public static boolean[] checkRow(int testPuzzle[][], int[][] puzzle, boolean usedVal[], int row)
	{
		for (int i = 0; i < 9; i++)
		{
			if (testPuzzle[row][i] > 0)
			{
				// if the value i exists in the row, change that boolean to true
				usedVal[puzzle[row][i] - 1] = true;
			}
		}
		return usedVal;
	}
	// method to check what values are in a given box's column. It will update the boolean arrar usedVal
	// based on what it finds
	public static boolean[] checkCol(int testPuzzle[][], int[][] puzzle, boolean usedVal[], int col)
	{
		for (int i = 0; i < 9; i++)
			{
				if (testPuzzle[i][col] > 0)
				{
					// if the value i exists in the column, change that boolean to true
					usedVal[puzzle[i][col] - 1] = true;
				}
			}
		return usedVal;
	}
	// method to check what values are in a given box's 3x3 box. It will update the boolean array usedVal
	// based on what it finds
	public static boolean[] checkBox(int testPuzzle[][], int[][] puzzle, boolean usedVal[], int row, int col)
	{
		// need to make sure we have the right starting points
		// each box starts where the row = 0,3,6 and each column = 0,3,6
		for (int i = row - (row % 3); i < row - (row % 3) + 3; i++) // start at correct row
		{
			for (int j = col - (col % 3); j < col - (col % 3) + 3; j++) // start at correct column
			{
				if (testPuzzle[i][j] > 0)
				{
					// if the value i exists in the 3x3 box, change that boolean to true
					usedVal[puzzle[i][j] - 1] = true;
				}
			}
		}
		return usedVal;
	}
	// this is the method that solves the puzzle
	public static boolean solver(int[][] puzzle)
	{
		// create another board called testPuzzle. Each value in this 9x9 array
		// will be either a 0, 1, or 2. If it is a 2, there was a preset value
		// in the input puzzle. If it is a 0, there is no current value.
		// If it is a 1, there is a possible value in the give row/column, but
		// it is not necessarily the correct one. This will allow us to keep track of 
		// where we are in the puzzle, and what we have solved vs. what we are attempting
		int[][] testPuzzle = new int[puzzle.length][puzzle[0].length];
		// now for each non-zero value in the preset puzzle, set the testPuzzle = 2
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				if (puzzle[i][j] > 0)
				{
					testPuzzle[i][j] = 2;
				}
			}
			// Use recursive method to solve puzzle, starting from the first box (0,0)
		}
		return solver(puzzle, testPuzzle, 0,0);
	}
	// main recursive searching method
	// inputs are the the original puzzle, the test puzzle, and 2 integers row and col. The integers
	// keep track of the position on the puzzle that we are currently changing
	public static boolean solver(int[][] puzzle, int[][] testPuzzle, int row, int col)
	{
		// if we get to the end of the row, 
		if (row == 9) // because each row column is from 0 to 8, if a = 9 then we have finished the row
		{
			// check if we have all values set, and if we have a possible solution
			int counter = 0;
			for (int i = 0; i < 9; i++)
			{
				for (int j = 0; j < 9; j++)
				{
					// if there is a non-zero value in a given box, update counter by 1
					// if counter gets to 81, we know that we have a completed (but not
					// necessarily correct) puzzle
					if (testPuzzle[i][j] > 0)
					{
						counter += 1;
					}
				}
			}
			if (counter == 81)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		// if not at the end of the row, we proceed
		if (testPuzzle[row][col] > 0) // if we already have a value, go on to the next one
		{
			int nextRow = row; // stay at the same row
			int nextCol = col + 1; // but move to the next column
			// but in the case that we pass the last column, move onto the next row and 
			// start over with the first column
			if (nextCol == 9)
			{
				nextRow = row + 1;
				nextCol = 0;
			}
			// recursive call of next position
			return solver(puzzle, testPuzzle, nextRow, nextCol);
		}
		// now, check each row, column, and 3x3 box
		else
		{
			// create a new array to check what values have already been used
			boolean[] usedVal = new boolean[9];
			// check the row and update usedVal
			checkRow(testPuzzle, puzzle, usedVal, row);
			// check the column and update usedVal
			checkCol(testPuzzle, puzzle, usedVal, col);
			// check 3x3 box
			checkBox(testPuzzle, puzzle, usedVal, row, col);
			// after we check each row, column, and 3x3 box, assign a possible value to the current position
			for (int i = 0; i < usedVal.length; i++)
			{
				// only try values that have not been previously used
				if(!usedVal[i])
				{
					// set it to 1 so that it is different from 2 (prefixed values) and 0 (undefined values)
					testPuzzle[row][col] = 1;
					puzzle[row][col] = i + 1;
					// increase the index, just like before
					int nextRow = row; // stay at the same row
					int nextCol = col + 1; // but move to the next column
					// but in the case that we pass the last column, move onto the next row and 
					// start over with the first column
					if (nextCol == 9)
					{
						nextRow = row + 1;
						nextCol = 0;
					}
					// if puzzle is solved return true
					if(solver(puzzle, testPuzzle, nextRow, nextCol))
					{
						return true;
					}
					// otherwise current set failed and we have to reverse the settings in testPuzzle
					// and move on to the next value
					for (int k = 0; k < 9; k++)
					{
						for (int l = 0; l < 9; l++)
						{
							// only reverse set values before current position
							if (k > row || (k == row && l >= col))
							{
								if (testPuzzle[k][l] == 1)
								{
									testPuzzle[k][l] = 0;
									puzzle[k][l] = 0;
								}
							}
						}
					}
				}
			}
		}
		// default
		return false;
	}
}
