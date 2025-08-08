package ui.juvenilewarrant.action;

import java.util.List;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import messaging.info.reply.CountInfoMessage;
import messaging.juvenilewarrant.GetJuvenileWarrantsForInactivateEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;
import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.juvenilewarrant.helper.JuvenileWarrantNumberComparator;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.UIConstants;

/**
 * @author hrodriguez
 *  
 */
public class DisplayWarrantInactivateSearchResultsAction extends Action
{
    /**
     * @roseuid 4200EAE00092
     */
    public DisplayWarrantInactivateSearchResultsAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4200E9F400A6
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = null;

        JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;

        this.clearForm(form);

        GetJuvenileWarrantsForInactivateEvent requestEvent = (GetJuvenileWarrantsForInactivateEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEWARRANTSFORINACTIVATE);

        String warrantNum = form.getWarrantNumParameter(aRequest);
        if (warrantNum == null || warrantNum.equals(""))
        {
            requestEvent.setLastName(form.getLastName());
            requestEvent.setFirstName(form.getFirstName());
        }
        else
        {
            requestEvent.setWarrantNum(warrantNum);
        }

        CompositeResponse response = MessageUtil.postRequest(requestEvent);

        String errorMsg = UIJuvenileWarrantHelper.getWarrantStageError(response);

        ActionErrors errors = new ActionErrors();

        Object obj = MessageUtil.filterComposite(response, CountInfoMessage.class);
        if (obj != null)
        // Result count exceeded maximum limit
        {
            ActionMessage error = new ActionMessage("error.max.limit.exceeded");
            errors.add(ActionErrors.GLOBAL_MESSAGE, error);
            saveErrors(aRequest, errors);
            forward = aMapping.findForward(UIConstants.SEARCH_FAILURE);
        }
        else if (errorMsg != null)
        // Warrant already active
        {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.juvenilewarrant.warrant.inactive", errorMsg));
            saveErrors(aRequest, errors);
            forward = aMapping.findForward(UIConstants.SEARCH_FAILURE);
        }
        else
        {
            List warrants = MessageUtil.compositeToList(response, JuvenileWarrantResponseEvent.class);

            int size = warrants.size();

            switch (size)
            {
            case 0:
    		    StringBuffer buffer = new StringBuffer(30);
    		    String lastName = form.getLastName();
    		    String firstName = form.getFirstName();
    		           		            
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
                forward = aMapping.findForward(UIConstants.SEARCH_FAILURE);
                form.clearSearchCriteria();
                break;

            case 1:
                form.setProperties(response);
                forward = aMapping.findForward(UIConstants.SUCCESS);
                break;

            default:
                // if search response gets multiple results - go to
                // searchResults page
                form.setSearchResultSize(String.valueOf(size));
                Collections.sort(warrants, new JuvenileWarrantNumberComparator());
                form.setWarrants(warrants);
                forward = aMapping.findForward(UIConstants.LISTSUCCESS);
                break;
            }
            
            form.setBackToWarrantUrl(forward.getPath());
        }        

        return forward;
    }

    /**
     *  
     */
    private void clearForm(JuvenileWarrantForm form)
    {
        form.clearCharges();

    }

}
