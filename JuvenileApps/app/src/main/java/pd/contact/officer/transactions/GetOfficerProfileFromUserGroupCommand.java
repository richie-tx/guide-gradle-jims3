package pd.contact.officer.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.GetOfficerProfileFromUserGroupEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.UserEntityBean;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.security.PDSecurityHelper;

public class GetOfficerProfileFromUserGroupCommand implements ICommand
{

    /**
     * @roseuid 4297213600CF
     */
    public GetOfficerProfileFromUserGroupCommand()
    {

    }

    /**
     * @param event
     * @roseuid 428B82BC0224
     */
    public void execute(IEvent event)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetOfficerProfileFromUserGroupEvent ugEvent = (GetOfficerProfileFromUserGroupEvent) event;
	List<UserEntityBean> userEBeans = PDSecurityHelper.getUserGroupByIdOrName(ugEvent.getUserGroupId(), "");
	if (userEBeans != null)
	{
	    Iterator<UserEntityBean> users = userEBeans.iterator();
	    while (users.hasNext()){
		UserEntityBean user = users.next();
		if (user != null)
		{
		    OfficerProfileResponseEvent officerProfileResponseEvent = null;

		    Iterator<OfficerProfile> officerprofiles = OfficerProfile.findAll("logonId", user.getUsername());
		    if (officerprofiles != null)
		    {
			while (officerprofiles.hasNext())
			{
			    OfficerProfile officerProfile = (OfficerProfile) officerprofiles.next();
			    officerProfileResponseEvent = PDOfficerProfileHelper.getBasicOfficerProfileResponseEvent(officerProfile);
			    dispatch.postEvent(officerProfileResponseEvent);
			}
		    }
		}
	    }
	}
	//87192
	/*UserGroup userGroup = PDSecurityHelper.getUserGroupByIdOrName(ugEvent.getUserGroupId(), "");//UserGroup.find(ugEvent.getUserGroupId());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	if (userGroup != null)
	{
	    Iterator<User> users = userGroup.getUsers().iterator();
	    users = PDContactHelper.sortedByUserName(users);
	    while (users.hasNext())
	    {
		User user = users.next();
		if (user != null)
		{
		    OfficerProfileResponseEvent officerProfileResponseEvent = null;

		    Iterator<OfficerProfile> officerprofiles = OfficerProfile.findAll("logonId", user.getJIMSLogonId());
		    if (officerprofiles != null)
		    {
			while (officerprofiles.hasNext())
			{
			    OfficerProfile officerProfile = (OfficerProfile) officerprofiles.next();
			    officerProfileResponseEvent = PDOfficerProfileHelper.getBasicOfficerProfileResponseEvent(officerProfile);
			    dispatch.postEvent(officerProfileResponseEvent);
			}
		    }
		}
	    }
	}*/
    }

    /**
     * @param event
     * @roseuid 428B82BC0226
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 428B82BC0232
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 428B82BC0234
     */
    public void update(Object anObject)
    {

    }
}
