package messaging.juvenilewarrant.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author asrvastava
 *
 */
public class JuvenileWarrantServiceResponseEvent extends ResponseEvent
{
	private String serviceID;
	private Date serviceTimeStamp;

	private String comments;
	private String serviceStatusId;
	private String serviceStatus;
	private String airFare;
	private String perDiem;
	private String mileage;
	private boolean isBadAddress;
	private String streetNum;
	private String streetName;
	private String countyId;
	private String county;
	private String state;
	private String city;
	private String zipCode;
	private String additionalZipCode;
	private String aptNumber;
	private String streetType;
	private String addressType;
	private String addressTypeId;
	private String streetTypeType;
	private String streetTypeId;
	// executor info
	private String executorOfficerId;
	private String executorFirstName;
	private String executorMiddleName;
	private String executorLastName;
	private String executorIdType;
	private String executorAgencyName;
	private String executorPhoneNum;
	private String executorCellNum;
	private String executorPager;
	private String executorEmail;
	

	/**
	 * @return
	 */
	public String getAirFare()
	{
		return airFare;
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
	public String getComments()
	{
		return comments;
	}

	/**
	 * @return
	 */
	public boolean isBadAddress()
	{
		return isBadAddress;
	}

	/**
	 * @return
	 */
	public String getMileage()
	{
		return mileage;
	}

	/**
	 * @return
	 */
	public String getPerDiem()
	{
		return perDiem;
	}

	/**
	 * @return
	 */
	public String getServiceID()
	{
		return serviceID;
	}

	/**
	 * @return
	 */
	public String getServiceStatusId()
	{
		return serviceStatusId;
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
	public String getStreetNum()
	{
		return streetNum;
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
	public void setAirFare(String string)
	{
		airFare = string;
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
	public void setComments(String string)
	{
		comments = string;
	}

	/**
	 * @param b
	 */
	public void setBadAddress(boolean b)
	{
		isBadAddress = b;
	}

	/**
	 * @param string
	 */
	public void setMileage(String string)
	{
		mileage = string;
	}

	/**
	 * @param string
	 */
	public void setPerDiem(String string)
	{
		perDiem = string;
	}

	/**
	 * @param string
	 */
	public void setServiceID(String string)
	{
		serviceID = string;
	}

	/**
	 * @param string
	 */
	public void setServiceStatusId(String string)
	{
		serviceStatusId = string;
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
	public void setStreetNum(String string)
	{
		streetNum = string;
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
	public String getServiceStatus()
	{
		return serviceStatus;
	}

	/**
	 * @param string
	 */
	public void setServiceStatus(String string)
	{
		serviceStatus = string;
	}

	/**
	 * @return
	 */
	public String getAptNumber()
	{
		return aptNumber;
	}

	/**
	 * @param string
	 */
	public void setAptNumber(String string)
	{
		aptNumber = string;
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
	public void setStreetType(String string)
	{
		streetType = string;
	}

	/**
	 * @return
	 */
	public String getAddressTypeId()
	{
		return addressTypeId;
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
	public String getStreetTypeType()
	{
		return streetTypeType;
	}

	/**
	 * @param string
	 */
	public void setAddressTypeId(String string)
	{
		addressTypeId = string;
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
	public void setStreetTypeType(String string)
	{
		streetTypeType = string;
	}

	/**
	 * @return
	 */
	public String getAdditionalZipCode()
	{
		return additionalZipCode;
	}

	/**
	 * @return
	 */
	public String getExecutorAgencyName()
	{
		return executorAgencyName;
	}

	/**
	 * @return
	 */
	public String getExecutorCellNum()
	{
		return executorCellNum;
	}

	/**
	 * @return
	 */
	public String getExecutorEmail()
	{
		return executorEmail;
	}

	/**
	 * @return
	 */
	public String getExecutorFirstName()
	{
		return executorFirstName;
	}

	/**
	 * @return
	 */
	public String getExecutorIdType()
	{
		return executorIdType;
	}

	/**
	 * @return
	 */
	public String getExecutorLastName()
	{
		return executorLastName;
	}

	/**
	 * @return
	 */
	public String getExecutorMiddleName()
	{
		return executorMiddleName;
	}

	/**
	 * @return
	 */
	public String getExecutorOfficerId()
	{
		return executorOfficerId;
	}

	/**
	 * @return
	 */
	public String getExecutorPager()
	{
		return executorPager;
	}

	/**
	 * @return
	 */
	public String getExecutorPhoneNum()
	{
		return executorPhoneNum;
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
	public void setExecutorAgencyName(String string)
	{
		executorAgencyName = string;
	}

	/**
	 * @param string
	 */
	public void setExecutorCellNum(String string)
	{
		executorCellNum = string;
	}

	/**
	 * @param string
	 */
	public void setExecutorEmail(String string)
	{
		executorEmail = string;
	}

	/**
	 * @param string
	 */
	public void setExecutorFirstName(String string)
	{
		executorFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setExecutorIdType(String string)
	{
		executorIdType = string;
	}

	/**
	 * @param string
	 */
	public void setExecutorLastName(String string)
	{
		executorLastName = string;
	}

	/**
	 * @param string
	 */
	public void setExecutorMiddleName(String string)
	{
		executorMiddleName = string;
	}

	/**
	 * @param string
	 */
	public void setExecutorOfficerId(String string)
	{
		executorOfficerId = string;
	}

	/**
	 * @param string
	 */
	public void setExecutorPager(String string)
	{
		executorPager = string;
	}

	/**
	 * @param string
	 */
	public void setExecutorPhoneNum(String string)
	{
		executorPhoneNum = string;
	}

	/**
	 * @return
	 */
	public String getCounty()
	{
		return county;
	}

	/**
	 * @return
	 */
	public String getCountyId()
	{
		return countyId;
	}

	/**
	 * @param string
	 */
	public void setCounty(String string)
	{
		county = string;
	}

	/**
	 * @param string
	 */
	public void setCountyId(String string)
	{
		countyId = string;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		if(streetNum != null && !streetNum.equals("")){
			sb.append(streetNum); 
		}
		if(streetName != null && !streetName.equals("")){
			sb.append(" " + streetName); 
		}
		if(streetType != null && !streetType.equals("")){
			sb.append(" " + streetType); 
		}
		if(aptNumber != null && !aptNumber.equals("")){
			sb.append(" #" + aptNumber); 
		}
		if(city != null && !city.equals("")){
			sb.append(", " + city); 
		}
		if(state != null && !state.equals("")){
			sb.append(" " + state); 
		}
		if(zipCode != null && !zipCode.equals("")){
			sb.append(" " + zipCode);
			if(additionalZipCode != null && !additionalZipCode.equals("")){
				sb.append("-" + additionalZipCode);
			}
		}
		return sb.toString();
		
	}
}
