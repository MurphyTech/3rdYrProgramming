/* 
 * David Murphy
 * 12493252
 * d.murphy53@nuigalway.ie
 */
package assign5;

import java.util.Comparator;

public class RationalComparator implements Comparator<Rational> {
		@Override
		public int compare(Rational o1, Rational o2) {
			// ascending order (descending order would be: o2.compareTo(o1);
			return o1.compareTo(o2);
		}
}
