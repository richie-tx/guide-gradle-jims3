package ui.juvenilewarrant.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import messaging.info.reply.CountInfoMessage;
import messaging.juvenilewarrant.GetJuvenileWarrantsForRecallEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantServiceResponseEvent;

import ui.common.PhoneNumber;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;
import ui.juvenilewarrant.form.JuvenileAssociateBean;
import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.juvenilewarrant.helper.JuvenileWarrantNumberComparator;

import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;

import naming.JuvenileWarrantControllerServiceNames;

import naming.PDJuvenileWarrantConstants;

import naming.UIConstants;

/**
 * @author HRodriguez - 02/04/2005 - Create action
 *  
 */
public class DisplayWarrantRecallSearchResultsAction extends Action
{

    /**
     * @roseuid 4200EAED0371
     */
    public DisplayWarrantRecallSearchResultsAction()
    {
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4200E9F901A1
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {

        ActionForward forward = new ActionForward();
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

        GetJuvenileWarrantsForRecallEvent jwRequestEvent = (GetJuvenileWarrantsForRecallEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEWARRANTSFORRECALL);

        jwRequestEvent.setWarrantNum(jwForm.getWarrantNum());
        jwRequestEvent.setLastName(jwForm.getLastName());
        jwRequestEvent.setFirstName(jwForm.getFirstName());

        // FETCH WARRANTS BASED ON SEARCH REQUEST OR HYPERLINK
        CompositeResponse response = MessageUtil.postRequest(jwRequestEvent);
        
        String errorMsg = UIJuvenileWarrantHelper.getWarrantStageError(response);
        
        if (errorMsg != null)
        {
            ActionErrors errors = new ActionErrors();
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.juvenilewarrant.warrant.recall", errorMsg));
            saveErrors(aRequest, errors);
            return aMapping.findForward(UIConstants.SEARCH_FAILURE);
        }
        
        Object obj=MessageUtil.filterComposite(response, CountInfoMessage.class);
	    if(obj!= null)
	    {
            ActionErrors errors = new ActionErrors();
            ActionMessage error = new ActionMessage("error.max.limit.exceeded");
            errors.add(ActionErrors.GLOBAL_MESSAGE, error);
            saveErrors(aRequest, errors);
            return aMapping.findForward(UIConstants.SEARCH_FAILURE);
        }
 
        List warrants = MessageUtil.compositeToList(response, JuvenileWarrantResponseEvent.class);
        int size = 0;         
        if (warrants != null)
        { 
           size = warrants.size();
        }   
         
        
        
         /*
         * if search response gets 0 result - return to the Search page with
         * error message
         */
        if (size == 0)
        {
            ActionErrors errors = new ActionErrors();
		    StringBuffer buffer = new StringBuffer(30);
		    String lastName = jwForm.getLastName();
		    String firstName = jwForm.getFirstName();
		    String warrantNum = jwForm.getWarrantNum();
		           		            
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
            jwForm.clearSearchCriteria();
            return aMapping.findForward(UIConstants.SEARCH_FAILURE);
        }

        // if search response gets only 1 result - go to Details page
        else if (size == 1)
        {
            // process juvenile warrant data
            JuvenileWarrantResponseEvent jwResponseEvent = (JuvenileWarrantResponseEvent) warrants.get(0);
            jwForm.clearWarrants();
            processWarrantDetails(jwForm, response, jwResponseEvent);
            forward = aMapping.findForward(UIConstants.SUCCESS);
        }
        // if search response gets multiple results - go to searchResults page
        else
        {
            jwForm.setSearchResultSize(String.valueOf(size));
            Collections.sort(warrants, new JuvenileWarrantNumberComparator());
            jwForm.setWarrants(warrants);
            forward = aMapping.findForward(UIConstants.LISTSUCCESS);
        }

        jwForm.setBackToWarrantUrl(forward.getPath());

        return forward;
    }

    /**
     * Retrieves supplemental data from a given warrant
     * 
     * @param jwForm
     * @param compositeResponse
     * @param jwResponseEvent
     */
    private void processWarrantDetails(JuvenileWarrantForm jwForm, CompositeResponse compositeResponse,
            JuvenileWarrantResponseEvent jwResponseEvent)
    {
        jwForm.setProperties(compositeResponse);

        List associates = jwForm.getAssociates();

        // FETCH RELEASE ASSOCIATE DATA
        if ((jwForm.getReleaseAssociateNum() != null) && (!jwForm.getReleaseAssociateNum().equals("")))
        {
            for (Iterator iter = associates.iterator(); iter.hasNext();)
            {
                JuvenileAssociateBean associate = (JuvenileAssociateBean) iter.next();
                if (associate.getAssociateNum().equals(jwForm.getReleaseAssociateNum()))
                {
                    if (associate.getAssociateName() != null)
                        jwForm.setReleaseDecisionName(associate.getAssociateName().getFormattedName());
                    jwForm.setReleaseRelationshipToJuvenile(associate.getRelationshipToJuvenile());
                }
            }
        }

        // FETCH SERVICES
        List servicesSelected = MessageUtil.compositeToList(compositeResponse, JuvenileWarrantServiceResponseEvent.class);
        List services = new ArrayList();

        Iterator servicesIterator = servicesSelected.iterator();
        while (servicesIterator.hasNext())
        {
            JuvenileWarrantServiceResponseEvent serviceResponseEvent = (JuvenileWarrantServiceResponseEvent) servicesIterator
                    .next();
            if (serviceResponseEvent.getServiceStatusId().equals(PDJuvenileWarrantConstants.WARRANT_SERVICE_SUCCESSFUL))
            {
                // Set arrest info
                jwForm.setWarrantServiceProperties(serviceResponseEvent);
            }
            else
            {
                // Convert Phone Numbers
                serviceResponseEvent.setExecutorPhoneNum(formatPhone(serviceResponseEvent.getExecutorPhoneNum()));
                serviceResponseEvent.setExecutorCellNum(formatPhone(serviceResponseEvent.getExecutorCellNum()));
                serviceResponseEvent.setExecutorPager(formatPhone(serviceResponseEvent.getExecutorPager()));
                services.add(serviceResponseEvent);
            }

        }
        jwForm.setServices(services);

    }

    /**
     * Formats a given string into phone number format
     * 
     * @param number
     * @return
     */
    private String formatPhone(String number)
    {
        PhoneNumber phone = new PhoneNumber(number);
        return phone.getFormattedPhoneNumber();
    }
}
