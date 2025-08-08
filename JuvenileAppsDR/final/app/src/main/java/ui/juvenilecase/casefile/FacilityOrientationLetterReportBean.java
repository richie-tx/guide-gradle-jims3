package ui.juvenilecase.casefile;
/**
 * <!-- Added for 12533 user story -->
 * @author sthyagarajan
 *
 */
public class FacilityOrientationLetterReportBean {

	private String juvenileFullName;
	private String officerName;
	private String officerPhone;
	private String location;
	private String facility;
	private String eventDateStr;
	private String eventTime;
	private String contactName; //bug 26711
	private String endTime;
	
	public String getJuvenileFullName() {
		return juvenileFullName;
	}

	public void setJuvenileFullName(String juvenileFullName) {
		this.juvenileFullName = juvenileFullName;
	}

	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}

	public String getOfficerPhone() {
		return officerPhone;
	}

	public void setOfficerPhone(String officerPhone) {
		this.officerPhone = officerPhone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
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


	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}


}
