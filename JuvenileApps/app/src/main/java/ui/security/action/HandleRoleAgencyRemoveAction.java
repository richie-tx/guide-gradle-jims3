//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\HandleRoleAgencyRemoveAction.java

package ui.security.action;

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
import ui.security.form.RoleForm;

public class HandleRoleAgencyRemoveAction extends Action
{

	/**
	 * @roseuid 425AB74701C5
	 */
	public HandleRoleAgencyRemoveAction()
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
		RoleForm roleForm = (RoleForm) aForm;
		String agencyId = aRequest.getParameter("agencyId");
		Collection availableAgencyList = roleForm.getAvailableAgencies();
		Collection currentList = roleForm.getCurrentAgencies();
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

			roleForm.setAvailableAgencies(availableAgencyList);

			currentList =
				sCart.removeFromShoppingCart(
					agencyEventName,
					agencyEventIdName,
					selectedAgenciesId,
					currentList,
					availableAgencyList);

			roleForm.setCurrentAgencies(currentList);
		}
		catch (Exception e){
		   e.printStackTrace();
		   ReturnException returnException = new ReturnException(e.getMessage());
		   throw returnException;
		}
		int size = roleForm.getAvailableAgencies().size();
		roleForm.setAgencySearchResultSize(String.valueOf(size));

		if (roleForm.getAction().equals(UIConstants.UPDATE))
		{
			return aMapping.findForward(UIConstants.UPDATE_REMOVE_AGENCY_SUCCESS);
		}
		return aMapping.findForward(UIConstants.CREATE_REMOVE_AGENCY_SUCCESS);
	}
}