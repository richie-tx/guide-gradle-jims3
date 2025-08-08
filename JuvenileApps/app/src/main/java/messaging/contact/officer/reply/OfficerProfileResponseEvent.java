/*
 * Created on August 04, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.contact.officer.reply;

import java.util.Collection;

import messaging.contact.domintf.IName;
import mojo.km.messaging.ResponseEvent;
import mojo.km.security.IUserInfo;

import ui.common.PhoneNumber;
import org.apache.commons.lang.StringUtils;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
/**
 * @updated ugopinath Dec 9, 2005
 */

public class OfficerProfileResponseEvent extends ResponseEvent implements IUserInfo,Comparable
{
    private String additionalZipCode;

    private String addressId;

    private String agencyId;

    private String agencyName;

    private String apartmentNum;

    private String assignedArea;

    private String badgeNum;

    private String cellPhone;

    private String city;

    private String deletableStatus;

    private String departmentId;

    private String departmentName;

    private String divisionName;

    private String email;

    private String extn;

    private String fax;

    private String faxLocation;

    private String firstName;

    private IName officerName;

    private String homePhone;

    private String JIMS2LogonId;

 //   private String JIMS2Password;

    private String JIMSLogonId;

    private String juvLocation;

    private String juvLocationId;

    private String juvUnit;

    private String juvUnitId;

    private String lastName;

    private String managerFirstName;

    private String managerId;

    private String managerLastName;

    private String managerMiddleName;

    private String middleName;

    private String officerId;

    private String officerProfileId;

    private String officerType;

    private String officerTypeId;

    private String officerSubType;

    private String officerSubTypeId;

    private String pager;

  //  private String password;

    private String otherIdNum;

    private String radioNumber;

    private String rank;

    private String rankId;

    private String ssn;

    private String state;

    private String stateId;

    private String status;

    private String statusId;

    private String streetName;

    private String streetNum;

    private String streetType;

    private String streetTypeId;

    private String updatableStatus;
    
    private String limitedUpdatableStatus;

    private String userId;

    private String userTypeId;

    private String workPhone;

    private Collection workSchedules;

    private String workShift;

    private String zipCode;
    
    private String caseLoadManagerEmail;
   

    private String caseLoadManagerWorkPhone;
    private String caseLoadManagerWorkPhoneExtn;
    private String survey;
    private String supervisor;
    private String accountType;
  
    

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
    public String getAddressId()
    {
        return addressId;
    }

    /**
     * @return
     */
    public String getAgencyId()
    {
        return agencyId;
    }

    /**
     * @return
     */
    public String getAgencyName()
    {
        return agencyName;
    }

    /**
     * @return
     */
    public String getApartmentNum()
    {
        return apartmentNum;
    }

    /**
     * @return
     */
    public String getAssignedArea()
    {
        return assignedArea;
    }

    /**
     * @return
     */
    public String getBadgeNum()
    {
        return badgeNum;
    }

    /**
     * @return
     */
    public String getCellPhone()
    {
        return cellPhone;
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
    public String getDeletableStatus()
    {
        return deletableStatus;
    }

    /**
     * @return
     */
    public String getDepartmentId()
    {
        return departmentId;
    }

    /**
     * @return
     */
    public String getDepartmentName()
    {
        return departmentName;
    }

    /**
     * @return
     */
    public String getDivisionName()
    {
        return divisionName;
    }

    /**
     * @return
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @return
     */
    public String getExtn()
    {
        return extn;
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
    public String getFaxLocation()
    {
        return faxLocation;
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
    public String getHomePhone()
    {
        return homePhone;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#getJIMS2LogonId()
     */
    public String getJIMS2LogonId()
    {
        return JIMS2LogonId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#getJIMS2Password()
     */
 /*   public String getJIMS2Password()
    {
        return JIMS2Password;
    }
*/
    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#getJIMSLogonId()
     */
    public String getJIMSLogonId()
    {
        return JIMSLogonId;
    }

    /**
     * @return
     */
    public String getJuvLocation()
    {
        return juvLocation;
    }

    /**
     * @return
     */
    public String getJuvLocationId()
    {
        return juvLocationId;
    }

    /**
     * @return
     */
    public String getJuvUnit()
    {
        return juvUnit;
    }

    /**
     * @return
     */
    public String getJuvUnitId()
    {
        return juvUnitId;
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
    public String getManagerFirstName()
    {
        return managerFirstName;
    }

    /**
     * @return
     */
    public String getManagerId()
    {
        return managerId;
    }

    /**
     * @return
     */
    public String getManagerLastName()
    {
        return managerLastName;
    }

    /**
     * @return
     */
    public String getManagerMiddleName()
    {
        return managerMiddleName;
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
    public String getOfficerId()
    {
        return officerId;
    }

    /**
     * @return
     */
    public String getOfficerProfileId()
    {
        return officerProfileId;
    }

    /**
     * @return
     */
    public String getOfficerType()
    {
        return officerType;
    }

    /**
     * @return
     */
    public String getOfficerTypeId()
    {
        return officerTypeId;
    }

    /**
     * @return
     */
    public String getPager()
    {
        return pager;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#getPassword()
     */
   /* public String getPassword()
    {
        return password;
    }
*/
    /**
     * @return
     */
    public String getOtherIdNum()
    {
        return otherIdNum;
    }

    /**
     * @return
     */
    public String getRadioNumber()
    {
        return radioNumber;
    }

    /**
     * @return
     */
    public String getRank()
    {
        return rank;
    }

    /**
     * @return
     */
    public String getRankId()
    {
        return rankId;
    }

    /**
     * @return
     */
    public String getSsn()
    {
        return ssn;
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
    public String getUpdatableStatus()
    {
        return updatableStatus;
    }

    /**
     * @return
     */
    public String getUserId()
    {
        return userId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#getUserOID()
     */
    public String getUserOID()
    {
        return JIMS2LogonId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#getUserTypeId()
     */
    public String getUserTypeId()
    {
        return userTypeId;
    }

    /**
     * @return
     */
    public String getWorkPhone()
    {
        return workPhone;
    }

    /**
     * @return
     */
    public Collection getWorkSchedules()
    {
        return workSchedules;
    }

    /**
     * @return
     */
    public String getWorkShift()
    {
        return workShift;
    }

    /**
     * @return
     */
    public String getZipCode()
    {
        return zipCode;
    }
    
    public String getSupervisor()
    {
	return supervisor;
    }
    
    public void setSupervisor(String string)
    {
	supervisor = string;
    }
    
    public String getSurvey()
    {
	return survey;
    }
    
    public void setSurvey(String string)
    {
	survey = string;
    }
    

    /**
     * @param additionalZipCode
     */
    public void setAdditionalZipCode(String additionalZipCode)
    {
        this.additionalZipCode = additionalZipCode;
    }

    /**
     * @param addressId
     */
    public void setAddressId(String addressId)
    {
        this.addressId = addressId;
    }

    /**
     * @param agencyId
     */
    public void setAgencyId(String agencyId)
    {
        this.agencyId = agencyId;
    }

    /**
     * @param agencyName
     */
    public void setAgencyName(String agencyName)
    {
        this.agencyName = agencyName;
    }

    /**
     * @param apartmentNum
     */
    public void setApartmentNum(String apartmentNum)
    {
        this.apartmentNum = apartmentNum;
    }

    /**
     * @param assignedArea
     */
    public void setAssignedArea(String assignedArea)
    {
        this.assignedArea = assignedArea;
    }

    /**
     * @param badgeNum
     */
    public void setBadgeNum(String badgeNum)
    {
        this.badgeNum = badgeNum;
    }

    /**
     * @param cellPhone
     */
    public void setCellPhone(String cellPhone)
    {
        this.cellPhone = cellPhone;
    }

    /**
     * @param city
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * @param deletableStatus
     */
    public void setDeletableStatus(String deletableStatus)
    {
        this.deletableStatus = deletableStatus;
    }

    /**
     * @param departmentId
     */
    public void setDepartmentId(String departmentId)
    {
        this.departmentId = departmentId;
    }

    /**
     * @param departmentName
     */
    public void setDepartmentName(String departmentName)
    {
        this.departmentName = departmentName;
    }

    /**
     * @param divisionName
     */
    public void setDivisionName(String divisionName)
    {
        this.divisionName = divisionName;
    }

    /**
     * @param email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * @param extn
     */
    public void setExtn(String extn)
    {
        this.extn = extn;
    }

    /**
     * @param fax
     */
    public void setFax(String fax)
    {
        this.fax = fax;
    }

    /**
     * @param faxLocation
     */
    public void setFaxLocation(String faxLocation)
    {
        this.faxLocation = faxLocation;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @param homePhone
     */
    public void setHomePhone(String homePhone)
    {
        this.homePhone = homePhone;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#setJIMS2LogonId(java.lang.String)
     */
    public void setJIMS2LogonId(String JIMS2LogonId)
    {
        this.JIMS2LogonId = JIMS2LogonId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#setJIMS2Password(java.lang.String)
     */
  /*  public void setJIMS2Password(String jims2Password)
    {
        this.JIMS2Password = jims2Password;
    }
*/
    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#setJIMSLogonId(java.lang.String)
     */
    public void setJIMSLogonId(String JIMSLogonId)
    {
        this.JIMSLogonId = JIMSLogonId;
    }

    /**
     * @param juvLocation
     */
    public void setJuvLocation(String juvLocation)
    {
        this.juvLocation = juvLocation;
    }

    /**
     * @param juvLocationId
     */
    public void setJuvLocationId(String juvLocationId)
    {
        this.juvLocationId = juvLocationId;
    }

    /**
     * @param juvUnit
     */
    public void setJuvUnit(String juvUnit)
    {
        this.juvUnit = juvUnit;
    }

    /**
     * @param juvUnitId
     */
    public void setJuvUnitId(String juvUnitId)
    {
        this.juvUnitId = juvUnitId;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @param managerFirstName
     */
    public void setManagerFirstName(String managerFirstName)
    {
        this.managerFirstName = managerFirstName;
    }

    /**
     * @param managerId
     */
    public void setManagerId(String managerId)
    {
        this.managerId = managerId;
    }

    /**
     * @param managerLastName
     */
    public void setManagerLastName(String managerLastName)
    {
        this.managerLastName = managerLastName;
    }

    /**
     * @param managerMiddleName
     */
    public void setManagerMiddleName(String managerMiddleName)
    {
        this.managerMiddleName = managerMiddleName;
    }

    /**
     * @param middleName
     */
    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    /**
     * @param officerId
     */
    public void setOfficerId(String officerId)
    {
        this.officerId = officerId;
    }

    /**
     * @param officerProfileId
     */
    public void setOfficerProfileId(String officerProfileId)
    {
        this.officerProfileId = officerProfileId;
    }

    /**
     * @param officerType
     */
    public void setOfficerType(String officerType)
    {
        this.officerType = officerType;
    }

    /**
     * @param officerTypeId
     */
    public void setOfficerTypeId(String officerTypeId)
    {
        this.officerTypeId = officerTypeId;
    }

    /**
     * @param pager
     */
    public void setPager(String pager)
    {
        this.pager = pager;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#setPassword(java.lang.String)
     */
   /* public void setPassword(String password)
    {
        this.password = password;
    }*/

    /**
     * @param otherIdNum
     */
    public void setOtherIdNum(String otherIdNum)
    {
        this.otherIdNum = otherIdNum;
    }

    /**
     * @param radioNumber
     */
    public void setRadioNumber(String radioNumber)
    {
        this.radioNumber = radioNumber;
    }

    /**
     * @param rank
     */
    public void setRank(String rank)
    {
        this.rank = rank;
    }

    /**
     * @param rankId
     */
    public void setRankId(String rankId)
    {
        this.rankId = rankId;
    }

    /**
     * @param ssn
     */
    public void setSsn(String ssn)
    {
        this.ssn = ssn;
    }

    /**
     * @param state
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * @param stateId
     */
    public void setStateId(String stateId)
    {
        this.stateId = stateId;
    }

    /**
     * @param status
     */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * @param statusId
     */
    public void setStatusId(String statusId)
    {
        this.statusId = statusId;
    }

    /**
     * @param streetName
     */
    public void setStreetName(String streetName)
    {
        this.streetName = streetName;
    }

    /**
     * @param streetNum
     */
    public void setStreetNum(String streetNum)
    {
        this.streetNum = streetNum;
    }

    /**
     * @param streetType
     */
    public void setStreetType(String streetType)
    {
        this.streetType = streetType;
    }

    /**
     * @param streetTypeId
     */
    public void setStreetTypeId(String streetTypeId)
    {
        this.streetTypeId = streetTypeId;
    }

    /**
     * @param updatableStatus
     */
    public void setUpdatableStatus(String updatableStatus)
    {
        this.updatableStatus = updatableStatus;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.security.IUserInfo#setUserTypeId(java.lang.String)
     */
    public void setUserTypeId(String userTypeId)
    {
        this.userTypeId = userTypeId;
    }

    /**
     * @param workPhone
     */
    public void setWorkPhone(String workPhone)
    {
        this.workPhone = workPhone;
    }

    /**
     * @param workSchedules
     */
    public void setWorkSchedules(Collection workSchedules)
    {
        this.workSchedules = workSchedules;
    }

    /**
     * @param workShift
     */
    public void setWorkShift(String workShift)
    {
        this.workShift = workShift;
    }

    /**
     * @param zipCode
     */
    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    /**
     * @return
     */
    public String getOfficerSubType()
    {
        return officerSubType;
    }

    /**
     * @return
     */
    public String getOfficerSubTypeId()
    {
        return officerSubTypeId;
    }

    /**
     * @param string
     */
    public void setOfficerSubType(String string)
    {
        officerSubType = string;
    }

    /**
     * @param string
     */
    public void setOfficerSubTypeId(String string)
    {
        officerSubTypeId = string;
    }

    /**
     * @return Returns the officerName.
     */
    public IName getOfficerName()
    {
        return officerName;
    }

    /**
     * @param officerName
     *            The officerName to set.
     */
    public void setOfficerName(IName officerName)
    {
        this.officerName = officerName;
    }
    
	/**
	 * @return String formatted Name
	 */
	public String getFormattedName()
	{
		String name = null;
		StringBuffer full = new StringBuffer();
		if(StringUtils.isNotEmpty(lastName))
			full.append(lastName);
		if(StringUtils.isNotEmpty(firstName))
			full.append(", "+firstName);
		if(StringUtils.isNotEmpty(middleName))
			full.append(" " + middleName);
		name = full.toString();
		return name;
	}
	
	/**
	 * @return String formatted Name
	 */
	public String getFormattedNameWithUserId()
	{
		String name = getFormattedName();
		if(StringUtils.isNotEmpty(userId))
			name += (" - " + userId);
		return name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o==null)
			return 1;
		if (!(o instanceof OfficerProfileResponseEvent))
			return 1;
		OfficerProfileResponseEvent l = (OfficerProfileResponseEvent)o;		
		if(StringUtils.equals(this.lastName,l.getLastName())){
			if(StringUtils.equals(this.firstName,l.getFirstName()) && StringUtils.isNotEmpty(this.middleName)){
				return this.middleName.compareToIgnoreCase(l.getMiddleName());
			}
			return this.firstName.compareToIgnoreCase(l.getFirstName());
		}
		
		return this.lastName.compareToIgnoreCase(l.getLastName());	
		
	}

@Override
public void setUserOID(String smUserId)
{
    // TODO Auto-generated method stub
    
}

@Override
public String getOrgCode()
{
    // TODO Auto-generated method stub
    return null;
}

@Override
public void setOrgCode(String orgCode)
{
    // TODO Auto-generated method stub
    
}

public String getLimitedUpdatableStatus()
{
    return limitedUpdatableStatus;
}

public void setLimitedUpdatableStatus(String limitedUpdatableStatus)
{
    this.limitedUpdatableStatus = limitedUpdatableStatus;
}

public String getCaseLoadManagerEmail()
{
    return caseLoadManagerEmail;
}

public void setCaseLoadManagerEmail(String caseLoadManagerEmail)
{
    this.caseLoadManagerEmail = caseLoadManagerEmail;
}

public String getCaseLoadManagerWorkPhone()
{
    return caseLoadManagerWorkPhone;
}

public void setCaseLoadManagerWorkPhone(String caseLoadManagerWorkPhone)
{
    this.caseLoadManagerWorkPhone = caseLoadManagerWorkPhone;
}

public String getCaseLoadManagerWorkPhoneExtn()
{
    return caseLoadManagerWorkPhoneExtn;
}

public void setCaseLoadManagerWorkPhoneExtn(String caseLoadManagerWorkPhoneExtn)
{
    this.caseLoadManagerWorkPhoneExtn = caseLoadManagerWorkPhoneExtn;
}
public String getAccountType()
{
    return accountType;
}

public void setAccountType(String accountType)
{
    this.accountType = accountType;
}


}