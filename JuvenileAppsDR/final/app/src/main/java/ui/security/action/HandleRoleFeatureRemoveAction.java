//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\HandleRoleFeatureRemoveAction.java

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
import ui.security.FeatureShoppingCartImpl;
import ui.security.form.RoleForm;

public class HandleRoleFeatureRemoveAction extends Action
{

	/**
	* @roseuid 425AB74701C5
	*/
	public HandleRoleFeatureRemoveAction()
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
		String featureId = aRequest.getParameter("featureId");
		Collection availableFeatureList = roleForm.getAvailableFeatures();
		Collection currentList = roleForm.getCurrentFeatures();
		String[] selectedFeaturesId = { featureId };

		IShoppingCart sCart = new FeatureShoppingCartImpl();
		try
		{
			availableFeatureList =
				sCart.addToAvailableShoppingItemList(
					"",
					"",
					selectedFeaturesId,
					roleForm.getCurrentFeatures(),
					availableFeatureList);

			roleForm.setAvailableFeatures(availableFeatureList);

			currentList = sCart.removeFromShoppingCart("", "", selectedFeaturesId, currentList, availableFeatureList);
			roleForm.setCurrentFeatures(currentList);
		}
		catch (Exception e){
		   e.printStackTrace();
		   ReturnException returnException = new ReturnException(e.getMessage());
		   throw returnException;
		}

		if (roleForm.getAction().equals(UIConstants.UPDATE))
		{
			return aMapping.findForward(UIConstants.UPDATE_REMOVE_FEATURE_SUCCESS);
		}
		return aMapping.findForward(UIConstants.CREATE_REMOVE_FEATURE_SUCCESS);
	}
}