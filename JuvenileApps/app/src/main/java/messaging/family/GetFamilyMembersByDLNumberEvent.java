/*
 * Created on Jan 30, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import mojo.km.messaging.RequestEvent;

/**
 * @author ugopinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetFamilyMembersByDLNumberEvent extends RequestEvent 
{

	private String driverLicenseNum;
	
	

	/**
	 * @return Returns the ssn.
	 */
	public String getDriverLicenseNum() {
		return driverLicenseNum;
	}
	/**
	 * @param ssn The ssn to set.
	 */
	public void setDriverLicenseNum(String driverLicenseNum) {
		this.driverLicenseNum = driverLicenseNum;
	}
}
