package ui.juvenilewarrant.action;

import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.info.reply.CountInfoMessage;
import messaging.juvenilewarrant.GetJuvenileWarrantsForProcessServiceEvent;
import messaging.juvenilewarrant.reply.ProcessReturnOfServiceResponseEvent;
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

import ui.juvenilewarrant.UIJuvenileWarrantHelper;
import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.juvenilewarrant.helper.ProcessReturnOfServiceWarrantNumberComparator;

/**
 * @author ldeen
 *  
 */
public class DisplayWarrantProcessReturnOfServiceSearchResultsAction extends Action {

	private static final String PROCESSRETURN_ERROR_KEY = "error.juvenilewarrant.warrant.processreturnservice";

	/**
	 * @roseuid 4203930B00CB
	 */
	public DisplayWarrantProcessReturnOfServiceSearchResultsAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4203930500AB
	 */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		// default value to failure in case no warrantTypeUI present
		String forwardStr = UIConstants.FAILURE;

		GetJuvenileWarrantsForProcessServiceEvent requestEvent = (GetJuvenileWarrantsForProcessServiceEvent) EventFactory
				.getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEWARRANTSFORPROCESSSERVICE);

		JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

		String warrantNum = jwForm.getWarrantNumParameter(aRequest);

		// getting input fields from form and setting on requestEvent
		requestEvent.setWarrantNum(warrantNum);
		requestEvent.setJuvenileLastName(jwForm.getLastName());
		requestEvent.setJuvenileFirstName(jwForm.getFirstName());

		String action = jwForm.getAction();
		if (action.equals("cancel")) {
			jwForm.clear();
			return aMapping.findForward(UIConstants.CANCEL);
		}

		// fetch warrants based on search request
		CompositeResponse compositeResponse = MessageUtil.postRequest(requestEvent);

		String errorMsg = UIJuvenileWarrantHelper.getWarrantStageError(compositeResponse);
		if (errorMsg != null) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(PROCESSRETURN_ERROR_KEY, errorMsg));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		}

        Object obj=MessageUtil.filterComposite(compositeResponse, CountInfoMessage.class);
	    if(obj!=null)
	    {
            ActionErrors errors = new ActionErrors();
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.max.limit.exceeded"));
            saveErrors(aRequest, errors);
            return aMapping.findForward(UIConstants.SEARCH_FAILURE);
        }
		List warrants = MessageUtil.compositeToList(compositeResponse, ProcessReturnOfServiceResponseEvent.class);

		// if search response gets only 0 result-return to the search page with
		// error message
		if (warrants.size() == 0) 
		{
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
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.juvenielwarrant.noreturnofservicefound", values));
			saveErrors(aRequest, errors);
			forwardStr = UIConstants.SEARCH_FAILURE;
		}
		// if search response gets only 1 result - go to Details page
		else if (warrants.size() == 1) 
		{
			forwardStr = UIConstants.SUCCESS;
			ProcessReturnOfServiceResponseEvent response = (ProcessReturnOfServiceResponseEvent) warrants.get(0);
			jwForm.setProcessServiceProperties(response);
		}
		// if search response gets multiple results - go to searchResults page
		else 
		{
			jwForm.setSearchResultSize(String.valueOf(warrants.size()));
			Collections.sort(warrants, new ProcessReturnOfServiceWarrantNumberComparator());
			jwForm.setWarrants(warrants);
			forwardStr = UIConstants.LISTSUCCESS;
		}

		return aMapping.findForward(forwardStr);
	}

}
