//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetAllGroupsCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pd.supervision.Group;

import messaging.supervisionoptions.GetAllGroupsEvent;
import messaging.supervisionoptions.reply.GroupResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetAllGroupsCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C43903B9
    */
   public GetAllGroupsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997B03BE
    */
   public void execute(IEvent event) 
   {
		GetAllGroupsEvent groupsEvent = (GetAllGroupsEvent)event;
		String agencyId = groupsEvent.getAgencyId();
		

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		Iterator groups = Group.findAll( agencyId );
		groups=convertGroupToEvent(groups);
		while ( groups.hasNext() )
		{
			GroupResponseEvent evt = (GroupResponseEvent)groups.next();
			dispatch.postEvent( evt );
		}
   }
   
   private GroupResponseEvent getResponseEvent(Group group){
   		if(group==null)
   			return null;
   		GroupResponseEvent evt = new GroupResponseEvent();	
   		evt.setAgencyId(group.getAgencyId());
   		evt.setStatusCd(group.getStatusCd());
   		evt.setGroupId( group.getOID().toString() );
   		evt.setParentGroupId(group.getParentGroupId());
		evt.setName( group.getGroupName() );
		return evt;
   }
   
   public Iterator convertGroupToEvent( Iterator groups ) 
   {
		
	
		ArrayList level1Groups = new ArrayList();	// - Level 1 groups to return.
		HashMap groupMap = new HashMap();			// - Index of groups by id.
		while ( groups.hasNext() )
		{

			Group group = (Group)groups.next();
			GroupResponseEvent evt = getResponseEvent(group);
				if(checkIfActiveGroup(evt)){
				if ( group.getParentGroupId() == null )
					{
						level1Groups.add( evt );
					}
					groupMap.put( group.getOID(), evt );
			}
		}
		// Second iteration - Add level 1 groups to level 1 list. Add level 2 & 3 groups to 
		// appropiate parent. 
		groups = groupMap.values().iterator();  
		Object obj = null;
		GroupResponseEvent thisGroup = null;
		GroupResponseEvent parentGroup = null;
		while ( groups.hasNext() )
		{
			GroupResponseEvent group = (GroupResponseEvent)groups.next();
			if ( group.getParentGroupId() != null )
			{
				//GroupResponseEvent thisGroup = (GroupResponseEvent)groupMap.get(group.getGroupId());
				obj = groupMap.get(group.getGroupId());
				if (obj != null){
					thisGroup = (GroupResponseEvent) obj;
				} else {
					continue;
				}
				//parentGroup = (GroupResponseEvent)groupMap.get(group.getParentGroupId());
				obj = groupMap.get(group.getParentGroupId());
				if (obj != null){
					parentGroup = (GroupResponseEvent) obj;
					parentGroup.addSubgroup(thisGroup);
				} else {
					continue;
				}
			}
		}
		return level1Groups.iterator();
   }
   
   /**
    * @param event
    * @roseuid 42F7997B03C8
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997B03CA
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F7997B03CC
    */
   public void update(Object anObject) 
   {
    
   }
   
   /**
    * check if statuscd inactive, and return result
    * @param evt
    * @return
    */
   private boolean checkIfActiveGroup(GroupResponseEvent evt){
	   boolean isActive = true;
	   // add the new group column statuscd and check if "I"
	   if(evt.getStatusCd().equalsIgnoreCase("I")){
		   isActive = false;
	   }
	   return isActive;
   }
   
}
