/*
 * Created on August 04, 2005
 *
 */
package pd.contact.officer;

//import java.util.ArrayList;
//import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import messaging.administerlocation.GetJuvLocationUnitDetailsEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.contact.officer.reply.OfficerJuvWarrantAssociationResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.officer.reply.WorkDayResponseEvent;
import messaging.officer.GetOfficerProfileByLogonIdEvent;
import messaging.officer.GetOfficerProfileFromUserGroupEvent;
import messaging.officer.UpdateOfficerProfileEvent;
import messaging.officer.WorkDayRequestEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.OfficerProfileControllerServiceNames;
import naming.PDAddressConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.PDOfficerProfileConstants;
import naming.PDSecurityConstants;
import pd.codetable.Code;
import pd.codetable.PDCodeHelper;
import pd.contact.agency.Department;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.security.JIMS2AccountView;
import pd.security.PDSecurityHelper;
import pd.supervision.administerserviceprovider.administerlocation.Location;
import ui.common.Name;
//import mojo.km.security.Constraint;
//import pd.security.JIMS2AccountType;
//import ui.common.SimpleCodeTableHelper;

/**
 * @author mchowdhury
 * @description helper class for Officer Profile To change the template for this
 *              generated type comment go to
 *              Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 *              Comments
 */

public class PDOfficerProfileHelper
{
    /**
     * @param workDay
     *            WorkDay
     * @return WorkDayResponseEvent
     */
    public static WorkDayResponseEvent getWorkDayResponseEvent(WorkDay workDay)
    {
	WorkDayResponseEvent event = new WorkDayResponseEvent();
	event.setDayId(workDay.getDayId());
	if ((workDay.getDayId() != null) && (!(workDay.getDayId().equals(""))))
	{
	    Code code = workDay.getDay();
	    if (code != null)
	    {
		event.setDay(code.getDescription());
	    }
	}
	event.setEndTimeId(workDay.getEndTimeId());
	if ((workDay.getEndTimeId() != null) && (!(workDay.getEndTimeId().equals(""))))
	{
	    Code code = workDay.getEndTime();
	    if (code != null)
	    {
		event.setEndTime(code.getDescription());
	    }
	}
	event.setStartTimeId(workDay.getStartTimeId());
	if ((workDay.getStartTimeId() != null) && (!(workDay.getStartTimeId().equals(""))))
	{
	    Code code = workDay.getStartTime();
	    if (code != null)
	    {
		event.setStartTime(code.getDescription());
	    }
	}
	event.setOffDay(workDay.getDayOff());
	event.setOfficerProfileId(workDay.getOfficerProfileId());
	event.setWorkScheduleId(workDay.getWorkScheduleId());
	return event;
    }

    /**
     * @param officerProfile
     * @return OfficerProfileResponseEvent
     */
    public static OfficerProfileResponseEvent getOfficerProfileResponseEvent(OfficerProfile officerProfile)
    {
	OfficerProfileResponseEvent event = getThinOfficerProfileResponseEvent(officerProfile);
	
	event.setAssignedArea(officerProfile.getAssignedArea());
	event.setDivisionName(officerProfile.getDivision());
	event.setExtn(officerProfile.getWorkPhoneExtn());
	event.setFax(officerProfile.getFaxNum());
	event.setFaxLocation(officerProfile.getFaxLocation());
	event.setHomePhone(officerProfile.getHomePhoneNum());
	event.setJuvLocationId(officerProfile.getJuvLocationId());
	if ((officerProfile.getJuvLocationId() != null) && (!(officerProfile.getJuvLocationId().equals(""))))
	{
	    Location location = officerProfile.getJuvLocation();
	    if (location != null)
	    {
		event.setJuvLocation(location.getLocationName());
	    }
	}
	event.setJuvUnitId(officerProfile.getJuvUnitId());
	if ((officerProfile.getJuvUnitId() != null) && (!(officerProfile.getJuvUnitId().equals(""))))
	{
	    GetJuvLocationUnitDetailsEvent reqEvent = new GetJuvLocationUnitDetailsEvent();
	    reqEvent.setJuvLocUnitId(officerProfile.getJuvUnitId());
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch.postEvent(reqEvent);
	    CompositeResponse response = (CompositeResponse) dispatch.getReply();
	    LocationResponseEvent respEvent = (LocationResponseEvent) MessageUtil.filterComposite(response, LocationResponseEvent.class);
	    event.setJuvUnit(respEvent.getLocationUnitName());
	    //			Code code = officerProfile.getJuvUnit();

	    //			if (code != null)
	    //			{
	    //				event.setJuvUnit(code.getDescription());
	    //			}
	}
	event.setManagerFirstName(officerProfile.getManagerFirstName());
	event.setManagerId(officerProfile.getManagerLogonId());
	event.setManagerLastName(officerProfile.getManagerLastName());
	event.setManagerMiddleName(officerProfile.getManagerMiddleName());
	
	OfficerProfile managerInfo = OfficerProfile.findByLogonId(officerProfile.getManagerLogonId());
	//null check
	if(managerInfo!=null)
	{
	event.setCaseLoadManagerEmail(managerInfo.getEmail());
	event.setCaseLoadManagerWorkPhone(managerInfo.getWorkPhoneNum());
	event.setCaseLoadManagerWorkPhoneExtn(managerInfo.getWorkPhoneExtn());
	}
	event.setOfficerTypeId(officerProfile.getOfficerTypeId());
	if ((officerProfile.getOfficerTypeId() != null) && (!(officerProfile.getOfficerTypeId().equals(""))))
	{
	    Code code = officerProfile.getOfficerType();
	    if (code != null)
	    {
		event.setOfficerType(code.getDescription());
	    }
	}
	event.setRadioNumber(officerProfile.getRadioNum());
	event.setRankId(officerProfile.getRankId());
	if ((officerProfile.getRankId() != null) && (!(officerProfile.getRankId().equals(""))))
	{
	    Code code = officerProfile.getRank();
	    if (code != null)
	    {
		event.setRank(code.getDescription());
	    }
	}
	event.setSsn(officerProfile.getSsn());
	event.setStatusId(officerProfile.getStatusId());
	if ((officerProfile.getStatusId() != null) && (!(officerProfile.getStatusId().equals(""))))
	{
	    Code code = officerProfile.getStatus();
	    if (code != null)
	    {
		event.setStatus(code.getDescription());
	    }
	}

	event.setOfficerSubTypeId(officerProfile.getOfficerSubTypeId());
	if ((officerProfile.getOfficerSubTypeId() != null) && (!(officerProfile.getOfficerSubTypeId().equals(""))))
	{
	    Code code = officerProfile.getOfficerSubType();
	    if (code != null)
	    {
		event.setOfficerSubType(code.getDescription());
	    }
	}

	event.setWorkShift(officerProfile.getWorkShift());
	event.setSupervisor(officerProfile.getSupervisor());
	event.setSurvey(officerProfile.getSurvey());
	Iterator addresses = officerProfile.getAddresses().iterator();
	while (addresses.hasNext())
	{
	    OfficerProfileAddress workAddress = (OfficerProfileAddress) addresses.next();
	    if (workAddress != null)
	    {
		event.setAdditionalZipCode(workAddress.getAdditionalZipCode());
		event.setAddressId(workAddress.getAddressId());
		event.setApartmentNum(workAddress.getAptNum());
		event.setCity(workAddress.getCity());
		event.setStateId(workAddress.getStateId());
		if ((workAddress.getStateId() != null) && (!(workAddress.getStateId().equals(""))))
		{
		    Code code = workAddress.getState();
		    if (code != null)
		    {
			event.setState(code.getDescription());
		    }
		}
		event.setStreetName(workAddress.getStreetName());
		event.setStreetNum(workAddress.getStreetNum());
		event.setStreetTypeId(workAddress.getStreetTypeId());
		if ((workAddress.getStreetTypeId() != null) && (!(workAddress.getStreetTypeId().equals(""))))
		{
		    Code code = workAddress.getStreetType();
		    if (code != null)
		    {
			event.setStreetType(code.getDescription());
		    }
		}
		event.setZipCode(workAddress.getZipCode());
		event.setAdditionalZipCode(workAddress.getAdditionalZipCode());
	    }
	}
	return event;
    }

    /**
     * @param officerProfile
     * @return OfficerProfileResponseEvent
     */
    public static OfficerProfileResponseEvent getThinOfficerProfileResponseEvent(OfficerProfile officerProfile)
    {
	
	OfficerProfileResponseEvent event = new OfficerProfileResponseEvent();
	event.setOfficerId(officerProfile.getOID().toString());
	event.setBadgeNum(officerProfile.getBadgeNum());
	event.setDepartmentId(officerProfile.getDepartmentId());
	Department department = officerProfile.getDepartment(); //87191/ Brings from SM
	if (department != null)
	{
	    event.setDepartmentName(department.getDepartmentName());
	    event.setAgencyId(department.getAgencyId());
	    event.setAgencyName(department.getAgencyName());
	}
	event.setCellPhone(officerProfile.getCellPhone());
	event.setEmail(officerProfile.getEmail());
	event.setFirstName(officerProfile.getFirstName());
	event.setLastName(officerProfile.getLastName());
	event.setMiddleName(officerProfile.getMiddleName());
	event.setOfficerProfileId(officerProfile.getOfficerProfileId());
	event.setManagerId(officerProfile.getManagerId());
	event.setPager(officerProfile.getPager());
	event.setOtherIdNum(officerProfile.getOtherIdNum());
	event.setUserId(officerProfile.getLogonId());
	event.setWorkPhone(officerProfile.getWorkPhoneNum());
	event.setSupervisor(officerProfile.getSupervisor());
	event.setSurvey(officerProfile.getSurvey());
	event.setTopic(PDOfficerProfileConstants.OFFICER_PROFILE_EVENT_TOPIC);

	String officerStatusId = officerProfile.getStatusId();
	event.setStatusId(officerStatusId);

	if ((officerProfile.getStatusId() != null) && (!(officerProfile.getStatusId().equals(""))))
	{
	    Code code = officerProfile.getStatus();
	    if (code != null)
	    {
		event.setStatus(code.getDescription());
	    }
	}
	event.setOfficerSubTypeId(officerProfile.getOfficerSubTypeId());
	if ((officerProfile.getOfficerSubTypeId() != null) && (!(officerProfile.getOfficerSubTypeId().equals(""))))
	{
	    Code code = officerProfile.getOfficerSubType();
	    if (code != null)
	    {
		event.setOfficerSubType(code.getDescription());
	    }
	}
	if (PDSecurityHelper.isUserMA())
	{
	   
	    event.setUpdatableStatus(PDConstants.YES);
	    if (officerStatusId != null && officerStatusId.equalsIgnoreCase(PDSecurityConstants.SHORTEND_INACTIVE))
	    {
		event.setDeletableStatus(PDConstants.NO);
	    }
	    else
	    {
		event.setDeletableStatus(PDConstants.YES);
	    }
	}
	else
	    if (PDSecurityHelper.isUserSA())
	    {
		 
		if (event.getAgencyId().equalsIgnoreCase(PDSecurityHelper.getUserAgencyId()))
		{
		    event.setUpdatableStatus(PDConstants.YES);
		    //event.setDeletableStatus(PDConstants.YES);
		    if (officerStatusId != null && officerStatusId.equalsIgnoreCase(PDSecurityConstants.SHORTEND_INACTIVE))
		    {
			event.setDeletableStatus(PDConstants.NO);
		    }
		    else
		    {
			event.setDeletableStatus(PDConstants.YES);
		    }
		}
		else
		{
		    event.setUpdatableStatus(PDConstants.NO);
		    event.setDeletableStatus(PDConstants.NO);
		}
		
		

	    }
	    else
		if (PDSecurityHelper.isUserASA() || PDSecurityHelper.isUserLA())
		{
		  /*  if ((PDSecurityHelper.validateAdminDept(event.getDepartmentId())))
		    {
			event.setUpdatableStatus(PDConstants.YES);
			event.setDeletableStatus(PDConstants.YES);
		    }
		    else
		    {
			event.setDeletableStatus(PDConstants.NO);
			event.setUpdatableStatus(PDConstants.NO);
		    }*/ //87191
		}
		else
		{
		    
		    String userId = event.getUserId();
		    String logonId = PDSecurityHelper.getLogonId();
		    String logon2 = null;
		    if ("L".equalsIgnoreCase(officerProfile.getOfficerTypeId()))
		    {
			if (userId == null || userId.equals(""))
			{
			    Iterator<JIMS2AccountView> iter = JIMS2AccountView.findAll("userAccountOID", officerProfile.getOID().toString());
			    while (iter.hasNext())
			    {
				JIMS2AccountView jims = (JIMS2AccountView) iter.next();
				userId = jims.getJIMS2LogonId();
				break;
			    }
			}
			IUserInfo user = PDSecurityHelper.getUser();
			logon2 = (user != null) ? user.getJIMS2LogonId() : "";
		    }
		    if (userId != null && (userId.equalsIgnoreCase(logonId) || userId.equalsIgnoreCase(logon2)))
		    {
			event.setLimitedUpdatableStatus(PDConstants.YES);
			event.setUpdatableStatus(PDConstants.NO);
		    }
		    else
		    {
			event.setUpdatableStatus(PDConstants.NO);
			event.setLimitedUpdatableStatus(PDConstants.NO);
		    }
		    event.setDeletableStatus(PDConstants.NO);
		    //	add logic here to check if user has MOP-INACTOFF feature and set deletableStatus to Yes
		    ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		    if (mgr != null)
		    {
			IUserInfo userInfo = mgr.getIUserInfo();
			Set ufeatures = mgr.getFeatures();
			if (ufeatures.contains("MOP-INACTOFF"))
			{
			    if (userInfo.getDepartmentId().equalsIgnoreCase(event.getDepartmentId()))
			    {
				event.setDeletableStatus(PDConstants.YES);
			    }
			}
		    }
		}
	return event;
    }

    public static void setOfficerWorkAddress(OfficerProfileAddress workAddress, UpdateOfficerProfileEvent requestEvent)
    {
	workAddress.setAdditionalZipCode(requestEvent.getAdditionalZipCode());
	workAddress.setAptNum(requestEvent.getApartmentNum());
	workAddress.setCity(requestEvent.getCity());
	workAddress.setStateId(requestEvent.getStateId());
	workAddress.setStreetName(requestEvent.getStreetName());
	workAddress.setStreetNum(requestEvent.getStreetNum());
	workAddress.setStreetTypeId(requestEvent.getStreetTypeId());
	workAddress.setZipCode(requestEvent.getZipCode());
	workAddress.setAddressTypeId(PDAddressConstants.ADDRESS_TYPE_BUS);
    }

    /**
     * @param workDayRequestEvent
     */
    public static WorkDay setWorkDay(WorkDay workDay, WorkDayRequestEvent workDayRequestEvent)
    {
	workDay.setDayId(workDayRequestEvent.getDayId());
	workDay.setDayOff(workDayRequestEvent.getDayOff());
	workDay.setStartTimeId(workDayRequestEvent.getStartTimeId());
	workDay.setEndTimeId(workDayRequestEvent.getEndTimeId());
	workDay.setWorkScheduleId(workDayRequestEvent.getWorkScheduleId());
	return workDay;
    }

    public static OfficerProfileResponseEvent getBasicOfficerProfileResponseEvent(OfficerProfile officerProfile)
    {
	OfficerProfileResponseEvent event = new OfficerProfileResponseEvent();
	event.setOfficerId(officerProfile.getOID().toString());
	event.setCellPhone(officerProfile.getCellPhone());
	event.setEmail(officerProfile.getEmail());
	event.setFirstName(officerProfile.getFirstName());
	event.setLastName(officerProfile.getLastName());
	event.setMiddleName(officerProfile.getMiddleName());
	event.setOfficerProfileId(officerProfile.getOfficerProfileId());
	event.setOfficerName(new Name(officerProfile.getFirstName(), officerProfile.getMiddleName(), officerProfile.getLastName()));
	event.setManagerId(officerProfile.getManagerId());
	event.setUserId(officerProfile.getLogonId());
	event.setBadgeNum(officerProfile.getBadgeNum());
	event.setOfficerTypeId(officerProfile.getOfficerTypeId());
	event.setOfficerType((PDCodeHelper.getCodeDescriptionByCode(PDCodeHelper.getCodes(PDCodeTableConstants.OFFICERTYPE, false), officerProfile.getOfficerTypeId())));
	event.setOfficerSubTypeId(officerProfile.getOfficerSubTypeId());
	event.setSupervisor(officerProfile.getSupervisor());
	event.setSurvey(officerProfile.getSurvey());
	event.setOfficerSubType((PDCodeHelper.getCodeDescriptionByCode(PDCodeHelper.getCodes(PDCodeTableConstants.OFFICER_SUBTYPE, false), officerProfile.getOfficerSubTypeId())));
	String officerStatusId = officerProfile.getStatusId();
	event.setStatusId(officerStatusId);
	event.setStatus((PDCodeHelper.getCodeDescriptionByCode(PDCodeHelper.getCodes(PDCodeTableConstants.AGENCY_STATUS, false), officerProfile.getStatusId())));
	event.setWorkPhone(officerProfile.getWorkPhoneNum());
	event.setJuvUnitId(officerProfile.getJuvUnitId());
	
	if ((officerProfile.getJuvUnitId() != null) && (!(officerProfile.getJuvUnitId().equals(""))))
	{
	    GetJuvLocationUnitDetailsEvent reqEvent = new GetJuvLocationUnitDetailsEvent();
	    reqEvent.setJuvLocUnitId(officerProfile.getJuvUnitId());
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch.postEvent(reqEvent);
	    CompositeResponse response = (CompositeResponse) dispatch.getReply();
	    LocationResponseEvent respEvent = (LocationResponseEvent) MessageUtil.filterComposite(response, LocationResponseEvent.class);
	    event.setJuvUnit(respEvent.getLocationUnitName());
	}
	
	return event;
    }

    /**
     * @param warrant
     */
    public static void sendOfficerWarrantAssociationResponseEvent(JuvenileWarrant warrant)
    {
	OfficerJuvWarrantAssociationResponseEvent oJWAResponse = new OfficerJuvWarrantAssociationResponseEvent();
	if (warrant != null)
	{
	    oJWAResponse.setJuvNumber("" + warrant.getJuvenileNum());
	    oJWAResponse.setJuvFirstName(warrant.getFirstName());
	    oJWAResponse.setJuvLastName(warrant.getLastName());
	    oJWAResponse.setWarrantId(warrant.getOID().toString());
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    dispatch.postEvent(oJWAResponse);

	}
    }

    /**
     * Send Emails to the users in the group.
     * 
     * @param groupId
     * @return OfficerProfileResponseEvent
     */
    public static List<OfficerProfileResponseEvent> getOfficerProfilesInUserGroup(String groupId)
    {
	GetOfficerProfileFromUserGroupEvent event = (GetOfficerProfileFromUserGroupEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILEFROMUSERGROUP);
	event.setUserGroupId(groupId);
	CompositeResponse replyEvent = MessageUtil.postRequest(event);
	List<OfficerProfileResponseEvent> userGroupUsers = MessageUtil.compositeToList(replyEvent, OfficerProfileResponseEvent.class);
	return userGroupUsers;
    }

    /**
     * getOfficerProfileByLogonId
     * 
     * @param userId
     * @return OfficerProfileResponseEvent
     */
    public static OfficerProfileResponseEvent getOfficerProfileByLogonId(String userId)
    {
	GetOfficerProfileByLogonIdEvent event = (GetOfficerProfileByLogonIdEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILEBYLOGONID);
	event.setUserId(userId);
	CompositeResponse replyEvent = MessageUtil.postRequest(event);
	List<OfficerProfileResponseEvent> userList = MessageUtil.compositeToList(replyEvent, OfficerProfileResponseEvent.class);
	if (userList != null && !userList.isEmpty())
	{
	    return userList.get(0);
	}
	return null;
    }
}