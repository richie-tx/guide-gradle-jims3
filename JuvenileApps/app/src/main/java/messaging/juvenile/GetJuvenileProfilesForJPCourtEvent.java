package messaging.juvenile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 *  NOT IN CURRENT USE - 1/9/07. Replaced with JCC.DEFENDENT.
 */
public class GetJuvenileProfilesForJPCourtEvent extends RequestEvent
{

	private String lastName;
	private Date dateOfBirth;
	private String juvenileNum;
	

	/**
	 * 
	 */
	public GetJuvenileProfilesForJPCourtEvent()
	{

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
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
}
