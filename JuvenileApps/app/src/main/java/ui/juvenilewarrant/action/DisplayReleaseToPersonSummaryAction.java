package ui.juvenilewarrant.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilewarrant.GetJuvenileAssociateDataEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author ldeen
 *  
 */
public class DisplayReleaseToPersonSummaryAction extends Action
{

    /**
     * @roseuid 41FFE16D03C8
     */
    public DisplayReleaseToPersonSummaryAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 41FFC64D014A
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
        jwForm.setAction("summary");
        String success = UIConstants.SUCCESS;

        if (jwForm.getReleaseAssociateNum() != null && !jwForm.getReleaseAssociateNum().equals(""))
        {
            jwForm.clearNewReleaseAssociate();

            // get the associate and fill the form
            GetJuvenileAssociateDataEvent assoRequestEvent = (GetJuvenileAssociateDataEvent) EventFactory
                    .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEASSOCIATEDATA);

            assoRequestEvent.setAssociateNumber(jwForm.getReleaseAssociateNum());
            CompositeResponse response = MessageUtil.postRequest(assoRequestEvent);

            JuvenileAssociateResponseEvent associateResponse = (JuvenileAssociateResponseEvent) MessageUtil.filterComposite(
                    response, JuvenileAssociateResponseEvent.class);
            List assocAddresses = MessageUtil.compositeToList(response, JuvenileAssociateAddressResponseEvent.class);

            jwForm.populateReleaseAssociateValues(associateResponse);

            jwForm.populateRealeaseAssociateAddressValues(assocAddresses);
        }
        else
        {
            if (jwForm.getNewReleaseToAssociate() != null)
            {
                // Parse date and put in dataOfBirth property in the bean
                jwForm.getNewReleaseToAssociate().initDateOfBirth();
            }

            jwForm.populateRealeaseAssociateAddressValuesFromRequest(aRequest);

            // to populate the race, sex and relationship desc.
            jwForm.populateRealeaseAssociateCodeDescriptionsFromIds();
        }

        return aMapping.findForward(success);
    }
}
