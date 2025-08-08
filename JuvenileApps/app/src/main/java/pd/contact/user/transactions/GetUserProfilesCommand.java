//Source file: C:\\views\\MSA\\app\\src\\pd\\contact\\transactions\\GetUserProfilesCommand.java

package pd.contact.user.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.authentication.GetJIMS2AccountEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.user.GetUserProfilesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import pd.contact.user.UserProfile;
import pd.security.JIMS2AccountView;
import pd.security.PDSecurityHelper;
import pd.transferobjects.helper.UserProfileHelper;

/**
 * @author dgibler This command is responsible for retrieving all User Profiles
 *         that match the criteria in the event that is passed in.
 */
public class GetUserProfilesCommand implements ICommand
{
    /**
     * @roseuid 4107BF64021D
     */
    public GetUserProfilesCommand()
    {
    }

    /**
     * @param event
     * @roseuid 4107B06C0268
     */
    public void execute(IEvent event)
    {
	GetUserProfilesEvent userEvent = (GetUserProfilesEvent) event;

	/// scenerio 1 jims2LogonId != null
	String jimsLogonId = userEvent.getLogonId();
	if (jimsLogonId != null && !jimsLogonId.equals(""))
	{
	    GetJIMS2AccountEvent jimsEvent = new GetJIMS2AccountEvent();
	    jimsEvent.setUserID(jimsLogonId);
	    jimsEvent.setLogonId(jimsLogonId);
	    MetaDataResponseEvent metaData = (MetaDataResponseEvent) JIMS2AccountView.findMeta(jimsEvent);

	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    if (metaData.getCount() > 2000)
	    {
		CountInfoMessage infoEvent = new CountInfoMessage();
		infoEvent.setCount(metaData.getCount());
		dispatch.postEvent(infoEvent);
	    }
	    else
	    {
		Iterator<JIMS2AccountView> jims2Iter = JIMS2AccountView.findAll(jimsEvent);

		if (jims2Iter.hasNext())
		{
		    while (jims2Iter.hasNext())
		    {
			JIMS2AccountView view = (JIMS2AccountView) jims2Iter.next();
			if (view != null)
			{
			    userEvent.setLogonId(view.getLogonId());
			    this.sendUserProfileToUI(userEvent);
			}
		    }
		}
		else
		{
		    this.sendUserProfileToUI(userEvent);
		}
	    }
	}

    }

    /**
     * @param userEvent
     */
    private void sendUserProfileToUI(GetUserProfilesEvent userEvent)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	if (PDSecurityHelper.isUserSA())
	{
	    userEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
	}
	else
	    if (PDSecurityHelper.isUserLA())
	    {
		userEvent.setDepartmentId(PDSecurityHelper.getUser().getDepartmentId());
	    }
	/*else if (PDSecurityHelper.isUserASA())
	{
	   // map = this.getDepartments(); //TODO 87191 do not worry about this, not used in jims2. check carlas email.
	}*/

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

		boolean firstOne = true;
		boolean thinResponse = false;

		if (userProfiles != null)
		{
		    Iterator<UserProfile> userProfilesItr = userProfiles.iterator();
		    while (userProfilesItr.hasNext())
		    {

			UserProfile userProfile = userProfilesItr.next();
			if (userProfile != null)
			{

			    if (firstOne)
			    {
				firstOne = false;
				if (userProfilesItr.hasNext())
				{
				    thinResponse = true;
				}
			    }
			    UserResponseEvent responseEvent = new UserResponseEvent();
			    userProfile.fillUserProfile(responseEvent, thinResponse);

			    /*if (PDSecurityHelper.isUserASA())
			    {
			        
			        //TODO replace with lathas webservice.
			        if (map.containsKey(userProfile.getDepartmentId()))
			        {
			    	dispatch.postEvent(responseEvent);
			        }
			    }
			    else
			    {*/
			    dispatch.postEvent(responseEvent);
			    //}
			}
		    }
		}
	    }
	}

    }

    //87191
    /*
    MetaDataResponseEvent metaData = (MetaDataResponseEvent) UserProfile.findMeta(userEvent);
    if (metaData.getCount() > 2000)
    {
        CountInfoMessage infoEvent = new CountInfoMessage();
        //	       	infoEvent.setMessage("Record count exceeded - total records found = " + metaData.getCount());
        infoEvent.setCount(metaData.getCount());
        dispatch.postEvent(infoEvent);
    }
    else
    {
        Iterator userProfiles = UserProfile.findAll(userEvent);
        boolean firstOne = true;
        boolean thinResponse = false;

        while (userProfiles.hasNext())
        {
    	UserProfile userProfile = (UserProfile) userProfiles.next();
    	// the following code ensures that if there are multiple matching 
    	// users that we don't send a fat response for the list 
    	if (firstOne)
    	{
    	    firstOne = false;
    	    if (userProfiles.hasNext())
    	    {
    		thinResponse = true;
    	    }
    	}
    	UserResponseEvent responseEvent = new UserResponseEvent();
    	userProfile.fillUserProfile(responseEvent, thinResponse);
    	// this is done like this since M2o4 program is hard to change for ASA
    	if (PDSecurityHelper.isUserASA())
    	{
    	    if (map.containsKey(userProfile.getDepartmentId()))
    	    {
    		dispatch.postEvent(responseEvent);
    	    }
    	}
    	else
    	{
    	    dispatch.postEvent(responseEvent);
    	}
        }
    }*/

    /**
     * @return
     */
    //Replace with same logic 87191
    /*   private HashMap getDepartments()
       {
    User user = User.find(PDSecurityHelper.getLogonId());
    HashMap map = new HashMap();
    if (user != null)
    {
        Collection constraints = user.getConstraintsByConstrainerType(Department.class);
        Iterator constraintsIterator = constraints.iterator();
        while (constraintsIterator.hasNext())
        {
    	Constraint constraint = (Constraint) constraintsIterator.next();
    	if (constraint != null && constraint.getConstrainerType().equalsIgnoreCase("DEPARTMENT"))
    	{
    	    DepartmentConstraintsForUserAdministrationResponseEvent rEvent = new DepartmentConstraintsForUserAdministrationResponseEvent();
    	    rEvent.setDepartmentId(constraint.getConstrainerId());
    	    Department department = Department.find(constraint.getConstrainerId());
    	    if (department != null)
    	    {
    		map.put(department.getDepartmentId(), department.getDepartmentName());
    	    }
    	}
        }
    }
    return map;
       }
    */
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