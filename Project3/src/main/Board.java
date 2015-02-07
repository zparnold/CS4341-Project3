/**
 * 
 */
package main;

/**
 * @author zparnold ssmaceachern
 *
 */
public class Board {

	// Class vars
	private int[][] board = null;

	private int[] evaluation = new int[4];
	private int whoWon = 0;

	private static int BOARD_WIDTH = 7;
	private static int BOARD_HEIGHT = 6;

	/**
	 * Constructor for the board class, takes in a 2D array for the board
	 * Automatically evaluates the board
	 * 
	 * @param stringBoard
	 *            - a 2d array representation of the current game board
	 * @param winner
	 *            - player 1, or 2 whoever won
	 */
	public Board(int[][] stringBoard, int winner) {
		this.setBoard(stringBoard);
		this.setWhoWon(winner);

	}

	/**
	 * Produces the evaluation for the features generated by the Heuristics
	 */
	public void evaluate() {
		// Handle empty board
		if (this.getBoard() == null) {
			System.err.println("Board is null.");
			System.exit(-1);
		}

		int player1TwoInARows = 0;
		int player2TwoInARows = 0;
		int player1ThreeInARows = 0;
		int player2ThreeInARows = 0;

		// Count Horizontal pieces in a row
		for (int i = 0; i < BOARD_HEIGHT; i++) {
			int player1TwoStreak = 0;
			int player1ThreeStreak = 0;
			int player2TwoStreak = 0;
			int player2ThreeStreak = 0;

			for (int j = 0; j < BOARD_WIDTH; j++) {

				if (board[i][j] == 1) {
					try {
						if (((j + 1) < BOARD_WIDTH) && board[i][j + 1] == 1) {

							if (((j + 2) < BOARD_WIDTH) && board[i][j + 2] == 1) {
								player1ThreeStreak++;
							} else if (((j + 2) < BOARD_WIDTH) && board[i][j + 2] == 0) {
								player1TwoStreak++;
							} else{
								player1TwoStreak++;
							}
						}
					} catch (Exception e) {
						continue;
					}
				} else if (board[i][j] == 2) {
					try {
						if (((j + 1) < BOARD_WIDTH) && board[i][j + 1] == 2) {

							if (((j + 2) < BOARD_WIDTH) && board[i][j + 2] == 2) {
								player2ThreeStreak++;

							} else if (((j + 2) < BOARD_WIDTH) && board[i][j + 2] == 0) {
								player2TwoStreak++;
							} else{
								player2TwoStreak++;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				player1TwoInARows += player1TwoStreak;
				player1ThreeInARows += player1ThreeStreak;
				player2TwoInARows += player2TwoStreak;
				player2ThreeInARows += player2ThreeStreak;

				player1TwoStreak = player1ThreeStreak = player2TwoStreak = player2ThreeStreak = 0;
			}
		}

		// Count VERTICAL pieces in a row
				for (int i = 0; i < BOARD_HEIGHT; i++) {
					int player1TwoStreak = 0;
					int player1ThreeStreak = 0;
					int player2TwoStreak = 0;
					int player2ThreeStreak = 0;

					for (int j = 0; j < BOARD_WIDTH; j++) {

						if (board[i][j] == 1) {
							try {
								if (((i + 1) < BOARD_HEIGHT) && board[i+1][j] == 1) {

									if (((i + 2) < BOARD_HEIGHT) && board[i+2][j] == 1) {
										player1ThreeStreak++;
									} else if (((i + 2) < BOARD_HEIGHT) && board[i+2][j] == 0) {
										player1TwoStreak++;
									} else{
										player1TwoStreak++;
									}
								}
							} catch (Exception e) {
								continue;
							}
						} else if (board[i][j] == 2) {
							try {
								if (((i + 1) < BOARD_HEIGHT) && board[i+1][j] == 2) {

									if (((i + 2) < BOARD_HEIGHT) && board[i+2][j] == 2) {
										player2ThreeStreak++;

									} else if (((i + 2) < BOARD_HEIGHT) && board[i+2][j] == 0) {
										player2TwoStreak++;
									}
									else{
										player2TwoStreak++;
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

						}
						player1TwoInARows += player1TwoStreak;
						player1ThreeInARows += player1ThreeStreak;
						player2TwoInARows += player2TwoStreak;
						player2ThreeInARows += player2ThreeStreak;

						player1TwoStreak = player1ThreeStreak = player2TwoStreak = player2ThreeStreak = 0;
					}
				}

				
		int player1MiddleCount = 0, player2MiddleCount = 0;
		for(int i = 1; i < BOARD_HEIGHT-1; i++){
			for(int j = 1; j < BOARD_WIDTH-1; j++){
				if(board[i][j] == 1){
					player1MiddleCount++;
				}else if(board[i][j] == 2){
					player2MiddleCount++;
				}
			}
		}

		// Ideas for heuristics:

		// How many pieces each one has in the middle of the board

		// How many 3 in a rows the person has (vertically, horizontally)
		// How many 2 in a rows the person has (vertically, horizontally)

		// How many total pieces are on the board (inverse relationship, the
		// more there are, the lower the heuristic

		System.out.println("player1TwoInARows: " + player1TwoInARows);
		System.out.println("player1ThreeInARows: " + player1ThreeInARows);
		System.out.println("player2TwoInARows: " + player2TwoInARows);
		System.out.println("player2ThreeInARows: " + player2ThreeInARows);
		System.out.println("player1MiddleCount: " + player1MiddleCount);
		System.out.println("player2MiddleCount: " + player2MiddleCount);

	}

	// Getters and Setters

	/**
	 * @return the evaluation
	 */
	public int[] getEvaluation() {
		return evaluation;
	}

	/**
	 * @param evaluation
	 *            the evaluation to set
	 */
	private void setEvaluation(int[] evaluation) {
		this.evaluation = evaluation;
	}

	/**
	 * @return the board
	 */
	public int[][] getBoard() {
		return board;
	}

	/**
	 * @param stringBoard
	 *            the board to set
	 */
	private void setBoard(int[][] stringBoard) {
		this.board = stringBoard;
	}

	/**
	 * @return the whoWon
	 */
	public int getWhoWon() {
		return whoWon;
	}

	/**
	 * @param whoWon
	 *            the whoWon to set
	 */
	private void setWhoWon(int whoWon) {
		this.whoWon = whoWon;
	}

}
