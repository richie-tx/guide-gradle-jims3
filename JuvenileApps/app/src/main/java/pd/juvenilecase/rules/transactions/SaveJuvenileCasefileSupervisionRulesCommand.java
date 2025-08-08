//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\SaveJuvenileCasefileSupervisionRulesCommand.java

package pd.juvenilecase.rules.transactions;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import naming.PDJuvenileCaseConstants;

import messaging.juvenilecase.SaveJuvenileCaseSupervisionRuleValueRequestEvent;
import messaging.rules.SaveJuvenileCasefileSupervisionRulesEvent;
import messaging.rules.SaveSupervisonRuleEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.juvenilecase.rules.JuvenileCaseSupervisionRule;
import pd.juvenilecase.rules.JuvenileCaseSupervisionRuleValue;

public class SaveJuvenileCasefileSupervisionRulesCommand implements ICommand
{

	/**
	 * @roseuid 43821BA50203
	 */
	public SaveJuvenileCasefileSupervisionRulesCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4381F46B0234
	 */
	public void execute(IEvent event)
	{
		SaveJuvenileCasefileSupervisionRulesEvent saveRulesEvent = (SaveJuvenileCasefileSupervisionRulesEvent) event;
		String casefileId = String.valueOf(saveRulesEvent.getSupervisionNum());		
		
		Map rules = indexOnCondition( JuvenileCaseSupervisionRule.findAll( "casefileId", casefileId ) );
		
		Enumeration events = saveRulesEvent.getRequests();
		if (events != null)
		{
			Home home = new Home();
			while (events.hasMoreElements())
			{
				SaveSupervisonRuleEvent saveRuleEvent = (SaveSupervisonRuleEvent) events.nextElement();
				String condId = String.valueOf(saveRuleEvent.getConditionId());
				
				JuvenileCaseSupervisionRule rule = null;
				JuvenileCaseSupervisionRule myRule = (JuvenileCaseSupervisionRule)rules.get(condId);
				if ( myRule != null )   // the rule arleady exists. 
				{
					if(myRule.getCompletionStatusId().equals(PDJuvenileCaseConstants.RULE_STATUS_INACTIVE)){
						rule = new JuvenileCaseSupervisionRule();
						rule.setCasefileId( casefileId );
						rule.setConditionId(condId);
						rule.setMonitorFrequencyId(saveRuleEvent.getMonitorFrequencyId());
						rule.setRuleTypeId(saveRuleEvent.getRuleTypeId());
						rule.setResolvedDesc(saveRuleEvent.getResolvedDesc());
						rule.setUnformattedDesc(saveRuleEvent.getUnformattedDesc());
						rule.setCompletionStatusId(saveRuleEvent.getCompletionStatusId());
						home.bind(rule);
						saveElementValues( saveRuleEvent.getRequests(), rule );
						
					}
					else{
						rule=myRule;
					}
				}
				else
				{
					rule = new JuvenileCaseSupervisionRule();
					// The following attributes can only be set when the rule is created.
					rule.setCasefileId( casefileId );
					rule.setConditionId(condId);
					rule.setMonitorFrequencyId(saveRuleEvent.getMonitorFrequencyId());
					rule.setRuleTypeId(saveRuleEvent.getRuleTypeId());
					rule.setResolvedDesc(saveRuleEvent.getResolvedDesc());
					rule.setUnformattedDesc(saveRuleEvent.getUnformattedDesc());
					rule.setCompletionStatusId(saveRuleEvent.getCompletionStatusId());
					saveElementValues( saveRuleEvent.getRequests(), rule );
					home.bind(rule);
				}
				
				

				
				if(rule.getCompletionStatusId()!=null && (rule.getCompletionStatusId().equals(PDJuvenileCaseConstants.RULE_STATUS_COMPLETE) || rule.getCompletionStatusId().equals(PDJuvenileCaseConstants.RULE_STATUS_NON_COMPLIANT))  ){
					
				}
				else{
					
					rule.setAdditionalInformation(saveRuleEvent.getAdditionalInformation());
					
				}
//				 The folowing attributes may be updated after the rule is created.
				rule.setCompletionDate(saveRuleEvent.getCompletionDate());
				rule.setCompletionStatusId(saveRuleEvent.getCompletionStatusId());
			}
		}
		//Clear hashmap
		rules.clear();

	}

	/**
	 * @param varElementsRequests
	 * @param object
	 */
	private void saveElementValues(Enumeration varElementsRequests, JuvenileCaseSupervisionRule rule)
	{
		if(rule.getRuleValues()!=null){
			Map elements = indexOnVariableElement( rule.getRuleValues().iterator() );
			if(varElementsRequests!=null){
				while (varElementsRequests.hasMoreElements())
				{
					SaveJuvenileCaseSupervisionRuleValueRequestEvent saveVariableElementRequest =
						(SaveJuvenileCaseSupervisionRuleValueRequestEvent) varElementsRequests.nextElement();
		
					String variableElementId = saveVariableElementRequest.getVariableElementId();
					JuvenileCaseSupervisionRuleValue variableElementValue = (JuvenileCaseSupervisionRuleValue)elements.get(variableElementId);
					if ( variableElementValue == null)
					{
						variableElementValue = new JuvenileCaseSupervisionRuleValue();
						variableElementValue.setVariableElementId( variableElementId );
						rule.insertRuleValues(variableElementValue);
					}
									
					variableElementValue.setValue(saveVariableElementRequest.getValue());
				}
			}
		}

	}
	
	private Map indexOnCondition( Iterator iter )
	{
		HashMap index = new HashMap();
		
		while ( iter.hasNext() )
		{
			JuvenileCaseSupervisionRule rule = (JuvenileCaseSupervisionRule)iter.next();
			JuvenileCaseSupervisionRule myRule=(JuvenileCaseSupervisionRule)index.get(rule.getConditionId());
			if(myRule != null){
				
				if(myRule.getCompletionStatusId().equals(PDJuvenileCaseConstants.RULE_STATUS_INACTIVE)){
					if(rule.getCompletionStatusId().equals(PDJuvenileCaseConstants.RULE_STATUS_INACTIVE))
					{
						if(rule.getCompletionDate().after(rule.getCompletionDate())){
							index.put( rule.getConditionId(), rule );
						}
					}
					else{
						index.put( rule.getConditionId(), rule );
					}
				}
			}
			else{
				index.put( rule.getConditionId(), rule );
			}
		}
		
		return index;
	}
	private Map indexOnVariableElement( Iterator iter )
	{
		HashMap index = new HashMap();
		
		while ( iter.hasNext() )
		{
			JuvenileCaseSupervisionRuleValue variableElement = (JuvenileCaseSupervisionRuleValue)iter.next();
			index.put( variableElement.getVariableElementId(), variableElement );
		}
		
		return index;
	}

	/**
	 * @param event
	 * @roseuid 4381F46B0236
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4381F46B0238
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 4381F46B0243
	 */
	public void update(Object anObject)
	{

	}

}
