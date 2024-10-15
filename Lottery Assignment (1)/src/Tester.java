/**
 * A simplified lottery simulation
 * 
 * @author Meixin Zhan
 * @version February 2024
 */
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Tester {
	
	private int LOTTERY_MAX;
	private Lottery lot;
	private Lottery user;
	
	/**
	 * Default constructor
	 */
	public Tester(){
		LOTTERY_MAX=100;
		lot=new Lottery();	
		user=new Lottery();
	}
	
	public static void main(String[] args) {
		Tester start=new Tester();
		start.menu();
	}
	
	/**
	 * Constructor
	 */
	public void setMax() {
		LOTTERY_MAX=100;
	}
	
	 /**
     * Menu for user
     */
    public void menu() {
        boolean exit=false;
        do {
            System.out.println("Hello! Please enter a number to select an option:");
            System.out.println("1. Do a standard lottery");
            System.out.println("2. Do a lottery with a selected maximum");
            System.out.println("3. Do a chosen number of weeks's lottery");
            System.out.println("4. Close the program");
            
            Scanner choice = new Scanner(System.in);
            String userChoice=choice.nextLine();
            
            setMax();

            switch(userChoice){
            case "1": 
            	standardLot();
            	System.out.println("-----------");
            break;
            case "2": 
            	rangeLot();
            	System.out.println("-----------");
            break;
            case "3":
            	System.out.println("Please input a number of weeks you wish to use for your lottery.");
            	String weeks=getUserNum();
            	boolean check=checkInt(weeks);
            	
            	while (check!=true) {
            		System.out.println("Invalid input, please re-enter your desired number of weeks for your lottery.");
            		weeks=getUserNum();
            		check=checkInt(weeks);
            	}
            	
            	if (check!=false && Integer.parseInt(weeks)<1) {
            		check=false;
            		while (check!=true) {
                		System.out.println("Invalid input, please re-enter your desired number of weeks for your lottery.");
                		weeks=getUserNum();
                		check=checkInt(weeks);
                		if (check!=false && Integer.parseInt(weeks)<1) {
                			check=false;
                		}
                	}
            	}
            	
            	weekLot(weeks);
            	System.out.println("-----------");
           break;
            case "4":
                System.out.println("Exited the program. Thank you for your time.");
                exit=true;
            break;
            default:
            	System.out.println("Not a recognised option, please try again.");
            break;
            }
        }
        while(exit!=true);
    }
	
    /**
     * Performs lottery for a user selected maximum/range
     */
	public void rangeLot() {
		Set<Integer> temp = new HashSet<Integer>();
        lot.setLot();
        user.setLot();
		
		addUserRange();
		while(LOTTERY_MAX<6) {
			System.out.println("Incorrect input format, please try again.");
			addUserRange();
		}
		addLotNum();
		
		addUserNum();
		
		lot.printSet(lot.getLotNum());
		user.printSet(user.getLotNum());
		
    	while(user.getLotNum().size()<6) {
        	System.out.println("Incorrect input format, please try again.");
        	addUserNum();
        }
		
		temp=compareSet(lot.getLotNum(), user.getLotNum());
		
		int win=calcWinnings(temp);
		System.out.println("Winnings: £"+win);
	}
	
	/**
	 * Performs a standard default lottery
	 */
	public void standardLot() {
        Set<Integer> temp = new HashSet<Integer>();
        lot.setLot();
        user.setLot();
        
    	addLotNum();
		
		addUserNum();
		lot.printSet(lot.getLotNum());
		user.printSet(user.getLotNum());
		
    	while(user.getLotNum().size()<6) {
        	System.out.println("Incorrect input format, please try again.");
        	addUserNum();
        }
		
		temp=compareSet(lot.getLotNum(), user.getLotNum());
		
		int win=calcWinnings(temp);
		System.out.println("Winnings: £"+win);
	}

	/**
	 * Performs a lottery over the week
	 */
	public void weekLot(String userSelect) {
        Set<Integer> temp = new HashSet<Integer>();
        lot.setLot();
        user.setLot();
        
        int win=0;
        int weeks=Integer.parseInt(userSelect);
    	
		addUserNum();
		
		user.printSet(user.getLotNum());
		
    	while(user.getLotNum().size()<6) {
        	System.out.println("Incorrect input format, please try again.");
        	addUserNum();
        }
		
    	for (int i=0; i<weeks; i++) {
    		lot=new Lottery();
    		addLotNum();
    		lot.printSet(lot.getLotNum());
    		temp=compareSet(lot.getLotNum(), user.getLotNum());
    		
    		win=win+calcWinnings(temp);
    	}
    	
		System.out.println("Winnings: £"+win);
		
		if (win>2) {
			System.out.println("You made profit!");
		}
		else {
			System.out.println("You did not make any money.");
		}
	}
	
	/**
	 * Asks for user input for lottery numbers and adds to a set
	 */
	public void addUserNum() {
		boolean integer;
		
		System.out.println("Please enter your 6 lottery number choice in format 0,0,0,0,0,0 (replacing 0s with integers)");
		System.out.println("NOTE: if you enter more than 6 values, only the first 6 will be selected.");
		String[] split = getUserNum().split(",");
		
		for(int i=0;i<6; i++){
            integer=checkInt(split[i]);
    		if (split.length<6) {
    			integer=false; //makes sure set has at least 6 numbers
    		}
            
    		//makes sure numbers inputed are higher than lottery max
    		if(integer!=false && Integer.parseInt(split[i])>LOTTERY_MAX) {
    			System.out.println("Invalid input, higher than max lottery number.");
    			integer=false;
    		}
    		
            if (integer!=false) {
            	user.addLotNum(Integer.parseInt(split[i]));
            }
            else if (integer!=true){
            	System.out.println("Incorrect input format, please try again.");
        		System.out.println("Please enter your 6 lottery number choice in format 0,0,0,0,0,0 (replacing 0s with integers)");
        		System.out.println("NOTE: if you enter more than 6 values, only the first 6 will be selected.");
            	split=getUserNum().split(",");
            	i=-1;
            }
            
		}
	}
	
	/**
	 * Generates lottery numbers randomly and stores
	 */
	public void addLotNum() {
		int gen;
		while(lot.getLotNum().size()<6) {
			gen=generateLotteryNum(LOTTERY_MAX);
			lot.addLotNum(gen);
		}
	}
	
	/**
	 * Asks user to enter a desired max number for the lottery
	 */
	public void addUserRange() {
		System.out.println("Please enter a selected range you wish to use for the lottery.");
		
		System.out.println("Please enter the maximum you wish to use for the lottery:");
		String rangeMax=getUserNum();
		
		boolean checkMax=checkInt(rangeMax);
		boolean checkMin=checkInt(rangeMax);
		
		while(checkMax!=true) {
			System.out.println("Please enter a VALID selected maximum you wish to use for the lottery:");
			rangeMax=getUserNum();
			checkMax=checkInt(rangeMax);
		}

		LOTTERY_MAX=Integer.parseInt(rangeMax);
	}
	
	/**
	 * Generates random number
	 * @param choiceMin minimum number possible
	 * @param choiceMax maximum number possible
	 * @return random number generated between range
	 */
	public int generateLotteryNum(int choiceMax) {
		Random ran=new Random();
		
		int roll=ran.nextInt(1,choiceMax+1);
		
		return roll;
	}
	
	/**
	 * Asks for and receives user input of lottery numbers
	 * @return user inputed lottery numbers
	 */
	public String getUserNum() {
		Scanner n=new Scanner(System.in);

		String input=n.nextLine();
		
		return input;
	}
	
	/**
	 * Checks for integer
	 * @param s input of string to check if it can be converted
	 * @return boolean of if string is convertible
	 */
	public boolean checkInt(String s) {
		boolean check=true;
        
        try {
          	 Integer.parseInt(s);
           }
           catch (NumberFormatException e){
           	check=false;
           }
        
		return check;
	}
	
    
    /**
     * Performs an intersection and compares lottery and user set numbers together
     * @param lotSet
     * @param userSet
     */
    public Set<Integer> compareSet(Set<Integer> lotSet, Set<Integer> userSet) {
        // create a temporary set to store the result of the intersection
        Set<Integer> temp = new HashSet<Integer>();
        Lottery test=new Lottery();
        
        // add / copy the entire contents of one set to the temporary set
        temp.addAll(lotSet);
        
        // apply the retainAll method to the contents of the temporary set, intersecting it
        // with the contents of another set
        temp.retainAll(userSet);
        
        System.out.println("Matched:");
        test.printSet(temp);
        return temp;
    }
    
    /**
     * Calculates winnings of one set
     * @param set set of numbers from user that matched the lottery generated set
     * @return winning amount of money
     */
    public int calcWinnings(Set<Integer> set) {
    	int size=set.size();
    	int winnings=0;
    	
    	System.out.println(size);
    	
    	if(size==3) {
    		winnings=25;
    	}
    	else if (size==4) {
    		winnings=100;
    	}
    	else if (size==5) {
    		winnings=1000;
    	}
    	else if (size==6) {
    		winnings=1000000;
    	}
    	
    	return winnings;
    }
}
