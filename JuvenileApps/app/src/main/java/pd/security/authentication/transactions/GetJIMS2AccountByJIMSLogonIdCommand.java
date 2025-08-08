//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\security\\authentication\\transactions\\GetJIMS2AccountCommand.java

package pd.security.authentication.transactions;

import java.util.Iterator;

import messaging.authentication.GetJIMS2AccountByJIMSLogonIdEvent;
import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.security.JIMS2Account;
import pd.security.JIMS2AccountType;

public class GetJIMS2AccountByJIMSLogonIdCommand implements ICommand
{

	/**
	 * @roseuid 4399BF8D02EE
	 */
	public GetJIMS2AccountByJIMSLogonIdCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 439711A8008C
	 */
	public void execute(IEvent event)
	{
		GetJIMS2AccountByJIMSLogonIdEvent getJIMS2AccountEvent = (GetJIMS2AccountByJIMSLogonIdEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		JIMS2AccountResponseEvent responseEvent=null;
		Iterator jims2AccountTypes = JIMS2AccountType.findAll("logonId", getJIMS2AccountEvent.getLogonId());
		while (jims2AccountTypes.hasNext())
		{
			JIMS2AccountType j2Account = (JIMS2AccountType)jims2AccountTypes.next();
			String acctId = j2Account.getJIMS2AccountId();
			JIMS2Account j2acct = JIMS2Account.find(acctId);
			if(j2acct!=null)
			{
				responseEvent = new JIMS2AccountResponseEvent();
				responseEvent.setJIMS2LogonId(j2acct.getJIMS2LogonId());
				responseEvent.setForgottenPasswdPhrase(j2acct.getPasswordQuestionId());
				responseEvent.setPasswordAnswer(j2acct.getAnswer());
				responseEvent.setJIMS2Password(j2acct.getPassword());
				responseEvent.setDepartmentName(j2acct.getDepartmentId());
				responseEvent.setFirstName(j2acct.getFirstName());
				responseEvent.setLastName(j2acct.getLastName());
				responseEvent.setMiddleName(j2acct.getMiddleName());
				responseEvent.setStatus(j2acct.getStatus());
				dispatch.postEvent(responseEvent);
				return;
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