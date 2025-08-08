/*
 * Created on December 12, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.notification.GetUserNoticesEvent;
import messaging.notification.reply.NotificationResponseEvent;
import messaging.task.reply.TaskResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.NotificationControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.SecurityUIHelper;
import ui.security.authentication.AuthenticationHelper;
import ui.security.authentication.form.LoginForm;
import ui.task.TaskHelper;

/**
 * @author mchowdhury
 *
 */
public class DisplayHomeAction extends Action
{
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		LoginForm loginForm = (LoginForm) aForm;
		this.callNotificationFramework(SecurityUIHelper.getLogonId(),loginForm);
		//US 36464  Bug #55418
		AuthenticationHelper.getOfficerCaseload(loginForm);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param userId
	 * @param loginForm
	 */
	private void callNotificationFramework(String userId, LoginForm loginForm) {
		// call Task Framework
		
		ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo userInfo = mgr.getIUserInfo();
        String agencyId = userInfo.getAgencyId();
        int submittedTaskCount = 0;
        StringBuffer taskSize = new StringBuffer("");
        if ( !UIConstants.CSC.equalsIgnoreCase(agencyId) ){
        	
        	Collection tasks = TaskHelper.getNotClosedUserTasks(SecurityUIHelper.getLogonId(), UIConstants.CLOSED_STATUS_ID);
    		Iterator tasksIter = tasks.iterator();
    		SortedMap taskMap = new TreeMap();
    		while(tasksIter.hasNext()){
    			TaskResponseEvent resp = (TaskResponseEvent) tasksIter.next();
    			String statusId = resp.getTask().getStatusCode();
    			if(statusId != null && statusId.equalsIgnoreCase(UIConstants.SUBMITTED_STATUS_ID)){
    				submittedTaskCount++;
    			}
    			taskMap.put(resp.getTask().getSubmittedDate(), resp);
    		}
    		
    		Collection sortedList = this.sortTaskCollection(taskMap);
    		
    		taskSize.append(submittedTaskCount);
    		taskSize.append(" / ");
    		if(tasks != null && !tasks.isEmpty()){
    			taskSize.append(tasks.size());
    		}else{
    			taskSize.append("0");
    		}
    		loginForm.setTaskCount(taskSize.toString());
    		loginForm.setTaskList(new ArrayList(sortedList));
        } else {
        	
        	taskSize.append("0");
        	loginForm.setTaskCount(taskSize.toString());
        }
		
		// call notice framework
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
			if(!nEvent.getNotification().getSubject().equalsIgnoreCase("SERVICE LOCATION REMOVAL")){ //US 174962
				noticeMap.put(nEvent.getNotification().getSentDate(), nEvent);
			}
		}
		Collection sortedNoticeList = this.sortNoticeCollection(noticeMap);
		loginForm.setNoticeList(sortedNoticeList);
		if(sortedNoticeList != null){
			loginForm.setNoticeListSize("" + sortedNoticeList.size());
		}else{
			loginForm.setNoticeListSize("0");
		}
	}
	
	/**
	 * @param taskMap
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

	/**
	 * @param taskMap
	 * @return
	 */
	private Collection sortTaskCollection(SortedMap taskMap) {
		LinkedList stack = new LinkedList();
		Iterator it = taskMap.values().iterator();
		while(it.hasNext()){
			TaskResponseEvent resp = (TaskResponseEvent) it.next();
		    stack.addFirst(resp);
		}
		
		Collection list = new ArrayList();
		Object[] obj = stack.toArray();
		for(int i=0;i<obj.length;i++){
		   list.add(obj[i]);
		}
		return list;
	}
}
