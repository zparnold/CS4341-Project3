/**
 * 
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zparnold ssmaceachern
 *
 */
public class Main {

	// Class vars
	private static int BOARD_WIDTH = 7;
	private static int BOARD_HEIGHT = 6;
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		Main board = new Main();
		board.readInFromCSV();

	}

	/**
	 * reads in the training data from the CSV file and spawns off new board
	 * classes to be evaluated
	 * 
	 * @throws IOException
	 */
	private void readInFromCSV() throws IOException {

		String myPath = new File("src/files/trainDataSet.csv")
				.getAbsolutePath();
		String outPath = new File("src/files/out.csv").getAbsolutePath();
		String csvFile = myPath;
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		int count = 0;
		try {

			br = new BufferedReader(new FileReader(csvFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(outPath));
			while ((line = br.readLine()) != null) {
				if (count > 5 && count < 10){
				int[][] stringBoard = new int[BOARD_HEIGHT][BOARD_WIDTH];
				String[] holder = line.split(csvSplitBy);
				int whoWon = Integer.parseInt(holder[holder.length - 1]);

				// Read in the file here (probably a for loop that's been
				// unrolled in 1 direction)
				// Don't forget that the last column is an eval of who won.
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 7; j++) {
						stringBoard[i][j] = Integer.parseInt(holder[(i*BOARD_WIDTH) + j]);
					}
				}
				
				
				Board newBoard = new Board(stringBoard, whoWon);
				
				System.out.println("Board:");
				for (int[] s : stringBoard){
					for (int i : s){
						System.out.print(i + ",");
					}
					System.out.println();
				}
				
				newBoard.evaluate();
				//System.out.println("Line: "+line);
				//System.out.println(Arrays.deepToString(stringBoard));
				
				//printBoard(line);
				// write now back to the file

				writer.write(line);
				writer.newLine();
				// writer.write(newBoard.getEvaluation().toString());
				}
				count++;
			}
			writer.close();
			// Handle exceptions

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
	}
	
	private void printBoard(String board){
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(board.charAt((i*7)+j) + " ");
			}
			System.out.print("\n");
		}
	}

}
