package messaging.interviewinfo;

import messaging.interviewinfo.to.SocialHistoryReportDataTO;
import mojo.km.messaging.reporting.ReportRequestEvent;


public class CreateSocialHistoryReportEvent extends ReportRequestEvent 
{
	private String casefileId;
	private SocialHistoryReportDataTO socialHistoryReportDataTO;
	private boolean generic = false;
	private boolean reportToReferee = false;
	
	/**
	* 
	*/
	public CreateSocialHistoryReportEvent() 
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
	 * @return Returns the socialHistoryReportDataTO.
	 */
	public SocialHistoryReportDataTO getSocialHistoryReportDataTO() 
	{
		return socialHistoryReportDataTO;
	}
	
	/**
	 * @param socialHistoryReportDataTO The socialHistoryReportDataTO to set.
	 */
	public void setSocialHistoryReportDataTO( SocialHistoryReportDataTO socialHistoryReportDataTO ) 
	{
		this.socialHistoryReportDataTO = socialHistoryReportDataTO;
	}

	/**
	 * @param generic the generic to set
	 */
	public void setGeneric(boolean generic) {
		this.generic = generic;
	}

	/**
	 * @return the generic
	 */
	public boolean isGeneric() {
		return generic;
	}

	public boolean isReportToReferee() {
		return reportToReferee;
	}

	public void setReportToReferee(boolean reportToReferee) {
		this.reportToReferee = reportToReferee;
	}
}
