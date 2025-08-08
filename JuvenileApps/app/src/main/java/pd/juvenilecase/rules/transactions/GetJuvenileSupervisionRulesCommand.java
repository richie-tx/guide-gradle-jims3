//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefileSupervisionRulesCommand.java

package pd.juvenilecase.rules.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.juvenilecase.reply.JuvenileCasefileRulesResponseEvent;
import messaging.juvenilecase.reply.RuleResponseEvent;
import messaging.rules.GetJuvenileSupervisionRulesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.factory.IJuvenileCaseworkResponseFactory;
import pd.juvenilecase.factory.JuvenileCaseworkResponseFactory;
import pd.juvenilecase.rules.RuleGroupConditionView;

public class GetJuvenileSupervisionRulesCommand implements ICommand
{

	/**
	 * @roseuid 43821BA301C5
	 */
	public GetJuvenileSupervisionRulesCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4381F46B005F
	 */
	public void execute(IEvent event)
	{
		GetJuvenileSupervisionRulesEvent requestEvent = (GetJuvenileSupervisionRulesEvent) event;
		String juvNum = requestEvent.getJuvenileNumber();
		
		if ( juvNum != null && juvNum.length() > 0 )
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			IJuvenileCaseworkResponseFactory responseFactory =  new JuvenileCaseworkResponseFactory();
			
			Iterator casefileIterator = JuvenileCasefile.findAll( "juvenileId", juvNum );
			while ( casefileIterator.hasNext() )
			{
				JuvenileCasefile casefile = (JuvenileCasefile) casefileIterator.next();

				JuvenileCasefileRulesResponseEvent casefileResponse = new JuvenileCasefileRulesResponseEvent();
				casefileResponse.setSupervisionNum( casefile.getOID().toString() );
				casefileResponse.setJuvenileNum( juvNum );
				Collection rules = casefileResponse.getRules();

				Iterator rulesIterator = RuleGroupConditionView.findAll( "casefileId", casefile.getOID().toString() );
				while(rulesIterator.hasNext())
				{
					RuleGroupConditionView ruleView = (RuleGroupConditionView) rulesIterator.next();
					RuleResponseEvent response = responseFactory.getRuleResponseEvent(ruleView);
					rules.add( response );					
				}
				dispatch.postEvent( casefileResponse );
			}
		}
	}

	/**
	 * @param event
	 * @roseuid 4381F46B006E
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4381F46B0070
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 4381F46B0072
	 */
	public void update(Object anObject)
	{

	}

}
