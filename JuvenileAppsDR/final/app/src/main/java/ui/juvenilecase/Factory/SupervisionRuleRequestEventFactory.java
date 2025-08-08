/*
 * Created on Dec 16, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.Factory;

import java.util.Collection;
import java.util.Iterator;

import messaging.juvenilecase.SaveJuvenileCaseSupervisionRuleValueRequestEvent;
import messaging.rules.GetJuvenileCasefileSupervisionRuleDetailsEvent;
import messaging.rules.GetJuvenileCasefileSupervisionRulesEvent;
import messaging.rules.GetJuvenileSupervisionRulesEvent;
import messaging.rules.SaveJuvenileCasefileSupervisionRulesEvent;
import messaging.rules.SaveSupervisonRuleEvent;
import messaging.supervisionoptions.SearchSupervisionConditionsDetailsEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import mojo.km.messaging.EventFactory;
import naming.JuvenileRulesControllerServiceNames;
import naming.SupervisionOptionsControllerServiceNames;
import ui.common.UIUtil;
import ui.juvenilecase.Rule;
import ui.juvenilecase.UIJuvenileCasefileSupervisionRulesHelper;
import ui.juvenilecase.form.SupervisionConditionForm;
import ui.security.SecurityUIHelper;

/**
 * @author athorat
 *
 */
public class SupervisionRuleRequestEventFactory implements ISupervisionRuleRequestEventFactory
{

	/**blake
	 * 
	 * 
	 */
	public SupervisionRuleRequestEventFactory()
	{
		super();
	}

	/* (non-Javadoc)
	 * @see ui.juvenilecase.Factory.ISupervisionRuleRequestEventFactory#getSaveSupervisionRulesEvent(ui.juvenilecase.form.SupervisionConditionForm)
	 */
	public SaveJuvenileCasefileSupervisionRulesEvent buildSaveSupervisionRulesEvent(
		SupervisionConditionForm conditionForm,
		String supervisionNum)
	{
		Collection rules = conditionForm.getRules();
		SaveJuvenileCasefileSupervisionRulesEvent saveJuvenileCasefileSupervisionRulesEvent =
			new SaveJuvenileCasefileSupervisionRulesEvent();
			
		saveJuvenileCasefileSupervisionRulesEvent.setSupervisionNum(supervisionNum);
		for (Iterator iter = rules.iterator(); iter.hasNext();)
		{
			Rule rule = (Rule)iter.next();
			SaveSupervisonRuleEvent saveSupervisonRuleEvent = new SaveSupervisonRuleEvent();

			saveSupervisonRuleEvent.setConditionId( rule.getConditionId() );
			saveSupervisonRuleEvent.setMonitorFrequencyId( rule.getMonitorFreqId() );
			saveSupervisonRuleEvent.setCompletionStatusId(rule.getCompletionStatusId());
			saveSupervisonRuleEvent.setCompletionDate(rule.getCompletionDate());
			saveSupervisonRuleEvent.setRuleTypeId(rule.getRuleTypeId());
			saveSupervisonRuleEvent.setAdditionalInformation(rule.getAdditionalInformation());
			saveSupervisonRuleEvent.setResolvedDesc(rule.getRuleLiteralResolved());
			saveSupervisonRuleEvent.setUnformattedDesc(UIUtil.removeXMLtags(rule.getRuleLiteralResolved(),true));
			saveSupervisonRuleEvent.setStatusChanged(rule.hasStatusChanged());
			setVarElements( rule, saveSupervisonRuleEvent );
			
			saveJuvenileCasefileSupervisionRulesEvent.addRequest(saveSupervisonRuleEvent);
		}
		
		return saveJuvenileCasefileSupervisionRulesEvent;
	}

	/**
	 * @param collection
	 * @param saveSupervisonRuleEvent
	 */
	private void setVarElements(
		Rule rule,
		SaveSupervisonRuleEvent saveSupervisonRuleEvent )
	{
		Collection varElements = rule.getVariables();
		for (Iterator iter = varElements.iterator(); iter.hasNext();)
		{
			VariableElementResponseEvent varElement = (VariableElementResponseEvent) iter.next();
			SaveJuvenileCaseSupervisionRuleValueRequestEvent saveVariableElementRequestEvent =
				new SaveJuvenileCaseSupervisionRuleValueRequestEvent();
			
			if ( varElement.isEnumeration() )
			{
				saveVariableElementRequestEvent.setValue( varElement.getValueId() );
			}
			else
			{
				saveVariableElementRequestEvent.setValue( varElement.getValue() );
			}
			
			String varElemId = varElement.getVariableElementId();
			if(varElemId != null && !varElemId.equals("")){
				saveVariableElementRequestEvent.setVariableElementId(varElemId);
			}
			saveSupervisonRuleEvent.addRequest(saveVariableElementRequestEvent);
		}

	}

	/* (non-Javadoc)
	 * @see ui.juvenilecase.Factory.ISupervisionRuleRequestEventFactory#getSearchSupervisionConditionsDetailsEvent(ui.juvenilecase.form.SupervisionConditionForm, java.lang.String, boolean)
	 */
	public SearchSupervisionConditionsDetailsEvent buildSearchSupervisionConditionsDetailsEvent(
		SupervisionConditionForm conditionForm,
		String supervisionTypeId,
		boolean standard)
	{
//		conditionForm.clearAll();
		SearchSupervisionConditionsDetailsEvent event =
			(SearchSupervisionConditionsDetailsEvent) EventFactory.getInstance(
				SupervisionOptionsControllerServiceNames.SEARCHSUPERVISIONCONDITIONSDETAILS);
		if (standard == false)
		{
			event.setGroup1(conditionForm.getGroup1Id());
			event.setGroup2(conditionForm.getGroup2Id());
			event.setGroup3(conditionForm.getGroup3Id());
		}
		else
		{
			event.setStandard(true);
		}
		event.setAgencyId(SecurityUIHelper.getUserAgencyId());
		event.setSupervisionTypeId(supervisionTypeId);
		event.addCourt( UIJuvenileCasefileSupervisionRulesHelper.rulesDefaultCourt );
		return event;
	}

	/* (non-Javadoc)
	 * @see ui.juvenilecase.Factory.ISupervisionRuleRequestEventFactory#getGetJuvenileCasefileSupervisionRulesEvent(java.lang.String)
	 */
	public GetJuvenileCasefileSupervisionRulesEvent buildGetJuvenileCasefileSupervisionRulesEvent(String supervisionNum)
	{
		GetJuvenileCasefileSupervisionRulesEvent rEvent =
			(GetJuvenileCasefileSupervisionRulesEvent) EventFactory.getInstance(
				JuvenileRulesControllerServiceNames.GETJUVENILECASEFILESUPERVISIONRULES);
		rEvent.setSupervisionNumber(supervisionNum);
		return rEvent;
	}

	/* (non-Javadoc)
	 * @see ui.juvenilecase.Factory.ISupervisionRuleRequestEventFactory#buildGetJuvenileCasefileSupervisionRuleDetailsEvent(java.lang.String)
	 */
	public GetJuvenileCasefileSupervisionRuleDetailsEvent buildGetJuvenileCasefileSupervisionRuleDetailsEvent(String ruleId)
	{
		GetJuvenileCasefileSupervisionRuleDetailsEvent rEvent =
			(GetJuvenileCasefileSupervisionRuleDetailsEvent) EventFactory.getInstance(
			JuvenileRulesControllerServiceNames.GETJUVENILECASEFILESUPERVISIONRULEDETAILS);
		rEvent.setRuleId(ruleId);
		return rEvent;

	}

	/* (non-Javadoc)
	 * @see ui.juvenilecase.Factory.ISupervisionRuleRequestEventFactory#getGetJuvenileCasefileSupervisionRulesEvent(java.lang.String)
	 */
	public GetJuvenileSupervisionRulesEvent buildGetJuvenileSupervisionRulesEvent(String juvenileNum)
	{
		GetJuvenileSupervisionRulesEvent rEvent =
			(GetJuvenileSupervisionRulesEvent) EventFactory.getInstance(
				JuvenileRulesControllerServiceNames.GETJUVENILESUPERVISIONRULES);
		rEvent.setJuvenileNumber(juvenileNum);
		return rEvent;
	}

}
