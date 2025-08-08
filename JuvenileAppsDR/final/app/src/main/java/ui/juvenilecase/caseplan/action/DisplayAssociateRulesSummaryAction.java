package ui.juvenilecase.caseplan.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.RuleResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.caseplan.form.CaseplanForm;

/**
 * 
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayAssociateRulesSummaryAction extends LookupDispatchAction
{
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		CaseplanForm form = (CaseplanForm) aForm;
		Collection temp = form.getCurrentGoalInfo().getAssociatedRules();
		form.getCurrentGoalInfo().clear();
		Collection selectedRules = new ArrayList();
		String[] ruleIds = form.getCurrentGoalInfo().getSelectedRules();
		if(ruleIds != null)
		{
			Iterator iter = temp.iterator();
			while(iter.hasNext())
			{
				RuleResponseEvent evt = (RuleResponseEvent)iter.next();
				boolean stillSelected = true;
				for(int i=0; i<ruleIds.length;i++)
				{
					if(evt.getRuleId().equals(ruleIds[i]))
					{
						selectedRules.add(evt);
						stillSelected = true;
						break;
					} 
					stillSelected = false;
				}
				if(!stillSelected && evt.isCurrentlySelected()) {
					// atleast one already selected rule was deselected
					// which is not allowed. fwd to error
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.cannotDeselectRules"));
					saveErrors(aRequest, errors);
					return aMapping.findForward("error");
				}
			}
			if(selectedRules.size()==0)
			{
				aRequest.setAttribute("status", "noRules");
				return aMapping.findForward(UIConstants.SUCCESS);
			}
			form.getCurrentGoalInfo().setSelectedRulesList(selectedRules);
		}
		aRequest.setAttribute("status", "summary");
		
		return aMapping.findForward(UIConstants.SUCCESS);
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
		
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
	HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		
		return aMapping.findForward(UIConstants.BACK);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}
	
	
	
		
	
}