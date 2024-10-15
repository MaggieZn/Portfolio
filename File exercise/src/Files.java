/**
 * A collection of file managing exercises
 * 
 * @author Meixin Zhan
 * @version 1.0
 */
import java.io.*;
import java.util.Scanner;

public class Files {
	/**
	 * Reads from a file and catches errors on file being read
	 * @param fileName file name to read from based on user input
	 */
	public void readFromFile(String fileName) {
		String read;
		FileReader fileRead = null;
        BufferedReader bufferedRead = null;
        
		try {
	        fileRead = new FileReader(fileName);
	        
	        bufferedRead = new BufferedReader(fileRead);
	        read=bufferedRead.readLine();
	        while (read!=null) {
	        	System.out.println(read);
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
	 * Checks file exists and is readable
	 * @param name name of file to check
	 * @return result of whether file is readable and exists
	 */
	public boolean fileSearch(String name){
		File fileExist = new File(name);
		boolean check=false;
		
		if (fileExist.exists()) {
			check=fileExist.canRead();
		}
		
		return check;
	}
	/**
	 * Writing a user input to a file
	 * @param file name of file to write to
	 */
	public void writeToFile(String file) {
		FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        String fileContent;
		boolean write=true;
		
		try {
	        outputStream=new FileOutputStream(file);
	        printWriter=new PrintWriter(outputStream);
	        
	        //keeps looping and getting user input until input is empty
			while (write!=false){
				System.out.println("Input file content:");
				Scanner content = new Scanner(System.in);
				fileContent=content.nextLine();
				if (fileContent.equals("")) {
					write=false;
					break;
				}
				//writes input to file
				printWriter.println(fileContent);
			}
		}
		//error writing to file
	    catch (IOException e) {
	    	System.out.println("Error, a problem occured opening or writing to a file.");
	    }
	    finally {
	    	if (printWriter != null) {
	    		printWriter.close();
	        }
	    }
			
	}
	
	/**
	 * Copying content of one file to another
	 * @param fileNameIn the input file; for content within file to be copied
	 * @param fileNameOut the output file; for copying content to/the destination of copied content
	 */
	public void fileCopy(String fileNameIn, String fileNameOut){
		//file read set up
		FileReader fileRead = null;
        BufferedReader bufferedRead = null;
        String nextLine;
        //file write to set up
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
		
		try {
			fileRead = new FileReader(fileNameIn);
	        
	        bufferedRead = new BufferedReader(fileRead);
	        
	        outputStream=new FileOutputStream(fileNameOut);
	        printWriter=new PrintWriter(outputStream);
	        nextLine=bufferedRead.readLine();
	        //until there is no line left to read, will repeat loop to read and write/copy to desired files
	        while (nextLine!=null) {
	        	System.out.println(nextLine);
	        	printWriter.println(nextLine);
	        	nextLine=bufferedRead.readLine();
	        }
		}
		//file not found
		catch (FileNotFoundException e){
			System.out.println("Error, your file was not found.");
		}
		//error with copying to file
		catch(IOException e){
			System.out.println("Error, a problem occured copying your file.");
		}
		//closing both files
		finally {
			if (bufferedRead!=null && printWriter!=null){
				try {
					bufferedRead.close();
					printWriter.close();
				}
				//closing file error
				catch (IOException e){
					System.out.println("Error, problem closing the file.");
				}
			}	
		}
	}
	
	/**
	 * Deciphering mystery.txt
	 */
	public void decipherMystery() {
		String cipher="mystery.txt";
		
		
		//file read set up
		FileReader fileRead = null;
        BufferedReader bufferedRead = null;
        //file write to set up
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
		
		try {
			fileRead = new FileReader(cipher);
	        
	        bufferedRead = new BufferedReader(fileRead);
	        
	        outputStream=new FileOutputStream("decipher.txt");
	        printWriter=new PrintWriter(outputStream);
	        cipher=bufferedRead.readLine();
	        //until there is no line left to read, will repeat loop to read and write/copy to desired files
	        while (cipher!=null) {
	        	String decipher=cipherDecipherString(cipher);
	        	System.out.println(decipher);
	        	printWriter.println(decipher);
	        	cipher=bufferedRead.readLine();
	        }
		}
		//file not found
		catch (FileNotFoundException e){
			System.out.println("Error, mystery.txt  was not found.");
		}
		//error with copying to file
		catch(IOException e){
			System.out.println("Error, a problem occured deciphering the file.");
		}
		//closing both files
		finally {
			if (bufferedRead!=null && printWriter!=null){
				try {
					bufferedRead.close();
					printWriter.close();
				}
				//closing file error
				catch (IOException e){
					System.out.println("Error, problem closing the file.");
				}
			}	
		}
	}
	/**
	 * Copied encipher.txt
	 */
	private static final String crypt1 = "cipherabdfgjk";
	private static final String crypt2 = "lmnoqstuvwxyz";
	/**
	 * method to encipher and decipher a given String using parallel arrays (crypt1 & crypt2)
	 *
	 * @param text A String containing text that is to be enciphered or deciphered
	 * @return A new String containing the result, e.g. the en/deciphered version of the String provided as an input
	 */
	private static String cipherDecipherString(String text)
	{
	    // declare variables we need
	    int i, j;
	    boolean found = false;
	    String temp="" ; // empty String to hold converted text

	    for (i = 0; i < text.length(); i++) // look at every character in text
	    {
	        found = false;
	        if ((j = crypt1.indexOf(text.charAt(i))) > -1) // is char in crypt1?
	        {           
	            found = true; // yes!
	            temp = temp + crypt2.charAt(j); // add the cipher character to temp
	        } 
	        else if ((j = crypt2.indexOf(text.charAt(i))) > -1) // and so on
	        {
	            found = true;
	            temp = temp + crypt1.charAt(j);
	        }

	        if (! found) // to deal with cases where char is NOT in crypt2 or 2
	        {
	            temp = temp + text.charAt(i); // just copy across the character
	        }
	    }
	    return temp;
	}
	
	/**
	 * Using details.txt to calculate an average score for competitors
	 */
	public void averageScore(){
		//variable for average
		float average=0;
		//variable to store file name
		String fileName="details.txt";
		//variables for reading file
		String nextLine;
		FileReader fileRead = null;
        BufferedReader bufferedRead = null;
        //variables for writing to an output file
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        
		try {
	        fileRead = new FileReader(fileName);
	        bufferedRead = new BufferedReader(fileRead);
	        
	        outputStream=new FileOutputStream("detailsAverage.txt");
	        printWriter=new PrintWriter(outputStream);
	        
	        nextLine=bufferedRead.readLine();
	        while (nextLine!=null) {
	        	//storing current line as an array
	        	String[] details=nextLine.split(" ");
	        	
	        	//calculating average
	        	for (int i=2; i<details.length; i++) {
	        		float score=Integer.parseInt(details[i]);
	        		average=average+score;
	        	}
	        	//printing results to screen
	        	System.out.println(details[1]+", "+details[0]+": Average score is "+String.format("%.2f", average/details.length));
	        	//writing results to output file
	        	printWriter.println(details[1]+", "+details[0]+": Average score is "+String.format("%.2f", average/details.length));
	        	//read next line
	        	nextLine=bufferedRead.readLine();
	        }
		}
		// File not found/doesn't exist
        catch (FileNotFoundException e) {
        	System.out.println("Error, details.txt was not found.");
        }
		//File closing error
		catch(IOException e) {
			System.out.println("Error, problem closing files.");
		}
		finally {
			if (bufferedRead!=null && printWriter!=null){
				try {
					bufferedRead.close();
					printWriter.close();
				}
				//closing file error
				catch (IOException e){
					System.out.println("Error, problem closing the file.");
				}
			}
		}
	}
	
	public void twoDArray(){
		int row=3;
		int col=4;
		int[][] array=new int[row][col];
		
		for(int rowCount=0; rowCount<row; rowCount++) {
			for (int colCount=0; colCount<col; colCount++) {
				array[rowCount][colCount]=rowCount+colCount;
			}
		}
		
		FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        
		try {
	        outputStream=new FileOutputStream("array.txt");
	        printWriter=new PrintWriter(outputStream);
	        
	        //writing to file
	        for(int rowCount=0; rowCount<row; rowCount++) {
	        	if (rowCount!=0) {
	        		printWriter.println();
	        	}
				for (int colCount=0; colCount<col; colCount++) {
					printWriter.print(array[rowCount][colCount]+" ");
				}
			}
		}
		//error writing to file
	    catch (IOException e) {
	    	System.out.println("Error, a problem occured opening or writing to a file.");
	    }
	    finally {
	    	if (printWriter != null) {
	    		printWriter.close();
	        }
	    }
	}
}
