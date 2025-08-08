/*
 * Created on Jun 10, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileContactNameResponseEvent extends ResponseEvent 
{
	private String contactNum;
	private String firstName;
	private String lastName;
	private String middleName;
	public String getFormattedName(){
		StringBuffer full = new StringBuffer();
		if(lastName != null){
			full.append(lastName);
		}
		if(firstName != null)
		{
			full.append(", ");
			full.append(firstName);
		}
		return full.toString();
		
	}
	
	/**
	 * @return Returns the contactNum.
	 */
	public String getContactNum() {
		return contactNum;
	}
	/**
	 * @param contactNum The contactNum to set.
	 */
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
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
