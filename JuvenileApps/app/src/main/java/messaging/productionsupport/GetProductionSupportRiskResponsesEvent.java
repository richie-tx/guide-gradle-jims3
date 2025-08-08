package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportRiskResponsesEvent extends RequestEvent 
{
   
	private String riskAssessmentId;	
	
	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
   
   /**
    * @roseuid 45702FFC0393
    */
   public GetProductionSupportRiskResponsesEvent() 
   {
    
   }

	/**
	 * @return the riskAssessmentId
	 */
	public String getRiskAssessmentId() {
		return riskAssessmentId;
	}
	
	/**
	 * @param riskAssessmentId the riskAssessmentId to set
	 */
	public void setRiskAssessmentId(String riskAssessmentId) {
		this.riskAssessmentId = riskAssessmentId;
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
	

}
