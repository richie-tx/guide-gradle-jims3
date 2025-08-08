/*
 * Created on Feb 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.km.scheduling;

import java.util.Calendar;
import java.util.Date;

import mojo.km.scheduling.ISchedule;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestSchedule implements ISchedule
{
/**
 * @param firstNotificationDate
 * @return Date currentDate
 *  */
	public Date getNextRunDate(Date firstNotificationDate) {

		Calendar firstNotificationCalendar = Calendar.getInstance();
		firstNotificationCalendar.setTime(firstNotificationDate);		
		Calendar currentDateCalendar = Calendar.getInstance();
		currentDateCalendar.set(Calendar.HOUR, firstNotificationCalendar.get(Calendar.HOUR));
		currentDateCalendar.set(Calendar.MINUTE, firstNotificationCalendar.get(Calendar.MINUTE));
		currentDateCalendar.set(Calendar.SECOND, firstNotificationCalendar.get(Calendar.SECOND));
		currentDateCalendar.set(Calendar.MILLISECOND, firstNotificationCalendar.get(Calendar.MILLISECOND));
		
		int inc = 5;
		currentDateCalendar.add(Calendar.MINUTE, inc);
		return currentDateCalendar.getTime();
	}
	/**
	 * @param runDate
	 * @return boolean runDateStillValid
	 */
	public boolean isRunDateStillValid(Date runDate) {
		return true;
	}

}
