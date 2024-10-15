/**
 * Records numbers in relation to the lottery system
 * 
 * @author Meixin Zhan
 * @version February 2024
 */
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class Lottery {
	private Set<Integer> lotNum;
	
	/**
	 * Default Constructor
	 */
	public Lottery() {
		lotNum=new HashSet<Integer>();
	}
	
	/**
	 * Constructor
	 */
	public void setLot() {
		lotNum=new HashSet<Integer>();
	}
	
	/**
	 * Adds a number to a set
	 * @param num number being added to set
	 */
	public void addLotNum(int num) {
		lotNum.add(num);
	}
	
	/**
	 * Gets the lottery number
	 * @return set containing lottery numbers
	 */
	public Set<Integer> getLotNum() {
		return lotNum;
	}
	
	/**
	 * Displays set of numbers
	 * @param set set of numbers
	 */
	public void printSet(Set<Integer> set) {
		System.out.println(set);
	}
	

}
