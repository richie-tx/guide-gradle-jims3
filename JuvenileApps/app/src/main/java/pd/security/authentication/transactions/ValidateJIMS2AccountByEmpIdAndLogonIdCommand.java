//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\security\\authentication\\transactions\\GetJIMS2AccountCommand.java

package pd.security.authentication.transactions;

import java.util.Iterator;

import messaging.authentication.ValidateJIMS2AccountByEmpIdAndLogonIdEvent;
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

public class ValidateJIMS2AccountByEmpIdAndLogonIdCommand implements ICommand
{

	/**
	 * @roseuid 4399BF8D02EE
	 */
	public ValidateJIMS2AccountByEmpIdAndLogonIdCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 439711A8008C
	 */
	public void execute(IEvent event)
	{
		ValidateJIMS2AccountByEmpIdAndLogonIdEvent vEvent = (ValidateJIMS2AccountByEmpIdAndLogonIdEvent) event;
		Iterator iter = JIMS2AccountType.findAll(vEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while(iter.hasNext())
		{
			JIMS2AccountType jims = (JIMS2AccountType) iter.next();
			if(jims != null){
				JIMS2AccountResponseEvent resp = new JIMS2AccountResponseEvent();
				resp.setJIMS2AccountTypeId(jims.getOID().toString());
				dispatch.postEvent(resp);
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