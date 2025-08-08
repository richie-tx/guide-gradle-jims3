/*
 * Created on Sep 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.codetable.action;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetCodetableRegistrationAttributesEvent;
import messaging.codetable.reply.CodetableAttributeResponseEvent;
import messaging.codetable.reply.CodetableRecordResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.codetable.form.CodetableRegistrationForm;

/**
 * @author Nagalla
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class HandleCodetableRegistrationAction extends JIMSBaseAction
{

	/**
	 * @roseuid
	 */
	public HandleCodetableRegistrationAction()
	{

	}

	/**
	 * @return Map
	 * @roseuid
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "link");
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
				cForm.setCodetableDescription(response.getCodetableDescription());
				cForm.setCurrentAgencies(response.getAgencies());
				break;
			}
		}
	 }
		GetCodetableRegistrationAttributesEvent request = (GetCodetableRegistrationAttributesEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETCODETABLEREGISTRATIONATTRIBUTES);

		request.setCodetableRegId(cForm.getCodetableRegId());
		request.setType(cForm.getCodetableType());

		List codetableAttributes = MessageUtil.postRequestListFilter(request, CodetableAttributeResponseEvent.class);

		if (codetableAttributes == null || codetableAttributes.isEmpty())
		{
			cForm.setShowAttributes(false);
			cForm.setOpType("view");
			cForm.setOpStatus("details");
			return aMapping.findForward(UIConstants.SUCCESS);
		}
		Collections.sort(codetableAttributes);
		cForm.setCodetableAttributes(codetableAttributes);
		cForm.setSearchResultsCount("" + cForm.getCodetableAttributes().size());

		cForm.setShowAttributes(true);
		cForm.setOpType("view");
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
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
		if (cForm.getCodetableType().equalsIgnoreCase("SL"))
		{
			cForm.setCodetableContextKey(cForm.getSelectedContextOrEntity());
			cForm.setCodetableEntityName("pd.codetable.Code");
		}
		else
		{
			cForm.setCodetableEntityName(cForm.getSelectedContextOrEntity());
		}
		cForm.setOpStatus("summary");
		cForm.setShowAttributes(false);
		return aMapping.findForward(UIConstants.NEXT);
	}

}
