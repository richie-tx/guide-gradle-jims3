//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\ServiceEventController.java

package pd.supervision.calendar;

import java.util.Collection;
import java.util.Date;

import messaging.calendar.CreateCalendarServiceEventEvent;
import messaging.interviewinfo.CreateInterviewEvent;

/**
 * @stereotype control
 */
public class ServiceEventController
{

	/**
	 * @roseuid 44805C4F0064
	 */
	public ServiceEventController()
	{

	}

	/**
	 * @stereotype design
	 * @param endDate
	 * @param startDate
	 * @param serviceLocationId
	 * @roseuid 447F49A5013C
	 */
	public void getCalendarServiceEvents(Date endDate, Date startDate, String serviceLocationId)
	{

	}

	/**
	 * @stereotype design
	 * @param CalendarServiceEventRequestEvents
	 * @roseuid 447F49B50175
	 */
	public void createCalendarServiceEvent(Collection CalendarServiceEventRequestEvents)
	{

	}

	public void createScheduleInterviewEvent(CreateCalendarServiceEventEvent calendarServiceEvent,
			CreateInterviewEvent interviewEvent)
	{

	}

	
	/**
	 * @stereotype design
	 * @param serviceLocationId
	 * @param instructorName
	 * @param eventDate
	 * @roseuid 447F49B403E6
	 */
	public void getServiceLocationServiceEvents(int locationId)
	{

	}

	/**
	 * @stereotype design
	 * @param serviceId
	 * @roseuid 447F49B403E6
	 */
	public void getServiceEventsByServiceId(int serviceId)
	{

	}

	/**
	 * @stereotype design
	 * @param instructorId
	 * @roseuid 447F49B403E6
	 */
	public void getServiceEventsByProviderProfileId(int insttructorId)
	{

	}

	/**
	 * @stereotype design
	 * @param startDateTime
	 * @param endDateTime
	 * @roseuid 447F49B403E6
	 */
	public void getRecurringServiceEvents(Date startDateTime, Date endDateTime)
	{

	}

	/**
	 * @stereotype design
	 * @param serviceProviderId
	 * @param instructorId
	 * @param serviceLocationId
	 * @param isInHouse
	 * @roseuid 447F49B403E6
	 */
	public void getCalendarServiceEventConflicts(int serviceProviderId, int instructorId, int serviceLocationId,
			boolean isInHouse)
	{

	}

	/**
	 * @stereotype design
	 * @param juvenileNum
	 * @roseuid 447F49B403E6
	 */
	public void getMemberLocations(String juvenileNum)
	{

	}

	/**
	 * @stereotype design
	 * @param checkEvents
	 * @roseuid 447F49B403E6
	 */
	public void getScheduleCalendarEventConflicts(Collection checkEvents)
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void createScheduleCalendarEvents()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void getJuvenileAttendance()
	{

	}
	
	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void getSchoolAdjudicationNotification()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void saveJuvenileAttendance()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void saveProgressNotes()
	{

	}
	
	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void saveMonthlySummary()
	{

	}
	
	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void getServiceEventAttendance()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void getProgramAttendance()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void saveServiceEventAttendance()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void getServiceEventCancellationList()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void saveServiceEventCancellation()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void getCalendarUserType()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void getViewCalendarEvents()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void getDocketEvents()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void getViewCalendarDocketEvents()
	{

	}
	
	/**
	 * @stereotype design
	 * @roseuid 
	 */
	public void GetViewCalendarDocketEventsByJuveniles()
	{

	}
	
	/**
	 * @stereotype design
	 * @roseuid 
	 */
	public void GetOfficerAssociatedJuvenileCasefiles()
	{

	}
	

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void getProgramReferralServiceEvents()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void getProgramReferralServiceEventsByProgRefId(String programReferralId)
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void getProgramReferralsByServiceEventId(String serviceEventId)
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void sendProgramReferralOrphanedNotification(String programReferralId)
	{

	}
	
	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void cancelEventsList(){
		
	}
	/**
	 * @stereotype design
	 * @param serviceEventId
	 */
	public void getServiceEventsByServiceEventId(String serviceEventId)
	{

	}
	
	/**
	 * @stereotype design
	 */
	public void getServiceEventDetails()
	{

	}

	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void saveProgramReferralWithEvents(String referralId, String programReferralId, String casefileId, String juvenileNum)
	{

	}
	/**
	 * @stereotype design
	 * @roseuid 447F49B403E6
	 */
	public void getProgramReferralWithEvents(String referralId, String programReferralId, String casefileId, String juvenileNum)
	{

	}
	

}
