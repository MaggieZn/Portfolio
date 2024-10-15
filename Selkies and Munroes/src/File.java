import java.io.*;

public class File {
	/**
	 * Displaying save.txt to allow user to select a save file to continue
	 */
	public void saveFiles(){
		//variable to store file name
		String fileName="save.txt";
		//variables for reading file
		String nextLine;
		FileReader fileRead = null;
        BufferedReader bufferedRead = null;
        
		try {
	        fileRead = new FileReader(fileName);
	        bufferedRead = new BufferedReader(fileRead);
	        
	        nextLine=bufferedRead.readLine();
	        while (nextLine!=null) {
	        	//storing current line as an array
	        	String[] details=nextLine.split(",");
	        	
	        	//displaying save files to user
	        	System.out.println(details[0]);
	        	//read next line
	        	nextLine=bufferedRead.readLine();
	        }
		}
		// File not found/doesn't exist
        catch (FileNotFoundException e) {
        	System.out.println("Error, save.txt was not found.");
        }
		//File closing error
		catch(IOException e) {
			System.out.println("Error, problem closing files.");
		}
		finally {
			if (bufferedRead!=null){
				try {
					bufferedRead.close();
				}
				//closing file error
				catch (IOException e){
					System.out.println("Error, problem closing the file.");
				}
			}
		}
	}
	/**
	 * Makes sure there are no conflicting save names
	 */
	public boolean conflictSaves(String save){
		//variable to store file name
		String fileName="save.txt";
		//variables for reading file
		String nextLine;
		FileReader fileRead = null;
        BufferedReader bufferedRead = null;
        boolean conflict=false;
        
		try {
	        fileRead = new FileReader(fileName);
	        bufferedRead = new BufferedReader(fileRead);
	        
	        nextLine=bufferedRead.readLine();
	        while (nextLine!=null) {
	        	//storing current line as an array
	        	String[] details=nextLine.split(",");
	        	
	        	//compares for same save name
	        	if(details[0].equals(save)){
	        		conflict=true;
	        	}
	        	//read next line
	        	nextLine=bufferedRead.readLine();
	        }
		}
		// File not found/doesn't exist
        catch (FileNotFoundException e) {
        	System.out.println("Error, save.txt was not found.");
        }
		//File closing error
		catch(IOException e) {
			System.out.println("Error, problem closing files.");
		}
		finally {
			if (bufferedRead!=null){
				try {
					bufferedRead.close();
				}
				//closing file error
				catch (IOException e){
					System.out.println("Error, problem closing the file.");
				}
			}
		}
		return conflict;
	}
	/**
	 * Reads from save.txt and catches errors on file being read, will process saved player information
	 * @param save reads from save "file" indicated
	 */
	public void loadFile(String save) {
		String read;
		FileReader fileRead = null;
        BufferedReader bufferedRead = null;
        Board load=new Board();
        
		try {
	        fileRead = new FileReader("save.txt");
	        
	        bufferedRead = new BufferedReader(fileRead);
	        read=bufferedRead.readLine();
	        while (read!=null) {
	        	String[] details=read.split(",");
	        	
	        	if (details[0].equals(save)) {
	        		details=read.split(",");//using the save name to put the save information in an array
	        		
	        		//determines amount of players and array length for number of players
	        		int arrayLength=(details.length-1)/4;
	        		//save information divided into appropriate arrays
		        	String user[]=new String[arrayLength];
		        	String row[]=new String[arrayLength];
		        	String col[]=new String[arrayLength];
		        	String turns[]=new String[arrayLength];
		        	
	        		for (int j=0, i=1; j<arrayLength;j++, i=i+4) {
	        			user[j]=details[i];
		        		System.out.println("User: "+user[j]);
	        		}
	        		for (int j=0, i=2; j<arrayLength;j++, i=i+4) {
	        			row[j]=details[i];
		        		System.out.println("row: "+row[j]);
	        		}
	        		for (int j=0, i=3; j<arrayLength;j++, i=i+4) {
	        			col[j]=details[i];
		        		System.out.println("col: "+col[j]);
	        		}
	        		for (int j=0, i=4; j<arrayLength;j++, i=i+4) {
	        			turns[j]=details[i];
		        		System.out.println("turn: "+turns[j]);
	        		}
	        		//loads game with information from save
		        	load.game(user,row,col,turns, arrayLength);
					break;
	        	}
	        	read=bufferedRead.readLine();
	        }
		}
		// File not found/doesn't exist
        catch (FileNotFoundException e) {
        	System.out.println("Error, your file was not found.");
        }
		//File closing error
		catch(IOException e) {
			System.out.println("Error, problem closing the file");
		}
		finally {
			if (bufferedRead!=null){
				try {
					bufferedRead.close();
				}
				catch (IOException e){
					System.out.println("Error, problem closing the file");
				}
			}

		}
	}
	/**
	 * Saving each player's information to a save file
	 * @param user username of player
	 * @param col column position of player for 2D array purposes
	 * @param row row position of player for 2D array purposes
	 * @param turns the amount of turns that have passed
	 * @param name the name of the save file inputted by a player
	 */
	public void writeToFile(String[] user, String[] row, String[] col, String[] turns, String name) {
		FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
		FileReader fileRead = null;
        BufferedReader bufferedRead = null;
        String read;
		
		try {
	        fileRead = new FileReader("save.txt"); //reading save.txt
	        
	        bufferedRead = new BufferedReader(fileRead);
	        read=bufferedRead.readLine();//reads the first line of save.txt
	        
	        outputStream=new FileOutputStream("save.txt"); //writing destination
	        printWriter=new PrintWriter(outputStream);
	        
	        while (read!=null) {
	        	if (read!=null) {
	        		//reads from the save file and writes to it to avoid overwriting previous saves
		        	printWriter.println(read);
	        	}
	        	read=bufferedRead.readLine();
	        }
	        printWriter.print(name+",");
	        for (int i=0; i<user.length;i++) {
	        	//adding new save information
				printWriter.print(user[i]+","+ row[i]+"," + col[i]+","+ turns[i]+",");
	        }
		}
		// File not found/doesn't exist
        catch (FileNotFoundException e) {
        	System.out.println("Error, your file was not found.");
        }
		//error writing to file
	    catch (IOException e) {
	    	System.out.println("Error, a problem occured opening or writing to a file.");
	    }
	    finally {
	    	if (printWriter != null) {
	    		printWriter.close();
	        }
	    	if (bufferedRead!=null){
				try {
					bufferedRead.close();
				}
				catch (IOException e){
					System.out.println("Error, problem closing the file");
				}
			}
	    }
			
	}
}
