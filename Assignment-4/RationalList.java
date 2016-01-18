/* 
 * David Murphy
 * 12493252
 * d.murphy53@nuigalway.ie
 */
package assign4;

import java.util.ArrayList;

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
		
		//Call for sorted list
		sort(List);
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
}//END RationalList CLASS
