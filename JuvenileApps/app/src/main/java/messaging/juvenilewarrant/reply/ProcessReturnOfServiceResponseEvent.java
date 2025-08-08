/*
 * Created on Mar 11, 2005
 *  
 */
package messaging.juvenilewarrant.reply;

import java.util.Date;

import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.DateUtil;

import messaging.contact.domintf.INameKey;

public class ProcessReturnOfServiceResponseEvent extends ResponseEvent implements INameKey
{
    private String apartmentNum;

    private String city;

    private IName juvenileName;

    private String juvFirstName;

    private String juvLastName;

    private String juvMiddleName;

    private String nameKey;

    private IPhoneNumber officerCellPhone;

    private String officerDepartment;

    private String officerDepartmentId;

    private String officerEmail;

    private String officerFirstName;

    private String officerId;

    private String officerIdType;

    private String officerIdTypeId;

    private String officerLastName;

    private String officerMiddleName;

    private IName officerName;

    private String officerNum;

    private String officerOtherIdNum;

    private IPhoneNumber officerPager;

    private IPhoneNumber officerWorkPhone;

    private String petitionNum;

    private Date serviceDate;

    private String serviceStatus;

    private String serviceStatusId;

    private Date serviceTimeStamp;

    private String stateId;

    private String streetAddress2;

    private String streetName;

    private String streetNum;

    private String streetTypeId;

    private String warrantNum;

    private String warrantServiceId;
    
    private Date warrantActivationDate;
    
    private String warrantOriginatorCourtId;

	/**
	 * @return Returns the warrantOriginatorCourtId.
	 */
	public String getWarrantOriginatorCourtId() {
		
		return warrantOriginatorCourtId;
	}
	/**
	 * @param warrantOriginatorCourtId The warrantOriginatorCourtId to set.
	 */
	public void setWarrantOriginatorCourtId(String warrantOriginatorCourtId) {
		
		this.warrantOriginatorCourtId = warrantOriginatorCourtId;
	}
    private String warrantType;

    private String warrantTypeId;

    private String zipCode;

    private String zipCodeExtended;

    /**
     *  
     */
    public ProcessReturnOfServiceResponseEvent()
    {
        super();
    }

    /**
     * @return Returns the apartmentNum.
     */
    public String getApartmentNum()
    {
        return apartmentNum;
    }

    /**
     * @return Returns the city.
     */
    public String getCity()
    {
        return city;
    }

    /**
     * @return Returns the juvenileName.
     */
    public IName getJuvenileName()
    {
        return juvenileName;
    }

    /**
     * @return Returns the juvFirstName.
     */
    public String getJuvFirstName()
    {
        return juvFirstName;
    }

    /**
     * @return Returns the juvLastName.
     */
    public String getJuvLastName()
    {
        return juvLastName;
    }

    /**
     * @return Returns the juvMiddleName.
     */
    public String getJuvMiddleName()
    {
        return juvMiddleName;
    }

    /**
     * @return Returns the officerCellPhone.
     */
    public IPhoneNumber getOfficerCellPhone()
    {
        return officerCellPhone;
    }

    /**
     * @return Returns the officerDepartment.
     */
    public String getOfficerDepartment()
    {
        return officerDepartment;
    }

    /**
     * @return Returns the officerDepartmentId.
     */
    public String getOfficerDepartmentId()
    {
        return officerDepartmentId;
    }

    /**
     * @return Returns the officerEmail.
     */
    public String getOfficerEmail()
    {
        return officerEmail;
    }

    /**
     * @return Returns the officerFirstName.
     */
    public String getOfficerFirstName()
    {
        return officerFirstName;
    }

    /**
     * @return Returns the officerId.
     */
    public String getOfficerId()
    {
        return officerId;
    }

    /**
     * @return Returns the officerIdType.
     */
    public String getOfficerIdType()
    {
        return officerIdType;
    }

    /**
     * @return Returns the officerIdTypeId.
     */
    public String getOfficerIdTypeId()
    {
        return officerIdTypeId;
    }

    /**
     * @return Returns the officerLastName.
     */
    public String getOfficerLastName()
    {
        return officerLastName;
    }

    /**
     * @return Returns the officerMiddleName.
     */
    public String getOfficerMiddleName()
    {
        return officerMiddleName;
    }

    /**
     * @return Returns the executorName.
     */
    public IName getOfficerName()
    {
        return officerName;
    }

    /**
     * @return Returns the officerNum.
     */
    public String getOfficerNum()
    {
        return officerNum;
    }

    /**
     * @return Returns the officerOtherIdNum.
     */
    public String getOfficerOtherIdNum()
    {
        return officerOtherIdNum;
    }

    /**
     * @return Returns the officerPager.
     */
    public IPhoneNumber getOfficerPager()
    {
        return officerPager;
    }

    /**
     * @return Returns the officerWorkPhone.
     */
    public IPhoneNumber getOfficerWorkPhone()
    {
        return officerWorkPhone;
    }

    /**
     * @return Returns the petitionNum.
     */
    public String getPetitionNum()
    {
        return petitionNum;
    }

    public String getServiceDate()
    {
        String servDate = DateUtil.dateToString(this.getServiceTimeStamp(), "MM/dd/yyyy");
        return servDate;
    }

    /**
     * @return
     */
    public String getServiceStatus()
    {
        return serviceStatus;
    }

    /**
     * @return Returns the serviceStatusId.
     */
    public String getServiceStatusId()
    {
        return serviceStatusId;
    }

    public String getServiceTime()
    {
        String servTime = null;
        if (this.getServiceTimeStamp() != null)
        {
            servTime = DateUtil.dateToString(this.getServiceTimeStamp(), "HH:mm");
        }
        return servTime;
    }

    /**
     * @return
     */
    public Date getServiceTimeStamp()
    {
        return serviceTimeStamp;
    }

    /**
     * @return Returns the stateId.
     */
    public String getStateId()
    {
        return stateId;
    }

    /**
     * @return Returns the streetAddress2.
     */
    public String getStreetAddress2()
    {
        return streetAddress2;
    }

    /**
     * @return Returns the streetName.
     */
    public String getStreetName()
    {
        return streetName;
    }

    /**
     * @return Returns the streetNum.
     */
    public String getStreetNum()
    {
        return streetNum;
    }

    /**
     * @return Returns the streetTypeId.
     */
    public String getStreetTypeId()
    {
        return streetTypeId;
    }

    /**
     * @return
     */
    public String getWarrantNum()
    {
        return warrantNum;
    }

    /**
     * @return Returns the warrantServiceId.
     */
    public String getWarrantServiceId()
    {
        return warrantServiceId;
    }

    /**
     * @return Returns the warrantType.
     */
    public String getWarrantType()
    {
        return warrantType;
    }

    /**
     * @return Returns the warrantTypeId.
     */
    public String getWarrantTypeId()
    {
        return warrantTypeId;
    }

    /**
     * @return Returns the zipCode.
     */
    public String getZipCode()
    {
        return zipCode;
    }

    /**
     * @return Returns the zipCodeExtended.
     */
    public String getZipCodeExtended()
    {
        return zipCodeExtended;
    }

    /**
     * @param apartmentNum
     *            The apartmentNum to set.
     */
    public void setApartmentNum(String apartmentNum)
    {
        this.apartmentNum = apartmentNum;
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
     * @param juvenileName
     *            The juvenileName to set.
     */
    public void setJuvenileName(IName juvenileName)
    {
        this.juvenileName = juvenileName;
        this.setJuvFirstName(juvenileName.getFirstName());
        this.setJuvMiddleName(juvenileName.getMiddleName());
        this.setJuvLastName(juvenileName.getLastName());
    }

    /**
     * @param juvFirstName
     *            The juvFirstName to set.
     */
    public void setJuvFirstName(String juvFirstName)
    {
        this.juvFirstName = juvFirstName;
    }

    /**
     * @param juvLastName
     *            The juvLastName to set.
     */
    public void setJuvLastName(String juvLastName)
    {
        this.juvLastName = juvLastName;
    }

    /**
     * @param juvMiddleName
     *            The juvMiddleName to set.
     */
    public void setJuvMiddleName(String juvMiddleName)
    {
        this.juvMiddleName = juvMiddleName;
    }

    /**
     * @param officerCellPhone
     *            The officerCellPhone to set.
     */
    public void setOfficerCellPhone(IPhoneNumber officerCellPhone)
    {
        this.officerCellPhone = officerCellPhone;
    }

    /**
     * @param officerDepartment
     *            The officerDepartment to set.
     */
    public void setOfficerDepartment(String officerDepartment)
    {
        this.officerDepartment = officerDepartment;
    }

    /**
     * @param officerDepartmentId
     *            The officerDepartmentId to set.
     */
    public void setOfficerDepartmentId(String officerDepartmentId)
    {
        this.officerDepartmentId = officerDepartmentId;
    }

    /**
     * @param officerEmail
     *            The officerEmail to set.
     */
    public void setOfficerEmail(String officerEmail)
    {
        this.officerEmail = officerEmail;
    }

    /**
     * @param officerFirstName
     *            The officerFirstName to set.
     */
    public void setOfficerFirstName(String officerFirstName)
    {
        this.officerFirstName = officerFirstName;
    }

    /**
     * @param officerId
     *            The officerId to set.
     */
    public void setOfficerId(String officerId)
    {
        this.officerId = officerId;
    }

    /**
     * @param officerIdType
     *            The officerIdType to set.
     */
    public void setOfficerIdType(String officerIdType)
    {
        this.officerIdType = officerIdType;
    }

    /**
     * @param officerIdTypeId
     *            The officerIdTypeId to set.
     */
    public void setOfficerIdTypeId(String officerIdTypeId)
    {
        this.officerIdTypeId = officerIdTypeId;
    }

    /**
     * @param officerLastName
     *            The officerLastName to set.
     */
    public void setOfficerLastName(String officerLastName)
    {
        this.officerLastName = officerLastName;
    }

    /**
     * @param officerMiddleName
     *            The officerMiddleName to set.
     */
    public void setOfficerMiddleName(String officerMiddleName)
    {
        this.officerMiddleName = officerMiddleName;
    }

    /**
     * @param executorName
     *            The executorName to set.
     */
    public void setOfficerName(IName officerName)
    {
        this.officerName = officerName;
        this.setOfficerFirstName(officerName.getFirstName());
        this.setOfficerMiddleName(officerName.getMiddleName());
        this.setOfficerLastName(officerName.getLastName());
    }

    /**
     * @param officerNum
     *            The officerNum to set.
     */
    public void setOfficerNum(String officerNum)
    {
        this.officerNum = officerNum;
    }

    /**
     * @param officerOtherIdNum
     *            The officerOtherIdNum to set.
     */
    public void setOfficerOtherIdNum(String officerOtherIdNum)
    {
        this.officerOtherIdNum = officerOtherIdNum;
    }

    /**
     * @param officerPager
     *            The officerPager to set.
     */
    public void setOfficerPager(IPhoneNumber officerPager)
    {
        this.officerPager = officerPager;
    }

    /**
     * @param officerWorkPhone
     *            The officerWorkPhone to set.
     */
    public void setOfficerWorkPhone(IPhoneNumber officerWorkPhone)
    {
        this.officerWorkPhone = officerWorkPhone;
    }

    /**
     * @param petitionNum
     *            The petitionNum to set.
     */
    public void setPetitionNum(String petitionNum)
    {
        this.petitionNum = petitionNum;
    }

    /**
     * @param serviceDate
     *            The serviceDate to set.
     */
    public void setServiceDate(Date serviceDate)
    {
        this.serviceDate = serviceDate;
    }

    /**
     * @param string
     */
    public void setServiceStatus(String string)
    {
        serviceStatus = string;
    }

    /**
     * @param serviceStatusId
     *            The serviceStatusId to set.
     */
    public void setServiceStatusId(String serviceStatusId)
    {
        this.serviceStatusId = serviceStatusId;
    }

    /**
     * @param date
     */
    public void setServiceTimeStamp(Date date)
    {
        serviceTimeStamp = date;
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
     * @param streetAddress2
     *            The streetAddress2 to set.
     */
    public void setStreetAddress2(String streetAddress2)
    {
        this.streetAddress2 = streetAddress2;
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
     * @param streetNum
     *            The streetNum to set.
     */
    public void setStreetNum(String streetNum)
    {
        this.streetNum = streetNum;
    }

    /**
     * @param streetTypeId
     *            The streetTypeId to set.
     */
    public void setStreetTypeId(String streetTypeId)
    {
        this.streetTypeId = streetTypeId;
    }

    /**
     * @param string
     */
    public void setWarrantNum(String string)
    {
        warrantNum = string;
    }

    /**
     * @param warrantServiceId
     *            The warrantServiceId to set.
     */
    public void setWarrantServiceId(String warrantServiceId)
    {
        this.warrantServiceId = warrantServiceId;
    }

    /**
     * @param warrantType
     *            The warrantType to set.
     */
    public void setWarrantType(String warrantType)
    {
        this.warrantType = warrantType;
    }

    /**
     * @param warrantTypeId
     *            The warrantTypeId to set.
     */
    public void setWarrantTypeId(String warrantTypeId)
    {
        this.warrantTypeId = warrantTypeId;
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
     * @param zipCodeExtended
     *            The zipCodeExtended to set.
     */
    public void setZipCodeExtended(String zipCodeExtended)
    {
        this.zipCodeExtended = zipCodeExtended;
    }

    /**
     * @return Returns the nameKey.
     */
    public String getNameKey()
    {
        if (nameKey == null)
        {
            StringBuffer key = new StringBuffer(60);
            if (this.juvLastName != null)
            {
                key.append(this.juvLastName);
            }
            if (this.juvFirstName != null)
            {
                key.append(this.juvFirstName);
            }
            if (this.juvMiddleName != null)
            {
                key.append(this.juvMiddleName);
            }
            if (this.warrantNum != null)
            {
                key.append(this.warrantNum);
            }
            this.nameKey = key.toString();
        }
        return this.nameKey;
    }

    /**
     * @param nameKey
     *            The nameKey to set.
     */
    public void setNameKey(String nameKey)
    {
        this.nameKey = nameKey;
    }
    /**
     * @return Returns the warrantActivationDate.
     */
    public Date getWarrantActivationDate()
    {
        return warrantActivationDate;
    }
    /**
     * @param warrantActivationDate The warrantActivationDate to set.
     */
    public void setWarrantActivationDate(Date warrantActivationDate)
    {
        this.warrantActivationDate = warrantActivationDate;
    }
}
