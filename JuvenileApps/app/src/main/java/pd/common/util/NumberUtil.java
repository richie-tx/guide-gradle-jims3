/*
 * Created on Jan 31, 2007
 *
 */
package pd.common.util;

/**
 * @author dgibler
 *  
 */
public final class NumberUtil {
	/**
	 *  
	 */
	/**
	 * @param aString
	 * @return
	 */
	public static boolean isaNumber(String aString) {
		boolean isaNumber = false;
		try {
			Integer anInteger = new Integer(aString);
			isaNumber = true;
		} catch (NumberFormatException e) {
		}
		return isaNumber;
	}

	/**
	 * @param aString
	 * @return
	 */
	public static Integer getIntegerFromString(String aString) {
		Integer anInteger = new Integer(0);
		try {
			anInteger = new Integer(aString);
		} catch (NumberFormatException e) {
		}
		return anInteger;
	}

	private NumberUtil() {
		super();
	}
}
