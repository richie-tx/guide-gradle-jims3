/*
 * Created on Sep 25, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mojo.km.scheduling;

/**
 * @author racooper
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
 
import java.util.*;
public class QuarterlySchedule implements ISchedule{


 


/**
*  MonthlySchedule calculates the next run date based on the FIRST run date.
*  The way it works: creates a Calendar based on today's date, and then resets the
*  hours, minutes, seconds and milliseconds of the calendar
*  to the first run timed date hours, minutes, seconds and milliseconds.
*  THEN 1 Month is added to the date.
*
* 	This insures that the next run time date will have the same hours, min, sec, mill sec. as the
*   first run time date.
*
*   THIS CODE ASSUME SAME TIMEZONE FOR ITS CALCULATIONS.
* */

 
    public Date getNextRunDate(Date firstNotificationDate) {

		Calendar firstNotificationCalendar = Calendar.getInstance();
		firstNotificationCalendar.setTime(firstNotificationDate);

		Calendar currentDateCalendar = Calendar.getInstance();

		currentDateCalendar.set(Calendar.HOUR,firstNotificationCalendar.get(Calendar.HOUR));
		currentDateCalendar.set(Calendar.MINUTE,firstNotificationCalendar.get(Calendar.MINUTE));
		currentDateCalendar.set(Calendar.SECOND,firstNotificationCalendar.get(Calendar.SECOND));
		currentDateCalendar.set(Calendar.MILLISECOND,firstNotificationCalendar.get(Calendar.MILLISECOND));

		currentDateCalendar.add(Calendar.MONTH, 3);

		return currentDateCalendar.getTime();

    }

    public boolean isRunDateStillValid( Date runDate ) {
        return true;
    }

}
