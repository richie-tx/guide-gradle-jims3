/*
 * Created on Apr 4, 2006
 *
 */
package pd.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author dgibler
 *
 */
public final class NumberFormatter
{
	/**
	 * 
	 */
	private NumberFormatter()
	{
		super();
	}

	/**
	 * Returns a number as String as currency with the given number of decimal places.
	 * @param unformattedNum
	 * @param numberOfDecimalPlaces
	 * @return
	 */
	public static String getCurrencyFromString(String unformattedNum, int numberOfDecimalPlaces)
	{
		String formattedNum = "";

		if (unformattedNum != null && !unformattedNum.equals(""))
		{
			try
			{
				BigDecimal aNum = new BigDecimal(unformattedNum);
				BigDecimal bNum = aNum.movePointLeft(numberOfDecimalPlaces);
				NumberFormat nf = DecimalFormat.getCurrencyInstance();
				nf.setMinimumFractionDigits(numberOfDecimalPlaces);
				formattedNum = nf.format(bNum);
			}
			catch (NumberFormatException e)
			{
				formattedNum = "";
			}
		}
		return formattedNum;
	}
	/**
	 * Returns a number as String with the given number of decimal places.
	 * @param unformattedNum
	 * @param numberOfDecimalPlaces
	 * @param useGrouping
	 * @return
	 */
	public static String getNumberFromString(String unformattedNum, int numberOfDecimalPlaces, boolean useGrouping)
	{
		String formattedNum = "";

		if (unformattedNum != null && !unformattedNum.equals(""))
		{
			try
			{
				BigDecimal aNum = new BigDecimal(unformattedNum);
				BigDecimal bNum = aNum.movePointLeft(numberOfDecimalPlaces);
				NumberFormat nf = DecimalFormat.getNumberInstance();
				nf.setMinimumFractionDigits(numberOfDecimalPlaces);
				nf.setGroupingUsed(useGrouping);
				formattedNum = nf.format(bNum);
			}
			catch (NumberFormatException e)
			{
				formattedNum = "";
			}
		}
		return formattedNum;
	}
	/**
	 * Returns a number as currency
	 * @param unformattedNum
	 * @return
	 */
	public static String getCurrencyFromString(String unformattedNum)
	{
		String formattedNum = NumberFormatter.getCurrencyFromString(unformattedNum, 0);
		return formattedNum;
	}
	/**
	 * Returns a number as currency
	 * @param unformattedNum
	 * @return
	 */
	public static String getNumberFromString(String unformattedNum)
	{
		String formattedNum = NumberFormatter.getNumberFromString(unformattedNum, 0, true);
		return formattedNum;
	}

	/**
	 * Returns a number in a given format with a given number of decimal places.
	 * @param aPattern
	 * @param unformattedNum
	 * @param numberOfDecimalPlaces
	 * @return
	 */
	public static String getNumberFromString(String aPattern, String unformattedNum, int numberOfDecimalPlaces)
	{
		String formattedNum = "";

		if (unformattedNum != null && !unformattedNum.equals(""))
		{
			try
			{
				BigDecimal aNum = new BigDecimal(unformattedNum);
				BigDecimal bNum = aNum.movePointLeft(numberOfDecimalPlaces);
				DecimalFormat myFormatter = new DecimalFormat(aPattern);
				formattedNum = myFormatter.format(bNum);
			}
			catch (NumberFormatException e)
			{
				formattedNum = "";
			}
		}
		return formattedNum;
	}
	/**
	 * Returns a number in a given format with a given number of decimal places.
	 * @param aPattern
	 * @param unformattedNum
	 * @return
	 */
	public static String getNumberFromString(String aPattern, String unformattedNum)
	{
		String formattedNum = NumberFormatter.getNumberFromString(aPattern, unformattedNum, 0);
		return formattedNum;

	}
}
