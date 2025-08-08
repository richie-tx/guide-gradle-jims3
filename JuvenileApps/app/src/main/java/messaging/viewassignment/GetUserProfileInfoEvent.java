package messaging.viewassignment;

import mojo.km.messaging.RequestEvent;

public class GetUserProfileInfoEvent extends RequestEvent {
	private String lastName;
	
	private String firstName;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
