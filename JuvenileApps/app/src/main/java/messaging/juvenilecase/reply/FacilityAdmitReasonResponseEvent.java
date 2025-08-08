package messaging.juvenilecase.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class FacilityAdmitReasonResponseEvent extends ResponseEvent{
	
	private Date admitDate;
	private String formattedAdmitDate;
	private String reasonCode;
	private String secureStatus;
	private String reasonDescription;
	private String offenseCodeId;
	private String offenseDescription;
	private String juvenileNum;
	private String firstName;
	private String lastName;
	private String middleName;
	private String raceId;
	private String sexId;
	private int ageInYears;
	private Date releaseDate;
	private int diffInDays;
	private String referralNumber;
	
	//added for US 14463
	private Date dob;
	
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
	 * @return the offenseDescription
	 */
	public String getOffenseDescription() {
		return offenseDescription;
	}
	/**
	 * @param offenseDescription the offenseDescription to set
	 */
	public void setOffenseDescription(String offenseDescription) {
		this.offenseDescription = offenseDescription;
	}
	public Date getAdmitDate() {
		return admitDate;
	}
	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getSecureStatus() {
		return secureStatus;
	}
	public void setSecureStatus(String secureStatus) {
		this.secureStatus = secureStatus;
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
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getRaceId() {
		return raceId;
	}
	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}
	public String getSexId() {
		return sexId;
	}
	public void setSexId(String sexId) {
		this.sexId = sexId;
	}
	public int getAgeInYears() {
		return ageInYears;
	}
	public void setAgeInYears(int ageInYears) {
		this.ageInYears = ageInYears;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getDiffInDays()
	{
			return diffInDays;
	}
	
	public void setDiffInDays(int diffInDays)
	{
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getReferralNumber()
	{
	    return referralNumber;
	}
	
	public void setReferralNumber(String referralNumber)
	{
	   this.referralNumber = referralNumber;
	}

}
