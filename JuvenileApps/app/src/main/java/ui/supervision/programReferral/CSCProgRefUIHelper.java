/*
 * Created on Apr 22, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.administerprogramreferrals.ChangeReferralStatusEvent;
import messaging.administerprogramreferrals.DeleteProgramReferralEvent;
import messaging.administerprogramreferrals.ExitProgramReferralEvent;
import messaging.administerprogramreferrals.GetFilteredServiceProvidersEvent;
import messaging.administerprogramreferrals.GetProgramLocationsEvent;
import messaging.administerprogramreferrals.GetProgramReferralsEvent;
import messaging.administerprogramreferrals.GetSuperviseeCaseEvent;
import messaging.administerprogramreferrals.GetSupervisionOrderConditionEvent;
import messaging.administerprogramreferrals.InitiateReferralsEvent;
import messaging.administerprogramreferrals.ReferProgramReferralEvent;
import messaging.administerprogramreferrals.ReferralFormFieldValue;
import messaging.administerprogramreferrals.SaveProgramReferralsEvent;
import messaging.administerprogramreferrals.SaveReferralFormEvent;
import messaging.administerprogramreferrals.SubmitProgramReferralEvent;
import messaging.administerprogramreferrals.UpdateProgramReferralEvent;
import messaging.administerprogramreferrals.UpdateProgramReferralsEvent;
import messaging.administerprogramreferrals.reply.AppointmentDetailsResponseEvent;
import messaging.administerprogramreferrals.reply.CSProgramReferralResponseEvent;
import messaging.administerprogramreferrals.reply.ReferralFormFieldResponseEvent;
import messaging.administerprogramreferrals.reply.ReferralFormOptionResponseEvent;
import messaging.administerprogramreferrals.reply.ReferralFormResponseEvent;
import messaging.administerprogramreferrals.reply.ReferralTypeResponseEvent;
import messaging.administerprogramreferrals.reply.ServiceProviderReftypeResponseEvent;
import messaging.administerprogramreferrals.reply.SuperviseeCaseResponseEvent;
import messaging.administerprogramreferrals.reply.SuperviseeNOfficerDetailsResponseEvent;
import messaging.administerprogramreferrals.reply.SupervisionOrderConditionResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.IPhoneNumber;
import messaging.csserviceprovider.reply.CSProgramLocationResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CSAdministerProgramReferralsConstants;
import naming.CSProgramReferralControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import ui.common.ComplexCodeTableHelper;
import ui.common.PhoneNumber;
import ui.common.RefFormFieldComparator;
import ui.common.SimpleCodeTableHelper;
import ui.common.StringUtil;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.programReferral.form.CSCProgRefForm;
import ui.supervision.programReferral.form.CSCProgRefForm.RefTypeCodeNRefTypeNumNRefIdValue;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSCProgRefUIHelper 
{
	
	private static final String ID_SEPARATOR = "-";
	private static final String ZERO = "0";
	
	private static final String LEFT_ALIGN = "left";
	
	/**
	 * 
	 * @param defendantId
	 * @return
	 */
	public static GetProgramReferralsEvent getProgramReferralsEvent(String defendantId)
	{
		GetProgramReferralsEvent reqEvent= (GetProgramReferralsEvent) EventFactory.getInstance(CSProgramReferralControllerServiceNames.GETPROGRAMREFERRALS);
		
		reqEvent.setDefendantId(defendantId);
		return reqEvent;
	}
	
	
	
	public static CSCProgRefSearchBean convertCSProgramReferralResponseEventTOProgRefSearchBean(CSProgramReferralResponseEvent myEvent)
	{
		CSCProgRefSearchBean myBean=new CSCProgRefSearchBean();
		
		myBean.setReferralId(myEvent.getProgramReferralId());
		
		myBean.setSpn(myEvent.getDefendantId());
		myBean.setCriminalCaseId(myEvent.getCriminalCaseId());
		String caseNum = myBean.getCriminalCaseId().substring(3);
		myBean.setCaseNum(caseNum);
		
		myBean.setAutoReferral(myEvent.isAutoReferral());
		
		myBean.setReferralTypeCd(myEvent.getReferralTypeCode());
		myBean.setReferralTypeDesc(myEvent.getReferralTypeDesc());
		
		myBean.setProgramId(myEvent.getProgramId());
		myBean.setLocationId(myEvent.getLocationId());
		
		if(!StringUtil.isEmpty(myEvent.getNewServiceProviderName()))
		{
			myBean.setUserEnteredSP(true);
			myBean.setServiceProvidername(myEvent.getNewServiceProviderName());
		}
		else
		{
			myBean.setServiceProvidername(myEvent.getServiceProviderName());
			myBean.setProgramIdentifier(myEvent.getProgramIdentifier());
		}
		
		myBean.setReferralDate(myEvent.getReferralDate());	
		myBean.setBeginDate(myEvent.getProgramBeginDate());		
		myBean.setEndDate(myEvent.getProgramEndDate());
		
		myBean.setSentToState(myEvent.isSentToState());
		myBean.setReferralStatusCd(myEvent.getReferralStatusCode());
		myBean.calculateStatus();
		
		return myBean;
	}
	
	public static void populateAppointmentDetailsOnReferral(CSCProgRefForm programRefForm, AppointmentInfoBean appointmentInfoBean, AppointmentDetailsResponseEvent apptResponseEvent)
	{
		appointmentInfoBean.setProgramLocationExist(false);
		String spName = apptResponseEvent.getServiceProviderName();
		if(spName!=null)
		{
			appointmentInfoBean.setProgramLocationExist(true);
			
			appointmentInfoBean.setServiceProviderName(spName);
			IPhoneNumber spPhoneNum = new PhoneNumber(apptResponseEvent.getServiceProviderPhone());
			appointmentInfoBean.setServiceProviderPhone(spPhoneNum);
			appointmentInfoBean.setServiceProviderURL(apptResponseEvent.getServiceProviderURL());
			
//			print the selected Program Location and entered schedule date and time
			String scheduledDateStr = programRefForm.getScheduledDateAsStr();
			if(!StringUtil.isEmpty(scheduledDateStr))
			{
				appointmentInfoBean.setScheduleDateStr(scheduledDateStr);
				appointmentInfoBean.setScheduleTime(programRefForm.getScheduledTime());
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateUtil.stringToDate(scheduledDateStr, DateUtil.DATE_FMT_1));
				
				int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
				appointmentInfoBean.setWeekDay(getWeekDayName(dayOfWeek));
			}
			
			CSCServiceProviderProgLocBean spProgLocBean = programRefForm.getReferralProgramLocBean();
			CSCLocationInfoBean locBean = spProgLocBean.getProgramLocationBean();
			if(locBean!=null)
			{
				CSCLocationInfoBean locationInfoBean = new CSCLocationInfoBean();
				appointmentInfoBean.setProgramLocationBean(locationInfoBean);
				
				locationInfoBean.setStreetNumber(locBean.getStreetNumber());
				locationInfoBean.setStreetName(locBean.getStreetName());
				locationInfoBean.setStreetTypeCd(locBean.getStreetTypeCd());
				locationInfoBean.setAptNum(locBean.getAptNum());
				locationInfoBean.setCity(locBean.getCity());
				locationInfoBean.setState(locBean.getState());
				locationInfoBean.setZipCode(locBean.getZipCode());
				locationInfoBean.setLocationPhone(locBean.getLocationPhone());
				locationInfoBean.setLocationFax(locBean.getLocationFax());
			}
			
			appointmentInfoBean.setProgramName(apptResponseEvent.getProgramName());
			appointmentInfoBean.setContactsList(apptResponseEvent.getContactsList());
			appointmentInfoBean.setOfficeHours(apptResponseEvent.getOfficeHours());
		}
	}
	
	
	
	
	public static void populateAppointmentDetails(AppointmentInfoBean appointmentInfoBean, AppointmentDetailsResponseEvent apptResponseEvent)
	{
		appointmentInfoBean.setProgramLocationExist(false);
		String spName = apptResponseEvent.getServiceProviderName();
		if(spName!=null)
		{
			appointmentInfoBean.setProgramLocationExist(true);
			
			appointmentInfoBean.setServiceProviderName(spName);
			IPhoneNumber spPhoneNum = new PhoneNumber(apptResponseEvent.getServiceProviderPhone());
			appointmentInfoBean.setServiceProviderPhone(spPhoneNum);
			appointmentInfoBean.setServiceProviderURL(apptResponseEvent.getServiceProviderURL());
			
			Date scheduledDateTime = apptResponseEvent.getScheduleDateTime();
			String dateStr = "";
			String AMPMTime = "";
			if(scheduledDateTime!=null)
			{
				String dateTime = DateUtil.dateToString(scheduledDateTime, DateUtil.DATETIME24_FMT_1).trim();
				dateTime = dateTime.trim();
				String[] dateTimeArr = dateTime.split(" ");
				dateStr = dateTimeArr[0];
				AMPMTime = get12HourFormatTime(dateTimeArr[1]);
				
				appointmentInfoBean.setScheduleDateStr(dateStr);
				appointmentInfoBean.setScheduleTime(AMPMTime);
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateUtil.stringToDate(dateStr, DateUtil.DATE_FMT_1));
				
				int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
				appointmentInfoBean.setWeekDay(getWeekDayName(dayOfWeek));
			}
	
			CSCLocationInfoBean locationInfoBean = new CSCLocationInfoBean();
			appointmentInfoBean.setProgramLocationBean(locationInfoBean);
			
			locationInfoBean.setStreetNumber(apptResponseEvent.getStreetNumber());
			locationInfoBean.setStreetName(apptResponseEvent.getStreetName());
			locationInfoBean.setStreetTypeCd(apptResponseEvent.getStreetType());
			locationInfoBean.setAptNum(apptResponseEvent.getAptNum());
			locationInfoBean.setCity(apptResponseEvent.getCity());
			locationInfoBean.setState(apptResponseEvent.getState());
			locationInfoBean.setZipCode(apptResponseEvent.getZipCode());
			IPhoneNumber phoneNumber = new PhoneNumber(apptResponseEvent.getLocationPhone());
			locationInfoBean.setLocationPhone(phoneNumber);
			IPhoneNumber faxNumber = new PhoneNumber(apptResponseEvent.getLocationFax());
			locationInfoBean.setLocationFax(faxNumber);

			appointmentInfoBean.setProgramName(apptResponseEvent.getProgramName());
			appointmentInfoBean.setContactsList(apptResponseEvent.getContactsList());
			appointmentInfoBean.setOfficeHours(apptResponseEvent.getOfficeHours());
		}
	}

	
	
	
	public static String getWeekDayName(int dayOfWeek)
	{
		String weekDayString = "";
		
		switch(dayOfWeek)
		{
			case 1: weekDayString = "Sunday";
					break;
					
			case 2: weekDayString = "Monday";
					break;
			
			case 3: weekDayString = "Tuesday";
					break;
			
			case 4: weekDayString = "Wednesday";
					break;
			
			case 5: weekDayString = "Thursday";
					break;
			
			case 6: weekDayString = "Friday";
					break;
			
			case 7: weekDayString = "Saturday";
					break;
		}
		return weekDayString;
	}
	
	
	public static void populateProgReferralForm(CSCProgRefForm progRefForm, CSProgramReferralResponseEvent referralRespEvent)
	{
		progRefForm.setAutoReferral(referralRespEvent.isAutoReferral());
		progRefForm.setIncarcerationReferral(referralRespEvent.isIncarcerationReferral());
		
		progRefForm.setCriminalCaseId(referralRespEvent.getCriminalCaseId());
		
		progRefForm.setReferralStatusCd(referralRespEvent.getReferralStatusCode());
		
		progRefForm.setOldReferralTypeCd(referralRespEvent.getReferralTypeCode());
		progRefForm.setReferralTypeCode(referralRespEvent.getReferralTypeCode());
		
		progRefForm.setReferralDate(referralRespEvent.getReferralDate());
		
		clearReferralLocationDetails(progRefForm);
		
		progRefForm.setServiceProviderId(referralRespEvent.getServiceProviderId());
		String programId = referralRespEvent.getProgramId();
		String locationId = referralRespEvent.getLocationId();
		if(programId!=null && locationId!=null)
		{
			String programLocId = programId + "-" + locationId;
			progRefForm.setProgLocId(programLocId);
		}
		
		String userEnteredSP = referralRespEvent.getNewServiceProviderName();
		if(!StringUtil.isEmpty(userEnteredSP))
		{
			progRefForm.setUserEnteredServiceProvider(true);
			progRefForm.setServiceProviderId(CSAdministerProgramReferralsConstants.USER_ENTERED_SP);
			progRefForm.setUserEnteredServiceProviderName(userEnteredSP);
			progRefForm.setUserEnteredSPPhone(new PhoneNumber(referralRespEvent.getNewServiceProviderPhone()));
			progRefForm.setUserEnteredSPFax(new PhoneNumber(referralRespEvent.getNewServiceProviderFax()));
		}
		
		Date scheduledDateTime = referralRespEvent.getScheduleDate();
		String date = "";
		String AMPMTime = "";
		if(scheduledDateTime!=null)
		{
			String dateTime = DateUtil.dateToString(scheduledDateTime, DateUtil.DATETIME24_FMT_1).trim();
			dateTime = dateTime.trim();
			String[] dateTimeArr = dateTime.split(" ");
			date = dateTimeArr[0];
			AMPMTime = get12HourFormatTime(dateTimeArr[1]);
			
			progRefForm.setScheduledDateAsStr(date);
			progRefForm.setScheduledTime(AMPMTime);
			
			progRefForm.setUserEnteredScheduledDateAsStr(date);
			progRefForm.setUserEnteredScheduledTime(AMPMTime);
		}
		
//		submit referral fields
		progRefForm.setProgramBeginDate(referralRespEvent.getProgramBeginDate());
//		progRefForm.setContractProgram(referralRespEvent.isSpContractProgram());   //RRY 09/01/09 use same value used to set bean contract program
		progRefForm.setContractProgram(referralRespEvent.isContractProgram());  //CWS 03/18/10 revised to use value for referral record, defect 64491
																				// 			   value may change based on service provider contract value
		progRefForm.setTracerNum(referralRespEvent.getTracerNumber());
/*	Dawn commented out the following code. don't understand why we should default when the
 *  information is on the record.
 * 		if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_SUBMITREFERRAL))
		{
//			by default
			progRefForm.setContractProgram(true);
			progRefForm.setTracerNum("");
		}*/
		progRefForm.setReasonForPlacementId(referralRespEvent.getPlacementReasonCd());
		try
		{
			if(referralRespEvent.getConfinementYears()==0)
			{
				progRefForm.setConfinementLengthYears("00");
			}
			else
			{
				progRefForm.setConfinementLengthYears(String.valueOf(referralRespEvent.getConfinementYears()));
			}
			
			if(referralRespEvent.getConfinementMonths()==0)
			{
				progRefForm.setConfinementLengthMonths("00");
			}
			else
			{
				progRefForm.setConfinementLengthMonths(String.valueOf(referralRespEvent.getConfinementMonths()));
			}
			
			if(referralRespEvent.getConfinementDays()==0)
			{
				progRefForm.setConfinementLengthDays("00");
			}
			else
			{
				progRefForm.setConfinementLengthDays(String.valueOf(referralRespEvent.getConfinementDays()));
			}
		}
		catch(Exception ex)
		{
			progRefForm.setConfinementLengthYears("00");
			progRefForm.setConfinementLengthMonths("00");
			progRefForm.setConfinementLengthDays("00");
		}
		
//		exit referral fields
		progRefForm.setProgramEndDate(referralRespEvent.getProgramEndDate());
		progRefForm.setReasonForDischargeId(referralRespEvent.getDischargeReasonCd());
		
		progRefForm.setSubmitComments("");
		progRefForm.setExitComments("");
		if(referralRespEvent.getSubmitComments()!= null)
		{
			progRefForm.setSubmitComments(referralRespEvent.getSubmitComments());
		}
		if(referralRespEvent.getExitComments()!= null)
		{
			progRefForm.setExitComments(referralRespEvent.getExitComments());
		}
		
	    progRefForm.setSentToState(referralRespEvent.isSentToState());
	    
		if(!(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT)))
		{
			CSCServiceProviderProgLocBean progLocBean = new CSCServiceProviderProgLocBean();
			progRefForm.setReferralProgramLocBean(progLocBean);
			
			if(progRefForm.isUserEnteredServiceProvider())
			{
				progLocBean.setServiceProviderName(referralRespEvent.getNewServiceProviderName());
			}
			else
			{
				progLocBean.setServiceProviderId(referralRespEvent.getServiceProviderId());
				progLocBean.setServiceProviderName(referralRespEvent.getServiceProviderName());
			}
			
			progLocBean.setReferralTypeCd(referralRespEvent.getReferralTypeCode());
			progLocBean.setReferralTypeDesc(referralRespEvent.getReferralTypeDesc());
			
			progLocBean.setProgramId(referralRespEvent.getProgramId());
			progLocBean.setProgramIdentifier(referralRespEvent.getProgramIdentifier());
			progLocBean.setProgramName(referralRespEvent.getProgramName());
			
			progLocBean.setCstsCode(referralRespEvent.getCstsCode());
			
			ArrayList languagesList = new ArrayList();
			List languageCdList = referralRespEvent.getProgramLanguagesList();
			if (languageCdList != null){
				Iterator languagesIter = languageCdList.iterator();
				while(languagesIter.hasNext())
				{
					String languageCd = (String)languagesIter.next();
					String languageDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ASP_CS_LANGUAGES_OFFERRED, languageCd);
					languagesList.add(languageDesc);
				}
			} 
			progLocBean.setLanguagesOffered(languagesList);
			
			String sexSpecificDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ASP_CS_SEX_SPECIFIC, referralRespEvent.getSexSpecificCode());
			progLocBean.setSexSpecificDesc(sexSpecificDesc);
			
			if(referralRespEvent.isSpContractProgram())
			{
				progLocBean.setContractProgramDesc("Yes");
			}
			else
			{
				progLocBean.setContractProgramDesc("No");
				progRefForm.setContractProgram(false);  //CWS 3/17/10  part of change for defect 64491 
			}
			
			progLocBean.setScheduleDateAsStr(date);
			progLocBean.setScheduleTimeDesc(AMPMTime);
			
			ArrayList locationsList = new ArrayList();
			progLocBean.setProgramLocationsList(locationsList);
			
			if(!progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_REREFERRAL))
			{
				CSCLocationInfoBean locationInfoBean = new CSCLocationInfoBean();
				locationInfoBean.setLocationId(referralRespEvent.getLocationId());
				locationInfoBean.setStreetNumber(referralRespEvent.getStreetNumber());
				locationInfoBean.setStreetName(referralRespEvent.getStreetName());
				locationInfoBean.setStreetTypeCd(referralRespEvent.getStreetType());
				locationInfoBean.setAptNum(referralRespEvent.getAptNum());
				locationInfoBean.setCity(referralRespEvent.getCity());
				locationInfoBean.setState(referralRespEvent.getState());
				locationInfoBean.setZipCode(referralRespEvent.getZipCode());
				
				if(progRefForm.isUserEnteredServiceProvider())
				{
					IPhoneNumber phoneNumber = new PhoneNumber(referralRespEvent.getNewServiceProviderPhone());
					locationInfoBean.setLocationPhone(phoneNumber);
					IPhoneNumber faxNumber = new PhoneNumber(referralRespEvent.getNewServiceProviderFax());
					locationInfoBean.setLocationFax(faxNumber);
				}
				else
				{
					IPhoneNumber phoneNumber = new PhoneNumber(referralRespEvent.getLocationPhone());
					locationInfoBean.setLocationPhone(phoneNumber);
					IPhoneNumber faxNumber = new PhoneNumber(referralRespEvent.getLocationFax());
					locationInfoBean.setLocationFax(faxNumber);
				}
				
				progLocBean.setProgramLocationBean(locationInfoBean);
			}
		}
	}
	
	
	public static void clearReferralLocationDetails(CSCProgRefForm progRefForm)
	{
		progRefForm.setServiceProviderId("");
		progRefForm.setProgLocId("");
		
		progRefForm.setUserEnteredServiceProvider(false);
		progRefForm.setUserEnteredServiceProviderName("");
		progRefForm.setUserEnteredSPPhone(new PhoneNumber(""));
		progRefForm.setUserEnteredSPFax(new PhoneNumber(""));
		
		progRefForm.setUserEnteredScheduledDateAsStr("");
		progRefForm.setUserEnteredScheduledTime("");
		
		progRefForm.setScheduledDateAsStr("");
		progRefForm.setScheduledTime("");
		
		progRefForm.setReferralProgramLocBean(null);
	}
	
	public static String get12HourFormatTime(String time24HourFormat)
	{
		String AMPMTime = "";
		
		String[] timeStrArr = time24HourFormat.split(":");
		int hour = Integer.parseInt(timeStrArr[0]);
		
		if(hour < 1)
		{
			hour = hour + 12;
			String hourStr = String.valueOf(hour);
			AMPMTime =  hourStr + ":" + timeStrArr[1] + " AM";
		}
		else if((hour>=1) && (hour < 12))
		{
			AMPMTime = time24HourFormat + " AM";
		}
		else
		if(hour==12)
		{
			AMPMTime = time24HourFormat + " PM";
		}
		else
		if(hour >= 13)
		{
			hour = hour - 12;
			String hourStr = String.valueOf(hour);
			if(hourStr.length()<2)
			{
				hourStr = ZERO + hourStr;
			}
			AMPMTime =  hourStr + ":" + timeStrArr[1] + " PM";
		}
		return AMPMTime;
	}
	
	public static InitiateReferralsEvent getInitiateReferralsEvent(CSCProgRefForm progRefForm)
	{
		InitiateReferralsEvent initiate_event = (InitiateReferralsEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.INITIATEREFERRALS);
		
		if (progRefForm.getAction().equals(CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT))
		{
			initiate_event.setProgramReferralId(progRefForm.getProgRefId());
		}
		initiate_event.setDefendantId(progRefForm.getSpn());
		initiate_event.setCriminalCaseId(progRefForm.getCriminalCaseId());
		List referralTypesCodeList = getReferralTypeCodes(progRefForm.getSelectedReferralTypesList());
		initiate_event.setReferralTypes(referralTypesCodeList);
		
		if (progRefForm.getAction().equals(CSAdministerProgramReferralsConstants.ACTION_CREATE))
		{
			initiate_event.setProgRefIdsToDeleteList(progRefForm.getProgRefIdsToDeleteList());
			initiate_event.setProgramUnitReferral( false );
			
			for ( int z =0; z < referralTypesCodeList.size(); z ++ ){
				
				String tempRefTypeCD = (String) referralTypesCodeList.get( z );
				if ( tempRefTypeCD != null && tempRefTypeCD.length() > 3 ){
					
					tempRefTypeCD = tempRefTypeCD.substring( 0,3 );
					if ( "INC".equals( tempRefTypeCD )){
						
						initiate_event.setIncarcerationReferral( true );
						continue;
					}
				}
				
			}
		}
		
		return initiate_event;
	}
	
	
	public static CSCServiceProviderBean getUserEnteredServiceProvider()
	{
		CSCServiceProviderBean myBean=new CSCServiceProviderBean();
		
//		myBean.setSelected(false);
		myBean.setServiceProviderFaxNumber(new PhoneNumber(""));
		myBean.setServiceProviderId("");
		myBean.setServiceProviderInHouseAsStr("");
		myBean.setServiceProviderName("");
		myBean.setServiceProviderPhoneNumber(new PhoneNumber(""));
		myBean.setServiceProviderRefTypes("");
		myBean.setServiceProviderRegions("");
		
		return myBean;
	}
	
	public static GetFilteredServiceProvidersEvent getFilteredServiceProvidersEvent(CSCProgRefForm progRefForm)
	{
		GetFilteredServiceProvidersEvent filteredSPEvent = (GetFilteredServiceProvidersEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.GETFILTEREDSERVICEPROVIDERS);
		
		List referralTypesCodeList = getReferralTypeCodes(progRefForm.getSelectedReferralTypesList());
		filteredSPEvent.setReferralTypes(referralTypesCodeList);
		
		ArrayList regionCdsList = new ArrayList();
		String[] regionCds = progRefForm.getSelectedRegionIds();
		if(regionCds!= null && (regionCds.length >0))
		{
			for(String regionCd:regionCds)
			{
				if(!regionCd.trim().equals(""))
				{
					regionCdsList.add(regionCd);
				}
			}
		}
		filteredSPEvent.setRegionCdsList(regionCdsList);
		
		ArrayList languageCdsList = new ArrayList();
		String[] languageCdArr = progRefForm.getSelectedLanguagesIds();
		if(languageCdArr!= null && languageCdArr.length>0)
		{
			for(String languageCd: languageCdArr)
			{
				if(!languageCd.trim().equals(""))
				{
					languageCdsList.add(languageCd);
				}
			}
		}
		filteredSPEvent.setLanguagesOfferedList(languageCdsList);
		
		filteredSPEvent.setSexSpecific(progRefForm.getSelectedSexSpecificId());
		filteredSPEvent.setContractProgram(progRefForm.getSelectedContractProgram());
		
		return filteredSPEvent;
	}
	
	
	
	public static GetProgramLocationsEvent getProgramLocationsEvent(CSCProgRefForm programReferralForm)
	{
		GetProgramLocationsEvent prog_loc_event = (GetProgramLocationsEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.GETPROGRAMLOCATIONS);
		
		List selectedSPList = programReferralForm.getSelectedSPList();
		prog_loc_event.setServiceProviderIds(getSelectedServiceProviderIds(selectedSPList));
		
		List progressedRefTypeList = programReferralForm.getProgressedReferralTypesList();
		List referralTypesCodeList = CSCProgRefUIHelper.getReferralTypeCodes(progressedRefTypeList);
//		do not query program-locations for the user entered refType
		if(programReferralForm.isUserEnteredServiceProvider())
		{
			String userEnteredSPRefTypeCd = programReferralForm.getUserEnteredServiceProviderRefTypeCd();
			if(referralTypesCodeList.contains(userEnteredSPRefTypeCd))
			{
				referralTypesCodeList.remove(userEnteredSPRefTypeCd);
			}
		}
		prog_loc_event.setReferralTypesCodeList(referralTypesCodeList);
		
		return prog_loc_event;
	}
	
	
	
	public static GetProgramLocationsEvent getProgramLocationsEventForReferral(CSCProgRefForm programReferralForm)
	{
		GetProgramLocationsEvent prog_loc_event = (GetProgramLocationsEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.GETPROGRAMLOCATIONS);
		
		List selectedSPIdList = new ArrayList();
		selectedSPIdList.add(programReferralForm.getServiceProviderId());
		prog_loc_event.setServiceProviderIds(selectedSPIdList);
		
		List referralTypesCodeList = new ArrayList();
		referralTypesCodeList.add(programReferralForm.getReferralTypeCode());
		prog_loc_event.setReferralTypesCodeList(referralTypesCodeList);
		
		return prog_loc_event;
	}
	
	public static GetProgramLocationsEvent getProgramLocationsEventForPrintPacket(CSCProgRefForm programReferralForm)
	{
		GetProgramLocationsEvent prog_loc_event = (GetProgramLocationsEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.GETPROGRAMLOCATIONS);
		
		List filteredSPList = programReferralForm.getFilteredSPList();
		prog_loc_event.setServiceProviderIds(getSelectedServiceProviderIds(filteredSPList));
		
//		obtain all the refTypes from the already-defined SPs
		HashSet selSPRefTypeCdSet = new HashSet();
		Iterator iter = filteredSPList.iterator();
		while(iter.hasNext())
		{
			CSCServiceProviderBean spBean = (CSCServiceProviderBean)iter.next();
			List refTypeCdsList = spBean.getServiceProviderRefTypeCdList();
			
			selSPRefTypeCdSet.addAll(refTypeCdsList);
		}
		List selSPRefTypeList = new ArrayList();
		selSPRefTypeList.addAll(selSPRefTypeCdSet);
		prog_loc_event.setReferralTypesCodeList(selSPRefTypeList);
		
		prog_loc_event.setOrderByLocation(true);
		
		return prog_loc_event;
	}
	

	
	public static void populateSuperviseeNOfficerDetails(AppointmentInfoBean appointmentInfoBean, SuperviseeNOfficerDetailsResponseEvent superviseeNOfficerDetailsEvt)
	{
		appointmentInfoBean.setDefendantId(superviseeNOfficerDetailsEvt.getDefendantId());
		appointmentInfoBean.setSuperviseeName(superviseeNOfficerDetailsEvt.getSuperviseeName());
		appointmentInfoBean.setSuperviseePhone(new PhoneNumber(superviseeNOfficerDetailsEvt.getSuperviseePhoneNum()));
		appointmentInfoBean.setDob(superviseeNOfficerDetailsEvt.getSuperviseeDOB());
		appointmentInfoBean.setOffenseDesc(superviseeNOfficerDetailsEvt.getOffenseDesc());
		appointmentInfoBean.setCrt(superviseeNOfficerDetailsEvt.getCrt());
		
		appointmentInfoBean.setOfficerName(superviseeNOfficerDetailsEvt.getOfficerName());
		appointmentInfoBean.setPositionPOI(superviseeNOfficerDetailsEvt.getPositionPOI());
		appointmentInfoBean.setPositionName(superviseeNOfficerDetailsEvt.getPositionName());
		appointmentInfoBean.setProgramUnitDesc(superviseeNOfficerDetailsEvt.getProgramUnitDesc());
		appointmentInfoBean.setOfficerPhone(new PhoneNumber(superviseeNOfficerDetailsEvt.getOfficerPhoneNum()));
	}
	

	/**
	 * Determine which service providers Ids have been selected
	 * @param availableReferralTypes
	 * @return
	 */
	public static List getSelectedServiceProviderIds(List selectedSPList)
	{
		List selected_sp_Ids_List = new ArrayList();
		
		int num_sps = selectedSPList.size();
		for (int i=0;i<num_sps;i++)
		{
			CSCServiceProviderBean this_sp = 
				(CSCServiceProviderBean)selectedSPList.get(i);
			
			selected_sp_Ids_List.add(this_sp.getServiceProviderId());
		}
			//return list of selected service providers Ids
		return selected_sp_Ids_List;
	}//end of getSelectedServiceProviders()
	
	
	public static CSCCaseInfoBean convertSuperviseeCaseResponseTOCSCCaseInfoBean(SuperviseeCaseResponseEvent myEvent)
	{
		CSCCaseInfoBean myBean=new CSCCaseInfoBean();
		
		myBean.setCdi(myEvent.getCdi());
		myBean.setCaseNum(myEvent.getCaseNumber());
		myBean.setCriminalCaseId(myEvent.getCdi()+myEvent.getCaseNumber());
		
		String completeCourtNumber = myEvent.getCourtNumber();
		if(completeCourtNumber != null)
		{
			completeCourtNumber = completeCourtNumber.trim();
			String courtNum = completeCourtNumber.substring(completeCourtNumber.indexOf(" ")+1); 
			myBean.setCrt(courtNum);
		}
		else
		{
			myBean.setCrt("");
		}
		
		myBean.setOffenseDesc(myEvent.getOffense());
		myBean.setCasefileDate(myEvent.getCaseFiledDate());
		
		String orderStatusCd = myEvent.getOrderStatusCd();
		String orderStatusDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ORDER_STATUS, orderStatusCd);
		myBean.setOrderStatusDesc(orderStatusDesc);
		
		String versionCd = myEvent.getVersionCd();
		String versionDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.VERSION_TYPE, versionCd);
		myBean.setVersionDesc(versionDesc);
		
		myBean.setOrderFileDate(myEvent.getOrderFiledDate());
		
		return myBean;
	}
	
	
	public static SupervisionOrderConditionBean convertConditionResponseEventToConditionBean(SupervisionOrderConditionResponseEvent condRespEvent)
	{
		SupervisionOrderConditionBean beanObj = new SupervisionOrderConditionBean();
		beanObj.setConditionSeqNumber(condRespEvent.getConditionSeqNum());
		beanObj.setConditionDesc(condRespEvent.getConditionDescription());
		
		return beanObj;
	}
	
	
	public static ReferralTypeBean convertReferralTypeResponseTOReferralTypeBean(ReferralTypeResponseEvent myEvent)
	{
		ReferralTypeBean myBean=new ReferralTypeBean();
		
		myBean.setReferralTypeId(myEvent.getReferralTypeId());
		
		myBean.setReferralTypeCode(myEvent.getReferralTypeCode());
		myBean.setReferralTypeDesc(myEvent.getProgramGroupDesc() + " / " + myEvent.getProgramTypeDesc());
		
		myBean.setSelected(false);
		return myBean;
	}
	
	public static CSCServiceProviderBean convertSPReftypeResponseTOServiceProviderBean(ServiceProviderReftypeResponseEvent myEvent,
																												Map selectedRefTypesMap)
	{
		CSCServiceProviderBean myBean=new CSCServiceProviderBean();
		
//		myBean.setSelected(false);
//		myBean.setSelectedSPId("");
		myBean.setServiceProviderFaxNumber(new PhoneNumber(myEvent.getFaxNumber()));
		myBean.setServiceProviderId(myEvent.getServiceProviderId());
		myBean.setServiceProviderInHouse(myEvent.isInHouse());
		myBean.setServiceProviderInHouseAsStr(myEvent.isInHouse()?"YES":"NO");
		myBean.setServiceProviderName(myEvent.getServiceProviderName());
		myBean.setServiceProviderPhoneNumber(new PhoneNumber(myEvent.getPhoneNumber()));
		myBean.setServiceProviderRefTypeCdList(myEvent.getProgramReferralTypes());
		myBean.setServiceProviderRefTypes(
		getRefTypeString(myEvent.getProgramReferralTypes(), selectedRefTypesMap));
		myBean.setServiceProviderRegions(getProgRegionString(myEvent.getProgramLocationRegions()));
		myBean.setServiceProviderURL(myEvent.getEmailAddress());
		return myBean;
	}
	
	
	
	/**
	 * Determine which referral types have been selected
	 * @param availableReferralTypes
	 * @return
	 */
	public static List getSelectedReferralTypes(List availableReferralTypes)
	{
		int num_types = availableReferralTypes.size();
		List selected_referral_types = new ArrayList();
		for (int i=0;i<num_types;i++)
		{
			ReferralTypeBean this_referral_type = 
				(ReferralTypeBean)availableReferralTypes.get(i);
			if (this_referral_type.selected)
				selected_referral_types.add(this_referral_type);
		}
		
			//return list of selected referral types
		return selected_referral_types;
	}//end of getSelectedReferralTypes()

	/**
	 * Determine which referral types have been selected
	 * @param availableReferralTypes
	 * @return
	 */
	public static List getSelectedReferralType(List availableReferralTypes, 
											String selectedReferralTypeCode)
	{
		int num_types = availableReferralTypes.size();
		List selected_referral_types = new ArrayList();
		for (int i=0;i<num_types;i++)
		{
			ReferralTypeBean this_referral_type = 
				(ReferralTypeBean)availableReferralTypes.get(i);
			if (this_referral_type.referralTypeCode.equals(
					selectedReferralTypeCode))
			{
				selected_referral_types.add(this_referral_type);
				break;
			}
		}
		
			//return list of selected referral types
		return selected_referral_types;
	}//end of getSelectedReferralTypes()
	
	/**
	 * Convert list of referral type beans to referral type codes
	 * @param referralTypeBeans
	 * @return
	 */
	public static List getReferralTypeCodes(List referralTypeBeans)
	{
		int num_types = referralTypeBeans.size();
		List referral_type_codes = new ArrayList();
		for (int i=0;i<num_types;i++)
		{
			ReferralTypeBean this_referral_type = 
				(ReferralTypeBean)referralTypeBeans.get(i);
			referral_type_codes.add(this_referral_type.getReferralTypeCode());
		}
		
			//return list of selected referral types
		return referral_type_codes;
	}//end of getSelectedReferralTypes()
	
	

	/**
	 * Convert list of referral types to corresponding number on selection form
	 * @param referralTypes
	 * @param refTypeBeans
	 * @return
	 */
	public static String getRefTypeString(List referralTypes, Map selectedRefTypesMap)
	{
		String ref_type_string = "";
		
		for (int i=0;i<referralTypes.size();i++)
		{
			RefTypeCodeNRefTypeNumNRefIdValue refTypeDetailsObj = (RefTypeCodeNRefTypeNumNRefIdValue)selectedRefTypesMap.get(referralTypes.get(i));
			ref_type_string += refTypeDetailsObj.getRefNum() + " ";
		}
		
			//return string of referral type numbers
		return ref_type_string;
	}//end of getRefTypeString()
	
	/**
	 * Convert list of program regions into string
	 * @param progRegions
	 * @return
	 */
	public static String getProgRegionString(List progRegions)
	{
		String prog_region_string = "";
		
		if (progRegions != null)
		{
			for (int i=0;i<progRegions.size();i++)
			{
				prog_region_string += progRegions.get(i) + " ";
			}							
		}
		
			//return string of program regions
		return prog_region_string;
	}//end of getProgRegionString()
	
	
	
	public static void convertProgramLocationRespEvtToPacketBean(CSCProgRefForm progRefForm, List sp_prog_loc_response)
	{
		List spPacketBeanList = new ArrayList();
		List newFilteredSPList = new ArrayList();
		List newFilteredSpProgLocList = new ArrayList();
		progRefForm.setSpPacketBeanList(spPacketBeanList);
		
		// Get location ids from jsp RRY
		String[] results = progRefForm.getSelectedSPIdsString().split(",");

		List filteredSPList = progRefForm.getFilteredSPList();
		
		for ( int z=0; z < results.length; z ++ ){
			for ( int s =0; s < filteredSPList.size(); s ++ ){
				
				CSCServiceProviderBean bean = (CSCServiceProviderBean) filteredSPList.get( s );
				String idToTest = results[z];
				
				if ( idToTest.equals( bean.getServiceProviderId() )){
					
					newFilteredSPList.add( bean );
					break;
				}
			}
		}

		
		for ( int z=0; z < results.length; z ++ ){
			for ( int s =0; s < sp_prog_loc_response.size(); s ++ ){
				
				CSProgramLocationResponseEvent response = ( CSProgramLocationResponseEvent ) sp_prog_loc_response.get( s );
					String idToTest = results[z];
				
				if ( idToTest.equals( response.getServiceProviderId() )){
					
					newFilteredSpProgLocList.add( response );
					break;
				}
			}
		}
		
		// Filter new list RRY
		if((newFilteredSpProgLocList != null) && (newFilteredSpProgLocList.size() > 0))
		{
//			Query all the Location Region Codes from the code table
			HashMap locRegionCodeDescMap = new HashMap();
			List regionCodesList = SimpleCodeTableHelper.getUnsortedCodeRespEvts(PDCodeTableConstants.LOCATION_REGION);
			for(int index = 0; index < regionCodesList.size(); index++)
			{
				CodeResponseEvent codeRespEvt = (CodeResponseEvent)regionCodesList.get(index);
				locRegionCodeDescMap.put(codeRespEvt.getCode(), codeRespEvt.getDescription());
			}
			
//			Query all the Language codes from code table
			HashMap languageCodeDescMap = new HashMap();
			List langRespEvtList = SimpleCodeTableHelper.getUnsortedCodeRespEvts(PDCodeTableConstants.ASP_CS_LANGUAGES_OFFERRED);
			for(int index = 0; index < langRespEvtList.size(); index++)
			{
				CodeResponseEvent responseEvt = (CodeResponseEvent)langRespEvtList.get(index);
				languageCodeDescMap.put(responseEvt.getCode(), responseEvt.getDescription());
			}
			
			Iterator spRespIter = newFilteredSpProgLocList.iterator();
			
			while( spRespIter.hasNext() )
			{
				CSCServiceProviderPacketBean spPacketBean = new CSCServiceProviderPacketBean();
				spPacketBeanList.add(spPacketBean);
				
				CSProgramLocationResponseEvent respEvt = (CSProgramLocationResponseEvent)spRespIter.next();
				String respEvtServiceProviderId = respEvt.getServiceProviderId();
				Iterator it = newFilteredSPList.iterator();
				while(it.hasNext())
				{
					CSCServiceProviderBean filteredSP = (CSCServiceProviderBean)it.next();
					if(filteredSP.getServiceProviderId().equalsIgnoreCase(respEvtServiceProviderId))
					{
						spPacketBean.setServiceProviderName(filteredSP.getServiceProviderName());
						spPacketBean.setServiceProviderPhone(filteredSP.getServiceProviderPhoneNumber());
						spPacketBean.setServiceProviderEmail(filteredSP.getServiceProviderURL());
						
						break;
					}
				}
				spPacketBean.setSpFaithBased(respEvt.isFaithBased());
				
				ArrayList programLocBeanList = new ArrayList();
				spPacketBean.setProgramLocList(programLocBeanList);
				
				List respEvtLocationsList = respEvt.getServiceProviderLocations();
				Iterator locationIter = respEvtLocationsList.iterator();
				while(locationIter.hasNext())
				{
					Map locationMap = (Map)locationIter.next();
					
					CSCLocationInfoBean locationInfoBean = new CSCLocationInfoBean();
					programLocBeanList.add(locationInfoBean);
					
					locationInfoBean.setLocationId((String)locationMap.get("locationId"));
					locationInfoBean.setStreetNumber((String)locationMap.get("streetNumber"));
					locationInfoBean.setStreetName((String)locationMap.get("streetName"));
					locationInfoBean.setStreetTypeCd((String)locationMap.get("streetType"));
					locationInfoBean.setAptNum((String)locationMap.get("aptNum"));
					locationInfoBean.setCity((String)locationMap.get("city"));
					locationInfoBean.setState((String)locationMap.get("state"));
					locationInfoBean.setZipCode((String)locationMap.get("zipCode"));
					IPhoneNumber phoneNumber = new PhoneNumber((String)locationMap.get("locationPhone"));
					locationInfoBean.setLocationPhone(phoneNumber);
					IPhoneNumber faxNumber = new PhoneNumber((String)locationMap.get("locationFax"));
					locationInfoBean.setLocationFax(faxNumber);
					locationInfoBean.setLocationRegionDesc("");
					if((locRegionCodeDescMap.get((String)locationMap.get("locationRegionCd"))!=null))
					{
						locationInfoBean.setLocationRegionDesc((String)locRegionCodeDescMap.get((String)locationMap.get("locationRegionCd")));
					}
					
					ArrayList programBeanList = new ArrayList();
					locationInfoBean.setProgramBeanList(programBeanList);
					
					List programsList = (List)locationMap.get("programs");
					Iterator pgmIter = programsList.iterator();
					while(pgmIter.hasNext())
					{
						Map programMap = (Map)pgmIter.next();
						
						CSCProgramInfoBean progInfoBean = new CSCProgramInfoBean();
						programBeanList.add(progInfoBean);
						
						progInfoBean.setProgramId((String)programMap.get("programId"));
						progInfoBean.setProgramName((String)programMap.get("programName"));
						
						progInfoBean.setProgramPrice("");
						String programPrice = (String)programMap.get("programPrice");
						if((!StringUtil.isEmpty(programPrice)) && (!programPrice.equalsIgnoreCase("0.00")))
						{
							progInfoBean.setProgramPrice(programPrice);
						}
						
						ArrayList languagesList = new ArrayList();
						progInfoBean.setLanguagesOffered(languagesList);
						
						List languageCdList = (List)programMap.get("programLanguages");
						StringBuffer languageStrBuf = new StringBuffer("");
						for(int index = 0; index < languageCdList.size(); index++)
						{
							String languageCd = (String)languageCdList.get(index);
							if(!StringUtil.isEmpty(languageCd))
							{
								String languageDesc = (String)languageCodeDescMap.get(languageCd);
								if(languageDesc!=null)
								{
									languageStrBuf.append(languageDesc);
									if(index < languageCdList.size()-1)
									{
										languageStrBuf.append(", ");
									}
								}
							}
						}
						languagesList.add(languageStrBuf.toString());
					}
				}
			}
		}
	}
	
	
	
	public static List convertProgramLocationRespEvtToBean(CSCProgRefForm progRefForm, List sp_prog_loc_response)
	{
		ArrayList serviceProviderPrgmsList = new ArrayList();
		
//		query the Language code table
		HashMap languageCodeDescMap = new HashMap();
		List langRespEvtList = SimpleCodeTableHelper.getUnsortedCodeRespEvts(PDCodeTableConstants.ASP_CS_LANGUAGES_OFFERRED);
		for(int index = 0; index < langRespEvtList.size(); index++)
		{
			CodeResponseEvent responseEvt = (CodeResponseEvent)langRespEvtList.get(index);
			languageCodeDescMap.put(responseEvt.getCode(), responseEvt.getDescription());
		}
		
		if((sp_prog_loc_response != null) && (sp_prog_loc_response.size() > 0))
		{
			Iterator iter = sp_prog_loc_response.iterator();
			
			while(iter.hasNext())
			{
				CSProgramLocationResponseEvent respEvt = (CSProgramLocationResponseEvent)iter.next();
				
				CSCServiceProviderProgramsBean serviceProviderPrgmsBean = new CSCServiceProviderProgramsBean();
				serviceProviderPrgmsBean.setServiceProviderId(respEvt.getServiceProviderId());
				serviceProviderPrgmsBean.setServiceProviderName(respEvt.getServiceProviderName());
				ArrayList programInfoBeanList = new ArrayList();
				serviceProviderPrgmsBean.setServiceProviderPrograms(programInfoBeanList);
				
				List programsList = respEvt.getServiceProviderPrograms();
				Iterator programsIter = programsList.iterator();
				
				while(programsIter.hasNext())
				{
					CSCProgramInfoBean programInfoBean = new CSCProgramInfoBean();
					
					Map programMap = (Map)programsIter.next();
					
					programInfoBean.setProgramId((String)programMap.get("programId"));
					programInfoBean.setProgramIdentifier((String)programMap.get("programIdentifier"));
					programInfoBean.setProgramName((String)programMap.get("programName"));
					
					programInfoBean.setProgramStatusId((String)programMap.get("programStatusId"));
					String progStatusDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ASP_CS_PROGRAM_STATUS, programInfoBean.getProgramStatusId());
					programInfoBean.setProgramStatusDesc(progStatusDesc);
					
					programInfoBean.setReferralTypeCd((String)programMap.get("referralTypeCd"));
					programInfoBean.setReferralTypeNum("");
					Map referralsMap = progRefForm.getReferralTypeCdNDetailsMap();
					RefTypeCodeNRefTypeNumNRefIdValue refTypeCodeNNumVal = (RefTypeCodeNRefTypeNumNRefIdValue)referralsMap.get(programInfoBean.getReferralTypeCd());
					if(refTypeCodeNNumVal != null)
					{
						programInfoBean.setReferralTypeNum(refTypeCodeNNumVal.getRefNum());
					}
					programInfoBean.setCstsCode((String)programMap.get("cstsCode"));
					programInfoBean.setSexSpecificId((String)programMap.get("sexSpecificCode"));
					String sexSpecificDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ASP_CS_SEX_SPECIFIC, programInfoBean.getSexSpecificId());
					programInfoBean.setSexSpecificDesc(sexSpecificDesc);
					if(((String)programMap.get("isContractProgram")).equalsIgnoreCase("true"))
					{
						programInfoBean.setContractProgramDesc("Yes");
					}
					else
					{
						programInfoBean.setContractProgramDesc("No");
					}
					
					ArrayList languagesList = new ArrayList();
					programInfoBean.setLanguagesOffered(languagesList);
					List languageCdList = (List)programMap.get("programLanguages");
					Iterator languagesIter = languageCdList.iterator();
					while(languagesIter.hasNext())
					{
						String languageCd = (String)languagesIter.next();
						if(!StringUtil.isEmpty(languageCd))
						{
							String languageDesc = (String)languageCodeDescMap.get(languageCd);
							if(languageDesc!=null)
							{
								languagesList.add(languageDesc);
							}
						}
					}
					
					
					List progLocationsBeanList = new ArrayList();
					programInfoBean.setProgramLocationList(progLocationsBeanList);
					List allLocations=ComplexCodeTableHelper.getLocationCodes();
					List locationsList = (List)programMap.get("programLocations");
					Iterator locationIter = locationsList.iterator();
					while(locationIter.hasNext())
					{
						Map locationMap = (Map)locationIter.next();
						// will only display locations that are active for this program and service provider
						if ( locationMap.get("validLocStatus").equals("A") ) {
							CSCLocationInfoBean locationInfoBean = new CSCLocationInfoBean();
							String locationId =(String)locationMap.get("locationId");
							String locDesc=ComplexCodeTableHelper.getLocationDesc(allLocations,locationId);
							if(locDesc!=null && locDesc.length()>0){
								locationInfoBean.setLocationId(locationId);
								locationInfoBean.setStreetNumber((String)locationMap.get("streetNumber"));
								locationInfoBean.setStreetName((String)locationMap.get("streetName"));
								locationInfoBean.setStreetTypeCd((String)locationMap.get("streetType"));
								locationInfoBean.setAptNum((String)locationMap.get("aptNum"));
								locationInfoBean.setCity((String)locationMap.get("city"));
								locationInfoBean.setState((String)locationMap.get("state"));
								locationInfoBean.setZipCode((String)locationMap.get("zipCode"));
								Iterator allLocsIter = allLocations.iterator();
								while (allLocsIter.hasNext()) {
									LocationResponseEvent location = (LocationResponseEvent) allLocsIter.next();
									if(StringUtils.isNotEmpty(location.getLocationId()) && location.getLocationId().equals(locationId)){
										String location_phone = (location.getPhoneNumber() == null)?"": location.getPhoneNumber();
										IPhoneNumber phoneNumber = new PhoneNumber(location_phone);
										locationInfoBean.setLocationPhone(phoneNumber);
										String location_fax = (location.getLocationFax() == null)?"": location.getLocationFax();
										IPhoneNumber faxNumber = new PhoneNumber(location_fax);
										locationInfoBean.setLocationFax(faxNumber);
										break;
									}
									
								}
								progLocationsBeanList.add(locationInfoBean);
							}	
							if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT))
							{
								String progLocId = programInfoBean.getProgramId() + "-" +locationInfoBean.getLocationId();
								if(progLocId.equalsIgnoreCase(progRefForm.getProgLocId()))
								{
									locationInfoBean.setSelected(true);
								}
								else
								{
									locationInfoBean.setSelected(false);
								}
							}
						}
					}
					programInfoBeanList.add(programInfoBean);
				}
				serviceProviderPrgmsList.add(serviceProviderPrgmsBean);
			}
		}
		return serviceProviderPrgmsList;
	}
	
	
	public static void convertProgLocRespEvtToBeanForRereferal(CSCProgRefForm progRefForm, List sp_prog_loc_response)
	{
		CSCServiceProviderProgLocBean spProgLocBean = progRefForm.getReferralProgramLocBean();
		
		ArrayList proglocationsList = new ArrayList();
		spProgLocBean.setProgramLocationsList(proglocationsList);
	
		String[] ids = progRefForm.getProgLocId().split("-");
		String selProgramId = ids[0];
		
		if((sp_prog_loc_response != null) && (sp_prog_loc_response.size() > 0))
		{
			Iterator iter = sp_prog_loc_response.iterator();
			
			while(iter.hasNext())
			{
				CSProgramLocationResponseEvent respEvt = (CSProgramLocationResponseEvent)iter.next();
				
				List programsList = respEvt.getServiceProviderPrograms();
				Iterator programsIter = programsList.iterator();
				
				while(programsIter.hasNext())
				{
					Map programMap = (Map)programsIter.next();
					String programId = (String)programMap.get("programId");
					
					if(selProgramId.equalsIgnoreCase(programId))
					{
						List locationsList = (List)programMap.get("programLocations");
						Iterator locationIter = locationsList.iterator();
						while(locationIter.hasNext())
						{
							Map locationMap = (Map)locationIter.next();
							
							CSCLocationInfoBean locationInfoBean = new CSCLocationInfoBean();
							locationInfoBean.setLocationId((String)locationMap.get("locationId"));
							locationInfoBean.setStreetNumber((String)locationMap.get("streetNumber"));
							locationInfoBean.setStreetName((String)locationMap.get("streetName"));
							locationInfoBean.setStreetTypeCd((String)locationMap.get("streetType"));
							locationInfoBean.setAptNum((String)locationMap.get("aptNum"));
							locationInfoBean.setCity((String)locationMap.get("city"));
							locationInfoBean.setState((String)locationMap.get("state"));
							locationInfoBean.setZipCode((String)locationMap.get("zipCode"));
							IPhoneNumber phoneNumber = new PhoneNumber((String)locationMap.get("locationPhone"));
							locationInfoBean.setLocationPhone(phoneNumber);
							IPhoneNumber faxNumber = new PhoneNumber((String)locationMap.get("locationFax"));
							locationInfoBean.setLocationFax(faxNumber);
							
							String progLocId = programId + "-" +locationInfoBean.getLocationId();
							if(progLocId.equalsIgnoreCase(progRefForm.getProgLocId()))
							{
								locationInfoBean.setSelected(true);
							}
							
							proglocationsList.add(locationInfoBean);
							
//							just to set dummy locationInfoBean
							spProgLocBean.setProgramLocationBean(locationInfoBean);
						}
					}
				}
			}
		}
	}
	
	
	public static List getSelectedServiceProviderProgLocList(CSCProgRefForm progRefForm, ArrayList selectedProgLocIdsList)
	{
		List selectedSpProgLocList = new ArrayList();
		
		List selectedSPList = progRefForm.getSelectedSPList();
		Map selRefTypesMap = progRefForm.getReferralTypeCdNDetailsMap();
		
		Iterator selectedSPIter = selectedSPList.iterator();
		while(selectedSPIter.hasNext())
		{
			CSCServiceProviderProgramsBean spProgBean = (CSCServiceProviderProgramsBean)selectedSPIter.next();
			List spPrograms = spProgBean.getServiceProviderPrograms();
			
			Iterator programIter = spPrograms.iterator();
			
			while(programIter.hasNext())
			{
				CSCProgramInfoBean progInfoBean = (CSCProgramInfoBean)programIter.next();
				String programId = progInfoBean.getProgramId();
				
				 List prgmlocationsList = progInfoBean.getProgramLocationList();
				 Iterator prgmLocIter = prgmlocationsList.iterator();
				 
				 while(prgmLocIter.hasNext())
				 {
					 CSCLocationInfoBean locationBean = (CSCLocationInfoBean)prgmLocIter.next();
					 String locationId = locationBean.getLocationId();
					 
					 Iterator selectedIdsIter = selectedProgLocIdsList.iterator();
					 
					 while(selectedIdsIter.hasNext())
					 {
						 String selProgLocId = (String)selectedIdsIter.next();
						 
						 int separatorIndex = selProgLocId.indexOf(ID_SEPARATOR);
						 String selProgramId = selProgLocId.substring(0, separatorIndex);
						 String selLocId = selProgLocId.substring(separatorIndex+1); 
						 
						 if((selProgramId.equals(programId)) && (selLocId.equals(locationId)))
						 {
							 CSCServiceProviderProgLocBean spProgLocBean = new CSCServiceProviderProgLocBean();
							 
							 spProgLocBean.setServiceProviderId(spProgBean.getServiceProviderId());
							 spProgLocBean.setServiceProviderName(spProgBean.getServiceProviderName());
							 spProgLocBean.setReferralTypeNum(progInfoBean.getReferralTypeNum());
							 
							 RefTypeCodeNRefTypeNumNRefIdValue refTypeObj = (RefTypeCodeNRefTypeNumNRefIdValue)selRefTypesMap.get(progInfoBean.getReferralTypeCd());
							 spProgLocBean.setReferralTypeCd(refTypeObj.getRefTypeCd());
							 spProgLocBean.setReferralTypeDesc(refTypeObj.getRefTypeDesc());
							 
							 spProgLocBean.setProgramId(programId);
							 spProgLocBean.setProgramIdentifier(progInfoBean.getProgramIdentifier());
							 spProgLocBean.setProgramName(progInfoBean.getProgramName());
							 spProgLocBean.setCstsCode(progInfoBean.getCstsCode());
							 spProgLocBean.setLanguagesOffered(progInfoBean.getLanguagesOffered());
							 spProgLocBean.setSexSpecificDesc(progInfoBean.getSexSpecificDesc());
							 spProgLocBean.setContractProgramDesc(progInfoBean.getContractProgramDesc());
							 ArrayList programLocationsList = new ArrayList();
							 programLocationsList.add(locationBean);
							 spProgLocBean.setProgramLocationsList(programLocationsList);
							 
							 spProgLocBean.setScheduleDateAsStr("");
							 spProgLocBean.setScheduleTimeDesc("");
							 
							 if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT))
							 {
								 spProgLocBean.setScheduleDateAsStr(progRefForm.getScheduledDateAsStr());
								 spProgLocBean.setScheduleTimeDesc(progRefForm.getScheduledTime());
							 }
							 selectedSpProgLocList.add(spProgLocBean);
						 }
					 }
				 }
			}
		}
		return selectedSpProgLocList;
	}
	
	
	
	public static UpdateProgramReferralsEvent getUpdateProgramReferralsEvent(CSCProgRefForm progRefForm)
	{
		UpdateProgramReferralsEvent updateProgramReferralsEvent = (UpdateProgramReferralsEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.UPDATEPROGRAMREFERRALS);
		ArrayList saveReferralsList = new ArrayList();
		
		Map refTypeCdNPgmRefIdMap = progRefForm.getReferralTypeCdNPgmRefIdMap();
		
		List selectedProgLocList = progRefForm.getSelectedServiceProviderProgLocList();
		Iterator iter = selectedProgLocList.iterator();
		
		while(iter.hasNext())
		{
			SaveProgramReferralsEvent saveEvent = (SaveProgramReferralsEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.SAVEPROGRAMREFERRALS);
			
			CSCServiceProviderProgLocBean spProgLocBean = (CSCServiceProviderProgLocBean)iter.next();
			
			String refTypeCd = spProgLocBean.getReferralTypeCd();
			
			saveEvent.setProgramReferralId((String)refTypeCdNPgmRefIdMap.get(refTypeCd));
			saveEvent.setReferralDate( DateUtil.stringToDate( progRefForm.getReferralDateAsStr(), DateUtil.DATE_FMT_1 ));
			List progLocList = spProgLocBean.getProgramLocationsList();
			if(progLocList.size()==1)
			{
				CSCLocationInfoBean locInfoBean = (CSCLocationInfoBean)progLocList.get(0);
				saveEvent.setProgramId(spProgLocBean.getProgramId());
				saveEvent.setLocationId(locInfoBean.getLocationId());
			}
			
			if(progRefForm.isScheduleDateTimeSelected())
			{
				Date scheduledDateTime = get24HourDateTime(spProgLocBean.getScheduleDate(),spProgLocBean.getScheduleTimeDesc());
				saveEvent.setScheduleDate(scheduledDateTime);
			}
			
			saveReferralsList.add(saveEvent);
		}
		
		if(progRefForm.isUserEnteredServiceProvider())
		{
			SaveProgramReferralsEvent saveRefEvent = (SaveProgramReferralsEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.SAVEPROGRAMREFERRALS);
			
			String refTypeCd = progRefForm.getUserEnteredServiceProviderRefTypeCd();
			
			saveRefEvent.setProgramReferralId((String)refTypeCdNPgmRefIdMap.get(refTypeCd));
			
			saveRefEvent.setNewServiceProviderName(progRefForm.getUserEnteredServiceProviderName());
			saveRefEvent.setNewServiceProviderPhone(progRefForm.getUserEnteredSPPhone().getPhoneNumber());
			saveRefEvent.setNewServiceProviderFax(progRefForm.getUserEnteredSPFax().getPhoneNumber());
			saveRefEvent.setReferralDate( DateUtil.stringToDate( progRefForm.getReferralDateAsStr(), DateUtil.DATE_FMT_1 ));
			
			if(progRefForm.isScheduleDateTimeSelected())
			{
				Date scheduledDateTime = get24HourDateTime(progRefForm.getUserEnteredScheduledDate(),progRefForm.getUserEnteredScheduledTime());
				saveRefEvent.setScheduleDate(scheduledDateTime);
			}
			
			saveReferralsList.add(saveRefEvent);
		}
		
		updateProgramReferralsEvent.setReferralsList(saveReferralsList);
		return updateProgramReferralsEvent;
	}
	
	public static ReferProgramReferralEvent getReferProgRefEvent(CSCProgRefForm progRefForm)
	{
		ReferProgramReferralEvent reqEvent =new ReferProgramReferralEvent();
		reqEvent.setProgramReferralId(progRefForm.getProgRefId());
		
		CSCServiceProviderProgLocBean spProgLocBean = progRefForm.getReferralProgramLocBean();
		reqEvent.setProgramId(spProgLocBean.getProgramId());
		
		CSCLocationInfoBean locBean = spProgLocBean.getProgramLocationBean();
		reqEvent.setLocationId(locBean.getLocationId());
		
		Date scheduledDate = DateUtil.stringToDate(progRefForm.getScheduledDateAsStr(), DateUtil.DATE_FMT_1);
		Date scheduledDateTime = get24HourDateTime(scheduledDate,progRefForm.getScheduledTime());
		reqEvent.setScheduleDateTime(scheduledDateTime);
		
		reqEvent.setSubmitComments(progRefForm.getSubmitComments());
		
		return reqEvent;
	}
	
	
	public static SubmitProgramReferralEvent getSubmitProgRefEvent(CSCProgRefForm progRefForm)
	{
		SubmitProgramReferralEvent reqEvent = (SubmitProgramReferralEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.SUBMITPROGRAMREFERRAL);
		reqEvent.setProgramReferralId(progRefForm.getProgRefId());
		reqEvent.setReferralDate( progRefForm.getReferralDate() );
		reqEvent.setProgramBeginDate(progRefForm.getProgramBeginDate());
		
		boolean isContractPgm = progRefForm.isContractProgram();
		reqEvent.setTracerNumber(null);
		if(isContractPgm)
		{
			reqEvent.setContractProgram(progRefForm.isContractProgram());
			reqEvent.setTracerNumber(progRefForm.getTracerNum());
		}
		
		reqEvent.setPlacementReasonCd(progRefForm.getReasonForPlacementId());
		
		try
		{
			reqEvent.setConfinementYears(Integer.parseInt(progRefForm.getConfinementLengthYears()));
			reqEvent.setConfinementMonths(Integer.parseInt(progRefForm.getConfinementLengthMonths()));
			reqEvent.setConfinementDays(Integer.parseInt(progRefForm.getConfinementLengthDays()));
		}
		catch(Exception ex)
		{
			reqEvent.setConfinementYears(0);
			reqEvent.setConfinementMonths(0);
			reqEvent.setConfinementDays(0);
		}
		
		reqEvent.setSubmitComments(progRefForm.getSubmitComments());
		
		return reqEvent;
	}
	
	
	public static DeleteProgramReferralEvent getDeleteProgRefEvent(CSCProgRefForm progRefForm)
	{
		DeleteProgramReferralEvent reqEvent = (DeleteProgramReferralEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.DELETEPROGRAMREFERRAL);
		reqEvent.setProgramReferralId(progRefForm.getProgRefId());
		
		return reqEvent;
	}	
	
	
	public static ExitProgramReferralEvent getExitProgRefEvent(CSCProgRefForm progRefForm)
	{
		ExitProgramReferralEvent reqEvent = new ExitProgramReferralEvent();
		
		reqEvent.setProgramReferralId(progRefForm.getProgRefId());
		
		reqEvent.setProgramEndDate(progRefForm.getProgramEndDate());
		reqEvent.setDischargeReasonCd(progRefForm.getReasonForDischargeId());
		
		reqEvent.setExitComments(progRefForm.getExitComments());
		
		return reqEvent;
	}	
		
	
	public static ChangeReferralStatusEvent getChangeReferralStatusEvent(CSCProgRefForm progRefForm)
	{
		ChangeReferralStatusEvent reqEvent = (ChangeReferralStatusEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.CHANGEREFERRALSTATUS);
		reqEvent.setProgramReferralId(progRefForm.getProgRefId());
		
		if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_REMOVEENTRY))
		{
			reqEvent.setRemoveEntry(true);
			reqEvent.setRemoveExit(false);
		}
		else
		if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_REMOVEEXIT))
		{
			reqEvent.setRemoveExit(true);
			reqEvent.setRemoveEntry(false);
		}
		return reqEvent;
	}
	
	
	
	public static UpdateProgramReferralEvent getUpdateProgRefEvent(CSCProgRefForm progRefForm)
	{
		UpdateProgramReferralEvent reqEvent = (UpdateProgramReferralEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.UPDATEPROGRAMREFERRAL);
		reqEvent.setProgramReferralId(progRefForm.getProgRefId());
		
		if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_UPDATE_SUBMIT))
		{
			reqEvent.setUpdateSubmitRef(true);
			
			reqEvent.setProgramBeginDate(progRefForm.getProgramBeginDate());
			
			boolean isContractPgm = progRefForm.isContractProgram();
			reqEvent.setTracerNumber(null);
			if(isContractPgm)
			{
				reqEvent.setContractProgram(progRefForm.isContractProgram());
				reqEvent.setTracerNumber(progRefForm.getTracerNum());
			}
			
			reqEvent.setPlacementReasonCd(progRefForm.getReasonForPlacementId());
			
			try
			{
				reqEvent.setConfinementYears(Integer.parseInt(progRefForm.getConfinementLengthYears()));
				reqEvent.setConfinementMonths(Integer.parseInt(progRefForm.getConfinementLengthMonths()));
				reqEvent.setConfinementDays(Integer.parseInt(progRefForm.getConfinementLengthDays()));
			}
			catch(Exception ex)
			{
				reqEvent.setConfinementYears(0);
				reqEvent.setConfinementMonths(0);
				reqEvent.setConfinementDays(0);
			}
		}
		else
		if(progRefForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_UPDATE_EXIT))
		{
			reqEvent.setUpdateSubmitRef(false);
			
			reqEvent.setProgramEndDate(progRefForm.getProgramEndDate());
			reqEvent.setDischargeReasonCd(progRefForm.getReasonForDischargeId());
		}
		return reqEvent;
	}
	
	
	
	public static Date get24HourDateTime(Date aScheduleDate,String aScheduleTime)
	{
		Date scheduledDateTime = null;
		
		if(aScheduleDate!=null)
		{
			String scheduledTime = aScheduleTime.trim();
			
//			set default date to 12 AM, if not entered
			if(StringUtil.isEmpty(scheduledTime))
			{
				scheduledTime = "12:00 AM";
			}
			
			if(scheduledTime.endsWith("PM"))
			{
				scheduledTime = scheduledTime.substring(0, scheduledTime.length()-2).trim();
				
				String[] timeStrArr = scheduledTime.split(":");
				
				int hour = Integer.parseInt(timeStrArr[0]);
				if(hour < 12)
				{
					hour = hour + 12;
				}
				
				String shour = Integer.toString(hour);
				if(shour.length()<2)
				{
					shour = ZERO + shour;
				}
				scheduledTime = shour + ":" + timeStrArr[1];
			}
			else
			{
				scheduledTime = scheduledTime.substring(0, scheduledTime.length()-2).trim();
				
				String[] timeStrArr = scheduledTime.split(":");
				
				int hour = Integer.parseInt(timeStrArr[0]);
				if(hour >= 12)
				{
					hour = hour - 12;
				}
				
				String shour = Integer.toString(hour);
				if(shour.length()<2)
				{
					shour = ZERO + shour;
				}
				scheduledTime = shour + ":" + timeStrArr[1];
			}
			scheduledDateTime = convertToDateTime(DateUtil.dateToString(aScheduleDate, "yyyyMMdd"), scheduledTime);
		}
		return scheduledDateTime;
	}
	
	
	
	 public static Date convertToDateTime(String strDate, String strTime)
	    {

	        Date date = null;
	        try
	        {
	            int year = new Integer(strDate.substring(0, 4)).intValue();
	            int month = new Integer(strDate.substring(4, 6)).intValue();
	            int day = new Integer(strDate.substring(6, 8)).intValue();
	            int hours = new Integer(strTime.substring(0, 2)).intValue();
	            int minutes = new Integer(strTime.substring(3, 5)).intValue();
	            int seconds = 0;
	            Calendar calendar = Calendar.getInstance();
	            calendar.setLenient(false);
	            calendar.set(year, month - 1, day, hours, minutes, seconds);
	            date = calendar.getTime();
	        }
	        catch (NumberFormatException e)
	        {
	        }
	        catch (IndexOutOfBoundsException e)
	        {
	        }
	        catch (IllegalArgumentException e)
	        {
	            //This exception is thrown if calendar was created with an invalid
	            // date.
	        }
	        return date;
	    }
	 
	 public static void clearUserEnteredSP(CSCProgRefForm progRefForm)
	{
		progRefForm.setUserEnteredServiceProvider(false);
		progRefForm.setUserEnteredServiceProviderName("");
		progRefForm.setUserEnteredServiceProviderRefTypeCd("");
		
		progRefForm.setUserEnteredSPPhone(new PhoneNumber(""));
		progRefForm.setUserEnteredSPFax(new PhoneNumber(""));
		
		progRefForm.setUserEnteredScheduledDateAsStr("");
		progRefForm.setUserEnteredScheduledTime("");
	}
	 
	 
	
 	public static void setSuperviseeHeaderInfo(CaseAssignmentForm caseAssignForm, CSCProgRefForm progReferralForm, SuperviseeHeaderForm superviseeHeaderForm )
 	{
 		clearCaseAssignmentFormFields(caseAssignForm);
 		
 		GetSuperviseeCaseEvent superviseeCaseEvent =(GetSuperviseeCaseEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.GETSUPERVISEECASE);
		superviseeCaseEvent.setCriminalCaseId(progReferralForm.getCriminalCaseId());
		SuperviseeCaseResponseEvent superviseeCaseRespEvt = (SuperviseeCaseResponseEvent)MessageUtil.postRequest(superviseeCaseEvent, SuperviseeCaseResponseEvent.class);
		if(superviseeCaseRespEvt!=null)
		{
			caseAssignForm.setSuperviseeNameStr(superviseeHeaderForm.getSuperviseeNameDesc());
			caseAssignForm.setDefendantId(superviseeHeaderForm.getSuperviseeId());
			caseAssignForm.setOfficerNameStr(superviseeHeaderForm.getOfficerNameDesc());
			caseAssignForm.setLevelOfSupervision(superviseeHeaderForm.getLOSDesc());
			caseAssignForm.setProgramUnitName(superviseeHeaderForm.getProgramUnitDesc());
			caseAssignForm.setOfficerPositionId(superviseeHeaderForm.getOfficerPositionId());
		 	
			caseAssignForm.setOffenseDesc(superviseeCaseRespEvt.getOffense());
			caseAssignForm.setCdi(superviseeCaseRespEvt.getCdi());
			caseAssignForm.setCaseNum(superviseeCaseRespEvt.getCaseNumber());
			
			String completeCourtNumber = superviseeCaseRespEvt.getCourtNumber();
			if(completeCourtNumber != null)
			{
				completeCourtNumber = completeCourtNumber.trim();
				String courtNum = completeCourtNumber.substring(completeCourtNumber.indexOf(" ")+1); 
				caseAssignForm.setCourtNumber(courtNum);
			}
			else
			{
				caseAssignForm.setCourtNumber("");
			}
		}
	 }
 	
 	
 	
 	private static void clearCaseAssignmentFormFields(CaseAssignmentForm caseAssignmentForm)
 	{
 		caseAssignmentForm.setSuperviseeNameStr("");
 		caseAssignmentForm.setDefendantId("");
 		caseAssignmentForm.setOfficerNameStr("");
 		caseAssignmentForm.setLevelOfSupervision("");
 		caseAssignmentForm.setProgramUnitName("");
 		caseAssignmentForm.setOfficerPositionId("");
 		
 		caseAssignmentForm.setOffenseDesc("");
 		caseAssignmentForm.setCdi("");
 		caseAssignmentForm.setCaseNum("");
		
 		caseAssignmentForm.setCourtNumber("");
 	}
 	
 	
 	
 	public static void populateOrderConditions(CSCProgRefForm progReferralForm)
 	{
		GetSupervisionOrderConditionEvent orderConditionsEvent = (GetSupervisionOrderConditionEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.GETSUPERVISIONORDERCONDITION);
		orderConditionsEvent.setCriminalCaseId(progReferralForm.getCriminalCaseId());
		List conditionsList = MessageUtil.postRequestListFilter(orderConditionsEvent, SupervisionOrderConditionResponseEvent.class);
		 // sort it by sequence number
        Collections.sort((List) conditionsList, SupervisionOrderConditionResponseEvent.SeqNumComparator);
		if((conditionsList!= null ) && (conditionsList.size() > 0))
		{
			for(int i = 0; i < conditionsList.size(); i++)
			{
				SupervisionOrderConditionResponseEvent respEvt = (SupervisionOrderConditionResponseEvent)conditionsList.get(i);
				SupervisionOrderConditionBean conditionBean = CSCProgRefUIHelper.convertConditionResponseEventToConditionBean(respEvt);
				progReferralForm.getConditionsList().add(conditionBean);
			}
		}
 	}
 	
 	
 	
 	public static void convertResponseEvtToReferralFormBean(CSCProgRefForm progReferralForm, List referralFormsList)
 	{
 		ArrayList referralFormBeanList = new ArrayList();
 		progReferralForm.setReferralFormsBeanList(referralFormBeanList);
 		
 		Iterator iter = referralFormsList.iterator();
 		
 		while(iter.hasNext())
 		{
 			ReferralFormBean referralFormBean = new ReferralFormBean();
 			
 			ReferralFormResponseEvent responseEvt = (ReferralFormResponseEvent)iter.next();
 			referralFormBean.setReferralFormId(responseEvt.getReferralFormId());
 			referralFormBean.setReferralFormName(responseEvt.getReferralFormTitle());
 			
 			referralFormBeanList.add(referralFormBean);
 		}
 	}
 	
 	
 	public static void convertResponseEvtToReferralFormBean(ReferralTypeBean referralTypeBean, List referralFormsList)
 	{
 		ArrayList referralFormBeanList = new ArrayList();
 		referralTypeBean.setReferralFormsList(referralFormBeanList);
 		
 		Iterator iter = referralFormsList.iterator();
 		
 		while(iter.hasNext())
 		{
 			ReferralFormBean referralFormBean = new ReferralFormBean();
 			
 			ReferralFormResponseEvent responseEvt = (ReferralFormResponseEvent)iter.next();
 			referralFormBean.setReferralFormId(responseEvt.getReferralFormId());
 			referralFormBean.setReferralFormName(responseEvt.getReferralFormTitle());
 			
 			referralFormBeanList.add(referralFormBean);
 		}
 	}
 	
	public static void populateReferralFormFields(CSCProgRefForm progRefForm, List fieldRespEvtList)
	{
		ArrayList formFieldsList = new ArrayList();
		progRefForm.setReferralformFieldsList(formFieldsList);
		
		Iterator fieldIter = fieldRespEvtList.iterator();
		while(fieldIter.hasNext())
		{
		    ReferralFormFieldResponseEvent responseEvt = (ReferralFormFieldResponseEvent)fieldIter.next();
		    
		    ReferralFormField refFormFieldQues = new ReferralFormField();
		    
		    refFormFieldQues.setFieldId(responseEvt.getFieldId());
		    refFormFieldQues.setFieldLabel(responseEvt.getFieldLabel());
		    
		    refFormFieldQues.setRowSequence(responseEvt.getRowSequence());
		    refFormFieldQues.setColumnSequence(responseEvt.getColumnSequence());
		    
		    refFormFieldQues.setSummaryRowSeq(responseEvt.getSummaryRowSeq());
		    refFormFieldQues.setSummaryColSeq(responseEvt.getSummaryColSeq());
		    
		    refFormFieldQues.setQuestionAlign(LEFT_ALIGN);
		    refFormFieldQues.setQuestionColumnWidth(responseEvt.getColumnWidth());
		    refFormFieldQues.setSummaryColumnWidth(responseEvt.getSummaryColumnWidth());
		    
		    refFormFieldQues.setRequired(responseEvt.isRequired());
		    if(refFormFieldQues.isRequired())
		    {
		    	refFormFieldQues.setRequiredImageShown(true);
		    }
		    
		    refFormFieldQues.setResponseNextLine(responseEvt.isResponseNewLine());
		    refFormFieldQues.setEachResponseNewLine(responseEvt.isEachResponseNewLine());
		    
		    refFormFieldQues.setFormatKey(responseEvt.getFormatKey());
		    refFormFieldQues.setChildFieldId(responseEvt.getChildFieldId());
		    
		    try
			{
				refFormFieldQues.setUiControlSize(Integer.parseInt(responseEvt.getUiControlSize()));
									
			}
			catch(Exception ex)
			{
//				Exception parsing the String
			}
		    try
			{
		    	refFormFieldQues.setTextLength(Integer.parseInt(responseEvt.getTextLength()));
			}
			catch(Exception ex)
			{
//				Exception parsing the String
			}
			try
			{
				refFormFieldQues.setMinValue(Integer.parseInt(responseEvt.getMinValue()));
									
			}
			catch(Exception ex)
			{
//				Exception parsing the String
			}
			try
			{
				refFormFieldQues.setMaxValue(Integer.parseInt(responseEvt.getMaxValue()));
									
			}
			catch(Exception ex)
			{
//				Exception parsing the String
			}
			try
			{
				refFormFieldQues.setNoOfResponsesInARow(Integer.parseInt(responseEvt.getNoOfRespInARow()));
									
			}
			catch(Exception ex)
			{
//				Exception parsing the String
			}
			
			refFormFieldQues.setRangeValidationExists(true);
			if((refFormFieldQues.getMinValue()==0) && (refFormFieldQues.getMaxValue()==0))
			{
				refFormFieldQues.setRangeValidationExists(false);
			}
			
			refFormFieldQues.setUiControlType(responseEvt.getResponseUIControlType());
			
			if((refFormFieldQues.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_TEXT)) ||
					(refFormFieldQues.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_TEXTBOX))||
	 				(refFormFieldQues.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_MCE_EDIT_TEXTBOX)))
			{
				refFormFieldQues.setResponseDataType(responseEvt.getResponseDataType());
			}
			
			setVaildationElementType(refFormFieldQues);
			
			Collection possibleResponses = responseEvt.getPossibleResponseEvents();

			if (possibleResponses != null && possibleResponses.size() > 0)
			{
				ArrayList myResponses = new ArrayList();
				
				Iterator iResponses = possibleResponses.iterator();
				while (iResponses.hasNext())
				{
					ReferralFormOptionResponseEvent optionEvent = (ReferralFormOptionResponseEvent) iResponses.next();
					if (optionEvent != null)
					{
						RefFormFieldOption myResponse = new RefFormFieldOption();
						
						myResponse.setOptionId(optionEvent.getOptionId());
						myResponse.setOptionDisplayText(optionEvent.getOptionLabel());
						myResponse.setOptionValue("");
						if(optionEvent.getOptionValue()!=null)
						{
							myResponse.setOptionValue(optionEvent.getOptionValue());
						}
						myResponse.setDefault(optionEvent.isDefault());
						myResponse.setDisplaySequence(optionEvent.getDisplaySequence());
						myResponse.setOptionEdit(optionEvent.isOptionEdit());
						if(myResponse.isOptionEdit())
						{
							myResponse.setUserEnteredOptionName(optionEvent.getUserEnteredOptionName());
						}
						
						myResponses.add(myResponse);
					}
				}
				Collections.sort(myResponses);
				refFormFieldQues.setResponseChoices(myResponses);
			}
			
			refFormFieldQues.setResponseId(responseEvt.getSelectedResponseId());
			refFormFieldQues.setResponseText(responseEvt.getSelectedResponseText());
			if(refFormFieldQues.getResponseDataType().equalsIgnoreCase(ReferralFormField.RESPONSE_VAR_TYPE_PHONE))
			{
				refFormFieldQues.setResponsePhoneNum(new PhoneNumber(refFormFieldQues.getResponseText()));
			}
			
			List selOptionsList = responseEvt.getSelectedResponseIdsList();
			if(selOptionsList!=null && selOptionsList.size()>0)
			{
				String[] selRespArr = new String[selOptionsList.size()];
				Iterator respIter = selOptionsList.iterator();
				int index=0;
				while(respIter.hasNext())
				{
					selRespArr[index++]= (String)respIter.next();
				}
				refFormFieldQues.setSelectedResponseIdsArr(selRespArr);
			}
		    
			formFieldsList.add(refFormFieldQues);
		}
	}
	
	
	private static void setVaildationElementType(ReferralFormField referalFormField)
	{
		String responseDataType = referalFormField.getResponseDataType();

		if(!StringUtil.isEmpty(responseDataType))
		{
			if(responseDataType.equalsIgnoreCase(ReferralFormField.RESPONSE_VAR_TYPE_ALPHA_NUMERIC))
			{
				referalFormField.setValidationElementType(UIConstants.FORMAT_PRESENTATION_TYPE_ALPHANUMERIC);
				referalFormField.setValidationElementDetailType(UIConstants.DISPLAY_PRESENTATION_TYPE_ALPHANUMERIC_NOSYMBOLS);
			}
			else
			if(responseDataType.equalsIgnoreCase(ReferralFormField.RESPONSE_VAR_TYPE_ALPHA_NUMERIC_WTH_SYMBOLS))
			{
				referalFormField.setValidationElementType(UIConstants.FORMAT_PRESENTATION_TYPE_ALPHANUMERIC);
				referalFormField.setValidationElementDetailType(UIConstants.DISPLAY_PRESENTATION_TYPE_ALPHANUMERIC_SYMBOLS);
			}
			else
			if(responseDataType.equalsIgnoreCase(ReferralFormField.RESPONSE_VAR_TYPE_INTEGER))
			{
				referalFormField.setValidationElementType(UIConstants.FORMAT_PRESENTATION_TYPE_INTEGER);
				referalFormField.setValidationElementDetailType("");
			}
			else
			if(responseDataType.equalsIgnoreCase(ReferralFormField.RESPONSE_VAR_TYPE_NUMERIC))
			{
				referalFormField.setValidationElementType(UIConstants.FORMAT_PRESENTATION_TYPE_NUMERIC);
				referalFormField.setValidationElementDetailType("");
			}
			else
			if(responseDataType.equalsIgnoreCase(ReferralFormField.RESPONSE_VAR_TYPE_DATE))
			{
				referalFormField.setValidationElementType(UIConstants.FORMAT_PRESENTATION_TYPE_DATE);
				referalFormField.setValidationElementDetailType("");
			}
			else
			if(responseDataType.equalsIgnoreCase(ReferralFormField.RESPONSE_VAR_TYPE_TIME))
			{
				referalFormField.setValidationElementType(UIConstants.FORMAT_PRESENTATION_TYPE_TIME);
				referalFormField.setValidationElementDetailType("");
			}
			else
			if(responseDataType.equalsIgnoreCase(ReferralFormField.RESPONSE_VAR_TYPE_PHONE))
			{
				referalFormField.setValidationElementType(UIConstants.FORMAT_PRESENTATION_TYPE_PHONE);
				referalFormField.setValidationElementDetailType("");
			}
			if(responseDataType.equalsIgnoreCase(ReferralFormField.RESPONSE_VAR_TYPE_CURRENCY))
			{
				referalFormField.setValidationElementType(UIConstants.FORMAT_PRESENTATION_TYPE_CURRENCY);
				referalFormField.setValidationElementDetailType("");
			}
		}
	}
	
	
 	
 	public static SaveReferralFormEvent getSaveReferralFormEvent(CSCProgRefForm progRefForm)
 	{
 		Map parentIdSelectedMap = new HashMap();
 		Map childFldIdPrntIdMap = new HashMap();
 		
 		SaveReferralFormEvent saveEvent = new SaveReferralFormEvent();
 		
 		saveEvent.setProgramReferralId(progRefForm.getProgRefId());
 		saveEvent.setReferralFormId(progRefForm.getReferralFormId());
 		
 		ArrayList fieldDataList = new ArrayList();
 		saveEvent.setFieldDataList(fieldDataList);
 		
 		List fieldsList = progRefForm.getReferralformFieldsList();
 		Iterator fieldIter = fieldsList.iterator();
 		while(fieldIter.hasNext())
 		{
 			ReferralFormFieldValue fieldValue = new ReferralFormFieldValue();
 			fieldDataList.add(fieldValue);
 			
 			ReferralFormField formField = (ReferralFormField)fieldIter.next();
 			
 			fieldValue.setFieldId(formField.getFieldId());
 			
 			if(formField.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_CHECKBOX))
 			{
 				fieldValue.setMultiCheckBoxField(true);
 				List selResponseIdsList = new ArrayList();
 				fieldValue.setResponseIdsList(selResponseIdsList);
 				
 				String[] responseIdsArr = formField.getSelectedResponseIdsArr();
 				if(responseIdsArr!=null)
 				{
 					for(int index=0; index < responseIdsArr.length; index++)
 					{
 						selResponseIdsList.add(responseIdsArr[index]);
 					}
 				}
		 		
 				Collection responseoptions = formField.getResponseChoices();
 				Iterator optionIter = responseoptions.iterator();
 				while(optionIter.hasNext())
 				{
 					RefFormFieldOption option = (RefFormFieldOption)optionIter.next(); 
 					if(option.isOptionEdit())
 					{
 						String userEntName = option.getUserEnteredOptionName();
 						if(!StringUtil.isEmpty(userEntName))
 						{
 							fieldValue.setUserEnteredOptionName(userEntName);
 							if(!selResponseIdsList.contains(option.getOptionId()))
 							{
 								selResponseIdsList.add(option.getOptionId());
 							}
 						}
 						else
 						{
 							selResponseIdsList.remove(option.getOptionId());
 						}
 					}
 				}
 			}
 			else
 			if((formField.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_TEXT)) ||
 				(formField.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_TEXTBOX)) ||
 				(formField.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_MCE_EDIT_TEXTBOX)))
 			{
 				if(formField.getResponseDataType().equalsIgnoreCase(ReferralFormField.RESPONSE_VAR_TYPE_PHONE))
 				{
 					formField.setResponseText(formField.getResponsePhoneNum().getPhoneNumber());
 				}
 				
 				if(childFldIdPrntIdMap.containsKey(formField.getFieldId()))
 				{
 					String parentFldId = (String)childFldIdPrntIdMap.get(formField.getFieldId());
 					boolean isParentSelected = (Boolean)parentIdSelectedMap.get(parentFldId);
 					if(isParentSelected)
 					{
 						fieldValue.setResponseText(formField.getResponseText());
 					}
 					else
 					{
 						fieldValue.setResponseText(null);
 					}
 				}
 				else
 				{
 					fieldValue.setResponseText(formField.getResponseText());
 				}
 			}
 			else
 			if(formField.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_SINGLE_CHECKBOX))
 			{
 				String responseId = formField.getResponseId();
 				if(responseId!=null && (responseId.equalsIgnoreCase(CSAdministerProgramReferralsConstants.UI_SINGLE_CHECKBOX_CHK_VAL)))
 				{
 					fieldValue.setResponseId(responseId);
 					
 					if(formField.getChildFieldId()!=null)
 	 				{
 						childFldIdPrntIdMap.put(formField.getChildFieldId(), formField.getFieldId());
 	 					parentIdSelectedMap.put(formField.getFieldId(), true);
 	 				}
 				}
 				else
 				{
 					if(formField.getChildFieldId()!=null)
 	 				{
 						childFldIdPrntIdMap.put(formField.getChildFieldId(), formField.getFieldId());
 	 					parentIdSelectedMap.put(formField.getFieldId(), false);
 	 				}
 				}
 			}
 			else
			if((formField.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_RADIO)) ||
	 				(formField.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_SELECT)))
 			{
 				String selOptionId = formField.getResponseId();
 				if(selOptionId!=null)
 				{
	 				Collection responseoptions = formField.getResponseChoices();
	 				Iterator optionIter = responseoptions.iterator();
	 				while(optionIter.hasNext())
	 				{
	 					RefFormFieldOption option = (RefFormFieldOption)optionIter.next(); 
	 					if(option.getOptionId().equalsIgnoreCase(selOptionId) && (!option.getOptionValue().trim().equalsIgnoreCase("")))
	 					{
	 						fieldValue.setResponseId(option.getOptionId());
	 						if(option.isOptionEdit())
	 						{
	 							if(!StringUtil.isEmpty(option.getUserEnteredOptionName()))
	 							{
	 								fieldValue.setUserEnteredOptionName(option.getUserEnteredOptionName());
	 							}
	 							else
	 							{
	 								fieldValue.setResponseId(null);
	 							}
	 						}
	 						break;
	 					}
	 				}
 				}
 			}
		}
 		return saveEvent;
 	}
 	
 	
 	
 	public static void sortRefFormFields(List fieldsList)
	{
		if (fieldsList == null || fieldsList.size() <= 0)
			return;
		
		RefFormFieldComparator refFormFieldComp = new RefFormFieldComparator();
		Collections.sort((List)fieldsList, refFormFieldComp);
	}
 	
 	
 	public static int maxColumnSizeForRefFormFields(Collection fieldsList, String type) 
 	{
		int maxColumnSize = 0;
		if (fieldsList == null || fieldsList.size() <= 0)
			return 0;
		
			
			ReferralFormField currentField = null;
			Iterator i = fieldsList.iterator();
			while (i.hasNext()) {
				currentField = (ReferralFormField) i.next();
				if(type.equalsIgnoreCase(UIConstants.INPUT))
				{
				if (currentField.getColumnSequence() > maxColumnSize)
					maxColumnSize = currentField.getColumnSequence();
				}
				else
					if(type.equalsIgnoreCase(UIConstants.SUMMARY))
					{
						if (currentField.getSummaryColSeq() > maxColumnSize)
							maxColumnSize = currentField.getSummaryColSeq();
					}
		}
		return maxColumnSize;
	} 
	
}
