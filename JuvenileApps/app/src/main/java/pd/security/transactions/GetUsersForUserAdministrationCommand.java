/*
 * Project: JIMS2
 * Class:   pd.security.transactions.GetUsersForUserAdministrationCommand
 * Version: 0.8.15
 *
 * Date:    2005-04-28
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package pd.security.transactions;

import java.util.Iterator;

import messaging.info.reply.CountInfoMessage;
import messaging.security.GetUsersForUserAdministrationEvent;
import messaging.security.reply.UserResponseforUserAdministrationEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;

public class GetUsersForUserAdministrationCommand implements mojo.km.context.ICommand
{
	// ------------------------------------------------------------------------
	// --- constructor                                                      ---
	// ------------------------------------------------------------------------

	/**
	 * @roseuid 42712D1C0280
	 */
	public GetUsersForUserAdministrationCommand()
	{

	} //end of pd.security.transactions.GetUsersForUserAdministrationCommand.GetUsersForUserAdministrationCommand

	// ------------------------------------------------------------------------
	// --- methods                                                          ---
	// ------------------------------------------------------------------------

	/**
	 *  
	 * @param event @roseuid 42711B0D013A
	 */
	public void execute(IEvent event)
	{
		/*GetUsersForUserAdministrationEvent uEvent = (GetUsersForUserAdministrationEvent) event;
		MetaDataResponseEvent metaData = (MetaDataResponseEvent) UserProfile.findMeta(uEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if (metaData.getCount() > 2000){
			CountInfoMessage infoEvent = new CountInfoMessage();
//      	infoEvent.setMessage("Record count exceeded - total records found = " + metaData.getCount());
        	infoEvent.setCount(metaData.getCount());  
        	dispatch.postEvent(infoEvent);
		}else{
			Iterator users = UserProfile.findAll(uEvent);
			while (users.hasNext())
			{
				UserProfile user = (UserProfile) users.next();
				UserResponseforUserAdministrationEvent userEvent = PDSecurityHelper.getUserResponseEvent(user);
				dispatch.postEvent(userEvent);
			}
		}*/	

	} //end of pd.security.transactions.GetUsersForUserAdministrationCommand.execute

	/**
	*  
	* @param event @roseuid 42711B0D013C
	*/
	public void onRegister(IEvent event)
	{

	} //end of pd.security.transactions.GetUsersForUserAdministrationCommand.onRegister
	/**
	 *  
	 * @param event @roseuid 42711B0D013E
	 */
	public void onUnregister(IEvent event)
	{

	} //end of pd.security.transactions.GetUsersForUserAdministrationCommand.onUnregister
	/**
	 *  
	 * @param anObject @roseuid 42711B0D0140
	 */
	public void update(Object anObject)
	{

	} //end of pd.security.transactions.GetUsersForUserAdministrationCommand.update
} // end GetUsersForUserAdministrationCommand
