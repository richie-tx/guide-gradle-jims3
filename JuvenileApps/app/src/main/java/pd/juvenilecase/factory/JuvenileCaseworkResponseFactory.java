/*
 * Created on Dec 8, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.factory;

import java.util.Collection;
import java.util.Iterator;

import messaging.juvenilecase.reply.RuleDetailResponseEvent;
import messaging.juvenilecase.reply.RuleResponseEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import naming.PDJuvenileCaseConstants;
import pd.juvenilecase.rules.JuvenileCaseSupervisionRule;
import pd.juvenilecase.rules.JuvenileCaseSupervisionRuleValue;
import pd.juvenilecase.rules.RuleGroupConditionView;
import pd.supervision.supervisionoptions.VariableElement;
import pd.supervision.supervisionoptions.VariableElementType;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileCaseworkResponseFactory implements IJuvenileCaseworkResponseFactory
{

	/**
	 * 
	 */
	public JuvenileCaseworkResponseFactory()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see pd.juvenilecase.Factory.IJuvenileCaseworkResponseFactory#getRuleResponseEvent(pd.juvenilecase.RuleGroupConditionView)
	 */
	public RuleResponseEvent getRuleResponseEvent(RuleGroupConditionView ruleview)
	{
		RuleResponseEvent response =  new RuleResponseEvent();
		response.setRuleId(ruleview.getRuleId());
		response.setCondCategoryId(Integer.toString(ruleview.getGroup1Id()));
		response.setCondTypeId(Integer.toString(ruleview.getGroup2Id()));
		response.setCondSubTypeId(Integer.toString(ruleview.getGroup3Id()));
		response.setRuleCompletionStatusId(ruleview.getCompletionStatusId());
		response.setRuleCompletionDate(ruleview.getCompletionDate());
		response.setTopic(PDJuvenileCaseConstants.RULE_LIST_TOPIC);
		response.setConditionId(ruleview.getConditionId());
		response.setRuleTypeId(ruleview.getRuleTypeId());
		response.setRuleMonitorFreqId(ruleview.getMonitorFreqId());
		response.setResolvedDesc(ruleview.getResolvedDesc());
		response.setUnformattedDesc(ruleview.getUnformattedDesc());
		if(ruleview.getCondition()!=null){
			response.setRuleName(ruleview.getCondition().getName());
			response.setStandard(ruleview.getCondition().getIsStandard());
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see pd.juvenilecase.Factory.IJuvenileCaseworkResponseFactory#getRuleDetailResponseEvent(pd.juvenilecase.RuleGroupConditionView)
	 */
	public RuleDetailResponseEvent getRuleDetailResponseEvent(RuleGroupConditionView ruleview)
	{
		JuvenileCaseSupervisionRule rule = JuvenileCaseSupervisionRule.find(String.valueOf(ruleview.getRuleId()));
		RuleDetailResponseEvent response = new RuleDetailResponseEvent();
		
		response.setRuleId(ruleview.getRuleId());
		response.setCondCategoryId(Integer.toString(ruleview.getGroup1Id()));
		response.setCondTypeId(Integer.toString(ruleview.getGroup2Id()));
		response.setCondSubTypeId(Integer.toString(ruleview.getGroup3Id()));
		response.setRuleCompletionStatusId(ruleview.getCompletionStatusId());
		response.setRuleCompletionDate(ruleview.getCompletionDate());
		response.setConditionId(ruleview.getConditionId());
		response.setConditionLiteral(ruleview.getLiteral());
		response.setRuleCompletionStatusId(rule.getCompletionStatusId());
		response.setRuleEntryDate(rule.getCreateTimestamp());
		response.setRuleMonitorFreqId(rule.getMonitorFrequencyId());
		response.setRuleTypeId(rule.getRuleTypeId());
		response.setAdditionalInformation(rule.getAdditionalInformation());
		response.setResolvedDesc(rule.getResolvedDesc());
		response.setUnformattedDesc(rule.getUnformattedDesc());
		if(rule.getCondition()!=null){
			response.setRuleName(rule.getCondition().getName());
			response.setStandard(rule.getCondition().getIsStandard());
		}
		Collection values = rule.getRuleValues();

		for ( Iterator iter = values.iterator(); iter.hasNext(); )
		{
			JuvenileCaseSupervisionRuleValue ruleValue = (JuvenileCaseSupervisionRuleValue)iter.next();
			VariableElement elem = ruleValue.getVariableElement();
			VariableElementType type = elem.getVariableElementType();
			
			VariableElementResponseEvent varElemRespEvt = new VariableElementResponseEvent();

			varElemRespEvt.setName( type.getName() );
			varElemRespEvt.setFixed( elem.getIsFixed() );
			
			if ( type.isEnumeration() )
			{
				varElemRespEvt.setValueId( ruleValue.getValue() );
//				varElemRespEvt.setCodeTableName( type.getVariableElementTypeCodeTable().getElementCodeTableId() );
				varElemRespEvt.setCodeTableName( type.getElementCodeTableId() );
			}
			else
			{
				varElemRespEvt.setValue( ruleValue.getValue() );
			}
			varElemRespEvt.setReference( type.isReference() );
			varElemRespEvt.setEnumeration( type.isEnumeration() );
			varElemRespEvt.setVariableElementId( elem.getOID().toString() );
			varElemRespEvt.setVariableElementTypeId( type.getOID().toString() );
			// Commented entries are not needed for Rules and are left here for reference. 			
			//varElemRespEvt.setcourtId;
			//varElemRespEvt.setIsExceptionCourt( elem.isExceptionCourt() );
			//following entries needed for the rule literal tag to show up correctly
			varElemRespEvt.setValueType( type.getType() );
			varElemRespEvt.setEnumerationTypeId( type.getEnumerationTypeId() );
			//varElemRespEvt.setenumerationTypeId;
			
			response.setVariable( varElemRespEvt.getName(), varElemRespEvt );			
		}
		response.setTopic(PDJuvenileCaseConstants.RULE_DETAIL_TOPIC);
		return response;
	}

}
