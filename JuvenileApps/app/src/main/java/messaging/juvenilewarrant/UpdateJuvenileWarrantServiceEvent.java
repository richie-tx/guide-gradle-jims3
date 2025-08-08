//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\UpdateJuvenileWarrantServiceEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class UpdateJuvenileWarrantServiceEvent extends RequestEvent
{
	private String officerDepartmentId;
	private String apartmentNumber;
	private Double airFare;
	private String comments;
	private Double mileage;
	private Double perDiem;
	private String city;
	private String county;
	private String state;
	private Date serviceTimeStamp;
	private String serviceStatus;
	private String streetName;
	private String streetNumber;
	private String streetType;
	private String addressType;
	private String zipCode;
	private String additionalZipCode;
	private String logonId; // not being used in I6
	private String officerId;
	private String warrantNumber;
	private boolean isBadAddress;
	private String addressId;

	/**
	 * @roseuid 41FFDB0F02AF
	 */
	public UpdateJuvenileWarrantServiceEvent()
	{

	}

	/**
	 * @return
	 */
	public Double getAirFare()
	{
		return airFare;
	}

	/**
	 * @return
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * @return
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * @return
	 */
	public Double getMileage()
	{
		return mileage;
	}

	/**
	 * @return
	 */
	public String getOfficerId()
	{
		return officerId;
	}

	/**
	 * @return
	 */
	public Double getPerDiem()
	{
		return perDiem;
	}

	/**
	 * @return
	 */
	public String getServiceStatus()
	{
		return serviceStatus;
	}

	/**
	 * @return
	 */
	public String getWarrantNumber()
	{
		return warrantNumber;
	}

	/**
	 * @param string
	 */
	public void setAirFare(Double airFare)
	{
		this.airFare = airFare;
	}

	/**
	 * @param string
	 */
	public void setComments(String string)
	{
		comments = string;
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
	public void setMileage(Double mileage)
	{
		this.mileage = mileage;
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
	public void setPerDiem(Double perDiem)
	{
		this.perDiem = perDiem;
	}

	/**
	 * @param string
	 */
	public void setServiceStatus(String string)
	{
		serviceStatus = string;
	}

	/**
	 * @param string
	 */
	public void setWarrantNumber(String string)
	{
		warrantNumber = string;
	}

	/**
	 * @return
	 */
	public boolean isBadAddress()
	{
		return isBadAddress;
	}

	/**
	 * @param b
	 */
	public void setBadAddress(boolean b)
	{
		isBadAddress = b;
	}

	/**
	 * @return
	 */
	public Date getServiceTimeStamp()
	{
		return serviceTimeStamp;
	}

	/**
	 * @param date
	 */
	public void setServiceTimeStamp(Date date)
	{
		serviceTimeStamp = date;
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
	public String getState()
	{
		return state;
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
	public String getStreetNumber()
	{
		return streetNumber;
	}

	/**
	 * @return
	 */
	public String getZipCode()
	{
		return zipCode;
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
	public void setState(String string)
	{
		state = string;
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
	public void setStreetNumber(String string)
	{
		streetNumber = string;
	}

	/**
	 * @param string
	 */
	public void setZipCode(String string)
	{
		zipCode = string;
	}

	/**
	 * @return
	 */
	public String getAddressType()
	{
		return addressType;
	}

	/**
	 * @return
	 */
	public String getApartmentNumber()
	{
		return apartmentNumber;
	}

	/**
	 * @return
	 */
	public String getStreetType()
	{
		return streetType;
	}

	/**
	 * @param string
	 */
	public void setAddressType(String string)
	{
		addressType = string;
	}

	/**
	 * @param string
	 */
	public void setApartmentNumber(String string)
	{
		apartmentNumber = string;
	}

	/**
	 * @param string
	 */
	public void setStreetType(String string)
	{
		streetType = string;
	}

	/**
	 * @return
	 */
	public String getAddressId()
	{
		return addressId;
	}

	/**
	 * @param string
	 */
	public void setAddressId(String string)
	{
		addressId = string;
	}

	/**
	 * @return
	 */
	public String getAdditionalZipCode()
	{
		return additionalZipCode;
	}

	/**
	 * @param string
	 */
	public void setAdditionalZipCode(String string)
	{
		additionalZipCode = string;
	}

	/**
	 * @return
	 */
	public String getCounty()
	{
		return county;
	}

	/**
	 * @param string
	 */
	public void setCounty(String string)
	{
		county = string;
	}

	/**
	 * @return
	 */
	public String getOfficerDepartmentId()
	{
		return officerDepartmentId;
	}

	/**
	 * @param string
	 */
	public void setOfficerDepartmentId(String string)
	{
		officerDepartmentId = string;
	}

}
