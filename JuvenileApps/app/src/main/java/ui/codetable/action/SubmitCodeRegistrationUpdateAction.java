/*
 * Created on Nov 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.codetable.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetCodetableRegistrationAttributesEvent;
import messaging.codetable.UpdateCodetableRegistrationEvent;
import messaging.codetable.reply.CodetableAttributeResponseEvent;
import messaging.codetable.reply.CodetableRecordResponseEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
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

import ui.action.JIMSBaseAction;
import ui.codetable.form.CodetableRegistrationForm;
import ui.common.CodeHelper;
import ui.security.SecurityUIHelper;

/**
 * @author Nagalla
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class SubmitCodeRegistrationUpdateAction extends JIMSBaseAction
{

	/**
	 * @roseuid
	 */
	public SubmitCodeRegistrationUpdateAction()
	{

	}

	/**
	 * @return Map
	 * @roseuid
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "link");
		keyMap.put("button.back", "back");
		keyMap.put("button.finish", "finish");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;

		for (Iterator iter = cForm.getCodetables().iterator(); iter.hasNext();)
		{
			CodetableRecordResponseEvent response = (CodetableRecordResponseEvent) iter.next();
			if (response.getCodetableRegId().equals(cForm.getCodetableRegId()))
			{
				cForm.setCodetableType(response.getType());
				cForm.setCodetableContextKey(response.getContextKey());
				cForm.setCodetableEntityName(response.getEntityName());
				cForm.setCodetableName(response.getCodetableName());
				cForm.setCodetableDescription(response.getCodetableDescription());
				break;
			}
		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetCodetableRegistrationAttributesEvent request = (GetCodetableRegistrationAttributesEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETCODETABLEREGISTRATIONATTRIBUTES);

		request.setCodetableRegId(cForm.getCodetableRegId());
		request.setType(cForm.getCodetableType());

		dispatch.postEvent(request);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection codetableAttributes = MessageUtil.compositeToCollection(compositeResponse,
				CodetableAttributeResponseEvent.class);
		if (codetableAttributes == null || codetableAttributes.isEmpty())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.genericaccount.found"));
			saveErrors(aRequest, errors);
			cForm.setCodetables(new ArrayList());
			return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		}
		Collections.sort((ArrayList) codetableAttributes);
		cForm.setCodetableAttributes(codetableAttributes);

		cForm.setOpStatus("details");
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
		List codetableTypes = CodeHelper.getCodetableTypeCodes();
		cForm.setCodetableTypeList(codetableTypes);
		cForm.resetForCreate();
		return aMapping.findForward(UIConstants.BACK);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		UpdateCodetableRegistrationEvent request = (UpdateCodetableRegistrationEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.UPDATECODETABLEREGISTRATION);

		String codetableName = cForm.getCodetableName();
		if (codetableName != null)
		{
			codetableName = codetableName.trim().toUpperCase();
		}
		request.setName(codetableName);
		request.setDescription(cForm.getCodetableDescription());
		request.setType(cForm.getCodetableType());
		request.setContextKey(cForm.getCodetableContextKey());
		request.setEntityName(cForm.getCodetableEntityName());

		Collection currentAgencies = cForm.getCurrentAgencies();

		Collection agencyIds = new ArrayList();
		Iterator iter = currentAgencies.iterator();
		while (iter.hasNext())
		{
			AgencyResponseEvent event = (AgencyResponseEvent) iter.next();
			agencyIds.add(event.getAgencyId());
		}
		request.setAgenciesList(agencyIds);

		if (cForm.getOpType() != null && cForm.getOpType().equalsIgnoreCase("update"))
			request.setCodeRegId(cForm.getCodetableRegId());

		List codetables = MessageUtil.postRequestListFilter(request, CodetableRecordResponseEvent.class);
		if (codetables == null && codetables.isEmpty())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.codetable.update"));
			saveErrors(aRequest, errors);
			cForm.setCodetables(new ArrayList());
			return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		}

		CodetableRecordResponseEvent regRecord = (CodetableRecordResponseEvent) codetables.iterator().next();
		cForm.setCodetableName(regRecord.getCodetableName());
		cForm.setCodetableDescription(regRecord.getCodetableDescription());
		cForm.setCodetableRegId(regRecord.getCodetableRegId());
		cForm.setCodetableContextKey(regRecord.getContextKey());
		cForm.setCodetableEntityName(regRecord.getEntityName());
		cForm.setOpStatus("confirm");
		cForm.setShowAttributes(false);
		return aMapping.findForward(UIConstants.FINISH);
	}

}
