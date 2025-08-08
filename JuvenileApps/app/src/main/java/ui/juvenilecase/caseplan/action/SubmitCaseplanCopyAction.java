package ui.juvenilecase.caseplan.action;

import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.identityaddress.domintf.IAddressable;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.administerlocation.reply.LocationNotificationResponseEvent;
import messaging.caseplan.GetCaseplanDetailsEvent;
import messaging.caseplan.reply.CaseplanDetailsResponseEvent;
import messaging.caseplan.GenerateCopyCasePlanEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasePlanControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage ;
import org.apache.struts.action.ActionErrors ;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.caseplan.form.CaseplanForm;
import ui.juvenilecase.form.JuvenileCasefileForm;

public class SubmitCaseplanCopyAction extends JIMSBaseAction{
	/**
	 * @roseuid 463BA574000E
	 */
	public SubmitCaseplanCopyAction() {

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
		CaseplanForm cpForm = (CaseplanForm)aForm;
		cpForm.setAction("submit");
		ActionForward forward = aMapping.findForward( UIConstants.SUBMIT ) ;
		//check if the selected casefile already has a caseplan associated
		GetCaseplanDetailsEvent evt = (GetCaseplanDetailsEvent)EventFactory.getInstance(JuvenileCasePlanControllerServiceNames.GETCASEPLANDETAILS);
		evt.setSupervisionNumber(cpForm.getSelectedValue());
		CompositeResponse resp = MessageUtil.postRequest(evt);
		CaseplanDetailsResponseEvent cpEvt = (CaseplanDetailsResponseEvent)MessageUtil.filterComposite(resp, CaseplanDetailsResponseEvent.class);
		if(cpEvt.getCaseplanID()!=null)
		{
			cpForm.setAction("copy");
			cpForm.setSecondaryAction("submit");
			ActionErrors errors = new ActionErrors();
			String msg = "Caseplan previously created for selected Casefile. Please select a different casefile";
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",msg));
			saveErrors( aRequest, errors ) ;
			forward = aMapping.findForward(UIConstants.FAILURE);
			return forward;			
		}		
		Iterator iter = cpForm.getCasefiles().iterator();
		while(iter.hasNext())
		{
			JuvenileProfileCasefileListResponseEvent casefile = (JuvenileProfileCasefileListResponseEvent)iter.next();
			if(casefile.getSupervisionNum().equals(cpForm.getSelectedValue()))
			{
				cpForm.setSelectedCasefile(casefile);
				break;
			}
		}
	 	return forward;
    }

	public ActionForward finish( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm cpForm = (CaseplanForm)aForm;
		GenerateCopyCasePlanEvent evt = new GenerateCopyCasePlanEvent();
		evt.setCasefileId(cpForm.getSelectedValue());
		evt.setCopyCaseplanId(cpForm.getCurrentCaseplan().getCaseplanId());
		evt.setCopyFromCasefileId(cpForm.getCasefileId());
		IDispatch disp = EventManager.getSharedInstance(EventManager.REQUEST);
		disp.postEvent(evt);
		CompositeResponse resp = (CompositeResponse)disp.getReply();
		MessageUtil.processReturnException(resp);
		
		//create notification
		JuvenileCasefileForm caseForm = (JuvenileCasefileForm)aRequest.getSession().getAttribute("juvenileCasefileForm");
		String juvenileName="";
		String juvNum="";
		if(caseForm!=null)
		{
			juvenileName = caseForm.getJuvenileFullName();
			juvNum = caseForm.getJuvenileNum();
		}
		GetJuvenileCasefileEvent getCasefileEvent = (GetJuvenileCasefileEvent) EventFactory
		        .getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);
		getCasefileEvent.setSupervisionNumber(cpForm.getSelectedValue());
		
		JuvenileCasefileResponseEvent casefileEvent = (JuvenileCasefileResponseEvent) MessageUtil.postRequest(
		        getCasefileEvent, JuvenileCasefileResponseEvent.class);
		 CaseplanDetailsResponseEvent nevt = new CaseplanDetailsResponseEvent();
         nevt.setSubject("Copy Caseplan casefile " + cpForm.getCasefileId());
         nevt.setNotificationMessage("A new Caseplan has been generated for " + cpForm.getSelectedValue()+
        		 " on " + DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1)+" . Generated Caseplan was created by copying caseplan from "+ cpForm.getCasefileId() + " to "
        		 + cpForm.getSelectedValue() + " for " + juvenileName+ ","+ juvNum
        		 +". Copied caseplan needs JPO review.");
         nevt.setIdentity(casefileEvent.getProbationOfficerLogonId());
		
		
		
		
         CreateNotificationEvent notification = (CreateNotificationEvent)EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
         notification.setNotificationTopic("JC.COPY.CASEPLAN.REVIEW.NEEDED");
         notification.setSubject(nevt.getSubject());
         notification.addIdentity("respEvent", (IAddressable)nevt);
         notification.addContentBean(nevt);
         CompositeResponse response = MessageUtil.postRequest(notification);
         MessageUtil.processReturnException( response ) ;
		cpForm.setAction(UIConstants.FINISH );
		cpForm.setSecondaryAction("");
		return( aMapping.findForward( UIConstants.FINISH ) ) ;
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
	
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		CaseplanForm cpForm = (CaseplanForm)aForm;
		cpForm.setAction( UIConstants.VIEW_DETAIL );
		return aMapping.findForward(UIConstants.SUCCESS);		
	}
	
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put( "button.finish", "finish" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.submit", "submitChanges" ) ;
		keyMap.put("button.backToCaseplanDetails", "back");
	}
}
