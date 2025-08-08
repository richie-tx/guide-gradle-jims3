/*
 * Created on Nov 11, 2003
 *
 */
package mojo.km.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mojo.km.exceptionhandling.ParseRuntimeException;

/**
 * @author dgibler
 * 
 * Utility class used to work with Date objects
 */
public class DateUtil
{
    public static final String FILENAME_DATE_FMT = "yyyyMMddHHmm";
    
    public static final String LOG_DATE_FMT = "yyyyMMdd HH:mm:ss";
    
    public static final String DATE_FMT_1 = "MM/dd/yyyy";

    public static final String DATE_FMT_MONTH_ONLY_FULL_NAME = "MMMM";

    public static final String DATETIME_FMT_1 = "MM/dd/yyyy hh:mm";

    public static final String DATETIME24_FMT_1 = "MM/dd/yyyy HH:mm";

    public static final String DATETIME24LOOSE_FMT_1 = "M/d/yyyy HH:mm";

    public static final String TIME24_FMT_1 = "HH:mm";

    public static final String TIME_FMT_1 = "hh:mm";

    public static final String DEFAULT_DATE_FMT = DATE_FMT_1;
    
    public static final SimpleDateFormat logFormat = new SimpleDateFormat(LOG_DATE_FMT);

    /**
     * Returns current date as a Date
     * 
     * @return java.util.Date
     * @post currDate != null
     */
    public static Date getCurrentDate()
    {
        Calendar currentCal = Calendar.getInstance();
        Date currDate = currentCal.getTime();
        return currDate;
    }
    
    /**
     * Returns current date as a Date
     * 
     * @return java.util.Date
     * @post currDate != null
     */
    public static String getCurrentDateString(String pattern)
    {
        SimpleDateFormat fmt = new SimpleDateFormat(pattern);
        return fmt.format(new Date());
    }
    
    public static String getLogDateString()
    {
        return logFormat.format(new Date());
    }

    /**
     * Converts a String date in MMDDYY format to a Date object.
     * 
     * @param aStringDate
     * @return java.util.Date
     * @pre aStringDate != null
     * @pre aStringDate.length() == 6
     * @deprecated
     */
    public static Date convertMMDDYY(String aStringDate)
    {
        SimpleDateFormat lDateFormatter = new SimpleDateFormat("MMddyy");
        lDateFormatter.setLenient(false);
        Date lDate = null;

        try
        {
            Integer testInt = new Integer(aStringDate);
            lDate = lDateFormatter.parse(aStringDate);
        }
        catch (ParseException e)
        {
        }
        catch (NumberFormatException e)
        {
        }
        return lDate;
    }
    
    public static Date stringToDate(String dateString, String pattern) throws ParseRuntimeException
    {
        Date date = null;
        if (dateString != null && !dateString.equals(""))
        {
            SimpleDateFormat fmt = new SimpleDateFormat(pattern);

            try
            {
                date = fmt.parse(dateString);
            }
            catch (ParseException e)
            {
                throw new ParseRuntimeException(e.getMessage());
            }
        }
        return date;
    }

    public static String dateToString(Date date, String pattern)
    {
        String dateString;

        if (date == null)
        {
            dateString = null;
        }
        else
        {
            SimpleDateFormat fmt = new SimpleDateFormat(pattern);
            dateString = fmt.format(date);
        }

        return dateString;
    }

    /**
     * Compares date1 to date2 for time sequence order. date1 &lt; date2; and a value greater than 0
     * if date1 &gt; date2
     * 
     * @author Jim Fisher
     * 
     * @param date1
     * @param date2
     * @param format
     *            date format to compare against
     * @returns the value 0 if date1 equals date2; value less than 0 if
     */
    static public int compare(Date date1, Date date2, String format)
    {
        int result = 0;
        String dateString1 = DateUtil.dateToString(date1, format);
        String dateString2 = DateUtil.dateToString(date2, format);
        date1 = DateUtil.stringToDate(dateString1, format);
        date2 = DateUtil.stringToDate(dateString2, format);
        result = date1.compareTo(date2);
        return result;
    }

    /**
	 * Extracts a string time in the format HH:MM:SS from a java.util.Date
	 * @param inDate
	 * @return String
	 * @pre inDate != null
	 * @pre inDate instanceof java.util.Date
	 * @post aTime != null
	 * @post aTime instanceof String
	 * @post aTime.length() == 8
	 * @deprecated
	 */
    public static String getHHMMSSWithColonFromDate(Date inDate)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setLenient(false);
		calendar.setTime(inDate);
		Integer hourInt = new Integer(calendar.get(Calendar.HOUR_OF_DAY));
		Integer minuteInt = new Integer(calendar.get(Calendar.MINUTE));
		Integer secondInt = new Integer(calendar.get(Calendar.SECOND));
		StringBuffer hhmmss = new StringBuffer();
		if (hourInt.toString().length() < 2)
		{
			hhmmss.append(0);
		}
		hhmmss.append(hourInt);
		hhmmss.append(":");
		if (minuteInt.toString().length() < 2)
		{
			hhmmss.append(0);
		}
		hhmmss.append(minuteInt);
		hhmmss.append(":");
		if (secondInt.toString().length() < 2)
		{
			hhmmss.append(0);
		}
		hhmmss.append(secondInt);
		return hhmmss.toString();
	}

}
