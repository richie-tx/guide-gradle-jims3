package ui.supervision.cscdcalendar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import naming.CSEventControllerServiceNames;
import naming.PDCodeTableConstants;
import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.InOutActivityBean;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;
import ui.supervision.cscdcalendar.form.CSCalendarOVForm;
import ui.supervision.cscdcalendar.form.CSCalendarOtherForm;
import ui.supervision.cscdcalendar.form.CSEventsSearchForm;
import messaging.administercaseload.reply.CaseAssignInOutResponseEvent;
import messaging.cscdcalendar.GetCSEventsReportEvent;
import messaging.cscdcalendar.GetCSFieldVisitIdEvent;
import messaging.cscdcalendar.GetCSOfficeVisitsEvent;
import messaging.cscdcalendar.GetCSOtherEventsEvent;
import messaging.cscdcalendar.reply.CSEventsReportReponseEvent;
import messaging.cscdcalendar.reply.CSFVItineraryResponseEvent;
import messaging.cscdcalendar.reply.CSFieldVisitIdResponseEvent;
import messaging.cscdcalendar.reply.CSGroupOfficeVisitResponseEvent;
import messaging.cscdcalendar.reply.CSOfficeVisitResponseEvent;
import messaging.cscdcalendar.reply.CSOtherResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;


/**
 * 
 * @author cc_bjangay
 *
 */
public class CSEventsReportUIHelper 
{
	
	/**
	 * 
	 * @param csEventsSearchForm
	 * @return
	 */
	public static GetCSEventsReportEvent getCSEventsReportEvent(CSEventsSearchForm csEventsSearchForm)
	{
		GetCSEventsReportEvent requestEvent = (GetCSEventsReportEvent)EventFactory.getInstance(CSEventControllerServiceNames.GETCSEVENTSREPORT);
		
		requestEvent.setPositionId(csEventsSearchForm.getPositionId());
		requestEvent.setCsEventCategory(csEventsSearchForm.getCalendarCategory());
		Date startDate = DateUtil.stringToDate(csEventsSearchForm.getStartDateStr(), DateUtil.DATE_FMT_1);
		requestEvent.setStartDate(startDate);
		Date endDate = DateUtil.stringToDate(csEventsSearchForm.getEndDateStr(), DateUtil.DATE_FMT_1);
		requestEvent.setEndDate(endDate);
		
		
		return requestEvent;
		
	}//end of getCSEventsReportEvent()
	
	
	/**
	 * 
	 * @param csEventsSearchForm
	 * @param responseEvtsList
	 */
	public static void convertResponseEventToBean(CSEventsSearchForm csEventsSearchForm, List responseEvtsList)
	{
		ArrayList csEventsReportList = new ArrayList();
		
		List fvOutcomeList = ComplexCodeTableHelper.getSupervisionCodes(PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.FV_OUTCOME);
		List ovOutcomeList = ComplexCodeTableHelper.getSupervisionCodes(PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.OV_OUTCOME);
		fvOutcomeList.addAll(ovOutcomeList);
		
		HashMap resultUserIdNameMap = new HashMap();		
		String count = Integer.toString(responseEvtsList.size());
		csEventsSearchForm.setCsEventsCount(count);
		
		Iterator reponseListIter = responseEvtsList.iterator();
		while(reponseListIter.hasNext())
		{
			CSEventsReportReponseEvent responseEvent = (CSEventsReportReponseEvent)reponseListIter.next();
			
			
			CSEventsReportBean csCalReportBean = new CSEventsReportBean();
			
			csCalReportBean.setCsEventId(responseEvent.getCsEventId());
			
			csCalReportBean.setDefendantId(responseEvent.getDefendantId());
			csCalReportBean.setDefendantName(responseEvent.getDefendantName().trim());
			
			String outcomeCd = responseEvent.getOutcomeCd();
			String outcome = "";
			if(outcomeCd != null)
			{
				outcome = ComplexCodeTableHelper.getDescrBySupervisionCode(fvOutcomeList, outcomeCd);
			}
			csCalReportBean.setOutcome(outcome);
			
			String resultUserId = responseEvent.getResultUserId();
			String resultUserName = "";
			boolean isResultUserNameExist = false;
			if(resultUserId!=null && resultUserId.trim().length()>0)
			{
				if(resultUserIdNameMap.containsKey(resultUserId))
				{
					resultUserName = (String)resultUserIdNameMap.get(resultUserId);
				}
				else
				{
					resultUserName = getUserNameFromUserId(resultUserId);
					resultUserIdNameMap.put(resultUserId, resultUserName);
				}
				isResultUserNameExist = true;
			}
			csCalReportBean.setResultUserName(resultUserName);
			
			String resultPositionName = responseEvent.getResultPositionName();
			boolean isResultPositionNameExist = false;
			if(resultPositionName!=null && resultPositionName.trim().length()>0)
			{
				csCalReportBean.setResultPositionName(resultPositionName);
				isResultPositionNameExist = true;
			}
			
			String userNPositionName = ""; 
			if(isResultUserNameExist)
			{
				userNPositionName = csCalReportBean.getResultUserName();
			}
			if(isResultPositionNameExist)
			{
				userNPositionName = userNPositionName + " | " + csCalReportBean.getResultPositionName();
			}
			csCalReportBean.setResultUserNPositionName(userNPositionName);
			
			csCalReportBean.setCsEventTypeId(responseEvent.getCsEventTypeId());
			csCalReportBean.setCsEventTypeDesc(responseEvent.getCsEventTypeDesc());
			csCalReportBean.setCsEventDate(responseEvent.getCsEventDate());
			
			csEventsReportList.add(csCalReportBean);
		}
		csEventsSearchForm.setCsEventsList(csEventsReportList);
		
	}//end of convertResponseEventToBean()
	

	/**
	 * 
	 * @param csEventsSearchForm
	 * @param inOutActivityResponses
	 */
	public static void convertInOutActivityResponseEventToBean
						(CSEventsSearchForm csEventsSearchForm, 
								List<CaseAssignInOutResponseEvent> inOutActivityResponses)
	{
			//create return list
		ArrayList in_out_activity_list = new ArrayList<InOutActivityBean>();

		int num_responses = inOutActivityResponses.size();	
		String courtNum = "";
		int courtNumInt = 0;
		for (int i=0;i<num_responses;i++)
		{
			CaseAssignInOutResponseEvent in_out_response = inOutActivityResponses.get(i);
			
				//populate bean with values from response
			InOutActivityBean in_out_bean = new InOutActivityBean();			
			in_out_bean.setAssignStaffPositionId(in_out_response.getAssignStaffPositionId());
			in_out_bean.setBeginDate(DateUtil.dateToString(in_out_response.getBeginDate(), DateUtil.DATE_FMT_1));
			in_out_bean.setEndDate(DateUtil.dateToString(in_out_response.getEndDate(), DateUtil.DATE_FMT_1));
			in_out_bean.setCriminalCaseId(in_out_response.getCriminalCaseId());
			in_out_bean.setDefendantId(in_out_response.getDefendantId());
			in_out_bean.setDefendantName(in_out_response.getDefendantName());
			in_out_bean.setInOut(in_out_response.getInOut());
			in_out_bean.setCaseAssignIoId(in_out_response.getCaseAssignIoId());
			if (in_out_response.getCourtId() != null && !in_out_response.getCourtId().equalsIgnoreCase("")) {
				courtNum = in_out_response.getCourtId();
				if(courtNum.length() > 3 ) {
					String crtIds [] = courtNum.split(" ");
					if(crtIds != null && crtIds.length > 1){
						courtNum = crtIds[1];
					}
					try {
						courtNumInt = new Integer(courtNum);
						courtNum = "" + courtNumInt;
						while(courtNum.length() < 3){
							courtNum = "0" + courtNum;
						}
					}catch(NumberFormatException e){
//do nothing, court number is not integer
					}	
				}
				in_out_bean.setCourt(courtNum);
			}
//			in_out_bean.setCourt(in_out_response.getCourtId());
			in_out_bean.setSupervisionOrderId(in_out_response.getSupervisionOrderId());
			
				//add bean to return list
			in_out_activity_list.add(in_out_bean);
		}
			//set in/out activity info on form
		csEventsSearchForm.setNumInOutActivity(in_out_activity_list.size());
		csEventsSearchForm.setInOutActivity(in_out_activity_list);
	}//end of convertInOutActivityResponseEventToBean()
	
	/**
	 * 
	 * @param csEventId
	 * @return
	 */
	public static String getFieldVisitId(String csEventId)
	{
		String fieldVisitId = null;
		
		GetCSFieldVisitIdEvent reqEvt = (GetCSFieldVisitIdEvent)EventFactory.getInstance(CSEventControllerServiceNames.GETCSFIELDVISITID);
		reqEvt.setCsEventId(csEventId);
		CSFieldVisitIdResponseEvent responseEvt = (CSFieldVisitIdResponseEvent) MessageUtil.postRequest(reqEvt, CSFieldVisitIdResponseEvent.class);
		
		if(responseEvt != null)
		{
			fieldVisitId = responseEvt.getCsFieldVisitId();
		}
		
		return fieldVisitId;
		
	}// end of getFieldVisitId()
	
	
	
	/**
	 * 
	 * @param fvForm
	 */
	public static void populateFVIternary(CSCalendarFVForm fvForm)
	{
		CSFVItineraryResponseEvent itineraryRE = 
   			cscdCalendarUIUtil.getItinerary(fvForm.getPositionId(), 
   					fvForm.getEventDate());
   		
   		if(itineraryRE != null) {
   			fvForm.getCurrentItinerary().load(itineraryRE);
   			fvForm.getCurrentItinerary().setAgencyId(fvForm.getAgencyId());
   			
   	   		
   	   		List events = cscdCalendarUIUtil.getEventsForItinerary(
   	   				itineraryRE.getFvItineraryId(), fvForm.getPositionId(), fvForm.getEventDate());
   	   		
   	   	fvForm.setEventsList(events);
   		} else {
   			//copy over event date from the form
   			fvForm.getCurrentItinerary().setEventDate(fvForm.getEventDate());
   			fvForm.getCurrentItinerary().setPositionId(fvForm.getPositionId());
   			fvForm.getCurrentItinerary().setAgencyId(fvForm.getAgencyId());
   		}
   		
	}//end of populateFVIternary()
	
	
	
	/**
	 * 
	 * @param ovForm
	 */
	public static void populateOfficeVisitsForPositionId(CSCalendarOVForm ovForm)
	{
		GetCSOfficeVisitsEvent getOfficeVisits = (GetCSOfficeVisitsEvent)EventFactory.getInstance(
				CSEventControllerServiceNames.GETCSOFFICEVISITS);
		getOfficeVisits.setCurrentContext(ovForm.getContext());
		getOfficeVisits.setEventDate(ovForm.getEventDate());
		getOfficeVisits.setPositionId(ovForm.getPositionId());
		
		CompositeResponse response = MessageUtil.postRequest(getOfficeVisits);
		Collection officeVisits = MessageUtil.compositeToCollection(response, CSOfficeVisitResponseEvent.class);
		
		Collection groupOfficeVisits = MessageUtil.compositeToCollection(response, CSGroupOfficeVisitResponseEvent.class);
		
		ovForm.setOfficeVisits((List)officeVisits);
		ovForm.setGroupOfficeVisits((List)groupOfficeVisits);
		
	}//end of populateOfficeVisitsForPositionId()
	
	
	
	/**
	 * 
	 * @param otherEvtForm
	 */
	public static void populateOtherEventsForPositionId(CSCalendarOtherForm otherEvtForm)
	{
		GetCSOtherEventsEvent reqEvent = (GetCSOtherEventsEvent)EventFactory.getInstance(
				CSEventControllerServiceNames.GETCSOTHEREVENTS);
		reqEvent.setCurrentContext(otherEvtForm.getContext());
		reqEvent.setEventDate(otherEvtForm.getEventDate());
		reqEvent.setPositionId(otherEvtForm.getPositionId());
		
		CompositeResponse response = MessageUtil.postRequest(reqEvent);
			Collection otherEvents = MessageUtil.compositeToCollection(response, CSOtherResponseEvent.class);
			otherEvtForm.setEvents(otherEvents);
			
	}//end of populateOtherEventsForPositionId()
	
	
	
	/**
	 * 
	 * @param csEventsSearchForm
	 * @param fvForm
	 * @param fieldVisitId
	 */
	public static void initializeFieldVisitForm(CSEventsSearchForm csEventsSearchForm, CSCalendarFVForm fvForm, String fieldVisitId)
	{
		String selectedDefendantId = "";
		Date selectedEventDate = null;
		
		String selectedCSEventId = csEventsSearchForm.getSelectedCSEventId();
		
		Iterator iter = csEventsSearchForm.getCsEventsList().iterator();
		while(iter.hasNext())
		{
			CSEventsReportBean reportBeanObj = (CSEventsReportBean)iter.next();
			if(reportBeanObj.getCsEventId().equalsIgnoreCase(selectedCSEventId))
			{
				selectedDefendantId = reportBeanObj.getDefendantId();
				selectedEventDate = reportBeanObj.getCsEventDate();
				break;
			}
		}
		
		fvForm.setContext(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION);
		fvForm.setPositionId(csEventsSearchForm.getPositionId());	
		fvForm.setSuperviseeId(selectedDefendantId);
		fvForm.setEventDate(selectedEventDate);
		fvForm.setSelectedFVEventId(fieldVisitId);
		fvForm.setActivityFlow("");
   		fvForm.setSecondaryActivityFlow("");
   		
	}//end of initializeFieldVisitForm()
	
	
	
	
	/**
	 * 
	 * @param csEventsSearchForm
	 * @param ovForm
	 * @return
	 */
	public static String initializeOfficeVisitForm(CSEventsSearchForm csEventsSearchForm, CSCalendarOVForm ovForm)
	{
		String selectedDefendantId = "";
		String selectedCSEventTypeId = "";
		Date selectedEventDate = null;
		
		String selectedCSEventId = csEventsSearchForm.getSelectedCSEventId();
		
		Iterator iter = csEventsSearchForm.getCsEventsList().iterator();
		while(iter.hasNext())
		{
			CSEventsReportBean reportBeanObj = (CSEventsReportBean)iter.next();
			if(reportBeanObj.getCsEventId().equalsIgnoreCase(selectedCSEventId))
			{
				selectedDefendantId = reportBeanObj.getDefendantId();
				selectedCSEventTypeId= reportBeanObj.getCsEventTypeId();
				selectedEventDate = reportBeanObj.getCsEventDate();
				break;
			}
		}
		
		ovForm.setContext(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION);
		ovForm.setAgencyId(SecurityUIHelper.getUserAgencyId());
		ovForm.setPositionId(csEventsSearchForm.getPositionId());
		ovForm.setSuperviseeId(selectedDefendantId);
		ovForm.setSelectedEventId(selectedCSEventId);
		ovForm.setEventDate(selectedEventDate);
		
		return selectedCSEventTypeId;
		
	}//end of initializeOfficeVisitForm()
	
	
	
	/**
	 * 
	 * @param csEventsSearchForm
	 * @param otherEvtForm
	 */
	public static void initializeOtherEventsForm(CSEventsSearchForm csEventsSearchForm, CSCalendarOtherForm otherEvtForm)
	{
		String selectedDefendantId = "";
		Date selectedEventDate = null;
		
		String selectedCSEventId = csEventsSearchForm.getSelectedCSEventId();
		
		Iterator iter = csEventsSearchForm.getCsEventsList().iterator();
		while(iter.hasNext())
		{
			CSEventsReportBean reportBeanObj = (CSEventsReportBean)iter.next();
			if(reportBeanObj.getCsEventId().equalsIgnoreCase(selectedCSEventId))
			{
				selectedDefendantId = reportBeanObj.getDefendantId();
				selectedEventDate = reportBeanObj.getCsEventDate();
				break;
			}
		}
		
		otherEvtForm.setContext(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION);
		otherEvtForm.setPositionId(csEventsSearchForm.getPositionId());
		otherEvtForm.setSuperviseeId(selectedDefendantId);
		otherEvtForm.setEventId(selectedCSEventId);
		otherEvtForm.setEventDate(selectedEventDate);
		
	}//end of initializeOtherEventsForm()
	
	
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	private static String getUserNameFromUserId(String userId)
	{
	  Name name = (Name)SecurityUIHelper.getUserName(userId);
	  return name.getFormattedName();
	  
	}//end of getUserNameFromUserId()
}
