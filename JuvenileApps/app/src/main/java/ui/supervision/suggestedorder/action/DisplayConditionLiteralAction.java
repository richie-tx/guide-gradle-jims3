//Source file: C:\\views\\CommonSupervision\\app\\src\\action\\DisplayConditionLiteralAction.java

package ui.supervision.suggestedorder.action;

import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.GetSupervisionConditionDetailsEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.SupervisionOptionsControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.suggestedorder.form.SuggestedOrderForm;

/**
 * @author dgibler
 *
 */
public class DisplayConditionLiteralAction extends Action
{

	/**
	 * @roseuid 433AF45802F6
	 */
	public DisplayConditionLiteralAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 433AF05100A6
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SuggestedOrderForm form = (SuggestedOrderForm) aForm;

		//Get ConditionLiteral
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetSupervisionConditionDetailsEvent getDetailsEvent =
			(GetSupervisionConditionDetailsEvent) EventFactory.getInstance(
				SupervisionOptionsControllerServiceNames.GETSUPERVISIONCONDITIONDETAILS);

		getDetailsEvent.setConditionId(form.getConditionId());
		dispatch.postEvent(getDetailsEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);

		ConditionDetailResponseEvent cdre =
			(ConditionDetailResponseEvent) MessageUtil.filterComposite(
				compositeResponse,
				ConditionDetailResponseEvent.class);

		if (cdre != null)
		{
			//Get ConditionLiteralSample

			form.setConditionName(cdre.getName());
			String conditionLiteral = cdre.getDescription();
			String conditionLiteralSample = conditionLiteral;

			Collection tokens = UISupervisionOptionHelper.tokenizeVariables(conditionLiteral, "{{", "}}");
			Collection referenceTokens = UISupervisionOptionHelper.tokenizeVariables(conditionLiteral, "\\[", "\\]");
			tokens.addAll(referenceTokens);

			HashMap detailDictionaryNameIdMapping =
				UISupervisionOptionHelper.createDetailDictionaryNameIdMapping(form.getDetailDictionary());

			form.setDetailDictionaryNameIdHashMap(detailDictionaryNameIdMapping);
			conditionLiteralSample =
				UISupervisionOptionHelper.createLiteralSample(conditionLiteral, tokens, detailDictionaryNameIdMapping);
			form.setConditionLiteralPreview(conditionLiteralSample);

			return aMapping.findForward(UIConstants.SUCCESS);
		}
		else
		{
			forward = aMapping.findForward(UIConstants.FAILURE);
		}

		return forward;
	}
}
