package messaging.calendar;

import messaging.interviewinfo.CreateInterviewEvent;

public class CreateScheduleInterviewEventEvent
{
	private CreateCalendarServiceEventEvent calendarServiceEvent;

	private CreateInterviewEvent interviewEvent;

	public CreateCalendarServiceEventEvent getCalendarServiceEvent()
	{
		return calendarServiceEvent;
	}

	public void setCalendarServiceEvent(CreateCalendarServiceEventEvent calendarServiceEvent)
	{
		this.calendarServiceEvent = calendarServiceEvent;
	}

	public CreateInterviewEvent getInterviewEvent()
	{
		return interviewEvent;
	}

	public void setInterviewEvent(CreateInterviewEvent interviewEvent)
	{
		this.interviewEvent = interviewEvent;
	}
}
