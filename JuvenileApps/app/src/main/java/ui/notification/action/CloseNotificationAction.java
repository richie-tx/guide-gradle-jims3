/*
 * Created on December 12, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.notification.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.notification.GetUserNoticesEvent;
import messaging.notification.UpdateNotificationStatusEvent;
import messaging.notification.reply.NotificationResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.NotificationControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.SecurityUIHelper;
import ui.security.authentication.form.LoginForm;

/**
 * @author mchowdhury
 *
 */
public class CloseNotificationAction extends LookupDispatchAction
{
	/**
	    * @roseuid 4399BB120155
	    */
	   protected Map getKeyMethodMap()
		{
			 Map keyMap = new HashMap();
			 keyMap.put("button.removeCustomRules", "close");
			 return keyMap;
		}
	   
	   public ActionForward close(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		LoginForm loginForm = (LoginForm) aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		UpdateNotificationStatusEvent updateEvent = 
				(UpdateNotificationStatusEvent) EventFactory.getInstance(NotificationControllerServiceNames.UPDATENOTIFICATIONSTATUS);		
		
		String[] selectedNotices = loginForm.getSelectedNotices();
		for(int i=0;i<selectedNotices.length;i++){
			updateEvent.setNotificationId(selectedNotices[i]);
			updateEvent.setStatus(UIConstants.CLOSED_STATUS_ID);
			dispatch.postEvent(updateEvent);
			
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map map = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(map);
		}
		this.callNotificationFramework(SecurityUIHelper.getLogonId(),loginForm);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param user
	 * @param loginForm
	 */
	private void callNotificationFramework(String logonId, LoginForm loginForm) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUserNoticesEvent pEvent = (GetUserNoticesEvent) EventFactory.getInstance(NotificationControllerServiceNames.GETUSERNOTICES);
		pEvent.setDestinationIdentityId(SecurityUIHelper.getLogonId());
		dispatch.postEvent(pEvent);
		
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Collection notices = MessageUtil.compositeToCollection(response, NotificationResponseEvent.class);
		Iterator noticeIter = notices.iterator();
		SortedMap noticeMap = new TreeMap();
		while(noticeIter.hasNext()){
			NotificationResponseEvent nEvent = (NotificationResponseEvent) noticeIter.next();
			noticeMap.put(nEvent.getNotification().getSentDate(), nEvent);			
		}
		Collection sortedNoticeList = this.sortNoticeCollection(noticeMap);
		loginForm.setNoticeList(sortedNoticeList);		
		if(notices != null){
			loginForm.setNoticeListSize("" + notices.size());
		}else{
			loginForm.setNoticeListSize("0");
		}
	}
	
	/**
	 * @param notice collection
	 * @return
	 */
	private Collection sortNoticeCollection(SortedMap noticeMap) {
		LinkedList stack = new LinkedList();
		Iterator it = noticeMap.values().iterator();
		while(it.hasNext()){
			NotificationResponseEvent nEvent = (NotificationResponseEvent) it.next();
		    stack.addFirst(nEvent);
		}
		
		Collection list = new ArrayList();
		Object[] obj = stack.toArray();
		for(int i=0;i<obj.length;i++){
		   list.add(obj[i]);
		}
		return list;	
	}	
}
