package ui.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.supervisionoptions.GetAllGroupsEvent;
import messaging.supervisionoptions.reply.GroupResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import ui.security.SecurityUIHelper;

/**
 * @author bschwartz
 *
 */
public class GroupHelper
{
	// Cached groups by agency 
	private static Map groupsByAgency = new HashMap();

	/**
	 * 
	 */
	public static String getGroupName( String groupId )
	{
		Map groupMap = getGroupsByAgency( SecurityUIHelper.getUserAgencyId() );
		
		Object object = groupMap.get(groupId);
		if ( object == null )
		{
			return "";
		}
		else
		{
			return ((GroupResponseEvent)object).getName();
		}
	}

	/**
	 * Recursively find type name from typeId
	 */
	public static String getTypeName( String typeId )
	{
		Map groupMap = getGroupsByAgency( SecurityUIHelper.getUserAgencyId() );
		
		Iterator iter = groupMap.values().iterator();
		while(iter.hasNext())
		{
			GroupResponseEvent gre = (GroupResponseEvent) iter.next();
			Iterator typeIter = gre.getSubgroups().iterator();
			while(typeIter.hasNext())
			{
				GroupResponseEvent typeGRE = (GroupResponseEvent) typeIter.next();
				if(typeGRE.getGroupId().equals(typeId))
					return typeGRE.getName() ;
			}
		}
		return "";
	}
	
	public static String getSubTypeName ( String subTypeId )
	{
		Map groupMap = getGroupsByAgency( SecurityUIHelper.getUserAgencyId() );
		
		Iterator iter = groupMap.values().iterator();
		while(iter.hasNext())
		{
			GroupResponseEvent gre = (GroupResponseEvent) iter.next();
			Iterator typeIter = gre.getSubgroups().iterator();
			while(typeIter.hasNext())
			{
				GroupResponseEvent typeGRE = (GroupResponseEvent) typeIter.next();
				Iterator subTypeIter = typeGRE.getSubgroups().iterator();
				while(subTypeIter.hasNext())
				{
					GroupResponseEvent subTypeGRE = (GroupResponseEvent) subTypeIter.next();
					if(subTypeGRE.getGroupId().equals(subTypeId))
						return subTypeGRE.getName() ;
				}
			}
		}
		return "";
	}

	/**
	 * 
	 */
	private static Map getGroupsByAgency( String agencyId )
	{
		if ( ! groupsByAgency.containsKey(agencyId) )
		{
			GetAllGroupsEvent event = new GetAllGroupsEvent(); 
			event.setAgencyId(agencyId);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(event);
			Collection groups =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), GroupResponseEvent.class );
			
			Map groupMap = new HashMap();
			if ( groups != null )
			{
				for ( Iterator iter = groups.iterator(); iter.hasNext(); )
				{
					GroupResponseEvent groupEvent = (GroupResponseEvent) iter.next();
					groupMap.put( groupEvent.getGroupId(), groupEvent );
				}
			}
			groupsByAgency.put( agencyId, groupMap );
		}

		return (Map)groupsByAgency.get( agencyId );		
	}

}
