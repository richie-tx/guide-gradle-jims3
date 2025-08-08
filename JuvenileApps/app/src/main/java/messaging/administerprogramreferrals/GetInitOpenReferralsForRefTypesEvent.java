package messaging.administerprogramreferrals;

import java.util.List;

import mojo.km.messaging.RequestEvent;

public class GetInitOpenReferralsForRefTypesEvent extends RequestEvent
{
	private String defendantId;
	private List refTypesCdList;
	
	
	
	/**
	 * @return the defendantId
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId the defendantId to set
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	/**
	 * @return the refTypesCdList
	 */
	public List getRefTypesCdList() {
		return refTypesCdList;
	}
	/**
	 * @param refTypesCdList the refTypesCdList to set
	 */
	public void setRefTypesCdList(List refTypesCdList) {
		this.refTypesCdList = refTypesCdList;
	}
}
