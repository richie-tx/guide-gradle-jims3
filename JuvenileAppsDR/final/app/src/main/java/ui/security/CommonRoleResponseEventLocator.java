/*
 * Created on Jun 3, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.security;

import java.util.HashMap;

import messaging.security.GetRolesEvent;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;

/**
 * @author mchowdhury
 * @description This class locates the special RoleResponseEvent such as EveryOne, 
 *              All Security Administrator. Since those response event has to be 
 *              returned in every Role search, this class captures the responseEvent
 *              in cache. So in the first instance of server role search, the event is 
 *              cached in memory and in the next calls, return it from the cache saving 
 *              to and from journey to database and hence saving resources.
 */
public class CommonRoleResponseEventLocator {
   private static HashMap cache;
   public synchronized static RoleResponseEvent getSpecialRoleResponseEvent(String roleName){
      if(cache != null && cache.containsKey(roleName))
         return (RoleResponseEvent) cache.get(roleName);	
      cache = new HashMap();
	  IDispatch requestDispatch = EventManager.getSharedInstance(EventManager.REQUEST);
      GetRolesEvent requestEvent = new GetRolesEvent();
	  requestEvent.setRoleName(roleName);
	  requestDispatch.postEvent(requestEvent);   	 
		
	  CompositeResponse replyEvent = (CompositeResponse) requestDispatch.getReply();
	  RoleResponseEvent responseEvent = (RoleResponseEvent) MessageUtil.filterComposite(replyEvent, RoleResponseEvent.class);
      
	  ReturnException returnException = 
			  (ReturnException) MessageUtil.filterComposite(replyEvent,ReturnException.class);
	  if (returnException == null){
	  	 cache.put(roleName,responseEvent);
	  	 return responseEvent;
	  }else
         return null;
   }
}
