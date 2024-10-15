import java.util.Scanner;
import java.util.Random;

public class Board {
	
	private Player player[];
	private String board[][];
	private String selkieHead[];
	private String selkieTail[];
	private String munroeTop[];
	private String munroeBottom[];
	private String whiskeyBoost[];
	
	/**
	 * Starts the game
	 */
	public void game(){
		File save=new File();
		
		int turns=0;
		boolean game=true;
		String winner="";
		
		for(int i=0; i<player.length;i++) {
			//when a new game is made, resets any saved player positions and turns
			player[i].resetPlayerPos();
			player[i].setTurns(turns);
		}
		
		int num=numOfPlayers();
		
		do {
			System.out.println("");//for readability
			
			//testing player personal turns taken
			turns=turns+1;
			
			startRound(num, turns); //every round of the game
			
			//declaration of winners once a player wither crosses the 100th tile or lands on it
			for(int i=0;i<num;i++) {
				player[i].setTurns(turns);
				if (player[i].getPlayerPosRow()==1 && player[i].getPlayerPosCol()<=1){
					game=false;
					winner=player[i].getUsername();
				}
				else if(player[i].getPlayerPosRow()==0 && player[i].getPlayerPosCol()<=10) {
					game=false;
					winner=player[i].getUsername();
				}
			}
			String choice=option();
		    
			//save function for when user types in the word "save"
		    if (choice.equals("save")) {
		    	System.out.println("Saving game...");
		    	
	    		String username[]=new String[num];
	    		String col[]=new String[num];
		    	String row[]=new String[num];
		    	String turn[]=new String[num];
		    	for (int i=0; i<num; i++) {
		    		username[i]=player[i].getUsername();
		    		
		    		col[i]=Integer.toString(player[i].getPlayerPosCol());
		    		
		    		row[i]=Integer.toString(player[i].getPlayerPosRow());
		    		
		    		turn[i]=Integer.toString(player[i].getTurns());
		    	}
		    	//save file name
		    	System.out.println("Pick a name for your save file: ");
		    	System.out.println("(Please make sure it is a different name from existing save files.)");
		    	save.saveFiles(); //displays existing save names
		    	String name;
				Scanner n = new Scanner(System.in);
			    name=n.nextLine();
			    
			    //identifying saves with the same name
			    boolean conflict=save.conflictSaves(name);
			    while (conflict!=false) {
			    	System.out.println("Please make sure it is a different name from existing save files.");
			    	n = new Scanner(System.in);
				    name=n.nextLine();
				    conflict=save.conflictSaves(name);
			    }
			    
			    //writing to save.txt
		    	save.writeToFile(username, row, col, turn,name);
		    	game=false;
		    }
		    //when the player types "help" display instructions/help
		    else if(choice.equals("help")) {
		    	Game help=new Game();
		    	help.help();
		    }
			
		}
		while (game!=false); //stops game from running
		
		if (winner!="") { //declares winner if game as been stopped by a player winning
			System.out.println("");
			System.out.println("Turns taken: "+turns);
			System.out.println("You won the game! Congratulations "+winner+"!");
		}
	}
	/**
	 * Starts and continues a game that has been loaded
	 * @param user username of player(s)
	 * @param newRow row position of player(s)
	 * @param newCol column position of player(s)
	 * @param newTurns the amount of turns that have already been passed
	 * @param newNum number of players
	 */
	public void game(String[] user, String[] newRow, String[] newCol, String[] newTurns, int newNum){
		File save=new File();
		
		boolean game=true;
		String winner="";
		
		for(int i=0; i<newNum;i++) { //setting and recalling player information
			player[i].setTurns(Integer.parseInt(newTurns[i]));
			player[i].setUsername(user[i]);
			player[i].setPlayerPosRow(Integer.parseInt(newRow[i]));
			player[i].setPlayerPosCol(Integer.parseInt(newCol[i]));
		}
		
		int turns=player[0].getTurns(); //initiate turns to continue counting
		int num=newNum; //initiating number of players
		
		do {
			System.out.println("");//for readability
			
			//testing player personal turns taken
			turns=turns+1;
			
			startRound(num, turns); //every round of the game
			
			//declaration of winners once a player wither crosses the 100th tile or lands on it
			for(int i=0;i<num;i++) {
				player[i].setTurns(turns);
				if (player[i].getPlayerPosRow()==1 && player[i].getPlayerPosCol()<=1){
					game=false;
					winner=player[i].getUsername();
				}
				else if(player[i].getPlayerPosRow()==0 && player[i].getPlayerPosCol()<=10) {
					game=false;
					winner=player[i].getUsername();
				}
			}
			String choice=option();
		    
			//save function for when user types in the word "save"
		    if (choice.equals("save")) {
		    	System.out.println("Saving game...");
		    	
	    		String username[]=new String[num];
	    		String col[]=new String[num];
		    	String row[]=new String[num];
		    	String turn[]=new String[num];
		    	for (int i=0; i<num; i++) {
		    		username[i]=player[i].getUsername();
		    		
		    		col[i]=Integer.toString(player[i].getPlayerPosCol());
		    		
		    		row[i]=Integer.toString(player[i].getPlayerPosRow());
		    		
		    		turn[i]=Integer.toString(player[i].getTurns());
		    	}
		    	//save file name
		    	System.out.println("Pick a name for your save file: ");
		    	System.out.println("(Please make sure it is a different name from existing save files.)");
		    	save.saveFiles(); //displays existing save names
		    	String name;
				Scanner n = new Scanner(System.in);
			    name=n.nextLine();
			    
			    //identifying saves with the same name
			    boolean conflict=save.conflictSaves(name);
			    while (conflict!=false) {
			    	System.out.println("Please make sure it is a different name from existing save files.");
			    	n = new Scanner(System.in);
				    name=n.nextLine();
				    conflict=save.conflictSaves(name);
			    }
			    
			    //writing to save.txt
		    	save.writeToFile(username, row, col, turn,name);
		    	game=false;
		    }
		    //when the player types "help" display instructions/help
		    else if(choice.equals("help")) {
		    	Game help=new Game();
		    	help.help();
		    }
			
		}
		while (game!=false); //stops the game
		
		if (winner!="") { //for when a winner is declared by reaching the 100th tile or exceeding it
			System.out.println("");
			System.out.println("Turns taken: "+turns);
			System.out.println("You won the game! Congratulations "+winner+"!");
		}
	}
	/**
	 * Default constructor
	 */
	public Board() {
		player=new Player[6];
		for (int i=0; i<player.length;i++) {
			player[i]=new Player();
		}
		board=new String[11][11];
		selkieHead=new String[7];
		selkieTail=new String[7];
		munroeTop=new String[7];
		munroeBottom=new String[7];
		whiskeyBoost=new String[7];
	}
	
	/**
	 * Starts a round of the game
	 */
	public void startRound(int num, int turns) {
		setBoard();
		getBoard();
		
		setSelkiePos();
		setMunroePos();
		
		int nums=num;
		
		if (turns==1) { //when it's the first round, the game will ask for each player's desired username
			for (int i=0; i<nums; i++) {
				System.out.println("Player "+(i+1));
				player[i].setUsername(selectUser());
			}
			setWhiskeyBoost(); //sets whiskey boost locations
		}
		
		setTokenSymbol();

		for (int i=0; i<nums; i++) {
			System.out.println("Player "+(i+1)+" ("+player[i].getUsername()+")'s turn:");
			movePlayer(rollDice(),i); //moving player in accordance to dice rolls
			
			for (int j=0; j<whiskeyBoost.length;j++) { //when player lands on whiskey boost
				if (board[player[i].getPlayerPosRow()][player[i].getPlayerPosCol()]==whiskeyBoost[j]) {
					
					System.out.println("You landed on a whiskey boost!");
					movePlayer(5,i); //moving 5 spaces on whiskey boost
				}
			}
		}
		
		
		//for when two players are in the same space
		for (int i=0; i<nums; i++) {
			for(int j=0; j<nums;j++) {
				if(i==j) {
					//when the loop number i matches the player number
					continue;
				}
				if (player[j].getPlayerPosCol()==player[i].getPlayerPosCol() && player[j].getPlayerPosRow()==player[i].getPlayerPosRow()) {
					board[player[j].getPlayerPosRow()][player[j].getPlayerPosCol()]=player[j].getToken()+player[i].getToken();
				}
			}
		}
		
		displayBoard();
	}
	
	/**
	 * Setting the board grid
	 */
	public void setBoard() {
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board.length; j++) {
				board[i][j]="----";
				if(i==0) {
					board[i][j]="   ";
				}
				if(j==0) {
					int reverse=-(j+i-10);
					board[i][j]=Integer.toString(reverse);
				}
			}
		}
		
		//setting certain numbers on the board to aid in visual reference for players
		board[1][1]=" 100";
		board[1][10]=" 091";
		board[10][1]=" 001";
		board[9][10]=" 011";
		board[8][1]=" 021";
		board[7][10]=" 031";
		board[6][1]=" 041";
		board[5][10]=" 051";
		board[4][1]=" 061";
		board[3][10]=" 071";
		board[2][1]=" 081";
		
		board[0][0]="10"; //not part of board, for the grid to further aid visual reference
	}
	/**
	 * Setting selkie head
	 */
	public void setSelkieHead(){
		for (int i=0; i<selkieHead.length; i++) {
			selkieHead[i]=" S"+(i+1)+" ";
		}
	}
	/**
	 * Setting selkie tail
	 */
	public void setSelkieTail() {
		for (int i=0; i<selkieTail.length; i++) {
			selkieTail[i]=" s"+(i+1)+" ";
		}
	}
	/**
	 * Setting selkie locations on game board
	 */
	public void setSelkiePos() {
		setSelkieHead();
		setSelkieTail();
		getSelkieHead();
		getSelkieTail();
		
		//plotting selkie tail locations
		board[10][10]=selkieTail[0];
		board[10][6]=selkieTail[1];
		board[7][6]=selkieTail[2];
		board[9][3]=selkieTail[3];
		board[8][4]=selkieTail[4];
		board[5][5]=selkieTail[5];
		board[3][3]=selkieTail[6];
		
		//plotting selkie head locations
		board[6][9]=selkieHead[0];
		board[6][5]=selkieHead[1];
		board[5][8]=selkieHead[2];
		board[4][2]=selkieHead[3];
		board[2][8]=selkieHead[4];
		board[1][6]=selkieHead[5];
		board[1][4]=selkieHead[6];
	}
	/**
	 * Setting munroe top
	 */
	public void setMunroeTop(){
		for (int i=0; i<munroeTop.length; i++) {
			munroeTop[i]=" M"+(i+1)+" ";
		}
	}
	/**
	 * Setting munroe bottom
	 */
	public void setMunroeBottom() {
		for (int i=0; i<munroeBottom.length; i++) {
			munroeBottom[i]=" m"+(i+1)+" ";
		}
	}
	/**
	 * Setting munroe positions on game board
	 */
	public void setMunroePos() {
		setMunroeTop();
		setMunroeBottom();
		getMunroeTop();
		getMunroeBottom();
		
		//setting munroe bottom locations
		board[10][1]=munroeBottom[0];
		board[10][4]=munroeBottom[1];
		board[10][8]=munroeBottom[2];
		board[8][8]=munroeBottom[3];
		board[8][1]=munroeBottom[4];
		board[5][10]=munroeBottom[5];
		board[3][10]=munroeBottom[6];
		
		//setting munroe top locations
		board[6][3]=munroeTop[0];
		board[9][7]=munroeTop[1];
		board[8][10]=munroeTop[2];
		board[3][5]=munroeTop[3];
		board[6][2]=munroeTop[4];
		board[4][7]=munroeTop[5];
		board[1][9]=munroeTop[6];
	}
	/**
	 * Set activity space whiskey boost position on board
	 */
	public void setWhiskeyBoost() {
		Random col=new Random();
		Random row=new Random();
		
		int min=1, max=10; //for 2D array integer limit
		boolean clash=false; //for when there is a clash in activity tile and aid/obstacle tile
		
		for(int i=0; i<whiskeyBoost.length; i++) { //generate random position on board for number of whiskey boosts
			int newCol=col.nextInt(min,max+1);
			int newRow=row.nextInt(min,max+1);
			
			whiskeyBoost[i]=board[newRow][newCol];
			
			for(int j=0; j<selkieHead.length; j++) {
				if (whiskeyBoost[i]==selkieHead[j]) { //for when there is a clash in selkie head
					clash=true;
					while(clash!=false) {
						newCol=col.nextInt(min,max+1);
						newRow=row.nextInt(min,max+1);
						
						whiskeyBoost[i]=board[newRow][newCol];
						
						if(whiskeyBoost[i]==munroeBottom[j]) { //for when there is a clash in munroe bottom
							newCol=col.nextInt(min,max+1);
							newRow=row.nextInt(min,max+1);
							
							whiskeyBoost[i]=board[newRow][newCol];
						}
						j=0; //reset counter in case there's a new generated clash
						if (whiskeyBoost[i]!=selkieHead[j] && whiskeyBoost[i]!=munroeBottom[j]) {
							clash=false; //clash resolved, end loop
						}
					}
				}
			}
		}
	}
	
	/**
	 * Get the board grid
	 * @return board grid
	 */
	public String[][] getBoard() {
		return board;
	}
	/**
	 * Get selkie head
	 * @return selkie head
	 */
	public String[] getSelkieHead() {
		return selkieHead;
	}
	/**
	 * Get selkie tail
	 * @return selkie tail
	 */
	public String[] getSelkieTail() {
		return selkieTail;
	}
	/**
	 * Get munroe top
	 * @return munroe top
	 */
	public String[] getMunroeTop() {
		return munroeTop;
	}
	/**
	 * Get munroe bottom
	 * @return munroe bottom
	 */
	public String[] getMunroeBottom() {
		return munroeBottom;
	}
	
	/**
	 * Display board grid
	 */
	public void displayBoard() {
		for (int i=0; i<board.length; i++) {
			if (i!=0) {
				System.out.println(" ");
			}
			for (int j=0; j<board.length; j++) {
				if(i+j==0) {
					System.out.print("|"+board[0][0]+"|");
				}
				else if(i==0) {
					System.out.print("");
				}
				else {
					System.out.print("|"+board[i][j]+"|");
				}
			}
		}
	}
	/**
	 * Allows player to select their own username
	 * @return user player's selected username
	 */
	public String selectUser() {
		String user;
		Scanner choice = new Scanner(System.in);
		System.out.println("Please input a username:");
	    user=choice.nextLine();
		return user;
	}
	
	/**
	 * Setting the token symbol for each player
	 */
	public void setTokenSymbol() {
		String token="P";
		for (int i=0; i<player.length; i++) {
			token="P"+(i+1);
			player[i].setToken(token);
		}
	}
	
	/**
	 * Rolls dice to allow player to move however many spaces between 1-6
	 * @return dice number rolled
	 */
	public int rollDice() {
		Random ran=new Random();
		
		//makes sure the dice is 6-sided
		int min=1, max=6;
		int dice=ran.nextInt(min,max+1);
		
		String roll=Integer.toString(dice);
		System.out.println("You rolled: "+roll);
		
		return dice;
	}
	
	/**
	 * Calculates player movement and updates board as well as player information accordingly
	 * @param move spaces player will move according to dice roll
	 * @param num to indicate which player is moving based on number of players
	 */
	public void movePlayer(int move, int num) {
		int currentRow=player[num].getPlayerPosRow();
		
		//if the movement exceeds the array's limit and is even
		if(player[num].getPlayerPosCol()+move>10 && currentRow%2==0) {
			//remainder of movement calculated
			int remainder=player[num].getPlayerPosCol()+move-10;
			//go up one row
			currentRow=currentRow-1;
			//set player position
			player[num].setPlayerPosRow(currentRow);
			player[num].setPlayerPosCol(board.length-remainder);
			selkieTile(num);
			munroeTile(num);
			
			//moving player
			board[player[num].getPlayerPosRow()][player[num].getPlayerPosCol()]=" "+player[num].getToken()+" ";
			//set player position
		}
		//else if the row is odd
		//spaces to move "back" to replicate snakes and ladders movement
		else if(player[num].getPlayerPosCol()-move<1 && currentRow%2!=0) {
			//remainder of movement calculated
			int remainder=(player[num].getPlayerPosCol()-move)*(-1);
			//go up one row
			currentRow=currentRow-1;
			//set player position
			player[num].setPlayerPosRow(currentRow);
			player[num].setPlayerPosCol(remainder+1);
			//checks if that the tile landed is a selkie head or munroe bottom tile
			selkieTile(num);
			munroeTile(num);
			
			//moving player
			board[player[num].getPlayerPosRow()][player[num].getPlayerPosCol()]=" "+player[num].getToken()+" ";
		}
		else {
			//if the current row is even and not the 10th row (the first row on the board)
			if(currentRow!=10 && currentRow%2==0) {
				player[num].setPlayerPosCol(player[num].getPlayerPosCol()+move);
				selkieTile(num);
				munroeTile(num);
				
				board[player[num].getPlayerPosRow()][player[num].getPlayerPosCol()]=" "+player[num].getToken()+" ";
			}
			else if(currentRow==10) {
				//only applied if its the first row
				player[num].setPlayerPosCol(player[num].getPlayerPosCol()+move);
				selkieTile(num);
				munroeTile(num);
				
				board[player[num].getPlayerPosRow()][player[num].getPlayerPosCol()]=" "+player[num].getToken()+" ";
			}
			else {
				//for when the current row is odd and not the first row on the board (the most bottom one/10th row)
				player[num].setPlayerPosCol(player[num].getPlayerPosCol()-move);
				selkieTile(num);
				munroeTile(num);
				
				board[player[num].getPlayerPosRow()][player[num].getPlayerPosCol()]=" "+player[num].getToken()+" ";
			}
		}
		
	}
	/**
	 * If the player lands on a munroe bottom tile, player will be moved accordingly
	 * @param num to indicate which player is moving
	 */
	public void munroeTile(int num) {
		if (player[num].getPlayerPosRow()==10 && player[num].getPlayerPosCol()==1) {
			//setting player's new position
			player[num].setPlayerPosRow(6);
			player[num].setPlayerPosCol(9);
			System.out.println("You climbed Munroe 1!");
		}
		else if(player[num].getPlayerPosRow()==10 && player[num].getPlayerPosCol()==4) {
			player[num].setPlayerPosRow(9);
			player[num].setPlayerPosCol(7);
			System.out.println("You climbed Munroe 2!");
		}
		else if(player[num].getPlayerPosRow()==10 && player[num].getPlayerPosCol()==8) {
			player[num].setPlayerPosRow(8);
			player[num].setPlayerPosCol(10);
			System.out.println("You climbed Munroe 3!");
		}
		else if(player[num].getPlayerPosRow()==8 && player[num].getPlayerPosCol()==8) {
			player[num].setPlayerPosRow(3);
			player[num].setPlayerPosCol(5);
			System.out.println("You climbed Munroe 4!");
		}
		else if(player[num].getPlayerPosRow()==8 && player[num].getPlayerPosCol()==1) {
			player[num].setPlayerPosRow(6);
			player[num].setPlayerPosCol(2);
			System.out.println("You climbed Munroe 5!");
		}
		else if(player[num].getPlayerPosRow()==5 && player[num].getPlayerPosCol()==10) {
			player[num].setPlayerPosRow(4);
			player[num].setPlayerPosCol(7);
			System.out.println("You climbed Munroe 6!");
		}
		else if(player[num].getPlayerPosRow()==1 && player[num].getPlayerPosCol()==9) {
			player[num].setPlayerPosRow(4);
			player[num].setPlayerPosCol(7);
			System.out.println("You climbed Munroe 7!");
		}
	}
	/**
	 * If the player lands on a selkie head tile, player will be moved down accordingly
	 * @param num to indicate which player is moving
	 */
	public void selkieTile(int num) {
		if (player[num].getPlayerPosRow()==6 && player[num].getPlayerPosCol()==9) {
			player[num].setPlayerPosRow(10);
			player[num].setPlayerPosCol(10);
			System.out.println("Uh oh! You slid down Selkie 1!");
		}
		else if(player[num].getPlayerPosRow()==6 && player[num].getPlayerPosCol()==5) {
			player[num].setPlayerPosRow(10);
			player[num].setPlayerPosCol(6);
			System.out.println("Uh oh! You slid down Selkie 2!");
		}
		else if(player[num].getPlayerPosRow()==5 && player[num].getPlayerPosCol()==8) {
			player[num].setPlayerPosRow(7);
			player[num].setPlayerPosCol(6);
			System.out.println("Uh oh! You slid down Selkie 3!");
		}
		else if(player[num].getPlayerPosRow()==4 && player[num].getPlayerPosCol()==2) {
			player[num].setPlayerPosRow(9);
			player[num].setPlayerPosCol(3);
			System.out.println("Uh oh! You slid down Selkie 4!");
		}
		else if(player[num].getPlayerPosRow()==2 && player[num].getPlayerPosCol()==8) {
			player[num].setPlayerPosRow(8);
			player[num].setPlayerPosCol(4);
			System.out.println("Uh oh! You slid down Selkie 5!");
		}
		else if(player[num].getPlayerPosRow()==1 && player[num].getPlayerPosCol()==6) {
			player[num].setPlayerPosRow(5);
			player[num].setPlayerPosCol(5);
			System.out.println("Uh oh! You slid down Selkie 6!");
		}
		else if(player[num].getPlayerPosRow()==1 && player[num].getPlayerPosCol()==4) {
			player[num].setPlayerPosRow(3);
			player[num].setPlayerPosCol(3);
			System.out.println("Uh oh! You slid down Selkie 7!");
		}
	}
	/**
	 * Asks for number of players and checks number of players is not out of range
	 * @return number of players
	 */
	public int numOfPlayers() {
		String input;
		int num;
		boolean isNum=true;
		Scanner choice = new Scanner(System.in);
		System.out.println("Please input number of players (2-6):");
	    input=choice.nextLine();
	    
	    try { //catches a non-integer input
	        Integer.parseInt(input);
	    } catch (final NumberFormatException e) {
	    	isNum=false;
	    }
	    while (isNum!=true) { //whilst there isn't an integer input
	    	choice = new Scanner(System.in);
			System.out.println("Error, please input number of players (2-6):");
			input=choice.nextLine();
			
		    try {
		        Integer.parseInt(input);
		    } 
		    catch (final NumberFormatException e) {
		    	isNum=false;
		    	continue;
		    }
		    isNum=true;
	    }
	    
	    num=Integer.parseInt(input);
	    
	    while (num>6 || num<2) { //for when the number exceeds or is under the player amount
	    	System.out.println("Not valid number of players, please have a number of players between 2-6:");
	    	input=choice.nextLine();
		    try { //catches a non-integer input
		        Integer.parseInt(input);
		    } catch (final NumberFormatException e) {
		    	isNum=false;
		    }
		    while (isNum!=true) { //whilst there isn't an integer input
		    	choice = new Scanner(System.in);
				System.out.println("Error, please input number of players (2-6):");
				input=choice.nextLine();
				
			    try {
			        Integer.parseInt(input);
			    } 
			    catch (final NumberFormatException e) {
			    	isNum=false;
			    	continue;
			    }
			    isNum=true;
		    }
		    num=Integer.parseInt(input);
	    }
		return num;
	}
	
	
	/**
	 * Allows player/s to save game midway or pause/wait to roll dice again and continue
	 * @return player input command
	 */
	public String option() {
		String choice;
		
		System.out.println("");//for readability
		
		Scanner c=new Scanner(System.in);
	    choice=c.nextLine();
	    
	    return choice;
	}
}
