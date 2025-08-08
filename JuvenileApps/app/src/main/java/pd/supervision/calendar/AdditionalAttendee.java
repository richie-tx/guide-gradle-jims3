package pd.supervision.calendar;


import mojo.km.persistence.PersistentObject;

/**
* Name of the additional attendee
*/
public class AdditionalAttendee extends PersistentObject 
{
	/**
	* Name of the additional attendee
	*/
	private String lastName;
	private String middleName;
	private String firstName;
	private String juvenileId;
	
	private String serviceEventAttendanceId;
	
	/**
	* @roseuid 448EC36203CF
	*/
	public AdditionalAttendee() 
	{
	}

	public String getLastName() {
		fetch();
		return lastName;
	}

	public void setLastName(String lastName) {
		if (this.lastName == null || !this.lastName.equals(lastName)) {
			markModified();
		}		
		this.lastName = lastName;
	}

	public String getMiddleName() {
		fetch();
		return middleName;
	}

	public void setMiddleName(String middleName) {
		if (this.middleName == null || !this.firstName.equals(middleName)) {
			markModified();
		}
		this.middleName = middleName;
	}

	public String getFirstName() {
		fetch();
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (this.firstName == null || !this.firstName.equals(firstName)) {
			markModified();
		}
		this.firstName = firstName;
	}

	public String getServiceEventAttendanceId() {
		fetch();
		return serviceEventAttendanceId;
	}

	public void setServiceEventAttendanceId(String serviceEventAttendanceId) {
		if (this.serviceEventAttendanceId == null || !this.serviceEventAttendanceId.equals(serviceEventAttendanceId)) {
			markModified();
		}
		this.serviceEventAttendanceId = serviceEventAttendanceId;
	}

	public String getJuvenileId() {
		fetch();
		return juvenileId;
	}

	public void setJuvenileId(String juvenileId) {
		if (this.juvenileId == null || !this.juvenileId.equals(juvenileId)) {
			markModified();
		}
		this.juvenileId = juvenileId;
	}

	
	
}
