package ui.juvenilewarrant.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilewarrant.GetJuvenileWarrantsForActivateEvent;

import messaging.juvenilewarrant.reply.ActiveWarrantErrorEvent;
import messaging.juvenilewarrant.reply.InvalidWarrantStageErrorEvent;
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
 * @author ldeen
 *
 */
public class DisplayWarrantActivateDetailsAction extends Action
{

	/**
	 * @roseuid 420278DE031A
	 */
	public DisplayWarrantActivateDetailsAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4202751A00CD
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

		/** default value to failure in case no warrantType present */
		String success = UIConstants.FAILURE;

		GetJuvenileWarrantsForActivateEvent requestEvent =
			(GetJuvenileWarrantsForActivateEvent) EventFactory.getInstance(
				JuvenileWarrantControllerServiceNames.GETJUVENILEWARRANTSFORACTIVATE);
			
		requestEvent.setWarrantNum(jwForm.getWarrantNum());

		String action = jwForm.getAction();
		if (action.equals("cancel"))
		{
			jwForm.clear();
			success = UIConstants.CANCEL;
		}
		else
		{
			// fetch warrants based on search request		
			CompositeResponse response = MessageUtil.postRequest(requestEvent);	
			
			Collection warrants = MessageUtil.compositeToList(response, JuvenileWarrantResponseEvent.class);		
			
			ActionErrors errors = new ActionErrors();	
			this.checkBusinessErrors(aRequest, response, errors);
			
			if (errors.isEmpty() == false)
			{
			    success = UIConstants.SEARCH_FAILURE;
			}
			else
			{
				int size = warrants.size();
				
				// if search response gets only 0 result-return to the search page with error message
				if (size == 0)
				{			
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
					jwForm.clearSearchCriteria();
					success = UIConstants.SEARCH_FAILURE;
				}
				// if search response gets only 1 result-go to Details page
				else if (size == 1)
				{			
					jwForm.setProperties(response);				
		
					if (jwForm.getWarrantTypeUI().equals(UIConstants.ACTDTA))
					{
						success = UIConstants.ACTDTA_SUCCESS;
					}
					else if (jwForm.getWarrantTypeUI().equals(UIConstants.ACTARR))
					{
						success = UIConstants.ACTARR_SUCCESS;
					}
				}
				
				jwForm.setBackToWarrantUrl(aMapping.findForward(success).getPath());
			}
		}
		return aMapping.findForward(success);
	}

	/**
	 * @param response
	 */
	private void checkBusinessErrors(HttpServletRequest aRequest, CompositeResponse response, ActionErrors errors)
	{
		Object activeError = MessageUtil.filterComposite(response, ActiveWarrantErrorEvent.class);		
		Object invalidStageError = MessageUtil.filterComposite(response, InvalidWarrantStageErrorEvent.class);

		if (activeError != null)
		{
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.juvenilewarrant.active.warrant.found"));
		}
		else if(invalidStageError != null)
		{
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.juvenilewarrant.warrant.requestactivation"));			
		}
		saveErrors(aRequest, errors);
	}
}
