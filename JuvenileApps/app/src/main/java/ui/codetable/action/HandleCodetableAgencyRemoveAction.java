package ui.codetable.action;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mojo.km.messaging.exception.ReturnException;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.common.IShoppingCart;
import ui.common.ShoppingCartImpl;
import ui.codetable.form.CodetableRegistrationForm;;

public class HandleCodetableAgencyRemoveAction extends Action
{

	/**
	 * @roseuid 425AB74701C5
	 */
	public HandleCodetableAgencyRemoveAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4256D6310196
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
		String agencyId = aRequest.getParameter("agencyId");
		Collection availableAgencyList = cForm.getAvailableAgencies();
		Collection currentList = cForm.getCurrentAgencies();
		String[] selectedAgenciesId = { agencyId };

		IShoppingCart sCart = new ShoppingCartImpl();
		String agencyEventName = UIConstants.AGENCY_RESPONSE_EVENT;
		String agencyEventIdName = UIConstants.AGENCY_RESPONSE_EVENT_ID;
		try
		{
			availableAgencyList =
				sCart.addToAvailableShoppingItemList(
					agencyEventName,
					agencyEventIdName,
					selectedAgenciesId,
					currentList,
					availableAgencyList);

			cForm.setAvailableAgencies(availableAgencyList);

			currentList =
				sCart.removeFromShoppingCart(
					agencyEventName,
					agencyEventIdName,
					selectedAgenciesId,
					currentList,
					availableAgencyList);

			cForm.setCurrentAgencies(currentList);
		}
		catch (Exception e){
		   e.printStackTrace();
		   ReturnException returnException = new ReturnException(e.getMessage());
		   throw returnException;
		}
		int size = cForm.getAvailableAgencies().size();
		cForm.setAgencySearchResultSize(String.valueOf(size));

		/*if(cForm.getAction().equals(UIConstants.UPDATE))
		{
			return aMapping.findForward(UIConstants.CREATE_REMOVE_AGENCY_SUCCESS);
		}*/
		return aMapping.findForward(UIConstants.CREATE_REMOVE_AGENCY_SUCCESS);
	}
}