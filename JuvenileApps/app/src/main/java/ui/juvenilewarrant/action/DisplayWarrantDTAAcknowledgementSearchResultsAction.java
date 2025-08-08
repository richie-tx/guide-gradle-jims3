package ui.juvenilewarrant.action;

import java.util.List;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.GetOfficerProfileEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.juvenilewarrant.GetJuvenileWarrantsForAcknowledgeEvent;
import messaging.juvenilewarrant.reply.AcknowledgedWarrantErrorEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.juvenilewarrant.helper.JuvenileWarrantNumberComparator;

/**
 * @author ldeen
 *  
 */
public class DisplayWarrantDTAAcknowledgementSearchResultsAction extends Action
{

    /**
     * @roseuid 420278CE00D8
     */
    public DisplayWarrantDTAAcknowledgementSearchResultsAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 420274E801E2
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

        /* default value to failure in case no warrantType present */
        String success = UIConstants.FAILURE;

        GetJuvenileWarrantsForAcknowledgeEvent requestEvent = (GetJuvenileWarrantsForAcknowledgeEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEWARRANTSFORACKNOWLEDGE);

        /* getting input fields from form and setting on requestEvent */
        requestEvent.setWarrantTypeId(jwForm.getWarrantTypeId());
        requestEvent.setWarrantNum(jwForm.getWarrantNum());
        requestEvent.setLastName(jwForm.getLastName());
        requestEvent.setFirstName(jwForm.getFirstName());

        String action = jwForm.getAction();
        if (action.equals("cancel"))
        {
            jwForm.clear();
            return aMapping.findForward(UIConstants.CANCEL);
        }

        // fetch warrants based on search request
        CompositeResponse response = MessageUtil.postRequest(requestEvent);

        String error = this.getWarrantError(response);
        if (error != null)
        {
            ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.juvenilewarrant.warrant.acknowledged", error));
            saveErrors(aRequest, errors);
            return aMapping.findForward(UIConstants.SEARCH_FAILURE);
        }
        else
        {
            Object obj=MessageUtil.filterComposite(response, CountInfoMessage.class);
    	    if(obj!=null)
    	    {
                ActionErrors errors = new ActionErrors();
                ActionMessage wildCardError = new ActionMessage("error.max.limit.exceeded");
                errors.add(ActionErrors.GLOBAL_MESSAGE, wildCardError);
                saveErrors(aRequest, errors);
                return aMapping.findForward(UIConstants.SEARCH_FAILURE);
              }

            List warrants = MessageUtil.compositeToList(response, JuvenileWarrantResponseEvent.class);

            ActionErrors errors;

            /*
             * if search response gets only 0 result-return to the search page
             * with error message
             */
            switch (warrants.size())
            {
            case 0:
            	errors = new ActionErrors();
    		    StringBuffer buffer = new StringBuffer(30);
    		    String warrantNum = jwForm.getWarrantNum();
    		           		            
    		    if(warrantNum != null && !warrantNum.equals(""))
    		    {
    		    	buffer.append(" Warrant # ");
    		        buffer.append(warrantNum);
    		    }
    		    String errorString = buffer.toString();
    		    Object[] values = new Object[1];
    		    values[0] = errorString;
    		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecordsFound", values));
                saveErrors(aRequest, errors);
                return aMapping.findForward(UIConstants.SEARCH_FAILURE);

            // if search response gets only 1 result-go to Details page
            case 1:
                jwForm.setProperties(response);

                GetOfficerProfileEvent officerRequestEvent = (GetOfficerProfileEvent) EventFactory
                        .getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILE);

                officerRequestEvent.setOfficerProfileId(jwForm.getOfficerId());

                CompositeResponse officerComposite = MessageUtil.postRequest(officerRequestEvent);

                OfficerProfileResponseEvent officerEvt = (OfficerProfileResponseEvent) MessageUtil.filterComposite(
                        officerComposite, OfficerProfileResponseEvent.class);

                if (officerEvt == null)
                {
                    errors = new ActionErrors();
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.juvenilewarrant.missingofficer", error));
                    saveErrors(aRequest, errors);
                    return aMapping.findForward(UIConstants.SEARCH_FAILURE);
                }

                jwForm.setOfficerProperties(officerEvt);
                if (jwForm.getWarrantTypeUI().equals(UIConstants.REQACKDTA))
                {
                    success = UIConstants.REQACKDTA_SUCCESS;
                }
                forward = aMapping.findForward(success);
                break;

            default:
                // Multiple results goes to searchResults page
                jwForm.setSearchResultSize(String.valueOf(warrants.size()));
                Collections.sort(warrants, new JuvenileWarrantNumberComparator());
                jwForm.setWarrants(warrants);

                if (jwForm.getWarrantTypeUI().equals(UIConstants.REQACKDTA))
                {
                    success = UIConstants.REQACKDTA_LISTSUCCESS;
                }
                forward = aMapping.findForward(success);
                break;
            }
        }
        return forward;
    }

    /**
     * Checks to see if a ReturnException or AcknowledgedWarrantErrorEvent was
     * thrown. If so, returns the message.
     * 
     * @param response
     * @return message of error
     */
    private String getWarrantError(CompositeResponse response)
    {
        AcknowledgedWarrantErrorEvent ackError = (AcknowledgedWarrantErrorEvent) MessageUtil.filterComposite(response,
                AcknowledgedWarrantErrorEvent.class);
        String message = null;
        if (ackError != null)
        {
             message = ackError.getMessage();
        }
        return message;
    }
}