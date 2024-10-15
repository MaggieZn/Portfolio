/**
 * A menu to organise file handling exercises, options selected on user input
 * 
 * @author Meixin Zhan
 * @version 1.0
 */
import java.util.Scanner;

public class Menu {
	
	private Files menu;
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		Menu run=new Menu();
		run.userSelect();
	}
	public Menu(){
		menu=new Files();
	}
	
	/**
	 * Displays menu
	 */
	public void displayMenu(){
		System.out.println("Please select one of the options below:");
		System.out.println("1. Read from a file.");
		System.out.println("2. Write to a file.");
		System.out.println("3. Copy to a file from another file.");
		System.out.println("4. Decipher 'mystery.txt'");
		System.out.println("5. Calculate averages from 'details.txt'.");
		System.out.println("6. 2D Array exercise.");
		System.out.println("0. Exit.");
	}
	/**
	 * Allows user choice of menu
	 */
	public void userSelect() {
		String userChoice;
	    boolean exit=false; //allows looping until exit has been selected
	    do{
	    	displayMenu();
	    	Scanner choice = new Scanner(System.in);
		    userChoice=choice.nextLine();
		    
	    	switch(userChoice){
	    	case "1":
	    		//asks user to input file name
	    		String fileName;
	    		System.out.println("Please input your selected file name to read from:");
	    		Scanner name = new Scanner(System.in);
	    	    fileName=name.nextLine();
	    		
	    	    boolean check=menu.fileSearch(fileName);
	    	    //reads from a file if file is readable and exists
	    	    if (check!=true) {
	    	    	System.out.println("File can not be read.");
	    	    }
	    	    else {
	    	    	menu.readFromFile(fileName);
	    	    }
	    		
	    		System.out.println("----------"); //helps user readability
		    break;
		    case "2":
		    	//asks user to input file name
		    	String fileNameWrite;
	    		System.out.println("Please input your selected file name to write to:");
	    		Scanner nameOut = new Scanner(System.in);
	    	    fileNameWrite=nameOut.nextLine();
	    	    
	    	    boolean checkWrite=menu.fileSearch(fileNameWrite);
	    	    //writes to a file if file is readable and exists
	    	    if (checkWrite!=true) {
	    	    	System.out.println("File can not be read.");
	    	    }
	    	    else {
	    	    	menu.writeToFile(fileNameWrite);
	    	    }
	    	    
	    	    System.out.println("----------"); //helps user readability
		    break;
		    case "3":
		    	String fileNameOut;
		    	String fileNameIn;
		    	//asking which file to be copied
				System.out.println("Input file you'd like to copy:");
				Scanner in = new Scanner(System.in);
				fileNameIn=in.nextLine();
		    	
		    	//asking where the contents should be copied to
				System.out.println("Input file you'd like to copy to:");
				Scanner out = new Scanner(System.in);
				fileNameOut=out.nextLine();
				
				//checking file validity
				boolean checkCopy=menu.fileSearch(fileNameIn);
				checkCopy=menu.fileSearch(fileNameOut);
	    	    //copies to file if files are both valid
	    	    if (checkCopy!=true) {
	    	    	System.out.println("Error with a file name.");
	    	    }
	    	    else {
	    	    	menu.fileCopy(fileNameIn, fileNameOut);
	    	    }
		    	
		    	System.out.println("----------"); //helps user readability
		    break;
		    case "4":
		    	menu.decipherMystery();
		    	System.out.println("----------"); //helps user readability
		    break;
		    case"5":
		    	menu.averageScore();
		    	System.out.println("----------"); //helps user readability
		    break;
		    case "6":
		    	menu.twoDArray();
		    	System.out.println("----------"); //helps user readability
		    break;
		    case "0":
		    	System.out.println("Farewell!");
		        exit=true; //allows exit of loop
		    break;
		    default:
		    	System.out.println("Not a recognised choice");
		    break;
	    	}
	    }
	    while (exit!=true);
	}

}
