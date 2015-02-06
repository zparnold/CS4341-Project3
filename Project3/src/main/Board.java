/**
 * 
 */
package main;

/**
 * @author zparnold ssmaceachern
 *
 */
public class Board {
	
	//Class vars
	private String[][] board = null;
	
	private String[] evaluation = null;
	
	/**
	 * Constructor for the board class, takes in a 2D array for the board
	 * Automatically evaluates the board
	 * @param newBoard
	 */
	public Board(String[][] newBoard){
		this.setBoard(newBoard);
		this.evaluate();
	}
	
	/**
	 * Produces the evaluation for the features generated by the Heuristics
	 */
	public void evaluate(){
		//Ideas for heuristics:
		
		//How many pieces each one has in the middle of the board
		
		//How many 3 in a rows the person has (vertically, horizontally)
		//How many 2 in a rows the person has (vertically, horizontally)
		
		//Who went first
		
		
	}
	
	//Getters and Setters

	/**
	 * @return the evaluation
	 */
	public String[] getEvaluation() {
		return evaluation;
	}

	/**
	 * @param evaluation the evaluation to set
	 */
	private void setEvaluation(String[] evaluation) {
		this.evaluation = evaluation;
	}

	/**
	 * @return the board
	 */
	public String[][] getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	private void setBoard(String[][] board) {
		this.board = board;
	}

}
