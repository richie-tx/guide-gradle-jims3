package ui.juvenilewarrant.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilewarrant.reply.ChargeResponseEvent;
import messaging.juvenilewarrant.AcknowledgeDirectiveToApprehendEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;

import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author ryoung
 *  
 */
public class SubmitWarrantDTAAcknowledgementAction extends Action
{

    /**
     * @roseuid 416D2B0301BC
     */
    public SubmitWarrantDTAAcknowledgementAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 416D2A0C00A8
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);

        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

        AcknowledgeDirectiveToApprehendEvent requestEvent = (AcknowledgeDirectiveToApprehendEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.ACKNOWLEDGEDIRECTIVETOAPPREHEND);

        requestEvent.setWarrantNum(jwForm.getWarrantNum());

        // fetch warrants based on warrant number
        //Collection warrants = (Collection) this.fetchWarrants(requestEvent);

        CompositeResponse response = MessageUtil.postRequest(requestEvent);
        JuvenileWarrantResponseEvent warrantResponse = (JuvenileWarrantResponseEvent) MessageUtil.filterComposite(response,
                JuvenileWarrantResponseEvent.class);

        List charges = MessageUtil.compositeToList(response, ChargeResponseEvent.class);
        jwForm.setChargesSelected(charges);

        // if search response gets 0 result-go failure page
        if (warrantResponse == null)
        {
            ActionErrors errors = new ActionErrors();
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.common"));
            saveErrors(aRequest, errors);
            forward = aMapping.findForward(UIConstants.FAILURE);
        }
        else
        {
            jwForm.setWarrantAcknowledgementDate(warrantResponse.getWarrantAcknowledgementDate());
            jwForm.setWarrantAcknowledgeStatusId(warrantResponse.getWarrantAcknowledgeStatusId());
        }
        return forward;
    }
}
