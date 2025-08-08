//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\DisplayFeaturesAction.java

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
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.security.form.RoleSAForm;

public class DisplayFeaturesAction extends Action
{

	/**
	 * @roseuid 425AB68D0261
	 */
	public DisplayFeaturesAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 425551F8035F
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		GetFeaturesEvent requestEvent = new GetFeaturesEvent();
		requestEvent.setFeatureName(saRoleForm.getFeatureName());
		requestEvent.setFeatureCategory(saRoleForm.getFeatureCategory());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection features = MessageUtil.compositeToCollection(compositeResponse, FeaturesResponseEvent.class);
		saRoleForm.setFeatures(features);
		return aMapping.findForward(UIConstants.FEATURE_SUCCESS);
	}
}
