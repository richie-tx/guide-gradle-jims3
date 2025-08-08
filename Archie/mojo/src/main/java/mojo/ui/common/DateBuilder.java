package mojo.ui.common;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * <B>DateBuilder</B> implements static methods to create and print date
 * objects.
 *
 * @author R. Ratliff
 * @modelguid {054256DB-E25C-40D1-9309-38A66ACE2786}
 */
public class DateBuilder
{
  /**
   * This method is used to convert a date string into a date instance. All
   * known string formats are of the form: month day year
   * @param dateString is a string representing a date. The string can
   * represent a date one of numerious formats.
   * @return a date instance built from the dateString. This method
   * can return null if the date string could not be parsed into 
   * one of its known formats.
   * @modelguid {7C9C8C9F-821D-4057-AB94-89EA37AAED17}
   */
  public static Date buildDateFromString(String dateString)
  {
    Date date = null;
    int i = 0;
    
    while (date == null && i < FORMATS.length)
    {
      FORMATS[i].setLenient(true);
      date = FORMATS[i++].parse(dateString, new ParsePosition(0));
    }
    
    return date;
  }
  
	/** @modelguid {FCC55AA0-8E57-4439-8E03-CA30883EE405} */
  public static boolean isToday(String dateString)
  {
    boolean isToday = false;
    Calendar someDay = new GregorianCalendar();
    someDay.setTime(buildDateFromString(dateString));
    Calendar today = new GregorianCalendar();
    if ((someDay.get(Calendar.DAY_OF_MONTH) 
         == today.get(Calendar.DAY_OF_MONTH))
        && (someDay.get(Calendar.MONTH) == today.get(Calendar.MONTH))
        && (someDay.get(Calendar.YEAR) == today.get(Calendar.YEAR)))
    {
      isToday = true;
    }
    
    return isToday;
  }
  
	/** @modelguid {95798DA4-0F9E-4C9D-B4A1-D77EABBA0143} */
  public static String formatEasternTime(String pattern, long date)
  {
    return formatEasternTime(pattern, new Date(date));
  }
  
	/** @modelguid {7AEEE1CA-8E6D-4BE4-8A5E-70B874113ACF} */
  public static String formatEasternTime(String pattern, Date date)
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
    Calendar calendar = getEasternTimeZoneCalendar();
    calendar.setTime(date);
    dateFormat.setCalendar(calendar);
    
    return dateFormat.format(date);
  }
  
	/** @modelguid {0C6C0AB2-DC74-4431-8BA2-5EFA0C4E9EFE} */
  public static StringBuffer formatEasternTime(String pattern,
                                               Date date,
                                               StringBuffer toAppendTo,
                                               FieldPosition pos)
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
    Calendar calendar = getEasternTimeZoneCalendar();
    calendar.setTime(date);
    dateFormat.setCalendar(calendar);
    
    return dateFormat.format(date, toAppendTo, pos);
  }
  
	/** @modelguid {87D41FEA-47CF-4618-9399-0A5AC260F0AA} */
  public static long adjustToMidnightEastern(long date)
  {
	  Calendar calendar = getEasternTimeZoneCalendar();
	  calendar.setTime(new Date(date));
	  calendar.set(Calendar.MILLISECOND, 0);
	  calendar.set(Calendar.SECOND, 59);
	  calendar.set(Calendar.MINUTE, 59);
	  calendar.set(Calendar.HOUR_OF_DAY, 23);
	  calendar.set(Calendar.AM_PM, Calendar.PM);
	  
	  return calendar.getTime().getTime();
  }
  
	/** @modelguid {0CCE668D-EAFB-4003-90C9-77E94E439422} */
  private static Calendar getEasternTimeZoneCalendar()
  {
    SimpleTimeZone tz = new SimpleTimeZone(EST_OFFSET, IDS[IDS.length - 1]);
    tz.setStartRule(Calendar.APRIL, AHEAD_ONE_HOUR, Calendar.SUNDAY, TWO_AM);
    tz.setEndRule(Calendar.OCTOBER, BACK_ONE_HOUR, Calendar.SUNDAY, TWO_AM);
   
    return new GregorianCalendar(tz);
  }
  
  // internal class constants.
  //
	/** @modelguid {3AEE3AC9-F0A7-4352-A96B-0B1309C66A92} */
  private static final int HOURS = 60 * 60 * 1000;
	/** @modelguid {04EC1408-BEBD-4AD5-9355-B554BBEB423C} */
  private static final int EST_OFFSET = -5 * HOURS;
	/** @modelguid {3DF23E2D-1CEA-4A88-8952-311DA9E04F9E} */
  private static final int TWO_AM = 2 * HOURS;
	/** @modelguid {06973C51-814F-48B1-95B8-EEDFFB9CA952} */
  private static final int AHEAD_ONE_HOUR = 1;
	/** @modelguid {04311EC5-8065-442A-8D69-772AA7EA142B} */
  private static final int BACK_ONE_HOUR = -1;
	/** @modelguid {A3163C78-E621-4DCC-9E83-659C36379281} */
  private static final String[] IDS = TimeZone.getAvailableIDs(EST_OFFSET);
	/** @modelguid {D98B97F0-8272-4935-B6E8-1EC917AADC12} */
  private static final DateFormat[] FORMATS 
                                = {new SimpleDateFormat("MMMM d yy"),
                                new SimpleDateFormat("MMMM d yyyy"),
                                new SimpleDateFormat("MMM d yy"),
                                new SimpleDateFormat("MMM d yyyy"),
                                new SimpleDateFormat("MM d yy"),
                                new SimpleDateFormat("MM d yyyy"),
                                new SimpleDateFormat("MMMM/d/yy"),
                                new SimpleDateFormat("MMMM/d/yyyy"),
                                new SimpleDateFormat("MMM/d/yy"),
                                new SimpleDateFormat("MMM/d/yyyy"),
                                new SimpleDateFormat("MM/d/yy"),
                                new SimpleDateFormat("MM/d/yyyy"),
                                new SimpleDateFormat("d-MMM-yy"),
                                new SimpleDateFormat("d-MMM-yyyy"),
                                new SimpleDateFormat("d-MMMM-yy"),
                                new SimpleDateFormat("d-MMMM-yyyy"),
                                DateFormat.getDateInstance(DateFormat.LONG)};
}