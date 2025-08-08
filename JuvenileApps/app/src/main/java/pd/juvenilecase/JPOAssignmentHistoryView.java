package pd.juvenilecase;

import java.util.Date;
import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class JPOAssignmentHistoryView extends PersistentObject {

	private String caseFileId;
	private String serviceUnitId;
	private String assignmentLevelId;
	private String referralNumber;
	private Date assignmentAddDate;
	private Date jpoAssignmentDate;
	private String officerProfileId;
	private String assignedByUserId;
	
	//US 79648
	private String assignmentType;
	
	public String getCaseFileId() {
		return caseFileId;
	}
	public void setCaseFileId(String caseFileId) {
		this.caseFileId = caseFileId;
	}
	public String getServiceUnitId() {
		return serviceUnitId;
	}
	public void setServiceUnitId(String serviceUnitId) {
		this.serviceUnitId = serviceUnitId;
	}
	public String getAssignmentLevelId() {
		return assignmentLevelId;
	}
	public void setAssignmentLevelId(String assignmentLevelId) {
		this.assignmentLevelId = assignmentLevelId;
	}
	public String getReferralNumber() {
		return referralNumber;
	}
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
	public Date getAssignmentAddDate() {
		return assignmentAddDate;
	}
	public void setAssignmentAddDate(Date assignmentAddDate) {
		this.assignmentAddDate = assignmentAddDate;
	}
	public Date getJpoAssignmentDate() {
		return jpoAssignmentDate;
	}
	public void setJpoAssignmentDate(Date jpoAssignmentDate) {
		this.jpoAssignmentDate = jpoAssignmentDate;
	}
	public String getOfficerProfileId() {
		return officerProfileId;
	}
	public void setOfficerProfileId(String officerProfileId) {
		this.officerProfileId = officerProfileId;
	}
	public String getAssignedByUserId() {
		return assignedByUserId;
	}
	public void setAssignedByUserId(String assignedByUserId) {
		this.assignedByUserId = assignedByUserId;
	}

	/**
	* @return JPOAssignmentHistory
	* @param jpoAssignmentHistoryId
	*/
	static public JPOAssignmentHistoryView find(String jpoAssignmentHistoryId)
	{
		IHome home = new Home();
		JPOAssignmentHistoryView jpoAssignmentHistory = (JPOAssignmentHistoryView) home.find(jpoAssignmentHistoryId, JPOAssignmentHistoryView.class);
		return jpoAssignmentHistory;
	}
	/**
	* @return Iterator jpoAssignmentHistories
	* @param attrName name fo the attribute for where clause
	* @param attrValue value to be checked in the where clause
	*/
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator jpoAssignmentHistories = home.findAll(attrName, attrValue, JPOAssignmentHistoryView.class);
		return jpoAssignmentHistories;
	}
	
	/**
	* Finds casefiles by a certain event
	* @param event
	* @return Iterator of casefiles
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator histories = home.findAll(event, JPOAssignmentHistoryView.class);
		return histories;
	}
	/**
	 * @return the assignmentType
	 */
	public String getAssignmentType()
	{
	    return assignmentType;
	}
	/**
	 * @param assignmentType the assignmentType to set
	 */
	public void setAssignmentType(String assignmentType)
	{
	    this.assignmentType = assignmentType;
	}

}
