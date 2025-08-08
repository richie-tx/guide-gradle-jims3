package messaging.juvenilecase.reply;

import java.util.Calendar;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class JuvenileFacilitiesCurrPopResponseEvent extends ResponseEvent{

	private String admitReason;
	private Date admitDate;
	private Date courtDate;	
	private Date nextHearingDate;
	private String formattedNextHearingDate;
	private String formattedCourtDate;
	private String formattedAdmitDate;
	private String facilityCode;	
	private String referralNumber;
	private Date originalAdmitDate;	
	private String admitTime;
	private String secureStatus;
	private String reasonDescription;
	private String facilityName;
	private String probationOfficerFirstId;
	private String probationOfficerId;
	private String offenseCodeId;
	private String petitionNum;
	
	//Added for JJS Facility Reports
	
	private String floorNum;
	private String unit;
	private String roomNum;
	
	private String mou;  //added for US 19240
	
	//added for juvenile details
	private String juvenileNum;	
	private String firstName;
	private String lastName;
	private String middleName;
	private String raceId;
	private String sexId;
	private int ageInYears;
	private int diffInDays;
	private String juvenileDateOfBirth; //added for US 169455
	
	//added for US 14461
	private String  officerUVCode;
	private String officerFullName;
	private String bookingSupervisionNum;
	private String specialAttention;
	private String specialAttentionDesc;
	private String riskneedLvl;
	private String riskLvl;
	private String needsLvl;
	private String riskLvlDesc;
	private String needsLvlDesc;
	
	 private String primaryGuardianName;
	    private String guardianRelationship;
	    private String guardianPrimaryLanguage;
	    private String guardianAddress;
	    private String guardianPhoneNumber;
	    private String guardianEmailAddress;
	
	/**
	 * @return the petitionNum
	 */
	public String getPetitionNum() {
		return petitionNum;
	}
	/**
	 * @param petitionNum the petitionNum to set
	 */
	public void setPetitionNum(String petitionNum) {
		this.petitionNum = petitionNum;
	}
	/**
	 * @return the admitReason
	 */
	public String getAdmitReason() {
		return admitReason;
	}
	/**
	 * @param admitReason the admitReason to set
	 */
	public void setAdmitReason(String admitReason) {
		this.admitReason = admitReason;
	}
	/**
	 * @return the reasonDescription
	 */
	public String getReasonDescription() {
		return reasonDescription;
	}
	/**
	 * @param reasonDescription the reasonDescription to set
	 */
	public void setReasonDescription(String reasonDescription) {
		this.reasonDescription = reasonDescription;
	}
	/**
	 * @return the admitDate
	 */
	public Date getAdmitDate() {
		return admitDate;
	}
	/**
	 * @param admitDate the admitDate to set
	 */
	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}
	/**
	 * @return the courtDate
	 */
	public Date getCourtDate() {
		return courtDate;
	}
	/**
	 * @param courtDate the courtDate to set
	 */
	public void setCourtDate(Date courtDate) {
		this.courtDate = courtDate;
	}
	/**
	 * @return the nextHearingDate
	 */
	public Date getNextHearingDate() {
		return nextHearingDate;
	}
	/**
	 * @param nextHearingDate the nextHearingDate to set
	 */
	public void setNextHearingDate(Date nextHearingDate) {
		this.nextHearingDate = nextHearingDate;
	}
	/**
	 * @return the formattedNextHearingDate
	 */
	public String getFormattedNextHearingDate() {
		return formattedNextHearingDate;
	}
	/**
	 * @param formattedNextHearingDate the formattedNextHearingDate to set
	 */
	public void setFormattedNextHearingDate(String formattedNextHearingDate) {
		this.formattedNextHearingDate = formattedNextHearingDate;
	}
	/**
	 * @return the formattedCourtDate
	 */
	public String getFormattedCourtDate() {
		return formattedCourtDate;
	}
	/**
	 * @param formattedCourtDate the formattedCourtDate to set
	 */
	public void setFormattedCourtDate(String formattedCourtDate) {
		this.formattedCourtDate = formattedCourtDate;
	}
	/**
	 * @return the offenseCodeId
	 */
	public String getOffenseCodeId() {
		return offenseCodeId;
	}
	/**
	 * @param offenseCodeId the offenseCodeId to set
	 */
	public void setOffenseCodeId(String offenseCodeId) {
		this.offenseCodeId = offenseCodeId;
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
	 * @return the probationOfficerFirstId
	 */
	public String getProbationOfficerFirstId() {
		return probationOfficerFirstId;
	}
	/**
	 * @param probationOfficerFirstId the probationOfficerFirstId to set
	 */
	public void setProbationOfficerFirstId(String probationOfficerFirstId) {
		this.probationOfficerFirstId = probationOfficerFirstId;
	}
	/**
	 * @return the probationOfficerId
	 */
	public String getProbationOfficerId() {
		return probationOfficerId;
	}
	/**
	 * @param probationOfficerId the probationOfficerId to set
	 */
	public void setProbationOfficerId(String probationOfficerId) {
		this.probationOfficerId = probationOfficerId;
	}
	/**
	 * @return the referralNumber
	 */
	public String getReferralNumber() {
		return referralNumber;
	}
	/**
	 * @param referralNumber the referralNumber to set
	 */
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
	/**
	 * @return the originalAdmitDate
	 */
	public Date getOriginalAdmitDate() {
		return originalAdmitDate;
	}
	/**
	 * @param originalAdmitDate the originalAdmitDate to set
	 */
	public void setOriginalAdmitDate(Date originalAdmitDate) {
		this.originalAdmitDate = originalAdmitDate;
	}
	/**
	 * @return the admitTime
	 */
	public String getAdmitTime() {
		return admitTime;
	}
	/**
	 * @param admitTime the admitTime to set
	 */
	public void setAdmitTime(String admitTime) {
		this.admitTime = admitTime;
	}
	/**
	 * @return the secureStatus
	 */
	public String getSecureStatus() {
		return secureStatus;
	}
	/**
	 * @param secureStatus the secureStatus to set
	 */
	public void setSecureStatus(String secureStatus) {
		this.secureStatus = secureStatus;
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
	 * @return the floorNum
	 */
	public String getFloorNum() {
		return floorNum;
	}
	/**
	 * @param floorNum the floorNum to set
	 */
	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}
	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @return the roomNum
	 */
	public String getRoomNum() {
		return roomNum;
	}
	/**
	 * @param roomNum the roomNum to set
	 */
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
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
	 * @return the raceId
	 */
	public String getRaceId() {
		return raceId;
	}
	/**
	 * @param raceId the raceId to set
	 */
	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}
	/**
	 * @return the sexId
	 */
	public String getSexId() {
		return sexId;
	}
	/**
	 * @param sexId the sexId to set
	 */
	public void setSexId(String sexId) {
		this.sexId = sexId;
	}
	/**
	 * @return the ageInYears
	 */
	public int getAgeInYears() {
		return ageInYears;
	}
	/**
	 * @param ageInYears the ageInYears to set
	 */
	public void setAgeInYears(int ageInYears) {
		this.ageInYears = ageInYears;
	}
	
	/**
	 * @return the diffInDays
	 */
	public int getDiffInDays() {
		return diffInDays;
	}
	/**
	 * @param diffInDays the diffInDays to set
	 */
	public void setDiffInDays(int diffInDays) {
		this.diffInDays = diffInDays;
	}
	/**
	 * @return the formattedAdmitDate
	 */
	public String getFormattedAdmitDate() {
		return formattedAdmitDate;
	}
	/**
	 * @param formattedAdmitDate the formattedAdmitDate to set
	 */
	public void setFormattedAdmitDate(String formattedAdmitDate) {
		this.formattedAdmitDate = formattedAdmitDate;
	}
	public String getOfficerUVCode() {
		return officerUVCode;
	}
	public void setOfficerUVCode(String officerUVCode) {
		this.officerUVCode = officerUVCode;
	}
	public String getOfficerFullName() {
		return officerFullName;
	}
	public void setOfficerFullName(String officerFullName) {
		this.officerFullName = officerFullName;
	}
	public String getMou() {
		return mou;
	}
	public void setMou(String mou) {
		this.mou = mou;
	}
	public String getBookingSupervisionNum() {
		return bookingSupervisionNum;
	}
	public void setBookingSupervisionNum(String bookingSupervisionNum) {
		this.bookingSupervisionNum = bookingSupervisionNum;
	}
	public String getSpecialAttention()
	{
	    return specialAttention;
	}
	public void setSpecialAttention(String specialAttention)
	{
	    this.specialAttention = specialAttention;
	}
	public String getSpecialAttentionDesc()
	{
	    return specialAttentionDesc;
	}
	public void setSpecialAttentionDesc(String specialAttentionDesc)
	{
	    this.specialAttentionDesc = specialAttentionDesc;
	}
	public String getRiskneedLvl()
	{
	    return riskneedLvl;
	}
	public void setRiskneedLvl(String riskneedLvl)
	{
	    this.riskneedLvl = riskneedLvl;
	}
	public String getRiskLvl()
	{
	    return riskLvl;
	}
	public void setRiskLvl(String riskLvl)
	{
	    this.riskLvl = riskLvl;
	}	

	public String getNeedsLvl()
	{
	    return needsLvl;
	}
	public void setNeedsLvl(String needsLvl)
	{
	    this.needsLvl = needsLvl;
	}
	public String getRiskLvlDesc()
	{
	    return riskLvlDesc;
	}
	public void setRiskLvlDesc(String riskLvlDesc)
	{
	    this.riskLvlDesc = riskLvlDesc;
	}
	
	
	public String getNeedsLvlDesc()
	{
	    return needsLvlDesc;
	}
	public void setNeedsLvlDesc(String needsLvlDesc)
	{
	    this.needsLvlDesc = needsLvlDesc;
	}
	public String getJuvenileDateOfBirth()
	{
	    return juvenileDateOfBirth;
	}
	public void setJuvenileDateOfBirth(String juvenileDateOfBirth)
	{
	    this.juvenileDateOfBirth = juvenileDateOfBirth;
	}
	
	/**
	     * @return the primaryGuardianName
	     */
	    public String getPrimaryGuardianName() {
	        return primaryGuardianName;
	    }

	    /**
	     * @param primaryGuardianName the primaryGuardianName to set
	     */
	    public void setPrimaryGuardianName(String primaryGuardianName) {
	        this.primaryGuardianName = primaryGuardianName;
	    }

	    /**
	     * @return the guardianRelationship
	     */
	    public String getGuardianRelationship() {
	        return guardianRelationship;
	    }

	    /**
	     * @param guardianRelationship the guardianRelationship to set
	     */
	    public void setGuardianRelationship(String guardianRelationship) {
	        this.guardianRelationship = guardianRelationship;
	    }

	    /**
	     * @return the guardianPrimaryLanguage
	     */
	    public String getGuardianPrimaryLanguage() {
	        return guardianPrimaryLanguage;
	    }

	    /**
	     * @param guardianPrimaryLanguage the guardianPrimaryLanguage to set
	     */
	    public void setGuardianPrimaryLanguage(String guardianPrimaryLanguage) {
	        this.guardianPrimaryLanguage = guardianPrimaryLanguage;
	    }

	    /**
	     * @return the guardianAddress
	     */
	    public String getGuardianAddress() {
	        return guardianAddress;
	    }

	    /**
	     * @param guardianAddress the guardianAddress to set
	     */
	    public void setGuardianAddress(String guardianAddress) {
	        this.guardianAddress = guardianAddress;
	    }

	    /**
	     * @return the guardianPhoneNumber
	     */
	    public String getGuardianPhoneNumber() {
	        return guardianPhoneNumber;
	    }

	    /**
	     * @param guardianPhoneNumber the guardianPhoneNumber to set
	     */
	    public void setGuardianPhoneNumber(String guardianPhoneNumber) {
	        this.guardianPhoneNumber = guardianPhoneNumber;
	    }

	    /**
	     * @return the guardianEmailAddress
	     */
	    public String getGuardianEmailAddress() {
	        return guardianEmailAddress;
	    }

	    /**
	     * @param guardianEmailAddress the guardianEmailAddress to set
	     */
	    public void setGuardianEmailAddress(String guardianEmailAddress) {
	        this.guardianEmailAddress = guardianEmailAddress;
	    }

}
