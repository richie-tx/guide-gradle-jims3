package ui.juvenilewarrant.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.info.reply.CountInfoMessage;
import messaging.juvenilewarrant.GetJuvenileWarrantServiceInfoEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantsForReleaseEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantsForManageReleaseDecisionEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantServiceResponseEvent;
import ui.juvenilewarrant.helper.JuvenileWarrantNumberComparator;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDJuvenileWarrantConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.UIUtil;
import ui.juvenilewarrant.form.JuvenileAssociateBean;
import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;

/**
 * @author ldeen
 *  
 */
public class DisplayReleaseSearchResultsAction extends Action
{
    // TODO Needs to be refactored to be less procedural. Execute method is too
    // long.

    public static final String RELEASE_DECISION = "releaseDecision";

    public static final String MANAGE_RELEASE_TO_JP = "releaseToJP";

    public static final String MANAGE_RELEASE_TO_PERSON = "releaseToPerson";

    /**
     * @roseuid 4203940C007D
     */
    public DisplayReleaseSearchResultsAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42038C000011
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        //default value to failure in case no warrantTypeUI present
        String forwardStr = "";

        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
        String warrantTypeUI = jwForm.getWarrantTypeUI();

        // fetch loggedin user's name
        jwForm.setReleaseDecisionUserName(UIUtil.getCurrentUserName());
        jwForm.setReleaseDecisionUserId(UIUtil.getCurrentUserID());

        CompositeResponse response = null;
        
        String warrantNum = jwForm.getWarrantNumParameter(aRequest);
        
        if (RELEASE_DECISION.equals(warrantTypeUI))
        {
            GetJuvenileWarrantsForReleaseEvent releaseEvent = (GetJuvenileWarrantsForReleaseEvent) EventFactory
                    .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEWARRANTSFORRELEASE);
            // getting input fields from form and setting on requestEvent
            releaseEvent.setWarrantNum(warrantNum);
            releaseEvent.setJuvenileLastName(jwForm.getLastName());
            releaseEvent.setJuvenileFirstName(jwForm.getFirstName());

            response = MessageUtil.postRequest(releaseEvent);

            Object obj=MessageUtil.filterComposite(response, CountInfoMessage.class);
    	    if(obj!= null)
    	    {
                ActionErrors errors = new ActionErrors();
                errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.max.limit.exceeded"));
                saveErrors(aRequest, errors);
                jwForm.clearSearchCriteria();
                forwardStr = UIConstants.SEARCH_FAILURE;
            }
            
            String errorMsg = UIJuvenileWarrantHelper.getWarrantStageError(response);
            if (errorMsg != null)
            {
                ActionErrors errors = new ActionErrors();
                errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.juvenilewarrant.warrant.releasedecision", ""));
                saveErrors(aRequest, errors);
                jwForm.clearSearchCriteria();
                forwardStr = UIConstants.SEARCH_FAILURE;
            }
        }
        else
        {
            GetJuvenileWarrantsForManageReleaseDecisionEvent releaseDecisionEvent = (GetJuvenileWarrantsForManageReleaseDecisionEvent) EventFactory
                    .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEWARRANTSFORMANAGERELEASEDECISION);
            // getting input fields from form and setting on requestEvent
            releaseDecisionEvent.setWarrantNum(warrantNum);
            releaseDecisionEvent.setJuvenileLastName(jwForm.getLastName());
            releaseDecisionEvent.setJuvenileFirstName(jwForm.getFirstName());

            // set release decision
            if (MANAGE_RELEASE_TO_PERSON.equals(warrantTypeUI))
            {
                releaseDecisionEvent.setReleaseDecision(PDJuvenileWarrantConstants.RELEASE_DECISION_TO_PERSON);
                jwForm.setBackToWarrantUrl("/jsp/warrantReleaseToPerson.jsp");
            }
            else if (MANAGE_RELEASE_TO_JP.equals(warrantTypeUI))
            {
                releaseDecisionEvent.setReleaseDecision(PDJuvenileWarrantConstants.RELEASE_DECISION_TO_JUVENILE_PROBATION);
            }

            response = MessageUtil.postRequest(releaseDecisionEvent);            

            String errorMsg = UIJuvenileWarrantHelper.getWarrantStageError(response);
            if (errorMsg != null)
            {
                ActionErrors errors = new ActionErrors();
                if (MANAGE_RELEASE_TO_PERSON.equals(warrantTypeUI))
                {
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.juvenilewarrant.warrant.managereleaseto.person",
                            errorMsg));
                }
                else if (MANAGE_RELEASE_TO_JP.equals(warrantTypeUI))
                {
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.juvenilewarrant.warrant.managereleasetojp",
                            errorMsg));
                }
                saveErrors(aRequest, errors);
                forwardStr = UIConstants.SEARCH_FAILURE;
            } 
        }

        List warrants = MessageUtil.compositeToList(response, JuvenileWarrantResponseEvent.class);
        int size = warrants.size();

        Object obj=MessageUtil.filterComposite(response, CountInfoMessage.class);
	    if(obj!= null)
	    {
            ActionErrors errors = new ActionErrors();
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.max.limit.exceeded"));
            saveErrors(aRequest, errors);
            forwardStr = UIConstants.SEARCH_FAILURE;
        }
        if (forwardStr.equals(""))
        {
	        if (size == 0)
	        {
	            // no result found error
	        	StringBuffer buffer = new StringBuffer(30);
    		    String lastName = jwForm.getLastName();
    		    String firstName = jwForm.getFirstName();
    		           		            
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
    		    ActionErrors errors = new ActionErrors();
	            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecordsFound", values));
	            saveErrors(aRequest, errors);
	            jwForm.clearSearchCriteria();
	            forwardStr = UIConstants.SEARCH_FAILURE;
	        }
	        else if (size == 1)
	        {
	            // if there is only one record, get the WarrantResponseEvent from
	            // the collection
	
	            // Done in the reset... so we don't need to do this... right?
	            // jwForm.clearWarrants();
	
	            // forward to the action designated for showing details
	            jwForm.setProperties(response);
	            
	//          Get Juvenile Warrant Service (must be a successful Juvenile Warrant Service)
	            GetJuvenileWarrantServiceInfoEvent serviceRequestEvent = (GetJuvenileWarrantServiceInfoEvent) EventFactory
	                    .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEWARRANTSERVICEINFO);
	
	            serviceRequestEvent.setWarrantNum(jwForm.getWarrantNum());
	
	            CompositeResponse replyEvent = MessageUtil.postRequest(serviceRequestEvent);
	
	            JuvenileWarrantServiceResponseEvent serviceResponse = (JuvenileWarrantServiceResponseEvent) MessageUtil.filterComposite(
	                    replyEvent, JuvenileWarrantServiceResponseEvent.class);
	
	            jwForm.setWarrantServiceProperties(serviceResponse);
	            
	            if (RELEASE_DECISION.equals(warrantTypeUI))
	            {
	                forwardStr = UIConstants.RELEASE_DECISION_SUCCESS;
	            }
	            if (MANAGE_RELEASE_TO_JP.equals(warrantTypeUI))
	            {
	                forwardStr = UIConstants.RELEASE_TOJP_SUCCESS;
	            }
	            if (MANAGE_RELEASE_TO_PERSON.equals(warrantTypeUI))
	            {
	                // create a blank place holder for the new associate
	                JuvenileAssociateBean newBean = new JuvenileAssociateBean();
	                newBean.setWarrantNum(jwForm.getWarrantNum());
	                // create place holders for two blank addresses
	                List twoBlankAddresses = new ArrayList();
	                
	                JuvenileAssociateAddressResponseEvent evt = new JuvenileAssociateAddressResponseEvent();
	                twoBlankAddresses.add(evt);
	                
	                evt = new JuvenileAssociateAddressResponseEvent();
	                twoBlankAddresses.add(evt);
	                
	                newBean.setAssociateAddresses(twoBlankAddresses);
	                jwForm.setNewReleaseToAssociate(newBean);
	                
	                forwardStr = UIConstants.RELEASE_TOPERSON_SUCCESS;
	            }
	        }
	        else if (size > 1)
	        {           
	            jwForm.setSearchResultSize(String.valueOf(size));
	            Collections.sort(warrants, new JuvenileWarrantNumberComparator());
	            jwForm.setWarrants(warrants);
	            forwardStr = UIConstants.LISTSUCCESS;
	        }
        }
        return aMapping.findForward(forwardStr);
    }

}
