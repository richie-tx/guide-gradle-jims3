//Source file: C:\\views\\dev\\app\\src\\messaging\\officer\\UpdateOfficerProfileEvent.java

package messaging.officer;

import java.util.Collection;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import mojo.km.messaging.RequestEvent;

public class UpdateOfficerProfilesEvent extends RequestEvent
{
 	/**
	 * 
	 */
	private String newManagerId;
	private String newManagerFirstName;
	private String newManagerMiddleName;
	private String newManagerLastName;
	private static final long serialVersionUID = 1L;
	 
  	private Collection<OfficerProfileResponseEvent> updateOfficerProfilesEvents;


	public Collection<OfficerProfileResponseEvent> getUpdateOfficerProfilesEvents() {
		return updateOfficerProfilesEvents;
	}


	public void setUpdateOfficerProfilesEvents(
			Collection<OfficerProfileResponseEvent> updateOfficerProfilesEvents) {
		this.updateOfficerProfilesEvents = updateOfficerProfilesEvents;
	}

	/**
	 * @roseuid 42E67A0000A9
	 */
	public UpdateOfficerProfilesEvent()
	{
	}


	public String getNewManagerId() {
		return newManagerId;
	}


	public void setNewManagerId(String newManagerId) {
		this.newManagerId = newManagerId;
	}


	public String getNewManagerFirstName() {
		return newManagerFirstName;
	}


	public void setNewManagerFirstName(String newManagerFirstName) {
		this.newManagerFirstName = newManagerFirstName;
	}


	public String getNewManagerMiddleName() {
		return newManagerMiddleName;
	}


	public void setNewManagerMiddleName(String newManagerMiddleName) {
		this.newManagerMiddleName = newManagerMiddleName;
	}


	public String getNewManagerLastName() {
		return newManagerLastName;
	}


	public void setNewManagerLastName(String newManagerLastName) {
		this.newManagerLastName = newManagerLastName;
	}
	 
}