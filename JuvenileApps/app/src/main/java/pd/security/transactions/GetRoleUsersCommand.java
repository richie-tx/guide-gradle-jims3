package pd.security.transactions;

import java.util.Collection;
import java.util.Iterator;
import messaging.security.GetRoleUsersEvent;
import messaging.security.reply.RoleUsersResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.naming.SecurityConstants;
import mojo.km.security.Role;
import mojo.km.security.User;

/**
 * @author mchowdhury Gets users associated with a Role.
 */
public class GetRoleUsersCommand implements ICommand
{

    /**
     * @roseuid 425AB15803D8
     */
    public GetRoleUsersCommand()
    {

    }

    /**
     * @param event
     * @roseuid 425551F802B1
     */
    public void execute(IEvent event)
    {
       /* GetRoleUsersEvent userEvent = (GetRoleUsersEvent) event;
        Role role = Role.find(userEvent.getRoleId());
        Collection users = role.getUsers();

        Iterator uIt = users.iterator();
        User user = null;
        while (uIt.hasNext())
        {
            user = (User) uIt.next();
            if (user != null)
            {
                sendUserInfo(user, role);
            }
        }*///87191
    }

    /**
     * @param user
     * @param role
     */
    private void sendUserInfo(User user, Role role)
    {
	//87191
        /*IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        RoleUsersResponseEvent responseEvent = new RoleUsersResponseEvent();
        responseEvent.setTopic(SecurityConstants.ROLE_USER_EVENT_TOPIC);
        responseEvent.setRoleId(role.getOID().toString());
        responseEvent.setUserId(user.getJIMSLogonId());
        responseEvent.setUserLoginName(user.getJIMSLogonId());
        responseEvent.setFirstName(user.getFirstName());
        responseEvent.setMiddleName(user.getMiddleName());
        responseEvent.setLastName(user.getLastName());
        responseEvent.setUserType(user.getUserTypeId());
        dispatch.postEvent(responseEvent);*/ //87191
    }

    /**
     * @param event
     * @roseuid 425551F802B3
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 425551F802B5
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 425551F802B7
     */
    public void update(Object anObject)
    {

    }
}
