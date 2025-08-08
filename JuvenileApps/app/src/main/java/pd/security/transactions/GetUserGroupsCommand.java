//Source file: C:\\views\\archproduction\\app\\src\\pd\\security\\transactions\\GetUserGroupsCommand.java

package pd.security.transactions;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.security.GetUserGroupsEvent;
import messaging.security.reply.UserGroupResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.UserEntityBean;
import pd.security.PDSecurityHelper;

public class GetUserGroupsCommand implements ICommand
{

    /**
     * @roseuid 429721360081
     */
    public GetUserGroupsCommand()
    {
    }

    /**
     * @param event
     * @roseuid 428B82BB0360
     */
    public void execute(IEvent event)
    {
	GetUserGroupsEvent userEvent = (GetUserGroupsEvent) event;
	/*		MetaDataResponseEvent metaData = (MetaDataResponseEvent) UserGroup.findMeta(userEvent);
	        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	        if (metaData.getCount() > 2000){
	        	CountInfoMessage infoEvent = new CountInfoMessage();
	//        	infoEvent.setMessage("Record count exceeded - total records found = " + metaData.getCount());
	          	infoEvent.setCount(metaData.getCount());  
	        	dispatch.postEvent(infoEvent);
	        }else{*/ //87191

	List<UserEntityBean> users = PDSecurityHelper.getUserGroupByIdOrName(userEvent.getUserGroupName(),userEvent.getUserGroupDescription()); //bug Found while checking the notification for specialty court. Send either name or description.
	if (users != null)
	{
	    Iterator<UserEntityBean> usersItr = users.iterator();
	    while (usersItr.hasNext())
	    {
		UserEntityBean user = usersItr.next();
		if (user != null && user.getUseraccesses() != null)
		{
		    
		    this.postUserGroupEvent(user);
		}
	    }
	}
	//87191
	/*Iterator userGroups = UserGroup.findAll(userEvent);
	String agencyName = userEvent.getAgencyName();
	String agencyId = userEvent.getAgencyId();
	
	if (agencyName != null && !agencyName.equals("") || (agencyId != null && !agencyId.equals("")))
	{
		Map agenciesMap = this.retrieveAgencies(agencyName,agencyId);
		this.filterAndPostUserGroupEvents(userGroups,agenciesMap);
	}
	else
	{
		while (userGroups.hasNext())
		{
			UserGroup userGroup = (UserGroup) userGroups.next();
			if (userGroup != null)
			{
				this.postUserGroupEvent(userGroup);
			}
		}
	}*/
	// }	
    }

    /**
     * TODO revisited later
     * @param userGroups
     * @param agenciesMap
     */
    private void filterAndPostUserGroupEvents(Iterator userGroups, Map agenciesMap)
    {
	//87191
	/*if (!agenciesMap.isEmpty())
	{
		while (userGroups.hasNext())
		{
			UserGroup userGroup = (UserGroup) userGroups.next();
			if (userGroup != null)
			{
				Iterator constraints = userGroup.getConstraints().iterator();
				while (constraints.hasNext())
				{
					Constraint constraint = (Constraint) constraints.next();
					if (constraint != null)
					{
						String agencyCode = constraint.getConstrainerId();
						if (agenciesMap.containsKey(agencyCode))
						{
							this.postUserGroupEvent(userGroup);
						}
					}
				}
			}
		}
	}*/
    }

    private void postUserGroupEvent(UserEntityBean user)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	UserGroupResponseEvent responseEvent = PDSecurityHelper.getUserGroupResponseEvent(user);
	dispatch.postEvent(responseEvent);
    }
//87191
/*    private Map retrieveAgencies(String agencyName, String agencyId)
    {
	GetAgenciesEvent gaEvent = (GetAgenciesEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETAGENCIES);
	gaEvent.setAgencyName(agencyName);
	gaEvent.setAgencyId(agencyId);
	Iterator agencies = Agency.findAll(gaEvent);
	Map agenciesMap = new HashMap();
	Agency agency = null;
	while (agencies.hasNext())
	{
	    agency = ((Agency) agencies.next());
	    agenciesMap.put(agency.getAgencyId(), agency.getAgencyName());
	}
	return agenciesMap;
    }*/

    /**
     * @param event
     * @roseuid 428B82BB036C
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 428B82BB036E
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 428B82BB0370
     */
    public void update(Object anObject)
    {

    }
}