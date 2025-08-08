package mojo.km.scheduling;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import mojo.km.utilities.DateUtil;

/**
 * a sample class that shows how to use XML file to configure the scheduler
 *  
 */
public class SampleXMLSchedule implements ISchedule
{

    /*
     * NOTE: I used the word Date to refer to a date such as a java date or a string such as
     * 01/01/2003 .. I used the word Day to refer to a given day in a week, such as Monday, Tuesday
     * ... etc
     */

    /**
     * an attribute holds a date format
     */
    private String theDateFormat = "MM/dd/yyyy";

    /**
     * Checks if the date that was previously was scheduled is still valid.
     * 
     * @param runDate
     *            the run date
     * @return boolean true if the date is still valid. and false otherwise.
     */
    public boolean isRunDateStillValid(Date runDate)
    {
        XMLHelper lHelper = new XMLHelper("mySchedule.xml");
        boolean result = true;
        // check for blackout date
        for (int i = 1; i <= lHelper.getNodeCount("blackoutDate"); i++)
        {
            Map lMap = lHelper.getNodeAttributes("blackoutDate", i);
            String blackoutDate = (String) lMap.get("date");
            if (isDateBlackoutDate(runDate, blackoutDate))
            {
                result = false;
            }
        }

        // check for blackoutDay
        for (int i = 1; i <= lHelper.getNodeCount("blackoutDay"); i++)
        {
            Map lMap = lHelper.getNodeAttributes("blackoutDay", i);
            String blackoutDay = (String) lMap.get("day");
            if (isDayBlackoutDay(runDate, blackoutDay))
            {
                result = false;
            }
        }
        return result;
    }

    /**
     * Returns the next run date for this app CONTRACT: a NULL return means that the scheduler to
     * STOP.
     * 
     * @param firstNotificationDate
     *            the first time the scheduler ran
     * @return Date the next run date.
     */
    public Date getNextRunDate(Date firstNotificationDate)
    {
        XMLHelper lHelper = new XMLHelper("mySchedule.xml");

        // check for full termination
        // i.e no need for further notification any more.
        if (lHelper.getAttributeValue("fullTermination", 1, "value").equalsIgnoreCase("true"))
        {
            return null;
        }

        // Calculate the next run date
        Date nextRunDate = null;
        String incrumentValue = lHelper.getAttributeValue("normalNotification", 1, "day");
        int incHrs = Integer.parseInt(incrumentValue) * 24;
        nextRunDate = getNextRunDate(firstNotificationDate, incHrs);
        boolean tryAgain = false;

        // we will need to loop incase the new date is on a weekend, and the
        // next date on the weekend put us on a blackout date. Hence tryAgain
        // is only updated if a blackout Day is found.
        do
        {
            tryAgain = false;
            // check if the next run date is a blackout date
            for (int i = 1; i <= lHelper.getNodeCount("blackoutDate"); i++)
            {
                Map lMap = lHelper.getNodeAttributes("blackoutDate", i);
                String blackoutDate = (String) lMap.get("date");
                if (isDateBlackoutDate(nextRunDate, blackoutDate))
                {
                    // calculate new next run date
                    String dateString = (String) lMap.get("newDate");
                    Date newDate = DateUtil.stringToDate(dateString, theDateFormat);
                    nextRunDate = updateDate(nextRunDate, newDate);
                    break;
                }
            }

            // check for blackoutDay
            for (int i = 1; i <= lHelper.getNodeCount("blackoutDay"); i++)
            {
                Map lMap = lHelper.getNodeAttributes("blackoutDay", i);
                String blackoutDay = (String) lMap.get("day");
                if (isDayBlackoutDay(nextRunDate, blackoutDay))
                {
                    // calculate new next run date
                    String dayIncStr = (String) lMap.get("newDay");
                    int dayInc = Integer.parseInt(dayIncStr);
                    int dayIncInHrs = dayInc * 24;
                    nextRunDate = getNextRunDate(nextRunDate, dayIncInHrs);
                    tryAgain = true;
                    break;
                }
            }
        }
        while (tryAgain);
        return nextRunDate;
    }

    /**
     * Returns true if a String day (Monday, Tuesday...etc) is specified in the XML file as a
     * blackout date
     * 
     * @param baseDate
     *            the date to check
     * @param aDay
     *            the day to check
     * @return boolean true if the day is specified in the xml file as a blackout day
     */
    private boolean isDayBlackoutDay(Date baseDate, String aDay)
    {
        String theDay = DateUtil.dateToString(baseDate, "EEEE");
        return theDay.equalsIgnoreCase(aDay);
    }

    /**
     * Returns a date such that the hours, minutes, seconds, and milliseconds is equal to the base
     * date. where the Year, Month and Day is from the newDate
     * 
     * @param baseDate
     *            the base date to use for hrs, min, sec, and mill sec.
     * @param newDate
     *            the date to use for the rest of teh date
     * @return Date the new date
     *  
     */
    private Date updateDate(Date baseDate, Date newDate)
    {
        // we need to update the hrs, min, sec, and mills for the new date
        Calendar baseDateCalendar = Calendar.getInstance();
        baseDateCalendar.setTime(baseDate);

        Calendar newDateCalendar = Calendar.getInstance();
        newDateCalendar.setTime(newDate);
        newDateCalendar.set(Calendar.HOUR, baseDateCalendar.get(Calendar.HOUR));
        newDateCalendar.set(Calendar.MINUTE, baseDateCalendar.get(Calendar.MINUTE));
        newDateCalendar.set(Calendar.SECOND, baseDateCalendar.get(Calendar.SECOND));
        newDateCalendar.set(Calendar.MILLISECOND, baseDateCalendar.get(Calendar.MILLISECOND));

        return newDateCalendar.getTime();
    }

    /**
     * Returns true if a String date (such as 01/01/2003...etc) is specified in the XML file as a
     * blackout date
     * 
     * @param baseDate
     *            the date to check
     * @param otherDate
     *            the date to check
     * @return boolean true if the day is specified in the xml file as a blackout date
     */
    private boolean isDateBlackoutDate(Date aDate, String otherDate)
    {
        boolean result = false;
        try
        {
            String aDateStr = DateUtil.dateToString(aDate, theDateFormat);
            result = (aDateStr.equals(otherDate)) ? true : false;
        }
        catch (Exception e)
        {
            return result;
        }
        return result;
    }

    /**
     * returns a Date after adding deltaHrs to it.
     * 
     * @param startDate
     *            the start date to use
     * @param deltaHrs
     *            the number of hours to add to this date
     * @return Date the new Date.
     */
    private Date getNextRunDate(Date startDate, int deltaHrs)
    {
        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.setTime(startDate);
        startDateCalendar.add(Calendar.HOUR, deltaHrs);

        return startDateCalendar.getTime();
    }

}