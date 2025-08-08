package pd.juvenilecase.referral;

import java.util.Date;
import java.util.Iterator;

import pd.codetable.criminal.JuvenileReferralDispositionCode;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;

import naming.PDCodeTableConstants;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @author ugopinath
This entity represents the assignment history generated 
with Referral Create/Update
*/
public class JJSSVIntakeHistory extends PersistentObject
{
	
	private String referralNumber;
	private String juvenileNum;
	private Date assignmentDate;
	private Date intakeDate;
	private String jpoId;
	private String jpoName;
	private String intakeDecisionId;
	private String intakeDecisionDescription;
	private String intakeHistoryId;
	private String assignmentType;
	private String assignmentTypeDescription;
	private String supervisionTypeId;
	private String supervisionTypeDescription;
	private String createUserName;
	private String casefileId; //added for US 183149
	
	/**
	* @roseuid 4276853503B9
	*/
	public JJSSVIntakeHistory()
	{
	}
	/**
	* @return Assignment
	* @param assignmentId
	*/
	static public JJSSVIntakeHistory find(String intakeHistoryId)
	{
		IHome home = new Home();
		JJSSVIntakeHistory intake = (JJSSVIntakeHistory) home.find(intakeHistoryId, JJSSVIntakeHistory.class);
		return intake;
	}
	/**
	* @return Iterator assignments
	* @param attrName name fo the attribute for where clause
	* @param attrValue value to be checked in the where clause
	*/
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator intakeRecs = home.findAll(attrName, attrValue, JJSSVIntakeHistory.class);
		return intakeRecs;
	}
	//added for task  #81747
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JJSSVIntakeHistory.class);
	}
	
	/**
	* Access method for the referralNumber property.
	* @return the current value of the referralNumber property
	*/
	public String getReferralNumber()
	{
		fetch();
		return referralNumber;
	}
	/**
	* Sets the value of the referralNumber property.
	* @param aReferralNumber the new value of the referralNumber property
	*/
	public void setReferralNumber(String aReferralNumber)
	{
		if (this.referralNumber == null || !this.referralNumber.equals(aReferralNumber))
		{
			markModified();
		}
		referralNumber = aReferralNumber;
	}
	
	
	/**
	* @roseuid 427136DA0030
	*/
	public void bind()
	{
		markModified();
	}

	
	
	public String getAssignmentType() {
		fetch();
		return assignmentType;
	}
	public void setAssignmentType(String assignmentType) {
		if (this.assignmentType == null || !this.assignmentType.equals(assignmentType))
		{
			markModified();
		}
		this.assignmentType = assignmentType;
	}
	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum()
	{
	    fetch();
	    return juvenileNum;
	}
	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum)
	{
	    if (this.juvenileNum == null || !this.juvenileNum.equals(juvenileNum))
		{
			markModified();
		}
	    this.juvenileNum = juvenileNum;
	}
	/**
	 * @return the assignmentDate
	 */
	public Date getAssignmentDate()
	{
	    fetch();
	    return assignmentDate;
	}
	/**
	 * @param assignmentDate the assignmentDate to set
	 */
	public void setAssignmentDate(Date assignmentDate)
	{
	    if (this.assignmentDate == null || !this.assignmentDate.equals(assignmentDate))
	    {
		markModified();
	    }
	    this.assignmentDate = assignmentDate;
	}
	/**
	 * @return the intakeDate
	 */
	public Date getIntakeDate()
	{
	    fetch();
	    return intakeDate;
	}
	/**
	 * @param intakeDate the intakeDate to set
	 */
	public void setIntakeDate(Date intakeDate)
	{
	    if (this.intakeDate == null || !this.intakeDate.equals(intakeDate))
	    {
	 	markModified();
	    }
	    this.intakeDate = intakeDate;
	}
	/**
	 * @return the jpoId
	 */
	public String getJpoId()
	{
	    fetch();
	    return jpoId;
	}
	/**
	 * @param jpoId the jpoId to set
	 */
	public void setJpoId(String jpoId)
	{    if (this.jpoId == null || !this.jpoId.equals(jpoId))
	    {
	 	markModified();
	    }
	    this.jpoId = jpoId;
	}
	/**
	 * @return the intakeDecisionId
	 */
	public String getIntakeDecisionId()
	{
	    fetch();
	    return intakeDecisionId;
	}
	/**
	 * @param intakeDecisionId the intakeDecisionId to set
	 */
	public void setIntakeDecisionId(String intakeDecisionId)
	{
	    if (this.intakeDecisionId == null || !this.intakeDecisionId.equals(intakeDecisionId))
	    {
	 	markModified();
	    }
	    this.intakeDecisionId = intakeDecisionId;
	}
	/**
	 * @return the intakeHistoryId
	 */
	public String getIntakeHistoryId()
	{
	    fetch();
	    return intakeHistoryId;
	}
	/**
	 * @param intakeHistoryId the intakeHistoryId to set
	 */
	public void setIntakeHistoryId(String intakeHistoryId)
	{
	    if (this.intakeHistoryId == null || !this.intakeHistoryId.equals(intakeHistoryId))
	    {
	 	markModified();
	    }
	    this.intakeHistoryId = intakeHistoryId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSupervisionTypeId()
	{
	    fetch();
	    return supervisionTypeId;
	}
	/**
	 * 
	 * @param supervisionTypeId
	 */
	public void setSupervisionTypeId(String supervisionTypeId)
	{
	    if (this.supervisionTypeId == null || !this.supervisionTypeId.equals(supervisionTypeId))
	    {
	 	markModified();
	    }
	    this.supervisionTypeId = supervisionTypeId;
	}
	
	public String getSupervisionTypeDescription()
	{
	    if ( this.supervisionTypeId != null 
		    && this.supervisionTypeId.length() > 0 ) {
		this.supervisionTypeDescription = CodeHelper.getCodeDescription(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE, this.supervisionTypeId);
	    }
	    
	    return supervisionTypeDescription;
	}
	public String getIntakeDecisionDescription()
	{
	    if ( this.intakeDecisionId != null
		    && this.intakeDecisionId.length() > 0 ) {
		JuvenileReferralDispositionCode response = JuvenileReferralDispositionCode.find(this.intakeDecisionId);
		if ( response != null ) {
		    this.intakeDecisionDescription = response.getDescription();
		}
		
	    }
	    return intakeDecisionDescription;
	}
	
	
	public String getJpoName()
	{
	    if (this.jpoId != null
		    && this.jpoId.length() > 0 ){
		OfficerProfile officerProfile = OfficerProfile.findByLogonId(this.jpoId);
		if ( officerProfile != null ){
		    OfficerProfileResponseEvent officerProfileResponseEvent = PDOfficerProfileHelper.getBasicOfficerProfileResponseEvent(officerProfile);
		    if ( officerProfileResponseEvent != null ) {
			this.jpoName = officerProfileResponseEvent.getFormattedName();
		    }
		}
	    }
	    
	    return jpoName;
	}
	
	public String getCreateUserName()
	{
	    if (this.getCreateUserID() != null
		    && this.getCreateUserID().length() > 0 ) {
		OfficerProfile officerProfile = OfficerProfile.findByLogonId(this.getCreateUserID());
		
		if ( officerProfile != null ){
		    OfficerProfileResponseEvent officerProfileResponseEvent = PDOfficerProfileHelper.getBasicOfficerProfileResponseEvent(officerProfile);
		    if ( officerProfileResponseEvent != null ) {
			this.createUserName = officerProfileResponseEvent.getFormattedName();
		    }
		}
		
	    }
	    return createUserName;
	}
	
	public String getAssignmentTypeDescription()
	{
	    if (this.assignmentType != null 
		    && this.assignmentType.length() > 0 ) {
		this.assignmentTypeDescription = ComplexCodeTableHelper.getDescrByCode(ComplexCodeTableHelper.getRefAssmntTypeCodes(), this.assignmentType);
	    }
	    return assignmentTypeDescription;
	}
	public String getCasefileId()
	{
	    return casefileId;
	}
	public void setCasefileId(String casefileId)
	{
	    this.casefileId = casefileId;
	}
	
	
	
	
	
	
	
	
	
	

	
	
}
