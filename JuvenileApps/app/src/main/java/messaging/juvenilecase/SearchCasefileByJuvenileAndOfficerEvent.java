//Source file: C:\\views\\dev\\app\\src\\messaging\\officer\\GetOfficerProfilesEvent.java

package messaging.juvenilecase;

import java.util.List;

import mojo.km.messaging.RequestEvent;

public class SearchCasefileByJuvenileAndOfficerEvent extends RequestEvent
{

/*	private String juvenileFirstName;
	private String juvenileLastName;
	private String juvenileMiddleName;
*/	
	private List juvNumList;
	
	private String officerFirstName;
	private String officerLastName;
	private String officerMiddleName;
	
	private boolean isCaseLoadManager;
	
	private String officerUserId;



	/**
	 * @return Returns the officerFirstName.
	 */
	public String getOfficerFirstName() {
		return officerFirstName;
	}
	/**
	 * @param officerFirstName The officerFirstName to set.
	 */
	public void setOfficerFirstName(String officerFirstName) {
		this.officerFirstName = officerFirstName;
	}
	/**
	 * @return Returns the officerLastName.
	 */
	public String getOfficerLastName() {
		return officerLastName;
	}
	/**
	 * @param officerLastName The officerLastName to set.
	 */
	public void setOfficerLastName(String officerLastName) {
		this.officerLastName = officerLastName;
	}
	/**
	 * @return Returns the officerMiddleName.
	 */
	public String getOfficerMiddleName() {
		return officerMiddleName;
	}
	/**
	 * @param officerMiddleName The officerMiddleName to set.
	 */
	public void setOfficerMiddleName(String officerMiddleName) {
		this.officerMiddleName = officerMiddleName;
	}

	/**
	 * @return Returns the isCaseLoadManager.
	 */
	public boolean isCaseLoadManager() {
		return isCaseLoadManager;
	}
	/**
	 * @param isCaseLoadManager The isCaseLoadManager to set.
	 */
	public void setCaseLoadManager(boolean isCaseLoadManager) {
		this.isCaseLoadManager = isCaseLoadManager;
	}
	/**
	 * @return Returns the officerUserId.
	 */
	public String getOfficerUserId() {
		return officerUserId;
	}
	/**
	 * @param officerUserId The officerUserId to set.
	 */
	public void setOfficerUserId(String officerUserId) {
		this.officerUserId = officerUserId;
	}
	/**
	 * @return Returns the juvNumList.
	 */
	public List getJuvNumList() {
		return juvNumList;
	}
	/**
	 * @param juvNumList The juvNumList to set.
	 */
	public void setJuvNumList(List juvNumList) {
		this.juvNumList = juvNumList;
	}
}
