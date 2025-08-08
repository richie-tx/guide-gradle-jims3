/*
 * Created on Oct 19, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.contact.party.form;

import org.apache.struts.action.ActionForm;

/**
 * @author dapte
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PartySearchForm extends ActionForm {
	
	// for search by spn
	private String spn;
	// for search by logon Id
	private String logonId;
	// for search by name
	private String  lastName;
	private String  firstName;
	private String  middleName;
	private String 	businessName;
	private String 	dob;
	private String age;
	private String race;
	private String sex;
	// for search by id numbers
	private String barNumber;
	private String ssn;
	private String bondsmanLicenseNumber;
	private String driverLicenseIdNumber;
	private String issuingState;
	// for search by officer
	private String officerId;
	private String officerIdType;
	private String agency;
	// for search by capacity
	private String capacity;
	
	// on the create/update form
	private String daLogNumber;
	
	
	/**
	 * Clear form
	 */
	private void clear() {
		 spn = "";
		 logonId = "";
		 lastName = "";
		 firstName = "";
		 middleName = "";
		 businessName = "";
		 dob= "";
		 age = "";
		 race = "";
		 sex = "";
		 barNumber = "";
		 ssn = "";
		 bondsmanLicenseNumber = "";
		 driverLicenseIdNumber = "";
		 issuingState = "";
		 officerId = "";
		 officerIdType = "";
		 agency = "";
		 capacity = "";	
	}
	
	/**
	 *  Clear Form
	 */
	public void reset() {
		clear();
	}
	
	/**
	 * @return String
	 */
	public String getAge()
	{
		return age;
	}

	/**
	 * @return String
	 */
	public String getAgency()
	{
		return agency;
	}

	/**
	 * @return String
	 */
	public String getBarNumber()
	{
		return barNumber;
	}

	/**
	 * @return String
	 */
	public String getBondsmanLicenseNumber()
	{
		return bondsmanLicenseNumber;
	}

	/**
	 * @return String
	 */
	public String getBusinessName()
	{
		return businessName;
	}

	/**
	 * @return String
	 */
	public String getCapacity()
	{
		return capacity;
	}

	/**
	 * @return String
	 */
	public String getDob()
	{
		return dob;
	}

	/**
	 * @return String
	 */
	public String getDriverLicenseIdNumber()
	{
		return driverLicenseIdNumber;
	}

	/**
	 * @return String
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return String
	 */
	public String getIssuingState()
	{
		return issuingState;
	}

	/**
	 * @return String
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return String
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * @return String
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @return String
	 */
	public String getOfficerId()
	{
		return officerId;
	}

	/**
	 * @return String
	 */
	public String getOfficerIdType()
	{
		return officerIdType;
	}

	/**
	 * @return String
	 */
	public String getRace()
	{
		return race;
	}

	/**
	 * @return String
	 */
	public String getSex()
	{
		return sex;
	}

	/**
	 * @return String
	 */
	public String getSpn()
	{
		return spn;
	}

	/**
	 * @return String
	 */
	public String getSsn()
	{
		return ssn;
	}

	/**
	 * @param string
	 */
	public void setAge(String string)
	{
		age = string;
	}

	/**
	 * @param string
	 */
	public void setAgency(String string)
	{
		agency = string;
	}

	/**
	 * @param string
	 */
	public void setBarNumber(String string)
	{
		barNumber = string;
	}

	/**
	 * @param string
	 */
	public void setBondsmanLicenseNumber(String string)
	{
		bondsmanLicenseNumber = string;
	}

	/**
	 * @param string
	 */
	public void setBusinessName(String string)
	{
		businessName = string;
	}

	/**
	 * @param string
	 */
	public void setCapacity(String string)
	{
		capacity = string;
	}

	/**
	 * @param string
	 */
	public void setDob(String string)
	{
		dob = string;
	}

	/**
	 * @param string
	 */
	public void setDriverLicenseIdNumber(String string)
	{
		driverLicenseIdNumber = string;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	}

	/**
	 * @param string
	 */
	public void setIssuingState(String string)
	{
		issuingState = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string)
	{
		lastName = string;
	}

	/**
	 * @param string
	 */
	public void setLogonId(String string)
	{
		logonId = string;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string)
	{
		middleName = string;
	}

	/**
	 * @param string
	 */
	public void setOfficerId(String string)
	{
		officerId = string;
	}

	/**
	 * @param string
	 */
	public void setOfficerIdType(String string)
	{
		officerIdType = string;
	}

	/**
	 * @param string
	 */
	public void setRace(String string)
	{
		race = string;
	}

	/**
	 * @param string
	 */
	public void setSex(String string)
	{
		sex = string;
	}

	/**
	 * @param string
	 */
	public void setSpn(String string)
	{
		spn = string;
	}

	/**
	 * @param string
	 */
	public void setSsn(String string)
	{
		ssn = string;
	}

	/**
	 * @return String
	 */
	public String getDaLogNumber()
	{
		return daLogNumber;
	}

	/**
	 * @param string
	 */
	public void setDaLogNumber(String string)
	{
		daLogNumber = string;
	}

}
