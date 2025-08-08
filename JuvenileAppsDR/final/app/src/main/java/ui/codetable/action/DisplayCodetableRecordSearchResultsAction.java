//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\system\\codetable\\action\\DisplayCodetableRecordSearchResultsAction.java

package ui.codetable.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.SearchCodetableRecordsEvent;
import messaging.codetable.SearchCodetableNonMaRecordsEvent;
import messaging.codetable.reply.CodetableRecordResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.codetable.form.CodetableForm;
import ui.common.UIUtil;
import ui.security.SecurityUIHelper;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCodetableRecordSearchResultsAction extends LookupDispatchAction
{

	/**
	 * @roseuid 45B9594900F0
	 */
	public DisplayCodetableRecordSearchResultsAction()
	{

	}

	/**
	 * @return Map
	 * @roseuid 4294862303BD
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.submit", "find");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.refresh", "refresh");
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 429486240004
	 */
	public ActionForward find(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableForm cForm = (CodetableForm) aForm;
		SearchCodetableRecordsEvent request = (SearchCodetableRecordsEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.SEARCHCODETABLERECORDS);

		String promptCodetableName = cForm.getPromptCodetableName();
		if (promptCodetableName != null)
		{
			promptCodetableName = promptCodetableName.trim().toUpperCase();
		}
		request.setCodeTableName(promptCodetableName);

		String promptCodetableDescription = cForm.getPromptCodetableDescription();
		if (promptCodetableDescription != null)
		{
			promptCodetableDescription = promptCodetableDescription.trim().toUpperCase();
		}
		request.setCodeTableDescription(promptCodetableDescription);

		if (SecurityUIHelper.isUserMA())
		{
			request.setCodeTableAgencyId("");
		}
		else
		{			
		    	request.setCodeTableAgencyId(UIUtil.getCurrentUserAgencyID() != null ? UIUtil.getCurrentUserAgencyID().toUpperCase() : "");
		}
		CompositeResponse compositeResponse = MessageUtil.postRequest(request);

		ActionForward forward;

		CountInfoMessage infomsg = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,
				CountInfoMessage.class);

		if (infomsg != null)
		{
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.max.limit.exceeded");
			errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.SEARCH_FAILURE);

		}

		Collection codetables = MessageUtil
				.compositeToCollection(compositeResponse, CodetableRecordResponseEvent.class);
		if (codetables == null || codetables.isEmpty())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.search.results.found"));
			saveErrors(aRequest, errors);
			cForm.setCodetables(new ArrayList());
			return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		}

		if (codetables.size() == 1)
		{
			CodetableRecordResponseEvent result = (CodetableRecordResponseEvent) MessageUtil.filterComposite(
					compositeResponse, CodetableRecordResponseEvent.class);
			ActionForward oldForward = aMapping.findForward("success");
			forward = new ActionForward(oldForward.getPath() + "?codetableRegId=" + result.getCodetableRegId()
					+ "&submitAction=Link");
			// forward = new
			// ActionForward("displayCodetableDataList.do?submitAction=Link&codetableRegId="+
			// result.getCodetableRegId()+"&action=default");
			Collection col = new ArrayList();
			col.add(result);
			cForm.setCodetables(col);
			cForm.setSearchResultsCount("" + codetables.size());
			forward.setRedirect(true);
			return forward;
		}

		Collections.sort((List) codetables);
		cForm.setCodetables(codetables);
		cForm.setSearchResultsCount("" + codetables.size());
		return aMapping.findForward(UIConstants.FIND_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 429486240004
	 */
	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableForm cForm = (CodetableForm) aForm;
		cForm.setPromptCodetableName("");
		cForm.setPromptCodetableDescription("");
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 429486240004
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableForm cForm = (CodetableForm) aForm;
		return aMapping.findForward(UIConstants.CANCEL);
	}
}
