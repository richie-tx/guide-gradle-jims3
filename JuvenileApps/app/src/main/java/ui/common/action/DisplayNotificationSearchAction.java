/*
 * Created on Oct 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.common.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.user.reply.UserResponseEvent;
import messaging.notification.GetNoticeEvent;
import messaging.notification.GetUserNoticesEvent;
import messaging.notification.domintf.INotification;
import messaging.notification.reply.NotificationResponseEvent;
import messaging.task.domintf.ITask;
import messaging.task.reply.TaskResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.NotificationControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.common.form.SearchNotificationsForm;
import ui.common.form.TaskNotifBean;
import ui.security.SecurityUIHelper;
import ui.task.TaskHelper;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayNotificationSearchAction extends JIMSBaseAction {

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.link", "link");
		keyMap.put("button.view", "view");
	}

	public ActionForward view(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		SearchNotificationsForm myForm = (SearchNotificationsForm) aForm;		
		GetNoticeEvent notice = new GetNoticeEvent();
		notice.setNotificationId(myForm.getNotificationId());
	    List list = MessageUtil.postRequestListFilter(notice, NotificationResponseEvent.class);
	    if(list != null && !list.isEmpty()){
		    NotificationResponseEvent resp = (NotificationResponseEvent) list.get(0);
			myForm.setNoticeMessage(resp.getNotification().getMessage());
		}	    
	    return aMapping.findForward(UIConstants.VIEW_SUCCESS);
	}
	
	public ActionForward submit(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			SearchNotificationsForm myForm = (SearchNotificationsForm) aForm;
			ArrayList myList=new ArrayList();
			myForm.setSearchResults(myList);
			myForm.setDates();
			HashMap myUserMap=new HashMap();
			if(myForm.getNotificationTypeId().equals("N")){
				getNotifications(myForm,myUserMap);
			}
			else if(myForm.getNotificationTypeId().equals("T")){
				getTasks(myForm,myUserMap);
			}
			else{
				sendToErrorPage(aRequest,"error.generic","This type of search is unsupported. Please change the search type");
			}
			filterByDates(myForm);
			if(myForm.getSearchResults()==null || myForm.getSearchResults().size()<1){
				sendToErrorPage(aRequest,"error.no.search.results.found");
				return aMapping.findForward(UIConstants.SUCCESS);
			}
			return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
		}
	
	public void filterByDates(SearchNotificationsForm aForm){
		ArrayList myFilteredList=new ArrayList();
		Date startDate=aForm.getBeginDate();
		Date endDate=aForm.getEndDate();
		if(aForm.getSearchResults()!=null && aForm.getSearchResults().size()>0){
			Iterator myIter=aForm.getSearchResults().iterator();
			while(myIter.hasNext()){
				TaskNotifBean myBean=(TaskNotifBean)myIter.next();
				String tempDateStr=myBean.getSentDate();
				Date tempDate=null;
				if(tempDateStr!=null){
					tempDate=DateUtil.stringToDate(tempDateStr,UIConstants.DATE_FMT_1);
				}
				if(tempDate!=null){
					if((startDate.before(tempDate) || startDate.equals(tempDate)) && (endDate.after(tempDate) || endDate.equals(tempDate))){
						myFilteredList.add(myBean);
					}
				}
			}
		}
		aForm.setSearchResults(myFilteredList);
	}
	
	public void getNotifications(SearchNotificationsForm aForm, HashMap myUserMap){
		Collection myList=aForm.getSearchResults();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUserNoticesEvent pEvent = (GetUserNoticesEvent) EventFactory.getInstance(NotificationControllerServiceNames.GETUSERNOTICES);
		pEvent.setDestinationIdentityId(aForm.getOwnerUserId());
		dispatch.postEvent(pEvent);
		
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Collection notices = MessageUtil.compositeToCollection(response, NotificationResponseEvent.class);
		if(notices!=null && notices.size()>0){
			Iterator noticeIter = notices.iterator();
			while(noticeIter.hasNext()){
				NotificationResponseEvent nEvent = (NotificationResponseEvent) noticeIter.next();
				if(nEvent!=null && nEvent.getNotification()!=null){
					TaskNotifBean myBean=convert(nEvent.getNotification(), nEvent,aForm.getOwnerUserId(), myUserMap);
					myList.add(myBean);
				}
			}
		}
	}
	
	public void getTasks(SearchNotificationsForm aForm, HashMap myUserMap){
		Collection myList=aForm.getSearchResults();
		String status=UIConstants.CLOSED_STATUS_ID;
		Collection tasks = TaskHelper.getUserTasksByStatus(aForm.getOwnerUserId(),aForm.getTaskStatusId());
		if(tasks!=null && tasks.size()>0){
			Iterator tasksIter = tasks.iterator();
			
			while(tasksIter.hasNext()){
				TaskResponseEvent resp = (TaskResponseEvent) tasksIter.next();
				if(resp!=null && resp.getTask()!=null){
					TaskNotifBean myBean=convert(resp.getTask(),aForm.getOwnerUserId(),myUserMap);
					myList.add(myBean);
				}
				
			}
		}
	}
	
	public TaskNotifBean convert(INotification aNotif, NotificationResponseEvent aRespEvt, String defaultOwner, HashMap myUserMap){
		TaskNotifBean myBean=new TaskNotifBean();
		if(aRespEvt.getDestinationIdentity()!=null && !aRespEvt.getDestinationIdentity().equals("")){
			myBean.setOwnerUserId(aRespEvt.getDestinationIdentity());
		}
		else{
			myBean.setOwnerUserId(defaultOwner);
		}
		
		if(myBean.getOwnerUserId()!=null && !myBean.getOwnerUserId().equals("")){
			if(myUserMap.containsKey(myBean.getOwnerUserId())){
				myBean.setOwnerUserId(((String)myUserMap.get(myBean.getOwnerUserId())));
			}
			else{
			
				UserResponseEvent myUserEvt=SecurityUIHelper.getUser(myBean.getOwnerUserId());
				if(myUserEvt!=null && (myUserEvt.getFirstName()!=null || myUserEvt.getLastName()!=null)){
					Name myName=new Name();
					myName.setFirstName(myUserEvt.getFirstName());
					myName.setLastName(myUserEvt.getLastName());
					myBean.setOwnerUserId(myName.getFormattedName());
					myUserMap.put(myBean.getOwnerUserId(),myName.getFormattedName());
				}
			}
		}
		myBean.setStatusId(aNotif.getStatusCode());
		if(myBean.getStatusId()!=null && !myBean.getStatusId().equals("")){
			if(myBean.getStatusId().equals("P"))
				myBean.setStatus("PENDING");
			else if(myBean.getStatusId().equals("C"))
				myBean.setStatus("CLOSED");
			else if(myBean.getStatusId().equals("E"))
				myBean.setStatus("ERROR");
			else if(myBean.getStatusId().equals("O"))
				myBean.setStatus("OPEN");
			else
				myBean.setStatus(aNotif.getStatusCode());
		}
		
		myBean.setSubject(aNotif.getSubject());
		myBean.setTask(false);
		myBean.setNotificationId(aNotif.getNotificationId());
		myBean.setMessage(aNotif.getMessage());
	
		if(aNotif.getSentDate()!=null){
			String myDateStr=DateUtil.dateToString(aNotif.getSentDate(),UIConstants.DATE_FMT_1);
			myBean.setSentDate(myDateStr);
		}
		//myBean.setCreateDate(aRespEvt.getCreateDate());
		//myBean.setSentDate(aNotif.getSentDate());
		myBean.setType("NOTICE");
		myBean.setCategory("ADMINISTRATIVE");
		return myBean;
	}
	
	public TaskNotifBean convert(ITask aRespEvt, String defaultOwner, HashMap myUserMap){
		TaskNotifBean myBean=new TaskNotifBean();
//		if(aRespEvt.getOwnerId()!=null && !aRespEvt.getOwnerId().equals("")){
//			myBean.setOwnerUserId(aRespEvt.getOwnerId());
//		}
//		else{
			myBean.setOwnerUserId(defaultOwner);
//		}
		if(myBean.getOwnerUserId()!=null && !myBean.getOwnerUserId().equals("")){
			if(myUserMap.containsKey(myBean.getOwnerUserId())){
				myBean.setOwnerUserId(((String)myUserMap.get(myBean.getOwnerUserId())));
			}
			else{
				UserResponseEvent myUserEvt=SecurityUIHelper.getUser(myBean.getOwnerUserId());
				if(myUserEvt!=null && (myUserEvt.getFirstName()!=null || myUserEvt.getLastName()!=null)){
					Name myName=new Name();
					myName.setFirstName(myUserEvt.getFirstName());
					myName.setLastName(myUserEvt.getLastName());
					myBean.setOwnerUserId(myName.getFormattedName());
					myUserMap.put(myBean.getOwnerUserId(),myName.getFormattedName());
				}
			}
		}
		myBean.setStatusId(aRespEvt.getStatusCode());
		myBean.setStatus(aRespEvt.getStatus());
		myBean.setSubject(aRespEvt.getTaskSubject());
		myBean.setTask(true);
		myBean.setTaskId(aRespEvt.getTaskId());
		if(aRespEvt.getCreateDate()!=null){
			String myDateStr=DateUtil.dateToString(aRespEvt.getCreateDate(),UIConstants.DATE_FMT_1);
			myBean.setSentDate(myDateStr);
		}
		myBean.setUrl(aRespEvt.getUrl());
		myBean.setType("TASK");
		myBean.setCategory("ADMINISTRATIVE");
		
		return myBean;
	}
	
	public ActionForward link(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			SearchNotificationsForm myForm = (SearchNotificationsForm) aForm;
			myForm.clear();
			myForm.setDates();
			if(myForm.isAllowOnlyYours()){
				IUserInfo myUser=SecurityUIHelper.getUser();
				myForm.setOwnerUserId(SecurityUIHelper.getLogonId());
			}
			return aMapping.findForward(UIConstants.SUCCESS);
		}
	
	public ActionForward refresh(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			return link(aMapping,aForm,aRequest,aResponse);
		}
	
}
