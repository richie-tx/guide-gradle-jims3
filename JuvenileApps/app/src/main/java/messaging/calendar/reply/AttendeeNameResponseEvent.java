package messaging.calendar.reply;


import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.Name;


/**
 *
 */
public class AttendeeNameResponseEvent extends ResponseEvent implements Comparable
{
	private String addlAttendeeId;
	private String lastName;
	private String middleName;
	private String firstName;
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		AttendeeNameResponseEvent input = (AttendeeNameResponseEvent) o;
		if(this.lastName==null){
			return -1;
		}
		else if (input.lastName==null){
			return 1;
		}
		int compare = this.lastName.compareTo(input.lastName);
		if(compare == 0) {
			if(this.firstName==null){
				return -1;
			}
			else if (input.firstName==null){
				return 1;
			}
			compare = this.firstName.compareTo(input.firstName);
			
			if(compare == 0) {
				if(this.middleName==null){
					return -1;
				}
				else if (input.middleName==null){
					return 1;
				}
				compare = this.middleName.compareTo(input.middleName);
			}
		}
		
		
			
		return compare;
		
	}

	public String getAddlAttendeeId() {
		return addlAttendeeId;
	}

	public void setAddlAttendeeId(String addlAttendeeId) {
		this.addlAttendeeId = addlAttendeeId;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFormattedName()
	{
		Name name = new Name(firstName, middleName, lastName);
		return name.getFormattedName();	
	}
}
