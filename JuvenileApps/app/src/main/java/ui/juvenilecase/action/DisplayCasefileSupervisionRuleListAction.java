/*
 * Project: JIMS
 * Class:   ui.juvenilecase.action.DisplayCasefileSupervisionListAction
 *
 * Date:    2005-11-22
 *
 * Author:  Uma Gopinath
 * Email:   ugopinath@jims.hctx.net
 */

package ui.juvenilecase.action;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.caseplan.reply.GoalListResponseEvent;
import messaging.juvenilecase.reply.RuleDetailResponseEvent;
import messaging.juvenilecase.reply.RuleResponseEvent;
import messaging.rules.GetJuvenileCasefileSupervisionRuleDetailsEvent;
import messaging.rules.GetJuvenileCasefileSupervisionRulesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.Rule;
import ui.juvenilecase.UIJuvenileCasefileStatusHelper;
import ui.juvenilecase.UIJuvenileCasefileSupervisionRulesHelper;
import ui.juvenilecase.Factory.ISupervisionRuleRequestEventFactory;
import ui.juvenilecase.Factory.SupervisionRuleRequestEventFactory;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.SupervisionRulesForm;

/**
 * @author ugopinath
 * 
 */
public class DisplayCasefileSupervisionRuleListAction extends LookupDispatchAction
{
	/*
	 * 
	 */
	public ActionForward edit(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SupervisionRulesForm form = (SupervisionRulesForm)aForm;
		aRequest.setAttribute("action", "edit");

		// translate Date object into user friendly string
		Rule selectedRule = form.getSelectedRule() ;
		if( selectedRule != null )
		{
			Date completionDate = selectedRule.getCompletionDate();
			if( completionDate != null && completionDate.toString().length() > 0 )
			{
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				form.setCompletionDate(formatter.format(completionDate));
			}
		}

		return( aMapping.findForward("display") );
	}

	/*
	 * 
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SupervisionRulesForm form = (SupervisionRulesForm)aForm;
		aRequest.setAttribute("action", "summary");

		// translate from selectedCompletionId to user friendly string
		String completionDate = form.getCompletionDate();
		if( completionDate != null && completionDate.length() > 0 )
		{
			// Parse string date into Date object
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			ParsePosition pos = new ParsePosition(0);
			Date formattedDate = formatter.parse(completionDate, pos);
			form.getSelectedRule().setCompletionDate(formattedDate);
		}

		return( aMapping.findForward("display") );
	}

	/*
	 * 
	 */
	public ActionForward displayAll(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SupervisionRulesForm supervisionRulesForm = (SupervisionRulesForm)aForm;
		supervisionRulesForm.clearAll();

		ISupervisionRuleRequestEventFactory eventFactory = new SupervisionRuleRequestEventFactory();
		JuvenileCasefileForm headerForm = UIJuvenileCasefileSupervisionRulesHelper.getHeaderForm(aRequest);
		GetJuvenileCasefileSupervisionRulesEvent event = 
				eventFactory.buildGetJuvenileCasefileSupervisionRulesEvent(headerForm.getSupervisionNum());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		Collection<RuleResponseEvent> rules = MessageUtil.compositeToCollection(
				(CompositeResponse)dispatch.getReply(), RuleResponseEvent.class);
		if( rules != null )
		{
			for( RuleResponseEvent ruleResponseEvent : rules )
			{
				Rule rule = UIJuvenileCasefileSupervisionRulesHelper.getBasicRule(ruleResponseEvent);
				supervisionRulesForm.addAssignedRulesList(rule);
			}
		}
       	supervisionRulesForm.setAllowUpdates(UIJuvenileCasefileStatusHelper.casefileStatusClosed(aRequest));;
		return( aMapping.findForward(UIConstants.SUCCESS) );
	}

	/*
	 * 
	 */
	public ActionForward displayRuleDetails(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SupervisionRulesForm supRulesForm = (SupervisionRulesForm)aForm;
		String selectedValue = supRulesForm.getSelectedValue();
		supRulesForm.setSelectedRule(null);

		ISupervisionRuleRequestEventFactory eventFactory = new SupervisionRuleRequestEventFactory();
		GetJuvenileCasefileSupervisionRuleDetailsEvent event = 
				eventFactory.buildGetJuvenileCasefileSupervisionRuleDetailsEvent(selectedValue);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);

		CompositeResponse response = (CompositeResponse)dispatch.getReply();
		RuleDetailResponseEvent ruleResponse = (RuleDetailResponseEvent)
				MessageUtil.filterComposite(response, RuleDetailResponseEvent.class);

		// Populate rule found
		Rule selectedRule = null;
		if( ruleResponse != null )
		{
			selectedRule = UIJuvenileCasefileSupervisionRulesHelper.getDetailRule(ruleResponse);
			supRulesForm.setSelectedRule(selectedRule);
		}
		Collection goals = MessageUtil.compositeToCollection(response, GoalListResponseEvent.class);
		supRulesForm.setGoalList(goals);

       	supRulesForm.setAllowUpdates(UIJuvenileCasefileStatusHelper.casefileStatusClosed(aRequest));

       	ActionForward forward = null;
		if( selectedRule != null )
		{
			forward = aMapping.findForward("display");
			aRequest.setAttribute("action", "display");
		}
		
		return forward;
	}

	/*
	 * 
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}

	/*
	 * 
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return( aMapping.findForward(UIConstants.CANCEL) );
	}

	/*
	 * 
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.displayAll", "displayAll");
		keyMap.put("button.displayRuleDetails", "displayRuleDetails");
		keyMap.put("button.edit", "edit");
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		
		return keyMap;
	}
}
