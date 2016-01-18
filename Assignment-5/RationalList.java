/* 
 * David Murphy
 * 12493252
 * d.murphy53@nuigalway.ie
 */
package assign5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RationalList {
	
	public static void main(String[] args){
		//Create ArrayList
		ArrayList<Rational> List = new ArrayList<Rational>();
		
		//Assign 10 list values
		List.add(new Rational(4,9));
		List.add(new Rational(1,1));
		List.add(new Rational(45,469));
		List.add(new Rational(894,123));
		List.add(new Rational(7,21));
		List.add(new Rational(8,89));
		List.add(new Rational(1001,9009));
		List.add(new Rational(404,999));
		List.add(new Rational(1,2));
		List.add(new Rational(3,6));
		
		//Printing ArrayList as Entered
		System.out.println("List as Entered\n");
		for (Rational counter: List ){
			System.out.println(counter);
		}
		System.out.println("\n");
		
		//Shuffle
		Collections.shuffle(List);
		//Printing Shuffled ArrayList
		System.out.println("After Shuttle & Before Sorting\n");
		for (Rational counter: List ){
			System.out.println(counter);
		}
		System.out.println("\n");
		
		
		//New Sort
		Collections.sort(List, new RationalComparator());
		//Printing New Sorted list
		System.out.println("After Sorting\n");
		for (Rational counter: List ){
			System.out.println(counter);
		}
		System.out.println("\n");

		//Call for sorted list
		Collections.sort(List, new RationalComparator());
		sort(List);
		
		//Printing original sorted list
		System.out.println("After Sorting\n");
		for (Rational counter: List ){
			System.out.println(counter);
		}
		System.out.println("\n");
		
		//binarySearch
		searchByFraction(List);
	}//END MAIN
	
	public static void sort(ArrayList<Rational> Array){
		//Define ints
		int i,j;
	    for (i = 1; i < Array.size(); i++) {
	    	//CREATE & DEFINE TEMP RATIONAL VALUE NAMED KEY
	        Rational key =  (Rational) Array.get(i);        
	        j = i;
	        //WHILE KEY IS GREATER THAN LAST ARRAY VALUE
	        while(j > 0 && (Array.get(j-1).compareTo(key) > 0)) {
	        	//SET VALUE INTO ARRAY
	            Array.set(j,Array.get(j-1));
	            j--;
	        }//END WHILE
	        Array.set(j,key);
	    }//END FOR
	    Array.toString();
	    System.out.println("\nFinished :) ");
	}//END SORT()

	//binarySearch method
	public static int searchByFraction(ArrayList<Rational> List){
		System.out.println("\n\n\nStarting binarySearch()\n\n\n");
		//start scanner for user input
		Scanner in = new Scanner(System.in);
		//prompting and assigning values
		System.out.println("Enter Numerator");
		int n = in.nextInt();
		System.out.println("Enter Denominator");
		int d = in.nextInt();
		//close scanner
		in.close();
		//make user fraction into rational object
		Rational r1 = new Rational(n,d);
		//binarySearch
		int index = Collections.binarySearch(List, r1);
		//printing appropriate message
		if (index >= 0) {
			System.out.println("Rational " + r1 + " was found at ArrayList index " + index );
		}
		else {
			System.out.println("Rational " + r1 + " was not found in ArrayList");
		}
		
		return index;
	}
}//END RationalList CLASS
