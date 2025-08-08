//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\SaveJuvenileCasefileSupervisionRulesCommand.java

package pd.juvenilecase.rules.transactions;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import naming.PDJuvenileCaseConstants;

import messaging.juvenilecase.SaveJuvenileCaseSupervisionRuleValueRequestEvent;
import messaging.rules.SaveSupervisonRuleEvent;
import messaging.rules.SaveTransferCasefileRulesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.rules.JuvenileCaseSupervisionRule;
import pd.juvenilecase.rules.JuvenileCaseSupervisionRuleValue;

public class SaveTransferCasefileRulesCommand implements ICommand
{

	/**
	 * @roseuid 43821BA50203
	 */
	public SaveTransferCasefileRulesCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4381F46B0234
	 */
	public void execute(IEvent event)
	{
		SaveTransferCasefileRulesEvent saveRulesEvent = (SaveTransferCasefileRulesEvent) event;
		String casefileId = String.valueOf(saveRulesEvent.getSupervisionNum());		
		
		JuvenileCaseSupervisionRule rule = JuvenileCaseSupervisionRule.find(saveRulesEvent.getRuleID());
		if(rule!=null)
		{
			rule.setCasefileId(casefileId);
		}
		IHome home = new Home();
		home.bind(rule);

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
