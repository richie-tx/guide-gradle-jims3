// no longer in use. Migrated to SM. Refer US #87188.

//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\user\\transactions\\GetUserProfileCommand.java
package pd.contact.user.transactions;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.officer.ValidateOfficerProfileEvent;
import messaging.user.GetUserProfileEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.OfficerProfileControllerServiceNames;
import pd.contact.user.UserProfile;
import pd.transferobjects.helper.UserProfileHelper;

public class GetUserProfileCommand implements ICommand
{

    /**
     * @roseuid 42E67E8A0222
     */
    public GetUserProfileCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42E65EA601BC
     */
    public void execute(IEvent event)
    {
	GetUserProfileEvent thisEvent = (GetUserProfileEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	//UserProfile userProfile = UserProfile.find(thisEvent.getLogonId()); // 
	UserProfile userProfile = UserProfileHelper.getUserProfileFromJUCode(thisEvent.getLogonId());

	ValidateOfficerProfileEvent officerProfile = (ValidateOfficerProfileEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.VALIDATEOFFICERPROFILE);
	officerProfile.setLogonId(thisEvent.getLogonId());
	dispatch.postEvent(officerProfile);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	OfficerProfileResponseEvent officerResponse = (OfficerProfileResponseEvent) MessageUtil.filterComposite(compositeResponse, OfficerProfileResponseEvent.class);
	//Create user reply
	if (userProfile != null)
	{
	    UserResponseEvent userResponseEvent = new UserResponseEvent();
	    if (officerResponse != null)
	    {
		userResponseEvent.setBadgeNum(officerResponse.getBadgeNum());
		userResponseEvent.setOtherIdNum(officerResponse.getBadgeNum());
	    }
	    if (thisEvent.isThinResponseRequested())
	    {
		// the boolean parameter is requesting a thin response for the user
		userProfile.fillUserProfile(userResponseEvent, true);
	    }
	    else
	    {
		// the boolean parameter is requesting a thick response for the user
		userProfile.fillUserProfile(userResponseEvent, false);
	    }

	    dispatch.postEvent(userResponseEvent);
	}

    }

    /**
     * @param event
     * @roseuid 42E65EA601BE
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 42E65EA601C0
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 42E65EA601C2
     */
    public void update(Object anObject)
    {

    }
}