package messaging.interviewinfo;

import mojo.km.messaging.RequestEvent;

public class GetInterviewReportEvent extends RequestEvent 
{
	private String reportId;
	
	/**
	* 
	*/
	public GetInterviewReportEvent() 
	{
    
	}
	
	/**
	 * @return
	 */
	public String getReportId()
	{
		return reportId;
	}

	/**
	 * @param string
	 */
	public void setReportId(String string)
	{
		reportId = string;
	}

}
