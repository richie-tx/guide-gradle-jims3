package ui.juvenilecase.referral.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.SaveJuvenileProfileMainEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.referral.form.JuvenileReferralForm;

public class SubmitUpdateJuvenileSsnAction extends LookupDispatchAction
{
    protected Map getKeyMethodMap()
    {
	Map<String, String> keyMap = new HashMap<String, String>();
	keyMap.put("button.back", "back");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.next", "next");
	keyMap.put("button.finish", "finish");
	return keyMap;
    }
    
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setAction("updateJuvenileSsnNext");
	return aMapping.findForward(UIConstants.NEXT);
    }
    
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	
	if (form.getJuvenileNum() != null && !form.getJuvenileNum().isEmpty())
	{
	    SaveJuvenileProfileMainEvent requestEvent = (SaveJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEPROFILEMAIN);
	    requestEvent.setJuvenileNum(form.getJuvenileNum());
	    requestEvent.setAction("updateJuvenileSsnFinish"); 
	    requestEvent.setSSN(form.getSSN().getFormattedSSN());
	    form.setAction("updateJuvenileSsnFinish");
	    CompositeResponse response = MessageUtil.postRequest(requestEvent);
	    
	    ActionMessages messageHolder = new ActionMessages();
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.updateJuvSsnSuccess"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	    
	    ErrorResponseEvent errorEvt = (ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);
	    if (errorEvt != null)
	    {
		sendToErrorPage(aRequest, "error.generic", "An error has occurred: " + errorEvt.getMessage());
		errorEvt.getMessage();
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    
	}
	JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("REFERRAL_BRIEFINGDETAILS_FORM");
	briefingDetailsForm.getProfileDetail().setCompleteSSN(form.getSSN().getFormattedSSN());
	return aMapping.findForward(UIConstants.SUCCESS);
    }
    
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward(UIConstants.BACK);
    }
    
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward(UIConstants.CANCEL);
    }
    
    protected void sendToErrorPage(HttpServletRequest aRequest, String msgKey, String param)
    {
	ActionErrors errors = new ActionErrors();
	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msgKey, param));
	saveErrors(aRequest, errors);
    }
}
