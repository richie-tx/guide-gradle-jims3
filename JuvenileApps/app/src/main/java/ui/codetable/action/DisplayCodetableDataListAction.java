//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\system\\codetable\\action\\DisplayCodeTableDataListAction.java

package ui.codetable.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetCodetableRecordEvent;
import messaging.codetable.reply.CodetableAttributeResponseEvent;
import messaging.codetable.reply.CodetableCompoundListResponseEvent;
import messaging.codetable.reply.CodetableDataResponseEvent;
import messaging.codetable.reply.CodetableRecordResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
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

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class DisplayCodetableDataListAction extends LookupDispatchAction
{

	/**
	 * @roseuid 45B9592F0238
	 */
	public DisplayCodetableDataListAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45B94F500095
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableForm cForm = (CodetableForm) aForm;

		if (cForm.getCodetables() != null && !(cForm.getCodetables().equals("")))
		{

			for (Iterator iter = cForm.getCodetables().iterator(); iter.hasNext();)
			{
				CodetableRecordResponseEvent response = (CodetableRecordResponseEvent) iter.next();
				if (response.getCodetableRegId().equals(cForm.getCodetableRegId()))
				{
					cForm.setCodetableType(response.getType());
					cForm.setCodetableContextKey(response.getContextKey());
					cForm.setCodetableEntityName(response.getEntityName());
					cForm.setCodetableName(response.getCodetableName());
					break;
				}
			}
		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetCodetableRecordEvent request = (GetCodetableRecordEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETCODETABLERECORD);

		request.setCodetableRegId(cForm.getCodetableRegId());
		request.setType(cForm.getCodetableType());

		dispatch.postEvent(request);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse,
				ErrorResponseEvent.class);
		if (error != null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", error.getMessage()));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}

		Collection codetableAttributes = MessageUtil.compositeToCollection(compositeResponse,
				CodetableAttributeResponseEvent.class);
		if (codetableAttributes == null || codetableAttributes.isEmpty())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
					"No attributes found for the Entity Selected"));
			saveErrors(aRequest, errors);
			// cForm.setCodetables(new ArrayList());
			return aMapping.findForward(UIConstants.FAILURE);
		}
		Collections.sort((ArrayList) codetableAttributes);
		cForm.setCodetableAttributes(codetableAttributes);

		Collection codetableCompoundList = MessageUtil.compositeToCollection(compositeResponse,
				CodetableCompoundListResponseEvent.class);
		if (codetableCompoundList != null && !codetableCompoundList.isEmpty())
		{
			cForm.setCodetableCompoundList(codetableCompoundList);
		}

		Collection codetableDataList = MessageUtil.compositeToCollection(compositeResponse,
				CodetableDataResponseEvent.class);

		if (codetableDataList == null || codetableDataList.isEmpty())
		{
			// ActionErrors errors = new ActionErrors();
			// errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.codetable.emptyList"));
			// saveErrors(aRequest, errors);
			// cForm.setCodetables(new ArrayList());
			// return aMapping.findForward(UIConstants.FAILURE);
			codetableDataList = new ArrayList();
		}
		Collections.sort((ArrayList) codetableDataList);
		cForm.setCodetableDataList(codetableDataList);
		cForm.setFilteredCodetables(codetableDataList);
		cForm.resetFilterCriteria();
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableForm cForm = (CodetableForm) aForm;
		return aMapping.findForward(UIConstants.CANCEL);
	}

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.link", "link");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.refresh", "refresh");
		return buttonMap;
	}
}
