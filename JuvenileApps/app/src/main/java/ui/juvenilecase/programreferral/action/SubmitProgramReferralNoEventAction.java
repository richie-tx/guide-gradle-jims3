package ui.juvenilecase.programreferral.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import messaging.programreferral.SaveProgramReferralEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.IUserInfo;
import mojo.km.security.SecurityManagerBaseResponse;
import mojo.km.security.Token;
import mojo.km.security.UserEntityBean;
import mojo.km.security.helper.SecurityManagerWebServiceHelper;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import naming.PDTaskConstants;
import naming.UIConstants;
import pd.security.PDSecurityHelper;
import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.programreferral.UIProgramReferralBean;
import ui.juvenilecase.programreferral.UIProgramReferralBean.ProgramReferralTaskInfo;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.programreferral.form.ProgramReferralForm;
import ui.security.SecurityUIHelper;

public class SubmitProgramReferralNoEventAction extends JIMSBaseAction{
	/**
	 * @roseuid 463BA574000E
	 */
	public SubmitProgramReferralNoEventAction() {

	}

	public ActionForward finish( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm ;
		UIProgramReferralBean programReferral = form.getProgramReferral() ;

		IUserInfo user = SecurityUIHelper.getUser( ) ;
		Name userName = new Name( user.getFirstName( ), "", user.getLastName( ) ) ;
		Date date = new Date();
	    SaveProgramReferralEvent saveRefEvent = new SaveProgramReferralEvent() ;
		saveRefEvent.setCasefileId( programReferral.getCasefileId() ) ;
		saveRefEvent.setProgramId( programReferral.getProviderProgramId()) ;
		saveRefEvent.setBeginDate(DateUtil.stringToDate(programReferral.getBeginDateStr(), DateUtil.DATE_FMT_1) );
		saveRefEvent.setAssignedHours(programReferral.getAssignedHours());
		saveRefEvent.setCourtOrdered(programReferral.isCourtOrdered());
		saveRefEvent.setControllingReferralNum(programReferral.getControllingReferralNum());
		saveRefEvent.setComments(programReferral.getComments());
		saveRefEvent.setCurrentUserName(userName.getFormattedName( ) );
		saveRefEvent.setSentDate(date);
		saveRefEvent.setReferralStatusCd("TN");
		saveRefEvent.setReferralSubStatusCd("REF");
		saveRefEvent.setSendEmailToContacts(form.isSendEmailToContacts());

		CompositeResponse response = MessageUtil.postRequest( saveRefEvent ) ;
		MessageUtil.processReturnException(response); 
		String programReferralId = "";
		if( response != null )
		{	
			ProgramReferralResponseEvent resp = 
				(ProgramReferralResponseEvent) MessageUtil.filterComposite( response, ProgramReferralResponseEvent.class ) ;
			programReferralId = resp.getReferralId() ;
		}
		
/** add code to create notification 
		SendProgramReferralNotificationEvent event = new SendProgramReferralNotificationEvent();
		event.setSubject("Program Referral Create");
		String msg = "A program referral " + programReferral.getProviderProgramName() +
		" has been created for " + form.getProgramReferral().getJuvenileLastName();
		event.setNotificationMessage(msg);
		GetServiceProviderEvent gspEvent =  (GetServiceProviderEvent) EventFactory
				.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDER);
		gspEvent.setServiceProviderId(programReferral.getJuvServiceProviderId());

		CompositeResponse compositeResponse = (CompositeResponse) MessageUtil.postRequest(gspEvent);

		JuvenileServiceProviderResponseEvent resp = (JuvenileServiceProviderResponseEvent)
			MessageUtil.filterComposite(compositeResponse, JuvenileServiceProviderResponseEvent.class);
		
		if (resp != null)
		{
			event.setIdentity(resp.getAdminUserProfileId()) ; //programReferral.getJuvServiceProviderId());
		}

		CreateNotificationEvent notificationEvent = 
			(CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
		notificationEvent.setNotificationTopic("MJCW.SP.PROGRAM.REFERRAL.ACTION.ALERT");
		notificationEvent.setSubject(event.getSubject());
		notificationEvent.addIdentity("respEvent", (IAddressable)event);
		notificationEvent.addContentBean(event);

		IDispatch dispatchNotification = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatchNotification.postEvent(notificationEvent);
		CompositeResponse notResponse = (CompositeResponse) dispatchNotification.getReply();
		MessageUtil.processReturnException(notResponse); 
		
/** end notification code */
/** begin task code */	
		if (programReferralId != null && !"".equals(programReferralId))
		{	
			ProgramReferralTaskInfo taskInfo = new ProgramReferralTaskInfo();
			HashMap map = new HashMap();
			map.put("submitAction", "Link");
			
			StringBuffer title = new StringBuffer();
			title.append(" New referral for  ");
			title.append(programReferral.getProviderProgramName());
			
			String adminContactId = UIProgramReferralHelper.getSPAdminContactId(programReferral.getJuvServiceProviderId());
											
			taskInfo.setParameterMap(map);
			taskInfo.setTaskUserId(adminContactId);
			taskInfo.setTaskName(PDTaskConstants.SP_REFERRAL_TASK);
			taskInfo.setTitle(title.toString());
			
			programReferral.setReferralTaskInfo(taskInfo);	
		
			if( taskInfo != null )
			{
//			Map map = taskInfo.getParameterMap() ;
			map.put( "referralId", programReferralId ) ;
			programReferral.getReferralTaskInfo().createTask() ;
			}
			
			
			if( saveRefEvent.isSendEmailToContacts() ) {
			    
			    // send email to contacts
			    this.emailServiceProviderContacts( aRequest, adminContactId, form, saveRefEvent, user);
			}
		}	
		
/** end task code */
		
		form.setAction(UIConstants.CONFIRM);
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
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return( aMapping.findForward( UIConstants.CANCEL ) ) ;

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
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return( aMapping.findForward( UIConstants.CANCEL ) ) ;

	}
	
	/**
	 * Bug Fix 14417
	 * Added Back action.
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm ;
		UIProgramReferralBean programReferral = form.getProgramReferral() ;
		
		if(programReferral.isCourtOrdered()){
			programReferral.setCourtOrderedVal("true");
		}else{
			programReferral.setCourtOrderedVal("false");
		}
	/* //added for U.S #11099
	List <ServiceProviderResponseEvent> spActives = (List<ServiceProviderResponseEvent>) form.getServiceProviderList();//new ArrayList();
		Iterator<ServiceProviderResponseEvent> serProvRespEvt = spActives.iterator();
		while (serProvRespEvt.hasNext())
		{
			ServiceProviderResponseEvent serProvResp = (ServiceProviderResponseEvent) serProvRespEvt.next();
			if(serProvResp.getJuvServProviderId().equals(form.getProgramReferral().getJuvServiceProviderId())){
				List<ServiceResponseEvent> workList = new ArrayList<ServiceResponseEvent>();
				Iterator<ProviderProgramResponseEvent> itr2 = form.getProgramNameList().iterator();
				while (itr2.hasNext())
				{
					ProviderProgramResponseEvent ppre = (ProviderProgramResponseEvent) itr2.next();
					if (ppre.getServiceProviderId().equalsIgnoreCase(form.getProgramReferral().getJuvServiceProviderId()))
					{
						if(ppre.getProviderProgramId().equals(form.getProgramReferral().getProviderProgramId())){
							ServiceResponseEvent spe = new ServiceResponseEvent();
							spe.setProgramId(ppre.getProviderProgramId());
							spe.setProgramName(ppre.getProgramName());
							spe.setServiceProviderId(ppre.getServiceProviderId());
							workList.add(spe);
							break;
						}
					}
				}
				serProvResp.setServiceResponseEvents(workList);
				break;
			}
		}
		form.setServiceProviderList(spActives);*/
		return( aMapping.findForward( UIConstants.BACK ) ) ;
	}
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put( "button.finish", "finish" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.back", "back" ) ; //Bug Fix 14417
		keyMap.put( "button.returnToReferralList", "returnToReferralList" ) ;
	}
	
	/**
	 * 
	 * @param adminUserID
	 */
	private void emailServiceProviderContacts( HttpServletRequest aRequest, String adminUserID, ProgramReferralForm theForm, SaveProgramReferralEvent saveRefEvent,IUserInfo user ) {
	    
	    
	    JuvenileCasefileForm juvenileCasefileForm = (JuvenileCasefileForm)aRequest.getSession().getAttribute( "juvenileCasefileForm" ) ;
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    
	    Token token = PDSecurityHelper.getToken(); 
	    SecurityManagerBaseResponse<List<UserEntityBean>> userInfo = SecurityManagerWebServiceHelper.getServiceProviderContacts(token, adminUserID);
	    
	    UserEntityBean userBean = PDSecurityHelper.getSecurityUserProfileByJUCode( user.getJIMSLogonId());
	    String emailAddr = "";
	    String uvCode = "";
	   if( userBean != null ) {
	       
	       uvCode = user.getJIMSLogonId();
	       emailAddr = userBean.getEmail() ;
	   }
	    
	    if ( userInfo != null ) {
		
		List<UserEntityBean> userProfiles =  userInfo.getData(); 
		int ctr =0;
		if( userProfiles.size() > 0) {
		    
		    Iterator<UserEntityBean> userProfileItr = userProfiles.iterator();
		    
		    while( userProfileItr.hasNext() ) {
			
			UserEntityBean userProfileResp = (UserEntityBean) userProfileItr.next();
			if( !userProfileResp.isDisabled() ) {
			    
			    ctr++;
			    //send email here.
			    SendEmailEvent sendEmailEvent = new SendEmailEvent();
			    StringBuffer message = new StringBuffer(100);
			    String fromEmail = "jims2notification@itc.hctx.net";
			    
			    message.append("On ").append( DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1 ) ).append(", a referral was made to your agency for youth ").append( juvenileCasefileForm.getJuvenileFullName() ).append(". Please contact ").append( uvCode)
			    .append(" ").append( saveRefEvent.getCurrentUserName() ).append(" " ).append(emailAddr).append(" for any questions regarding this referral.");
			    
			     sendEmailEvent.setFromAddress(fromEmail);
			     sendEmailEvent.setMessage( message.toString() );
			     sendEmailEvent.setSubject("Referral from Harris County JPD");
			     sendEmailEvent.addToAddress(userProfileResp.getContactemail());
			     sendEmailEvent.addBccAddress("data.corrections@hcjpd.hctx.net");
			    
			     dispatch.postEvent(sendEmailEvent);
			}
		    }
		    
		    if( ctr == 0 ) {
			
			 SendEmailEvent sendEmailEvent = new SendEmailEvent();
			    StringBuffer message = new StringBuffer(100);
			    String fromEmail = "jims2notification@itc.hctx.net";
			    
			    message.append("On ").append( DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1 ) ).append(", a referral was made to your agency for youth ")
			    .append( juvenileCasefileForm.getJuvenileFullName() ).append(" Please contact with Data.Corrections@hcjpd.hctx.net for additional information regarding this referral.");
			    
			     sendEmailEvent.setFromAddress(fromEmail);
			     sendEmailEvent.setMessage( message.toString() );
			     sendEmailEvent.setSubject("Referral from Harris County JPD");
			     sendEmailEvent.addToAddress("data.corrections@hcjpd.hctx.net");
			    
			     dispatch.postEvent(sendEmailEvent);
		    }
		    
		}
		    
	    }
		    
	}
}
