/*
 * Created on Dec 16, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.contact.reply;

import org.apache.commons.lang.StringUtils;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EmployerResponseEvent extends ResponseEvent
{
	private String additionalZipCode;
	private String address2;
	private String city;
	private String currentEmployerInd;
	private String employerId;
	private String employerName;
	private String employmentStatus;
	private String employmentStatusId;
	private String occupation;
	private String partyId;
	private String phoneNum;
	private String state;
	private String stateId;
	private String streetName;
	private String streetNum;
	private String streetType;
	private String streetTypeId;
	private String zipCode;

	/**
	 * @return
	 */
	public String getAdditionalZipCode()
	{
		if (additionalZipCode == null) {
			if (zipCode != null && zipCode.length() > 5) {
				additionalZipCode = zipCode.substring(5);
			}
		}
		return additionalZipCode;
	}

	/**
	 * @return
	 */
	public String getAddress2()
	{
		return address2;
	}

	/**
	 * @return
	 */
	public String getCity()
	{
		return city;
	}



	/**
	 * @return
	 */
	public String getCurrentEmployerInd()
	{
		return currentEmployerInd;
	}

	/**
	 * @return
	 */
	public String getEmployerId()
	{
		return employerId;
	}

	/**
	 * @return
	 */
	public String getEmployerName()
	{
		return employerName;
	}

	/**
	 * @return
	 */
	public String getEmploymentStatus()
	{
		return employmentStatus;
	}

	/**
	 * @return
	 */
	public String getEmploymentStatusId()
	{
		return employmentStatusId;
	}

	/**
	 * @return
	 */
	public String getOccupation()
	{
		return occupation;
	}

	/**
	 * @return
	 */
	public String getPartyId()
	{
		return partyId;
	}

	/**
	 * @return
	 */
	public String getPhoneNum()
	{
		return phoneNum;
	}

	/**
	 * @return
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * @return
	 */
	public String getStateId()
	{
		return stateId;
	}

	/**
	 * @return
	 */
	public String getStreetName()
	{
		return streetName;
	}

	/**
	 * @return
	 */
	public String getStreetNum()
	{
		return streetNum;
	}

	/**
	 * @return
	 */
	public String getStreetType()
	{
		return streetType;
	}

	/**
	 * @return
	 */
	public String getStreetTypeId()
	{
		return streetTypeId;
	}

	/**
	 * @return
	 */
	public String getZipCode()
	{
		String s = "";
		if (zipCode != null && zipCode.length() >= 5) {
			s = zipCode.substring(0, 5);
		}
		return s;
	}
	
	/**
	 * @return String
	 */
	public String getCompleteZipCode() {
		StringBuffer zip = new StringBuffer();
		zip.append(getZipCode());
		if ( StringUtils.isNotEmpty(getAdditionalZipCode()) ) {
			zip.append("-");
			zip.append(getAdditionalZipCode());
		}
		return zip.toString();	
	}
	
	/**
	 * @return
	 */
	public String getFullZipCode() {
		return zipCode;
	}
	
	/**
	 * @param string
	 */
	public void setAdditionalZipCode(String string)
	{
		additionalZipCode = string;
	}

	/**
	 * @param string
	 */
	public void setAddress2(String string)
	{
		address2 = string;
	}

	/**
	 * @param string
	 */
	public void setCity(String string)
	{
		city = string;
	}

	/**
	 * @param string
	 */
	public void setCurrentEmployerInd(String string)
	{
		currentEmployerInd = string;
	}

	/**
	 * @param string
	 */
	public void setEmployerId(String string)
	{
		employerId = string;
	}

	/**
	 * @param string
	 */
	public void setEmployerName(String string)
	{
		employerName = string;
	}

	/**
	 * @param string
	 */
	public void setEmploymentStatus(String string)
	{
		employmentStatus = string;
	}

	/**
	 * @param string
	 */
	public void setEmploymentStatusId(String string)
	{
		employmentStatusId = string;
	}

	/**
	 * @param string
	 */
	public void setOccupation(String string)
	{
		occupation = string;
	}

	/**
	 * @param string
	 */
	public void setPartyId(String string)
	{
		partyId = string;
	}

	/**
	 * @param string
	 */
	public void setPhoneNum(String string)
	{
		phoneNum = string;
	}

	/**
	 * @param string
	 */
	public void setState(String string)
	{
		state = string;
	}

	/**
	 * @param string
	 */
	public void setStateId(String string)
	{
		stateId = string;
	}

	/**
	 * @param string
	 */
	public void setStreetName(String string)
	{
		streetName = string;
	}

	/**
	 * @param string
	 */
	public void setStreetNum(String string)
	{
		streetNum = string;
	}

	/**
	 * @param string
	 */
	public void setStreetType(String string)
	{
		streetType = string;
	}

	/**
	 * @param string
	 */
	public void setStreetTypeId(String string)
	{
		streetTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setZipCode(String string)
	{
		zipCode = string;
	}

}
