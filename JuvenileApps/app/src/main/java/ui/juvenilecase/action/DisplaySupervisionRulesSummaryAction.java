/*
 * Created on Nov 30, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.Name;
import ui.juvenilecase.Rule;
import ui.juvenilecase.form.SupervisionConditionForm;
import ui.security.SecurityUIHelper;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplaySupervisionRulesSummaryAction extends LookupDispatchAction
{

	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}


	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionConditionForm conditionForm = (SupervisionConditionForm)aForm;
		ActionForward forward = null;
		if(conditionForm.isStandard())
			forward = aMapping.findForward("standardBack");
		else
			forward = aMapping.findForward("customBack");
		return forward;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4278C9BB0044
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{		
		SupervisionConditionForm conditionForm = (SupervisionConditionForm)aForm;
		Collection rules = conditionForm.getRules();
		if (rules != null && rules.size() > 0) {
			Collection newRuleList = new ArrayList();
			Iterator iterRules = rules.iterator();
			while (iterRules.hasNext()) {
				Rule rule = (Rule)iterRules.next();
				newRuleList.add(rule);
			}
			conditionForm.clearRules();
			conditionForm.setRules(newRuleList);
		}
		conditionForm.setAction(UIConstants.SUMMARY);
		return aMapping.findForward("success");
	}

}
