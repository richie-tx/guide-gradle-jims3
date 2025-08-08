//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\security\\authentication\\transactions\\ValidateJIMS2AccountCommand.java

package pd.security.authentication.transactions;

import java.util.Iterator;

import naming.PDConstants;
import naming.PDContactConstants;

import messaging.authentication.ValidateJIMS2AccountEvent;
import messaging.authentication.ValidateJIMS2AccountTypeEvent;
import messaging.security.authentication.reply.LoginErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.security.JIMS2Account;
import pd.security.JIMS2AccountType;
import pd.security.JIMS2AccountView;

public class ValidateJIMS2AccountCommand implements ICommand
{

	/**
	 * @roseuid 4399CCC10314
	 */
	public ValidateJIMS2AccountCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 439711A90052
	 */
	public void execute(IEvent event)
	{
		ValidateJIMS2AccountEvent validateEvent = (ValidateJIMS2AccountEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		ValidateJIMS2AccountTypeEvent j2AccountType = new ValidateJIMS2AccountTypeEvent();
		j2AccountType.setJIMS2AccountTypeId(validateEvent.getJIMS2AccountTypeId());
		j2AccountType.setJIMS2AccountTypeOID(validateEvent.getJIMS2AccountTypeOID());

		String accountTypeOID = validateEvent.getJIMS2AccountTypeOID();
		if ((accountTypeOID != null) && (!(accountTypeOID.equals(""))))
		{
			Iterator iter2 = JIMS2AccountType.findAll((IEvent) j2AccountType);
			if (iter2 != null)
			{
				while (iter2.hasNext())
				{
					JIMS2AccountType j2Account = (JIMS2AccountType) iter2.next();
					String acctId = j2Account.getJIMS2AccountId();
					JIMS2Account j2acct = JIMS2Account.find(acctId);
					if(j2acct.getStatus().equals(PDContactConstants.ACTIVE)){
					LoginErrorResponseEvent error = new LoginErrorResponseEvent();
					error.setMessage(
						"The JIMS2 User ID ("
							+ j2acct.getJIMS2LogonId()
							+ ") Already Exists with the badge number or other ID number entered- Please try another");
					dispatch.postEvent(error);
					return;
				}
				}
			}
		}

		String jims2LogonId = validateEvent.getJIMS2LogonId(); 
		if ((jims2LogonId != null) && (!(jims2LogonId.equals(""))))
		{
			JIMS2Account jims2Account = JIMS2Account.findByLogonId(jims2LogonId.toUpperCase());
			if (jims2Account != null)
			{
			    if(jims2Account.getStatus().equals(PDContactConstants.ACTIVE)){
				LoginErrorResponseEvent error = new LoginErrorResponseEvent();
				error.setMessage(
					"The JIMS2 User ID (" + jims2Account.getJIMS2LogonId() + ") Already Exists - Please try another");
				dispatch.postEvent(error);
			}}
		}
		
		String jimsLogonId = validateEvent.getJimsLogonId(); 
	
		if ("N".equalsIgnoreCase(validateEvent.getJIMS2AccountTypeId()) && (jimsLogonId != null) && (!(jimsLogonId.equals(""))))
		{
			Iterator iter = JIMS2AccountView.findAll("logonId", validateEvent.getJimsLogonId().toUpperCase());
			if (iter.hasNext())
			{
				JIMS2AccountView view = (JIMS2AccountView) iter.next();
				 if(view.getStatus().equals(PDContactConstants.ACTIVE)){
				LoginErrorResponseEvent error = new LoginErrorResponseEvent();
				error.setMessage(
					"The JIMS2 User ID has an existing account (" + view.getJIMS2LogonId() + "). Additional JIMS2 User ID is not permitted for this type of user");
				dispatch.postEvent(error);
			}}
		}
		
	}

	/**
	 * @param event
	 * @roseuid 439711A9005D
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 439711A9005F
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 4399CCC10323
	 */
	public void update(Object updateObject)
	{

	}
}
