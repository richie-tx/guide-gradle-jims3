//Source file: C:\\views\\MSA\\app\\src\\pd\\contact\\transactions\\GetUserProfilesCommand.java

package pd.contact.user.transactions;

import java.util.Iterator;

import messaging.authentication.GetJIMS2AccountEvent;
import messaging.security.GetUsersForUserAdministrationEvent;
import messaging.security.reply.UserResponseforUserAdministrationEvent;
import messaging.user.GetUserProfilesByUserAttributeAndLogonIdEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.contact.user.UserProfile;
import pd.security.JIMS2AccountView;
import pd.security.PDSecurityHelper;

/**
 * @author mchowdhury This command is responsible for retrieving all User
 *         Profiles that match the criteria in the event that is passed in.
 */
public class GetUserProfilesByUserAttributeAndLogonIdCommand implements ICommand
{
    /**
     * @roseuid 4107BF64021D
     */
    public GetUserProfilesByUserAttributeAndLogonIdCommand()
    {
    }

    /**
     * @param event
     * @roseuid 4107B06C0268
     */
    public void execute(IEvent event)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetUserProfilesByUserAttributeAndLogonIdEvent userEvent = (GetUserProfilesByUserAttributeAndLogonIdEvent) event;
	GetUsersForUserAdministrationEvent uEvent = new GetUsersForUserAdministrationEvent();
	uEvent.setLastName(userEvent.getLastName());
	uEvent.setFirstName(userEvent.getFirstName());
	uEvent.setAgencyName(userEvent.getAgencyName());
	uEvent.setAgencyId(userEvent.getAgencyId());
	uEvent.setDepartmentName(userEvent.getDeptName());
	uEvent.setDepartmentId(userEvent.getDeptId());

	String jimsLogonId = userEvent.getJimsLogonId();

	if (jimsLogonId != null && !jimsLogonId.equals(""))
	{
	    GetJIMS2AccountEvent accEvent = new GetJIMS2AccountEvent();
	    accEvent.setLogonId(jimsLogonId); //U.S #79250
	    Iterator<JIMS2AccountView> iter = JIMS2AccountView.findAll(accEvent);
	    while (iter.hasNext())
	    {
		JIMS2AccountView jims = (JIMS2AccountView) iter.next();
		this.getUserEvent(jims.getLogonId(), uEvent, dispatch);
	    }
	}
	else
	{
	    this.getUserEvent(userEvent.getJimsLogonId(), uEvent, dispatch);
	}
    }

    /**
     * @param logonId
     * @param event
     * @param dispatch
     * @param jims2LogonId
     */
    private void getUserEvent(String logonId, GetUsersForUserAdministrationEvent event, IDispatch dispatch)
    {
	/*event.setLogonId(logonId);
	Iterator<UserProfile> users = UserProfile.findAll(event);
	while (users.hasNext())
	{
	    UserProfile user = (UserProfile) users.next();
	    UserResponseforUserAdministrationEvent adminEvent = PDSecurityHelper.getUserResponseEvent(user);
	    //adminEvent.setJims2LogonId(jims2LogonId);//79250
	    dispatch.postEvent(adminEvent);
	}*/ //87191
    }

    /**
     * @param event
     * @roseuid 4107B06C026D
     */
    public void onRegister(IEvent event)
    {
    }

    /**
     * @param event
     * @roseuid 4107B06C026F
     */
    public void onUnregister(IEvent event)
    {
    }

    /**
     * @param updateObject
     * @roseuid 4107BF640227
     */
    public void update(Object updateObject)
    {
    }
}