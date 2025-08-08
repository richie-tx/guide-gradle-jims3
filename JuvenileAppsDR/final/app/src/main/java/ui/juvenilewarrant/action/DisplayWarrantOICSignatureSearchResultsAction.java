package ui.juvenilewarrant.action;

import java.util.ArrayList;
//import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import messaging.codetable.reply.CodeResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDJuvenileWarrantConstants;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.juvenilewarrant.helper.JuvenileWarrantNumberComparator;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;

import messaging.juvenilewarrant.GetJuvenileWarrantsListEvent;

/**
 * @author ldeen
 */
public class DisplayWarrantOICSignatureSearchResultsAction extends Action
{
    /**
     * @roseuid 420CCB8B03A4
     */
    public DisplayWarrantOICSignatureSearchResultsAction()
    {
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 420CCB020164
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        // default value to failure in case no warrantType present
        String forwardStr = UIConstants.FAILURE;

        JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;

        GetJuvenileWarrantsListEvent requestEvent = (GetJuvenileWarrantsListEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEWARRANTSLIST);

        CompositeResponse response = null;

        List warrants = new ArrayList();

        if (form.getWarrantNum() != null && form.getWarrantNum().length() > 0)
        {
            requestEvent.setWarrantNum(form.getWarrantNum());
            response = MessageUtil.postRequest(requestEvent);
            warrants = MessageUtil.compositeToList(response, JuvenileWarrantResponseEvent.class);

            if (warrants.size() == 1)
            {
                JuvenileWarrantResponseEvent warrant = (JuvenileWarrantResponseEvent) warrants.get(0);
                if (this.checkPreconditions(warrant) == false)
                {
                    // handle no results
                    ActionErrors errors = new ActionErrors();
                    // TODO Move error.noRecords into a constant
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
                            "error.juvenilewarrant.warrant.requestsignature"));
                    saveErrors(aRequest, errors);
                    return aMapping.findForward(UIConstants.SEARCH_FAILURE);
                }
            }
        }
        else
        {
            requestEvent.setLastName(form.getLastName());
            requestEvent.setFirstName(form.getFirstName());
            requestEvent.setWarrantTypeId(PDJuvenileWarrantConstants.WARRANT_TYPE_OIC);
            requestEvent.setWarrantStatus(PDJuvenileWarrantConstants.WARRANT_STATUS_PENDING);
            requestEvent.setWarrantActivationStatusId(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE);
            requestEvent.setWarrantAcknowledgeStatusId(PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_NOT_PRINTED);
            requestEvent.setWarrantSignedStatusId(PDJuvenileWarrantConstants.WARRANT_NOT_SIGNED);

            response = MessageUtil.postRequest(requestEvent);

            Object obj=MessageUtil.filterComposite(response, CountInfoMessage.class);
    	    if(obj!= null)
    	    {
                ActionErrors errors = new ActionErrors();
                errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.max.limit.exceeded"));
                saveErrors(aRequest, errors);
                return aMapping.findForward(UIConstants.SEARCH_FAILURE);
            }
            else
            {

                warrants = MessageUtil.compositeToList(response, JuvenileWarrantResponseEvent.class);
            }
        }
        if (warrants.size() == 0)
        {
            // handle no results
            // TODO Move error.noRecords into a constant
            ActionErrors errors = new ActionErrors();
		    StringBuffer buffer = new StringBuffer(30);
		    String lastName = form.getLastName();
		    String firstName = form.getFirstName();
		    String warrantNum = form.getWarrantNum();
		           		            
		    if(warrantNum != null && !warrantNum.equals(""))
		    {
		    	buffer.append(" Warrant # ");
		        buffer.append(warrantNum);
		    }
		    if(lastName != null && !lastName.equals(""))
		    {
		    	buffer.append(" Juvenile ");
		    	if(firstName != null && !firstName.equals(""))
    		    {
    		        buffer.append(firstName);
    		        buffer.append(" ");
    		    }
		        buffer.append(lastName);
		    }
		    String errorString = buffer.toString();
		    Object[] values = new Object[1];
		    values[0] = errorString;
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecordsFound", values));
            saveErrors(aRequest, errors);

            return aMapping.findForward(UIConstants.SEARCH_FAILURE);
        }
        else if (warrants.size() == 1)
        {
            // handle single result
            forwardStr = UIConstants.SUCCESS;
            form.setWarrantTypeUI(UIConstants.REQSIGOIC_SUCCESS);
            form.setProperties(response);
        }
        else
        {
            // handle multiple results
            form.setSearchResultSize(String.valueOf(warrants.size()));
            Collections.sort(warrants, new JuvenileWarrantNumberComparator());
            form.setWarrants(warrants);
            forwardStr = UIConstants.LISTSUCCESS;
        }

        return aMapping.findForward(forwardStr);
    }

    /**
     * @param warrant
     * @return
     */
    private boolean checkPreconditions(JuvenileWarrantResponseEvent warrant)
    {
        String warrantType = warrant.getWarrantTypeId();
        String warrantStatus = warrant.getWarrantStatusId();
        String warrantActivationStatus = warrant.getWarrantActivationStatusId();
        String warrantAcknowledgeStatus = warrant.getWarrantAcknowledgeStatusId();
        String warrantSignedStatus = warrant.getWarrantSignedStatusId();

        if (PDJuvenileWarrantConstants.WARRANT_TYPE_OIC.equals(warrantType)
                && PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE.equals(warrantActivationStatus)
                && PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_NOT_PRINTED.equals(warrantAcknowledgeStatus)
                && PDJuvenileWarrantConstants.WARRANT_STATUS_PENDING.equals(warrantStatus)
                && PDJuvenileWarrantConstants.WARRANT_NOT_SIGNED.equals(warrantSignedStatus))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}