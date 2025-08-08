/*
 * Created on Ded 29, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerprogramreferrals;

import java.util.Collection;
import java.util.List;

import mojo.km.messaging.RequestEvent;

/**
 * @author c_shimek
 *
 * modified copy of InitiateReferralsEvent.java
 * 
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetServiceProvidersByReferralTypesEvent extends RequestEvent 
{
	private String defendantId;
	private String criminalCaseId;
	private List referralTypes;
	private String programReferralId;
	private Collection progRefIdsToDeleteList;
	
	/**
	 * @return Returns the criminalCaseId.
	 */
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	/**
	 * @param criminalCaseId The criminalCaseId to set.
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	/**
	 * @return Returns the referralTypes.
	 */
	public List getReferralTypes() {
		return referralTypes;
	}
	/**
	 * @param referralTypes The referralTypes to set.
	 */
	public void setReferralTypes(List referralTypes) {
		this.referralTypes = referralTypes;
	}
	public String getProgramReferralId() {
		return programReferralId;
	}
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}
	/**
	 * @return the progRefIdsToDeleteList
	 */
	public Collection getProgRefIdsToDeleteList() {
		return progRefIdsToDeleteList;
	}
	/**
	 * @param progRefIdsToDeleteList the progRefIdsToDeleteList to set
	 */
	public void setProgRefIdsToDeleteList(Collection progRefIdsToDeleteList) {
		this.progRefIdsToDeleteList = progRefIdsToDeleteList;
	}
	
}//end of GetServiceProvidersByReferralTypesEvent class
