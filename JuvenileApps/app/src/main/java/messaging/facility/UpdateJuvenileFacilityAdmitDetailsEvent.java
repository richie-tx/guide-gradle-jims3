package messaging.facility;

import java.util.Date;

import messaging.codetable.criminal.reply.JuvenileAdmitReasonsResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import mojo.km.messaging.RequestEvent;

public class UpdateJuvenileFacilityAdmitDetailsEvent extends RequestEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String juvNum;
	private String lastSeqNum;
	private String facilityStatus;
	private Date originalAdmitDate;
	private String originalAdmitTime;
	private String juvenileName;
	
	// observation
	private String specialAttentionCd;
	private String saReason;
	private String saReasonOtherComments;

	// release update
	private Date releaseDate;
	private String releaseTime;
	private String releaseBy;
	private String releaseTo;
	private String releaseAuthorizedBy;
	private String releaseReason;

	// return update
	private Date returnDate;
	private String returnTime;

	// admission update
	private boolean locationChangeFlag;
	private boolean reasonChangeFlag;
	private String admitReason;
	private boolean secureIndicatorChangeFlag;
	private String secureStatus;
	private boolean otherChangeFlag;
	private String admittedBy;
	private String floorNum;
	private String unit;
	private String roomNum;
	private String multipleOccupyUnit;
	private String comments;
	private String escapeAttempts;
	private String escapeAttemptComments;
	
	// Escape update
	private String escapeCode;
	
	// header Update
	private Date relocationDate;
	private String relocationTime;
	private String changeExplanation;
	private String facRefereeCourt;	
	private String detentionStatus;
	//104028
	private String lockerNumber;
	private String receiptNumber; 
	
	private int tjjdFacilityId;
	private String tjjdFundingSrc;
	//408911
	private String petitionNum;
	//
	private String avgCostPerDay;
    

    public int getTjjdFacilityId()
	{
	    return tjjdFacilityId;
	}

	public void setTjjdFacilityId(int tjjdFacilityId)
	{
	    this.tjjdFacilityId = tjjdFacilityId;
	}

	public String getTjjdFundingSrc()
	{
	    return tjjdFundingSrc;
	}

	public void setTjjdFundingSrc(String tjjdFundingSrc)
	{
	    this.tjjdFundingSrc = tjjdFundingSrc;
	}

    //
    private boolean isObservationStatusChanged;
	
	
	private JuvenileAdmitReasonsResponseEvent admitReasonResp;
	private JuvenileDetentionFacilitiesResponseEvent detResp;
	
	public String getSpecialAttentionCd() {
		return specialAttentionCd;
	}

	public void setSpecialAttentionCd(String specialAttentionCd) {
		this.specialAttentionCd = specialAttentionCd;
	}

	public String getSaReason() {
		return saReason;
	}

	public void setSaReason(String saReason) {
		this.saReason = saReason;
	}

	public String getSaReasonOtherComments() {
		return saReasonOtherComments;
	}

	public void setSaReasonOtherComments(String saReasonOtherComments) {
		this.saReasonOtherComments = saReasonOtherComments;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getReleaseBy() {
		return releaseBy;
	}

	public void setReleaseBy(String releaseBy) {
		this.releaseBy = releaseBy;
	}

	public String getReleaseTo() {
		return releaseTo;
	}

	public void setReleaseTo(String releaseTo) {
		this.releaseTo = releaseTo;
	}

	public String getReleaseAuthorizedBy() {
		return releaseAuthorizedBy;
	}

	public void setReleaseAuthorizedBy(String releaseAuthorizedBy) {
		this.releaseAuthorizedBy = releaseAuthorizedBy;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public boolean isLocationChangeFlag() {
		return locationChangeFlag;
	}

	public void setLocationChangeFlag(boolean locationChangeFlag) {
		this.locationChangeFlag = locationChangeFlag;
	}

	public boolean isReasonChangeFlag() {
		return reasonChangeFlag;
	}

	public void setReasonChangeFlag(boolean reasonChangeFlag) {
		this.reasonChangeFlag = reasonChangeFlag;
	}

	public String getAdmitReason() {
		return admitReason;
	}

	public void setAdmitReason(String admitReason) {
		this.admitReason = admitReason;
	}

	public boolean isSecureIndicatorChangeFlag() {
		return secureIndicatorChangeFlag;
	}

	public void setSecureIndicatorChangeFlag(boolean secureIndicatorChangeFlag) {
		this.secureIndicatorChangeFlag = secureIndicatorChangeFlag;
	}

	public String getSecureStatus() {
		return secureStatus;
	}

	public void setSecureStatus(String secureStatus) {
		this.secureStatus = secureStatus;
	}

	public boolean isOtherChangeFlag() {
		return otherChangeFlag;
	}

	public void setOtherChangeFlag(boolean otherChangeFlag) {
		this.otherChangeFlag = otherChangeFlag;
	}

	public String getAdmittedBy() {
		return admittedBy;
	}

	public void setAdmittedBy(String admittedBy) {
		this.admittedBy = admittedBy;
	}

	public String getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getEscapeCode() {
		return escapeCode;
	}

	public void setEscapeCode(String escapeCode) {
		this.escapeCode = escapeCode;
	}

	public String getEscapeAttempts() {
		return escapeAttempts;
	}

	public void setEscapeAttempts(String escapeAttempts) {
		this.escapeAttempts = escapeAttempts;
	}

	public String getEscapeAttemptComments() {
		return escapeAttemptComments;
	}

	public void setEscapeAttemptComments(String escapeAttemptComments) {
		this.escapeAttemptComments = escapeAttemptComments;
	}

	public Date getRelocationDate() {
		return relocationDate;
	}

	public void setRelocationDate(Date relocationDate) {
		this.relocationDate = relocationDate;
	}

	public String getRelocationTime() {
		return relocationTime;
	}

	public void setRelocationTime(String relocationTime) {
		this.relocationTime = relocationTime;
	}

	public String getLastSeqNum() {
		return lastSeqNum;
	}

	public void setLastSeqNum(String lastSeqNum) {
		this.lastSeqNum = lastSeqNum;
	}

	public String getJuvNum() {
		return juvNum;
	}

	public void setJuvNum(String juvNum) {
		this.juvNum = juvNum;
	}

	public String getChangeExplanation() {
		return changeExplanation;
	}

	public void setChangeExplanation(String changeExplanation) {
		this.changeExplanation = changeExplanation;
	}

	public String getFacilityStatus() {
		return facilityStatus;
	}

	public void setFacilityStatus(String facilityStatus) {
		this.facilityStatus = facilityStatus;
	}

	public Date getOriginalAdmitDate() {
		return originalAdmitDate;
	}

	public void setOriginalAdmitDate(Date originalAdmitDate) {
		this.originalAdmitDate = originalAdmitDate;
	}

	public String getFacRefereeCourt() {
		return facRefereeCourt;
	}

	public void setFacRefereeCourt(String facRefereeCourt) {
		this.facRefereeCourt = facRefereeCourt;
	}

	public String getReleaseReason() {
		return releaseReason;
	}

	public void setReleaseReason(String releaseReason) {
		this.releaseReason = releaseReason;
	}

	public JuvenileAdmitReasonsResponseEvent getAdmitReasonResp() {
		return admitReasonResp;
	}

	public void setAdmitReasonResp(JuvenileAdmitReasonsResponseEvent admitReasonResp) {
		this.admitReasonResp = admitReasonResp;
	}

	public String getOriginalAdmitTime() {
		return originalAdmitTime;
	}

	public void setOriginalAdmitTime(String originalAdmitTime) {
		this.originalAdmitTime = originalAdmitTime;
	}

	public JuvenileDetentionFacilitiesResponseEvent getDetResp() {
		return detResp;
	}

	public void setDetResp(JuvenileDetentionFacilitiesResponseEvent detResp) {
		this.detResp = detResp;
	}

	public String getDetentionStatus() {
		return detentionStatus;
	}

	public void setDetentionStatus(String detentionStatus) {
		this.detentionStatus = detentionStatus;
	}

	public String getJuvenileName() {
		return juvenileName;
	}

	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}

	public String getMultipleOccupyUnit() {
		return multipleOccupyUnit;
	}

	public void setMultipleOccupyUnit(String multipleOccupyUnit) {
		this.multipleOccupyUnit = multipleOccupyUnit;
	}

	/**
	 * @return the isObservationStatusChanged
	 */
	public boolean isObservationStatusChanged() {
		return isObservationStatusChanged;
	}

	/**
	 * @param isObservationStatusChanged the isObservationStatusChanged to set
	 */
	public void setObservationStatusChanged(boolean isObservationStatusChanged) {
		this.isObservationStatusChanged = isObservationStatusChanged;
	}
	public String getLockerNumber()
	{
	    return lockerNumber;
	}

	public void setLockerNumber(String lockerNumber)
	{
	    this.lockerNumber = lockerNumber;
	}
	public String getReceiptNumber()
	{
	    return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber)
	{
	    this.receiptNumber = receiptNumber;
	}
	public String getPetitionNum()
	{
	    return petitionNum;
	}

	public void setPetitionNum(String petitionNum)
	{
	    this.petitionNum = petitionNum;
	}
	
	public String getAvgCostPerDay()
	{
	    return avgCostPerDay;
	}
	public void setAvgCostPerDay(String avgCostPerDay)
	{
	    this.avgCostPerDay = avgCostPerDay;
	}
}
