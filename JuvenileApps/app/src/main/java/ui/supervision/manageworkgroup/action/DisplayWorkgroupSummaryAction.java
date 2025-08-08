//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\manageworkgroup\\action\\DisplayWorkgroupSummaryAction.java

package ui.supervision.manageworkgroup.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdstaffposition.CreateWorkgroupUsersEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.manageworkgroup.UIWorkgroupHelper;
import ui.supervision.manageworkgroup.form.WorkgroupForm;
import ui.supervision.manageworkgroup.form.WorkgroupUserBean;

/**
 * @author hrodriguez
 */
public class DisplayWorkgroupSummaryAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.findUsers", "findUsers");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.addSelectedUsers", "addSelectedUsers");
		keyMap.put("button.remove", "remove");
		keyMap.put("button.next", "next");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45DB5B25010E
	 */

	// This button is clicked from Create/UpdateWorkgroupAddUsers page and forward to
	// itself with userResultList.
	public ActionForward findUsers(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		WorkgroupForm wgForm = (WorkgroupForm) aForm;
		String action = wgForm.getAction();
		wgForm.clearUserResultList();
		
		
//		GetUsersEvent requestEvent = (GetUsersEvent) EventFactory
//				.getInstance(UserControllerServiceNames.GETUSERS);
//		requestEvent.setAgencyId(wgForm.getAgencyId());
//		requestEvent.setLastName(wgForm.getUserLastName());
//		requestEvent.setFirstName(wgForm.getUserFirstName());
		
		CreateWorkgroupUsersEvent requestEvent = (CreateWorkgroupUsersEvent) EventFactory.getInstance(CSCDStaffPositionControllerServiceNames.CREATEWORKGROUPUSERS);
		requestEvent.setAgencyId(wgForm.getAgencyId());
		requestEvent.setUserFirstName(wgForm.getUserFirstName());
		requestEvent.setUserLastName(wgForm.getUserLastName());
		requestEvent.setJobTitleId(wgForm.getJobTitleId());
		requestEvent.setDivisionId(wgForm.getDivisionId());
		requestEvent.setProgramUnitId(wgForm.getProgramUnitId());
		requestEvent.setPositionTypeId(wgForm.getPositionTypeId());
		
		CompositeResponse compositeResponse = postRequestEvent(requestEvent);
		Collection userResultList = MessageUtil.compositeToCollection(compositeResponse, CSCDSupervisionStaffResponseEvent.class);
		Map selectedListMap = UIWorkgroupHelper.buildResponseEventMap(wgForm.getUserSelectedList());
		if (userResultList != null && userResultList.size() > 0)
		{
		    // traverse through the list and add WorkGroupUser bean
		    for(Iterator iter = userResultList.iterator(); iter.hasNext(); ){
		    	CSCDSupervisionStaffResponseEvent cre = (CSCDSupervisionStaffResponseEvent)iter.next();
		    	WorkgroupUserBean wgUserBean = UIWorkgroupHelper.populateWorkgroupUserBean(cre);
//		    	Defect JIMS200052563 start here
		    	if(selectedListMap!=null && !selectedListMap.containsKey(wgUserBean.getUserId())){
		    		wgForm.addUserResult(wgUserBean);
		    	}
//		    	Defect JIMS200052563 end here		 
		    }
			forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
		}
		else
		{
			this.sendToErrorPage(aRequest, "error.noRecords");
			forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
		}
		
		return forward;
	}

	// This button is clicked from Create/UpdateWorkgroupAddUsers page and forward to
	// itself to clear all search criteria.
	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
//		ActionForward forward = new ActionForward();
		WorkgroupForm wgForm = (WorkgroupForm) aForm;
//		wgForm.refreshSearchUser();
		wgForm.setUserLastName("");
		wgForm.setUserFirstName("");
		wgForm.setJobTitleId("");
		wgForm.setDivisionId("");
		wgForm.setProgramUnitId("");
		wgForm.setPositionTypeId("");
		
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}

	// This button is clicked from Create/UpdateWorkgroupAddUsers page and forward to
	// itself to add Selected User to userSelectedList.
	public ActionForward addSelectedUsers(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		WorkgroupForm wgForm = (WorkgroupForm) aForm;
		String action = wgForm.getAction();
		
		Collection resultList = MessageUtil.processEmptyCollection(wgForm.getUserResultList());
		Map resultListMap = UIWorkgroupHelper.buildResponseEventMap(resultList);
		Map selectedListMap = UIWorkgroupHelper.buildResponseEventMap(wgForm.getUserSelectedList());

		String[] selectedUsers = wgForm.getSelectedUsers();
		for(int i = 0; i < selectedUsers.length; ){
		    String userId = selectedUsers[i++];
		    // add it only when it is already not there
		    if(!selectedListMap.containsKey(userId)){
			    WorkgroupUserBean cre = (WorkgroupUserBean) resultListMap.get(userId);
				wgForm.addUserSelected(cre);
				resultListMap.remove(userId);
		    }
		}
		wgForm.clearUserResultList();
		Collection coll1 = resultListMap.values();
		Iterator iter = coll1.iterator();
		while (iter.hasNext())
		{
			wgForm.addUserResult((WorkgroupUserBean)iter.next());
		}
		
		forward = aMapping.findForward(UIConstants.ADD_USER_SUCCESS);

		return forward;
	}
	
	// This button is clicked from Create/UpdateWorkgroupAddUsers page and forward to
	// itself to remove User to userSelectedList.
	public ActionForward remove(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		WorkgroupForm wgForm = (WorkgroupForm) aForm;
		String action = wgForm.getAction();
		
		String idToBeRemoved = wgForm.getUserId();

		Collection usersSelected = wgForm.getUserSelectedList();
		Map usersSelectedMap = UIWorkgroupHelper.buildResponseEventMap(usersSelected);
		WorkgroupUserBean objectToBeRemoved = (WorkgroupUserBean) usersSelectedMap.get(idToBeRemoved);

		usersSelectedMap.remove(idToBeRemoved);
		Collection coll1 = usersSelectedMap.values();
		ArrayList coll2 = new ArrayList();
		Object obj = null;
		Iterator iter = coll1.iterator();
		while (iter.hasNext())
		{
			obj = (Object) iter.next();
			coll2.add(obj);
		}

		wgForm.setUserSelectedList(coll2);

//		Collection userResultList = wgForm.getUserResultList();
//		userResultList.add(objectToBeRemoved);
//		wgForm.setUserResultList(userResultList);
		wgForm.addUserResult(objectToBeRemoved);
		forward = aMapping.findForward(UIConstants.REMOVE_FROM_LIST_SUCCESS);

		return forward;
	}
	

	// This button is clicked from Create/UpdateWorkgroupAddUsers page and forward to
	// Create/DeleteWorkgroupSummary page.
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		WorkgroupForm wgForm = (WorkgroupForm) aForm;
		String action = wgForm.getAction();
				
		if (action.equals(UIConstants.CREATE)) {
			forward = aMapping.findForward(UIConstants.CREATE_SUCCESS);
		} else if (action.equals(UIConstants.UPDATE)) {
			forward = aMapping.findForward(UIConstants.UPDATE_SUCCESS);
		} else {
			forward = aMapping.findForward(UIConstants.FAILURE);
		}
		return forward;
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		WorkgroupForm wgForm = (WorkgroupForm) aForm;
		String action = wgForm.getAction();

		if (action.equals(UIConstants.CREATE)) {
			forward = aMapping.findForward(UIConstants.CANCEL_CREATE);
		} else {
			forward = aMapping.findForward(UIConstants.CANCEL);
		}
		return forward;
	}
	
	/**
	 * @param map
	 * @param responseEvents
	 * @return
	 */
	private Collection filterOutDuplicates(Map map, Collection responseEvents)
	{
		Collection filteredCollection = new ArrayList();
		WorkgroupUserBean re = null;
		Object obj = null;
		Iterator iter = responseEvents.iterator();
		while (iter.hasNext())
		{
			re = (WorkgroupUserBean) iter.next();
			obj = map.get(re.getUserId());
			if (obj == null)
			{
			    responseEvents.remove(obj);
//				filteredCollection.add(re);
			}
		}
		return filteredCollection;
	}

}
