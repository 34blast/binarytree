/**
 * 
 */
package rmscott.test;

/**
 * @author rmscott
 *
 */
public class BinaryCount {
	static final int ZERO = 0;
	static final int ONE = 1;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for( int idx = 0; idx < 16; idx++) {
			System.out.print(idx);
			System.out.print(" value = ");
			int value = ONE << idx;
			System.out.println(value);
		}
	}

}
