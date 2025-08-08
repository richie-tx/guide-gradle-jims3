package messaging.interviewinfo;

import mojo.km.messaging.RequestEvent;

public class GetInterviewReportsEvent extends RequestEvent 
{
	private String casefileId;
	private String juvenileId;
	//<KISHORE>JIMS200060775 : Add Social Hist. link to Program Ref Detail(PD)-KK
	private String reportType;
	
	/**
	* 
	*/
	public GetInterviewReportsEvent() 
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
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
}
