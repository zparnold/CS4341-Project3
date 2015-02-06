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

/**
 * @author zparnold ssmaceachern
 *
 */
public class Main {

	// Class vars 

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
	 * @throws IOException 
	 */
	private void readInFromCSV() throws IOException {
		
		String myPath = new File("src/files/trainDataSet.csv").getAbsolutePath();
		String outPath = new File("src/files/out.csv").getAbsolutePath();
		String csvFile = myPath;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					outPath));
			while ((line = br.readLine()) != null) {
				String stringBoard[][] = null;

				// Read in the file here (probably a for loop that's been
				// unrolled in 1 direction)
				// Don't forget that the last column is an eval of who won.

				Board newBoard = new Board(stringBoard);
				newBoard.evaluate();

				// write now back to the file
				
				writer.write(line);
				writer.newLine();
				//writer.write(newBoard.getEvaluation().toString());
				
			}
			writer.close();
			//Handle exceptions
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

}
