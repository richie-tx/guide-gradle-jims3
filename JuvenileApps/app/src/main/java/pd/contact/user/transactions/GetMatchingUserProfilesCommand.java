//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\contact\\user\\transactions\\GetMatchingUserProfilesCommand.java

package pd.contact.user.transactions;

import java.util.Iterator;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import messaging.user.GetMatchingUserProfilesEvent;
import messaging.contact.user.reply.UserResponseEvent;
import pd.contact.user.UserProfile;
import pd.contact.PDContactHelper;


public class GetMatchingUserProfilesCommand implements ICommand 
{
   
   /**
    * @roseuid 442B04440186
    */
   public GetMatchingUserProfilesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 442AF82702E1
    */
   public void execute(IEvent event) 
   {
		/*GetMatchingUserProfilesEvent thisEvent = (GetMatchingUserProfilesEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator iter = UserProfile.findAll(thisEvent); 
		while (iter.hasNext())
		{
			UserProfile userProfile = (UserProfile) iter.next();
			UserResponseEvent userResponse = PDContactHelper.getUserResponseEvent(userProfile);
			dispatch.postEvent(userResponse);
		}
*/ //87191
   }
   
   /**
    * @param event
    * @roseuid 442AF82702EF
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 442AF82702F1
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 442AF82702F3
    */
   public void update(Object anObject) 
   {
    
   }
  
}
