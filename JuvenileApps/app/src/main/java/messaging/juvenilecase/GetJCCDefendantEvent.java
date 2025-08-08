package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;
/**
 * 
 *
 */
public class GetJCCDefendantEvent extends RequestEvent 
{
	private String firstName;
	private String middleName;
	private String lastName;
	private String dateOfBirth;
	private String m204JuvNumber;
   
   /**
    * 
    */
   public GetJCCDefendantEvent() 
   {
    
   }
   
	/**
	 * @return Returns the dateOfBirth.
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth The dateOfBirth to set.
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return Returns the middleName.
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName The middleName to set.
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
}
