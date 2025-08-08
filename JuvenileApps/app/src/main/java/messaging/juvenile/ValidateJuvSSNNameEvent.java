/*
 * Created on Jun 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ValidateJuvSSNNameEvent extends RequestEvent {

	private String juvenileNum;
	private String firstName;
	private String middleName;
	private String lastName;
	private String ssn;
	
	private String juvRecType;
	
	
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
	/**
	 * @return Returns the ssn.
	 */
	public String getSsn() {
		return ssn;
	}
	/**
	 * @param ssn The ssn to set.
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getJuvRecType()
	{
	    return juvRecType;
	}
	public void setJuvRecType(String juvRecType)
	{
	    this.juvRecType = juvRecType;
	}
}// END CLASS
