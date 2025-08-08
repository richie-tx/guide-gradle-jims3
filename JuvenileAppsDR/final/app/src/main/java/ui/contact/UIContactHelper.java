/*
 * Created on Jul 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.contact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import messaging.administerlocation.GetJuvLocationUnitsByLocationIdEvent;
import messaging.administerlocation.GetJuvLocationUnitsEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.agency.GetAgenciesEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.officer.reply.WorkDayResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.officer.GetOfficerProfileEvent;
import messaging.officer.GetOfficerProfilesByAttributeEvent;
import messaging.user.GetUserProfileEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.LocationControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.UIConstants;
import naming.UserControllerServiceNames;
import ui.common.Name;
import ui.contact.officer.form.OfficerForm;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UIContactHelper
{
	public static Collection fetchAgencies(String agencyType, boolean sort)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetAgenciesEvent agencyEvent = (GetAgenciesEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETAGENCIES);
		agencyEvent.setAgencyTypeId(agencyType);

		dispatch.postEvent(agencyEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Collection agencies = MessageUtil.compositeToCollection(response, AgencyResponseEvent.class);
		agencies = MessageUtil.processEmptyCollection(agencies);
		if (sort == true)
		{
			Collections.sort((ArrayList) agencies);
		}
		return agencies;
	}

	/**
	 * @param responseEvent OfficerProfileResponseEvent
	 * @param officerForm OfficerForm
	 * @return OfficerProfileResponseEvent
	 */
	public static void setOfficerProfileForm(OfficerProfileResponseEvent responseEvent, OfficerForm officerForm)
	{
		officerForm.setLastName(responseEvent.getLastName());
		officerForm.setFirstName(responseEvent.getFirstName());
		officerForm.setDepartmentId(responseEvent.getDepartmentId());
		officerForm.setOtherIdNum(responseEvent.getOtherIdNum());
		officerForm.setBadgeNum(responseEvent.getBadgeNum());
		officerForm.setUserId(responseEvent.getUserId());
		officerForm.setMiddleName(responseEvent.getMiddleName());
		officerForm.setDepartmentName(responseEvent.getDepartmentName());
		officerForm.setOfficerTypeId(responseEvent.getOfficerTypeId());
		officerForm.setOfficerSubTypeId(responseEvent.getOfficerSubTypeId());
		officerForm.setSsn1(getSsn1(responseEvent));
		officerForm.setSsn2(getSsn2(responseEvent));
		officerForm.setSsn3(getSsn3(responseEvent));
		officerForm.setJuvLocationId(responseEvent.getJuvLocationId());
		if(responseEvent.getJuvLocationId() != null)
		{
			Collection coll = getLocationUnits(officerForm);
			officerForm.setJuvUnits(coll);
			officerForm.setJuvUnitId(responseEvent.getJuvUnitId());
		}
		else
			officerForm.setJuvUnits(new ArrayList());
		officerForm.setWorkPhoneAreaCode(getPhoneAreaCode(responseEvent.getWorkPhone()));
		officerForm.setWorkPhonePrefix(getPhonePrefix(responseEvent.getWorkPhone()));
		officerForm.setWorkPhoneMain(getPhoneMain(responseEvent.getWorkPhone()));
		officerForm.setExtn(responseEvent.getExtn());
		officerForm.setHomePhoneAreaCode(getPhoneAreaCode(responseEvent.getHomePhone()));
		officerForm.setHomePhonePrefix(getPhonePrefix(responseEvent.getHomePhone()));
		officerForm.setHomePhoneMain(getPhoneMain(responseEvent.getHomePhone()));
		officerForm.setCellPhoneAreaCode(getPhoneAreaCode(responseEvent.getCellPhone()));
		officerForm.setCellPhonePrefix(getPhonePrefix(responseEvent.getCellPhone()));
		officerForm.setCellPhoneMain(getPhoneMain(responseEvent.getCellPhone()));
		officerForm.setPagerAreaCode(getPhoneAreaCode(responseEvent.getPager()));
		officerForm.setPagerPrefix(getPhonePrefix(responseEvent.getPager()));
		officerForm.setPagerMain(getPhoneMain(responseEvent.getPager()));
		officerForm.setFaxAreaCode(getPhoneAreaCode(responseEvent.getFax()));
		officerForm.setFaxPrefix(getPhonePrefix(responseEvent.getFax()));
		officerForm.setFaxMain(getPhoneMain(responseEvent.getFax()));
		officerForm.setFaxLocation(responseEvent.getFaxLocation());
		officerForm.setEmail(responseEvent.getEmail());
		officerForm.setRankId(responseEvent.getRankId());
		officerForm.setDivisionName(responseEvent.getDivisionName());
		officerForm.setAssignedArea(responseEvent.getAssignedArea());
		officerForm.setRadioNumber(responseEvent.getRadioNumber());
		officerForm.setWorkShift(responseEvent.getWorkShift());
		officerForm.setStreetNumber(responseEvent.getStreetNum());
		officerForm.setStreetName(responseEvent.getStreetName());
		officerForm.setStreetTypeId(responseEvent.getStreetTypeId());
		officerForm.setApartmentNumber(responseEvent.getApartmentNum());
		officerForm.setCity(responseEvent.getCity());
		officerForm.setStateId(responseEvent.getStateId());
		officerForm.setZipCode(responseEvent.getZipCode());
		officerForm.setAdditionalZipCode(responseEvent.getAdditionalZipCode());
		officerForm.setManagerId(responseEvent.getManagerId());
		officerForm.setManagerFirstName(responseEvent.getManagerFirstName());
		officerForm.setManagerLastName(responseEvent.getManagerLastName());
		officerForm.setManagerMiddleName(responseEvent.getManagerMiddleName());
		officerForm.setStatusId(responseEvent.getStatusId());
		officerForm.setOfficerProfileId(responseEvent.getOfficerProfileId());
		officerForm.setAgencyId(responseEvent.getAgencyId());
		officerForm.setDeletableStatus(responseEvent.getDeletableStatus());
		officerForm.setUpdatableStatus(responseEvent.getUpdatableStatus());
		officerForm.setLimitedUpdatableStatus(responseEvent.getLimitedUpdatableStatus());
		officerForm.setSupervisor(responseEvent.getSupervisor());
		officerForm.setSurvey(responseEvent.getSurvey());
		Collection workSchedules = responseEvent.getWorkSchedules();
		setWorkDayResponseEventToOfficerForm(workSchedules, officerForm);
	}

	public static Collection getLocationUnits(OfficerForm officerForm)
	{
		GetJuvLocationUnitsByLocationIdEvent event = 
	
		(GetJuvLocationUnitsByLocationIdEvent ) EventFactory.getInstance(LocationControllerServiceNames.GETJUVLOCATIONUNITSBYLOCATIONID);
		event.setLocationId(officerForm.getJuvLocationId());
		event.setAgencyId(getDepartmentId(officerForm));
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		List coll = MessageUtil.compositeToList(compositeResponse, LocationResponseEvent.class);
		if (coll != null)
		{
			Collections.sort(coll, LocationResponseEvent.JuvUnitNameComparator);
			return coll;
		}
		else
			return null;
	}
	public static String getDepartmentId(OfficerForm of)
	{
		ISecurityManager securityManager = (ISecurityManager)ContextManager.getSession().get("ISecurityManager");		
		IUserInfo userInfo = securityManager.getIUserInfo();
		return userInfo.getDepartmentId();
		
	}
	/**
	 * @param workSchedules
	 * @param officerForm
	 */
	private static void setWorkDayResponseEventToOfficerForm(Collection workSchedules, OfficerForm officerForm)
	{
		HashMap map = new HashMap();
		Iterator iter = workSchedules.iterator();
		while (iter.hasNext())
		{
			WorkDayResponseEvent workDayResponseEvent = (WorkDayResponseEvent) iter.next();
			if (workDayResponseEvent.getOfficerProfileId() != null && workDayResponseEvent.getDayId() != null)
			{
				map.put(workDayResponseEvent.getOfficerProfileId() + workDayResponseEvent.getDayId(), workDayResponseEvent);
			}
		}

		WorkDayResponseEvent sundayEvent = (WorkDayResponseEvent) map.get(officerForm.getOfficerProfileId() + UIConstants.SUNDAY.toUpperCase());
		if (sundayEvent != null)
		{
			officerForm.setStartTimeId0(sundayEvent.getStartTimeId());
			officerForm.setEndTimeId0(sundayEvent.getEndTimeId());
			officerForm.setDayOff0(sundayEvent.getOffDay());
			officerForm.setWorkDayId0(sundayEvent.getDayId());
			officerForm.setWorkScheduleId0(sundayEvent.getWorkScheduleId());
		}

		WorkDayResponseEvent mondayEvent = (WorkDayResponseEvent) map.get(officerForm.getOfficerProfileId() + UIConstants.MONDAY.toUpperCase());
		if (mondayEvent != null)
		{
			officerForm.setStartTimeId1(mondayEvent.getStartTimeId());
			officerForm.setEndTimeId1(mondayEvent.getEndTimeId());
			officerForm.setDayOff1(mondayEvent.getOffDay());
			officerForm.setWorkDayId1(mondayEvent.getDayId());
			officerForm.setWorkScheduleId1(mondayEvent.getWorkScheduleId());
		}
		WorkDayResponseEvent tuesdayEvent =	(WorkDayResponseEvent) map.get(officerForm.getOfficerProfileId() + UIConstants.TUESDAY.toUpperCase());
		if (tuesdayEvent != null)
		{
			officerForm.setStartTimeId2(tuesdayEvent.getStartTimeId());
			officerForm.setEndTimeId2(tuesdayEvent.getEndTimeId());
			officerForm.setDayOff2(tuesdayEvent.getOffDay());
			officerForm.setWorkDayId2(tuesdayEvent.getDayId());
			officerForm.setWorkScheduleId2(tuesdayEvent.getWorkScheduleId());
		}
		WorkDayResponseEvent wednesdayEvent = (WorkDayResponseEvent) map.get(officerForm.getOfficerProfileId() + UIConstants.WEDNESDAY.toUpperCase());
		if (wednesdayEvent != null)
		{
			officerForm.setStartTimeId3(wednesdayEvent.getStartTimeId());
			officerForm.setEndTimeId3(wednesdayEvent.getEndTimeId());
			officerForm.setDayOff3(wednesdayEvent.getOffDay());
			officerForm.setWorkDayId3(wednesdayEvent.getDayId());
			officerForm.setWorkScheduleId3(wednesdayEvent.getWorkScheduleId());
		}
		WorkDayResponseEvent thursdayEvent = (WorkDayResponseEvent) map.get(officerForm.getOfficerProfileId() + UIConstants.THURSDAY.toUpperCase());
		if (thursdayEvent != null)
		{
			officerForm.setStartTimeId4(thursdayEvent.getStartTimeId());
			officerForm.setEndTimeId4(thursdayEvent.getEndTimeId());
			officerForm.setDayOff4(thursdayEvent.getOffDay());
			officerForm.setWorkDayId4(thursdayEvent.getDayId());
			officerForm.setWorkScheduleId4(thursdayEvent.getWorkScheduleId());
		}
		WorkDayResponseEvent fridayEvent = (WorkDayResponseEvent) map.get(officerForm.getOfficerProfileId() + UIConstants.FRIDAY.toUpperCase());
		if (fridayEvent != null)
		{
			officerForm.setStartTimeId5(fridayEvent.getStartTimeId());
			officerForm.setEndTimeId5(fridayEvent.getEndTimeId());
			officerForm.setDayOff5(fridayEvent.getOffDay());
			officerForm.setWorkDayId5(fridayEvent.getDayId());
			officerForm.setWorkScheduleId5(fridayEvent.getWorkScheduleId());
		}
		WorkDayResponseEvent saturdayEvent = (WorkDayResponseEvent) map.get(officerForm.getOfficerProfileId() + UIConstants.SATURDAY.toUpperCase());
		if (saturdayEvent != null)
		{
			officerForm.setStartTimeId6(saturdayEvent.getStartTimeId());
			officerForm.setEndTimeId6(saturdayEvent.getEndTimeId());
			officerForm.setDayOff6(saturdayEvent.getOffDay());
			officerForm.setWorkDayId6(saturdayEvent.getDayId());
			officerForm.setWorkScheduleId6(saturdayEvent.getWorkScheduleId());
		}
	}

	/**
	 * @param responseEvent
	 * @return
	 */
	private static String getSsn1(OfficerProfileResponseEvent responseEvent)
	{
		String ssn = responseEvent.getSsn();
		if (ssn != null && !(ssn.equals("")))
		{
			if (ssn.length() >= 3)
			{
				return ssn.substring(0, 3);
			}
			else
			{
				return "";
			}
		}
		return "";
	}

	/**
	 * @param responseEvent
	 * @return
	 */
	private static String getSsn2(OfficerProfileResponseEvent responseEvent)
	{
		String ssn = responseEvent.getSsn();
		if (ssn != null && !(ssn.equals("")))
		{
			if (ssn.length() >= 5)
			{
				return ssn.substring(3, 5);
			}
			else
			{
				return "";
			}
		}
		return "";
	}

	/**
	 * @param responseEvent
	 * @return
	 */
	private static String getSsn3(OfficerProfileResponseEvent responseEvent)
	{
		String ssn = responseEvent.getSsn();
		if (ssn != null && !(ssn.equals("")))
		{
			if (ssn.length() > 5)
			{
				return ssn.substring(5);
			}
			else
			{
				return "";
			}
		}
		return "";
	}

	/**
	 * @param phone
	 * @return
	 */
	private static String getPhoneAreaCode(String phone)
	{
		if (phone != null && !(phone.equals("")))
		{
			if (phone.length() >= 3)
			{
				return phone.substring(0, 3);
			}
			else
			{
				return "";
			}
		}
		return "";
	}

	/**
	 * @param phone
	 * @return
	 */
	private static String getPhonePrefix(String phone)
	{
		if (phone != null && !(phone.equals("")))
		{
			if (phone.length() >= 6)
			{
				return phone.substring(3, 6);
			}
			else
			{
				return "";
			}
		}
		return "";
	}

	/**
	 * @param phone
	 * @return
	 */
	private static String getPhoneMain(String phone)
	{
		if (phone != null && !(phone.equals("")))
		{
			if (phone.length() > 6)
			{
				return phone.substring(6);
			}
			else
			{
				return "";
			}
		}
		return "";
	}

	/**
		 * @param responseEvent
		 * @return
		 */
	private static String getWorkPhoneCode(OfficerProfileResponseEvent responseEvent)
	{
		String workPhone = responseEvent.getWorkPhone();
		if (workPhone != null && !(workPhone.equals("")))
		{
			if (workPhone.length() >= 3)
			{
				return workPhone.substring(0, 3);
			}
			else
			{
				return "";
			}
		}
		return "";
	}

	/**
	 * @param officerForm
	 */
	public static void setManageOfficerValuesFromDropDownList(OfficerForm officerForm)
	{
		CodeResponseEvent codeEvent = null;
		// set the officer Id
		if (officerForm.getOfficerTypeId() != null && !(officerForm.getOfficerTypeId().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getOfficerTypeId(), officerForm.getOfficerTypes());
			officerForm.setOfficerType(codeEvent.getDescription());
		}
		else
		{
			officerForm.setOfficerType("");
		}
		
		if (officerForm.getOfficerSubTypeId() != null && !(officerForm.getOfficerSubTypeId().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getOfficerSubTypeId(), officerForm.getOfficerSubTypes());
			officerForm.setOfficerSubType(codeEvent.getDescription());
		}
		else
		{
			officerForm.setOfficerSubType("");
		}

		// set Juv Location
		if (officerForm.getJuvLocationId() != null && !(officerForm.getJuvLocationId().equals("")))
		{
			String locationId = officerForm.getJuvLocationId();
//			officerForm.setJuvLocation(codeEvent.getDescription());
			Iterator iter = officerForm.getJuvLocations().iterator();
			while (iter.hasNext()) {
				LocationResponseEvent l = (LocationResponseEvent) iter.next();
				if (l.getLocationId().equals(locationId)) {
					officerForm.setJuvLocation(l.getLocationName());
					break;
				}
			}
		}
		else
		{
			officerForm.setJuvLocation("");
		}

		// set Juv Unit
		if (officerForm.getJuvUnitId() != null && !(officerForm.getJuvUnitId().equals("")))
		{
			//codeEvent = getCodeResponseEvent(officerForm.getJuvUnitId(), officerForm.getJuvUnits());
			Collection coll = officerForm.getJuvUnits();
			Iterator iter = coll.iterator();
			while(iter.hasNext())
			{
				LocationResponseEvent resp = (LocationResponseEvent)iter.next();
				if(resp.getJuvLocationUnitId().equals(officerForm.getJuvUnitId()))
					officerForm.setJuvUnit(resp.getLocationUnitName());
			}
			
		}
		else
		{
			officerForm.setJuvUnit("");
		}

		// set Rank
		if (officerForm.getRankId() != null && !(officerForm.getRankId().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getRankId(), officerForm.getRanks());
			officerForm.setRank(codeEvent.getDescription());
		}
		else
		{
			officerForm.setRank("");
		}

		//  set Street Type
		if (officerForm.getStreetTypeId() != null && !(officerForm.getStreetTypeId().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getStreetTypeId(), officerForm.getStreetTypes());
			officerForm.setStreetType(codeEvent.getDescription());
		}
		else
		{
			officerForm.setStreetType("");
		}

		// set State
		if (officerForm.getStateId() != null && !(officerForm.getStateId().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getStateId(), officerForm.getStates());
			officerForm.setState(codeEvent.getDescription());
		}
		else
		{
			officerForm.setState("");
		}
		//	   set StartTime0
		if (officerForm.getStartTimeId0() != null && !(officerForm.getStartTimeId0().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getStartTimeId0(), officerForm.getWorkDays());
			officerForm.setStartTime0(codeEvent.getDescription());
		}
		else
		{
			officerForm.setStartTime0("");
		}
		//	   set StartTime1
		if (officerForm.getStartTimeId1() != null && !(officerForm.getStartTimeId1().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getStartTimeId1(), officerForm.getWorkDays());
			officerForm.setStartTime1(codeEvent.getDescription());
		}
		else
		{
			officerForm.setStartTime1("");
		}
		//		set StartTime2
		if (officerForm.getStartTimeId2() != null && !(officerForm.getStartTimeId2().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getStartTimeId2(), officerForm.getWorkDays());
			officerForm.setStartTime2(codeEvent.getDescription());
		}
		else
		{
			officerForm.setStartTime2("");
		}
		//		set StartTime3
		if (officerForm.getStartTimeId3() != null && !(officerForm.getStartTimeId3().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getStartTimeId3(), officerForm.getWorkDays());
			officerForm.setStartTime3(codeEvent.getDescription());
		}
		else
		{
			officerForm.setStartTime3("");
		}
		//		set StartTime4
		if (officerForm.getStartTimeId4() != null && !(officerForm.getStartTimeId4().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getStartTimeId4(), officerForm.getWorkDays());
			officerForm.setStartTime4(codeEvent.getDescription());
		}
		else
		{
			officerForm.setStartTime4("");
		}
		//		set StartTime5
		if (officerForm.getStartTimeId5() != null && !(officerForm.getStartTimeId5().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getStartTimeId5(), officerForm.getWorkDays());
			officerForm.setStartTime5(codeEvent.getDescription());
		}
		else
		{
			officerForm.setStartTime5("");
		}
		//			set StartTime6
		if (officerForm.getStartTimeId6() != null && !(officerForm.getStartTimeId6().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getStartTimeId6(), officerForm.getWorkDays());
			officerForm.setStartTime6(codeEvent.getDescription());
		}
		else
		{
			officerForm.setStartTime6("");
		}
		//	   set EndTime0
		if (officerForm.getEndTimeId0() != null && !(officerForm.getEndTimeId0().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getEndTimeId0(), officerForm.getWorkDays());
			officerForm.setEndTime0(codeEvent.getDescription());
		}
		else
		{
			officerForm.setEndTime0("");
		}
		//			 set EndTime1
		if (officerForm.getEndTimeId1() != null && !(officerForm.getEndTimeId1().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getEndTimeId1(), officerForm.getWorkDays());
			officerForm.setEndTime1(codeEvent.getDescription());
		}
		else
		{
			officerForm.setEndTime1("");
		}
		//			  set EndTime2
		if (officerForm.getEndTimeId2() != null && !(officerForm.getEndTimeId2().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getEndTimeId2(), officerForm.getWorkDays());
			officerForm.setEndTime2(codeEvent.getDescription());
		}
		else
		{
			officerForm.setEndTime2("");
		}
		//			  set EndTime3
		if (officerForm.getEndTimeId3() != null && !(officerForm.getEndTimeId3().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getEndTimeId3(), officerForm.getWorkDays());
			officerForm.setEndTime3(codeEvent.getDescription());
		}
		else
		{
			officerForm.setEndTime3("");
		}
		//			  set EndTime4
		if (officerForm.getEndTimeId4() != null && !(officerForm.getEndTimeId4().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getEndTimeId4(), officerForm.getWorkDays());
			officerForm.setEndTime4(codeEvent.getDescription());
		}
		else
		{
			officerForm.setEndTime4("");
			//			  set EndTime5
		}
		if (officerForm.getEndTimeId5() != null && !(officerForm.getEndTimeId5().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getEndTimeId5(), officerForm.getWorkDays());
			officerForm.setEndTime5(codeEvent.getDescription());
		}
		else
		{
			officerForm.setEndTime5("");
		}
		//				  set EndTime6
		if (officerForm.getEndTimeId6() != null && !(officerForm.getEndTimeId6().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getEndTimeId6(), officerForm.getWorkDays());
			officerForm.setEndTime6(codeEvent.getDescription());
		}
		else
		{
			officerForm.setEndTime6("");
		}
		
		if (officerForm.getStatusId() != null && !(officerForm.getStatusId().equals("")))
		{
			codeEvent = getCodeResponseEvent(officerForm.getStatusId(), officerForm.getOfficerStatuses());
			officerForm.setStatus(codeEvent.getDescription());
		}
		else
		{
			officerForm.setStatus("");
		}
	}

	/**
	 * @param code String
	 * @param collection
	 */
	private static CodeResponseEvent getCodeResponseEvent(String code, Collection collection)
	{
		CodeResponseEvent codeEvent = null;
		Iterator iter = collection.iterator();
		while (iter.hasNext())
		{
			codeEvent = (CodeResponseEvent) iter.next();
			if (code.equals(codeEvent.getCode()))
			{
				break;
			}
		}
		return codeEvent;
	}
	
	/**
	 * @param agencyList
	 * @return collection
	 */
	public static Collection sortAgencyList(Collection agencyList){
		Iterator iter = agencyList.iterator();
		SortedMap map = new TreeMap();
		while(iter.hasNext()){
			AgencyResponseEvent ag = (AgencyResponseEvent) iter.next();	
			map.put(ag.getAgencyName() + " " + ag.getAgencyId(), ag);
		}
		return new ArrayList(map.values());
	}
	
	
	 public static List getJuvUnitCodes()
	    {

	        // TODO Auto-generated method stub
	        //return CodeHelper.getCodes(PDCodeTableConstants.JUVUNIT, true);
	    	GetJuvLocationUnitsEvent ev = (GetJuvLocationUnitsEvent) EventFactory
	        .getInstance(LocationControllerServiceNames.GETJUVLOCATIONUNITS);
	    	
	    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(ev);

			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(response);
			MessageUtil.processReturnException(dataMap);
			
			Iterator iter = MessageUtil.compositeToCollection(response, LocationResponseEvent.class).iterator();
			List list = new ArrayList();
			CodeResponseEvent codeResp = new CodeResponseEvent();
			while(iter.hasNext()){
				LocationResponseEvent resp = (LocationResponseEvent)iter.next();
				codeResp.setCode(resp.getJuvLocationUnitId());
				codeResp.setDescription(resp.getLocationUnitName());
				list.add(codeResp);
			}
			return list;
	    }
	 
	 public static List<LocationResponseEvent> getLocationUnits()
	    {

	        // TODO Auto-generated method stub
	        //return CodeHelper.getCodes(PDCodeTableConstants.JUVUNIT, true);
	    	GetJuvLocationUnitsEvent ev = (GetJuvLocationUnitsEvent) EventFactory
	        .getInstance(LocationControllerServiceNames.GETJUVLOCATIONUNITS);
	    	
	    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(ev);

			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(response);
			MessageUtil.processReturnException(dataMap);
			
			Iterator iter = MessageUtil.compositeToCollection(response, LocationResponseEvent.class).iterator();
			List list = new ArrayList();
			CodeResponseEvent codeResp = new CodeResponseEvent();
			while(iter.hasNext()){
				LocationResponseEvent resp = (LocationResponseEvent)iter.next();
				list.add(resp);
			}
			return list;
	    }
	 
		/**
		 * Creates UserResponseEVent from UserProfile entity
		 * @param userProfile
		 * @return
		 */
		public static String getUserProfileName(String userLogonId)
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			GetUserProfileEvent userEvent =  (GetUserProfileEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILE);
			userEvent.setLogonId(userLogonId);

			dispatch.postEvent(userEvent);
			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			UserResponseEvent userResponseEvent = (UserResponseEvent) MessageUtil.filterComposite(response, UserResponseEvent.class);
			
			if (userResponseEvent != null) {
				Name fullName = new Name(userResponseEvent.getFirstName(),userResponseEvent.getMiddleName(),userResponseEvent.getLastName());
				String userProfileName = fullName.getFormattedName();	
				return userProfileName;
			} else {
				return "";
			}
		}
	 
		/**
		 * @return
		 */
		public static String getOfficerLogonId(String officerId)
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			GetOfficerProfileEvent officerEvent = (GetOfficerProfileEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILE);
			officerEvent.setOfficerProfileId(officerId);
			dispatch.postEvent(officerEvent);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);

			OfficerProfileResponseEvent officerResponseEvent = (OfficerProfileResponseEvent) MessageUtil.filterComposite(compositeResponse, OfficerProfileResponseEvent.class);
			if (officerResponseEvent != null) {
				return officerResponseEvent.getUserId();
			} else {
				return "";
			}
		}	
		/**
		 * @return
		 */
		public static String getOfficerProfilePhone(String officerLogonId)
		{
			String workPhone = null;
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			GetOfficerProfilesByAttributeEvent event = (GetOfficerProfilesByAttributeEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILESBYATTRIBUTE);
			event.setAttributeName("logonId");
			event.setAttributeValue(officerLogonId);
			dispatch.postEvent(event);
			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			List officerprofiles = MessageUtil.compositeToList(response, OfficerProfileResponseEvent.class);
			
			if (officerprofiles != null && officerprofiles.size() > 0)
			{
				Iterator<OfficerProfileResponseEvent> events = officerprofiles.iterator();
				while(events.hasNext()){
					OfficerProfileResponseEvent resp = events.next();
					workPhone = resp.getWorkPhone();
				}
			}

			return workPhone;
		}
		
		//added for US 11109
		
		public static String getAreaCode(String phone)
		{
			return getPhoneAreaCode(phone);
		}
		public static String getPrefix(String phone)
		{
			return getPhonePrefix(phone);
		}
		public static String getLast4Digits(String phone)
		{
			return getPhoneMain(phone);
		}
}