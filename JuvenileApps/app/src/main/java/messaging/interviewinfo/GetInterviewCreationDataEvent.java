package messaging.interviewinfo;

import mojo.km.messaging.RequestEvent;

public class GetInterviewCreationDataEvent extends RequestEvent 
{
	private String casefileId;
	private String juvenileId;
	
//	private String calendarEventId;		// ON HOLD, may not need id here.
   
   /**
    * @roseuid 448ECBC40095
    */
   public GetInterviewCreationDataEvent() 
   {
    
   }
	/**
	 * @return
	 */
	public String getCasefileId()
	{
		return casefileId;
	}

	/**
	 * @param string
	 */
	public void setCasefileId(String string)
	{
		casefileId = string;
	}

	/**
	 * @return Returns the juvenileId.
	 */
	public String getJuvenileId() {
		return juvenileId;
	}
	/**
	 * @param juvenileId The juvenileId to set.
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	
	/**
	 * @return
	 * /
	public String getCalendarEventId()
	{
		return calendarEventId;
	}

	/**
	 * @param string
	 * /
	public void setCalendarEventId(String string)
	{
		calendarEventId = string;
	}
	*/

}
