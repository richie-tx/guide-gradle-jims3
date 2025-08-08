//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercasenotes\\GetSuperviseesInSupervisionPeriodEvent.java

package messaging.administercasenotes;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetSuperviseesInSupervisionPeriodEvent extends RequestEvent 
{
	private boolean activeSupervisionPeriod;
	private String cjisNum;
	private Date dateOfBirth;
	private String driverLicenseNum;
	private String driverLicenseStateId;
	private String fbiNum;
	private String firstName;
   
	private String lastName;
	private String middleName;
	private String raceId;
	private String sexId;
	private String sidNum;
	private String spn;
	private String ssn;
	private String supervisionPeriodId;
	private String userAgencyId;

   /**
    * @roseuid 44F461740289
    */
	public GetSuperviseesInSupervisionPeriodEvent() 
	{  
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
	public Date getDateOfBirth()
	{
		return dateOfBirth;
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
	public String getSpn()
	{
		return spn;
	}

	/**
	 * @return
	 */
	public String getSsn()
	{
		return ssn;
	}
	/**
	 * @return Returns the supervisionPeriodId.
	 */
	public String getSupervisionPeriodId() {
		return supervisionPeriodId;
	}

	/**
	 * @return Returns the userAgencyId.
	 */
	public String getUserAgencyId()
	{
		return userAgencyId;
	}

	/**
	 * @return Returns the activeSupervisionPeriod indicator.
	 */
	public boolean isActiveSupervisionPeriod()
	{
		return activeSupervisionPeriod;
	}

	/**
	 * @param value  The value for which to set the activeSupervisionPeriod indicator.
	 */
	public void setActiveSupervisionPeriod(boolean value)
	{
		this.activeSupervisionPeriod = value;
	}
	
	/**
	 * @param value
	 */
	public void setCjisNum(String value)
	{
		cjisNum = value;
	}

	/**
	 * @param aDate
	 */
	public void setDateOfBirth(Date aDate)
	{
		dateOfBirth = aDate;
	}

	/**
	 * @param value
	 */
	public void setDriverLicenseNum(String value)
	{
		driverLicenseNum = value;
	}

	/**
	 * @param value
	 */
	public void setDriverLicenseStateId(String value)
	{
		driverLicenseStateId = value;
	}

	/**
	 * @param value
	 */
	public void setFbiNum(String value)
	{
		fbiNum = value;
	}

	/**
	 * @param value
	 */
	public void setFirstName(String value)
	{
		firstName = value;
	}

	/**
	 * @param value
	 */
	public void setLastName(String value)
	{
		lastName = value;
	}

	/**
	 * @param value
	 */
	public void setMiddleName(String value)
	{
		middleName = value;
	}

	/**
	 * @param value
	 */
	public void setRaceId(String value)
	{
		raceId = value;
	}

	/**
	 * @param value
	 */
	public void setSexId(String value)
	{
		sexId = value;
	}

	/**
	 * @param value
	 */
	public void setSidNum(String value)
	{
		sidNum = value;
	}

	/**
	 * @param value
	 */
	public void setSpn(String value)
	{
		spn = value;
	}

	/**
	 * @param value
	 */
	public void setSsn(String value)
	{
		ssn = value;
	}
	/**
	 * @param supervisionPeriodId The supervisionPeriodId to set.
	 */
	public void setSupervisionPeriodId(String supervisionPeriodId) {
		this.supervisionPeriodId = supervisionPeriodId;
	}

	/**
	 * @param anAgencyId The userAgencyId to set.
	 */
	public void setUserAgencyId(String anAgencyId)
	{
		this.userAgencyId = anAgencyId;
	}
}
