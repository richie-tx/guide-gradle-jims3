/*
 * Created on Oct 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.productionsupport.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;


/**
 * @author rcarter
 *
 */
public class ProductionSupportFacilityHeaderResponseEvent extends ResponseEvent
{
	private String headerId;
	private String juvenileId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String suffixName;
	private String bookingReferralNum;
	private String bookingSupervisionNum;
	private String lastSequenceNum;
	private String highestSequenceNumInUse;
	private String facilityCode;
	private String facilityName;
	private String facilityStatusCode;
	private String facilityStatus;
	private String nextHearingDate;
	private String probableCauseHearingDate;
	private String rectype;
	
	//added for Production Support Audit Data
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	public ProductionSupportFacilityHeaderResponseEvent()
	{
	}

	/**
	 * @return the headerId
	 */
	public String getHeaderId() {
		return headerId;
	}

	/**
	 * @param headerId the headerId to set
	 */
	public void setHeaderId(String headerId) {
		this.headerId = headerId;
	}

	/**
	 * @return the juvenileId
	 */
	public String getJuvenileId() {
		return juvenileId;
	}

	/**
	 * @param juvenileId the juvenileId to set
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the suffixName
	 */
	public String getSuffixName() {
		return suffixName;
	}

	/**
	 * @param suffixName the suffixName to set
	 */
	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	/**
	 * @return the bookingReferralNum
	 */
	public String getBookingReferralNum() {
		return bookingReferralNum;
	}

	/**
	 * @param bookingReferralNum the bookingReferralNum to set
	 */
	public void setBookingReferralNum(String bookingReferralNum) {
		this.bookingReferralNum = bookingReferralNum;
	}

	/**
	 * @return the bookingSupervisionNum
	 */
	public String getBookingSupervisionNum() {
		return bookingSupervisionNum;
	}

	/**
	 * @param bookingSupervisionNum the bookingSupervisionNum to set
	 */
	public void setBookingSupervisionNum(String bookingSupervisionNum) {
		this.bookingSupervisionNum = bookingSupervisionNum;
	}

	/**
	 * @return the lastSequenceNum
	 */
	public String getLastSequenceNum() {
		return lastSequenceNum;
	}

	/**
	 * @param lastSequenceNum the lastSequenceNum to set
	 */
	public void setLastSequenceNum(String lastSequenceNum) {
		this.lastSequenceNum = lastSequenceNum;
	}

	/**
	 * @return the highestSequenceNumInUse
	 */
	public String getHighestSequenceNumInUse() {
		return highestSequenceNumInUse;
	}

	/**
	 * @param highestSequenceNumInUse the highestSequenceNumInUse to set
	 */
	public void setHighestSequenceNumInUse(String highestSequenceNumInUse) {
		this.highestSequenceNumInUse = highestSequenceNumInUse;
	}

	/**
	 * @return the facilityCode
	 */
	public String getFacilityCode() {
		return facilityCode;
	}

	/**
	 * @param facilityCode the facilityCode to set
	 */
	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
	}

	/**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return facilityName;
	}

	/**
	 * @param facilityName the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	/**
	 * @return the facilityStatusCode
	 */
	public String getFacilityStatusCode() {
		return facilityStatusCode;
	}

	/**
	 * @param facilityStatusCode the facilityStatusCode to set
	 */
	public void setFacilityStatusCode(String facilityStatusCode) {
		this.facilityStatusCode = facilityStatusCode;
	}

	/**
	 * @return the facilityStatus
	 */
	public String getFacilityStatus() {
		return facilityStatus;
	}

	/**
	 * @param facilityStatus the facilityStatus to set
	 */
	public void setFacilityStatus(String facilityStatus) {
		this.facilityStatus = facilityStatus;
	}

	/**
	 * @return the nextHearingDate
	 */
	public String getNextHearingDate() {
		return nextHearingDate;
	}

	/**
	 * @param nextHearingDate the nextHearingDate to set
	 */
	public void setNextHearingDate(String nextHearingDate) {
		this.nextHearingDate = nextHearingDate;
	}

	/**
	 * @return the probableCauseHearingDate
	 */
	public String getProbableCauseHearingDate() {
		return probableCauseHearingDate;
	}

	/**
	 * @param probableCauseHearingDate the probableCauseHearingDate to set
	 */
	public void setProbableCauseHearingDate(String probableCauseHearingDate) {
		this.probableCauseHearingDate = probableCauseHearingDate;
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

	public String getRectype()
	{
	    return rectype;
	}

	public void setRectype(String rectype)
	{
	    this.rectype = rectype;
	}

}
