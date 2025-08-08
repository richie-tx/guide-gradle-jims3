package messaging.interviewinfo.to;

import java.util.Date;


/**
 *
 */
public class JPCourtReferralTO extends ExcludedTO
{
	private String name = "";		// Full name.
	private String firstName = "";
	private String middleName = "";
	private String lastName = "";
	private Date dateOfBirth;
	private String race = "";
	private String m204JuvNumber = "";
	
	
	/**
	 * @return Returns the m204JuvId.
	 */
	public String getM204JuvNumber() {
		return m204JuvNumber;
	}
	/**
	 * @param juvId The m204JuvId to set.
	 */
	public void setM204JuvNumber(String juvNumber) {
		m204JuvNumber = juvNumber;
	}
	/**
	 * @return Returns the dateOfBirth.
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth The dateOfBirth to set.
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the race.
	 */
	public String getRace() {
		return race;
	}
	/**
	 * @param race The race to set.
	 */
	public void setRace(String race) {
		this.race = race;
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
