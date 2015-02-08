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
import java.io.InputStreamReader;
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
		board.readInFromCSV(args[0], args[1]);

	}

	/**
	 * reads in the training data from the CSV file and spawns off new board
	 * classes to be evaluated
	 * 
	 * @param outPath
	 * @param inPath
	 * 
	 * @throws IOException
	 */
	private void readInFromCSV(String inPath, String outPath)
			throws IOException {

		String myPath = new File(inPath).getAbsolutePath();
		String outFilePath = new File(outPath).getAbsolutePath();
		String csvFile = myPath;
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		int count = 0;
		try {

			br = new BufferedReader(new FileReader(csvFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					outFilePath));
			
			//Add headers to outfile
			String[] header = {"a1,a2,a3,a4,a5,a6,a7,b1,b2,b3,b4,b5,b6,b7,c1,c2,c3,c4,c5,c6,c7,d1,d2,d3,d4,d5,d6,d7,e1,e2,e3,e4,e5,e6,e7,f1,f2,f3,f4,f5,f6,f7,result,p1Two,p2Two,p1Threep,2Threep,1Mid,p2Mid,p1RowDom,p2RowDom,p1ColDom,p2ColDom,total"};
			writer.write(Arrays.toString(header));
			writer.newLine();
			while ((line = br.readLine()) != null) {
				if (count > 0) {
					int[][] stringBoard = new int[BOARD_HEIGHT][BOARD_WIDTH];
					String[] holder = line.split(csvSplitBy);
					int whoWon = Integer.parseInt(holder[holder.length - 1]);

					// Read in the file here (probably a for loop that's been
					// unrolled in 1 direction)
					// Don't forget that the last column is an eval of who won.
					for (int i = 0; i < 6; i++) {
						for (int j = 0; j < 7; j++) {
							stringBoard[i][j] = Integer
									.parseInt(holder[(i * BOARD_WIDTH) + j]);
						}
					}

					Board newBoard = new Board(stringBoard, whoWon);

					System.out.println("Board:");
					for (int[] s : stringBoard) {
						for (int i : s) {
							System.out.print(i + ",");
						}
						System.out.println();
					}

					newBoard.evaluate();

					// now get the evaluation in string format
					StringBuilder eval = new StringBuilder();

					for (Integer score : newBoard.getEvaluation()) {
						eval.append(",");
						eval.append(score.toString());
					}

					// write now back to the file

					line = line + eval;
					writer.write(line);
					writer.newLine();
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

}
