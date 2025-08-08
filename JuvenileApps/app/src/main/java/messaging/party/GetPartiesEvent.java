/*
 * Created on May 1, 2006
 *
 */
package messaging.party;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetPartiesEvent extends RequestEvent
{
	private Date birthDate;
	private String cjisNum; 
	private String connectionId;
	private String driverLicenseNum;
	private String driverLicenseStateId;
	private String fbiNum;
	private String firstName;
	private String lastName;
	private String middleName;
	private String raceId;
	private String sexId;
	private String sidNum;
	private String ssn;
	
	/**
	 * @return
	 */
	public Date getBirthDate()
	{
		return birthDate;
	}

	/**
	 * @return
	 */
	public String getCjisNum()
	{
		return cjisNum;
	}

	/**
	 * @return
	 */
	public String getConnectionId()
	{
		return connectionId;
	}

	/**
	 * @return
	 */
	public String getDriverLicenseNum()
	{
		return driverLicenseNum;
	}

	/**
	 * @return
	 */
	public String getDriverLicenseStateId()
	{
		return driverLicenseStateId;
	}

	/**
	 * @return
	 */
	public String getFbiNum()
	{
		return fbiNum;
	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @return
	 */
	public String getRaceId()
	{
		return raceId;
	}

	/**
	 * @return
	 */
	public String getSexId()
	{
		return sexId;
	}

	/**
	 * @return
	 */
	public String getSidNum()
	{
		return sidNum;
	}

	/**
	 * @return
	 */
	public String getSsn()
	{
		return ssn;
	}

	/**
	 * @param date
	 */
	public void setBirthDate(Date date)
	{
		birthDate = date;
	}

	/**
	 * @param string
	 */
	public void setCjisNum(String string)
	{
		cjisNum = string;
	}

	/**
	 * @param string
	 */
	public void setConnectionId(String string)
	{
		connectionId = string;
	}

	/**
	 * @param string
	 */
	public void setDriverLicenseNum(String string)
	{
		driverLicenseNum = string;
	}

	/**
	 * @param string
	 */
	public void setDriverLicenseStateId(String string)
	{
		driverLicenseStateId = string;
	}

	/**
	 * @param string
	 */
	public void setFbiNum(String string)
	{
		fbiNum = string;
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
	public void setLastName(String string)
	{
		lastName = string;
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
	public void setRaceId(String string)
	{
		raceId = string;
	}

	/**
	 * @param string
	 */
	public void setSexId(String string)
	{
		sexId = string;
	}

	/**
	 * @param string
	 */
	public void setSidNum(String string)
	{
		sidNum = string;
	}

	/**
	 * @param string
	 */
	public void setSsn(String string)
	{
		ssn = string;
	}

}
