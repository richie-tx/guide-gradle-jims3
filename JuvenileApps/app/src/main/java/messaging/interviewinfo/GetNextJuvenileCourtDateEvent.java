package messaging.interviewinfo;

import mojo.km.messaging.reporting.ReportRequestEvent;


public class GetNextJuvenileCourtDateEvent extends ReportRequestEvent 
{
	private String casefileId;
	
	/**
	* 
	*/
	public GetNextJuvenileCourtDateEvent() 
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

}
