//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileTraitsEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetCasefileandOfficerInfoEvent extends RequestEvent
{
	public String casefileId;

	/**
	 * @roseuid 42A7336D0279
	 */
	public GetCasefileandOfficerInfoEvent()
	{

	}

	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileId The casefileId to set.
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
}
