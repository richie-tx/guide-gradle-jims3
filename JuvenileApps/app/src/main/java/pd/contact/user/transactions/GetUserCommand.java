/*
 * Created on Nov 7, 2006
 *
 */
package pd.contact.user.transactions;

import pd.contact.user.UserProfile;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.user.GetUserEvent;
import messaging.user.reply.InvalidUserResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;

/**
 * @author Jim Fisher
 *  
 */
public class GetUserCommand implements ICommand
{

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent anEvent) throws Exception
    {
        GetUserEvent userEvent = (GetUserEvent) anEvent;
        
        // OID Query for UserProfile - use more efficient R7 query mapping
        UserProfile userProfile = UserProfile.find(userEvent.getLogonId());
        
        if(userProfile == null)
        {
            InvalidUserResponseEvent invalidUserEvent = new InvalidUserResponseEvent();			
            EventManager.getSharedInstance(EventManager.REPLY).postEvent(invalidUserEvent);
        }
        else
        {
            UserResponseEvent userResponse = new UserResponseEvent();
            boolean thinResponse = true;
            userProfile.fillUserProfile(userResponse, thinResponse);
            EventManager.getSharedInstance(EventManager.REPLY).postEvent(userResponse);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject)
    {
        // TODO Auto-generated method stub

    }

}
