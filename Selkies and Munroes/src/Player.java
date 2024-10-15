
public class Player {
	
	private String username;
	private String token;
	private int turnsTaken;
	private int playerPosCol;
	private int playerPosRow;
	
	/**
	 * Default constructor
	 */
	public Player() {
		username="";
		token="";
		turnsTaken=0;
		playerPosCol=0;
		playerPosRow=10;
	}
	
	/**
	 * Setting player username
	 * @param user player's username
	 */
	public void setUsername(String user) {
		username=user;
	}
	/**
	 * Setting player token
	 * @param newToken player's token to show on board
	 */
	public void setToken(String newToken) {
		token=newToken;
	}
	/**
	 * Setting player token
	 * @param newToken player's token to show on board
	 */
	public void setTurns(int newTurn) {
		turnsTaken=newTurn;
	}
	/**
	 * Setting player's row position on 2D array/game board
	 * @param newRow player's row position
	 */
	public void setPlayerPosRow(int newRow) {
		playerPosRow=newRow;
	}
	/**
	 * Setting player's column position on 2D array/game board
	 * @param newCol player's column position
	 */
	public void setPlayerPosCol(int newCol) {
		playerPosCol=newCol;
	}
	
	/**
	 * Get the username
	 * @return username chosen by player
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Get the player token
	 * @return player token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * Get the amount of turns taken by a player
	 * @return turns taken by player
	 */
	public int getTurns() {
		return turnsTaken;
	}
	/**
	 * Get the player's row position on the 2D array/game board
	 * @return player's row position
	 */
	public int getPlayerPosRow() {
		return playerPosRow;
	}
	/**
	 * Get the player's column position on the 2D array/game board
	 * @return player's column position
	 */
	public int getPlayerPosCol() {
		return playerPosCol;
	}
	
	/**
	 * Resets player position upon a new game being started
	 */
	public void resetPlayerPos() {
		playerPosCol=0;
		playerPosRow=10;
	}

}
