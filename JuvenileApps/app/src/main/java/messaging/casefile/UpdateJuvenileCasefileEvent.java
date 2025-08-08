//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\casefile\\UpdateJuvenileCasefileEvent.java

package messaging.casefile;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class UpdateJuvenileCasefileEvent extends RequestEvent {
	public String supervisionNumber;

	public Date supervisionEndDate;

	public boolean isMAYSINeeded;

	public boolean isReferralRiskNeeded;

	public boolean isInterviewRiskNeeded;
	
	//added for ProdSupport
	private String jpoOfficerId;
	private Date activationDate;
	private String sequenceNum;
	private Date createDate;
	private String supervisionTypeId;
	private String controllingReferralId;
	private Date updateDate;
	private String updateUser;
	private String juvenileNum;// added for US 180414
	
	private boolean pactRiskNeeded;
	private boolean hispanicIndicatorNeeded;
	private boolean schoolHistoryNeeded;
	private boolean vopEntryNeeded;
	private boolean substanceAbuseNeeded;
	

	/**
	 * @roseuid 44CF771F026F
	 */
	public UpdateJuvenileCasefileEvent() {

	}

	/**
	 * Access method for the supervisionNumber property.
	 * 
	 * @return the current value of the supervisionNumber property
	 */
	public String getSupervisionNumber() {
		return supervisionNumber;
	}

	/**
	 * Sets the value of the supervisionNumber property.
	 * 
	 * @param aSupervisionNumber
	 *            the new value of the supervisionNumber property
	 */
	public void setSupervisionNumber(String aSupervisionNumber) {
		supervisionNumber = aSupervisionNumber;
	}

	/**
	 * Access method for the supervisionEndDate property.
	 * 
	 * @return the current value of the supervisionEndDate property
	 */
	public Date getSupervisionEndDate() {
		return supervisionEndDate;
	}

	/**
	 * Sets the value of the supervisionEndDate property.
	 * 
	 * @param aSupervisionEndDate
	 *            the new value of the supervisionEndDate property
	 */
	public void setSupervisionEndDate(Date aSupervisionEndDate) {
		supervisionEndDate = aSupervisionEndDate;
	}

	/**
	 * Access method for the isInterviewRiskNeeded property.
	 * 
	 * @return the current value of the isInterviewRiskNeeded property
	 */
	public boolean isInterviewRiskNeeded() {
		return isInterviewRiskNeeded;
	}

	/**
	 * Sets the value of the isInterviewRiskNeeded property.
	 * 
	 * @param isInterviewRiskNeeded
	 *            the new value of the isInterviewRiskNeeded property
	 */
	public void setInterviewRiskNeeded(boolean isInterviewRiskNeeded) {
		this.isInterviewRiskNeeded = isInterviewRiskNeeded;
	}

	/**
	 * Access method for the isMAYSINeeded property.
	 * 
	 * @return the current value of the isMAYSINeeded property
	 */
	public boolean isMAYSINeeded() {
		return isMAYSINeeded;
	}

	/**
	 * Sets the value of the isMAYSINeeded property.
	 * 
	 * @param isMAYSINeeded
	 *            the new value of the isMAYSINeeded property
	 */
	public void setMAYSINeeded(boolean isMAYSINeeded) {
		this.isMAYSINeeded = isMAYSINeeded;
	}

	/**
	 * Access method for the isReferralRiskNeeded property.
	 * 
	 * @return the current value of the isReferralRiskNeeded property
	 */
	public boolean isReferralRiskNeeded() {
		return isReferralRiskNeeded;
	}

	/**
	 * Sets the value of the isReferralRiskNeeded property.
	 * 
	 * @param isReferralRiskNeeded
	 *            the new value of the isReferralRiskNeeded property
	 */
	public void setReferralRiskNeeded(boolean isReferralRiskNeeded) {
		this.isReferralRiskNeeded = isReferralRiskNeeded;
	}
	
	/**
	 * Access method for the activationDate property.
	 * 
	 * @return the current value of the activationDate property
	 */
	public Date getActivationDate() {
		return activationDate;
	}

	/**
	 * Sets the value of the activationDate property.
	 * 
	 * @param aActivationDate
	 *            the new value of the supervisionEndDate property
	 */
	public void setActivationDate(Date aActivationDate) {
		activationDate = aActivationDate;
	}
	
	/**
	 * Access method for the sequenceNum property.
	 * 
	 * @return the current value of the sequenceNum property
	 */
	public String getSequenceNum() {
		return sequenceNum;
	}

	/**
	 * Sets the value of the sequenceNum property.
	 * 
	 * @param aSequenceNum
	 *            the new value of the sequenceNum property
	 */
	public void setSequenceNum(String aSequenceNum) {
		sequenceNum = aSequenceNum;
	}
	/**
	 * Access method for the createDate property.
	 * 
	 * @return the current value of the createDate property
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the value of the createDate property.
	 * 
	 * @param aCreateDate
	 *            the new value of the createDate property
	 */
	public void setCreateDate(Date aCreateDate) {
		createDate = aCreateDate;
	}

	/**
	 * Access method for the supervisionTypeId property.
	 * 
	 * @return the current value of the supervisionTypeId property
	 */
	public String getSupervisionTypeId() {
		return supervisionTypeId;
	}

	/**
	 * Sets the value of the supervisionTypeId property.
	 * 
	 * @param aSupervisionTypeId
	 *            the new value of the supervisionTypeId property
	 */
	public void setSupervisionTypeId(String aSupervisionTypeId) {
		supervisionTypeId = aSupervisionTypeId;
	}
	
	/**
	 * Access method for the controllingReferralId property.
	 * 
	 * @return the current value of the controllingReferralId property
	 */
	public String getControllingReferralId() {
		return controllingReferralId;
	}

	/**
	 * Sets the value of the controllingReferralId property.
	 * 
	 * @param aControllingReferralId
	 *            the new value of the controllingReferralId property
	 */
	public void setControllingReferralId(String aControllingReferralId) {
		controllingReferralId = aControllingReferralId;
	}
	
	/**
	 * Access method for the updateDate property.
	 * 
	 * @return the current value of the updateDate property
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * Sets the value of the createDate property.
	 * 
	 * @param aUpdateDate
	 *            the new value of the updateDate property
	 */
	public void setUpdateDate(Date aUpdateDate) {
		updateDate = aUpdateDate;
	}
	
	/**
	 * Access method for the updateUser property.
	 * 
	 * @return the current value of the updateUser property
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * Sets the value of the updateUser property.
	 * 
	 * @param aUpdateUser
	 *            the new value of the updateUser property
	 */
	public void setUpdateUser(String aUpdateUser) {
		updateUser = aUpdateUser;
	}

	/**
	 * @return the jpoOfficerId
	 */
	public String getJpoOfficerId() {
		return jpoOfficerId;
	}

	/**
	 * @param jpoOfficerId the jpoOfficerId to set
	 */
	public void setJpoOfficerId(String jpoOfficerId) {
		this.jpoOfficerId = jpoOfficerId;
	}

	public String getJuvenileNum()
	{
	    return juvenileNum;
	}

	public void setJuvenileNum(String juvenileNum)
	{
	    this.juvenileNum = juvenileNum;
	}

	public boolean getPactRiskNeeded()
	{
	    return pactRiskNeeded;
	}

	public void setPactRiskNeeded(boolean pactRiskNeeded)
	{
	    this.pactRiskNeeded = pactRiskNeeded;
	}

	public boolean getHispanicIndicatorNeeded()
	{
	    return hispanicIndicatorNeeded;
	}

	public void setHispanicIndicatorNeeded(boolean hispanicIndicatorNeeded)
	{
	    this.hispanicIndicatorNeeded = hispanicIndicatorNeeded;
	}

	public boolean getSchoolHistoryNeeded()
	{
	    return schoolHistoryNeeded;
	}

	public void setSchoolHistoryNeeded(boolean schoolHistoryNeeded)
	{
	    this.schoolHistoryNeeded = schoolHistoryNeeded;
	}

	public boolean getVopEntryNeeded()
	{
	    return vopEntryNeeded;
	}

	public void setVopEntryNeeded(boolean vopEntryNeeded)
	{
	    this.vopEntryNeeded = vopEntryNeeded;
	}

	public boolean getSubstanceAbuseNeeded()
	{
	    return substanceAbuseNeeded;
	}

	public void setSubstanceAbuseNeeded(boolean substanceAbuseNeeded)
	{
	    this.substanceAbuseNeeded = substanceAbuseNeeded;
	}
	
	
	
	

}
