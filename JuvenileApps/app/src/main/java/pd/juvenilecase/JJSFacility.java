package pd.juvenilecase;

import java.util.Date;
import java.util.Iterator;

import messaging.contact.domintf.IName;
import messaging.facility.GetJuvenileFacilityDetailsEvent;
import messaging.juvenilecase.GetJuvenileDetentionFacilitiesEvent;
import messaging.juvenilecase.GetJuvenileFacilityHistoricalReceiptsEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.util.DateUtil;
import naming.PDCodeTableConstants;
import naming.PDJuvenileConstants;

import org.apache.commons.lang.StringUtils;

import pd.codetable.SimpleCodeTableHelper;
import pd.codetable.criminal.JuvenileAdmitReasons;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.contact.officer.OfficerProfile;
import pd.juvenile.Juvenile;
import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

/**
 * 
 * @roseuid 43BA9F090142
 */
public class JJSFacility extends PersistentObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String admitReason;
	private Date admitDate;
	private String admitTime;
	private String juvenileNumber;
	private String referralNumber;
	private Date originalAdmitDate;
	private String originalAdmitTime;
	private String originalAdmitOID;//added for User-story #44549
	private String postAdmitOID;
	
	private String releaseBy;
	private Date releaseDate;
	private String releaseTime;
	private String releaseTo;
	private String releaseReason;
	
	
	private String facilitySequenceNumber;
	private String secureStatus;
	private String lockerNumber;
	private String receiptNumber;
	private String admittedByJPO;
	private String returnReason;
	
	//Added for Facility Report
	private String floorNum;
	private String unit;
	private String roomNum;
	private String releaseAuthorizedBy;
	
	
	/**
	 * Properties for juvenile
	 */
	private Juvenile juvenile = null;
	private String juvenileId;
	private String dob; //added for US 169455 
	
	//changes for facility Starts
	private String detainedFacility;
	private String specialAttention;
	private String saReasonCode;
	private String saReasonOtherComments;
	
	private String admitAuthority;
	private String bookingSupervisionNum;
	private String detentionStatus;
	
	private String currentOffense;
	private String currentReferral;
	private String currentSupervisionNum;
	
	private Date detainedDate;
	private String detainedByInd;
	
	private String escapeCode;
	private String comments;
	private String escapeAttempts;
	private String escapeAttemptComments;
	
	private boolean locationChangeFlag;
	private boolean reasonChangeFlag;
	private boolean secureIndicatorChangeFlag;
	
	private String tempReleaseReasonCd;
	private String tempReleaseOtherComments;
	
	private String outcome;
	private String changeExplanation;
	private String transferToFacility;
	private String multipleOccupyUnit; 
	private String tjpcseqnum;
	
	private Date returnDate;
	private String returnStatus;
	private String returnTime;
	
	private String recType;
	private boolean otherChangeFlag;
	
	//US #32248
	private Date relocationDate;
	private String relocationTime;
	
	private Date lastChangeDate;
	private String lcTime;
	private Date nextHearingDate;
	
	//US 88627
	private String lcUser;
	
	private String riskAnalysisId;
	private int tjjdFacilityId;
	private String tjjdFundingSrc;
	private String vendorLocation;
	private String custodylastName;
	private String custodyfirstName;
	private String avgCostPerDay;
	private String  originaladmitSEQNUM;

	/**
	 * 
	 * @roseuid 43BA9F090142
	 */
	public JJSFacility()
	{
	}
	
	/**
	 * @param attributeName
	 * @param attributeValue
	 * @return Iterator
	 */
	public static JJSFacility find(String attributeName, String attributeValue){
	    IHome home = new Home();
	    return (JJSFacility) home.find(attributeName, attributeValue, JJSFacility.class);
	}
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName,attributeValue,JJSFacility.class);
	}	
	/**
	* @roseuid 42A99B9802DD
	*/
	static public Iterator findAll(GetJuvenileDetentionFacilitiesEvent event) {
		IHome home = new Home();
		return home.findAll(event, JJSFacility.class);	
	}
	
	/**
	* @roseuid 42A99B9802DD
	*/
	static public Iterator findAll(GetJuvenileFacilityDetailsEvent event) {
		IHome home = new Home();
		return home.findAll(event, JJSFacility.class);	
	}
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	static public Iterator findAll(GetJuvenileFacilityHistoricalReceiptsEvent event) {
		IHome home = new Home();
		return home.findAll(event, JJSFacility.class);	
	}
	
	   /**
	* @return 
	* @param event
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JJSFacility.class);
	}
	
	   /**
	* @return
	* 
	*/
	static public Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(JJSFacility.class);
	}
	
	/**
	 * 
	 * @return Returns the returnStatus.
	 */
	public String getReturnStatus() {
		return returnStatus;
	}

	/**
	 * 
	 * @param returnStatus The returnStatus to set.
	 */
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	
	
	/**
	 * 
	 * @return Returns the secureStatus.
	 */
	public String getSecureStatus() {
		return secureStatus;
	}

	/**
	 * 
	 * @param secureStatus The secureStatus to set.
	 */
	public void setSecureStatus(String secureStatus) {
		if (this.secureStatus != secureStatus)
		{
			markModified();
		}
		this.secureStatus = secureStatus;
	}

	/**
	 * 
	 * @return Returns the facilitySequenceNumber.
	 */
	public String getFacilitySequenceNumber() {
		return facilitySequenceNumber;
	}

	/**
	 * 
	 * @param facilitySequenceNumber The facilitySequenceNumber to set.
	 */
	public void setFacilitySequenceNumber(String facilitySequenceNumber) {
		this.facilitySequenceNumber = facilitySequenceNumber;
	}


	/**
	 * 
	 * @return Returns the admitDate.
	 */
	public Date getAdmitDate()
	{
		fetch();
		return admitDate;
	}

	/**
	 * 
	 * @param admitDate The admitDate to set.
	 */
	public void setAdmitDate(Date admitDate)
	{
		this.admitDate = admitDate;
	}
	
	/**
	 * setter with integer
	 * @param admitDate
	 */
	public void setAdmitDate( Integer admitDate)
	{
		Date calculatedDate = null;
		try {
			if(admitDate!=null)
			calculatedDate = DateUtil.IntToDate(admitDate, DateUtil.DATE_FMT_2);					
		} catch (ParseRuntimeException e) {
			e.printStackTrace();
		}
		this.admitDate = calculatedDate;
	}

	/**
	 * 
	 * @return Returns the setReturnDate.
	 */
	public Date getReturnDate()
	{
		fetch();
		return returnDate;
	}

	/**
	 * 
	 * @param setReturnDate The admitDate to set.
	 */
	public void setReturnDate(Date returnDate)
	{
		this.returnDate = returnDate;
		if (this.returnDate != returnDate)
		{
			markModified();
		}
	}
	
	/**
	 * setter with integer
	 * @param setReturnDate
	 */
	public void setReturnDate( Integer returnDate)
	{
		Date calculatedDate = null;
		try {
			if(returnDate!=null)
			calculatedDate = DateUtil.IntToDate(returnDate, DateUtil.DATE_FMT_2);					
		} catch (ParseRuntimeException e) {
			e.printStackTrace();
		}
		this.returnDate = calculatedDate;
	}
	
	/**
	 * 
	 * @return Returns the admitReason.
	 */
	public String getAdmitReason()
	{
		fetch();
		return admitReason;
	}

	/**
	 * 
	 * @param admitReason The admitReason to set.
	 */
	public void setAdmitReason(String admitReason)
	{
		this.admitReason = admitReason;
	}

	/**
	 * @return Returns the admitTime.
	 */
	public String getAdmitTime() {
		fetch();
		return admitTime;
	}
	/**
	 * @param admitTime The admitTime to set.
	 */
	public void setAdmitTime(String admitTime) {
		this.admitTime = admitTime;
	}
	
	/**
	 * @return Returns the returnTime.
	 */
	public String getReturnTime() {
		fetch();
		return returnTime;
	}
	/**
	 * @param returnTime The returnTime to set.
	 */
	public void setReturnTime(String returnTime) {
		if (this.returnTime != returnTime)
		{
			markModified();
		}
		this.returnTime = returnTime;
	}
	/**
	 * 
	 * @return Returns the juvenile.
	 */
	public Juvenile getJuvenile()
	{
		initJuvenile();
		return juvenile;
	}	

	/**
	 * 
	 * @return Returns the juvenileNumber.
	 */
	public String getJuvenileNumber()
	{
		fetch();
		return juvenileNumber;
	}

	/**
	 * 
	 * @param juvenileNumber The juvenileNumber to set.
	 */
	public void setJuvenileNumber(String juvenileNumber)
	{
		this.juvenileNumber = juvenileNumber;
	}

	/**
	 * 
	 * @return Returns the admittedByJPO.
	 */
	public String getAdmittedByJPO()
	{
		fetch();
		return admittedByJPO;
	}

	/**
	 * 
	 * @param admittedByJPO The admittedByJPO to set.
	 */
	public void setAdmittedByJPO(String admittedByJPO)
	{
		this.admittedByJPO = admittedByJPO;
	}

	/**
	 * 
	 * @return Returns the originalAdmitDate.
	 */
	public Date getOriginalAdmitDate()
	{
		fetch();
		return originalAdmitDate;
	}

	/**
	 * 
	 * @param originalAdmitDate The originalAdmitDate to set.
	 */
	public void setOriginalAdmitDate(Date originalAdmitDate)
	{
		this.originalAdmitDate = originalAdmitDate;
	}

	/**
	 * 
	 * @return Returns the referralNumber.
	 */
	public String getReferralNumber()
	{
		fetch();
		return referralNumber;
	}

	/**
	 * 
	 * @param referralNumber The referralNumber to set.
	 */
	public void setReferralNumber(String referralNumber)
	{
		this.referralNumber = referralNumber;
	}

	/**
	 * 
	 * @return Returns the releaseBy.
	 */
	public String getReleaseBy()
	{
		fetch();
		return releaseBy;
	}

	/**
	 * 
	 * @param releaseBy The releaseBy to set.
	 */
	public void setReleaseBy(String releaseBy)
	{
		if (this.releaseBy != releaseBy)
		{
			markModified();
		}
		this.releaseBy = releaseBy;
	}

	/**
	 * 
	 * @return Returns the releaseDate.
	 */
	public Date getReleaseDate()
	{
		fetch();
		return releaseDate;
	}

	/**
	 * 
	 * @param releaseDate The releaseDate to set.
	 */
	public void setReleaseDate(Date releaseDate)
	{
		if (this.releaseDate != releaseDate)
		{
			markModified();
		}
		this.releaseDate = releaseDate;
	}
	
	/**
	 * @return Returns the releaseTime.
	 */
	public String getReleaseTime() {
		fetch();
		return releaseTime;
	}
	/**
	 * @param releaseTime The releaseTime to set.
	 */
	public void setReleaseTime(String releaseTime) {
		if (this.releaseTime != releaseTime)
		{
			markModified();
		}
		this.releaseTime = releaseTime;
	}
	/**
	 * 
	 * @return Returns the releaseTo.
	 */
	public String getReleaseTo()
	{
		fetch();
		return releaseTo;
	}

	/**
	 * 
	 * @param releaseTo The releaseTo to set.
	 */
	public void setReleaseTo(String releaseTo)
	{
		if (this.releaseTo != releaseTo)
		{
			markModified();
		}
		this.releaseTo = releaseTo;
	}
	

	/**
	 * Set the reference value to class :: pd.juvenile.Juvenile
	 */
	public void setJuvenileId(String juvenileId)
	{
		this.juvenileId = juvenileId;
	}

	/**
	 * Get the reference value to class :: pd.juvenile.Juvenile
	 */
	public String getJuvenileId()
	{
		fetch();
		return juvenileId;
	}

	/**
	 * @return the lockerNumber
	 */
	public String getLockerNumber() {
		fetch();
		return lockerNumber;
	}

	/**
	 * @param lockerNumber the lockerNumber to set
	 */
	public void setLockerNumber(String lockerNumber) {
		this.lockerNumber = lockerNumber;
	}

	/**
	 * @return the receiptNumber
	 */
	public String getReceiptNumber() {
		fetch();
		return receiptNumber;
	}

	/**
	 * @param receiptNumber the receiptNumber to set
	 */
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	
	/**
	 * @return the floorNum
	 */
	public String getFloorNum() {
		fetch();
		return floorNum;
	}

	/**
	 * @param floorNum the floorNum to set
	 */
	public void setFloorNum(String floorNum) {
		if (this.floorNum != floorNum)
		{
			markModified();
		}
		this.floorNum = floorNum;
	}
	
	/**
	 * @return the unit
	 */
	public String getUnit() {
		fetch();
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		if (this.unit != unit)
		{
			markModified();
		}
		this.unit = unit;
	}
	
	/**
	 * @return the roomNum
	 */
	public String getRoomNum() {
		fetch();
		return roomNum;
	}

	/**
	 * @param roomNum the roomNum to set
	 */
	public void setRoomNum(String roomNum) {
		if (this.roomNum != roomNum)
		{
			markModified();
		}
		this.roomNum = roomNum;
	}
	
	/**
	 * 
	 * @return Returns the releaseAuthorizedBy.
	 */
	public String getReleaseAuthorizedBy()
	{
		fetch();
		return releaseAuthorizedBy;
	}

	/**
	 * 
	 * @param releaseAuthorizedBy The releaseAuthorizedBy to set.
	 */
	public void setReleaseAuthorizedBy(String releaseAuthorizedBy)
	{
		if (this.releaseAuthorizedBy != releaseAuthorizedBy)
		{
			markModified();
		}
		this.releaseAuthorizedBy = releaseAuthorizedBy;
	}


	/**
	 * Initialize class relationship to class pd.juvenile.Juvenile
	 */
	private void initJuvenile()
	{
		if (juvenile == null)
		{
			juvenile = (Juvenile) new mojo.km.persistence.Reference(juvenileId, Juvenile.class)
					.getObject();
		}
	}
	
	/***Facility Getters and setters Starts**/

	/**
	 * set the type reference for class member juvenile
	 */
	public void setJuvenile(Juvenile juvenile)
	{
		setJuvenileId("" + juvenile.getOID());
		this.juvenile = (Juvenile) new mojo.km.persistence.Reference(juvenile).getObject();
	}

	/**
	 * @return the detainedFacility
	 */
	public String getDetainedFacility() {
		fetch();
		return detainedFacility;
	}

	/**
	 * @param detainedFacility the detainedFacility to set
	 */
	public void setDetainedFacility(String detainedFacility) {
		this.detainedFacility = detainedFacility;
	}
	
	public String getAvgCostPerDay()
	{
	    fetch();
	    return avgCostPerDay;
	}
	
	public void setAvgCostPerDay(String avgCostPerDay)
	{
	    this.avgCostPerDay = avgCostPerDay;
	}

	/**
	 * @return the saReasonCode
	 */
	public String getSaReasonCode() {
		fetch();
		return saReasonCode;
	}

	/**
	 * @param saReasonCode the saReasonCode to set
	 */
	public void setSaReasonCode(String saReasonCode) {
		if (this.saReasonCode != saReasonCode)
		{
			markModified();
		}
		this.saReasonCode = saReasonCode;
	}

	/**
	 * @return the admitAuthority
	 */
	public String getAdmitAuthority() {
		fetch();
		return admitAuthority;
	}

	/**
	 * @param admitAuthority the admitAuthority to set
	 */
	public void setAdmitAuthority(String admitAuthority) {
		this.admitAuthority = admitAuthority;
	}

	/**
	 * @param bookingSupervisionNum the bookingSupervisionNum to set
	 */
	public void setBookingSupervisionNum(String bookingSupervisionNum) {
		
	    if (this.bookingSupervisionNum != bookingSupervisionNum)
		{
			markModified();
		}
	    this.bookingSupervisionNum = bookingSupervisionNum;
	}

	/**
	 * @return the bookingSupervisionNum
	 */
	public String getBookingSupervisionNum() {
		fetch();
		return bookingSupervisionNum;
	}

	/**
	 * @param specialAttention the specialAttention to set
	 */
	public void setSpecialAttention(String specialAttention) {
		if (this.specialAttention != specialAttention)
		{
			markModified();
		}
		this.specialAttention = specialAttention;
	}

	/**
	 * @return the specialAttention
	 */
	public String getSpecialAttention() {
		fetch();
		return specialAttention;
	}

	/**
	 * @param detentionStatus the detentionStatus to set
	 */
	public void setDetentionStatus(String detentionStatus) {
		this.detentionStatus = detentionStatus;
	}

	/**
	 * @return the detentionStatus
	 */
	public String getDetentionStatus() {
		fetch();
		return detentionStatus;
	}
	/**
	 * @return the currentOffense
	 */
	public String getCurrentOffense() {
		fetch();
		return currentOffense;
	}

	/**
	 * @param currentOffense the currentOffense to set
	 */
	public void setCurrentOffense(String currentOffense) {
		this.currentOffense = currentOffense;
	}

	/**
	 * @return the currentReferral
	 */
	public String getCurrentReferral() {
		fetch();
		return currentReferral;
	}

	/**
	 * @param currentReferral the currentReferral to set
	 */
	public void setCurrentReferral(String currentReferral) {
		this.currentReferral = currentReferral;
	}

	/**
	 * @return the currentSupervisionNum
	 */
	public String getCurrentSupervisionNum() {
		fetch();
		return currentSupervisionNum;
	}

	/**
	 * @param currentSupervisionNum the currentSupervisionNum to set
	 */
	public void setCurrentSupervisionNum(String currentSupervisionNum) {
	    if (this.currentSupervisionNum != currentSupervisionNum)
		{
			markModified();
		}
	    this.currentSupervisionNum = currentSupervisionNum;
	}

	/**
	 * @return the detainedDate
	 */
	public Date getDetainedDate() {
		fetch();
		return detainedDate;
	}

	/**
	 * @param detainedDate the detainedDate to set
	 */
	public void setDetainedDate(Date detainedDate) {
		this.detainedDate = detainedDate;
	}

	/**
	 * @return the escapeCode
	 */
	public String getEscapeCode() {
		fetch();
		return escapeCode;
	}

	/**
	 * @param escapeCode the escapeCode to set
	 */
	public void setEscapeCode(String escapeCode) {
		if (this.escapeCode != escapeCode)
		{
			markModified();
		}
		
		this.escapeCode = escapeCode;
	}

	/**
	 * @return the detainedByInd
	 */
	public String getDetainedByInd() {
		fetch();
		return detainedByInd;
	}

	/**
	 * @param detainedByInd the detainedByInd to set
	 */
	public void setDetainedByInd(String detainedByInd) {
		this.detainedByInd = detainedByInd;
	}

	/**
	 * @return the saReasonOtherComments
	 */
	public String getSaReasonOtherComments() {
		fetch();
		return saReasonOtherComments;
	}

	/**
	 * @param saReasonOtherComments the saReasonOtherComments to set
	 */
	public void setSaReasonOtherComments(String saReasonOtherComments) {
		if (this.saReasonOtherComments != saReasonOtherComments)
		{
			markModified();
		}
		this.saReasonOtherComments = saReasonOtherComments;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		fetch();
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		if (this.comments != comments)
		{
			markModified();
		}
		this.comments = comments;
	}

	/**
	 * @param escapeAttemptComments the escapeAttemptComments to set
	 */
	public void setEscapeAttemptComments(String escapeAttemptComments) {
		if (this.escapeAttemptComments != escapeAttemptComments)
		{
			markModified();
		}
		this.escapeAttemptComments = escapeAttemptComments;
	}

	/**
	 * @return the escapeAttemptComments
	 */
	public String getEscapeAttemptComments() {
		fetch();
		return escapeAttemptComments;
	}

	/**
	 * @param escapeAttempts the escapeAttempts to set
	 */
	public void setEscapeAttempts(String escapeAttempts) {
		if (this.escapeAttempts != escapeAttempts)
		{
			markModified();
		}
		this.escapeAttempts = escapeAttempts;
	}

	/**
	 * @return the escapeAttempts
	 */
	public String getEscapeAttempts() {
		fetch();
		return escapeAttempts;
	}

	/**
	 * @return the tempReleaseReasonCd
	 */
	public String getTempReleaseReasonCd() {
		fetch();
		return tempReleaseReasonCd;
	}

	/**
	 * @param tempReleaseReasonCd the tempReleaseReasonCd to set
	 */
	public void setTempReleaseReasonCd(String tempReleaseReasonCd) {
		this.tempReleaseReasonCd = tempReleaseReasonCd;
	}

	/**
	 * @return the tempReleaseOtherComments
	 */
	public String getTempReleaseOtherComments() {
		fetch();
		return tempReleaseOtherComments;
	}

	/**
	 * @param tempReleaseOtherComments the tempReleaseOtherComments to set
	 */
	public void setTempReleaseOtherComments(String tempReleaseOtherComments) {
		this.tempReleaseOtherComments = tempReleaseOtherComments;
	}

	/**
	 * @return the outcome
	 */
	public String getOutcome() {
		fetch();
		return outcome;
	}

	/**
	 * @param outcome the outcome to set
	 */
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	/**
	 * @return the changeExplanation
	 */
	public String getChangeExplanation() {
		fetch();
		return changeExplanation;
	}

	/**
	 * @param changeExplanation the changeExplanation to set
	 */
	public void setChangeExplanation(String changeExplanation) {
		if (this.changeExplanation != changeExplanation)
		{
			markModified();
		}
		this.changeExplanation = changeExplanation;
	}

	/**
	 * @param transferToFacility the transferToFacility to set
	 */
	public void setTransferToFacility(String transferToFacility) {
		this.transferToFacility = transferToFacility;
	}

	/**
	 * @return the transferToFacility
	 */
	public String getTransferToFacility() {
		fetch();
		return transferToFacility;
	}

	public void setOriginalAdmitTime(String originalAdmitTime) {
		this.originalAdmitTime = originalAdmitTime;
	}

	public String getOriginalAdmitTime() {
		fetch();
		return originalAdmitTime;
	}

	/**
	 * @return the multipleOccupyUnit
	 */
	public String getMultipleOccupyUnit() {
		fetch();
		return multipleOccupyUnit;
	}

	/**
	 * @param multipleOccupyUnit the multipleOccupyUnit to set
	 */
	public void setMultipleOccupyUnit(String multipleOccupyUnit) {
		if (this.multipleOccupyUnit != multipleOccupyUnit)
		{
			markModified();
		}
		this.multipleOccupyUnit = multipleOccupyUnit;
	}

	/**
	 * @return the tjpcseqnum
	 */
	public String getTjpcseqnum() {
		return tjpcseqnum;
	}

	/**
	 * @param tjpcseqnum the tjpcseqnum to set
	 */
	public void setTjpcseqnum(String tjpcseqnum) {
		this.tjpcseqnum = tjpcseqnum;
	}

	public boolean isLocationChangeFlag() {
		fetch();
		return locationChangeFlag;
	}

	public void setLocationChangeFlag(boolean locationChangeFlag) {
		if (this.locationChangeFlag != locationChangeFlag)
		{
			markModified();
		}
		this.locationChangeFlag = locationChangeFlag;
	}

	public boolean isReasonChangeFlag() {
		fetch();
		return reasonChangeFlag;
	}

	public void setReasonChangeFlag(boolean reasonChangeFlag) {
		if (this.reasonChangeFlag != reasonChangeFlag)
		{
			markModified();
		}
		this.reasonChangeFlag = reasonChangeFlag;
	}

	public boolean isSecureIndicatorChangeFlag() {
		fetch();
		return secureIndicatorChangeFlag;
	}

	public void setSecureIndicatorChangeFlag(boolean secureIndicatorChangeFlag) {
		if (this.secureIndicatorChangeFlag != secureIndicatorChangeFlag)
		{
			markModified();
		}
		this.secureIndicatorChangeFlag = secureIndicatorChangeFlag;
	}

	public boolean isOtherChangeFlag() {
		fetch();
		return otherChangeFlag;
	}

	public void setOtherChangeFlag(boolean otherChangeFlag) {
		if (this.otherChangeFlag != otherChangeFlag)
		{
			markModified();
		}
		this.otherChangeFlag = otherChangeFlag;
	}

	public String getRecType() {
		fetch();
		return recType;
	}

	public void setRecType(String recType) {
		if (this.recType != recType)
		{
			markModified();
		}
		this.recType = recType;
	}

	public String getReturnReason() {
		fetch();
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		if (this.returnReason != returnReason)
		{
			markModified();
		}
		this.returnReason = returnReason;
	}

	public String getReleaseReason() {
		return releaseReason;
	}

	public void setReleaseReason(String releaseReason) {
		this.releaseReason = releaseReason;
	}

	/**
	 * @return the originalAdmitOID
	 */
	public String getOriginalAdmitOID() {
		return originalAdmitOID;
	}

	/**
	 * @param originalAdmitOID the originalAdmitOID to set
	 */
	public void setOriginalAdmitOID(String originalAdmitOID) {
		this.originalAdmitOID = originalAdmitOID;
	}
	
	
	/**
	 * 
	 * @return Returns the relocationDate.
	 */
	public Date getRelocationDate()
	{
		fetch();
		return relocationDate;
	}

	/**
	 * 
	 * @param relocationDate The relocationDate to set.
	 */
	public void setRelocationDate(Date relocationDate)
	{
		if (this.relocationDate == null || !this.relocationDate.equals(relocationDate))
		{
			markModified();
		}
		this.relocationDate = relocationDate;
	}
	/**
	 * 
	 * @return Returns the relocationTime.
	 */
	public String getRelocationTime()
	{
		fetch();
		return relocationTime;
	}

	/**
	 * 
	 * @param relocationTime The relocationTime to set.
	 */
	public void setRelocationTime(String relocationTime)
	{
		if (this.relocationTime == null || !this.relocationTime.equals(relocationTime))
		{
			markModified();
		}
		this.relocationTime = relocationTime;
	}
	
	
	/**
	 * 
	 * @return Returns the lastChangeDate.
	 */
	public Date getLastChangeDate()
	{
		fetch();
		return lastChangeDate;
	}

	/**
	 * 
	 * @param lastChangeDate The lastChangeDate to set.
	 */
	public void setLastChangeDate(Date lastChangeDate)
	{
		if (this.lastChangeDate == null || !this.lastChangeDate.equals(lastChangeDate))
		{
			markModified();
		}
		this.lastChangeDate = lastChangeDate;
	}
	
	/**
	 * 
	 * @return Returns the lcTime.
	 */
	public String getLcTime()
	{
		fetch();
		return lcTime;
	}

	/**
	 * 
	 * @param lcTime The lcTime to set.
	 */
	public void setLcTime(String lcTime)
	{
		if (this.lcTime == null || !this.lcTime.equals(lcTime))
		{
			markModified();
		}
		this.lcTime = lcTime;
	}
	
	/**
	 * 
	 * @return Returns the nextHearingDate.
	 */
	public Date getNextHearingDate()
	{
		fetch();
		return nextHearingDate;
	}

	/**
	 * 
	 * @param nextHearingDate The nextHearingDate to set.
	 */
	public void setNextHearingDate(Date nextHearingDate)
	{
		if (this.nextHearingDate == null || !this.nextHearingDate.equals(nextHearingDate))
		{
			markModified();
		}
		this.nextHearingDate = nextHearingDate;
	}
	
	/**
	 * 
	 * @return Returns the lcUser.
	 */
	public String getLcUser()
	{
		fetch();
		return lcUser;
	}

	/**
	 * 
	 * @param lcUser The lcUser to set.
	 */
	public void setLcUser(String lcUser)
	{
		if (this.lcUser == null || !this.lcUser.equals(lcUser))
		{
			markModified();
		}
		this.lcUser = lcUser;
	}
	
	public String getRiskAnalysisId()
	{
	    fetch();
	    return riskAnalysisId;
	}

	public void setRiskAnalysisId(String riskAnalysisId)
	{
	    if (this.riskAnalysisId != riskAnalysisId) {
		markModified();
	    }
	    
	    this.riskAnalysisId = riskAnalysisId;
	}
	
	
	
	public int getTjjdFacilityId()
	{
	    fetch();
	    return tjjdFacilityId;
	}

	public void setTjjdFacilityId(int tjjdFacilityId)
	{
	    if ( this.tjjdFacilityId > 0 || this.tjjdFacilityId != tjjdFacilityId) {
		markModified();
	    }
	    
	    this.tjjdFacilityId = tjjdFacilityId;
	}

	public String getTjjdFundingSrc()
	{
	    fetch();
	    return tjjdFundingSrc;
	}

	public void setTjjdFundingSrc(String tjjdFundingSrc)
	{
	    if ( this.tjjdFundingSrc == null || !this.tjjdFundingSrc.equals( tjjdFundingSrc)) {
		markModified();
	    }
	    this.tjjdFundingSrc = tjjdFundingSrc;
	}

	public String getHoursMinsFromTime(String time){
		if(time!=null && !time.equals("")){
			int end =0;
			String[] getTime = StringUtils.split(time, '.');
			if(getTime!=null && getTime.length>0){
				end = getTime[0].lastIndexOf(":");
			}
			if(end!=-1){
				time = time.substring(0, end);
			}
			return time;
		}
		return null;
	}
	
	public String getVendorLocation() {
		fetch();
		return vendorLocation;
	}

	public void setVendorLocation(String vendorLocation) {
		this.vendorLocation = vendorLocation;
	}
	public String getCustodylastName()
	{
	    fetch();
	    return custodylastName;
	}

	public void setCustodylastName(String custodylastName)
	{
	    this.custodylastName = custodylastName;
	}
	
	public String getCustodyfirstName()
	{
	    fetch();
	    return custodyfirstName;
	}

	public void setCustodyfirstName(String custodyfirstName)
	{
	    this.custodyfirstName = custodyfirstName;
	}
	
	
	
	public String getOriginaladmitSEQNUM()
	{
	    return originaladmitSEQNUM;
	}

	public void setOriginaladmitSEQNUM(String originaladmitSEQNUM)
	{
	    this.originaladmitSEQNUM = originaladmitSEQNUM;
	}
	
	

	public String getPostAdmitOID()
	{
	    return postAdmitOID;
	}

	public void setPostAdmitOID(String postAdmitOID)
	{
	    this.postAdmitOID = postAdmitOID;
	}

	/**
	 * 
	 * @return
	 */
	public JuvenileDetentionFacilitiesResponseEvent valueObject(){
	    
	    
	    JuvenileDetentionFacilitiesResponseEvent resp = new JuvenileDetentionFacilitiesResponseEvent();
		resp.setJuvNum( this.getJuvenileNumber());
		resp.setReferralNumber( this.getReferralNumber());
		resp.setAdmitDate( this.getAdmitDate());
		resp.setAdmitTime(JuvenileFacilityHelper.getHoursMinsFromTime( this.getAdmitTime()));
		resp.setFacilityStatus( this.getDetentionStatus());
		if( this.getDetentionStatus()!=null && ! this.getDetentionStatus().isEmpty()){
			resp.setFacilityStatusDesc(SimpleCodeTableHelper.getDescrByCode("FACILITY_STATUS",  this.getDetentionStatus())); //set facility status desc.
		}
		resp.setDetentionStatus( this.getDetentionStatus());
		// added the Admit Reason and Admit Reason Desc below for Task#42493, US 38986
		resp.setAdmitReason( this.getAdmitReason());
		if( this.getAdmitReason()!=null && ! this.getAdmitReason().isEmpty()){
		    JuvenileAdmitReasons reason = JuvenileAdmitReasons.find("code", this.getAdmitReason());
		    
		    	if(reason != null && reason.getDescription() != null){
		    	    resp.setAdmitReasonDesc(reason.getDescription());
		    	}			
		}

		resp.setOriginalAdmitDate( this.getOriginalAdmitDate());
		resp.setOriginalAdmitOID( this.getOriginalAdmitOID()); //added for user-story44549
		resp.setSecureStatus( this.getSecureStatus());
		resp.setReleaseTime(JuvenileFacilityHelper.getHoursMinsFromTime( this.getReleaseTime()));
		resp.setReleaseDate( this.getReleaseDate());
		resp.setReleaseDateStr(DateUtil.dateToString( this.getReleaseDate(), DateUtil.DATE_FMT_1));
		resp.setReleaseAuthorizedBy( this.getReleaseAuthorizedBy());
		if( this.getReleaseAuthorizedBy()!=null&&! this.getReleaseAuthorizedBy().isEmpty()){
			if( this.getReleaseAuthorizedBy().trim().length()< 4){
			    resp.setRelAuthByDesc(getOfficerProfile( "UV" + this.getReleaseAuthorizedBy()));
			}else{
			    resp.setRelAuthByDesc(getOfficerProfile( this.getReleaseAuthorizedBy()));
			}
		}
		resp.setReleaseTo( this.getReleaseTo());
		if( this.getReleaseTo()!=null&&! this.getReleaseTo().isEmpty()){
			resp.setRelToDesc( ComplexCodeTableHelper.getDescrByCode(ComplexCodeTableHelper.getReleasedFromDetentionCodes(), this.getReleaseTo().trim()));
		}
		resp.setReleaseBy( this.getReleaseBy());
		if( this.getReleaseBy()!=null && ! this.getReleaseBy().isEmpty()){
		if( this.getReleaseBy().trim().length()<4){
				//IName name =SecurityUIHelper.getUserName("UV"+ this.getReleaseBy());
			resp.setRelByDesc(getOfficerProfile("UV"+ this.getReleaseBy()));
				
		}
		//below code for the 'else condition' added for Bug#42059: for the fields from SQl and already have the UV (Eg UVB6K)we need not append the 'UV'
		else {
		    	resp.setRelByDesc(getOfficerProfile( this.getReleaseBy()));
			
		     }
		}
		if( this.getReleaseReason()!=null){
			resp.setReleaseReason( this.getReleaseReason());
			resp.setReleaseReasonDesc( SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RELEASE_REASON, this.getReleaseReason()));
		}
		if ( this.getFacilitySequenceNumber() == null ||  this.getFacilitySequenceNumber().equals("")) {
			resp.setFacilitySequenceNumber("0");
		} else {
			resp.setFacilitySequenceNumber( this.getFacilitySequenceNumber());
		}
		
		resp.setLockerNumber( this.getLockerNumber());
		resp.setReceiptNumber( this.getReceiptNumber());
		resp.setSpecialAttention( this.getSpecialAttention());
		if(resp.getSpecialAttention()!=null && !resp.getSpecialAttention().isEmpty()){
			resp.setSpecialAttentionDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SPECIAL_ATTENTION,resp.getSpecialAttention()));
		}else{
			resp.setSpecialAttention("NO");
			resp.setSpecialAttentionDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SPECIAL_ATTENTION,resp.getSpecialAttention()));
		}
		resp.setSaReason( this.getSaReasonCode());
		StringBuffer saReasonDescBuf = new StringBuffer();
		boolean saReasonOT= false;
		if( this.getSaReasonCode()!=null && ! this.getSaReasonCode().isEmpty()){
			if( this.getSaReasonCode().contains(",")){
				String[] saReasonCodes = StringUtils.split( this.getSaReasonCode(), ",");
				for (int i = 0; i < saReasonCodes.length; i++) {
					if(saReasonCodes[i]!=null && !saReasonCodes[i].isEmpty()){
						saReasonDescBuf.append(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SPECIAL_ATTENTION_REASON, saReasonCodes[i]));
						if(i!=saReasonCodes.length-1){
							saReasonDescBuf.append(",");
						}
					}
					if(saReasonCodes[i]!=null && !saReasonCodes[i].isEmpty() && saReasonCodes[i].equalsIgnoreCase("OT"))
						saReasonOT=true;
				}
			}else{
				if( this.getSaReasonCode()!=null && ! this.getSaReasonCode().isEmpty()){
					saReasonDescBuf.append(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SPECIAL_ATTENTION_REASON,  this.getSaReasonCode()));
				}
			}
		}
		resp.setSaReasonDesc(saReasonDescBuf.toString());
		//get the Special Attention Reason Other Comments from the new table	
		if( this.getOID()!=null && ! this.getOID().isEmpty()){
			if(saReasonOT)
				resp.setSaReasonOtherComments(JuvenileFacilityHelper.getMostRecentSplAttnReasonComments( this.getJuvenileNumber(), this.getOID()));
			resp.setComments(JuvenileFacilityHelper.getFacilityAdmissionComments( this.getJuvenileNumber(), this.getOID())); //set admission comments.
		}
		
		resp.setAdmitAuthority( this.getAdmitAuthority());
		//String admitAuthM204Code=null;
		if( this.getAdmitAuthority()!=null && ! this.getAdmitAuthority().isEmpty()){
			
			//IName name =getUserName( this.getAdmitAuthority());
			resp.setAdmitAuthorityDesc(getOfficerProfile(this.getAdmitAuthority()));
		}
		
		
		resp.setAdmittedByJPO( this.getAdmittedByJPO());
		if( this.getAdmittedByJPO()!=null && ! this.getAdmittedByJPO().isEmpty()){
			if( this.getAdmittedByJPO().trim().length()<4){
				//IName name =SecurityUIHelper.getUserName("UV"+ this.getAdmittedByJPO());
				// name =getUserName("UV"+ this.getAdmittedByJPO());
				resp.setAdmittedByJPODesc(getOfficerProfile("UV"+ this.getAdmittedByJPO()));	
			}else{
			    resp.setAdmittedByJPODesc(getOfficerProfile(this.getAdmittedByJPO()));	
			}
		}
		resp.setDetainedFacility( this.getDetainedFacility());
		if( this.getDetainedFacility()!=null && ! this.getDetainedFacility().equals("")){
			resp.setDetainedFacilityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, this.getDetainedFacility()));
		}
		
		resp.setBookingSupervisionNum( this.getBookingSupervisionNum());
		resp.setReferralNumber( this.getReferralNumber());
		
		resp.setCurrentOffense( this.getCurrentOffense());
		if( this.getCurrentOffense()!=null && ! this.getCurrentOffense().isEmpty()){
			resp.setCurrentOffenseDesc(JuvenileOffenseCode.find("offenseCode", this.getCurrentOffense()).getLongDescription());
		}
		resp.setCurrentReferral( this.getCurrentReferral()); 
		resp.setCurrentSupervisionNum( this.getCurrentSupervisionNum());
		resp.setDetainedDate( this.getDetainedDate());
		if( this.getEscapeCode()!=null){
			resp.setEscapeCode( this.getEscapeCode());
			resp.setEscapeCodeDesc(JuvenileFacilityHelper.getServicesDelivered( this.getEscapeCode()).getDescription());
		}
		if( this.getEscapeAttempts()!=null&& this.getEscapeAttempts().equals("0")){
			resp.setEscapeAttempts("");
		}else{
			resp.setEscapeAttempts( this.getEscapeAttempts());
		}
		resp.setEscapeAttemptComments( this.getEscapeAttemptComments());
		resp.setDetainedByInd( this.getDetainedByInd());
		//resp.setComments( this.getComments()); //U.S #51737
		resp.setReturnDate( this.getReturnDate());
		resp.setReturnStatus( this.getReturnStatus());
		resp.setReturnTime(JuvenileFacilityHelper.getHoursMinsFromTime( this.getReturnTime()));
		resp.setReturnReason( this.getReturnReason());
		resp.setOtherChangeFlag( this.isOtherChangeFlag());
		resp.setReasonChangeFlag( this.isReasonChangeFlag());
		resp.setSecureIndicatorChangeFlag( this.isSecureIndicatorChangeFlag());
		resp.setLocationChangeFlag( this.isLocationChangeFlag());
		resp.setTempReleaseReasonCd( this.getTempReleaseReasonCd());
		if( this.getTempReleaseReasonCd()!=null && ! this.getTempReleaseReasonCd().isEmpty()){
			resp.setTempReleaseReasonCdDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.TEMP_RELEASE_REASON, this.getTempReleaseReasonCd()));
		}
		resp.setTempReleaseOtherComments( this.getTempReleaseOtherComments());
		resp.setChangeExplanation( this.getChangeExplanation());
		resp.setTransferToFacility( this.getTransferToFacility());
		if( this.getTransferToFacility()!=null && ! this.getTransferToFacility().isEmpty()){
			resp.setTransferToFacilityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, this.getTransferToFacility()));
		}
		resp.setOutcome( this.getOutcome());
		resp.setCustodylastName(this.getCustodylastName());
		resp.setCustodyfirstName(this.getCustodyfirstName());
		if( this.getOutcome()!=null && ! this.getOutcome().isEmpty()){
			resp.setOutcomeDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.DETENTION_OUTCOME,  this.getOutcome()));
		}
		resp.setRoomNum( this.getRoomNum());
		resp.setUnit( this.getUnit());
		resp.setFloorNum( this.getFloorNum());
		resp.setMultipleOccupyUnit( this.getMultipleOccupyUnit()); 
		resp.setDetentionId( this.getOID()); //task #34820
		resp.setTjjdFacilityId(this.getTjjdFacilityId());
		resp.setTjjdFundingSrc(this.getTjjdFundingSrc());
		resp.setTopic(PDJuvenileConstants.JUVENILE_PROFILE_FACILITY_HISTORY_TOPIC);
		
		resp.setRiskAnalysisId(this.riskAnalysisId);
		
		return resp;
	}
	
	
	/**
	 * 
	 * @param uvcode
	 * @return
	 */
	private String getOfficerProfile( String uvcode ){
	    
	    Iterator offIter = OfficerProfile.findAll("logonId", uvcode );
	    String officerName = "NOT AVAILABLE";
	    	if( offIter.hasNext() ){	    	    
	    	    OfficerProfile officer = (OfficerProfile) offIter.next(); 
	    	    IName name = new Name(officer.getFirstName(), officer.getMiddleName(), officer.getLastName() );
	    	    if(  name!=null){
	    		officerName = name.getFormattedName();   
		    }			
	    	}
	    return officerName;
	}

	public String getDob()
	{
	    return dob;
	}

	public void setDob(String dob)
	{
	    this.dob = dob;
	}

	

}
