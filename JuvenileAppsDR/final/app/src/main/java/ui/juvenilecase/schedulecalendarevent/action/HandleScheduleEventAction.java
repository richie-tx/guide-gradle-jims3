package ui.juvenilecase.schedulecalendarevent.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.GetMemberLocationsEvent;
import messaging.calendar.GetScheduleCalendarEventConflictsEvent;
import messaging.calendar.ServiceEventTypeAttribute;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.codetable.criminal.reply.ServiceTypeCdResponseEvent;
import messaging.family.GetActiveFamilyConstellationEvent;
import messaging.family.GetFamilyMemberEmploymentInfoEvent;
import messaging.interviewinfo.GetInterviewCreationDataEvent;
import messaging.interviewinfo.reply.InterviewPersonResponseEvent;
import messaging.juvenile.GetJuvenileJobsEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenile.reply.JuvenileJobResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberAddressViewResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberEmploymentInfoResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDCalendarConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.PDJuvenileFamilyConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ujac.util.BeanComparator;

import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilewarrant.JJSPetition;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.contact.UIContactHelper;
import ui.juvenilecase.CalendarHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileLoadCodeTables;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;
import ui.juvenilecase.programreferral.UIProgramReferralBean;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.schedulecalendarevent.CalendarRetrieverFactory;
import ui.juvenilecase.schedulecalendarevent.InvolvedWeaponOffenseBean;
import ui.juvenilecase.schedulecalendarevent.UISupervisionCalendarHelper;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;
import ui.security.SecurityUIHelper;

public class HandleScheduleEventAction extends JIMSBaseAction {
	private static final String TRUE_STR = "true";
	private static final String FALSE_STR = "false";
	private static final String YES_STR = "yes";
	private static final String NO_STR = "no";
	private static final String DISPLAY_DETAILS_STR = "displayDetails";
	private static final String DISPLAY_PRE_CAL_EVENT_SUCCESS_STR = "displaySchedulePreScheduledCalendarEventSuccess";
	private static final String DISPLAY_INTERVIEW_CAL_EVENT_SUCCESS_STR = "displayScheduleInterviewCalendarEventSuccess";
	private static final String DISPLAY_NONINTERVIEW_CAL_EVENT_SUCCESS_STR = "displayScheduleNonInterviewCalendarEventSuccess";

	/**
	 * @roseuid 4576E78A0045
	 */
	public HandleScheduleEventAction() {
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49A50033
	 */
	public ActionForward serviceTypeChange(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ScheduleNewEventForm newEventForm = (ScheduleNewEventForm) aForm;
		newEventForm.getCurrentService().clear();
		
		ActionForward forward = aMapping.findForward(UIConstants.ADD_SUCCESS);
		newEventForm.setAction(DISPLAY_DETAILS_STR);
		newEventForm.getCurrentService().setLocationsList(ComplexCodeTableHelper.getActiveJuvenileLocationUnits());
		newEventForm.setOfficerHours("");
		newEventForm.setOfficerPhoneAreaCode("");
		String serviceEventTypeCode = newEventForm.getCurrentService().getServiceTypeCode();
		String serviceEventTypeCategory = newEventForm.getCurrentService().getServiceTypeCategory();

		if (serviceEventTypeCategory.equals(UIConstants.PRESCHEDULED_SERVICE_TYPE)) {
			GetCalendarServiceEventsEvent calSvcEvent = (GetCalendarServiceEventsEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETCALENDARSERVICEEVENTS);
			
			// Check to see if service events are only SP events
			boolean spEventsOnly = true;
			Collection serviceTypeList = newEventForm.getCurrentService().getServiceTypeList();
			{
				Iterator<ServiceTypeCdResponseEvent> iter = serviceTypeList.iterator();
				while (spEventsOnly && iter.hasNext()) {
					ServiceTypeCdResponseEvent serviceType = iter.next();
					spEventsOnly = serviceType.getCategory().trim().equals(UIConstants.PRESCHEDULED_SERVICE_TYPE);
				}
			}
			//get dats from query parameter and filter			
			String startDate = (String) aRequest.getParameter("startDate");
			String endDate = (String) aRequest.getParameter("endDate");
			//Date startDt,endDt;
			Date startDt=new Date();
			Date endDt=new Date();
			String xDate = "";
			String yDate = "";
			if( endDate != null )			    
			{
			    	xDate = endDate + " 00:00";
			    	endDt = DateUtil.stringToDate(xDate, DateUtil.DATETIME24_FMT_1);				
				calSvcEvent.setEndDatetime(endDt);
			}
			if( startDate != null )
			{
			    	yDate = startDate + " 00:00";
			    	startDt = DateUtil.stringToDate(yDate, DateUtil.DATETIME24_FMT_1);				
				calSvcEvent.setStartDatetime(startDt);		    
			}
					
			//
			// If service events are only true, let the event know.
			calSvcEvent.setSpEventsOnly(spEventsOnly);
			calSvcEvent.setResponseType(PDConstants.CAL_DETAIL_MEDIUM);
			calSvcEvent.setFilterInvalidContexts(false);

			String retrieverName = CalendarRetrieverFactory.getRetrieverName(CalendarRetrieverFactory.PRESCHEDULED_RETRIEVER);
			calSvcEvent.setRetriever(retrieverName);

			ServiceEventTypeAttribute eventType = new ServiceEventTypeAttribute();
			eventType.setServiceEventTypeId(serviceEventTypeCode);
			calSvcEvent.addCalendarAttribute(eventType);

			CalendarContextEvent calendarContextEvent = new CalendarContextEvent();
			calendarContextEvent.setJuvenileId(newEventForm.getJuvenileNum());
			calSvcEvent.setCalendarContextEvent(calendarContextEvent);
			
			List<CalendarServiceEventResponseEvent> eventList = MessageUtil.postRequestListFilter(calSvcEvent,CalendarServiceEventResponseEvent.class);

			//Sort eventList by programId
		    List theListToBeSorted = new ArrayList(eventList);
		    ArrayList sortFields = new ArrayList();
		    sortFields.add(new BeanComparator("programId"));
		    ComparatorChain multiSort = new ComparatorChain(sortFields);
		    Collections.sort(theListToBeSorted, multiSort);
		    eventList = new ArrayList(theListToBeSorted);
			
			List preScheduledEvents = new ArrayList();
			((ArrayList) preScheduledEvents).ensureCapacity(eventList.size());

			if (eventList != null) {
				// Complex logic built into For Loop to avoid repeated calls to the database
				// to find out whether a program has a referral. Query now runs once per
				// programId and result is stored in a HashMap in memory and used as needed.
				String programId = UIConstants.EMPTY_STRING;
				boolean checkIfProgramReferralExist = false;
				HashMap programReferralIds = new HashMap();

				for (CalendarServiceEventResponseEvent calEvt : eventList) {
					String eventCode = calEvt.getEventStatusCode();

					if (!calEvt.getProgramId().trim().equals(programId)) {
						programId = calEvt.getProgramId();
						checkIfProgramReferralExist = true;
					} 

					// Logic added to allow COMPLETED service events shown if
					// list is limited to spOnlyEvents
					if ((!eventCode.equals(PDCalendarConstants.SERVICE_EVENT_STATUS_CANCELLED) &&
							!eventCode.equals(PDCalendarConstants.SERVICE_EVENT_STATUS_COMPLETED)) || 
							((!eventCode.equals(PDCalendarConstants.SERVICE_EVENT_STATUS_CANCELLED) && 
							(eventCode.equals(PDCalendarConstants.SERVICE_EVENT_STATUS_COMPLETED) && spEventsOnly)))) {
						String hasProgramReferral = "";
						if (checkIfProgramReferralExist) {
							boolean programReferralExistInHashMap = programReferralIds.containsKey(calEvt.getProgramId().toString());
							//String programReferralExistInHashMap = (String) programReferralIds.get(calEvt.getProgramId().toString());
							//if (notNullNotEmptyString(programReferralExistInHashMap)) {
							if (programReferralExistInHashMap) {
								checkIfProgramReferralExist = false; 
							} else {
								UIProgramReferralBean referralBean = UIProgramReferralHelper.getActiveJuvenileProgramReferral(newEventForm.getJuvenileNum(), calEvt.getProgramId());
								if (referralBean != null) {
									
									if (!referralBean.getCasefileId().equals(newEventForm.getCaseFileId())) {
										hasProgramReferral = TRUE_STR; // Green - Open Referral on another casefile, can't schedule
									} else {
										hasProgramReferral = YES_STR; //Blue - Open referral on this casefile, you can schedule
									}
								} else {
									hasProgramReferral = NO_STR; //Crossed Out Blue - No Referrals anywhere, you can schedule
								}
								programReferralIds.put(calEvt.getProgramId(), hasProgramReferral);
								checkIfProgramReferralExist = false;
							}
						} else {
							hasProgramReferral = (String) programReferralIds.get(calEvt.getProgramId().toString());
						}
						calEvt.setHasProgramReferral(hasProgramReferral);	
//						calEvt.setHasProgramReferral(hasProgramReferral.equals(TRUE_STR) ? "yes" : "no");						
						preScheduledEvents.add(calEvt);
					}
				}

				if (preScheduledEvents.size() > 1) {
					Collections.sort(preScheduledEvents);
				}
			}
			newEventForm.setPreScheduledEvents(preScheduledEvents);
			forward = aMapping.findForward(DISPLAY_PRE_CAL_EVENT_SUCCESS_STR);
		}// if PRESCHEDULED_SERVICE_TYPE
		else if (serviceEventTypeCategory.equals(UIConstants.NONINTERVIEW_SERVICE_TYPE)) {
			List activeFacilityList=CodeHelper.getActiveCodesInM204(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY,true);
			newEventForm.setActiveFacilityList(activeFacilityList);
			if (serviceEventTypeCode.equals(UIConstants.HOME_VISIT)
					|| serviceEventTypeCode.equals(UIConstants.CURFEW_CHECK)
					|| serviceEventTypeCode.equals(UIConstants.FACE_TO_FACE_CURFEW_CHECK)
					|| serviceEventTypeCode.equals(UIConstants.PHONE_CURFEW_CHECK)
					|| serviceEventTypeCode.equals(UIConstants.HOME_DIAGNOSTIC_VISIT)) {
				if (newEventForm.getCurrentService().getFamilyMemberLocations().isEmpty()) {
					getMemberLocations(newEventForm);
				}
			} else if (serviceEventTypeCode.equals(UIConstants.JOB_VISIT)) {
				// <KISHORE>JIMS200058872 : Calendar: Modify School Visit
				// Location (UI) - KK
				// Use the existing LocationResponseEvent class to populate the
				// locations
				List<LocationResponseEvent> locationsList = new ArrayList<LocationResponseEvent>();
				FamilyMemberEmploymentInfoResponseEvent employer = null;
				String location = "";

				// Get the Recent employment location of the juvenile
				location = getJuvenileRecentEmploymentPlace(newEventForm.getJuvenileNum());
				LocationResponseEvent eventObj = new LocationResponseEvent();
				eventObj.setLocationName(newEventForm.getJuvenileName()	+ " - JUVENILE; " + location);
				eventObj.setLocationId(location);
				if (StringUtils.isNotEmpty(location))
					locationsList.add(eventObj);

				// Get the Family Constellations
				Map dataMap = UIJuvenileFamilyHelper.getActiveFamilyConstellation(newEventForm.getJuvenileNum());
				if (dataMap != null) {
					Collection families = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC);
					if (families != null && families.size() > 0) {
						Iterator<FamilyConstellationListResponseEvent> myIter = families.iterator();
						while (myIter.hasNext()) {
							FamilyConstellationListResponseEvent myFamily = myIter.next();
							// Get the family members of the active
							// constellation
							if (myFamily.isActive()) {
								Collection currentFamMembers = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
								Collections.sort((List) currentFamMembers);
								Iterator<FamilyConstellationMemberListResponseEvent> iter = currentFamMembers.iterator();
								while (iter.hasNext()) {
									FamilyConstellationMemberListResponseEvent obj = iter.next();
									employer = getFamilyMemeberMostRecentEmployer(obj.getMemberNum());
									if (employer != null) {
										LocationResponseEvent locObj = new LocationResponseEvent();
										locObj.setLocationName(obj.getFullName()+ " - "	+ obj.getRelationToJuvenile()+ "; "	+ employer.getCurrentEmployer());
										locObj.setLocationId(employer.getEmploymentId());
										locationsList.add(locObj);
									}
								} // inner while
								break; // out of outer while
							}
						} // outer while
					}
				}
				newEventForm.getCurrentService().setLocationsList(locationsList);
			} else if (serviceEventTypeCode.equals(UIConstants.SCHOOL_VISIT) 
				|| serviceEventTypeCode.equals(UIConstants.SCHOOL_ADJUDICATION)) {
				/*
				 * Collection schoolDistricts = newEventForm.getCurrentService().getSchoolDistricts();
				 * if(nullOrEmptyCollection( schoolDistricts ) ) {
				 *  	schoolDistricts = UIJuvenileHelper.fetchSchoolDistricts();
				 *      newEventForm.getCurrentService().setSchoolDistricts(schoolDistricts); 
				 * }
				 */
				// <KISHORE>JIMS200058872 : Calendar: Modify School Visit
				// Location (UI) - KK
				if (UIConstants.SCHOOL_ADJUDICATION.equals(serviceEventTypeCode) ){
					newEventForm.setTypeOfWeapons(CodeHelper.getCodes(PDCodeTableConstants.INVOVLED_WEAPON_TYPE, true) );
					List offenseInvolvedWeapons = new ArrayList();
					List offenses = retrieveOffenses(newEventForm);
					if (offenses != null){
						if (offenses.size() > 1){
							List sortedList = new ArrayList(offenses);
							ArrayList sortFields = new ArrayList();
							sortFields.add(new ComparatorChain(new BeanComparator("offenseCodeId")));
							ComparatorChain multiSort = new ComparatorChain(sortFields);
							Collections.sort(sortedList, multiSort);
							offenses = sortedList;
						}
						InvolvedWeaponOffenseBean iwOffense = new InvolvedWeaponOffenseBean();
						for (int x=0; x<offenses.size(); x++) {
							JJSOffenseResponseEvent offEvent = (JJSOffenseResponseEvent) offenses.get(x);
							iwOffense = new InvolvedWeaponOffenseBean();
							iwOffense.setCategory(offEvent.getCatagory() );
							iwOffense.setOffenseCodeId(offEvent.getPetitionAllegationId() );
							iwOffense.setOffenseCategoryDescription(offEvent.getOffenseCategoryDescription() );
							iwOffense.setOffenseDescription(offEvent.getOffenseDescription() );
							iwOffense.setPetitionNumber(offEvent.getPetitionNumber() );
							iwOffense.setReferralNumber(offEvent.getReferralNum());
							iwOffense.setSeveritySubtype(offEvent.getSeveritySubtype() );
							iwOffense.setTypeOfWeaponDescription("");
							iwOffense.setTypeOfWeaponId("");
							iwOffense.setWeaponDescription("");
							iwOffense.setWeaponInvolved(false);
							iwOffense.setWeaponInvolvedStr("");
							offenseInvolvedWeapons.add(iwOffense);
						}
						newEventForm.setOffenseInvolvedWeaponList(offenseInvolvedWeapons);
						offenseInvolvedWeapons = null;
					}	
				}
				Collection schoolHistory = UIJuvenileHelper.fetchSchoolHistory(newEventForm.getJuvenileNum());
				if (schoolHistory != null) {
					Iterator<JuvenileSchoolHistoryResponseEvent> iter = schoolHistory.iterator();
					HashMap schoolsMap = new HashMap();
					while (iter.hasNext()) {
						JuvenileSchoolHistoryResponseEvent event = iter.next();
						String districtKey = event.getSchoolDistrictId() + "/"	+ event.getSchoolId();
						if (!schoolsMap.containsKey(districtKey)) {
							event.setSchoolId(districtKey);
							String schoolInstructionType =  event.getInstructionType();
							if (schoolInstructionType != null && !schoolInstructionType.equals("")) {
							    event.setSchool(event.getSchoolDistrict() + "/"	+ event.getSchool() + ":  " + event.getInstructionType());
							} else {
								event.setSchool(event.getSchoolDistrict() + "/"	+ event.getSchool());
							}								
							schoolsMap.put(districtKey, event);
						}
					}
					List schools = new ArrayList();
					iter = schoolsMap.entrySet().iterator();
					while (iter.hasNext()) {
						Map.Entry entry = (Map.Entry) iter.next();
						schools.add(entry.getValue());
					}
					if (notNullNotEmptyCollection(schools)) {
						Collections.sort(schools);
					}
					newEventForm.getCurrentService().setSchools(schools);
				}
				newEventForm.getCurrentService().setTypeOfWeapons(CodeHelper.getCodes(PDCodeTableConstants.INVOVLED_WEAPON_TYPE, true) );
				}else if(serviceEventTypeCode.equals(UIConstants.FACILITY_PARENT_ORIENTATION)){ //12253 user story changes 
					newEventForm.getCurrentService().setFacilityId("DET");
					newEventForm.getCurrentService().getCurrentEvent().setEventTime("06:00 PM");
					newEventForm.getCurrentService().getCurrentEvent().setSessionLength("20");
					
					//Retrieve Guardian first and last name;
					// Get the Family Constellations
					Map dataMap = UIJuvenileFamilyHelper.getActiveFamilyConstellation(newEventForm.getJuvenileNum());
					if (dataMap != null) {
						Collection families = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC);
						if (families != null && families.size() > 0) {
							Iterator<FamilyConstellationListResponseEvent> myIter = families.iterator();
							while (myIter.hasNext()) {
								FamilyConstellationListResponseEvent myFamily = myIter.next();
								// Get the family members of the active
								// constellation
								if (myFamily.isActive()) {
									Collection currentFamMembers = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
									if(currentFamMembers!=null){
										Collections.sort((List) currentFamMembers);
									Iterator<FamilyConstellationMemberListResponseEvent> iter = currentFamMembers.iterator();
									while (iter.hasNext()) {
										FamilyConstellationMemberListResponseEvent member = iter.next();
										if(member.isGuardian()){
											newEventForm.setGuardianFirstName(member.getFirstName());
											newEventForm.setGuardianLastName(member.getLastName());
											break;
										}
									} // inner while
								}
									break; // out of outer while
								}
							} // outer while
						}
					}
					
				}else if(serviceEventTypeCode.equals(UIConstants.APPOINTMENT_LETTER)){ //ON HOLD 01/22/2016 User Story 
					 Collection referrals = UIJuvenileCaseworkHelper.fetchJuvenileCasefileReferralsList(newEventForm.getCaseFileId(), newEventForm.getJuvenileNum());
					 Iterator iter = referrals.iterator();				       
				       while(iter.hasNext())
				       {
				    	   
				    	   JuvenileCasefileReferralsResponseEvent resp = (JuvenileCasefileReferralsResponseEvent) iter.next();
				    	   JuvenileCasefileReferralsResponseEvent refWithOffense = UISupervisionCalendarHelper.getMaxSeverityOffense(resp, newEventForm.getJuvenileNum());
				    	  
				    	   String shortDesc = ( JuvenileReferralHelper.getFinalDisposition(resp.getReferralNumber(), newEventForm.getJuvenileNum(), newEventForm.getCaseFileId()));
				    	   if(shortDesc!=null && !shortDesc.equals(""))
				    		   resp.setReferralNumberWithSeverity(refWithOffense.getReferralNumber() + " - " +  shortDesc);
				    	   else
				    		   resp.setReferralNumberWithSeverity(refWithOffense.getReferralNumber());
				    	  
				       }
				       newEventForm.getCurrentService().setReferrals(referrals);				       
			    	 
				}
				else {
					if (newEventForm.getCurrentService().getLocationsList().isEmpty()) {
						newEventForm.getCurrentService().setLocationsList(ComplexCodeTableHelper.getActiveJuvenileLocationUnits());
					}
				}
			forward = aMapping.findForward(DISPLAY_NONINTERVIEW_CAL_EVENT_SUCCESS_STR);
		} // else if NONINTERVIEW_SERVICE_TYPE
		else if (serviceEventTypeCategory.equals(UIConstants.INTERVIEW_SERVICE_TYPE)) {
			GetInterviewCreationDataEvent getInterviewDataEvent = new GetInterviewCreationDataEvent();
			getInterviewDataEvent.setCasefileId(newEventForm.getCaseFileId());

			List intervieweeList = MessageUtil.postRequestListFilter(getInterviewDataEvent, InterviewPersonResponseEvent.class);

			// Do special sort here, need to refactor the method in
			// JuvenileInterviewForm
			JuvenileInterviewForm interviewForm = new JuvenileInterviewForm();
			intervieweeList = (List) interviewForm.specialSortJuvFamMembersContacts((Collection) intervieweeList);
			newEventForm.getCurrentService().setIntervieweeList(intervieweeList);

			Collection interviewTypeList = CodeHelper.getCodes(PDCodeTableConstants.INTERVIEW_TYPE, true);
			newEventForm.getCurrentService().setInterviewTypeList(interviewTypeList);

			if (newEventForm.getCurrentService().getLocationsList().isEmpty()) {
				newEventForm.getCurrentService().setLocationsList(ComplexCodeTableHelper.getActiveJuvenileLocationUnits());
			}
			UIJuvenileLoadCodeTables.getInstance().setScheduleNewEventForm(newEventForm);
			newEventForm.setAddressStatus(UIConstants.EMPTY_STRING);
			forward = aMapping.findForward(DISPLAY_INTERVIEW_CAL_EVENT_SUCCESS_STR);
		} else {
			newEventForm.setAction(UIConstants.EMPTY_STRING);
		}
		return forward;
	}

	private String getJuvenileRecentEmploymentPlace(String juvenileNumber) {
		String location = "";
		GetJuvenileJobsEvent jobsEvent = (GetJuvenileJobsEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEJOBS);

		jobsEvent.setJuvenileNum(juvenileNumber);
		List jobs = MessageUtil.postRequestListFilter(jobsEvent,JuvenileJobResponseEvent.class);
		if (jobs != null && !jobs.isEmpty()) {
			Collections.sort(jobs);
			// Job Location is a required value on the UI so we can take the
			// first Object in the list
			JuvenileJobResponseEvent resp = (JuvenileJobResponseEvent) jobs.get(0);
			location = resp.getEmploymentPlace() == null ? "" : resp.getEmploymentPlace();
		}
		return location;
	}

	private FamilyMemberEmploymentInfoResponseEvent getFamilyMemeberMostRecentEmployer(
			String memberNumber) {
		GetFamilyMemberEmploymentInfoEvent event = (GetFamilyMemberEmploymentInfoEvent) EventFactory
				.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBEREMPLOYMENTINFO);

		event.setMemberNum(memberNumber);
		List employments = MessageUtil.postRequestListFilter(event,FamilyMemberEmploymentInfoResponseEvent.class);
		if (employments != null && employments.size() > 0) {
			Collections.sort(employments);
			// We have to get the first not null employer on the record
			// Hence we cannot really take the first one in the list
			Iterator<FamilyMemberEmploymentInfoResponseEvent> iter = employments.iterator();
			while (iter.hasNext()) {
				FamilyMemberEmploymentInfoResponseEvent responseEvt = iter.next();
				if (notNullNotEmptyString(responseEvt.getCurrentEmployer())) {
					return responseEvt;
				}
			}
		}
		return null;
	}
	/*public static Date getTodaysDate() 
	{
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	public static Date getPastDate() 
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, CalendarHelper.CALENDAR_PAST_YEAR);

		return cal.getTime();
	}*/

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49A50033
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ScheduleNewEventForm form = (ScheduleNewEventForm) aForm;

		form.setProgramReferralNew(false);
		form.setProgramReferral(null);
		aRequest.setAttribute("calendarNeedsRefresh", FALSE_STR);

		if (UIConstants.SCHOOL_ADJUDICATION.equals(form.getCurrentService().getServiceTypeCode())) {
			List wrkList = new ArrayList();
			InvolvedWeaponOffenseBean iwOffense = new InvolvedWeaponOffenseBean();
			for (int w=0; w < form.getOffenseInvolvedWeaponList().size(); w++) {
				InvolvedWeaponOffenseBean iwo = (InvolvedWeaponOffenseBean) form.getOffenseInvolvedWeaponList().get(w);
				iwOffense = new InvolvedWeaponOffenseBean();
				iwOffense.setCategory(iwo.getCategory());
				iwOffense.setOffenseCodeId(iwo.getOffenseCodeId());
				iwOffense.setOffenseCategoryDescription(iwo.getOffenseCategoryDescription() );
				iwOffense.setOffenseDescription(iwo.getOffenseDescription());
				iwOffense.setPetitionNumber(iwo.getPetitionNumber());
				iwOffense.setReferralNumber(iwo.getReferralNumber());
				iwOffense.setSeveritySubtype(iwo.getSeveritySubtype());
				iwOffense.setTypeOfWeaponDescription(form.getTypeOfWeaponDescription()[w]);
				iwOffense.setTypeOfWeaponId(form.getTypeOfWeaponId()[w]);
				iwOffense.setWeaponDescription(form.getWeaponDescription()[w]);
				iwOffense.setWeaponInvolvedStr(form.getWeaponInvolvedStr()[w]);
				if ("Y".equals(form.getWeaponInvolvedStr()[w]) ) {
					iwOffense.setWeaponInvolved(true);
				}
				wrkList.add(iwOffense);
			}
			form.setOffenseInvolvedWeaponList(wrkList);
			wrkList = null;
			iwOffense = null;
		}
		//added for US 11109
		if (UIConstants.APPOINTMENT_LETTER.equals(form.getCurrentService().getServiceTypeCode())) {
			//set phone number
			form.setOfficerPhone(UIUtil.convertPhoneNum(form.getOfficerPhoneAreaCode(), form.getOfficerPhonePrefix(), form.getOfficerPhoneMain()).getFormattedPhoneNumber());
			form.setOfficerFax(UIUtil.convertPhoneNum(form.getFaxAreaCode(), form.getFaxPrefix(), form.getFaxMain()).getFormattedPhoneNumber());			
			if(form.getCurrentService().getControllingReferral()!=null)
			{
				String[] ref = form.getCurrentService().getControllingReferral().split("-");
				if(ref.length>1)
				{
					if(ref[1].trim().equalsIgnoreCase("F1") || ref[1].trim().equalsIgnoreCase("F2") || ref[1].trim().equalsIgnoreCase("F3") || ref[1].trim().equalsIgnoreCase("JF") || ref[1].trim().equalsIgnoreCase("CF"))
						form.getCurrentService().setLetterType("FELONY");
					else
						form.getCurrentService().setLetterType("MISDEMEANOR");					
				}
				if(ref.length>2)
					form.getCurrentService().setPetitionNumber(ref[2].trim());
				 String shortDesc = ( JuvenileReferralHelper.getFinalDisposition(ref[0], form.getJuvenileNum(), form.getCaseFileId()));
				 StringBuffer controllingRef=new StringBuffer();
				 controllingRef.append(ref[0] + "-" + ref[1]);
				 if(shortDesc!=null && !shortDesc.equals(""))
					 controllingRef.append("-" + shortDesc);
				 form.getCurrentService().setControllingReferral(controllingRef.toString());
			}
		}
		// Clear list of conflicting events that may exist
		ScheduleNewEventForm.Event currentEvent = form.getCurrentService().getCurrentEvent();

		currentEvent.setJpoConflictingEvents(new ArrayList());
		currentEvent.setJuvenileConflictingEvents(new ArrayList());

		String serviceEventCode = form.getCurrentService().getServiceTypeCode();
		String serviceEventCategory = form.getCurrentService().getServiceTypeCategory();

		if (serviceEventCategory == null) {
			this.saveErrors(aRequest, "error.generic","Service Type is in error");
			return aMapping.findForward(returnErrorPage(form));
		}

		List allEvents;
		if (serviceEventCategory.equals(UIConstants.PRESCHEDULED_SERVICE_TYPE)) {
			allEvents = new ArrayList();

			if (notNullNotEmptyCollection(form.getPreScheduledEvents())) {
				int servProv = -198788;
				Collection<ScheduleNewEventForm.ServiceProvider> psEventsList = form.getPreScheduledEvents();
				boolean invalidProgramReferral = false;
				for (ScheduleNewEventForm.ServiceProvider preScheduledEvent : psEventsList) {
					Collection eventRespEvts = preScheduledEvent.getServiceEventResponseEvents();
					if (notNullNotEmptyCollection(eventRespEvts)) {
						Collection<CalendarServiceEventResponseEvent> csEventList = eventRespEvts;
						for (CalendarServiceEventResponseEvent preScheduledEvent1 : csEventList) {
							if (preScheduledEvent1 != null	&& preScheduledEvent1.isPreScheduledSelected()) {
								if (servProv == -198788) {
									servProv = preScheduledEvent1.getServiceProviderId();
									if(preScheduledEvent1.getProgramScheduleTypeId()!=null){ //added for U.S #11099
										if(preScheduledEvent1.getProgramScheduleTypeId().equals("P")){
											sendToErrorPage(aRequest, "error.generic","Program requires creation in Program Referral tab. Calendar use is not required");
											return aMapping.findForward(DISPLAY_PRE_CAL_EVENT_SUCCESS_STR);
										}
									}
								} else if (servProv != preScheduledEvent1.getServiceProviderId()) {
									sendToErrorPage(aRequest, "error.generic",	"Selecting events from two different programs is not allowed");
									return aMapping.findForward(DISPLAY_PRE_CAL_EVENT_SUCCESS_STR);
								}
								if (preScheduledEvent1.getHasProgramReferral().equals("true")) {
									invalidProgramReferral = true;
								}
							}
							
						} // for
					}
				} // outer for

				if (servProv == -198788) {
					sendToErrorPage(aRequest, "error.generic","At least one event must be selected");
					return aMapping.findForward(DISPLAY_PRE_CAL_EVENT_SUCCESS_STR);
				}
				if (invalidProgramReferral) {
					this.saveErrors(aRequest, "error.programReferral");
					return aMapping.findForward(DISPLAY_PRE_CAL_EVENT_SUCCESS_STR);
				}
			} else {
				sendToErrorPage(aRequest, "error.generic","There are no prescheduled events of this event type");
				return aMapping.findForward(DISPLAY_PRE_CAL_EVENT_SUCCESS_STR);
			}

			ArrayList preScheduledEvents = new ArrayList();

			{
				Collection<ScheduleNewEventForm.ServiceProvider> pseList = form.getPreScheduledEvents();
				int capacity = 0;
				for (ScheduleNewEventForm.ServiceProvider preScheduledEvent : pseList) {
					capacity += preScheduledEvent.getServiceEventResponseEvents().size();
					preScheduledEvents.ensureCapacity(capacity);

					preScheduledEvents.addAll(preScheduledEvent.getServiceEventResponseEvents());
				}
			}// end local block

			if (preScheduledEvents.size() > 0) {
				String programId = null;
				String programName = null;

				Collection<CalendarServiceEventResponseEvent> csEventList = preScheduledEvents;
				for (CalendarServiceEventResponseEvent preScheduledEvent : csEventList) {
					if (preScheduledEvent.isPreScheduledSelected()) {
						int currentEnroll = 0;
						if (notNullNotEmptyString(preScheduledEvent.getCurrentEnrollment())) {
							try {
								currentEnroll = Integer.parseInt(preScheduledEvent.getCurrentEnrollment());
							} catch (NumberFormatException nfe) {
								this.saveErrors(aRequest, "error.generic","Error obtaining Current Enrollment value.");
								return (aMapping.findForward(returnErrorPage(form)));
							}
						}

						int maxAttendance = 0;
						if (notNullNotEmptyString(preScheduledEvent.getMaxAttendance())) {
							try {
								maxAttendance = Integer.parseInt(preScheduledEvent.getMaxAttendance());
							} catch (NumberFormatException nfe) {
								this.saveErrors(aRequest, "error.generic","Error obtaining Maximum Attendance value.");
								return (aMapping.findForward(returnErrorPage(form)));
							}
						}
						programId = preScheduledEvent.getProgramId();
						programName = preScheduledEvent.getProgramName();
						if (currentEnroll >= maxAttendance) {
							this.saveErrors(aRequest, "error.maxAttendance");
							return aMapping.findForward(DISPLAY_PRE_CAL_EVENT_SUCCESS_STR);
						}
						form.getCurrentService().setService(preScheduledEvent.getServiceName());
						form.getCurrentService().setServiceProvider(preScheduledEvent.getServiceProviderName());
						form.getCurrentService().setServiceProviderId(preScheduledEvent.getServiceProviderId()+ UIConstants.EMPTY_STRING);
						allEvents.add(preScheduledEvent);
					}
				}
				form.setProgramId(programId);
				form.setProgramName(programName);
				form.setProgramReferralNew(false);

				UIProgramReferralBean uiProgramReferral = UIProgramReferralHelper.getActiveProgramReferral(form.getCaseFileId(),programId);
				form.setProgramReferral(uiProgramReferral);
			}
		}// if PRESCHEDULED_SERVICE_TYPE
		else {
			if (serviceEventCategory.equals(UIConstants.NONINTERVIEW_SERVICE_TYPE)) {
				//12253 user story changes  added facility_ parent_orientation
				if (serviceEventCode.equals(UIConstants.PLACEMENT_VISIT)||serviceEventCode.equals(UIConstants.FACILITY_PARENT_ORIENTATION)) { 
					String facility = form.getCurrentService().getFacilityId();
					if (notNullNotEmptyString(facility)) {
						form.getCurrentService().setFacility(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY,facility));
					}
				}
			}

			/*
			 * Combine EventDate, EventTime and SessionLength to produce
			 * StartDate and EndDate from user input
			 */
			Calendar dateCalendar = Calendar.getInstance();
			String fullDateTime = currentEvent.getEventDateStr().concat(" ").concat(currentEvent.getEventTime());

			Date startDate = DateUtil.stringToDate(fullDateTime,UIConstants.DATETIME_FMT_1AMPM);
			dateCalendar.setTime(startDate);
			currentEvent.setStartDate(dateCalendar.getTime());

			{
				Calendar endDate = Calendar.getInstance();
				endDate.setTime(dateCalendar.getTime());

				String sessionLength = currentEvent.getSessionLength();
				int sessionHr = 0;
				int sessionMin = 0;
				// The sessionLength code table values are
				// 5,10,15,20,25,30,35,40,45,50...80 which
				// stands for session length 0.5 hr, 1 hr, 1.5 hrs....8hrs.
				// Parse out the hour by dividing the value by 10. Parse out
				// minutes
				// by doing a mod on 10..minutes value is always 0 or 30.
				if (notNullNotEmptyString(sessionLength)) {
					int sessionLengthInt = 0;
					try {
						sessionLengthInt = Integer.parseInt(sessionLength);
					} catch (NumberFormatException nfe) { /* empty */
					}
					sessionHr = sessionLengthInt / 10;
					if ((sessionLengthInt % 10) != 0) {
						sessionMin = 30;
					}
				}

				endDate.set(Calendar.HOUR_OF_DAY, endDate.get(Calendar.HOUR_OF_DAY)+ sessionHr);
				endDate.set(Calendar.MINUTE, endDate.get(Calendar.MINUTE)+ sessionMin);

				currentEvent.setEndDate(endDate.getTime());
			}// end local code block

			if (form.getCurrentService().getLocationId() != null
					&& form.getCurrentService().getLocationId().equals(UIConstants.USER_ENTERED_CUSTOM_ADDRESS)) {
				messaging.contact.to.Address newAddress = form.getCurrentService().getNewAddress();
				form.getCurrentService().setLocation("New Address");

				if (notNullNotEmptyString(newAddress.getStateCode())) {
					form.getCurrentService().setStateName(CodeHelper.getCodeDescriptionByCode(form.getCurrentService().getStateList(),newAddress.getStateCode()));
				}
				if (notNullNotEmptyString(newAddress.getCountyCode())) {
					form.getCurrentService().setCountyName(CodeHelper.getCodeDescriptionByCode(form.getCurrentService().getCountyList(),newAddress.getCountyCode()));
				}
				if (notNullNotEmptyString(newAddress.getStreetTypeCode())) {
					form.getCurrentService().setStreetTypeName(CodeHelper.getCodeDescriptionByCode(form.getCurrentService().getStreetTypeList(),newAddress.getStreetTypeCode()));
				}
				if (notNullNotEmptyString(newAddress.getAddressTypeCode())) {
					form.getCurrentService().setAddressTypeName(CodeHelper.getCodeDescriptionByCode(form.getCurrentService().getAddressTypeList(),newAddress.getAddressTypeCode()));
				}
				if (notNullNotEmptyString(newAddress.getStreetNumSuffixCode())) {
					form.getCurrentService().setStreetNumSuffixName(CodeHelper.getCodeDescriptionByCode(form.getCurrentService().getStreetNumSuffixList(),newAddress.getStreetNumSuffixCode()));
				}
			}

			if (currentEvent.isRecurringEvent()) {
				allEvents = UISupervisionCalendarHelper.getRecurringEvents(form);
				if (allEvents != null) {
					if (allEvents.size() > 60) {
						this.saveErrors(aRequest,"error.generic","A total of "+ allEvents.size()+ " events would have been generated which exceeds the max limit of 60. Please edit your recurring event options");
						return aMapping.findForward(returnErrorPage(form));
					} else if (!allEvents.isEmpty()) {
						int maxVal = allEvents.size();
						boolean errorFlag = false;

						GregorianCalendar myOneYearOutCal = new GregorianCalendar();
						myOneYearOutCal.setTime(startDate);
						myOneYearOutCal.add(Calendar.YEAR, 1);
						GregorianCalendar testCal = new GregorianCalendar();

						if (maxVal > 0) {
							CalendarServiceEventResponseEvent myLastEvt = (CalendarServiceEventResponseEvent) allEvents.get(maxVal - 1);
							testCal.setTime(myLastEvt.getStartDatetime());

							if (testCal.after(myOneYearOutCal)) {
								errorFlag = true;
							}

							if (!errorFlag) {
								myLastEvt = (CalendarServiceEventResponseEvent) allEvents.get(0);
								testCal.setTime(myLastEvt.getStartDatetime());

								if (testCal.after(myOneYearOutCal)) {
									errorFlag = true;
								}
							}
						} // if maxVal > 0

						if (errorFlag) {
							this.saveErrors(aRequest,"error.generic","Events spanning more than one year out would have been generated which is not allowed");
							return aMapping.findForward(returnErrorPage(form));
						}
					}// else
				}// if(allEvents != null)
			}// if(
				// form.getCurrentService().getCurrentEvent().isRecurringEvent())
			else {
				allEvents = new ArrayList();
				allEvents.add(UISupervisionCalendarHelper.getNonRecurringEvent(form));
			}
		}// else not PRESCHEDULED_SERVICE_TYPE

		if (nullOrEmptyCollection(allEvents)) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.events.match"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.BACK);
		}

		form.setAllEvents(allEvents);
		List juvenileConflictingEvents = this.getJuvenileConflictingEvents(allEvents, form);
		if (notNullNotEmptyCollection(juvenileConflictingEvents)) {
			Collections.sort(juvenileConflictingEvents);
		}
		currentEvent.setJuvenileConflictingEvents(juvenileConflictingEvents);

		if (!serviceEventCategory.equals(UIConstants.PRESCHEDULED_SERVICE_TYPE)) {
			List jpoConflictingEvents = this.getJPOConflictingEvents(allEvents,	form);
			if (notNullNotEmptyCollection(jpoConflictingEvents)) {
				Collections.sort(jpoConflictingEvents);
			}
			currentEvent.setJpoConflictingEvents(jpoConflictingEvents);
		}

		aRequest.setAttribute("pageType", UIConstants.SUMMARY);
		HttpSession session = aRequest.getSession();
		session.setAttribute( "ScheduleNewEventForm" , form );
		return (aMapping.findForward(UIConstants.SUMMARY));
	}

	/**
	 * @param allEvents
	 * @param form
	 * @return
	 */
	public List getJPOConflictingEvents(List allEvents,	ScheduleNewEventForm form) {
		// Get list of conflicting event with the event to be scheduled
		GetScheduleCalendarEventConflictsEvent gse = (GetScheduleCalendarEventConflictsEvent) EventFactory
				.getInstance(ServiceEventControllerServiceNames.GETSCHEDULECALENDAREVENTCONFLICTS);

		gse.setCheckEvents(allEvents);

		CalendarContextEvent calendarContextEvent = new CalendarContextEvent();
		calendarContextEvent.setProbationOfficerId(form.getOfficerId());
		gse.setCalendarContextEvent(calendarContextEvent);
		List conflictingEvents = null;
		if (form.getOfficerId() != null) {
			conflictingEvents = MessageUtil.postRequestListFilter(gse,CalendarServiceEventResponseEvent.class);
		}
		return conflictingEvents;
	}

	/**
	 * @param allEvents
	 * @param form
	 * @return
	 */
	public List getJuvenileConflictingEvents(List allEvents,ScheduleNewEventForm form) {
		// Get list of conflicting event with the event to be scheduled
		GetScheduleCalendarEventConflictsEvent gse = (GetScheduleCalendarEventConflictsEvent) EventFactory
				.getInstance(ServiceEventControllerServiceNames.GETSCHEDULECALENDAREVENTCONFLICTS);

		gse.setCheckEvents(allEvents);

		CalendarContextEvent calendarContextEvent = new CalendarContextEvent();
		calendarContextEvent.setJuvenileId(form.getJuvenileNum());
		gse.setCalendarContextEvent(calendarContextEvent);

		List conflictingEvents = MessageUtil.postRequestListFilter(gse,CalendarServiceEventResponseEvent.class);
		return conflictingEvents;
	}

	/**
	 * @param aRequest
	 * @param errorKey
	 */
	private void saveErrors(HttpServletRequest aRequest, String errorKey) {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey,SecurityUIHelper.getLogonId()));
		saveErrors(aRequest, errors);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward getSchool(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ScheduleNewEventForm newEventForm = (ScheduleNewEventForm) aForm;

		String districtKey = newEventForm.getCurrentService().getSchoolDistrictId();
		Map schoolMap = newEventForm.getCurrentService().getSchoolMap();
		List schools = (List) schoolMap.get(districtKey);

		if (notNullNotEmptyCollection(schools)) {
			Collections.sort(schools);
		}

		newEventForm.getCurrentService().setSchools(schools);
		newEventForm.setAction(DISPLAY_DETAILS_STR);

		return (aMapping.findForward(DISPLAY_NONINTERVIEW_CAL_EVENT_SUCCESS_STR));
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward createNewReferral(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return (aMapping.findForward(UIConstants.CREATE_NEW_REFERRAL));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping,
	 * org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ScheduleNewEventForm form = (ScheduleNewEventForm) aForm;

		form.getCurrentService().setServiceType(UIConstants.EMPTY_STRING);
		form.getCurrentService().setServiceTypeId(UIConstants.EMPTY_STRING);
		form.getCurrentService().setServiceTypeCode(UIConstants.EMPTY_STRING);
		form.getCurrentService().setServiceTypeCategory(UIConstants.EMPTY_STRING);
		((ScheduleNewEventForm) aForm).setAction(UIConstants.EMPTY_STRING);
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ui.action.JIMSBaseAction#cancel(org.apache.struts.action.ActionMapping,
	 * org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return (aMapping.findForward(UIConstants.CANCEL));
	}

	/*
	 * @param aRequest
	 * 
	 * @param errorKey
	 * 
	 * @param errorMsg
	 */
	private void saveErrors(HttpServletRequest aRequest, String errorKey,String errorMsg) {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey,	errorMsg, SecurityUIHelper.getLogonId()));
		saveErrors(aRequest, errors);
	}

	/*
	 * @param newEventForm
	 * 
	 * @return
	 */
	private String returnErrorPage(ScheduleNewEventForm newEventForm) {
		String returnStr = UIConstants.ADD_SUCCESS;

		if (newEventForm != null
				&& newEventForm.getCurrentService() != null
				&& newEventForm.getCurrentService().getServiceTypeCategory() != null) {
			String serviceEventTypeCategory = newEventForm.getCurrentService().getServiceTypeCategory();

			if (serviceEventTypeCategory.equals(UIConstants.PRESCHEDULED_SERVICE_TYPE)) {
				returnStr = DISPLAY_PRE_CAL_EVENT_SUCCESS_STR;
			} else if (serviceEventTypeCategory.equals(UIConstants.NONINTERVIEW_SERVICE_TYPE)) {
				returnStr = DISPLAY_NONINTERVIEW_CAL_EVENT_SUCCESS_STR;
			} else if (serviceEventTypeCategory	.equals(UIConstants.INTERVIEW_SERVICE_TYPE)) {
				returnStr = DISPLAY_INTERVIEW_CAL_EVENT_SUCCESS_STR;
			}
		}
		return returnStr;
	}

	/*
	 * @param juvenileNum
	 * 
	 * @return
	 */
	private Collection getCurrentActiveConstellation(String juvenileNum) {
		GetActiveFamilyConstellationEvent getCurrentConstellation = new GetActiveFamilyConstellationEvent();

		getCurrentConstellation.setJuvenileNum(juvenileNum);
		CompositeResponse replyEvent = MessageUtil.postRequest(getCurrentConstellation);
		Collection constellations = MessageUtil.compositeToCollection(replyEvent, FamilyConstellationListResponseEvent.class);
		return constellations;
	}

	/*
	 * @param newEventForm
	 */
	private void getMemberLocations(ScheduleNewEventForm newEventForm) {
		GetMemberLocationsEvent mlev = (GetMemberLocationsEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETMEMBERLOCATIONS);
		Collection constellations = getCurrentActiveConstellation(newEventForm.getJuvenileNum());

		if (notNullNotEmptyCollection(constellations)) { 
			/*
			 * Only 1 active
			 * constellation at a
			 * time, therefore it's
			 * safe to get the first
			 * in collection
			 */
			FamilyConstellationListResponseEvent activeConstellation = (FamilyConstellationListResponseEvent) constellations.iterator().next();

			mlev.setJuvenileNum(newEventForm.getJuvenileNum());
			mlev.setConstellationId(activeConstellation.getFamilyNum());

			List familyMemberLocations = MessageUtil.postRequestListFilter(mlev, FamilyMemberAddressViewResponseEvent.class);

			newEventForm.getCurrentService().setFamilyMemberLocations(familyMemberLocations);
		}
	}

	private List retrieveOffenses (ScheduleNewEventForm form) {
		List offenses = new ArrayList();
		Collection referrals = UIJuvenileCaseworkHelper.fetchJuvenileCasefileReferralsList(form.getCaseFileId(), form.getJuvenileNum());
		for (Iterator referralIter = referrals.iterator(); referralIter.hasNext();) {
			JuvenileCasefileReferralsResponseEvent referral = (JuvenileCasefileReferralsResponseEvent) referralIter.next();
			String TJPCCode = null;
			TJPCCode = "1";
			if (referral != null) {
				if (referral.getFinalDisposition() != null
					&& referral.getFinalDisposition().length() > 0) {
					TJPCCode = referral.getFinalDispositionTJPCCode();
				} else if (referral.getIntakeDecisionCode() != null
					&& referral.getIntakeDecisionCode().length() > 0) {
					TJPCCode = referral.getIntakeDecisionTJPCCode();
				}
				//Find out if disposition code has a TJPC disposition
				if (TJPCCode != null && (Integer.parseInt(TJPCCode) > 0)) {
					Collection referralOffenses = referral.getOffenseResponseEvents();
					if (referralOffenses != null){
						for (Iterator offenseIter = referralOffenses.iterator(); offenseIter.hasNext();) {
							JJSOffenseResponseEvent offense = (JJSOffenseResponseEvent) offenseIter.next();
							//need petition allegation 
							if (referral.getPetitions() != null && referral.getPetitions().size() > 0){
								List refPetitions = CollectionUtil.iteratorToList(referral.getPetitions().iterator());
								PetitionResponseEvent jjsPet = (PetitionResponseEvent) refPetitions.get(0);
								offense.setPetitionAllegationId(jjsPet.getOffenseCodeId());
							} else {
								offense.setPetitionAllegationId("");
							}
							//check offense level
							offense.setOffenseCategoryDescription("Misdemeanor");
							if ("F1".equals(offense.getCatagory()) || "F2".equals(offense.getCatagory()) ||
								"F3".equals(offense.getCatagory()) || "JF".equals(offense.getCatagory()) || "CF".equals(offense.getCatagory())) {
								offense.setOffenseCategoryDescription("Felony");
								String petitionNum = getPetition(form.getJuvenileNum(), referral.getReferralNumber());
								offense.setPetitionNumber(petitionNum);
								offenses.add(offense);
							} else 	//check severity
								if ("V".equals(offense.getSeveritySubtype()) || "S".equals(offense.getSeveritySubtype()) ) { 
									String petitionNum = getPetition(form.getJuvenileNum(), referral.getReferralNumber());
									offense.setPetitionNumber(petitionNum);
									offenses.add(offense);
							}
						}
					}	
				}
			}
		}
		return offenses;
	}

	private String getPetition(String juvenileNum, String referralNum) {
	     String petitionNum = "";
		CompositeResponse response = UIJuvenileWarrantHelper.fetchJJSPetitions(juvenileNum, "", referralNum);
		Collection petitions = MessageUtil.compositeToCollection( response, PetitionResponseEvent.class);
        Iterator iter = petitions.iterator();
        //Bug #82091 - Get the petition with seqnum 1
        while(iter.hasNext())
        {
        	PetitionResponseEvent petitionResponseEvent = (PetitionResponseEvent)iter.next();
    		if(petitionResponseEvent!=null && petitionResponseEvent.getSequenceNum()!=null && petitionResponseEvent.getSequenceNum().equals("1"))
    		{
    		    petitionNum = petitionResponseEvent.getPetitionNum();
    		    break;
    		}
        }
		return petitionNum;
	}
	/*
	 * given a string, returns true if that string is not null and contains one
	 * or more characters
	 * 
	 * @param str
	 * 
	 * @return
	 */
	private boolean notNullNotEmptyString(String str) {
		return (str != null && str.trim().length() > 0);
	}

	/*
	 * given a collection, return true if it's not null and not empty
	 * 
	 * @param coll
	 * 
	 * @return
	 */
	private boolean notNullNotEmptyCollection(Collection coll) {
		return (coll != null && !coll.isEmpty());
	}

	/*
	 * given a collection, return true if it's null or empty
	 * 
	 * @param coll
	 * 
	 * @return
	 */
	private boolean nullOrEmptyCollection(Collection coll) {
		return (coll == null || coll.isEmpty());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map buttonMap) {
		buttonMap.put("button.serviceTypeChange", "serviceTypeChange");
		buttonMap.put("button.next", "next");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.go", "getSchool");
		buttonMap.put("button.createNewReferral", "createNewReferral");
	}
}