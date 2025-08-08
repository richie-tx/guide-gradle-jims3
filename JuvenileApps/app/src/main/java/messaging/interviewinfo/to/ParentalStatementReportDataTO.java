package messaging.interviewinfo.to;

import org.apache.commons.lang.StringUtils;

import messaging.contact.to.Address;
import messaging.contact.to.PhoneNumberBean;

/**
 *
 */
public class ParentalStatementReportDataTO 
{
	private String courtDate = "";
	private String courtName = "";
	private String petitionNumber = "";
	private String juvenileName = "";
	private String locationUnitName = "";
	private String locationAddress1 = "";
	private String locationAddress2 = "";
	private Address locationAddress = new Address();
	private String formattedPhoneNumber = "";
	private PhoneNumberBean locationUnitPhone = new PhoneNumberBean("");
	/**
	 * @return Returns the courtDate.
	 */
	public String getCourtDate() {
		return courtDate;
	}
	/**
	 * @param courtDate The courtDate to set.
	 */
	public void setCourtDate(String courtDate) {
		this.courtDate = courtDate;
	}
	/**
	 * @return Returns the courtName.
	 */
	public String getCourtName() {
		return courtName;
	}
	/**
	 * @param courtName The courtName to set.
	 */
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	/**
	 * @return Returns the juvenileName.
	 */
	public String getJuvenileName() {
		return juvenileName;
	}
	/**
	 * @param juvenileName The juvenileName to set.
	 */
	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}
	/**
	 * @return Returns the petitionNumber.
	 */
	public String getPetitionNumber() {
		return petitionNumber;
	}
	/**
	 * @param petitionNumber The petitionNumber to set.
	 */
	public void setPetitionNumber(String petitionNumber) {
		this.petitionNumber = petitionNumber;
	}
	/**
	 * @return Returns the locationAddress.
	 */
	/**
	 * @return Returns the locationUnitName.
	 */
	public String getLocationUnitName() {
		return locationUnitName;
	}
	/**
	 * @param locationUnitName The locationUnitName to set.
	 */
	public void setLocationUnitName(String locationUnitName) {
		this.locationUnitName = locationUnitName;
	}
	/**
	 * @return the locationAddress1
	 */
	public String getLocationAddress1() {
		return locationAddress1;
	}
	/**
	 * @param locationAddress1 the locationAddress1 to set
	 */
	public void setLocationAddress1(Address locationAddress) {
		StringBuffer address = new StringBuffer();
		address.append(locationAddress.getStreetNum());
		address.append(" ");
		address.append(locationAddress.getStreetName());
		address.append(" ");
		address.append(locationAddress.getStreetTypeCode());
		address.append(" ");
		if(StringUtils.isNotEmpty(locationAddress.getAptNum())) {
			address.append(locationAddress.getAptNum());
		}
		this.locationAddress1 = address.toString();
	}
	/**
	 * @return the locationAddress2
	 */
	public String getLocationAddress2() {
		return locationAddress2;
	}
	/**
	 * @param locationAddress2 the locationAddress2 to set
	 */
	public void setLocationAddress2(Address locationAddress) {
		StringBuffer address = new StringBuffer();
		if(StringUtils.isNotEmpty(locationAddress.getCity())) {
			address.append(locationAddress.getCity());
			address.append(", ");
		}
		address.append(locationAddress.getStateCode());
		address.append(" ");
		address.append(locationAddress.getZipCode());
		this.locationAddress2 = address.toString();
	}
	/**
	 * @return Returns the locationAddress.
	 */
	public Address getLocationAddress() {
		return locationAddress;
	}
	/**
	 * @param locationAddress The locationAddress to set.
	 */
	public void setLocationAddress(Address locationAddress) {
		this.locationAddress = locationAddress;
	}
	/**
	 * @return the formattedPhoneNumber
	 */
	public String getFormattedPhoneNumber() {
		return formattedPhoneNumber;
	}
	/**
	 * @param formattedPhoneNumber the formattedPhoneNumber to set
	 */
	public void setFormattedPhoneNumber(PhoneNumberBean locationUnitPhone) {
		this.formattedPhoneNumber = locationUnitPhone.getFormattedPhoneNumber();
	}
	/**
	 * @return Returns the locationUnitPhone.
	 */
	public PhoneNumberBean getLocationUnitPhone() {
		return locationUnitPhone;
	}
	/**
	 * @param locationUnitPhone The locationUnitPhone to set.
	 */
	public void setLocationUnitPhone(PhoneNumberBean locationUnitPhone) {
		this.locationUnitPhone = locationUnitPhone;
	}
}
