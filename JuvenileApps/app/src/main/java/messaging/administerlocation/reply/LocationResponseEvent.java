/*
 * Created on Oct 19, 2004
 */
package messaging.administerlocation.reply;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import messaging.address.reply.AddressResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 */
public class LocationResponseEvent extends ResponseEvent implements Comparable
{
    private String locationName;
    private boolean inHouse;
    private String agencyId;
    private String locationId;
    private String status;
    private String statusId;
    private String serviceProviderName;
    private AddressResponseEvent locationAddress;
    private String facilityTypeId;
    private String facilityType;
    private String streetNum;
    private String streetName;
    private String city;
    private String aptNumber;
    private String stateId;
    private String zipCode;
    private String phoneNumber;
    private String locationFax;
    private String locationUnitName;
    private String juvLocationUnitId;
    private String juvLocationUnitPhoneNumber;
    private String juvUnitCd;
    private String locationCd;
    //for location inactivate notification
    private String notificationMessage;

    private Collection serviceTypeResponseEvents;

    private List locationUnits;

    private String locationRegion;
    private String locationRegionId;

    private String locationUnitSelectLabel;
    private int maysiFlg;
    private int drugFlag;
    private int officerProfileFlag;
    private int interviewCalFlag;
    private String serviceId;

    public String getServiceId()
    {
	return serviceId;
    }

    public void setServiceId(String serviceId)
    {
	this.serviceId = serviceId;
    }

    /**
     * @return
     */
    public boolean getInHouse()
    {
	return inHouse;
    }

    /**
     * @return
     */
    public String getAgencyId()
    {
	return agencyId;
    }

    /**
     * @param string
     */
    public void setLocationName(String string)
    {
	locationName = string;
    }

    /**
     * @param bool
     */
    public void setInHouse(boolean bool)
    {
	inHouse = bool;
    }

    /**
     * @param string
     */
    public void setAgencyId(String string)
    {
	agencyId = string;
    }

    /**
     * @return
     */
    public String getLocationId()
    {
	return locationId;
    }

    /**
     * @param string
     */
    public void setLocationId(String string)
    {
	locationId = string;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object o)
    {
	if (o == null)
	    return 1;
	if (!(o instanceof LocationResponseEvent))
	    return 1;
	LocationResponseEvent l = (LocationResponseEvent) o;
	return this.locationName.compareToIgnoreCase(l.getLocationName());

    }

    public static Comparator JuvUnitNameComparator = new Comparator() {
	public int compare(Object locationUnit, Object otherLocationUnit)
	{
	    String unitName = ((LocationResponseEvent) locationUnit).getLocationUnitName();
	    String otherUnitName = ((LocationResponseEvent) otherLocationUnit).getLocationUnitName();
	    if (unitName != null && otherUnitName != null)
	    {
		return unitName.compareTo(otherUnitName);
	    }
	    else
	    {
		return 0;
	    }
	}
    };

    /**
     * @return Returns the addressResponseEvent.
     */
    public AddressResponseEvent getLocationAddress()
    {
	return locationAddress;
    }

    /**
     * @param addressResponseEvent
     *            The addressResponseEvent to set.
     */
    public void setLocationAddress(AddressResponseEvent locationAddress)
    {
	this.locationAddress = locationAddress;
    }

    /**
     * @return Returns the serviceProviderName.
     */
    public String getServiceProviderName()
    {
	return serviceProviderName;
    }

    /**
     * @param serviceProviderName
     *            The serviceProviderName to set.
     */
    public void setServiceProviderName(String serviceProviderName)
    {
	this.serviceProviderName = serviceProviderName;
    }

    /**
     * @return Returns the status.
     */
    public String getStatus()
    {
	return status;
    }

    /**
     * @param status
     *            The status to set.
     */
    public void setStatus(String status)
    {
	this.status = status;
    }

    /**
     * @return Returns the statusId.
     */
    public String getStatusId()
    {
	return statusId;
    }

    /**
     * @param statusId
     *            The statusId to set.
     */
    public void setStatusId(String statusId)
    {
	this.statusId = statusId;
    }

    /**
     * @return Returns the locationRegion.
     */
    public String getLocationRegion()
    {
	return locationRegion;
    }

    /**
     * @param locationRegion
     *            The locationRegion to set.
     */
    public void setLocationRegion(String locationRegion)
    {
	this.locationRegion = locationRegion;
    }

    /**
     * @return Returns the locationRegionId.
     */
    public String getLocationRegionId()
    {
	return locationRegionId;
    }

    /**
     * @param locationRegionId
     *            The locationRegionId to set.
     */
    public void setLocationRegionId(String locationRegionId)
    {
	this.locationRegionId = locationRegionId;
    }

    /**
     * @return Returns the facilityType.
     */
    public String getFacilityType()
    {
	return facilityType;
    }

    /**
     * @param facilityType
     *            The facilityType to set.
     */
    public void setFacilityType(String facilityType)
    {
	this.facilityType = facilityType;
    }

    /**
     * @return Returns the facilityTypeId.
     */
    public String getFacilityTypeId()
    {
	return facilityTypeId;
    }

    /**
     * @param facilityTypeId
     *            The facilityTypeId to set.
     */
    public void setFacilityTypeId(String facilityTypeId)
    {
	this.facilityTypeId = facilityTypeId;
    }

    /**
     * @return Returns the city.
     */
    public String getCity()
    {
	return city;
    }

    /**
     * @param city
     *            The city to set.
     */
    public void setCity(String city)
    {
	this.city = city;
    }

    /**
     * @return Returns the stateId.
     */
    public String getStateId()
    {
	return stateId;
    }

    /**
     * @param stateId
     *            The stateId to set.
     */
    public void setStateId(String stateId)
    {
	this.stateId = stateId;
    }

    /**
     * @return Returns the streetName.
     */
    public String getStreetName()
    {
	return streetName;
    }

    /**
     * @param streetName
     *            The streetName to set.
     */
    public void setStreetName(String streetName)
    {
	this.streetName = streetName;
    }

    /**
     * @return Returns the streetNum.
     */
    public String getStreetNum()
    {
	return streetNum;
    }

    /**
     * @param streetNum
     *            The streetNum to set.
     */
    public void setStreetNum(String streetNum)
    {
	this.streetNum = streetNum;
    }

    /**
     * @return Returns the zipCode.
     */
    public String getZipCode()
    {
	return zipCode;
    }

    /**
     * @param zipCode
     *            The zipCode to set.
     */
    public void setZipCode(String zipCode)
    {
	this.zipCode = zipCode;
    }

    /**
     * @return Returns the phoneNumber.
     */
    public String getPhoneNumber()
    {
	return phoneNumber;
    }

    /**
     * @param phoneNumber
     *            The phoneNumber to set.
     */
    public void setPhoneNumber(String phoneNumber)
    {
	this.phoneNumber = phoneNumber;
    }

    /**
     * @return the locationFax
     */
    public String getLocationFax()
    {
	return locationFax;
    }

    /**
     * @param locationFax
     *            the locationFax to set
     */
    public void setLocationFax(String locationFax)
    {
	this.locationFax = locationFax;
    }

    /**
     * @return Returns the notificationMessage.
     */
    public String getNotificationMessage()
    {
	return notificationMessage;
    }

    /**
     * @param notificationMessage
     *            The notificationMessage to set.
     */
    public void setNotificationMessage(String notificationMessage)
    {
	this.notificationMessage = notificationMessage;
    }

    /**
     * @return Returns the aptNumber.
     */
    public String getAptNumber()
    {
	return aptNumber;
    }

    /**
     * @param aptNumber
     *            The aptNumber to set.
     */
    public void setAptNumber(String aptNumber)
    {
	this.aptNumber = aptNumber;
    }

    /**
     * @return Returns the juvLocationUnitId.
     */
    public String getJuvLocationUnitId()
    {
	return juvLocationUnitId;
    }

    /**
     * @param juvLocationUnitId
     *            The juvLocationUnitId to set.
     */
    public void setJuvLocationUnitId(String juvLocationUnitId)
    {
	this.juvLocationUnitId = juvLocationUnitId;
    }

    /**
     * @return Returns the juvUnitCd.
     */
    public String getJuvUnitCd()
    {
	return juvUnitCd;
    }

    /**
     * @param juvUnitCd
     *            The juvUnitCd to set.
     */
    public void setJuvUnitCd(String juvUnitCd)
    {
	this.juvUnitCd = juvUnitCd;
    }

    /**
     * @return Returns the locationUnitName.
     */
    public String getLocationUnitName()
    {
	return locationUnitName;
    }

    /**
     * @param locationUnitName
     *            The locationUnitName to set.
     */
    public void setLocationUnitName(String locationUnitName)
    {
	this.locationUnitName = locationUnitName;
    }

    /**
     * @return Returns the serviceTypeResponseEvents.
     */
    public Collection getServiceTypeResponseEvents()
    {
	return serviceTypeResponseEvents;
    }

    /**
     * @param serviceTypeResponseEvents
     *            The serviceTypeResponseEvents to set.
     */
    public void setServiceTypeResponseEvents(Collection serviceTypeResponseEvents)
    {
	this.serviceTypeResponseEvents = serviceTypeResponseEvents;
    }

    /**
     * @return Returns the locationUnits.
     */
    public List getLocationUnits()
    {
	return locationUnits;
    }

    /**
     * @param locationUnits
     *            The locationUnits to set.
     */
    public void setLocationUnits(List locationUnits)
    {
	this.locationUnits = locationUnits;
    }

    /**
     * @return Returns the locationCd.
     */
    public String getLocationCd()
    {
	return locationCd;
    }

    /**
     * @param locationCd
     *            The locationCd to set.
     */
    public void setLocationCd(String locationCd)
    {
	this.locationCd = locationCd;
    }

    /**
     * @return Returns the juvLocationUnitPhoneNumber.
     */
    public String getJuvLocationUnitPhoneNumber()
    {
	return juvLocationUnitPhoneNumber;
    }

    /**
     * @param juvLocationUnitPhoneNumber
     *            The juvLocationUnitPhoneNumber to set.
     */
    public void setJuvLocationUnitPhoneNumber(String juvLocationUnitPhoneNumber)
    {
	this.juvLocationUnitPhoneNumber = juvLocationUnitPhoneNumber;
    }

    /**
     * @return
     */
    public String getLocationName()
    {
	return locationName;
    }

    public int getMaysiFlg()
    {
	return maysiFlg;
    }

    public void setMaysiFlg(int maysiFlg)
    {
	this.maysiFlg = maysiFlg;
    }

    public int getDrugFlag()
    {
	return drugFlag;
    }

    public void setDrugFlag(int drugFlag)
    {
	this.drugFlag = drugFlag;
    }

    public String getLocationUnitSelectLabel()
    {
	String statusIdIndicator = this.getStatusId() != null && this.getStatusId().equalsIgnoreCase("I") ? this.getStatusId() : "";
	String statusLabel = statusIdIndicator.equalsIgnoreCase("I") ? this.juvUnitCd + " - " + this.locationUnitName + " (" + statusIdIndicator + ")" : this.juvUnitCd + " - " + this.locationUnitName;
	return statusLabel;
    }

    public int getOfficerProfileFlag()
    {
	return officerProfileFlag;
    }

    public void setOfficerProfileFlag(int officerProfileFlag)
    {
	this.officerProfileFlag = officerProfileFlag;
    }

    public int getInterviewCalFlag()
    {
	return interviewCalFlag;
    }

    public void setInterviewCalFlag(int interviewCalFlag)
    {
	this.interviewCalFlag = interviewCalFlag;
    }

}
