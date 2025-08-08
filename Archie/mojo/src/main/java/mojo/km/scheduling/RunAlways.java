package mojo.km.scheduling;

import java.util.Calendar;
import java.util.Date;

public class RunAlways implements ISchedule {

	public Date getNextRunDate(Date firstNotificationDate) {
		Calendar firstNotificationCalendar = Calendar.getInstance();
		firstNotificationCalendar.setTime(firstNotificationDate);
		Calendar currentDateCalendar = Calendar.getInstance();

		currentDateCalendar.set(Calendar.HOUR,firstNotificationCalendar.get(Calendar.HOUR));
		currentDateCalendar.set(Calendar.MINUTE,firstNotificationCalendar.get(Calendar.MINUTE));
		currentDateCalendar.set(Calendar.SECOND,firstNotificationCalendar.get(Calendar.SECOND));
		currentDateCalendar.set(Calendar.MILLISECOND,firstNotificationCalendar.get(Calendar.MILLISECOND));
		return currentDateCalendar.getTime();
	}

	public boolean isRunDateStillValid(Date runDate) {
		 return true;
	}

}
