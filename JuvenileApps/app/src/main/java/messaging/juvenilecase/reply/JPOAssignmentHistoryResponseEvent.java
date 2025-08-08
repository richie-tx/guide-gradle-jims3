package messaging.juvenilecase.reply;

import java.util.Date;
import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.ResponseEvent;

public class JPOAssignmentHistoryResponseEvent extends ResponseEvent implements IAddressable, Comparable{
	private String jpoAssignmentHistoryId;
	private String casefileId;
	private Date jpoAssignmentDate;
	private String officerProfileId;
	private String jpoOfficerUserId;
	private String officerFirstName;
	private String officerLastName;
	private String officerMiddleName;
	private Boolean wasChecked;
	
	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	public String getCasefileId() {
		return casefileId;
	}
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
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
	public String getOfficerFirstName() {
		return officerFirstName;
	}
	public void setOfficerFirstName(String officerFirstName) {
		this.officerFirstName = officerFirstName;
	}
	public String getOfficerLastName() {
		return officerLastName;
	}
	public void setOfficerLastName(String officerLastName) {
		this.officerLastName = officerLastName;
	}
	public String getOfficerMiddleName() {
		return officerMiddleName;
	}
	public void setOfficerMiddleName(String officerMiddleName) {
		this.officerMiddleName = officerMiddleName;
	}
	
	
	/**
	 * @return the jpoAssignmentHistoryId
	 */
	public String getJpoAssignmentHistoryId() {
		return jpoAssignmentHistoryId;
	}
	/**
	 * @param jpoAssignmentHistoryId the jpoAssignmentHistoryId to set
	 */
	public void setJpoAssignmentHistoryId(String jpoAssignmentHistoryId) {
		this.jpoAssignmentHistoryId = jpoAssignmentHistoryId;
	}
	/**
	 * @return the jpoOfficerUserId
	 */
	public String getJpoOfficerUserId() {
		return jpoOfficerUserId;
	}
	/**
	 * @param jpoOfficerUserId the jpoOfficerUserId to set
	 */
	public void setJpoOfficerUserId(String jpoOfficerUserId) {
		this.jpoOfficerUserId = jpoOfficerUserId;
	}
	
	/**
	 * @return the wasChecked
	 */
	public Boolean getWasChecked() {
		return wasChecked;
	}
	
	/**
	 * @param wasChecked the wasChecked to set
	 */
	public void setWasChecked(Boolean wasChecked) {
		this.wasChecked = wasChecked;
	}
	
	/**
	 * @return the createUserID
	 */
	public String getCreateUserID() {
		return createUserID;
	}
	/**
	 * @param createUserID the createUserID to set
	 */
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the createJIMS2UserID
	 */
	public String getCreateJIMS2UserID() {
		return createJIMS2UserID;
	}
	/**
	 * @param createJIMS2UserID the createJIMS2UserID to set
	 */
	public void setCreateJIMS2UserID(String createJIMS2UserID) {
		this.createJIMS2UserID = createJIMS2UserID;
	}
	/**
	 * @return the updateJIMS2UserID
	 */
	public String getUpdateJIMS2UserID() {
		return updateJIMS2UserID;
	}
	/**
	 * @param updateJIMS2UserID the updateJIMS2UserID to set
	 */
	public void setUpdateJIMS2UserID(String updateJIMS2UserID) {
		this.updateJIMS2UserID = updateJIMS2UserID;
	}
	public int compareTo(Object obj) {
		if(obj==null)
			return -1;
		JPOAssignmentHistoryResponseEvent evt = (JPOAssignmentHistoryResponseEvent) obj;
		int result = 0;
		if(getJpoAssignmentDate() != null && evt.getJpoAssignmentDate() != null )
		{
			try{
				if(this.jpoAssignmentDate!=null || evt.getJpoAssignmentDate()!=null){
					if(evt.getJpoAssignmentDate()==null)
						return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
					if(this.jpoAssignmentDate==null)
						return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
					
					if(this.jpoAssignmentDate==null)
						return -1;
					if(evt.getJpoAssignmentDate()==null)
						return 1;
					result= evt.getJpoAssignmentDate().compareTo(this.jpoAssignmentDate); // backwards in order to get list to show up most recent first
				}
			}
			catch(NumberFormatException e){
				result = 0;
			}
		}
		return result;
	}
}
