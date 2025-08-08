//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\schedulecalendarevent\\action\\HandleScheduleEventAction.java

package ui.juvenilecase.schedulecalendarevent.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.GetProgramsByProviderIdEvent;
import messaging.administerserviceprovider.reply.ProgramSourceFundResponseEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.CreateCalendarServiceEventEvent;
import messaging.calendar.CreateScheduleCalendarEventsEvent;
import messaging.calendar.GetProgramAttendanceEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.codetable.GetJuvenileFacilityByCodeEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.codetable.person.reply.JuvenileSchoolDistrictCodeResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.interviewinfo.CreateInterviewEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.productionsupport.RetrieveJuvPgmRefsByProvPgmIdEvent;
import messaging.programreferral.SaveProgramReferralAssociationsEvent;
import messaging.programreferral.SaveProgramReferralEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.CodeTableControllerServiceNames;
import naming.InterviewConstants;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileProgramReferralControllerServiceNames;
import naming.PDCalendarConstants;
import naming.ProgramReferralConstants;
import naming.ServiceEventControllerServiceNames;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.programreferral.JuvenileProgramReferral;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.common.UIUtil;
import ui.contact.UIContactHelper;
import ui.contact.user.helper.UIUserFormHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.FacilityOrientationLetterReportBean;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.helper.JuvenileCaseworkAlertsHelper;
import ui.juvenilecase.programreferral.ProgramReferralAction;
import ui.juvenilecase.programreferral.ProgramReferralStateManager;
import ui.juvenilecase.programreferral.UIProgramReferralBean;
import ui.juvenilecase.schedulecalendarevent.InvolvedWeaponOffenseBean;
import ui.juvenilecase.schedulecalendarevent.UISupervisionCalendarHelper;
import ui.juvenilecase.schedulecalendarevent.to.AdjudicationNotificationTO;
import ui.juvenilecase.schedulecalendarevent.to.AppointmentLetterBean;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;
import ui.security.SecurityUIHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class SubmitScheduleEventAction extends JIMSBaseAction
{
	private static final int ADD_14_DAYS = 336 ;
	private static final int ADD_7_DAYS = 168 ;
	private static final int SUBTRACT_7_DAYS = (-168) ;
	private static final int SUBTRACT_6_DAYS = (-144) ;
	private final String contactAdmin = "Please contact your System Administrator with a description of this problem." ;

	/*
	 */
	public SubmitScheduleEventAction()
	{
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward createNewReferral( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ScheduleNewEventForm form = (ScheduleNewEventForm)aForm ;
		// US 177340 - Code for MAX YOUTH STARTS 
		JuvenileServiceProvider juvServiceProvider = JuvenileServiceProvider.find(Integer.valueOf(form.getCurrentService().getServiceProviderId()));
		ActionForward forward = aMapping.findForward( UIConstants.SUCCESS ) ;
		String juvNum = form.getJuvenileNum();
		//get the MAX YOUTH number for the selected vendor from the table: CSJUVSERVPROV
		if (juvServiceProvider != null && juvServiceProvider.getMaxYouth() != null)
		{
		    int maxYouth = Integer.parseInt(juvServiceProvider.getMaxYouth());
		    if (maxYouth != 0)
		    {
			String selectedSPVendorId = form.getCurrentService().getServiceProviderId();
			// code for program list STARTS
			GetProgramsByProviderIdEvent getProgramNames = (GetProgramsByProviderIdEvent) 
				EventFactory.getInstance(ServiceProviderControllerServiceNames.GETPROGRAMSBYPROVIDERID);

			getProgramNames.setJuvServiceProviderId(selectedSPVendorId);
			//FROM JIMS2.CSPROVPROGRAM WHERE JUVSERVPROV_ID = 
			CompositeResponse compResponse = MessageUtil.postRequest(getProgramNames);
			List<ProviderProgramResponseEvent> programs = MessageUtil.compositeToList(compResponse, ProviderProgramResponseEvent.class);
			for (Iterator<ProviderProgramResponseEvent> iter = programs.iterator(); iter.hasNext();) {

			    ProviderProgramResponseEvent obj = iter.next();
			    if( !"A".equals( obj.getProgramStatusId())){

				iter.remove();
			    }
			}

			Collections.sort((List) programs);
			form.setProgramList(programs);
			// new code for program list ends
			ProviderProgramResponseEvent selectedSP = new ProviderProgramResponseEvent();
			Iterator servProviderItr = programs.iterator();
			while (servProviderItr.hasNext())
			{
			    ProviderProgramResponseEvent sp = (ProviderProgramResponseEvent) servProviderItr.next();
			    if (sp.getServiceProviderId().equalsIgnoreCase(selectedSPVendorId))
			    {
				selectedSP = sp;
				break;
			    }
			}
			Set<Integer> distinctJuvenileIds = new HashSet<>(); // Set to hold distinct juvenile IDs
			List<ProgramSourceFundResponseEvent> activeProgramsList = (List<ProgramSourceFundResponseEvent>) selectedSP.getProgramSourceFundList(); //get all the ACTIVE programs for the selected vendor Service Provider Name

			for (ProviderProgramResponseEvent program : programs)//
			{
			    RetrieveJuvPgmRefsByProvPgmIdEvent getJuvProgramReferralEvent = new RetrieveJuvPgmRefsByProvPgmIdEvent();
			    getJuvProgramReferralEvent.setProvProgramId(program.getProviderProgramId());
			    ArrayList<JuvenileProgramReferral> juvenileProgramReferralList = (ArrayList) CollectionUtil.iteratorToList(JuvenileProgramReferral.findAll(getJuvProgramReferralEvent));
			    // call above gets data FROM JIMS2.CSJUVPROGREF WHERE PROVPROGRAM_ID = program.getProviderProgramId() 
			    Iterator juvPgrmRefItr = juvenileProgramReferralList.iterator();
			    while (juvPgrmRefItr.hasNext())
			    {
				JuvenileProgramReferral jpr = (JuvenileProgramReferral) juvPgrmRefItr.next();
				if (jpr.getEndDate() == null) //
				{
				    String casefileID = jpr.getCasefileId();
				    GetJuvenileCasefileEvent getCasefileEvent = (GetJuvenileCasefileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);
				    getCasefileEvent.setSupervisionNumber(casefileID); //call to JCCASEFILE
				    CompositeResponse response = MessageUtil.postRequest(getCasefileEvent);
				    JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent) MessageUtil.filterComposite(response, JuvenileCasefileResponseEvent.class);
				    distinctJuvenileIds.add(Integer.parseInt(casefile.getJuvenileNum()));
				    //if (program.getServiceProviderId() != null && program.getServiceProviderId().equalsIgnoreCase(juvServiceProvider.getOID()) && program.getProgramEndDate() == null)
				    if (program.getServiceProviderId() != null && program.getServiceProviderId().equalsIgnoreCase(juvServiceProvider.getOID()) )

				    {
					distinctJuvenileIds.add(Integer.parseInt(casefile.getJuvenileNum()));
				    }
				}
			    }
			}
			if (distinctJuvenileIds.size() >= maxYouth) //check if the total UNIQUE juv count is less than the MAXYOUTH number 
			{
			    //TO DO check if the juvenileNum is already in the set, if yes, go forward with addition of program ref
			    if (distinctJuvenileIds.contains(Integer.parseInt(juvNum)))
			    {
				System.out.println(distinctJuvenileIds);
				System.out.println(juvNum);
			    }
			    else
			    {
				ActionErrors errors = new ActionErrors();
				String msg = "Maximum Youth Limit Exceeded for this Service Provider";
				aRequest.setAttribute( "pageType", "maxYouthLimit" ); 
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", msg));
				saveErrors(aRequest, errors);
				forward = aMapping.findForward(UIConstants.FAILURE);
				return forward;
			    }
			}
		    }
		} // US 177340 / 180163 - Code for MAX YOUTH ENDS
		UIProgramReferralBean programReferral = new UIProgramReferralBean() ;
		programReferral.setReferralState( ProgramReferralStateManager.getReferralState( 
		ProgramReferralConstants.TENTATIVE, ProgramReferralConstants.REFERRED ) ) ;
		Date saveEventDateYMD =(DateUtil.stringToDate(form.getSaveEventDate(), DateUtil.DATE_FMT_1));
		Date currentDateYMD = form.getCurrentDate();
		if (form.getSecondaryAction() == UIConstants.SCHEDULE_PAST_PRESCHEDULED_STR &&
		    currentDateYMD.after(saveEventDateYMD)) {
		    programReferral.setBeginDateStr(form.getSaveEventDate()) ;
		    
		}else{    
		    programReferral.setBeginDateStr( DateUtil.dateToString( form.getCurrentDate( ), DateUtil.DATE_FMT_1 ) ) ;
		}
			
		programReferral.setCurrentAction( ProgramReferralAction.CREATE ) ;
		programReferral.setProviderProgramId( form.getProgramId() ) ;
		programReferral.setProviderProgramName( form.getProgramName() ) ;
		programReferral.setJuvServiceProviderId( form.getCurrentService().getServiceProviderId() ) ;
		programReferral.setCasefileId( form.getCaseFileId() ) ;
		programReferral.setJuvenileId( form.getJuvenileNum() ) ;

		form.setProgramReferralNew( true ) ;
		GetProgramAttendanceEvent attendanceEvent = (GetProgramAttendanceEvent)
				EventFactory.getInstance( ServiceEventControllerServiceNames.GETPROGRAMATTENDANCE ) ;
		attendanceEvent.setProgramId( form.getProgramId() ) ;
		attendanceEvent.setJuvenileNum( form.getJuvenileNum() ) ;

		List attendanceEvents = MessageUtil.postRequestListFilter( attendanceEvent, ServiceEventAttendanceResponseEvent.class ) ;
		if( attendanceEvents != null )
		{
			programReferral.setExistingReferrals( attendanceEvents ) ;
		}

		form.setProgramReferral( programReferral ) ;

		return aMapping.findForward( UIConstants.CREATE_NEW_REFERRAL ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward goAheadAndSchedule( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ScheduleNewEventForm form = (ScheduleNewEventForm)aForm ;
		String source = (String) aRequest.getParameter("action");
		CreateScheduleCalendarEventsEvent createEvent = (CreateScheduleCalendarEventsEvent)
				EventFactory.getInstance( ServiceEventControllerServiceNames.CREATESCHEDULECALENDAREVENTS ) ;

		CreateCalendarServiceEventEvent calendarServiceEvent = new CreateCalendarServiceEventEvent() ;

		calendarServiceEvent.setEventTypeId( form.getCurrentService().getServiceTypeId() ) ;
		calendarServiceEvent.setEventStatusId( PDCalendarConstants.SERVICE_EVENT_STATUS_AVAILABLE ) ;
		calendarServiceEvent.setEventComments( form.getCurrentService().getCurrentEvent().getComments() ) ;
		calendarServiceEvent.setEventTypeCategory( form.getCurrentService().getServiceTypeCategory() ) ;

		CalendarContextEvent calendarContextEvent = new CalendarContextEvent();
		
		calendarContextEvent.setProbationOfficerId(form.getOfficerId());
		calendarContextEvent.setJuvenileId(form.getJuvenileNum());
		calendarContextEvent.setCaseFileId(form.getCaseFileId());
		
		calendarServiceEvent.setCalendarContextEvent(calendarContextEvent);
		
		//Nothing has been inserted into the database at this point.
		
		String serviceEventTypeCode = form.getCurrentService().getServiceTypeCode().trim() ;
		String serviceEventCategory = form.getCurrentService().getServiceTypeCategory().trim() ;

		if( UIConstants.NONINTERVIEW_SERVICE_TYPE.equalsIgnoreCase( serviceEventCategory ) )
		{
			if( UIConstants.HOME_VISIT.equalsIgnoreCase( serviceEventTypeCode )  || 
					UIConstants.HOME_DIAGNOSTIC_VISIT.equalsIgnoreCase( serviceEventTypeCode ) ||
					UIConstants.FACE_TO_FACE_CURFEW_CHECK.equalsIgnoreCase( serviceEventTypeCode ) ||
					UIConstants.PHONE_CURFEW_CHECK.equalsIgnoreCase( serviceEventTypeCode ) ||
					UIConstants.CURFEW_CHECK.equalsIgnoreCase( serviceEventTypeCode ) )
			{
				calendarServiceEvent.setMemberAddressId( form.getCurrentService().getMemberAddressId() ) ;
			}
			else if( UIConstants.SCHOOL_VISIT.equalsIgnoreCase( serviceEventTypeCode ) )
			{
				calendarServiceEvent.setSchoolCd( form.getCurrentService().getSchoolId() ) ;
				calendarServiceEvent.setSchoolDistrictId( form.getCurrentService().getSchoolDistrictId() ) ;
			}
			else if( UIConstants.SCHOOL_ADJUDICATION.equalsIgnoreCase( serviceEventTypeCode ) )
			{
				//Get Juvenile's birth date and full name
				String juvenileId = form.getJuvenileNum();
				UISupervisionCalendarHelper.getJuvenileNameAndBirthDate(form, juvenileId);
				//Get Officer's phone number
				String loginId = SecurityUIHelper.getJIMSLogonId();
				OfficerProfileResponseEvent officer = UIUserFormHelper.getUserOfficerProfile(loginId);
				String workPhone = null;
				if(officer!=null)
				{
					if (officer.getOfficerSubTypeId()!=null && officer.getOfficerSubTypeId().equals(UIConstants.OFFICER_SUBTYPE_CLM)) {
						workPhone = officer.getWorkPhone();
					} else {
						String CLMLogonId = officer.getManagerId();
						workPhone = UIContactHelper.getOfficerProfilePhone(CLMLogonId);
					}
				}
				
				workPhone = UIUtil.formatPhoneNum(workPhone);
				form.setOfficerPhone(workPhone);
				
				Date serviceEventDate = form.getCurrentService().getCurrentEvent().getStartDate() ;
				SimpleDateFormat fmt = new SimpleDateFormat("EEEE, MM/dd/yyyy");
				String eventDate = fmt.format(serviceEventDate);
				form.getCurrentService().getCurrentEvent().setEventDateStr(eventDate);
				
				calendarServiceEvent.setSchoolCd( form.getCurrentService().getSchoolId() ) ;
				calendarServiceEvent.setSchoolDistrictId( form.getCurrentService().getSchoolDistrictId() ) ;
				Collection districts = UIJuvenileHelper.fetchSchoolDistricts();
				JuvenileSchoolDistrictCodeResponseEvent school = ComplexCodeTableHelper.getSchool(districts, form.getCurrentService().getSchoolDistrictId(), form.getCurrentService().getSchoolId());
				form.getCurrentService().setSchoolName(school.getSchoolDescription());
				
				calendarServiceEvent.setContactLastName(form.getCurrentService().getContactLastName());
				calendarServiceEvent.setContactFirstName(form.getCurrentService().getContactFirstName());
				calendarServiceEvent.setEventStatusId( PDCalendarConstants.SERVICE_EVENT_STATUS_COMPLETED ) ;
				calendarServiceEvent.setSexOffender(form.getCurrentService().getSexOffenderRegistrantStr());
				calendarServiceEvent.setRestrictOther(form.getCurrentService().getRestrictionsOther());
				List wrkList = new ArrayList(form.getOffenseInvolvedWeaponList());
				String weaponDescs = "";
				StringBuffer sb = new StringBuffer();
				for (int x=0; x<wrkList.size(); x++){
					InvolvedWeaponOffenseBean iwo = (InvolvedWeaponOffenseBean) wrkList.get(x);
					sb.append(iwo.getReferralNumber());
					sb.append("^");
					sb.append(iwo.getOffenseCategoryDescription());
					sb.append("^");
					sb.append(iwo.getOffenseDescription());
					sb.append("^");
					sb.append(iwo.getWeaponInvolvedStr());
					if ("Y".equalsIgnoreCase(iwo.getWeaponInvolvedStr())){
						sb.append("^");
						sb.append(iwo.getTypeOfWeaponDescription());
					}
					sb.append("|");
				}
				weaponDescs = sb.toString();
				calendarServiceEvent.setWeaponDescs(weaponDescs);
				weaponDescs = null;
				
				CompositeResponse compResp = sendPrintRequest("REPORTING::ADJUDICATION_NOTIFICATION", form, null);
				ReportResponseEvent aReportRespEvt = (ReportResponseEvent)
					MessageUtil.filterComposite(compResp, ReportResponseEvent.class);
		
				if( aReportRespEvt == null || 
						aReportRespEvt.getContent() == null ||
						aReportRespEvt.getContent().length < 1 )
				{
					sendToErrorPage( aRequest, "error.generic",	"Problems generating report. " +contactAdmin ) ;
					return aMapping.findForward(UIConstants.FAILURE);
				}
				try {
					setPrintContentResp(aResponse, compResp, "ADJUDICATION_NOTIFICATION",
							UIConstants.PRINT_AS_PDF_DOC);
				} catch (GeneralFeedbackMessageException e) {
					e.printStackTrace();
				}
				calendarServiceEvent.setDocument(aReportRespEvt.getContent());
			}
			else if( UIConstants.CLOSING_LETTER.equalsIgnoreCase( serviceEventTypeCode ) || 
					UIConstants.CLOSING_PACKET.equalsIgnoreCase( serviceEventTypeCode ))
			{
				calendarServiceEvent.setContactLastName(form.getCurrentService().getContactLastName());
				calendarServiceEvent.setContactFirstName(form.getCurrentService().getContactFirstName());
				calendarServiceEvent.setEventStatusId( PDCalendarConstants.SERVICE_EVENT_STATUS_COMPLETED ) ;
				calendarServiceEvent.setLocationId( form.getCurrentService().getLocationId() ) ;
			}
			else if( UIConstants.PLACEMENT_VISIT.equalsIgnoreCase( serviceEventTypeCode ) )
			{
				calendarServiceEvent.setFacilityId( form.getCurrentService().getFacilityId() ) ;
			}
			else if(UIConstants.JOB_VISIT.equalsIgnoreCase( serviceEventTypeCode ))
			{
				//<KISHORE>JIMS200059076 : Calendar: Add new event type Job Visit (UI) - KK
				// For juvenile location selection, we get location name as the location
				// For the family member location selection, it will be their member employment Id
				if(StringUtils.isNotEmpty(form.getCurrentService().getLocation())){
					if(isIntNumber(form.getCurrentService().getLocation()))
						calendarServiceEvent.setMemberEmploymentId(form.getCurrentService().getLocation()) ;
					else
						calendarServiceEvent.setLocation( form.getCurrentService().getLocation() ) ;
				}
			}else if( UIConstants.FACILITY_PARENT_ORIENTATION.equalsIgnoreCase( serviceEventTypeCode ) ){ //12253 user story changes.
				String location = "";
				if(form.getCurrentService().getFacilityId()!=null && !form.getCurrentService().getFacilityId().isEmpty()){
					calendarServiceEvent.setFacilityId(form.getCurrentService().getFacilityId()) ;
					
					GetJuvenileFacilityByCodeEvent facilityEvent = (GetJuvenileFacilityByCodeEvent)EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEFACILITYBYCODE);
					facilityEvent.setCode(form.getCurrentService().getFacilityId());
					CompositeResponse response = MessageUtil.postRequest(facilityEvent);
					JuvenileFacilityResponseEvent juvFacRespEvnt =  (JuvenileFacilityResponseEvent) MessageUtil.filterComposite(response, JuvenileFacilityResponseEvent.class);
					if(juvFacRespEvnt!=null){
						if(juvFacRespEvnt.getAddress()!=null && juvFacRespEvnt.getAddress().isEmpty() || juvFacRespEvnt.getCity().isEmpty() || juvFacRespEvnt.getZip().isEmpty()){
							location ="";
						}else{
							location = juvFacRespEvnt.getAddress()+", "+juvFacRespEvnt.getCity()+", TX "+juvFacRespEvnt.getZip();
						}
					}
		        }
				
				//Fill Report Bean:
				 FacilityOrientationLetterReportBean reportBean = new FacilityOrientationLetterReportBean();
				 //Event Date
				 if(source.equals("EN")){ //english
						 DateFormat fmt = new SimpleDateFormat("EEEE, MMMM d, yyyy");
						 String eventDate = fmt.format(form.getCurrentService().getCurrentEvent().getStartDate());
						 reportBean.setEventDateStr(eventDate);
					 }else{ //spanish
						 DateFormat fmt = new SimpleDateFormat("EEEE, MMMM d, yyyy" , new Locale("es", "ES"));
						 String eventDate = fmt.format(form.getCurrentService().getCurrentEvent().getStartDate());
						 reportBean.setEventDateStr(eventDate.toUpperCase());
					 }
				 
				 //Event Time (start-end)
				 reportBean.setEventTime(form.getCurrentService().getCurrentEvent().getEventTime());
				 DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
				 String endEventTime = dateFormat.format(form.getCurrentService().getCurrentEvent().getEndDate().getTime());
				 reportBean.setEndTime(endEventTime);
				 
				 //Location
				 reportBean.setFacility(form.getCurrentService().getFacility());
				 if(location!=null && !location.isEmpty()){
					 if(form.getCurrentService().getFacilityId().equals("DET")&& !location.isEmpty()){
							if(source.equals("EN")){ //English
								reportBean.setLocation(location+" - First Floor Court Room");
							}else{ //spanish
								reportBean.setLocation(location+" - 1er. Piso (La junta ser� en la Corte Criminal que est� localizada a su lado derecho al entrar al edificio)");
							}
						} else{
							 reportBean.setLocation(location);
						}
				 }
					
				 UISupervisionCalendarHelper.getJuvenileNameAndBirthDate(form, form.getJuvenileNum());
				 
				 //Juv Name bug 26711
				 StringBuffer juvenileName = new StringBuffer();
				 juvenileName.append(StringUtils.capitalize(form.getJuvenileFirstName().toLowerCase()));
					if(form.getJuvenileLastName()!=null && !form.getJuvenileLastName().equals("")){
							juvenileName.append(" ");
							juvenileName.append(StringUtils.capitalize(form.getJuvenileLastName().toLowerCase()));	
					}
				reportBean.setJuvenileFullName(juvenileName.toString()) ;
			
				 //Officer Name bug 26711
				StringBuffer officerName = new StringBuffer();
				officerName.append(StringUtils.capitalize(form.getOfficerFirstName().toLowerCase()));
				if(form.getOfficerLastName()!=null && !form.getOfficerLastName().equals("")){
					officerName.append(" ");
					officerName.append(StringUtils.capitalize(form.getOfficerLastName().toLowerCase()));	
				}
				reportBean.setOfficerName(officerName.toString());
				 
				 //Officer Phone
				 String workPhone = null;
				 String officerLogonId=UIContactHelper.getOfficerLogonId(form.getOfficerId());
				 if(officerLogonId!=null && !officerLogonId.isEmpty())
				 workPhone = UIContactHelper.getOfficerProfilePhone(officerLogonId);
				 if(workPhone!=null && !workPhone.isEmpty()){
					 if(source.equals("EN")){ //English
						 reportBean.setOfficerPhone("at "+UIUtil.formatPhoneNum(workPhone));
					 }else{ //Spanish
						 reportBean.setOfficerPhone("al tel�fono "+UIUtil.formatPhoneNum(workPhone));
					 }
				 }
				 
				//guardian name  bug 26711
				StringBuffer guardianName = new StringBuffer();
				guardianName.append(StringUtils.capitalize(form.getGuardianFirstName().toLowerCase()));
				if(form.getGuardianLastName()!=null && !form.getGuardianLastName().equals("")){
					guardianName.append(" ");
					guardianName.append(StringUtils.capitalize(form.getGuardianLastName().toLowerCase()));	
				}
				reportBean.setContactName(guardianName.toString());
				 
				 aRequest.getSession().setAttribute("reportInfo", reportBean);
				 BFOPdfManager pdfManager = BFOPdfManager.getInstance();
				//let the pdfManager know that the report should be saved in the request during report creation
				aRequest.setAttribute("isPdfSaveNeeded", "true");
				if(source.equals("EN")){ //English
					pdfManager.createPDFReport(aRequest, aResponse, PDFReport.FACILITY_ORIENTATION_LETTER_EN);
				}else{ //Spanish
					pdfManager.createPDFReport(aRequest, aResponse, PDFReport.FACILITY_ORIENTATION_LETTER_ES);
				}
				byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
				if(pdfDocument!=null){
					calendarServiceEvent.setDocument(pdfDocument);
				}
				aRequest.removeAttribute("pdfSavedReport");
				// remove the pdf report attributes from session when finished saving to database
				aRequest.removeAttribute("isPdfSaveNeeded");
				source=null;	
			}
			else
			{
				calendarServiceEvent.setLocationId( form.getCurrentService().getLocationId() ) ;
			}
		} // if non-interview
		else if( UIConstants.INTERVIEW_SERVICE_TYPE.equalsIgnoreCase( serviceEventCategory ) )
		{
			CreateInterviewEvent event = new CreateInterviewEvent() ;
			event.setInterviewStatusCd( InterviewConstants.INTERVIEW_STATUS_PENDING ) ;
			event.setCasefileId( form.getCaseFileId() ) ;

			{ Date interviewDate = DateUtil.stringToDate( 
					form.getCurrentService().getCurrentEvent().getEventDateStr() + " " 
							+ form.getCurrentService().getCurrentEvent().getEventTime(), 
					UIConstants.DATETIME_FMT_1AMPM ) ;
				event.setInterviewDate( interviewDate ) ;
			
				event.setInterviewTypeId( form.getCurrentService().getInterviewTypeId() ) ;
			
				List persons = event.getInterviewPersons() ;
				persons.clear() ;

				Collection coll = form.getCurrentService().getSelectedIntervieweeList() ;
				if( coll != null )
				{
					persons.addAll( coll ) ;
				}
			} // end code block

			if( form.getCurrentService().getLocationId().equals( UIConstants.USER_ENTERED_CUSTOM_ADDRESS ) )
			{
				event.setAddress( form.getCurrentService().getNewAddress() ) ;
			}
			else
			{
				event.setLocationDescription( form.getCurrentService().getLocation() ) ;
				event.setJuvLocUnitId( form.getCurrentService().getLocationId() ) ;
				calendarServiceEvent.setLocationId( form.getCurrentService().getLocationId() ) ;
			}
			createEvent.setCreateInterviewEvent( event ) ;
		}

		ArrayList<CalendarServiceEventResponseEvent> scheduledEvents = new ArrayList() ;
		
	{ Collection<CalendarServiceEventResponseEvent> serviceEventList = form.getAllEvents() ;
			scheduledEvents.ensureCapacity( serviceEventList.size() ) ;
			for( CalendarServiceEventResponseEvent anEvent : serviceEventList )
			{
				if( anEvent.isAddConflictingEvent() )
				{
					scheduledEvents.add( anEvent ) ;
				}
			}
		} // end code block

		if( scheduledEvents.isEmpty() )
		{
			ActionErrors errors = new ActionErrors() ;
			errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( "error.no.event.selected" ) ) ;
			aRequest.setAttribute( "pageType", "summary" ) ;
			saveErrors( aRequest, errors ) ;
			return aMapping.findForward( UIConstants.SUMMARY ) ;
		}

		calendarServiceEvent.setEvents( scheduledEvents ) ;
		form.setAllEvents( scheduledEvents ) ;
		createEvent.setCreateCalendarEvent( calendarServiceEvent ) ; //Makes first event

		CompositeResponse response = (CompositeResponse)MessageUtil.postRequest( createEvent ) ;

		// Generating Notice for Pending Appointments to JPO
		JuvenileCaseworkAlertsHelper helper = new JuvenileCaseworkAlertsHelper() ;
		JuvenileCasefileForm juvenileCasefileForm = 
			(JuvenileCasefileForm) aRequest.getSession().getAttribute( "juvenileCasefileForm" ) ;
		String jpoId = juvenileCasefileForm.getProbationOfficerLogonId().trim() ;

		if( form.getJuvenileName() == null )
		{
			form.setJuvenileName( juvenileCasefileForm.getJuvenileName().getFormattedName() ) ;
		}
		
		for( CalendarServiceEventResponseEvent anEvent : scheduledEvents )
		{ /* Below mentioned code will send the notices only if the eventDate 
		   * is ahead of the corresponding notices date. */
			 
			Calendar eventDate = Calendar.getInstance() ;
			eventDate.setTime( anEvent.getStartDatetime() ) ;
			
			//TODO need to add a condition
			form.getCurrentService().getCurrentEvent().setProposedEventId(anEvent.getEventId()); //set the eventId  # U.S.30339 changes
			
			if( UIConstants.NONINTERVIEW_SERVICE_TYPE.equalsIgnoreCase( serviceEventCategory ) )
			{
				if( UIConstants.HOME_DIAGNOSTIC_VISIT.equalsIgnoreCase( serviceEventTypeCode ) ||
					UIConstants.JOB_VISIT.equalsIgnoreCase( serviceEventTypeCode ) || 
					UIConstants.HOME_VISIT.equalsIgnoreCase( serviceEventTypeCode ) || 
					UIConstants.FACE_TO_FACE_CURFEW_CHECK.equalsIgnoreCase( serviceEventTypeCode ) || 
					UIConstants.PHONE_CURFEW_CHECK.equalsIgnoreCase( serviceEventTypeCode ) || 
					UIConstants.CURFEW_CHECK.equalsIgnoreCase( serviceEventTypeCode ) )
				{
					Calendar currentDateAdjusted = Calendar.getInstance() ; // get today's date
					currentDateAdjusted.set( Calendar.HOUR, ADD_14_DAYS ) ;
					if( eventDate.after( currentDateAdjusted ) )
					{
						helper.sendPOPendingAppointment2WeekspriorNotification( 
								form, serviceEventTypeCode, anEvent, jpoId ) ; 
					}
					
					currentDateAdjusted.set( Calendar.HOUR, SUBTRACT_7_DAYS ) ;
					if( eventDate.after( currentDateAdjusted ) )
					{
						helper.sendPOPendingAppointment1WeekpriorNotification(  
								form, serviceEventTypeCode, anEvent, jpoId ) ; 
					}
					
					currentDateAdjusted.set( Calendar.HOUR, SUBTRACT_6_DAYS ) ;
					if( eventDate.after( currentDateAdjusted ) )
					{
						helper.sendPOPendingAppointmentDayBeforepriorNotification( 
								form, serviceEventTypeCode, anEvent, jpoId ) ; 
					}
					currentDateAdjusted = null ;
				}
				if( UIConstants.SCHOOL_ADJUDICATION.equalsIgnoreCase( serviceEventTypeCode ))
				{
					//create activities
					ScheduleNewEventForm.Service service = form.getCurrentService();
					ScheduleNewEventForm.Event event = service.getCurrentEvent();
					StringBuffer comments = new StringBuffer();
					comments.append("Generated notice to ");
					comments.append(service.getContactFirstName());
					comments.append(" ");
					comments.append(service.getContactLastName());
					comments.append(" with ");
					comments.append(service.getSchoolDistrictName());
					comments.append(" on ");
					comments.append(event.getEventDateStr());
					comments.append(" of juvenile's adjudication on felony and/or misdemeanor A/B offense(s).");
					UIJuvenileHelper.createActivity( form.getCaseFileId(), 	ActivityConstants.SCHOOL_ADJUDICATION_NOTIFICATION, comments.toString() ) ;
					StringBuffer comments1 = new StringBuffer();
					comments1.append(service.getContactFirstName());
					comments1.append(" ");
					comments1.append(service.getContactLastName());
					comments1.append(" with ");
					comments1.append(service.getSchoolDistrictName());
					comments1.append(" was notified on ");
					comments1.append(event.getEventDateStr());
					comments1.append(" of juvenile's adjudication on felony and/or misdemeanor A/B offense(s).");
					UIJuvenileHelper.createActivity( form.getCaseFileId(), 	ActivityConstants.SCHOOL_ADJUDICATION_NOTIFICATION2, comments1.toString() ) ; 
				}
			} // if NONINTERVIEW_SERVICE_TYPE
			
			if( UIConstants.INTERVIEW_SERVICE_TYPE.equalsIgnoreCase( serviceEventCategory ) )
			{
				if( UIConstants.INTERVIEW.equalsIgnoreCase( serviceEventTypeCode ) )
				{
					Calendar currentDateAdjusted = Calendar.getInstance() ;
					currentDateAdjusted.set( Calendar.HOUR, ADD_14_DAYS ) ;
					if( eventDate.after( currentDateAdjusted ) )
					{
						helper.sendPOPendingAppointment2WeekspriorNotification( 
								form, serviceEventTypeCode, anEvent, jpoId ) ;
					}
					
					currentDateAdjusted.set( Calendar.HOUR, SUBTRACT_7_DAYS ) ;
					if( eventDate.after( currentDateAdjusted ) )
					{
						helper.sendPOPendingAppointment1WeekpriorNotification( 
								form, serviceEventTypeCode, anEvent, jpoId ) ; 
					}
					
					currentDateAdjusted.set( Calendar.HOUR, SUBTRACT_6_DAYS ) ;
					if( eventDate.after( currentDateAdjusted ) )
					{
						helper.sendPOPendingAppointmentDayBeforepriorNotification( 
								form, serviceEventTypeCode, anEvent, jpoId ) ;  
					}
					currentDateAdjusted = null ;
					
					// Generating Notice for the supervision type Court.
					if( UIConstants.CASEFILE_SUPERVISION_TYPE_COURT_SUPERVISION.equalsIgnoreCase( 
							juvenileCasefileForm.getSupervisionTypeId() ) )
					{
						String subject = "Schedule Overdue Pre-court interview for Juvenile # " + form.getJuvenileNum() ;
						Calendar tDate = Calendar.getInstance() ;
						tDate.set( Calendar.HOUR, ADD_7_DAYS ) ;
						if( eventDate.after( tDate ) )
						{
							helper.schedulePreCourtInterviewNotification( 
									jpoId, subject, SUBTRACT_7_DAYS, anEvent.getStartDatetime(), form, juvenileCasefileForm ) ; 
						}
						tDate = null ;
					}
				}
			}
		} // forEach scheduledEvents 
		
		// End Notices.
		if( UIConstants.PRESCHEDULED_SERVICE_TYPE.equalsIgnoreCase( serviceEventCategory ) )
		{
			String programReferralId = form.getProgramReferral().getReferralId() ;
			if( form.isProgramReferralNew() )
			{
				UIProgramReferralBean programReferral = form.getProgramReferral() ;
				programReferral.executeAction() ;
				
				SaveProgramReferralEvent saveRefEvent = programReferral.getSaveProgramReferralEvent() ;
				saveRefEvent.setAttachedEvents( scheduledEvents ) ;
				saveRefEvent.setCasefileId( form.getCaseFileId() ) ;
				saveRefEvent.setProgramId( form.getProgramId() ) ;
				saveRefEvent.setControllingReferralNum(form.getProgramReferral().getControllingReferralNum());

				response = MessageUtil.postRequest( saveRefEvent ) ;
				if( response != null )
				{
					ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent)
							MessageUtil.filterComposite( response, ProgramReferralResponseEvent.class ) ;
					programReferralId = resp.getReferralId() ;
					resp.setJuvServiceProviderId( programReferral.getJuvServiceProviderId() ) ;
					resp.setJuvenileId( programReferral.getJuvenileId() ) ;
					resp.setProviderProgramName( programReferral.getProviderProgramName() ) ;
					helper.sendSPProgramReferralActionAlert( resp, juvenileCasefileForm ) ;
				}
				
				UIProgramReferralBean.ProgramReferralTaskInfo taskInfo = programReferral.getReferralTaskInfo() ;
				if( taskInfo != null )
				{
					Map map = taskInfo.getParameterMap() ;
					map.put( "referralId", programReferralId ) ;
					programReferral.getReferralTaskInfo().createTask() ;
				}  
			}

			if( programReferralId != null )
			{
				SaveProgramReferralAssociationsEvent saveRefAssocEvent = (SaveProgramReferralAssociationsEvent)
						EventFactory.getInstance( JuvenileProgramReferralControllerServiceNames.SAVEPROGRAMREFERRALASSOCIATIONS ) ;
				saveRefAssocEvent.setProgramReferralId( programReferralId ) ;

				List eventIdList = new ArrayList() ;
				Collection<CalendarServiceEventResponseEvent> eventList = form.getAllEvents() ;
				if( eventList != null )
				{
					((ArrayList)eventIdList).ensureCapacity( eventList.size() ) ;
					
					for( CalendarServiceEventResponseEvent anEvent : eventList )
					{
						eventIdList.add( anEvent.getEventId() ) ;
					}
				}
				saveRefAssocEvent.setAttachedEvents( eventIdList ) ;
				response = MessageUtil.postRequest( saveRefAssocEvent ) ; 
			}
		}

		aRequest.setAttribute( "pageType", "confirmation" ) ;
	
		ActionForward forward = aMapping.findForward( UIConstants.SUMMARY ) ;
		if(source!=null)
		{
			if(source.equalsIgnoreCase("closingLetterEnglish"))
				forward = aMapping.findForward( "closingLetterEnglishSuccess" ) ;
			else if(source.equalsIgnoreCase("closingLetterSpanish"))
				forward = aMapping.findForward( "closingLetterSpanishSuccess" ) ;
			else if(source.equalsIgnoreCase("closingPacketEnglish"))
				forward = aMapping.findForward( "closingPacketEnglishSuccess" ) ;
			else
				forward = aMapping.findForward( "closingPacketSpanishSuccess" ) ;
		}
		return forward ;
	}
	
	public boolean isIntNumber(String str){
		for (int i = 0; i < str.length(); i++) {
        //If we find a non-digit character we return false.
        if (!Character.isDigit(str.charAt(i)))
            return false;
		}
	    return true;  
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ActionForward forward = aMapping.findForward( UIConstants.BACK ) ;
		return forward ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ActionForward forward = aMapping.findForward( UIConstants.CANCEL ) ;
		return forward ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward returnToCalendar( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ActionForward forward = aMapping.findForward( UIConstants.RETURN_SUCCESS ) ;
		return forward ;
	}
	
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward goAheadAndScheduleBFO(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ScheduleNewEventForm form = (ScheduleNewEventForm) aForm;
		AdjudicationNotificationTO formTO = new AdjudicationNotificationTO();
		
		String source = (String) aRequest.getParameter("action");
		CreateScheduleCalendarEventsEvent createEvent = (CreateScheduleCalendarEventsEvent) EventFactory
				.getInstance(ServiceEventControllerServiceNames.CREATESCHEDULECALENDAREVENTS);
		
		formTO.setOffenseInvolvedWeaponList(form.getOffenseInvolvedWeaponList());
		formTO.setSchoolDistrictName(form.getCurrentService().getSchoolDistrictName());
		formTO.setSchoolName(form.getCurrentService().getSchoolName());
		formTO.setSchoolStreet(form.getCurrentService().getSchoolStreet());
		formTO.setSchoolCity(form.getCurrentService().getSchoolCity());
		formTO.setSchoolState(form.getCurrentService().getSchoolState());
		formTO.setSchoolZip(form.getCurrentService().getSchoolZip());
		formTO.setSexOffenderRegistrantStr(form.getCurrentService().getSexOffenderRegistrantStr());
		formTO.setRestrictionsOther(form.getCurrentService().getRestrictionsOther());
		formTO.setContactFirstName(form.getCurrentService().getContactFirstName());
		formTO.setContactLastName(form.getCurrentService().getContactLastName());
		formTO.setEventDateStr(form.getCurrentService().getCurrentEvent().getEventDateStr());
		formTO.setEventTime(form.getCurrentService().getCurrentEvent().getEventTime());
		
		CreateCalendarServiceEventEvent calendarServiceEvent = new CreateCalendarServiceEventEvent();

		calendarServiceEvent.setEventTypeId(form.getCurrentService()
				.getServiceTypeId());
		calendarServiceEvent
				.setEventStatusId(PDCalendarConstants.SERVICE_EVENT_STATUS_AVAILABLE);
		calendarServiceEvent.setEventComments(form.getCurrentService()
				.getCurrentEvent().getComments());
		calendarServiceEvent.setEventTypeCategory(form.getCurrentService()
				.getServiceTypeCategory());

		CalendarContextEvent calendarContextEvent = new CalendarContextEvent();

		calendarContextEvent.setProbationOfficerId(form.getOfficerId());
		calendarContextEvent.setJuvenileId(form.getJuvenileNum());
		calendarContextEvent.setCaseFileId(form.getCaseFileId());

		calendarServiceEvent.setCalendarContextEvent(calendarContextEvent);

		// Nothing has been inserted into the database at this point.

		// inside if statement

		// Get Juvenile's birth date and full name
		String juvenileId = form.getJuvenileNum();
		UISupervisionCalendarHelper.getJuvenileNameAndBirthDate(form,
				juvenileId);
		formTO.setJuvenileFullName(form.getJuvenileFullName());
		formTO.setBirthDate(form.getBirthDate());
		// Get Officer's phone number
		String loginId = SecurityUIHelper.getJIMSLogonId();
		OfficerProfileResponseEvent officer = UIUserFormHelper
				.getUserOfficerProfile(loginId);
		String workPhone = null;
		if (officer != null) {
			if (officer.getOfficerSubTypeId() != null
					&& officer.getOfficerSubTypeId().equals(
							UIConstants.OFFICER_SUBTYPE_CLM)) {
				workPhone = officer.getWorkPhone();
			} else {
				String CLMLogonId = officer.getManagerId();
				workPhone = UIContactHelper.getOfficerProfilePhone(CLMLogonId);
			}
		}

		workPhone = UIUtil.formatPhoneNum(workPhone);
		form.setOfficerPhone(workPhone);
		formTO.setOfficerPhone(form.getOfficerPhone());
		
		Date serviceEventDate = form.getCurrentService().getCurrentEvent()
				.getStartDate();
		SimpleDateFormat fmt = new SimpleDateFormat(
				"EEEE, MM/dd/yyyy");
		String eventDateStr = fmt.format(serviceEventDate);
		form.getCurrentService().getCurrentEvent().setEventDateStr(eventDateStr);

		calendarServiceEvent
				.setSchoolCd(form.getCurrentService().getSchoolId());
		calendarServiceEvent.setSchoolDistrictId(form.getCurrentService()
				.getSchoolDistrictId());
		Collection districts = UIJuvenileHelper.fetchSchoolDistricts();
		JuvenileSchoolDistrictCodeResponseEvent school = ComplexCodeTableHelper
				.getSchool(districts, form.getCurrentService()
						.getSchoolDistrictId(), form.getCurrentService()
						.getSchoolId());
		form.getCurrentService().setSchoolName(school.getSchoolDescription());

		calendarServiceEvent.setContactLastName(form.getCurrentService()
				.getContactLastName());
		calendarServiceEvent.setContactFirstName(form.getCurrentService()
				.getContactFirstName());
		calendarServiceEvent
				.setEventStatusId(PDCalendarConstants.SERVICE_EVENT_STATUS_COMPLETED);
		calendarServiceEvent.setSexOffender(form.getCurrentService()
				.getSexOffenderRegistrantStr());
		calendarServiceEvent.setRestrictOther(form.getCurrentService()
				.getRestrictionsOther());
		List wrkList = new ArrayList(form.getOffenseInvolvedWeaponList());
		String weaponDescs = "";
		StringBuffer sb = new StringBuffer();
		for (int x = 0; x < wrkList.size(); x++) {
			InvolvedWeaponOffenseBean iwo = (InvolvedWeaponOffenseBean) wrkList
					.get(x);
			sb.append(iwo.getReferralNumber());
			sb.append("^");
			sb.append(iwo.getOffenseCategoryDescription());
			sb.append("^");
			sb.append(iwo.getOffenseDescription());
			sb.append("^");
			sb.append(iwo.getWeaponInvolvedStr());
			if ("Y".equalsIgnoreCase(iwo.getWeaponInvolvedStr())) {
				sb.append("^");
				sb.append(iwo.getTypeOfWeaponDescription());
			}
			sb.append("|");
		}
		weaponDescs = sb.toString();
		calendarServiceEvent.setWeaponDescs(weaponDescs);
		weaponDescs = null;
		
		aRequest.getSession().setAttribute("form", formTO);
		// generate report
		
		aRequest.setAttribute("isPdfSaveNeeded", "true");
		
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse,PDFReport.ADJUDICATION_NOTIFICATION);			

		byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
		if(pdfDocument!=null){
			calendarServiceEvent.setDocument(pdfDocument);
		}
		aRequest.removeAttribute("pdfSavedReport");
		// remove the pdf report attributes from session when finished saving to database
		aRequest.removeAttribute("isPdfSaveNeeded");
		
		
		//everything needed to add to calendar follows
		
		ArrayList<CalendarServiceEventResponseEvent> scheduledEvents = new ArrayList();

		{
			Collection<CalendarServiceEventResponseEvent> serviceEventList = form
					.getAllEvents();
			scheduledEvents.ensureCapacity(serviceEventList.size());
			for (CalendarServiceEventResponseEvent anEvent : serviceEventList) {
				if (anEvent.isAddConflictingEvent()) {
					scheduledEvents.add(anEvent);
				}
			}
		} // end code block

		if (scheduledEvents.isEmpty()) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
					"error.no.event.selected"));
			aRequest.setAttribute("pageType", "summary");
			saveErrors(aRequest, errors);
			return null;
		}
		
		calendarServiceEvent.setEvents(scheduledEvents);
		form.setAllEvents(scheduledEvents);
		createEvent.setCreateCalendarEvent(calendarServiceEvent); // Makes first
																	// event

		CompositeResponse response = (CompositeResponse) MessageUtil
				.postRequest(createEvent);

		// Generating Notice for Pending Appointments to JPO
		JuvenileCaseworkAlertsHelper helper = new JuvenileCaseworkAlertsHelper();
		JuvenileCasefileForm juvenileCasefileForm = (JuvenileCasefileForm) aRequest
				.getSession().getAttribute("juvenileCasefileForm");
		String jpoId = juvenileCasefileForm.getProbationOfficerLogonId().trim();

		if (form.getJuvenileName() == null) {
			form.setJuvenileName(juvenileCasefileForm.getJuvenileName()
					.getFormattedName());
		}
		
		/*
		 * Below mentioned code will send the notices only if the eventDate is ahead of the corresponding notices date.
		 */
		for (CalendarServiceEventResponseEvent anEvent : scheduledEvents) { 

			Calendar eventDate = Calendar.getInstance();
			eventDate.setTime(anEvent.getStartDatetime());
			
			ScheduleNewEventForm.Service service = form
					.getCurrentService();
			ScheduleNewEventForm.Event event = service
					.getCurrentEvent();
			StringBuffer comments = new StringBuffer();
			comments.append("Generated notice to ");
			comments.append(service.getContactFirstName());
			comments.append(" ");
			comments.append(service.getContactLastName());
			comments.append(" with ");
			comments.append(service.getSchoolDistrictName());
			comments.append(" on ");
			comments.append(event.getEventDateStr());
			comments.append(" of juvenile's adjudication on felony and/or misdemeanor A/B offense(s).");
			UIJuvenileHelper.createActivity(form.getCaseFileId(),
					ActivityConstants.SCHOOL_ADJUDICATION_NOTIFICATION,
					comments.toString());
			StringBuffer comments1 = new StringBuffer();
			comments1.append(service.getContactFirstName());
			comments1.append(" ");
			comments1.append(service.getContactLastName());
			comments1.append(" with ");
			comments1.append(service.getSchoolDistrictName());
			comments1.append(" was notified on ");
			comments1.append(event.getEventDateStr());
			comments1
					.append(" of juvenile's adjudication on felony and/or misdemeanor A/B offense(s).");
			UIJuvenileHelper
					.createActivity(
							form.getCaseFileId(),
							ActivityConstants.SCHOOL_ADJUDICATION_NOTIFICATION2,
							comments1.toString());
		}
		
		aRequest.setAttribute("pageType", "confirmation");

		return null;
		
	}
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward generateApptLetter(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ScheduleNewEventForm form = (ScheduleNewEventForm) aForm;
		AppointmentLetterBean letterBean = new AppointmentLetterBean();
		
		CreateScheduleCalendarEventsEvent createEvent = (CreateScheduleCalendarEventsEvent) EventFactory
				.getInstance(ServiceEventControllerServiceNames.CREATESCHEDULECALENDAREVENTS);
		
		letterBean.setEventDateStr(form.getCurrentService().getCurrentEvent().getEventDateStr());
		letterBean.setEventTime(form.getCurrentService().getCurrentEvent().getEventTime());
		letterBean.setJuvenileFullName(form.getJuvenileName());
		letterBean.setOfficeHours(form.getOfficerHours());
		letterBean.setOfficerPhone(form.getOfficerPhone());
		letterBean.setOfficerFax(form.getOfficerFax());
		letterBean.setPetitionNumber(form.getCurrentService().getPetitionNumber());
		letterBean.setLetterType(form.getCurrentService().getLetterType());
		//get the court number and date
		JuvenileCasefileReferralsResponseEvent resp = getCourtNumber(form);
		if(resp!=null)
		{
			letterBean.setCourtNumber(resp.getCourtId());
			letterBean.setCourtDate(resp.getCourtDate());
		}
		CreateCalendarServiceEventEvent calendarServiceEvent = new CreateCalendarServiceEventEvent();

		calendarServiceEvent.setEventTypeId(form.getCurrentService()
				.getServiceTypeId());
		calendarServiceEvent
				.setEventStatusId(PDCalendarConstants.SERVICE_EVENT_STATUS_AVAILABLE);
		calendarServiceEvent.setEventComments(form.getCurrentService()
				.getCurrentEvent().getComments());
		calendarServiceEvent.setEventTypeCategory(form.getCurrentService()
				.getServiceTypeCategory());

		CalendarContextEvent calendarContextEvent = new CalendarContextEvent();

		calendarContextEvent.setProbationOfficerId(form.getOfficerId());
		calendarContextEvent.setJuvenileId(form.getJuvenileNum());
		calendarContextEvent.setCaseFileId(form.getCaseFileId());

		calendarServiceEvent.setCalendarContextEvent(calendarContextEvent);
		
		aRequest.getSession().setAttribute("form", letterBean);
		// generate report
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		aRequest.setAttribute("isPdfSaveNeeded", "true");
		pdfManager.createPDFReport(aRequest, aResponse,PDFReport.APPOINTMENT_LETTER);		
		

		byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
		if(pdfDocument!=null){
			calendarServiceEvent.setDocument(pdfDocument);
		}
		aRequest.removeAttribute("pdfSavedReport");
		// remove the pdf report attributes from session when finished saving to database
		aRequest.removeAttribute("isPdfSaveNeeded");
		
		//everything needed to add to calendar follows
		
		ArrayList<CalendarServiceEventResponseEvent> scheduledEvents = new ArrayList();

		{
			Collection<CalendarServiceEventResponseEvent> serviceEventList = form
					.getAllEvents();
			scheduledEvents.ensureCapacity(serviceEventList.size());
			for (CalendarServiceEventResponseEvent anEvent : serviceEventList) {
				if (anEvent.isAddConflictingEvent()) {
					scheduledEvents.add(anEvent);
				}
			}
		} // end code block

		if (scheduledEvents.isEmpty()) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
					"error.no.event.selected"));
			aRequest.setAttribute("pageType", "summary");
			saveErrors(aRequest, errors);
			return null;
		}
		calendarServiceEvent.setEvents(scheduledEvents);
		form.setAllEvents(scheduledEvents);
		createEvent.setCreateCalendarEvent(calendarServiceEvent); // Makes first
																	// event

		CompositeResponse response = (CompositeResponse) MessageUtil
				.postRequest(createEvent);
		
		
		StringBuffer comments = new StringBuffer();
		comments.append(form.getCurrentService().getLetterType());
		String[] ref = form.getCurrentService().getControllingReferral().split("-");
		comments.append(" APPOINTMENT LETTER generated for " + ref[0] + " on " + DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		if(form.getCurrentService().getLetterType().equalsIgnoreCase("FELONY"))
			comments.append(". Youth to report for office interview on " + form.getCurrentService().getCurrentEvent().getEventDateStr() + ".");
		else
			comments.append(". Youth to appear in court on " + formatter.format(resp.getCourtDate()) + ".");		
		
		UIJuvenileHelper.createActivity(form.getCaseFileId(),
				ActivityConstants.COURT_APPOINTMENT_LETTER_GENERATED,
				comments.toString());
		return null;
	}

	private JuvenileCasefileReferralsResponseEvent getCourtNumber(ScheduleNewEventForm form)
	{
		Iterator refs = form.getCurrentService().getReferrals().iterator();
		String[] ref = form.getCurrentService().getControllingReferral().split("-");
		while(refs.hasNext())
		{
			 JuvenileCasefileReferralsResponseEvent resp = (JuvenileCasefileReferralsResponseEvent) refs.next();
			 String[] respRef = resp.getReferralNumber().split("-");
			 if(respRef[0].equals(ref[0]))
				 return resp;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap() ;
		buttonMap.put( "button.back", "back" ) ;
		buttonMap.put( "button.cancel", "cancel" ) ;
		buttonMap.put( "button.finish", "goAheadAndSchedule" ) ;
		buttonMap.put( "button.generateDocument", "goAheadAndScheduleBFO" ) ;
		buttonMap.put("button.generateDocumentBFO", "goAheadAndScheduleBFO");
		//12253 user story changes starts
		buttonMap.put( "button.generateSpanishLetter", "goAheadAndSchedule") ;
		buttonMap.put( "button.generateEnglishLetter", "goAheadAndSchedule") ;
		//12253 user story changes ends
		buttonMap.put( "button.createNewReferral", "createNewReferral" ) ;
		buttonMap.put( "button.returnToCalendar", "returnToCalendar" ) ;
		buttonMap.put("button.generateAppointmentLetter", "generateApptLetter");
		return buttonMap ;
	}

	@Override
	protected void addButtonMapping(Map keyMap) {
		// TODO Auto-generated method stub
		
	}
}