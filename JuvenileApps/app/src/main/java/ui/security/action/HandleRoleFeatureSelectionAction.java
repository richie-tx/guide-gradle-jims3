//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\HandleRoleFeatureSelectionAction.java

package ui.security.action;

import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.security.GetFeaturesEvent;
import messaging.security.reply.FeaturesResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.common.IShoppingCart;
import ui.security.FeatureShoppingCartImpl;
import ui.security.SecurityUIHelper;
import ui.security.form.RoleForm;

public class HandleRoleFeatureSelectionAction extends Action
{

	/**
	 * @roseuid 425AB74701C5
	 */
	public HandleRoleFeatureSelectionAction()
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
		GetFeaturesEvent requestEvent = new GetFeaturesEvent();

		String featureName = roleForm.getFeatureName();
		if (featureName != null && !(featureName.equals("")))
		{
			featureName = featureName.toUpperCase();
		}
		requestEvent.setFeatureName(featureName);
		String featureCategory = roleForm.getFeatureCategory();
		if (featureCategory != null && !(featureCategory.equals("")))
		{
			featureCategory = featureCategory.toUpperCase();
		}
		requestEvent.setFeatureCategory(featureCategory);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection features = MessageUtil.compositeToCollection(compositeResponse, FeaturesResponseEvent.class);
		IShoppingCart sCart = new FeatureShoppingCartImpl();
		Collection availableFeatureList;
		try
		{
			availableFeatureList =
				sCart.removeFromAvailableShoppingItemList(
					"",
					"",
					roleForm.getCurrentFeatures(),
					SecurityUIHelper.createFeatureTreeData(features));
			roleForm.setAvailableFeatures(availableFeatureList);
		}
		catch (Exception e){
		   e.printStackTrace();
		   ReturnException returnException = new ReturnException(e.getMessage());
		   throw returnException;
		}
		int size = 0;
		if (features != null)
		{
			size = features.size();
		}
		roleForm.setFeatureSearchResultSize(String.valueOf(size));
		if (size == 0)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.saroles.no.features.found"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.CREATE_FEATURE_FAILURE);
		}
		else
		{
		return aMapping.findForward(UIConstants.CREATE_FEATURE_SUCCESS);
		}
	}
}