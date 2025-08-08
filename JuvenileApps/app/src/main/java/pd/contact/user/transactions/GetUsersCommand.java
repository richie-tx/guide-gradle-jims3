//Source file: C:\\views\\archproduction\\app\\src\\pd\\contact\\user\\transactions\\GetUsersCommand.java

package pd.contact.user.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.user.GetUsersEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.SecurityManagerBaseResponse;
import mojo.km.security.Token;
import mojo.km.security.UserEntityBean;
import mojo.km.security.helper.SecurityManagerWebServiceHelper;
import pd.contact.PDContactHelper;
import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;
import pd.transferobjects.helper.UserProfileHelper;

/**
 * @author mchowdhury This class will get SecurityUserResponseEvent from
 *         datastore To change the template for this generated type comment go
 *         to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 *         Comments
 */
public class GetUsersCommand implements ICommand
{

    /**
     * @roseuid 429720FA00AF
     */
    public GetUsersCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4294862403CC
     */
    public void execute(IEvent event)
    {
	GetUsersEvent userEvent = (GetUsersEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	Object obj = UserProfileHelper.getUserProfiles(userEvent.getLogonId(), userEvent.getFirstName(), userEvent.getLastName(), userEvent.getDepartmentName(), userEvent.getDepartmentId());
	if (obj != null)
	{
	    if (obj instanceof UserProfile)
	    {
		UserProfile userProfile = (UserProfile) obj;
		CountInfoMessage infoEvent = new CountInfoMessage();
		infoEvent.setCount(userProfile.getMetaDataRespEvent().getCount());
		dispatch.postEvent(infoEvent);
	    }
	    if (obj instanceof List)
	    {
		List<UserProfile> userProfiles = (List<UserProfile>) obj;
		if (userProfiles != null)
		{
		    Iterator<UserProfile> userProfilesItr = userProfiles.iterator();
		    while (userProfilesItr.hasNext())
		    {
			UserProfile userProfile = userProfilesItr.next();
			if (userProfile != null)
			{
			    SecurityUserResponseEvent responseEvent = PDContactHelper.getSecurityUserResponseEvent(userProfile);
			    dispatch.postEvent(responseEvent);
			}
		    }
		}
	    }

	}

	//87191 Changed to the sm service
	//MetaDataResponseEvent metaData = (MetaDataResponseEvent) UserProfile.findMeta(userEvent); //87191
	/*if (metaData.getCount() > 2000)
	{
	    CountInfoMessage infoEvent = new CountInfoMessage();
	    //        	infoEvent.setMessage("Record count exceeded - total records found = " + metaData.getCount());
	    infoEvent.setCount(metaData.getCount());
	    dispatch.postEvent(infoEvent);
	}*/
	/*else
	{
	    Iterator userProfiles = UserProfile.findAll(userEvent);
	    while (userProfiles.hasNext())
	    {
		UserProfile userProfile = (UserProfile) userProfiles.next();
		if (userProfile != null)
		{
		    SecurityUserResponseEvent responseEvent = PDContactHelper.getSecurityUserResponseEvent(userProfile);
		    dispatch.postEvent(responseEvent);
		}
	    }
	}*/

    }

    /**
     * @param event
     * @roseuid 4294862403CE
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4294862403D0
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 4294862403DB
     */
    public void update(Object anObject)
    {

    }

}
