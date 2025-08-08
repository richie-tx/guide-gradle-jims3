//created 6/22/2015
package ui.juvenilecase.schedulecalendarevent.to;

import java.util.Date;
import java.util.List;


public class AppointmentLetterBean
{
	//items used for report
	private Date currentDate ;
	private String juvenileFullName;	
	private String officerPhone;
	private String officerFax;
	private String officerName;
	private String petitionNumber;
	private String officeHours;
	private String courtNumber;
	private Date courtDate;
	private String letterType;
		//Current Event Data Used in the subclass
	private String eventDateStr ;
	private String eventTime ;
	
	
	public AppointmentLetterBean()
	{
		currentDate = new Date( ) ;
	}
	
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public String getJuvenileFullName() {
		return juvenileFullName;
	}
	public void setJuvenileFullName(String juvenileFullName) {
		this.juvenileFullName = juvenileFullName;
	}

	public String getOfficerPhone() {
		return officerPhone;
	}
	public void setOfficerPhone(String officerPhone) {
		this.officerPhone = officerPhone;
	}

	public String getEventDateStr() {
		return eventDateStr;
	}

	public void setEventDateStr(String eventDateStr) {
		this.eventDateStr = eventDateStr;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	/**
	 * @return the officerFax
	 */
	public String getOfficerFax() {
		return officerFax;
	}

	/**
	 * @param officerFax the officerFax to set
	 */
	public void setOfficerFax(String officerFax) {
		this.officerFax = officerFax;
	}

	/**
	 * @return the officerName
	 */
	public String getOfficerName() {
		return officerName;
	}

	/**
	 * @param officerName the officerName to set
	 */
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}

	/**
	 * @return the petitionNumber
	 */
	public String getPetitionNumber() {
		return petitionNumber;
	}

	/**
	 * @param petitionNumber the petitionNumber to set
	 */
	public void setPetitionNumber(String petitionNumber) {
		this.petitionNumber = petitionNumber;
	}

	/**
	 * @return the officeHours
	 */
	public String getOfficeHours() {
		return officeHours;
	}

	/**
	 * @param officeHours the officeHours to set
	 */
	public void setOfficeHours(String officeHours) {
		this.officeHours = officeHours;
	}

	/**
	 * @return the courtNumber
	 */
	public String getCourtNumber() {
		return courtNumber;
	}

	/**
	 * @param courtNumber the courtNumber to set
	 */
	public void setCourtNumber(String courtNumber) {
		this.courtNumber = courtNumber;
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
	 * @return the letterType
	 */
	public String getLetterType() {
		return letterType;
	}

	/**
	 * @param letterType the letterType to set
	 */
	public void setLetterType(String letterType) {
		this.letterType = letterType;
	}
	
	
}
