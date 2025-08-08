/*
 * Created on Apr 11, 2006
 *
 */
package pd.common.util;

import java.util.Date;

import mojo.km.utilities.DateUtil;

/**
 * @author dgibler
 *
 */
public class DateFormatter
{
	private static String ST = "st";
	private static String ND = "nd";
	private static String RD = "rd";
	private static String TH = "th";
	private static String D = "d";
	private static String MMMM = "MMMM";
	private static String YYYY = "yyyy";

	/**
	 * Returns month literal of a given date.
	 * @param aDate
	 * @return
	 */
	public static String getMonthLiteral(Date aDate)
	{
		String dateStr = DateUtil.dateToString(aDate, MMMM);
		return dateStr;
	}
	/**
	 * Returns the year of a given date.
	 * @param aDate
	 * @return
	 */
	public static String getYear(Date aDate)
	{
		String dateStr = DateUtil.dateToString(aDate, YYYY);
		return dateStr;
	}
	/**
	 * Returns day with proper suffix of a given date.
	 * @param day
	 * @return
	 */
	public static String getDayOfMonthWithSuffix(Date aDate)
	{
		String aDay = DateUtil.dateToString(aDate, D);
		Integer dayInteger = new Integer(aDay);
		int day = dayInteger.intValue();
		StringBuffer sbDay = new StringBuffer(dayInteger.toString());
		String dayString = dayInteger.toString();
		if (day > 10 && day < 20)
		{
			sbDay.append(TH);
		}
		else
		{
			if (dayString.length() > 1)
			{
				dayString = dayString.substring(1);
				dayInteger = new Integer(dayString);
				day = dayInteger.intValue();
			}
			switch (day)
			{
				case 1 :
					sbDay.append(ST);
					break;
				case 2 :
					sbDay.append(ND);
					break;
				case 3 :
					sbDay.append(RD);
					break;
				case 21 :
					sbDay.append(ST);
					break;
				case 31 :
					sbDay.append(ST);
					break;

				default :
					sbDay.append(TH);
			}
		}
		return sbDay.toString();
	}
	/**
	 * Returns day of a given date.
	 * @param day
	 * @return
	 */
	public static String getDayOfMonth(Date aDate)
	{
		String aDay = DateUtil.dateToString(aDate, D);
		Integer dayInteger = new Integer(aDay);
		int day = dayInteger.intValue();
		StringBuffer sbDay = new StringBuffer(dayInteger.toString());
		String dayString = dayInteger.toString();

		return dayString;
	}
	/**
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDayDiff(Date startDate, Date endDate)
	{
		long differenceInDays = 0;
		if (startDate != null && endDate != null)
		{
			long diff = endDate.getTime() - startDate.getTime();
			differenceInDays = diff / (1000 * 60 * 60 * 24);
		}
		Long aLong = new Long(differenceInDays);
		return aLong.intValue();
	}
	/**
	 * @param days
	 * @return
	 */
	public static int getYears(int days)
	{

		return (days / 365);
	}
	/**
	 * @param days
	 * @return
	 */
	public static int getMonths(int days)
	{
		return (days / 30);
	}
}