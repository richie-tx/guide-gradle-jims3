package messaging.interviewinfo;

import mojo.km.messaging.reporting.ReportRequestEvent;


public class CreateParentalStatementReportEvent extends ReportRequestEvent 
{
	private String casefileId;
	private boolean spanishText = false;
	
	/**
	* 
	*/
	public CreateParentalStatementReportEvent() 
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
	 * @return Returns the spanishText.
	 */
	public boolean isSpanishText() 
	{
		return spanishText;
	}
	/**
	 * @param spanishText The spanishText to set.
	 */
	public void setSpanishText(boolean spanishText) 
	{
		this.spanishText = spanishText;
	}
}
