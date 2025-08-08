// Source file:
// C:\\views\\MJCW\\app\\src\\ui\\juvenilecase\\programreferral\\action\\DisplayProgramReferralDetailsAction.java

package ui.juvenilecase.programreferral.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.administerserviceprovider.GetProgramByProgramIdEvent;
import messaging.administerserviceprovider.GetProviderProgramsEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.calendar.GetProgramAttendanceEvent;
import messaging.calendar.GetProgramReferralServiceEventsEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileJISResponseEvent;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.programreferral.GetJuvenileProgramReferralAssignmentHistoryEvent;
import messaging.programreferral.GetProgramReferralDetailsEvent;
import messaging.programreferral.TransferProgramReferralEvent;
import messaging.programreferral.reply.ProgramReferralAssignmentHistoryResponseEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileProgramReferralControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.ProgramReferralConstants;
import naming.ServiceEventControllerServiceNames;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.JuvenileAbuseForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.programreferral.ProgramReferralAction;
import ui.juvenilecase.programreferral.UIProgramReferralBean;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.programreferral.form.ProgramReferralForm;
import ui.security.SecurityUIHelper;

public class DisplayProgramReferralDetailsAction extends JIMSBaseAction
{

    /**
     * @roseuid 463BA573033A
     */
    public DisplayProgramReferralDetailsAction()
    {
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49960111
     */
    public ActionForward displayReferralDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String referralId = aRequest.getParameter("referralId");
	ProgramReferralForm form = (ProgramReferralForm) aForm;
	UIProgramReferralBean programReferral = null;
	if (notNullNotEmptyString(referralId))
	{
	    GetProgramReferralDetailsEvent gpdt = (GetProgramReferralDetailsEvent) EventFactory.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALDETAILS);
	    gpdt.setProgramReferralId(referralId);
	    CompositeResponse compositeResponse = (CompositeResponse) MessageUtil.postRequest(gpdt);

	    ProgramReferralResponseEvent respDetail = (ProgramReferralResponseEvent) MessageUtil.filterComposite(compositeResponse, ProgramReferralResponseEvent.class);
	    if (respDetail != null)
	    {
		programReferral = new UIProgramReferralBean(respDetail);
		String outComeSubcatCd = programReferral.getOutComeSubcategoryCd();
		if (outComeSubcatCd != null && !"".equalsIgnoreCase(outComeSubcatCd))
		{
		    List temp1 = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_OUTCOME_REF_DESC");
		    for (int x = 0; x < temp1.size(); x++)
		    {
			JuvenileCodeTableChildCodesResponseEvent joscre = (JuvenileCodeTableChildCodesResponseEvent) temp1.get(x);
			if (outComeSubcatCd.equalsIgnoreCase(joscre.getCode()))
			{
			    programReferral.setOutComeSubcategoryDescription(joscre.getDescription());
			    break;
			}
		    }
		    temp1 = null;
		    outComeSubcatCd = null;
		}
		programReferral.setCurrentUserType(ProgramReferralConstants.JPO_USER);
		programReferral.setNextPossibleActions(programReferral.getReferralState().getPossibleNextActions(ProgramReferralConstants.JPO_USER));
	    }
	}

	if (programReferral != null)
	{
	    
	    ProviderProgramResponseEvent programDetail = this.getProviderProgramDetails(programReferral.getProviderProgramId());
	    form.setTransferredProgRef(programDetail.getTransferredProgRef());
	    
	    UIJuvenileCaseworkHelper.populateJuvenileCasefileForm(UIJuvenileCaseworkHelper.getHeaderForm(aRequest, true), programReferral.getCasefileId());
	    /*Enumeration enuma= aRequest.getAttributeNames();
	    //	JuvenileCasefileForm casefile1= (JuvenileCasefileForm) aRequest.getAttribute("JUVENILE_CASEFILE_FORM");
	    JuvenileCasefileForm casefileForm1 = UIJuvenileHelper.getJuvenileCasefileForm( aRequest ) ;
	    String juvenileCaseform = aRequest.getParameter("JuvenileCasefileForm");*/
	    
	    //Attendance Events
	    GetProgramAttendanceEvent gpae = (GetProgramAttendanceEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMATTENDANCE);

	    gpae.setProgramId(programReferral.getProviderProgramId());
	    gpae.setJuvenileNum(programReferral.getJuvenileId());

	    CompositeResponse compResponse = (CompositeResponse) MessageUtil.postRequest(gpae);
	    List<ServiceEventAttendanceResponseEvent> attendanceEvents = (List<ServiceEventAttendanceResponseEvent>) MessageUtil.compositeToCollection(compResponse, ServiceEventAttendanceResponseEvent.class);

	    if (attendanceEvents != null)
	    {
		Collections.sort((List<ServiceEventAttendanceResponseEvent>) attendanceEvents);
		programReferral.setExistingReferrals(attendanceEvents);
	    }
	    
	    //Program Referral Service Events
	    GetProgramReferralServiceEventsEvent gprs = (GetProgramReferralServiceEventsEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMREFERRALSERVICEEVENTS);

	    gprs.setJuvenileNum(programReferral.getJuvenileId());
	    gprs.setProgramId(programReferral.getProviderProgramId());
	    gprs.setProgramReferralId(programReferral.getReferralId());

	    CompositeResponse compositeResponse = (CompositeResponse) MessageUtil.postRequest(gprs);
	    List calendarEvents = (List) MessageUtil.compositeToCollection(compositeResponse, CalendarServiceEventResponseEvent.class);
	    
	    float creditHours = 0;
	    float totalCreditHours = 0;
	    List<ServiceEventAttendanceResponseEvent> eventsAttended = new ArrayList<ServiceEventAttendanceResponseEvent>();
	    if (calendarEvents != null)
	    {
		Collections.sort((List) calendarEvents);

		List activeCalendarEvents = new ArrayList();
		Iterator<CalendarServiceEventResponseEvent> iter = calendarEvents.iterator();
		while (iter.hasNext())
		{
		    CalendarServiceEventResponseEvent event = iter.next();
		    if (!UIConstants.STATUS_CODE_CANCELLED.equalsIgnoreCase(event.getEventStatusCode()))
		    {
			event.setEventStatus(CodeHelper.getCodeDescription(PDCodeTableConstants.SERVEVENT_STATUS, event.getEventStatusCode()));
			activeCalendarEvents.add(event);
		    }		    
		    
		    if(programReferral.getExistingReferrals() != null && programReferral.getExistingReferrals().size() > 0){			
			
			Iterator<ServiceEventAttendanceResponseEvent> iterExistingRef = programReferral.getExistingReferrals().iterator();
				
			while(iterExistingRef.hasNext()){
				
				ServiceEventAttendanceResponseEvent attendanceEvent = (ServiceEventAttendanceResponseEvent)iterExistingRef.next();
				if(attendanceEvent != null){
				    
				    if((attendanceEvent.getAttendanceStatusCd() != null && attendanceEvent.getAttendanceStatusCd().equals("AT")) && 
					(attendanceEvent.getProgramReferralId() != null && attendanceEvent.getProgramReferralId().equals(event.getProgramReferralId()) && 
						(attendanceEvent.getServiceEventId() != null && attendanceEvent.getServiceEventId().equals(event.getEventId())))){
					
					creditHours = (float) UIProgramReferralHelper.calculateTimeDiff(attendanceEvent.getStartDateTime(), attendanceEvent.getEndDateTime());
    				    	attendanceEvent.setCreditHours(creditHours);
            				totalCreditHours += creditHours; 
            				
        				eventsAttended.add(attendanceEvent);
            				
            				break;
				    } 
				    
				    
				    if((attendanceEvent.getAttendanceStatusCd() != null && !attendanceEvent.getAttendanceStatusCd().equals("AT")) && 
						(attendanceEvent.getProgramReferralId() != null && attendanceEvent.getProgramReferralId().equals(event.getProgramReferralId()) && 
							(attendanceEvent.getServiceEventId() != null && attendanceEvent.getServiceEventId().equals(event.getEventId())))){
	            				
	        			eventsAttended.add(attendanceEvent);
	            				
	            			break;
				    } 
				    
				}
													
			    }		
					
		    }	
		    
		}
		
		programReferral.setTotalCreditHours(totalCreditHours);
		programReferral.setExistingReferrals(eventsAttended);
		programReferral.setJuvenileEvents(activeCalendarEvents);
	    }  

	    GetJuvenileProgramReferralAssignmentHistoryEvent gjprahe = (GetJuvenileProgramReferralAssignmentHistoryEvent) EventFactory.getInstance(JuvenileProgramReferralControllerServiceNames.GETJUVENILEPROGRAMREFERRALASSIGNMENTHISTORY);

	    gjprahe.setProgramReferralId(programReferral.getReferralId());

	    compositeResponse = (CompositeResponse) MessageUtil.postRequest(gjprahe);
	    List<ProgramReferralAssignmentHistoryResponseEvent> prAssignmentHistory = (List) MessageUtil.compositeToCollection(compositeResponse, ProgramReferralAssignmentHistoryResponseEvent.class);
	    //Task 39399 

	    if (prAssignmentHistory != null)
	    {
		int size = prAssignmentHistory.size();
		Iterator<ProgramReferralAssignmentHistoryResponseEvent> prAssignHistoryItr = prAssignmentHistory.iterator();
		List<ProgramReferralAssignmentHistoryResponseEvent> prAssignmentHistoryNew = new ArrayList<ProgramReferralAssignmentHistoryResponseEvent>();
		while (prAssignHistoryItr.hasNext())
		{
		    ProgramReferralAssignmentHistoryResponseEvent prAssgnHistory = prAssignHistoryItr.next();
		    String superVisionID = prAssgnHistory.getCasefileId();

		    GetJuvenileCasefileEvent getCasefileEvent = (GetJuvenileCasefileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);
		    getCasefileEvent.setSupervisionNumber(superVisionID);
		    CompositeResponse response = MessageUtil.postRequest(getCasefileEvent);
		    JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent) MessageUtil.filterComposite(response, JuvenileCasefileResponseEvent.class);

		    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		    prAssgnHistory.setCasefileAssignDate(sdf.format(casefile.getAssignmentDate()));
		    if (casefile.getClosingDate() != null)
		    {
			prAssgnHistory.setCasefileEndDate(sdf.format(casefile.getClosingDate()));
		    }
		    if (casefile.getControllingReferral() != null && !casefile.getControllingReferral().isEmpty())
		    {
			prAssgnHistory.setCntrlRefNum(casefile.getControllingReferral());
			programReferral.setControllingReferralNum(casefile.getControllingReferral());
		    }
		    else
			if (casefile.getControllingReferralId() != null && !casefile.getControllingReferralId().isEmpty())
			{
			    prAssgnHistory.setCntrlRefNum(casefile.getControllingReferralId());
			    programReferral.setControllingReferralNum(casefile.getControllingReferralId());
			}
			else
			{
			    prAssgnHistory.setCntrlRefNum(UIProgramReferralHelper.getLowestCtrlngRefNbr((String) casefile.getSupervisionNum()));
			    programReferral.setControllingReferralNum(prAssgnHistory.getCntrlRefNum());
			}
		    prAssignmentHistoryNew.add(prAssgnHistory);
		}
		Collections.sort((List) prAssignmentHistoryNew);
		programReferral.setPrAssignmentHistoryList(prAssignmentHistoryNew);
	    }


	    HttpSession session = aRequest.getSession();
	    JuvenileBriefingDetailsForm juvenileBriefingForm = null;

	    juvenileBriefingForm = UIJuvenileHelper.getJuvBriefingDetailsForm(aRequest);

	    if (juvenileBriefingForm == null)
	    {

		juvenileBriefingForm = new JuvenileBriefingDetailsForm();
		setProfileDetail(programReferral.getJuvenileId(), juvenileBriefingForm);
		session.setAttribute("juvenileBriefingDetailsForm", juvenileBriefingForm);
	    }
	}// if  programReferral != null

	form.setProgramReferral(programReferral);
	String tAction = form.getAction();
	form.setAction(UIConstants.VIEW);
	programReferral.setCurrentAction(ProgramReferralAction.UPDATE);
	ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
	//		defect #71970 change	
	//		if( tAction != null &&  !tAction.equals(UIConstants.CANCEL) )
	//		{
	//			 forward = aMapping.findForward(UIConstants.SUCCESS);
	//		}
	//		else
	if (tAction != null && tAction.equals(UIConstants.CLOSE) && programReferral.getReferralStatus().equals(ProgramReferralConstants.CLOSED)) // ADDED PROGRAM REFERRAL STATUS CHECK FOR BUG #40488
	{
	    forward = aMapping.findForward(UIConstants.CANCEL);
	}

	return forward;
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49960111
     */
    public ActionForward createProgramReferral(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
	ProgramReferralForm form = (ProgramReferralForm) aForm;
	ActionForward forward = aMapping.findForward(UIConstants.FAILURE);
	JuvenileCasefileForm juvForm = (JuvenileCasefileForm) getSessionForm(aMapping, aRequest, "juvenileCasefileForm", true);
	String casefileId = juvForm.getSupervisionNum();
	if (casefileId == null || "".equals(casefileId))
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No casefile supplied to create referral"));
	    saveErrors(aRequest, errors);
	}
	else
	{
	    String controllingReferralNum = UIConstants.EMPTY_STRING;
	    List spTemp = ComplexCodeTableHelper.getAllServiceProviders();
	    List spActives = new ArrayList();
	    // keep only the active service providers -- defect 71046	
	    for (int x = 0; x < spTemp.size(); x++)
	    {
		ServiceProviderResponseEvent spt = (ServiceProviderResponseEvent) spTemp.get(x);
		if ("A".equalsIgnoreCase(spt.getStatusId()))
		{
		    spActives.add(spt);
		}
	    }
	    /** begin program retrieval */
	    GetProviderProgramsEvent prEvent = (GetProviderProgramsEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETPROVIDERPROGRAMS);
	    prEvent.setServiceProviderId("");
	    prEvent.setProgramName("");
	    prEvent.setStateProgramCode("");
	    prEvent.setTargetInterventionId("");
	    prEvent.setStatusId("A");
	    prEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());

	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch.postEvent(prEvent);
	    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	    MessageUtil.processReturnException(compositeResponse);
	    Collection coll = MessageUtil.compositeToCollection(compositeResponse, ProviderProgramResponseEvent.class);
	    Collections.sort((ArrayList) coll);
	    form.setProgramNameList(coll);
	    form.setProgramNames(new ArrayList());
	    /** end program retrieval */
	    for (int x = 0; x < spActives.size(); x++)
	    {
		ServiceProviderResponseEvent sppe = (ServiceProviderResponseEvent) spActives.get(x);
		//System.out.println("(" + sppe.getJuvServProviderId() + ") " + sppe.getJuvServProviderName());
		List workList = new ArrayList();
		Iterator itr2 = coll.iterator();
		while (itr2.hasNext())
		{
		    ProviderProgramResponseEvent ppre = (ProviderProgramResponseEvent) itr2.next();
		    if (ppre.getServiceProviderId().equalsIgnoreCase(sppe.getJuvServProviderId()))
		    {
			//System.out.println("   (" + sppe.getJuvServProviderId() + ") " + ppre.getProgramCodeId() + "  " + ppre.getProgramName());			
			
			if(!ppre.getIsProgramDiscontinued()) //US 174149
			{
			    ServiceResponseEvent spe = new ServiceResponseEvent();
			    spe.setProgramId(ppre.getProviderProgramId());
			    spe.setProgramName(ppre.getProgramName());
			    spe.setServiceProviderId(ppre.getServiceProviderId());
			    workList.add(spe);
			}	    			
		    }
		}
		sppe.setServiceResponseEvents(workList);
	    }
	    form.setServiceProviderList(spActives);
	    /** Get controlling referral number */
	    controllingReferralNum = UIProgramReferralHelper.getControllingRefNumber(casefileId);

	    UIProgramReferralBean programReferral = new UIProgramReferralBean();
	    programReferral.setBeginDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	    //			programReferral.setReferralStatus("TENTATIVE REFERRED");
	    //			programReferral.setReferralId("TRN");
	    programReferral.setCasefileId(casefileId);
	    programReferral.setJuvenileLastName(juvForm.getJuvenileFullName()); // using last name because no full name exist in bean
	    programReferral.setControllingReferralNum(controllingReferralNum);
	    form.setProgramReferral(programReferral);
	    forward = aMapping.findForward(UIConstants.CREATE);
	}
	return forward;
    }

    /*
     * @param str
     * @return
     */
    private boolean notNullNotEmptyString(String str)
    {
	return (str != null && str.length() > 0);
    }

    /**
     * @param juvenileNum
     * @param form
     */
    private void setProfileDetail(String juvenileNum, JuvenileBriefingDetailsForm form)
    {
	GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

	reqProfileMain.setJuvenileNum(juvenileNum);
	reqProfileMain.setFromProfile(form.getFromProfile());
	CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
	JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);

	form.setJisInfo(new JuvenileJISResponseEvent());
	if (juvProfileRE != null)
	{
	    form.setProfileDetail(juvProfileRE);
	    form.setProfileDescriptions();

	    if (juvProfileRE.getDateOfBirth() != null)
	    { // Get age based on
	      // year
		int age = UIUtil.getAgeInYears(juvProfileRE.getDateOfBirth());
		if (age > 0)
		{
		    form.setAge(String.valueOf(age));
		}
		else
		{
		    form.setAge(UIConstants.EMPTY_STRING);
		}
	    }
	    Collection jisResps = juvProfileRE.getJisInfo();
	    if (jisResps != null)
	    {
		Collections.sort((List) jisResps);
		Iterator jisIter = jisResps.iterator();
		if (jisIter.hasNext())
		{
		    JuvenileJISResponseEvent jis = (JuvenileJISResponseEvent) jisIter.next();
		    form.setJisInfo(jis);
		}
	    }

	    form.setInMentalHealthServices(juvProfileRE.isMentalHealthServices());
	    //U.S 88526
	    if (juvProfileRE.getHispanic() != null)
	    {
		if (juvProfileRE.getHispanic().equalsIgnoreCase("Y"))
		{
		    form.setHispanic(UIConstants.YES_FULL_TEXT);
		}
		else
		{
		    form.setHispanic(UIConstants.NO_FULL_TEXT);
		}
	    }
	    else
	    {
		form.setHispanic(UIConstants.EMPTY_STRING);
	    }
	    
	    if (juvProfileRE.getIsUSCitizen() != null)
	    {
		if (juvProfileRE.getIsUSCitizen().equalsIgnoreCase("Y"))
		{
		    form.setIsUsCitizen(UIConstants.YES_FULL_TEXT);
		}
		else
		{
		    form.setIsUsCitizen(UIConstants.NO_FULL_TEXT);
		}
	    }
	    else
	    {
		form.setIsUsCitizen(UIConstants.EMPTY_STRING);
	    }
	}
    }
    
    private ProviderProgramResponseEvent getProviderProgramDetails( String providerProgramId )
	{
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		GetProgramByProgramIdEvent reqEvent = (GetProgramByProgramIdEvent)
				EventFactory.getInstance( ServiceProviderControllerServiceNames.GETPROGRAMBYPROGRAMID ) ;

		reqEvent.setProviderProgramId( providerProgramId ) ;

		dispatch.postEvent( reqEvent ) ;
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply() ;
		ProviderProgramResponseEvent resp = (ProviderProgramResponseEvent)
				MessageUtil.filterComposite( compositeResponse, ProviderProgramResponseEvent.class ) ;

		return resp ;

	}

    /* (non-Javadoc)
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.link", "displayReferralDetails");
	keyMap.put("button.details", "showEventDetails");
	keyMap.put("button.createProgramReferral", "createProgramReferral");
    }
}
