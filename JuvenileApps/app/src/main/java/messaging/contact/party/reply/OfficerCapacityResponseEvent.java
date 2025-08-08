/*
 * Created on Oct 13, 2004
 */
package messaging.contact.party.reply;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author Dhanashree
 * 
 * This event will carry the Officer data from the PD to the UI.
 */
public class OfficerCapacityResponseEvent extends ResponseEvent
{
	private String agencyId;
	private String agencyName;
	private String originatingAgencyId;
	private String assignedArea;
	private String badgeNum;
	private String cellPhoneNum;
	private String departmentId;
	private String departmentName;
	private String email;
	private String firstName;
	private String middleName;
	private String lastName;
	private String logonId;
	private String officerId;
	private String officerIdType;
	private String officerIdTypeId;
	private String pagerNum;
	private String partyId;
	private String payrollNum;
	private String phoneNum;
	private String rank;
	private String rankId;
	private String[] selectedWorkOffDays;
	private Collection workOffDays = new ArrayList();
	private String workShift;

	public OfficerCapacityResponseEvent()
	{

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
	public String getCellPhoneNum()
	{
		return cellPhoneNum;
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
	public String getEmail()
	{
		return email;
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
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * @return
	 */
	public String getOfficerIdType()
	{
		return officerIdType;
	}

	/**
	 * @return
	 */
	public String getOfficerIdTypeId()
	{
		return officerIdTypeId;
	}

	/**
	 * @return
	 */
	public String getPagerNum()
	{
		return pagerNum;
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
	public String getPayrollNum()
	{
		return payrollNum;
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
	public String[] getSelectedWorkOffDays()
	{
		return selectedWorkOffDays;
	}

	/**
	 * @return
	 */
	public Collection getWorkOffDays()
	{
		workOffDays.clear();
		workOffDays.add("Monday");
		workOffDays.add("Tuesday");
		workOffDays.add("Wednesday");
		workOffDays.add("Thursday");
		workOffDays.add("Friday");
		workOffDays.add("Saturday");
		workOffDays.add("Sunday");
		return workOffDays;
	}

	/**
	 * @return
	 */
	public String getWorkShift()
	{
		return workShift;
	}

	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}

	/**
	 * @param string
	 */
	public void setAgencyName(String string)
	{
		agencyName = string;
	}

	/**
	 * @param string
	 */
	public void setAssignedArea(String string)
	{
		assignedArea = string;
	}

	/**
	 * @param string
	 */
	public void setBadgeNum(String string)
	{
		badgeNum = string;
	}

	/**
	 * @param string
	 */
	public void setCellPhoneNum(String string)
	{
		cellPhoneNum = string;
	}

	/**
	 * @param string
	 */
	public void setDepartmentId(String string)
	{
		departmentId = string;
	}

	/**
	 * @param string
	 */
	public void setDepartmentName(String string)
	{
		departmentName = string;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string)
	{
		email = string;
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
	public void setLogonId(String string)
	{
		logonId = string;
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
	public void setOfficerIdTypeId(String string)
	{
		officerIdTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setPagerNum(String string)
	{
		pagerNum = string;
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
	public void setPayrollNum(String string)
	{
		payrollNum = string;
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
	public void setRank(String string)
	{
		rank = string;
	}

	/**
	 * @param string
	 */
	public void setRankId(String string)
	{
		rankId = string;
	}

	/**
	 * @param strings
	 */
	public void setSelectedWorkOffDays(String[] strings)
	{
		selectedWorkOffDays = strings;
	}

	/**
	 * @param collection
	 */
	public void setWorkOffDays(Collection collection)
	{
		workOffDays = collection;
	}

	/**
	 * @param string
	 */
	public void setWorkShift(String string)
	{
		workShift = string;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string)
	{
		middleName = string;
	}

	/**
	 * For PD, the officerID denotes the OID. However, 
	 * For the UI, the officer ID is either the badge number or the payroll number.
	 * If both are present, then the badge number takes precedence.
	 * Hence, the getOfficerId method, when used by the UI should return the appropriate number
	 * and not the OID. 
	 * 
	 * If the UI needs the OID, it needs to call the getOfficerOID method.
	 */
	public String getOfficerId()
	{
		if (badgeNum != null)
		{
			return badgeNum;
		}
		return payrollNum;
	}

	/**
	 * @return
	 */
	public String getOfficerOID()
	{
		return officerId;
	}

	/**
	 * @param string
	 */
	public void setOfficerId(String string)
	{
		officerId = string;
	}

	/**
	 * @return
	 */
	public String getOriginatingAgencyId()
	{
		return originatingAgencyId;
	}

	/**
	 * @param string
	 */
	public void setOriginatingAgencyId(String string)
	{
		originatingAgencyId = string;
	}

}
