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
							} else if (((j + 2) < BOARD_WIDTH)
									&& board[i][j + 2] == 0) {
								player1TwoStreak++;
							} else {
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

							} else if (((j + 2) < BOARD_WIDTH)
									&& board[i][j + 2] == 0) {
								player2TwoStreak++;
							} else {
								player2TwoStreak++;
							}
						}
					} catch (Exception e) {
						continue;
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
						if (((i + 1) < BOARD_HEIGHT) && board[i + 1][j] == 1) {

							if (((i + 2) < BOARD_HEIGHT)
									&& board[i + 2][j] == 1) {
								player1ThreeStreak++;
							} else if (((i + 2) < BOARD_HEIGHT)
									&& board[i + 2][j] == 0) {
								player1TwoStreak++;
							} else {
								player1TwoStreak++;
							}
						}
					} catch (Exception e) {
						continue;
					}
				} else if (board[i][j] == 2) {
					try {
						if (((i + 1) < BOARD_HEIGHT) && board[i + 1][j] == 2) {

							if (((i + 2) < BOARD_HEIGHT)
									&& board[i + 2][j] == 2) {
								player2ThreeStreak++;

							} else if (((i + 2) < BOARD_HEIGHT)
									&& board[i + 2][j] == 0) {
								player2TwoStreak++;
							} else {
								player2TwoStreak++;
							}
						}
					} catch (Exception e) {
						continue;
					}

				}
				player1TwoInARows += player1TwoStreak;
				player1ThreeInARows += player1ThreeStreak;
				player2TwoInARows += player2TwoStreak;
				player2ThreeInARows += player2ThreeStreak;

				player1TwoStreak = player1ThreeStreak = player2TwoStreak = player2ThreeStreak = 0;
			}
		}

		// How many does each player have in the middle?
		int player1MiddleCount = 0, player2MiddleCount = 0;
		for (int i = 1; i < BOARD_HEIGHT - 1; i++) {
			for (int j = 1; j < BOARD_WIDTH - 1; j++) {
				if (board[i][j] == 1) {
					player1MiddleCount++;
				} else if (board[i][j] == 2) {
					player2MiddleCount++;
				}
			}
		}


		// Column and row dominance
		int player1RowDominance = 0, player1ColumnDominance = 0, player2RowDominance = 0, player2ColumnDominance = 0;
		int player1Total, player2Total;

		// loop over the rows and columns looking for domination
		// This one is row based
		for (int i = 0; i < BOARD_HEIGHT; i++) {
			player1Total = player2Total = 0;

			for (int j = 0; j < BOARD_WIDTH; j++) {
				if (board[i][j] == 1) {
					player1Total++;
				} else if (board[i][j] == 2) {
					player2Total++;
				}
			}
			// Simple enough to understand I think
			if (player1Total > player2Total) {
				player1RowDominance++;
			} else if (player2Total > player1Total) {
				player2RowDominance++;
			}
		}

		// This one is column based

		for (int j = 0; j < BOARD_WIDTH; j++) {
			player1Total = player2Total = 0;

			for (int i = 0; i < BOARD_HEIGHT; i++) {
				if (board[i][j] == 1) {
					player1Total++;
				} else if (board[i][j] == 2) {
					player2Total++;
				}
			}
			// Simple enough to understand I think
			if (player1Total > player2Total) {
				player1ColumnDominance++;
			} else if (player2Total > player1Total) {
				player2ColumnDominance++;
			}
		}

		//Player Isolation Feature:
		//If one player has a row or column to themselves with no other pieces
		// in it other than their own, does it make a difference?
		int player1RowsOwned = 0, player2RowsOwned = 0;
		int player1ColOwned = 0, player2ColOwned = 0;
		for (int i = 0; i < BOARD_HEIGHT; i++) {
			//Check 
			int p1Count = 0, p2Count = 0;
			for (int j = 0; j < BOARD_WIDTH; j++) {
				if (board[i][j] == 1) {
					p1Count++;
				} else if (board[i][j] == 2) {
					p2Count++;
				}
			}
			
			if(p1Count == 0 && p2Count == 0)
				continue;
			
			if(p1Count == 0 && p2Count > 0){
				player2RowsOwned++;
			} else if(p2Count == 0 && p1Count > 0){
				player1RowsOwned++;
			}
		}
		
		for (int i = 0; i < BOARD_WIDTH; i++) {
			//Check 
			int p1Count = 0, p2Count = 0;
			for (int j = 0; j < BOARD_HEIGHT; j++) {
				if (board[j][i] == 1) {
					p1Count++;
				} else if (board[j][i] == 2) {
					p2Count++;
				}
			}
			
			if(p1Count == 0 && p2Count > 0){
				player2ColOwned++;
			} else if(p2Count == 0 && p1Count > 0){
				player1ColOwned++;
			}
		}
		
		// Ideas for heuristics:

		// How many total pieces are on the board (inverse relationship, the
		// more there are, the lower the heuristic
		
		//Potential Connect 4's
		int p1possibleConnect4 = 0, p2possibleConnect4 = 0;
		for(int i = 0; i < BOARD_HEIGHT; i++){
			for(int j = 0; j < BOARD_WIDTH; j++){
				if(board[i][j] == 1){
					p1possibleConnect4 += checkConnect4(1, i, j, board);
						
				}
				
				if(board[i][j] == 2){
					p2possibleConnect4 += checkConnect4(2, i, j, board);
						
				}
			}
		}
		
		System.out.println("player1TwoInARows: " + player1TwoInARows);
		System.out.println("player1ThreeInARows: " + player1ThreeInARows);
		System.out.println("player2TwoInARows: " + player2TwoInARows);
		System.out.println("player2ThreeInARows: " + player2ThreeInARows);
		System.out.println("player1MiddleCount: " + player1MiddleCount);
		System.out.println("player2MiddleCount: " + player2MiddleCount);

		System.out.println("player1possibleConnect4: " + p1possibleConnect4);
		System.out.println("player2possibleConnect4: " + p2possibleConnect4);

		System.out.println("player1RowDominance: " + player1RowDominance);
		System.out.println("player2RowDominance: " + player2RowDominance);
		System.out.println("player1ColumnDominance: " + player1ColumnDominance);
		System.out.println("player2ColumnDominance: " + player2ColumnDominance);
		
		System.out.println("player1RowsOwned: " + player1RowsOwned);
		System.out.println("player2RowsOwned: " + player2RowsOwned);
		System.out.println("player1ColOwned: " + player1ColOwned);
		System.out.println("player2ColOwned: " + player2ColOwned);
	}

	// Getters and Setters
	/**
	 * Check the surrounding positions of a board and return a boolean if there is a possible connect4
	 * @param player
	 * @param x Coord
	 * @param y Coord
	 * @param b Board
	 * @return
	 */
	private int checkConnect4(int player, int x, int y, int[][] b) {
		/*
		 * We get a position. Check from position whether or not there is a connect4
		 * We check by checking the four surrounding spaces of a space for a potential connect4
		 * 	0,0,0,?,0,?,0,
			0,0,0,?,?,0,0,
			?,?,?,X,?,?,?,
			0,0,?,?,0,0,0,
			0,?,0,?,0,0,0,
			?,0,0,?,0,0,0,
		 */
		//If this function is called, a piece was found
		int currentStreak = 1;
		int searchDepth = 3;
		int possibleConnect4 = 0;
		
		//Horizontal
		//Going right. If greater than board width, break
		for(int i = 1; i <= searchDepth; i++){
			if(y + i >= BOARD_WIDTH || (board[x][y + i] != player && board[x][y + i] > 0)){
				break;
			}
			if(board[x][y + i] == player || board[x][y + i] == 0){
				currentStreak++;
				
				if(currentStreak == 4){
					currentStreak = 1;
					possibleConnect4++;
				}
			}
		}
		
		//Going left. If less than 0, break.
		currentStreak = 1;
		for(int i = searchDepth; i >= 0; i--){
			if(y - i < 0 || (board[x][y - i] != player && board[x][y - i] > 0)){
				break;
			}
			if(board[x][y - i] == player || board[x][y - i] == 0){
				currentStreak++;
				
				if(currentStreak == 4){
					currentStreak = 1;
					possibleConnect4++;
				}
			}
		}
		
		//Vertical
		//Going Down. If greater than board width, break
		currentStreak = 1;
		for(int i = 1; i <= searchDepth; i++){
			if(x + i >= BOARD_HEIGHT || (board[x + i][y] != player && board[x + i][y] > 0)){
				break;
			}
			if(board[x + i][y] == player || board[x + i][y] == 0){
				currentStreak++;
				
				if(currentStreak == 4){
					currentStreak = 1;
					possibleConnect4++;
				}
			}
		}
		
		//Going Up. If less than 0, break.
		currentStreak = 1;
		for(int i = searchDepth; i >= 0; i--){
			if(x - i < 0 || (board[x - i][y] != player && board[x - i][y] > 0)){
				break;
			}
			if(board[x - i][y] == player || board[x - i][y] == 0){
				currentStreak++;
				
				if(currentStreak == 4){
					currentStreak = 1;
					possibleConnect4++;
				}
			}
		}
		//possibleConnect4 = currentStreak/4;
		return possibleConnect4;
	}

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
