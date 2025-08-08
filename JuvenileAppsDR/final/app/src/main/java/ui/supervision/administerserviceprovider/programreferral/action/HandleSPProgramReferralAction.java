// Source file:
// C:\\views\\MJCW\\app\\src\\ui\\supervision\\administerserviceprovider\\programreferral\\action\\HandleSPProgramReferralAction.java

package ui.supervision.administerserviceprovider.programreferral.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.administerserviceprovider.GetServiceProviderEvent;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.GetProgramAttendanceEvent;
import messaging.calendar.GetProgramReferralServiceEventsEvent;
import messaging.calendar.ServiceEventAttribute;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.interviewinfo.CreateSocialHistoryReportEvent;
import messaging.interviewinfo.GetInterviewReportEvent;
import messaging.interviewinfo.GetInterviewReportsEvent;
import messaging.interviewinfo.GetSocialHistoryReportDataEvent;
import messaging.interviewinfo.reply.InterviewReportHeaderResponseEvent;
import messaging.interviewinfo.reply.InterviewReportResponseEvent;
import messaging.interviewinfo.reply.SocialHistoryReportDataResponseEvent;
import messaging.interviewinfo.to.SocialHistoryReportDataTO;
import messaging.interviewinfo.to.SupervisionRuleTO;
import messaging.interviewinfo.to.TraitTO;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.notification.NotificationMessage;
import messaging.notification.SendProgramReferralNotificationEvent;
import messaging.programreferral.GetProgramReferralDetailsEvent;
import messaging.programreferral.SaveProgramReferralEvent;
import messaging.programreferral.reply.ProgramReferralCommentResponseEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.security.IUserInfo;
import mojo.km.security.helper.SecurityUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.ActivityConstants;
import naming.JuvenileCasefileNotificationControllerServiceNames;
import naming.JuvenileInterviewInfoControllerServiceNames;
import naming.JuvenileProgramReferralControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.ProgramReferralConstants;
import naming.ServiceEventControllerServiceNames;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.contact.UIContactHelper;
import ui.juvenilecase.JuvenileDetentionFacility;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.programreferral.ProgramReferralAction;
import ui.juvenilecase.programreferral.UIProgramReferralBean;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.programreferral.form.ProgramReferralForm;
import ui.security.SecurityUIHelper;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;

public class HandleSPProgramReferralAction extends JIMSBaseAction 
{
	/**
	 * @roseuid 463BA5490358
	 */
	public HandleSPProgramReferralAction() 
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
	public ActionForward acceptWithChanges( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm;		

		UIProgramReferralBean programReferral = form.getProgramReferral();
		programReferral.setEndDateStr(""); // Bug no: #46218
		if( programReferral != null )
		{
			programReferral.setCurrentAction(ProgramReferralAction.ACCEPTWITHCHANGES);
			programReferral.executeAction();
			form.setAction(UIConstants.UPDATE);
		}

		return( aMapping.findForward(UIConstants.SUCCESS) );		
	}
	
	// Task # 52635 changes
		public ActionForward acceptAndClose( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm ;
		UIProgramReferralBean programReferral = form.getProgramReferral() ;
		programReferral.setEndDateStr(""); 
		programReferral.setCurrentAction( ProgramReferralAction.ACCEPTANDCLOSE ) ;
		programReferral.setOutComeCd("S");
		programReferral.executeAction();
		form.setAction( UIConstants.UPDATE ) ;

		return( aMapping.findForward( UIConstants.SUCCESS ) ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward reject( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm;		

		UIProgramReferralBean programReferral = form.getProgramReferral();
		if( programReferral != null )
		{
			programReferral.setCurrentAction(ProgramReferralAction.REJECT);
			programReferral.executeAction();
			form.setAction(UIConstants.UPDATE);
		}

		return( aMapping.findForward(UIConstants.SUCCESS) );		
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward addComments( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm;		

		UIProgramReferralBean programReferral = form.getProgramReferral();
		if( programReferral != null )
		{
			programReferral.setCurrentAction(ProgramReferralAction.ADDCOMMENTS);
			form.setAction(UIConstants.UPDATE);
		}
		
		return( aMapping.findForward(UIConstants.SUCCESS) );		
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward accept( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm;		

		UIProgramReferralBean programReferral = form.getProgramReferral();
		programReferral.setEndDateStr(""); // Bug no: #46218
		if( programReferral != null )
		{
			programReferral.setCurrentAction(ProgramReferralAction.ACCEPT);
			programReferral.executeAction();
			form.setAction(UIConstants.UPDATE);
		}
		
		return( aMapping.findForward(UIConstants.SUCCESS) );		
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward submitChanges( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm;

		UIProgramReferralBean programReferral = form.getProgramReferral();
		if( programReferral != null )
		{
			programReferral.executeAction();
			form.setAction(UIConstants.SUMMARY);
		}
		
		return( aMapping.findForward(UIConstants.SUCCESS) );			
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward finish( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm;
		UIProgramReferralBean programReferral = form.getProgramReferral();

		//<KISHORE>JIMS200057235	MJCW Sch Cal Even and View Cal - Attend Status is incorrect
		if( programReferral != null )
		{
			GetProgramReferralServiceEventsEvent gprs = (GetProgramReferralServiceEventsEvent)
			EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMREFERRALSERVICEEVENTS);

			gprs.setJuvenileNum(programReferral.getJuvenileId());
			gprs.setProgramId(programReferral.getProviderProgramId());
			gprs.setProgramReferralId(programReferral.getReferralId());

			CompositeResponse compositeResponse = (CompositeResponse)MessageUtil.postRequest(gprs);			
			List calendarEvents = (List)
			MessageUtil.compositeToCollection(compositeResponse,CalendarServiceEventResponseEvent.class);

			if( calendarEvents != null )
			{
				Collections.sort( (List)calendarEvents );
				programReferral.setJuvenileEvents(calendarEvents);
			}

			if( programReferral.getCurrentAction() != null )
			{
				programReferral.executeAction();
			}
			else
			{
				sendToErrorPage( aRequest, "error.generic", "Action type (Accept, Reject, Add Comments, and so on) not specified") ;
				return( aMapping.findForward(UIConstants.SUCCESS) );			
			}

			//bug fix 22872 starts
			if(programReferral.getControllingReferralNum()==null || programReferral.getControllingReferralNum().isEmpty()){
				String	controlRefNo = UIProgramReferralHelper.getControllingRefNumber(programReferral.getCasefileId()) ;
				if(controlRefNo!=null && !controlRefNo.isEmpty()){
					programReferral.setControllingReferralNum(controlRefNo.substring(0,4));
				}
			}
			//bug fix 22872 ends
			
			SaveProgramReferralEvent saveRefEvent = programReferral.getSaveProgramReferralEvent();		
			MessageUtil.postRequest(saveRefEvent);
			if (programReferral.getReferralTaskInfo()!= null)
			{
				programReferral.getReferralTaskInfo().createTask();
			}
			
			if (programReferral.getReferralNoticeInfo()!= null)
			{
				programReferral.getReferralNoticeInfo().sendNotification();
			}
			if (programReferral.getCurrentAction().equals(ProgramReferralAction.ADDCOMMENTS )) {
				SendProgramReferralNotificationEvent event = (SendProgramReferralNotificationEvent) EventFactory
				.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDPROGRAMREFERRALNOTIFICATION);
				event.setSubject("New Program Referral comments for " + programReferral.getJuvenileFirstName() +
						" " + programReferral.getJuvenileLastName());
				String officerId = programReferral.getOfficerId();
				event.setIdentity(UIContactHelper.getOfficerLogonId(officerId));

				String s = "The service provider added comments for the  " + programReferral.getProviderProgramName() +
					" program referral on "  + DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1) +
					" for " + programReferral.getCasefileId();

				event.setNotificationMessage(s);
				CreateNotificationEvent notificationEvent = (CreateNotificationEvent)
					EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
				notificationEvent.setNotificationTopic("MJCW.SP.PROGRAM.REFERRAL.COMMENT.ALERT");
				notificationEvent.setSubject(event.getSubject());
				notificationEvent.addIdentity("respEvent", (IAddressable)event);
				notificationEvent.addContentBean(event);

				IDispatch dispatchNotification = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatchNotification.postEvent(notificationEvent);
			}
		}
		
		form.setAction(UIConstants.CONFIRM);
		return( aMapping.findForward(UIConstants.SUCCESS) );			
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward showEventDetails( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		String eventId = aRequest.getParameter("eventId");
		
		if( notNullNotEmptyString( eventId ) )
		{
			ProgramReferralForm form = (ProgramReferralForm)aForm;
			ArrayList attributes = new ArrayList();
			ServiceEventAttribute sa = new ServiceEventAttribute();
			sa.setServiceEventId(new Integer(eventId));
			attributes.add(sa);
			
			GetCalendarServiceEventsEvent gce =(GetCalendarServiceEventsEvent) 
					EventFactory.getInstance( ServiceEventControllerServiceNames.GETCALENDARSERVICEEVENTS);
			
			CalendarContextEvent context = new CalendarContextEvent();
			context.setJuvenileId(form.getProgramReferral().getJuvenileId());
			gce.setCalendarContextEvent(context);
			
			gce.setCalendarAttributes(attributes);
			
			CompositeResponse response = MessageUtil.postRequest(gce);
			CalendarServiceEventResponseEvent resp = (CalendarServiceEventResponseEvent)
					MessageUtil.filterComposite(response, CalendarServiceEventResponseEvent.class);
			if (resp != null)
			{
			    if(resp.getInstructorName() != null) {
				    String name = resp.getInstructorName();

				    // Remove anything after the parenthesis only if the field contains one
				    if (name != null && name.contains("(")) {
					name = name.replaceAll("\\s*\\(.*\\)", "");
				    }
				   
				    resp.setInstructorName(name);
				}
			    
				form.setCurrentServiceEvent(resp);
			}			
		}
		
		return( aMapping.findForward(UIConstants.DETAIL_SUCCESS) );
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward populateOtherReferral( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		String referralId = aRequest.getParameter("referralId");

		UIProgramReferralBean programReferral = null;
		if( notNullNotEmptyString( referralId ) )
		{					
			GetProgramReferralDetailsEvent gpdt = (GetProgramReferralDetailsEvent)
					EventFactory.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALDETAILS);
			gpdt.setProgramReferralId(referralId);

			CompositeResponse compositeResponse = MessageUtil.postRequest(gpdt);
			ProgramReferralResponseEvent respDetail = (ProgramReferralResponseEvent)
					MessageUtil.filterComposite(compositeResponse,ProgramReferralResponseEvent.class);
			if (respDetail != null)
			{
				programReferral = new UIProgramReferralBean(respDetail);
				programReferral.setCurrentUserType(ProgramReferralConstants.SP_USER);				
			}
		}
		
		if (programReferral != null)
		{
			GetProgramReferralServiceEventsEvent gprs = (GetProgramReferralServiceEventsEvent)
					EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMREFERRALSERVICEEVENTS);
			gprs.setJuvenileNum(programReferral.getJuvenileId());
			gprs.setProgramId(programReferral.getProviderProgramId());
			gprs.setProgramReferralId(programReferral.getReferralId());
			
			CompositeResponse compositeResponse = MessageUtil.postRequest(gprs);
			List calendarEvents = (List)MessageUtil.compositeToCollection(
					compositeResponse,CalendarServiceEventResponseEvent.class);
					
			if( calendarEvents != null )
			{
				if( calendarEvents.size() > 1 )
				{
					Collections.sort( calendarEvents );
				}
				programReferral.setJuvenileEvents(calendarEvents);
			}

			GetProgramAttendanceEvent gpae = (GetProgramAttendanceEvent) 
					EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMATTENDANCE);
			
			gpae.setProgramId(programReferral.getProviderProgramId());
			gpae.setJuvenileNum(programReferral.getJuvenileId());

			compositeResponse = MessageUtil.postRequest(gpae);
			List attendanceEvents =(List)MessageUtil.compositeToCollection(
					compositeResponse,ServiceEventAttendanceResponseEvent.class);

			if (attendanceEvents != null)
			{
				if( attendanceEvents.size() > 1 )
				{
					Collections.sort( attendanceEvents );
				}
				programReferral.setExistingReferrals(attendanceEvents);
			}
		}// if programReferral != null

		((ProgramReferralForm)aForm).setOtherProgramReferral(programReferral);		

		return( aMapping.findForward(UIConstants.LIST_SUCCESS) );									
	}	

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward returnToReferralList( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return( aMapping.findForward(UIConstants.CANCEL) );
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward notifyClm( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
        //<KISHORE>JIMS200060329 : Email method from SP to CLM (UI) - KK
		return( aMapping.findForward("notifyClm") );
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward sendToClm( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
	    
	    	String providerName = "";
	    	String spName = "";
	    	String spID = "";
	    	String logonEmail = "";
	    	String phoneNum = "";
	    	String emailAddress = "";
	    	
	    	IUserInfo userInfo = SecurityUtil.getCurrentUser();
		if(userInfo instanceof ServiceProviderContactResponseEvent)
		{
		    ServiceProviderContactResponseEvent resp = (ServiceProviderContactResponseEvent) userInfo;
		    providerName = resp.getFirstName() + "" + resp.getLastName();
		    spID 	 = resp.getJuvServProviderProfileId();
		    logonEmail 	 = resp.getJIMS2LogonId();
			
		}
		String adminContactId = UIProgramReferralHelper.getSPAdminContactId(spID);
		
		GetServiceProviderEvent spEvent = (GetServiceProviderEvent) EventFactory
			.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDER);
		spEvent.setServiceProviderId( spID );

		CompositeResponse compositeResponse = (CompositeResponse) MessageUtil
				.postRequest(spEvent);

		JuvenileServiceProviderResponseEvent resp = (JuvenileServiceProviderResponseEvent) MessageUtil
			.filterComposite(compositeResponse,
					JuvenileServiceProviderResponseEvent.class);

        	if (resp != null) {
        		spName = resp.getServiceProviderName();
        	}
		
		Collection<ServiceProviderContactResponseEvent> contacts =UIServiceProviderHelper.getContactsFromSecurityManager(adminContactId);
		//format the phone number
		Iterator<ServiceProviderContactResponseEvent> iter = contacts.iterator();
		while(iter.hasNext())
		{
			ServiceProviderContactResponseEvent conResp = (ServiceProviderContactResponseEvent)iter.next();
			if( logonEmail.equalsIgnoreCase( conResp.getEmail() )){
			    phoneNum = conResp.getWorkPhone();
			    emailAddress = conResp.getContactEmail();
			    //orgUnit      = conResp.getProviderName();
			}
		}	
		
		//<KISHORE>JIMS200060329 : Email method from SP to CLM (UI) - KK
		String message = (String)aRequest.getParameter("message");
		String clmId = (String)aRequest.getParameter("clmId");
		String clmName = (String)aRequest.getParameter("clmName");
		String juvId = (String)aRequest.getParameter("juvId");
		if(StringUtils.isNotEmpty(clmId) && StringUtils.isNotEmpty(message)){
			//String serviceProviderName = SecurityUIHelper.getServiceProviderName();
			NotificationMessage nevt = new NotificationMessage() ;
			nevt.setSubject("Please Contact "+ providerName + "- " + spName);
			nevt.setIdentity(clmId);
			StringBuffer sb = new StringBuffer();
			sb.append("<B>");
			sb.append("TO "+clmName);
			sb.append("</B>");
			sb.append("<br/>");
			sb.append("<B>");
			sb.append("( " +juvId + " )");
			sb.append("</B>");
			sb.append("<br/>");
			sb.append(message);
			sb.append("<br/>");
			sb.append(providerName).append(" ,").append(phoneNum).append(" ,").append(emailAddress);
			nevt.setNotificationMessage(sb.toString());
			sendNotification(nevt, UIConstants.SP_CLM_NOTIFICATION);
		}

		return( aMapping.findForward(UIConstants.SUCCESS) );
	}
	
	public static void sendNotification(NotificationMessage nevt, String topic)
	{
		CreateNotificationEvent notificationEvent = (CreateNotificationEvent)
				EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
		notificationEvent.setNotificationTopic(topic);
		notificationEvent.setSubject(nevt.getSubject());
		notificationEvent.addIdentity(UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, nevt);
		notificationEvent.addContentBean(nevt);
		
		EventManager.getSharedInstance(EventManager.REQUEST).postEvent(notificationEvent);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward viewSocialHistory( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
        //<KISHORE>JIMS200060472 : Add Social Hist. link to Program Ref Detail(UI)-KK
		ProgramReferralForm form = (ProgramReferralForm) aForm;
		//Get casefileId from juvenileCasefileForm that's in session
		HttpSession session = aRequest.getSession();
		JuvenileCasefileForm casefileForm = 
			(JuvenileCasefileForm) session.getAttribute("juvenileCasefileForm");
		String casefileId = casefileForm.getSupervisionNum();
		
		GetInterviewReportsEvent reqEvent = new GetInterviewReportsEvent();
		reqEvent.setCasefileId( casefileId );
		reqEvent.setReportType(PDJuvenileCaseConstants.SOCIAL_HISTORY_REPORT_TYPE);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(reqEvent);
        CompositeResponse compResponse = (CompositeResponse) dispatch.getReply();
        
        Collection reportHistory = MessageUtil.compositeToCollection(compResponse, InterviewReportHeaderResponseEvent.class);
        Collections.sort((List)reportHistory);
        form.setReportHistory(reportHistory);
        
        return( aMapping.findForward("viewSocialHistory") );
	}
	
	public ActionForward displayReportDetails(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		String reportId = aRequest.getParameter("reportId");

		if(reportId != null)
		{
			GetInterviewReportEvent reqEvent = new GetInterviewReportEvent();
			reqEvent.setReportId(reportId);

			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(reqEvent);

			CompositeResponse compResponse = (CompositeResponse) dispatch.getReply();
			InterviewReportResponseEvent aRespEvent = (InterviewReportResponseEvent) MessageUtil.filterComposite(compResponse, InterviewReportResponseEvent.class);

			try{
				aResponse.setContentType("application/x-file-download");
				aResponse.setHeader("Content-disposition", "attachment; filename=" + aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/")+1) + ".pdf");
				aResponse.setHeader("Cache-Control", "must-revalidate");
				aResponse.setContentLength(aRespEvent.getContent().length);
				aResponse.resetBuffer();
				OutputStream os = aResponse.getOutputStream();
				os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
				os.flush();
				os.close();

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward generateReport( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
        //<KISHORE>JIMS200060472 : Add Social Hist. link to Program Ref Detail(UI)-KK
		ProgramReferralForm form = (ProgramReferralForm)aForm;
		//Get casefileId from juvenileCasefileForm that's in session
		HttpSession session = aRequest.getSession();
		JuvenileCasefileForm casefileForm = 
			(JuvenileCasefileForm) session.getAttribute("juvenileCasefileForm");
		String casefileId = casefileForm.getSupervisionNum();
		String juvenileNum = casefileForm.getJuvenileNum();

		viewSocialHistoryData(form, casefileId, juvenileNum);
		
		// Notified by is whoever the current logged on user is.
		IUserInfo user = SecurityUIHelper.getUser();
		form.getSocialHistoryData().setCurrentUser(new Name(user.getFirstName(), user.getMiddleName(), user.getLastName()).getFormattedName());

		if( form.getSocialHistoryData().getJuvenileFacilityStayRecord() != null && 
				form.getSocialHistoryData().getJuvenileFacilityStayRecord().size() > 0 )
		{
			Object obj = form.getSocialHistoryData().getJuvenileFacilityStayRecord().get(0);
			// Since we're using the same list to transfer data from PD -> UI, then UI -> UJAC, 
			// we need to know when to translate the object from ResponseEvent to UI Obj.
			if( obj.getClass().equals(JuvenileDetentionFacilitiesResponseEvent.class) )
			{
				List uiList = new ArrayList();
				int size = form.getSocialHistoryData().getJuvenileFacilityStayRecord().size();
				for( int i = 0; i < size; i++ )
				{
					JuvenileDetentionFacilitiesResponseEvent re = (JuvenileDetentionFacilitiesResponseEvent)
							form.getSocialHistoryData().getJuvenileFacilityStayRecord().get(i);
					uiList.add(new JuvenileDetentionFacility(re));
				}
				form.getSocialHistoryData().setJuvenileFacilityStayRecord(uiList);
			}
		}

		// Warrants history should not be displayed in the report
		form.getSocialHistoryData().setWarrantHistoryNeeded(false);
		CreateSocialHistoryReportEvent reqEvent = new CreateSocialHistoryReportEvent();
		reqEvent.setCasefileId(casefileId);
		reqEvent.setSocialHistoryReportDataTO(form.getSocialHistoryData());

		// Add record in activity table
		UIJuvenileHelper.createActivity(casefileId, ActivityConstants.SOCIAL_HISTORY_REPORT_GENERATED, "");

		// remove the date/time/user stamp in the various Comment fields 
		removeDateTimeUserFromComments( reqEvent ) ;
		
		CompositeResponse compositeResponse = postRequestEvent(reqEvent);
		ReportResponseEvent aRespEvent = (ReportResponseEvent)MessageUtil.filterComposite(compositeResponse, ReportResponseEvent.class);

		try
		{
			aResponse.setContentType("application/x-file-download");
			aResponse.setHeader("Content-disposition", "attachment; filename=" 
					+ aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/") + 1) + ".pdf");
			aResponse.setHeader("Cache-Control", "must-revalidate");
			aResponse.setContentLength(aRespEvent.getContent().length);
			aResponse.resetBuffer();
			OutputStream os = aResponse.getOutputStream();
			os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
			os.flush();
			os.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		// dont need to forward since response is already committed.
		return null;
	}
	
	/*
	 * 
	 */
	public void viewSocialHistoryData(ProgramReferralForm form, String casefileId, String juvenileNum)
	{
		// Get Social History Data
		GetSocialHistoryReportDataEvent event = (GetSocialHistoryReportDataEvent)
				EventFactory.getInstance(JuvenileInterviewInfoControllerServiceNames.GETSOCIALHISTORYREPORTDATA);
		event.setCasefileId(casefileId);
		event.setWarrantHistoryNeeded(false);
		
		List shDataList = MessageUtil.postRequestListFilter(event, SocialHistoryReportDataResponseEvent.class);

		for( Iterator<SocialHistoryReportDataResponseEvent> iter = shDataList.iterator();iter.hasNext();)
		{
			SocialHistoryReportDataResponseEvent data = iter.next();
			SocialHistoryReportDataTO tObj = data.getTO();

			// Sort all of the collections
			Collections.sort(tObj.getGangTraits());
			Collections.sort(tObj.getStrengthTraits());
			Collections.sort(tObj.getSubstanceAbuseTraits());
			Collections.sort(tObj.getSubstanceAbuseInformation());
			Collections.sort(tObj.getEducationalHistory());
			Collections.sort(tObj.getEmploymentHistory());
			
			List activeReferrals = UIProgramReferralHelper.getActiveCasefileProgramReferrals(casefileId);
			if( activeReferrals.size() > 1 )
			{
				Collections.sort(activeReferrals);
			}

			List closedReferrals = UIProgramReferralHelper.getClosedCasefileProgramReferrals(casefileId);
			if( closedReferrals.size() > 1 )
			{
				Collections.sort(closedReferrals);
			}
			List myReferrals = activeReferrals;
			myReferrals.addAll(closedReferrals);

			if( myReferrals.size() > 1 )
			{
				Collections.sort(myReferrals);
			}
			form.setSocialHistoryData(data.getTO());
			form.getSocialHistoryData().setProgramReferralList(myReferrals);
		}

	}

	/* 
	 * iterate through the request event, looking
	 * for all comment type fields that have an
	 * appended date/time stamp/userName, such as:
	 * "THIS IS A COMMENT. [10/01/2008 00:00 - JONES, WANDA]"
	 */
	private boolean removeDateTimeUserFromComments( CreateSocialHistoryReportEvent reqEvent )
	{
		boolean rtn = true ;
		String newComments = null ;
		SocialHistoryReportDataTO report = reqEvent.getSocialHistoryReportDataTO() ;

		// special case
		Iterator iter = report.getProgramReferralList().iterator() ;
		while( iter.hasNext() )
		{
			ProgramReferralResponseEvent act = (ProgramReferralResponseEvent)iter.next() ;
			if( act.getReferralComments() != null )
			{
				for( Iterator<ProgramReferralCommentResponseEvent> commentIter = act.getReferralComments().iterator();
						commentIter.hasNext(); /*empty*/ )
				{
					ProgramReferralCommentResponseEvent cmt = commentIter.next() ;
					if( (newComments = stripDateTimeAndUser( cmt.getCommentText()) ) != null )
					{
						cmt.setCommentText(newComments) ;
					}
				}
			}
		}
		
		// special case of [stripping] comments for SupervisionRule
		iter = report.getCompliantSupervisionRules().iterator() ;
		while( iter.hasNext() )
		{
			SupervisionRuleTO act = (SupervisionRuleTO)iter.next() ;
			if( (newComments = stripDateTimeAndUser( act.getResolvedDesc()) ) != null )
			{
				act.setResolvedDesc(newComments) ;
			}
		}

		// the next are all of trait type - abstracted to a common function
		stripTraitComments( report.getEducationalPerformanceTraits().iterator() ) ;
		stripTraitComments( report.getGangTraits().iterator() ) ;
		stripTraitComments( report.getSubstanceAbuseTraits().iterator() ) ;
		
		return( rtn ) ;
	}
	
	/*
	 * given an Iterator to Traits, visit each one, pass its
	 * comment to a function to strip the comments out. 
	 * if the String returned is not null, then assign that 
	 * String back into the comments for the report
	 */
	private boolean stripTraitComments( Iterator<TraitTO> iter )
	{
		boolean rtn = false ;
		String newComments = null ;
		
		while( iter.hasNext() )
		{
			TraitTO act = iter.next() ;
			if( (newComments = stripDateTimeAndUser( act.getTraitComments()) ) != null )
			{
				act.setTraitComments(newComments) ;
			}
		}
		
		return( rtn ) ;
	}

	/* given a String, scan it for a left brace char 
	 * and ensure there is a closing right brace char ...
	 * if so, strip everything from where the left brace 
	 * begins to its right ... sample input string:
	 * "THIS IS EVS ACTIVITY. [09/13/2007 00:00 - JONES, WANDA]"
	 * 
	 */
	private String stripDateTimeAndUser( String str )
	{
		String nstr = null ; 
		
		if( str != null  &&  (str.length() > 0) )
		{
			int leftBraceLocation = str.indexOf( '[') ;
			if( leftBraceLocation > (-1) )
			{
				int rightBraceLocation = str.indexOf( ']') ;
				if( rightBraceLocation > leftBraceLocation )
				{
					nstr = str.substring( 0, leftBraceLocation ) ;
				}
			}
		}
		
		return( nstr ) ;
	}
	
	/*
	 * given a String, return true if it's not null and not empty 
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null && (str.length() > 0) ) ;
	}
	
	/* (non-Javadoc) 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) 
	{
		keyMap.put("button.details", "showEventDetails");
		keyMap.put("prompt.other", "populateOtherReferral");
		keyMap.put("button.acceptWithChanges", "acceptWithChanges");	
		keyMap.put( "button.acceptAndClose", "acceptAndClose" ) ;
		keyMap.put("button.reject", "reject");
		keyMap.put("button.accept", "accept");
		keyMap.put("button.submit", "submitChanges");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.addComments", "addComments");
		keyMap.put("button.returnToReferralList", "returnToReferralList");
		keyMap.put("prompt.notifyClm", "notifyClm");
		keyMap.put("button.sendToCaseloadManager", "sendToClm");
		keyMap.put("button.viewSocialHistoryData", "viewSocialHistory");
		keyMap.put("button.generateReport", "generateReport");
		keyMap.put("button.link", "displayReportDetails");
		keyMap.put("button.refreshReportList", "viewSocialHistory");
	}
}
