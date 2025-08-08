package messaging.interviewinfo.reply;

import messaging.interviewinfo.to.SocialHistoryReportDataTO;
import mojo.km.messaging.ResponseEvent;

/**
 * @author bschwartz
 *
 */
public class SocialHistoryReportDataResponseEvent extends ResponseEvent
{
	private String casefileId;
	private SocialHistoryReportDataTO to = new SocialHistoryReportDataTO();

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
	 * @return
	 */
	public SocialHistoryReportDataTO getTO()
	{
		return to;
	}


}
