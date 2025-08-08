// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\mentalhealth\\CreateHospitalizationHistoryEvent.java

package messaging.mentalhealth;

import mojo.km.messaging.RequestEvent;
import java.util.Date;
import messaging.contact.domintf.IPhoneNumber;

public class CreateHospitalizationHistoryEvent extends RequestEvent {
	public String facilityName;

	public Date admissionDate;

	public String admissionType;

	public Date releaseDate;

	public String physicianName;

	//public IPhoneNumber physicianPhone;
	public String physicianPhone;

	public String hospitalizationReason;

	public String juvenileNum;

	/**
	 * @return Returns the juvenileNum. roseuid 45AFB1F80178
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum
	 *            The juvenileNum to set. roseuid 45AFB1F80179
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @roseuid 45AFB1F80171
	 */
	public CreateHospitalizationHistoryEvent() {

	}

	/**
	 * @return java.util.Date
	 * @roseuid 45AFA6EF0033
	 */

	public Date getAdmissionDate() {
		return admissionDate;
	}

	/**
	 * @param admissionDate
	 * @roseuid 45AFA6EF0031
	 */
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	/**
	 * @return String
	 * @roseuid 45AFA6EF0064
	 */
	public String getAdmissionType() {
		return admissionType;
	}

	/**
	 * @param admissionType
	 * @roseuid 45AFA6EF0035
	 */
	public void setAdmissionType(String admissionType) {
		this.admissionType = admissionType;
	}

	/**
	 * @return String
	 * @roseuid 45AFA6EF0023
	 */

	public String getFacilityName() {
		return facilityName;
	}

	/**
	 * @param facilityName
	 * @roseuid 45AFA6EF0021
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	/**
	 * @return String
	 * @roseuid 45AFA6EF0041
	 */

	public String getHospitalizationReason() {
		return hospitalizationReason;
	}

	/**
	 * @param hospitalizationReason
	 * @roseuid 45AFA6EF0070
	 */
	public void setHospitalizationReason(String hospitalizationReason) {
		this.hospitalizationReason = hospitalizationReason;
	}

	/**
	 * @return String
	 * @roseuid 45AFA6EF0060
	 */

	public String getPhysicianName() {
		return physicianName;
	}

	/**
	 * @param physicianName
	 * @roseuid 45AFA6EF0053
	 */

	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

	/**
	 * @return Returns the physicianPhone.
	 * @roseuid 45AFA6EF0069
	 */
	public String getPhysicianPhone() {
		return physicianPhone;
	}

	/**
	 * @param physicianPhone
	 * @roseuid 45AFA6EF0062
	 */
	public void setPhysicianPhone(String physicianPhone) {
		this.physicianPhone = physicianPhone;
	}

	/**
	 * @return java.util.Date
	 * @roseuid 45AFA6EF0051
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate
	 * @roseuid 45AFA6EF0043
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
}
