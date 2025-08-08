package messaging.interviewinfo;

import mojo.km.messaging.RequestEvent;


public class GetSocialHistoryReportDataEvent extends RequestEvent 
{
	private String casefileId;
	//<KISHORE>JIMS200060775 : Add Social Hist. link to Program Ref Detail(PD)-KK
	private boolean warrantHistoryNeeded = true;
	
	/**
	* 
	*/
	public GetSocialHistoryReportDataEvent() 
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
	 * @return the warrantHistoryNeeded
	 */
	public boolean isWarrantHistoryNeeded() {
		return warrantHistoryNeeded;
	}

	/**
	 * @param warrantHistoryNeeded the warrantHistoryNeeded to set
	 */
	public void setWarrantHistoryNeeded(boolean warrantHistoryNeeded) {
		this.warrantHistoryNeeded = warrantHistoryNeeded;
	}

}
