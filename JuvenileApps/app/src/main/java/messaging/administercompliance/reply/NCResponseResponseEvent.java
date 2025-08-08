//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercasenotes\\CasenoteEvent.java

package messaging.administercompliance.reply;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.DateUtil;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class NCResponseResponseEvent extends ResponseEvent
{

	private String statusId;
	private String status;
	private String caseId;
	private Timestamp createDate;
	private String createdBy;
	private String createdByName;
	private Timestamp statusChangedDate;
	private String ncResponseId;
	private String taskId;
	private String courtActionSummary;
	private Timestamp lastContactDate;
	private String addressType;
	private String aptNumber;
	private String city;
	private String state;
	private String streetName;
	private String streetNumber;
	private String zipcode;
	private String comments;
	private String totalSpecimenAnalyzed;
	private String commentType;
	private Timestamp filedDate;
	private String presentedBy;
	private String signedBy;
	private String positionIdOfPresentedBy;
	private String positionIdOfSignedBy;
	
	private String managerApprovalUser;
	private Timestamp managerApprovalDate;
	private String submissionApprovedUser;
	private Timestamp submissionApprovedDate;
	private Timestamp subMgrAppDate;
	private String filedUser;

	
	/**
	 * sort the response event based on the statusChanged Date
	 */
	public static Comparator NCResponseResponseEventDateComparator = new Comparator() {
		public int compare(Object nCResponseEvent, Object previousNCResponseEvent){
		
			if(nCResponseEvent == null || previousNCResponseEvent == null || !(nCResponseEvent instanceof NCResponseResponseEvent) 
					||  !(previousNCResponseEvent instanceof NCResponseResponseEvent))
			{
				return 1;
			}
			// get the date values  and status/oid strings to sort on
			NCResponseResponseEvent currentNCResponseResponseEvent = (NCResponseResponseEvent)nCResponseEvent;
			NCResponseResponseEvent previousNCResponseResponseEvent = (NCResponseResponseEvent)previousNCResponseEvent;
			Date currentDate1 = currentNCResponseResponseEvent.getStatusChangedDate();
			Date previousDate2 = previousNCResponseResponseEvent.getStatusChangedDate();
			String currentStatusOid1 = currentNCResponseResponseEvent.getStatus();
			String previousStatusOid2= previousNCResponseResponseEvent.getStatus();
			int compareResult = 0;
			// 1) sort first on the status/oid values
			if(currentStatusOid1 != null && previousStatusOid2 != null){
				compareResult = currentStatusOid1.compareTo(previousStatusOid2);
			}
			// 2) check if both the effective date fields on the bean are set. if they are sort on this, if not return equal
			if(currentDate1 != null && previousDate2 != null && compareResult == 0){
				compareResult = DateUtil.compare(previousDate2,currentDate1, DateUtil.LOG_DATE_FMT);
			}
			
		return compareResult; 
		
		}
	};
	
	/**
	 * @return the filedUser
	 */
	public String getFiledUser() {
		return filedUser;
	}
	/**
	 * @param filedUser the filedUser to set
	 */
	public void setFiledUser(String filedUser) {
		this.filedUser = filedUser;
	}
	/**
	 * @return the managerApprovalDate
	 */
	public Timestamp getManagerApprovalDate() {
		return managerApprovalDate;
	}
	/**
	 * @param managerApprovalDate the managerApprovalDate to set
	 */
	public void setManagerApprovalDate(Timestamp managerApprovalDate) {
		this.managerApprovalDate = managerApprovalDate;
	}
	/**
	 * @return the managerApprovalUser
	 */
	public String getManagerApprovalUser() {
		return managerApprovalUser;
	}
	/**
	 * @param managerApprovalUser the managerApprovalUser to set
	 */
	public void setManagerApprovalUser(String managerApprovalUser) {
		this.managerApprovalUser = managerApprovalUser;
	}
	/**
	 * @return the submissionApprovedDate
	 */
	public Timestamp getSubmissionApprovedDate() {
		return submissionApprovedDate;
	}
	/**
	 * @param submissionApprovedDate the submissionApprovedDate to set
	 */
	public void setSubmissionApprovedDate(Timestamp submissionApprovedDate) {
		this.submissionApprovedDate = submissionApprovedDate;
	}
	/**
	 * @return the subMgrAppDate
	 */
	public Timestamp getSubMgrAppDate() {
		return subMgrAppDate;
	}
	/**
	 * @param subMgrAppDate the subMgrAppDate to set
	 */
	public void setSubMgrAppDate(Timestamp subMgrAppDate) {
		this.subMgrAppDate = subMgrAppDate;
	}
	/**
	 * @return the submissionApprovedUser
	 */
	public String getSubmissionApprovedUser() {
		return submissionApprovedUser;
	}
	/**
	 * @param submissionApprovedUser the submissionApprovedUser to set
	 */
	public void setSubmissionApprovedUser(String submissionApprovedUser) {
		this.submissionApprovedUser = submissionApprovedUser;
	}
	/**
	 * @return Returns the totalSpecimenAnalyzed.
	 */
	public String getTotalSpecimenAnalyzed() {
		return totalSpecimenAnalyzed;
	}
	/**
	 * @param totalSpecimenAnalyzed The totalSpecimenAnalyzed to set.
	 */
	public void setTotalSpecimenAnalyzed(String totalSpecimenAnalyzed) {
		this.totalSpecimenAnalyzed = totalSpecimenAnalyzed;
	}
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the addressType.
	 */
	public String getAddressType() {
		return addressType;
	}
	/**
	 * @param addressType The addressType to set.
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	/**
	 * @return Returns the aptNumber.
	 */
	public String getAptNumber() {
		return aptNumber;
	}
	/**
	 * @param aptNumber The aptNumber to set.
	 */
	public void setAptNumber(String aptNumber) {
		this.aptNumber = aptNumber;
	}
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return Returns the lastContactDate.
	 */
	public Timestamp getLastContactDate() {
		return lastContactDate;
	}
	/**
	 * @param lastContactDate The lastContactDate to set.
	 */
	public void setLastContactDate(Timestamp lastContactDate) {
		this.lastContactDate = lastContactDate;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return Returns the streetName.
	 */
	public String getStreetName() {
		return streetName;
	}
	/**
	 * @param streetName The streetName to set.
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	/**
	 * @return Returns the streetNumber.
	 */
	public String getStreetNumber() {
		return streetNumber;
	}
	/**
	 * @param streetNumber The streetNumber to set.
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	/**
	 * @return Returns the zipcode.
	 */
	public String getZipcode() {
		return zipcode;
	}
	/**
	 * @param zipcode The zipcode to set.
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	/**
	 * @return Returns the caseId.
	 */
	public String getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId The caseId to set.
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return Returns the createDate.
	 */
	public Timestamp getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate The createDate to set.
	 */
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return Returns the createdBy.
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy The createdBy to set.
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return Returns the ncResponseId.
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
	/**
	 * @return Returns the statusChangedDate.
	 */
	public Timestamp getStatusChangedDate() {
		return statusChangedDate;
	}
	/**
	 * @param statusChangedDate The statusChangedDate to set.
	 */
	public void setStatusChangedDate(Timestamp statusChangedDate) {
		this.statusChangedDate = statusChangedDate;
	}
	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	/**
	 * @return Returns the commentType.
	 */
	public String getCommentType() {
		return commentType;
	}
	/**
	 * @param commentType The commentType to set.
	 */
	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the createdByName
	 */
	public String getCreatedByName() {
		return createdByName;
	}
	/**
	 * @param createdByName the createdByName to set
	 */
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	/**
	 * @return the filedDate
	 */
	public Timestamp getFiledDate() {
		return filedDate;
	}
	/**
	 * @param filedDate the filedDate to set
	 */
	public void setFiledDate(Timestamp filedDate) {
		this.filedDate = filedDate;
	}
	/**
	 * @return the positionIdOfPresentedBy
	 */
	public String getPositionIdOfPresentedBy() {
		return positionIdOfPresentedBy;
	}
	/**
	 * @param positionIdOfPresentedBy the positionIdOfPresentedBy to set
	 */
	public void setPositionIdOfPresentedBy(String positionIdOfPresentedBy) {
		this.positionIdOfPresentedBy = positionIdOfPresentedBy;
	}
	/**
	 * @return the positionIdOfSignedBy
	 */
	public String getPositionIdOfSignedBy() {
		return positionIdOfSignedBy;
	}
	/**
	 * @param positionIdOfSignedBy the positionIdOfSignedBy to set
	 */
	public void setPositionIdOfSignedBy(String positionIdOfSignedBy) {
		this.positionIdOfSignedBy = positionIdOfSignedBy;
	}
	/**
	 * @return the presentedBy
	 */
	public String getPresentedBy() {
		return presentedBy;
	}
	/**
	 * @param presentedBy the presentedBy to set
	 */
	public void setPresentedBy(String presentedBy) {
		this.presentedBy = presentedBy;
	}
	/**
	 * @return the signedBy
	 */
	public String getSignedBy() {
		return signedBy;
	}
	/**
	 * @param signedBy the signedBy to set
	 */
	public void setSignedBy(String signedBy) {
		this.signedBy = signedBy;
	}
	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCourtActionSummary() {
		return courtActionSummary;
	}
	
	/**
	 * 
	 * @param courtActionSummary
	 */
	public void setCourtActionSummary(String courtActionSummary) {
		this.courtActionSummary = courtActionSummary;
	}
	
	

}
