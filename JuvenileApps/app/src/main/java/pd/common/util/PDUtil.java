/*
 * Created on Jul 23, 2007
 *
 */
package pd.common.util;

import java.text.DecimalFormat;

import naming.UIConstants;

/**
 * @author dgibler
 *
 */
public class PDUtil {
	/**
	 * See Description for formatCurrency(String aValue, String aCurrencyMask,
	 * boolean blanksToZero)
	 * 
	 * @param aValue
	 * @param aCurrencyMask
	 * @return
	 */
	public static String formatCurrency(Double aValue, String aCurrencyMask) {
		if (aCurrencyMask == null || aCurrencyMask.equals("")) {
			aCurrencyMask = UIConstants.CURRENCY_US_DEFAULT_POSITIVE_FORMAT;
		}
		DecimalFormat myFormatter = new DecimalFormat(aCurrencyMask);
		String output = myFormatter.format(aValue);
		return output;
	}

	public static String removeLeadingZeros(String str)
	{
	 if (str == null)
	 {
	    return null;
	 }
	 char[] chars = str.toCharArray();
	 int index = 0;
	 for (; index < str.length(); index++)
	 {
	  if (chars[index] != '0')
	  {
	   break;
	  }
	 }
	 return (index == 0) ? str : str.substring(index);
	}

}
