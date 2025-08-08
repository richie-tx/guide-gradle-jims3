package ui.juvenilewarrant.action;

import java.util.List;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.juvenilewarrant.GetJuvenileWarrantsForManageServiceEvent;
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

import ui.contact.user.helper.UIUserFormHelper;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;
import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.juvenilewarrant.helper.JuvenileWarrantNumberComparator;
import ui.security.SecurityUIHelper;

/**
 * @author ldeen
 * 
 */
public class DisplayWarrantServiceStatusSearchResultsAction extends Action
{

    /**
     * @roseuid 4203941601C5
     */
    public DisplayWarrantServiceStatusSearchResultsAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42038AB60244
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        String forward = UIConstants.FAILURE;

        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;        
        
        String warrantNum = jwForm.getWarrantNumParameter(aRequest);
        
        GetJuvenileWarrantsForManageServiceEvent requestEvent = (GetJuvenileWarrantsForManageServiceEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEWARRANTSFORMANAGESERVICE);
        if(warrantNum == null || warrantNum.equals(""))
        {
            requestEvent.setJuvenileLastName(jwForm.getLastName());
            requestEvent.setJuvenileFirstName(jwForm.getFirstName());
        }
        else
        {
            requestEvent.setWarrantNum(warrantNum);
        }

        CompositeResponse response = MessageUtil.postRequest(requestEvent);
        ActionErrors errors = new ActionErrors();

        String errorMsg = UIJuvenileWarrantHelper.getWarrantStageError(response);
        if (errorMsg != null)
        {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.juvenilewarrant.warrant.servicedecision", errorMsg));
            saveErrors(aRequest, errors);
            forward = UIConstants.SEARCH_FAILURE;
        }
        
        Object obj=MessageUtil.filterComposite(response, CountInfoMessage.class);
	    if(obj!= null)
	    {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.max.limit.exceeded"));
            saveErrors(aRequest, errors);
            forward = UIConstants.SEARCH_FAILURE;
        }
        else
        {
            List warrants = MessageUtil.compositeToList(response, JuvenileWarrantResponseEvent.class);

            switch (warrants.size())
            {
            case 0:
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
                errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecordsFound", values));
                saveErrors(aRequest, errors);
                forward = UIConstants.SEARCH_FAILURE;
                jwForm.clearSearchCriteria();
                break;

            case 1:
                forward = UIConstants.SUCCESS;
                jwForm.setProperties(response);
				
				OfficerProfileResponseEvent officerProfile = UIUserFormHelper.getUserOfficerProfile( SecurityUIHelper.getLogonId() );
				if ( officerProfile != null ){
					
	               	// Preload the executor information		
					jwForm.setSearch("officerSearch");
					jwForm.setOfficerIdTypeId("B");
					jwForm.setOfficerId( officerProfile.getBadgeNum() );
					jwForm.setOfficerAgencyId( officerProfile.getDepartmentId() );
					
				}
                break;

            default:
                jwForm.setSearchResultSize(String.valueOf(warrants.size()));
                Collections.sort(warrants, new JuvenileWarrantNumberComparator());
                jwForm.setWarrants(warrants);
                forward = UIConstants.LISTSUCCESS;
                break;
            }
        }

        return aMapping.findForward(forward);
    }
}
