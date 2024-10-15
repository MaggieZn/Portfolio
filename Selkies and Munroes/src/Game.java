import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Game open=new Game();

		open.userSelectMenu();

	}
	
	
	/**
	 * Method to display menu to user
	 */
	public void displayMenu() {
		System.out.println("Welcome to Selkies and Munroes! Please select one of the options below:");
		System.out.println("1. Start Game.");
		System.out.println("2. Load Game.");
		System.out.println("3. Help/Instructions.");
		System.out.println("0. Exit Game.");
	}
	/**
	 * Method for user to select from menu
	 */
	public void userSelectMenu() {
		String userChoice;
	    boolean exit=false; //allows looping until exit has been selected
	    
	    Board game=new Board();
		File file=new File();
	    do{
	    	displayMenu();
	    	Scanner choice = new Scanner(System.in);
		    userChoice=choice.nextLine();
		    
	    	switch(userChoice){
	    	case "1"://starts a new game
	    		System.out.println("Loading new game...");
	    		game.game();
		    break;
		    case "2":
		    	System.out.println("Pick a save to load:");
		    	file.saveFiles();
		    	
		    	String save;
		    	Scanner s = new Scanner(System.in);
			    save=s.nextLine();
		    	
		    	file.loadFile(save);
		    break;
		    case "3": //help/instructions
		    	help();
		    	System.out.println("------------"); //for readability
		    break;
		    case "0":
		    	System.out.println("Exiting game... Farewell!");
		        exit=true; //allows exit of loop
		    break;
		    default:
		    	System.out.println("Not a recognised choice, please pick a different option.");
		    break;
	    	}
	    }
	    while (exit!=true);
	}
	/**
	 * Help/instruction information
	 */
	public void help() {
    	System.out.println("How to play:");
    	System.out.println("- The rules are simple! Similar to snakes and ladders but the snakes are replaced by selkies and munroes!");
    	System.out.println("- When a player lands on a selkie's head, they slide down to the selkie's corresponding tail.");
    	System.out.println("- Selkies are represented on a board with an 'S' for the head and 's' for the tail, along with their corresponding number.");
    	System.out.println("- When a player lands on a munroe bottom, they climb up to the munroe's top!");
    	System.out.println("- Munroes are represented on a board with an 'M' for the top and 'm' for the bottom, along with their corresponding number.");
    	System.out.println("- There are hidden activity spaces on the board:");
    	System.out.println("	- A whiskey boost allows the player that lands on it to move 5 spaces!");
    	System.out.println("		- The space the player lands on with this boost will be played into affect (e.g. a munroe or selkie).");
    	System.out.println("- First player to the 100th tile (shown on the board) wins!");
    	System.out.println("- The round of the game will continue with any input, except for certain cases as listed below:");
    	System.out.println("	- To save mid-game, please enter 'save' without the surrounding commas as input instead");
    	System.out.println("	- To get a reminder on help mid-game, please enter 'help' without the surrounding commas as input instead.");
	}
}
