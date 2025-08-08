package ui.juvenilecase.caseplan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.caseplan.AssociateRulesToGoalEvent;
import messaging.juvenilecase.reply.RuleResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasePlanControllerServiceNames;
import naming.UIConstants;

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
public class SubmitAssociateRulesAction extends LookupDispatchAction
{
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward saveContinue(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		CaseplanForm form = (CaseplanForm) aForm;
		AssociateRulesToGoalEvent associateEvent = (AssociateRulesToGoalEvent) EventFactory.getInstance(
				JuvenileCasePlanControllerServiceNames.ASSOCIATERULESTOGOAL);
		associateEvent.setGoalID(form.getCurrentGoalInfo().getGoalId());
		Iterator ite = form.getCurrentGoalInfo().getSelectedRulesList().iterator();
		while(ite.hasNext()) {
			RuleResponseEvent evt = (RuleResponseEvent)ite.next();
			if(!evt.isCurrentlySelected()) {
					associateEvent.insertRuleID(evt.getRuleId());
			}
			
		}
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(associateEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);	
		aRequest.setAttribute("status", "confirm");		
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
		CaseplanForm form = (CaseplanForm) aForm;
		form.getCurrentGoalInfo().setSelectedRules(new String[0]);
		form.getCurrentGoalInfo().setSelectedRulesList(new ArrayList());
		return aMapping.findForward(UIConstants.BACK);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.saveContinue", "saveContinue");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}
	
	
	
		
	
}