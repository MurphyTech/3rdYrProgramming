/* 
 * David Murphy
 * 12493252
 * d.murphy53@nuigalway.ie
 */
package assign5;

//IMPORT
import java.math.BigInteger;

public class Rational implements Comparable<Rational>{
	//DECLARE GLOBAL VALUES
	private long numerator;
	private long denominator;
	
	public Rational(){
		this(0,1);
	}

	//constructors
	public Rational(long numerator,long denominator){
		if (denominator == 0){
			throw new IllegalArgumentException("denominator cannot be Zero!!!");
		}

		int div = numerator == 0 ? 1 : getGCD(numerator,denominator);
		this.numerator = numerator/div;
		this.denominator = numerator == 0 ? 1 : denominator/div; 
	}

	//GETTERS
	public long getNumerator(){
		return this.numerator;
	}
	public long getDenominator(){
		return this.denominator;
	}
	
	//Setters
	public void setNumerator(long n){
		numerator = n;
	}
	
	public void setDenominator(long d){
		denominator = d;
	}


	//add a second rational number to this rational number
	public Rational addition(Rational secRat){
		long n = numerator * secRat.getDenominator() + denominator * secRat.getNumerator();
		long d = denominator * secRat.getDenominator();
		return new Rational(n,d);
	}

	//subtract a second rational number from this rational number
	public Rational subtraction(Rational secRat){
		long n = numerator * secRat.getDenominator() - denominator * secRat.getNumerator();
		long d = denominator * secRat.getDenominator();
		return new Rational(n,d);
	}

	//divide a second rational number from this one
	public Rational divide(Rational secRat){
		long n = numerator * secRat.getDenominator();
		long d = denominator * secRat.getNumerator();
		return new Rational(n,d);
	}

	//multiply a second rational number by this rational number
		public Rational multiply(Rational secRat){
		long n = numerator * secRat.getNumerator();
		long d = denominator * secRat.getDenominator();
		return new Rational(n,d);
	}

	//get the greatest common divisor
	private static int getGCD(long a, long b) {
   		BigInteger b1 = BigInteger.valueOf(a);
    	BigInteger b2 = BigInteger.valueOf(b);
    	BigInteger gcd = b1.gcd(b2);
    	return gcd.intValue();
	}
	
	public Rational checkRational(Rational chkRat){
		if (chkRat == null){
			throw new NullPointerException("Must be a non-null value");
		}	
		return chkRat;
		
	}

	@Override
	//maybe this could be changed???
	public int compareTo(Rational otherRat){
		checkRational(otherRat);
		Rational difference = subtraction(otherRat);
		if (difference.numerator > 0){
			return 1;
		}
		if (difference.numerator < 0){
			return -1;
		}
		return 0;
	}

	//toString
	public String toString(){
		if (denominator == 1){
			
			//System.out.println(numerator + "");
			return numerator + "";
		}
		else 
			//System.out.println(numerator + "/" + denominator);
			return numerator + "/" + denominator;
	}



}
