//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\contact\\user\\transactions\\GetAvailableGenericLogonIdsCommand.java

package pd.contact.user.transactions;

import pd.contact.user.UserProfile;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.user.GetAvailableGenericLogonIdsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import java.util.Iterator;
import naming.PDAdministerServiceProviderConstants;

public class GetAvailableGenericLogonIdsCommand implements ICommand
{

	/**
	 * @roseuid 447C464302FA
	 */
	public GetAvailableGenericLogonIdsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 446A2E43026A
	 */
	public void execute(IEvent event)
	{
	    //87191
/*		GetAvailableGenericLogonIdsEvent thisEvent = (GetAvailableGenericLogonIdsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator iter = UserProfile.findAll(thisEvent);		
		while(iter.hasNext()) {
			UserProfile userProfile = (UserProfile) iter.next();
			
			if(userProfile.getLogonId() != null && !(userProfile.getLogonId().equals(""))) {
				if(!checkAdminUserProfileId(userProfile.getLogonId())) {			
					if(!checkContactUserProfileId(userProfile.getLogonId())) {
						System.out.println("---------DJNDJN----------------");
						System.out.println("---------DJNDJN--- userProfile.getLogonId    = " + userProfile.getLogonId());
						UserResponseEvent replyEvent = userProfile.getValueObject();
						dispatch.postEvent(replyEvent);		
						System.out.println("---------DJNDJN--- Posting replyEvent    = " + replyEvent);
						System.out.println("---------DJNDJN----------------");
					}				
				}
			}	*/		
/*
			if(userProfile.getLogonId() != null && !(userProfile.getLogonId().equals(""))) {
				if((!checkAdminUserProfileId(userProfile.getLogonId()) || !checkContactUserProfileId(userProfile.getLogonId()))) {			
					UserResponseEvent replyEvent = userProfile.getValueObject();
					dispatch.postEvent(replyEvent);	
				}
			}									
*/
		//}
	}
	
	private boolean checkAdminUserProfileId(String logonId)
	{
		Iterator iter = JuvenileServiceProvider.findAll(PDAdministerServiceProviderConstants.ADMINUSERPROFILE_ID, logonId);
		System.out.println("---------DJNDJN--- check Admin User Profile Id = " + logonId);
		if (iter.hasNext()) {	
			System.out.println("---------DJNDJN--- returning ADMIN TRUE ");
			return true;			
		}
		else {
			System.out.println("---------DJNDJN--- returning ADMIN False ");
			return false;
		}
	}

	private boolean checkContactUserProfileId(String logonId)
	{
		Iterator iter = JuvenileServiceProvider.findAll(PDAdministerServiceProviderConstants.CONTACTUSERPROFILE_ID, logonId);
		System.out.println("---------DJNDJN--- check Contact User Profile Id = " + logonId);
		if (iter.hasNext()) {
			System.out.println("---------DJNDJN--- returning CONTACT TRUE ");
			return true;			
		}
		else {
			System.out.println("---------DJNDJN--- returning CONTACT FALSE ");
			return false;
		}
	}

	/**
	 * @param event
	 * @roseuid 446A2E430276
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 446A2E430278
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 446A2E430285
	 */
	public void update(Object anObject)
	{

	}
}
