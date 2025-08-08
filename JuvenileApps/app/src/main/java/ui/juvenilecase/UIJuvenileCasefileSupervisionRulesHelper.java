/*
 * Created on Dec 16, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase;

import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import naming.UIConstants;

import messaging.juvenilecase.reply.RuleDetailResponseEvent;
import messaging.juvenilecase.reply.RuleResponseEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import ui.juvenilecase.form.JuvenileCasefileForm;

/**
 * @author athorat
 *
 */
public class UIJuvenileCasefileSupervisionRulesHelper
{
	public final static String rulesDefaultCourt = "JUV0313";	// was "CR 0176"
	
	/**
	 * 
	 */
	public UIJuvenileCasefileSupervisionRulesHelper()
	{
		super();
	}


	public static Rule getBasicRule( RuleResponseEvent ruleResponseEvent )
	{
		Rule rule = new Rule();
		
		rule.setRuleId( ruleResponseEvent.getRuleId() );
		rule.setCompletionDate( ruleResponseEvent.getRuleCompletionDate() );
		rule.setCompletionStatusId( ruleResponseEvent.getRuleCompletionStatusId() );
		rule.setOriginalStatusId(rule.getCompletionStatusId());
		rule.setMonitorFreqId( ruleResponseEvent.getRuleMonitorFreqId() );
		rule.setCondCategoryId( ruleResponseEvent.getCondCategoryId() );
		rule.setCondTypeId( ruleResponseEvent.getCondTypeId() );
		rule.setCondSubtypeId( ruleResponseEvent.getCondSubTypeId() );
		rule.setConditionId( ruleResponseEvent.getConditionId() );
		rule.setRuleTypeId(ruleResponseEvent.getRuleTypeId());
		rule.setUnformattedDesc(ruleResponseEvent.getUnformattedDesc());
		rule.setResolvedDesc(ruleResponseEvent.getResolvedDesc());
		rule.setRuleName(ruleResponseEvent.getRuleName());
		rule.setStandard(ruleResponseEvent.isStandard());
		return rule;
	}

	public static Rule getDetailRule( RuleDetailResponseEvent ruleResponseEvent )
	{
		Rule rule = getBasicRule( ruleResponseEvent );
		
		rule.setEntryDate(ruleResponseEvent.getRuleEntryDate());
		rule.setRuleLiteral(ruleResponseEvent.getConditionLiteral());
		rule.setRuleTypeId(ruleResponseEvent.getRuleTypeId());
		rule.setAdditionalInformation(ruleResponseEvent.getAdditionalInformation());
		rule.setUnformattedDesc(ruleResponseEvent.getUnformattedDesc());
		rule.setResolvedDesc(ruleResponseEvent.getResolvedDesc());
		rule.setRuleName(ruleResponseEvent.getRuleName());
		rule.setStandard(ruleResponseEvent.isStandard());
		Iterator vars = ruleResponseEvent.getVariables().iterator();
		while ( vars.hasNext() )
		{
			VariableElementResponseEvent newEvt = (VariableElementResponseEvent)((VariableElementResponseEvent)vars.next()).clone();
			rule.addVariable( newEvt );
		}
		
		return rule;
	}

	public static Rule createRule( ConditionDetailResponseEvent conditionResponseEvent )
	{
		Rule rule = new Rule();
		
		rule.setRuleId( null );
		rule.setCompletionDate( new Date() );
		rule.setCompletionStatusId(UIConstants.RULE_STATUS_ACTIVE);
		rule.setOriginalStatusId("");
		rule.setMonitorFreqId( null );
		rule.setCondCategoryId( conditionResponseEvent.getGroup1Id() );
		rule.setCondTypeId( conditionResponseEvent.getGroup2Id() );
		rule.setCondSubtypeId( conditionResponseEvent.getGroup3Id() );
		rule.setConditionId( conditionResponseEvent.getConditionId() );
		rule.setRuleLiteral(conditionResponseEvent.getDescription());
		rule.setRuleName(conditionResponseEvent.getName());
		rule.setStandard(conditionResponseEvent.isStandard());
		Iterator vars = conditionResponseEvent.getVariableElements().iterator();
		while ( vars.hasNext() )
		{
			VariableElementResponseEvent evt = (VariableElementResponseEvent)vars.next();
			if ( evt.getVariableElementId() != null )
			{
				VariableElementResponseEvent newEvt = (VariableElementResponseEvent)evt.clone();
				rule.addVariable( newEvt );
			}
		}
		
		return rule;
	}

	public static String getFreqId(ConditionDetailResponseEvent condition)
	{
		return condition.getConditionId() +"_"+ "FREQ";
	}

	public static String getFreqId(String conditionId)
	{
		return conditionId +"_"+ "FREQ";
	}

	public static String getVarElementId(ConditionDetailResponseEvent condition, VariableElementResponseEvent varElement)
	{
		return condition.getConditionId()+ "_" + varElement.getName() +"_"+ "ID";
	}

	public static JuvenileCasefileForm getHeaderForm(HttpServletRequest aRequest)
	{
		HttpSession session = aRequest.getSession();
		JuvenileCasefileForm headerForm = (JuvenileCasefileForm) session.getAttribute("juvenileCasefileForm");
		return headerForm;
	}
	
}
