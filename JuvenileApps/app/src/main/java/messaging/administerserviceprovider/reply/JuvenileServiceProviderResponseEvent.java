package messaging.administerserviceprovider.reply;

import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class JuvenileServiceProviderResponseEvent extends ResponseEvent implements Comparable
{
    private String adminUserProfileId;

    private String billingAddressId;

    private String contactUserProfileId;

    private String extnNum;

    private String fax;

    private String ftpSite;

    private String ifasNumber;

    private Date inactiveDate;

    private String inactiveReason;

    private boolean inHouse;

    private String mailingAddressId;

    private String originatingDepartment;

    private String phone;

    private Collection providerPrograms;

    private Date reactivateDate;

    private String serviceProviderId;

    private String serviceProviderName;

    private String serviceType;

    private String serviceTypeId;

    private Collection spProfiles;

    private Date startDate;

    private String status;

    private String statusId;

    private String webSite;

    private String workflowID;
    
    private String locationRegion;

    private String locationRegionId;
    private String programName;
    private String serviceId;
    private String serviceName;
    
    private String maxYouth; // added for 177341
    private String email;
    private boolean isEmailCheck;


    public int compareTo(Object obj)
    {
        JuvenileServiceProviderResponseEvent evt = (JuvenileServiceProviderResponseEvent) obj;

        String sp1 = serviceProviderName.trim();
        String sp2 = evt.getServiceProviderName().trim();
        return sp1.compareToIgnoreCase(sp2);
    }

    /**
     * @return
     */
    public String getAdminUserProfileId()
    {
        return adminUserProfileId;
    }

    /**
     * @return
     */
    public String getBillingAddressId()
    {
        return billingAddressId;
    }

    /**
     * @return
     */
    public String getContactUserProfileId()
    {
        return contactUserProfileId;
    }

    /**
     * @return
     */
    public String getExtnNum()
    {
        return extnNum;
    }

    /**
     * @return
     */
    public String getFax()
    {
        return fax;
    }

    /**
     * @return
     */
    public String getFtpSite()
    {
        return ftpSite;
    }

    /**
     * @return
     */
    public String getIfasNumber()
    {
        return ifasNumber;
    }

    /**
     * @return
     */
    public Date getInactiveDate()
    {
        return inactiveDate;
    }

    /**
     * @return
     */
    public String getInactiveReason()
    {
        return inactiveReason;
    }

    /**
     * @return
     */
    public boolean getIsInHouse()
    {
        return inHouse;
    }

    /**
     * @return
     */
    public String getMailingAddressId()
    {
        return mailingAddressId;
    }

    /**
     * @return
     */
    public String getOriginatingDepartment()
    {
        return originatingDepartment;
    }

    /**
     * @return
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * @return
     */
    public Collection getProviderPrograms()
    {
        return providerPrograms;
    }

    /**
     * @return
     */
    public Date getReactivateDate()
    {
        return reactivateDate;
    }

    /**
     * @return
     */
    public String getServiceProviderId()
    {
        return serviceProviderId;
    }

    /**
     * @return
     */
    public String getServiceProviderName()
    {
        return serviceProviderName;
    }

    /**
     * @return Returns the serviceType.
     */
    public String getServiceType()
    {
        return serviceType;
    }

    /**
     * @return Returns the serviceTypeId.
     */
    public String getServiceTypeId()
    {
        return serviceTypeId;
    }

    /**
     * @return
     */
    public Collection getSpProfiles()
    {
        return spProfiles;
    }

    /**
     * @return
     */
    public Date getStartDate()
    {
        return startDate;
    }

    /**
     * @return Returns the status.
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * @return
     */
    public String getStatusId()
    {
        return statusId;
    }

    /**
     * @return
     */
    public String getWebSite()
    {
        return webSite;
    }

    /**
     * @return Returns the workflowID.
     */
    public String getWorkflowID()
    {
        return workflowID;
    }

    /**
     * @return
     */
    public boolean isInHouse()
    {
        return inHouse;
    }

    /**
     * @param string
     */
    public void setAdminUserProfileId(String string)
    {
        adminUserProfileId = string;
    }

    /**
     * @param string
     */
    public void setBillingAddressId(String string)
    {
        billingAddressId = string;
    }

    /**
     * @param string
     */
    public void setContactUserProfileId(String string)
    {
        contactUserProfileId = string;
    }

    /**
     * @param string
     */
    public void setExtnNum(String string)
    {
        extnNum = string;
    }

    /**
     * @param string
     */
    public void setFax(String string)
    {
        fax = string;
    }

    /**
     * @param string
     */
    public void setFtpSite(String string)
    {
        ftpSite = string;
    }

    /**
     * @param string
     */
    public void setIfasNumber(String string)
    {
        ifasNumber = string;
    }

    /**
     * @param date
     */
    public void setInactiveDate(Date date)
    {
        inactiveDate = date;
    }

    /**
     * @param string
     */
    public void setInactiveReason(String string)
    {
        inactiveReason = string;
    }

    /**
     * @param b
     */
    public void setInHouse(boolean b)
    {
        inHouse = b;
    }

    /**
     * @param string
     */
    public void setMailingAddressId(String string)
    {
        mailingAddressId = string;
    }

    /**
     * @param string
     */
    public void setOriginatingDepartment(String string)
    {
        originatingDepartment = string;
    }

    /**
     * @param string
     */
    public void setPhone(String string)
    {
        phone = string;
    }

    /**
     * @param collection
     */
    public void setProviderPrograms(Collection collection)
    {
        providerPrograms = collection;
    }

    /**
     * @param date
     */
    public void setReactivateDate(Date date)
    {
        reactivateDate = date;
    }

    /**
     * @param string
     */
    public void setServiceProviderId(String setServiceProviderId)
    {
        this.serviceProviderId = setServiceProviderId;
    }

    /**
     * @param string
     */
    public void setServiceProviderName(String string)
    {
        serviceProviderName = string;
    }

    /**
     * @param serviceType
     *            The serviceType to set.
     */
    public void setServiceType(String serviceType)
    {
        this.serviceType = serviceType;
    }

    /**
     * @param serviceTypeId
     *            The serviceTypeId to set.
     */
    public void setServiceTypeId(String serviceTypeId)
    {
        this.serviceTypeId = serviceTypeId;
    }

    /**
     * @param collection
     */
    public void setSpProfiles(Collection aCollection)
    {
        spProfiles = aCollection;
    }

    /**
     * @param date
     */
    public void setStartDate(Date date)
    {
        startDate = date;
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
     * @param string
     */
    public void setStatusId(String string)
    {
        statusId = string;
    }

	/**
	 * @return Returns the locationRegion.
	 */
	public String getLocationRegion() {
		return locationRegion;
	}
	/**
	 * @param locationRegion The locationRegion to set.
	 */
	public void setLocationRegion(String locationRegion) {
		this.locationRegion = locationRegion;
	}
	/**
	 * @return Returns the locationRegionId.
	 */
	public String getLocationRegionId() {
		return locationRegionId;
	}
	/**
	 * @param locationRegionId The locationRegionId to set.
	 */
	public void setLocationRegionId(String locationRegionId) {
		this.locationRegionId = locationRegionId;
	}
    /**
     * @param string
     */
    public void setWebSite(String string)
    {
        webSite = string;
    }

    /**
     * @param workflowID
     *            The workflowID to set.
     */
    public void setWorkflowID(String workflowID)
    {
        this.workflowID = workflowID;
    }
    
    public String getProgramName()
    {
        return programName;
    }

    public void setProgramName(String programName)
    {
        this.programName = programName;
    }
    

    public String getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(String serviceId)
    {
        this.serviceId = serviceId;
    }
    
    public String getServiceName()
    {
        return serviceName;
    }
    

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getMaxYouth()
    {
	return maxYouth;
    }

    public void setMaxYouth(String maxYouth)
    {
	this.maxYouth = maxYouth;
    }
    
    public String getEmail()
    {
	return this.email;
    }

    public void setEmail(String email)
    {
	this.email = email;
    }

    public boolean isEmailCheck()
    {
        return isEmailCheck;
    }

    public void setEmailCheck(boolean isEmailCheck)
    {
        this.isEmailCheck = isEmailCheck;
    }
    
}