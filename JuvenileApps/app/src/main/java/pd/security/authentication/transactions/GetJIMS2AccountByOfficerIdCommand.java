//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\security\\authentication\\transactions\\GetJIMS2AccountCommand.java

package pd.security.authentication.transactions;

import java.util.Iterator;

import messaging.authentication.GetJIMS2AccountByOfficerIdEvent;
import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.ResponseLocatorConstants;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.exception.ReflectionException;
import pd.security.JIMS2Account;
import pd.security.JIMS2AccountType;

public class GetJIMS2AccountByOfficerIdCommand implements ICommand
{

	/**
	 * @roseuid 4399BF8D02EE
	 */
	public GetJIMS2AccountByOfficerIdCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 439711A8008C
	 */
	public void execute(IEvent event)
	{
		GetJIMS2AccountByOfficerIdEvent getJIMS2AccountEvent = (GetJIMS2AccountByOfficerIdEvent) event;
		Iterator iter = JIMS2AccountType.findAll(getJIMS2AccountEvent);
		if(iter.hasNext())
		{
			JIMS2AccountType acctType = (JIMS2AccountType)iter.next();
			if(acctType!=null)
			{
				JIMS2Account jims2Acct = JIMS2Account.find(acctType.getJIMS2AccountId());
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
				if (jims2Acct != null)
				{
					ResponseContextFactory respFac = new ResponseContextFactory();
					ResponseCreator aCreator =  null;
			   		try {
						aCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.JIMS2ACCOUNT_RESPONSE_LOCATOR);
					} catch (ReflectionException e) {
						e.printStackTrace();
					}
					JIMS2AccountResponseEvent resp = (JIMS2AccountResponseEvent) aCreator.create(jims2Acct);
					dispatch.postEvent(resp);
				}
			}
		}	
	}

	/**
	 * @param event
	 * @roseuid 439711A8008E
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 439711A8009B
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 4399BF8D02FE
	 */
	public void update(Object updateObject)
	{

	}
}