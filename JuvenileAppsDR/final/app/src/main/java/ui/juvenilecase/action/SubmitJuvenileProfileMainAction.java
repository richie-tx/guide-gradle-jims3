/*
 * Project: JIMS
 * Class:   ui.juvenilecase.action.SubmitJuvenileProfileMainAction
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package ui.juvenilecase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.SaveJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileMainForm;
import ui.juvenilecase.form.JuvenileProfileForm;

/**
 * Class SubmitJuvenileProfileMainAction.
 * 
 * @author Anand Thorat
 */
public class SubmitJuvenileProfileMainAction extends org.apache.struts.action.Action
{

    // ------------------------------------------------------------------------
    // --- constructor ---
    // ------------------------------------------------------------------------

    /**
     * @roseuid 42A9C0DF0196
     */
    public SubmitJuvenileProfileMainAction()
    {

    } //end of

    // ui.juvenilecase.action.SubmitJuvenileProfileMainAction.SubmitJuvenileProfileMainAction

    // ------------------------------------------------------------------------
    // --- method ---
    // ------------------------------------------------------------------------

    /**
     * 
     * @param aMapping
     *            The a mapping.
     * @param aForm
     *            The a form.
     * @param aRequest
     *            The a request.
     * @param aResponse
     *            The a response.
     * @return ActionForward
     * @roseuid 42A9B48801D6
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMainForm juvMainForm = (JuvenileMainForm) aForm;
        JuvenileProfileForm headerForm = UIJuvenileHelper.getHeaderForm(aRequest);
        String juvenileId = headerForm.getJuvenileNum();

        SaveJuvenileProfileMainEvent requestEvent = (SaveJuvenileProfileMainEvent) EventFactory
                .getInstance(JuvenileControllerServiceNames.SAVEJUVENILEPROFILEMAIN);
        UIJuvenileHelper.populateEventFromForm(requestEvent, juvMainForm);

        requestEvent.setJuvenileNum(juvenileId);     
        CompositeResponse response=MessageUtil.postRequest(requestEvent);
        
        ErrorResponseEvent errorEvt = (ErrorResponseEvent) MessageUtil.filterComposite(response,
        		ErrorResponseEvent.class);
        if(errorEvt!=null){
        	sendToErrorPage(aRequest,"error.generic","An error has occurred: " + errorEvt.getMessage());
        	errorEvt.getMessage();
        	 return aMapping.findForward(UIConstants.CONFIRM_SUCCESS);
        }
        GetJuvenileProfileMainEvent getJuvenileEvent = (GetJuvenileProfileMainEvent) EventFactory
                .getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
        getJuvenileEvent.setJuvenileNum(juvenileId);
        CompositeResponse replyEvent = MessageUtil.postRequest(getJuvenileEvent);
		
        JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent,
                JuvenileProfileDetailResponseEvent.class);

        if (detail != null)
        {
            UIJuvenileHelper.setJuvenileMainForm(juvMainForm, detail);
            UIJuvenileHelper.putHeaderForm(aRequest, detail);
        }

        headerForm.setJuvenileName(juvMainForm.getName());

        juvMainForm.setAction(UIConstants.CONFIRM_UPDATE_SUCCESS);
        return aMapping.findForward(UIConstants.CONFIRM_SUCCESS);

    } //end of ui.juvenilecase.action.SubmitJuvenileProfileMainAction.execute

    /**
	 * Method that adds a message to the request for sending back to the user takes 1 parameter, message key's message is diplayed as is 
	 * to the user after substitution of the parameter values
	 * @param aRequest -- a Request
	 * @param msgKey -- error message key to add 
	 * @param param -- a single parameter value contained in the message key
	 */
	protected void sendToErrorPage(HttpServletRequest aRequest, String msgKey, String param) {
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msgKey,param));
	    saveErrors(aRequest, errors);
	}
    
} // end SubmitJuvenileProfileMainAction
