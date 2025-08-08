/*
 * Created on Nov 7, 2006
 *
 */
package pd.contact.officer.transactions;

import java.util.Iterator;

import naming.OfficerProfileControllerServiceNames;
import naming.PDOfficerProfileConstants;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.contact.user.UserProfile;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.officer.GetOfficerProfilesEvent;
import messaging.officer.ValidateOfficerEvent;
import messaging.user.reply.InvalidUserResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;

/**
 * @author Jim Fisher
 *  
 */
public class ValidateOfficerCommand implements ICommand
{

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent anEvent) throws Exception
    {
        ValidateOfficerEvent officerEvent = (ValidateOfficerEvent) anEvent;

        Iterator i = null;

        if (officerEvent.isValidateByUserId() == true)
        {
            // validate user ID
            i = this.fetchOfficerByUserId(officerEvent);
        } else
        {
            // validate by officer ID (badge, other, etc...)
            i = this.fetchOfficerProfile(officerEvent);
        }

        OfficerProfileResponseEvent officerProfileResponse = null;
        if (i != null && i.hasNext())

        {
	        while (i.hasNext())
	        {
	            OfficerProfile officerProfile = (OfficerProfile) i.next();
	            officerProfileResponse = PDOfficerProfileHelper.getThinOfficerProfileResponseEvent(officerProfile);
	            String officerStatusId = officerProfile.getStatusId();
	 
	            if (!officerStatusId.equals("I") && !officerStatusId.equals("INACTIVE"))
	            {    
	                EventManager.getSharedInstance(EventManager.REPLY).postEvent(officerProfileResponse);
	            }
	        } 
        }else if (officerEvent.isValidateByUserId())
        {
            // execute if officer record does not exist, find valid user
            this.fetchUser(officerEvent.getLogonId());
        }
    }

    /**
     * @param officerEvent
     */
    private Iterator fetchOfficerByUserId(ValidateOfficerEvent officerEvent)
    {
        String loginId = officerEvent.getLogonId().toUpperCase();
        return OfficerProfile.findAll("logonId", loginId);
    }

    /**
     * @param validateEvent
     * @return
     */
    private Iterator fetchOfficerProfile(ValidateOfficerEvent validateEvent)
    {
        GetOfficerProfilesEvent getOfficerProfilesEvent = (GetOfficerProfilesEvent) EventFactory
                .getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILES);
        getOfficerProfilesEvent.setDepartmentId(validateEvent.getDepartmentId());
        getOfficerProfilesEvent.setLogonId(validateEvent.getLogonId());
        
        if (validateEvent.getOfficerIdType().equalsIgnoreCase(PDOfficerProfileConstants.BADGE_NUM_ID))
        {
            getOfficerProfilesEvent.setBadgeNum(validateEvent.getOfficerId());
        }

        if (validateEvent.getOfficerIdType().equalsIgnoreCase(PDOfficerProfileConstants.OTHER_NUM_ID))
        {
            getOfficerProfilesEvent.setOtherIdNum(validateEvent.getOfficerId());
        }
        return OfficerProfile.findAll(getOfficerProfilesEvent);
    }

    private void fetchUser(String logonId)
    {
        UserProfile userProfile = null;

        // OID Query for UserProfile - use more efficient R7 query mapping
        if (logonId != null)
        {
        	String uloginId = logonId.toUpperCase();
            userProfile = UserProfile.find(uloginId);
        }

        if (userProfile == null)
        {
            InvalidUserResponseEvent invalidUserEvent = new InvalidUserResponseEvent();
            EventManager.getSharedInstance(EventManager.REPLY).postEvent(invalidUserEvent);
        } else
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
