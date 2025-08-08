/*
 * Created on Nov 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.common.calendar;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CalendarUtil
{
	private CalendarUtil() {}
	
	/**
	 * Converts a given date from a given timezone date time to a target
	 * timezone date and time.
	 * @param fromTimeZone
	 * @param targetTimeZone
	 * @param date
	 * @return date
	 */	
	public static Date convertDate(String fromTimeZone, String targetTimeZone, Date date) {
		TimeZone from = TimeZone.getTimeZone(fromTimeZone);
		TimeZone target = TimeZone.getTimeZone(targetTimeZone);
		return CalendarUtil.convertDate(from, target, date);
	}
	
	/**
	 * Converts a given date from a given timezone date time to a target
	 * timezone date and time.
	 * @param fromTimeZone
	 * @param targetTimeZone
	 * @param date
	 * @return date
	 */
	public static Date convertDate(TimeZone fromTimeZone, TimeZone targetTimeZone, Date date) {
		Calendar fromTZ = Calendar.getInstance(fromTimeZone);
		Calendar targetTZ = Calendar.getInstance(targetTimeZone);
		int fromGMT = fromTZ.get(Calendar.ZONE_OFFSET);
		int target = targetTZ.get(Calendar.ZONE_OFFSET);
		
		int difference = target - fromGMT;
		
		targetTZ.setTime(date);
		targetTZ.add(Calendar.MILLISECOND, difference);
		
		return targetTZ.getTime();
		
	}
	
	public static Date addOrSubstractDaysFromDate(Date aDate,int noOfDays) {
		Calendar calendar = Calendar.getInstance(); //get the calendar instance
		calendar.setTime(aDate);//set it to today
		calendar.add(Calendar.DATE, noOfDays);// this is where the adding || subtracting happens
		return calendar.getTime();
		}
	
	public static Date addOrSubstractYearsFromDate(Date aDate,int noOfYears) {
		Calendar calendar = Calendar.getInstance(); //get the calendar instance
		calendar.setTime(aDate);//set it to today
		calendar.add(Calendar.YEAR, noOfYears);// this is where the adding || subtracting happens
		return calendar.getTime();
		}

}
