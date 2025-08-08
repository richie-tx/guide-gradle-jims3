package ui.juvenilecase.programreferral.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.GetProgramReferralWithEventsEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.notification.SendProgramReferralNotificationEvent;
import messaging.programreferral.GetJuvenileProgramReferralAssignmentHistoryEvent;
import messaging.programreferral.reply.ProgramReferralAssignmentHistoryResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasefileNotificationControllerServiceNames;
import naming.JuvenileProgramReferralControllerServiceNames;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.programreferral.UIProgramReferralBean;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.programreferral.form.ProgramReferralForm;

public class SubmitProgramReferralTransferAction extends JIMSBaseAction{
	/**
	 * @roseuid 463BA574000E
	 */
	public SubmitProgramReferralTransferAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 463A437A03DB
	 */
	public ActionForward submitChanges(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
		ProgramReferralForm form = (ProgramReferralForm)aForm ;		
		String supervisionNum = form.getSelectedValue();

		List casefiles = form.getProgramReferral().getCasefiles();
		Iterator cases = casefiles.iterator();
		while (cases.hasNext())
		{
			JuvenileProfileCasefileListResponseEvent juvenileCase = (JuvenileProfileCasefileListResponseEvent)cases.next();
			if (juvenileCase.getSupervisionNum().equals(supervisionNum)) {
				form.setSelectedCasefile(juvenileCase);
				break;
			}
		}
		form.setAction(UIConstants.SUMMARY);

	 	return aMapping.findForward(UIConstants.SUCCESS);
    }

	public ActionForward finish( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm ;
		UIProgramReferralBean programReferral = form.getProgramReferral() ;
/*
		TransferProgramReferralEvent gprs = (TransferProgramReferralEvent)
			EventFactory.getInstance(JuvenileProgramReferralControllerServiceNames.TRANSFERPROGRAMREFERRAL);
		String oldCasefileId = programReferral.getCasefileId();
		gprs.setCasefileId(form.getSelectedValue());
		gprs.setProgramReferralId(programReferral.getReferralId());*/
		/*MessageUtil.postRequest( gprs ) ;
		form.setAction( UIConstants.CONFIRM ) ;*/
		/*MessageUtil.postRequest( gprs ) ;
		form.setAction( UIConstants.CONFIRM ) ; // commented for ER JIMS200075798*/
		String oldCasefileId = programReferral.getCasefileId();
		 //added for ER JIMS200075798 added new command to transfer future events  
		if( programReferral != null )
		{
			GetProgramReferralWithEventsEvent gprs1 = (GetProgramReferralWithEventsEvent) 
			EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMREFERRALWITHEVENTS);

			gprs1.setCasefileId(form.getSelectedValue());
			gprs1.setProgramReferralId(programReferral.getReferralId());
			gprs1.setAssignmentDate(DateUtil.getCurrentDate()); // bug fix #37849 transfer create date.
			// Update rest of the stuff
			gprs1.setProgramOutomeCd(programReferral.getOutComeCd());
			gprs1.setProgramOutcomeCdDesc(programReferral.getOutComeDescription());
			gprs1.setProgramOutcomeSubcategoryCd(programReferral.getOutComeSubcategoryCd());
			gprs1.setReferralStatusCd(programReferral.getReferralStatus());
			gprs1.setSubstatusCd(programReferral.getReferralSubstatusCd());
			gprs1.setEndDate(DateUtil.stringToDate(programReferral.getEndDateStr(),DateUtil.DATE_FMT_1));
			if(programReferral.getControllingReferralNum()!=null && !programReferral.getControllingReferralNum().isEmpty())
				gprs1.setControllingReferralNum(form.getSelectedCasefile().getControllingReferral());
			else{
				gprs1.setControllingReferralNum(UIProgramReferralHelper.getLowestCtrlngRefNbr(form.getSelectedValue()));
			}
			gprs1.setAcknowlegmentDate(programReferral.getAcknowledgementDate());
			
			
			CompositeResponse compositeResponse = (CompositeResponse)MessageUtil.postRequest(gprs1);			
		
		
		form.setAction( UIConstants.CONFIRM ) ;
		
		//End of ER JIMS200075798
		
		SendProgramReferralNotificationEvent event = (SendProgramReferralNotificationEvent) EventFactory
		.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDPROGRAMREFERRALNOTIFICATION);
		event.setSubject("Program Referral Transfer");
		
		GetJuvenileCasefileEvent gjce = (GetJuvenileCasefileEvent)
		EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);
		gjce.setSupervisionNumber(form.getSelectedValue());
		CompositeResponse response = MessageUtil.postRequest( gjce ) ;
		JuvenileCasefileResponseEvent detail = (JuvenileCasefileResponseEvent) MessageUtil.filterComposite(response,JuvenileCasefileResponseEvent.class);
		event.setIdentity(detail.getProbationOfficerLogonId());

		String s = "A program referral  " + programReferral.getProviderProgramName() +
			" has been transferred from "  + oldCasefileId + " for " + programReferral.getJuvenileName() + 
			" to " + form.getSelectedValue() + ".";

		event.setNotificationMessage(s);
		CreateNotificationEvent notificationEvent = (CreateNotificationEvent)
			EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
		notificationEvent.setNotificationTopic("MJCW.PROGRAM.REFERRAL.TRANSFER");
		notificationEvent.setSubject(event.getSubject());
		notificationEvent.addIdentity("respEvent", (IAddressable)event);
		notificationEvent.addContentBean(event);

		IDispatch dispatchNotification = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatchNotification.postEvent(notificationEvent);
		//Get the latest history // bug fix #37849
		GetJuvenileProgramReferralAssignmentHistoryEvent gjprahe = (GetJuvenileProgramReferralAssignmentHistoryEvent) 
				EventFactory.getInstance(JuvenileProgramReferralControllerServiceNames.GETJUVENILEPROGRAMREFERRALASSIGNMENTHISTORY);
				
				gjprahe.setProgramReferralId(programReferral.getReferralId());
							
				compositeResponse = (CompositeResponse) MessageUtil.postRequest(gjprahe);		
				List prAssignmentHistory = (List)
						MessageUtil.compositeToCollection(compositeResponse,ProgramReferralAssignmentHistoryResponseEvent.class);
										
				if( prAssignmentHistory != null )
				{
					int size = prAssignmentHistory.size();
					Iterator<ProgramReferralAssignmentHistoryResponseEvent> prAssignHistoryItr = prAssignmentHistory.iterator();
					List<ProgramReferralAssignmentHistoryResponseEvent>prAssignmentHistoryNew = new ArrayList<ProgramReferralAssignmentHistoryResponseEvent>();
					while(prAssignHistoryItr.hasNext()){	
						ProgramReferralAssignmentHistoryResponseEvent prAssgnHistory = prAssignHistoryItr.next();
						String superVisionID = prAssgnHistory.getCasefileId();
						GetJuvenileCasefileEvent getCasefileEvent = (GetJuvenileCasefileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);
						getCasefileEvent.setSupervisionNumber(superVisionID);
						CompositeResponse response1 = MessageUtil.postRequest(getCasefileEvent);
						JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent) MessageUtil.filterComposite(response1, JuvenileCasefileResponseEvent.class);
						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
						prAssgnHistory.setCasefileAssignDate(sdf.format(casefile.getAssignmentDate()));
						if(casefile.getClosingDate()!=null){
							prAssgnHistory.setCasefileEndDate(sdf.format(casefile.getClosingDate()));
						}
						prAssgnHistory.setCntrlRefNum(casefile.getControllingReferralId()); //Bug #40734
						prAssignmentHistoryNew.add(prAssgnHistory);
					}
					Collections.sort( (List)prAssignmentHistoryNew );
					programReferral.setPrAssignmentHistoryList(prAssignmentHistoryNew);	
				}
		}
		
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
	
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put( "button.finish", "finish" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.submit", "submitChanges" ) ;
		keyMap.put( "button.referralList", "returnToReferralList" ) ;
	}
}
