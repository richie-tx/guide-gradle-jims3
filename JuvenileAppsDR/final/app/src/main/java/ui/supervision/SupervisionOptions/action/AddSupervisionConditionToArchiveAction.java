//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\SubmitSupervisionConditionDeleteAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.ArchiveSupervisionConditionEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;
import ui.supervision.supervisionorder.UISupervisionOrderHelper;

public class AddSupervisionConditionToArchiveAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42F7C4A203A9
	 */
	public AddSupervisionConditionToArchiveAction()
	{

	}
	
	public Map getKeyMethodMap()
		{
			Map buttonMap = new HashMap();			
			buttonMap.put("prompt.showArchived", "showHide");
			buttonMap.put("prompt.hideArchived", "showHide");
			buttonMap.put("prompt.archive", "archive");
			return buttonMap;
		}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A3B0218
	 */
	public ActionForward showHide(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionConditionForm form = (SupervisionConditionForm) aForm;

		String showArchived = aRequest.getParameter("showArchived");
		if("true".equals(showArchived)){
			form.setShowArchived(false);
		}else{
			form.setShowArchived(true);
		}
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.ARCHIVE_SUCCESS,form.getAgencyId()));
	
	}
	
	public ActionForward archive(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			SupervisionConditionForm form = (SupervisionConditionForm) aForm;
	
			String conditionId = aRequest.getParameter("conditionId");
			ArchiveSupervisionConditionEvent archiveConditionEvent = new ArchiveSupervisionConditionEvent();
			archiveConditionEvent.setConditionId(conditionId);
			// post event to pd
			IDispatch requestDispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			requestDispatch.postEvent(archiveConditionEvent);

			CompositeResponse compositeResponse = UISupervisionOrderHelper.getCompositeResponse(requestDispatch);
			Collection searchResults = MessageUtil.compositeToCollection(compositeResponse, ConditionResponseEvent.class);
			MessageUtil.processEmptyCollection(searchResults);

			ConditionResponseEvent condRespEvent = (ConditionResponseEvent)searchResults.iterator().next();
			// search through the iterator to set the archived flag
			Iterator it = form.getConditionSearchResults().iterator();
			while (it.hasNext())
			{
				ConditionResponseEvent resultBean = (ConditionResponseEvent) it.next();
				if(resultBean.getConditionId().equals(condRespEvent.getConditionId())){
					resultBean.setArchived(condRespEvent.isArchived());
				}
			}
			 
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.ARCHIVE_SUCCESS,form.getAgencyId()));
		}
}
