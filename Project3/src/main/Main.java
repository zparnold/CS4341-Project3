/**
 * 
 */
package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author zparnold ssmaceachern
 *
 */
public class Main {
	
	//Class vars
	private static String csvFile = "../files/trainDataSet.csv";
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Main board = new Main();
		board.readInFromCSV();

	}
	/**
	 * reads in the training data from the CSV file and spawns off new board classes to be evaluated
	 */
	private void readInFromCSV(){
			 
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
		 
			try {
		 
				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {
					String stringBoard[][] = null;

					//Read in the file here (probably a for loop that's been unrolled in 1 direction)
					//Don't forget that the last column is an eval of who won.
					
					Board newBoard = new Board(stringBoard);
				}
		 
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
