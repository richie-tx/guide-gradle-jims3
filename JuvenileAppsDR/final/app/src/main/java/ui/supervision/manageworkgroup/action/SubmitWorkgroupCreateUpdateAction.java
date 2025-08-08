//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\manageworkgroup\\action\\SubmitWorkgroupCreateUpdateAction.java

package ui.supervision.manageworkgroup.action;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.manageworkgroup.CreateWorkGroupEvent;
import messaging.manageworkgroup.DeleteWorkGroupEvent;
import messaging.manageworkgroup.SaveWorkGroupEvent;
import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.manageworkgroup.form.WorkgroupForm;
import ui.supervision.manageworkgroup.form.WorkgroupUserBean;

/**
 * @author hrodriguez
 */
public class SubmitWorkgroupCreateUpdateAction extends JIMSBaseAction {

	/**
	 * @roseuid 45DB60FE0103
	 */
//	public SubmitWorkgroupCreateUpdateAction() {

//	}
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.update", "update");
		keyMap.put("button.delete", "finish");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.searchWorkgroups", "searchWorkgroups");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45DB5B250140
	 */

	// This button is clicked from WorkgroupDetails page and forward to
	// UpdateWorkgroup page.
	public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		WorkgroupForm wgForm = (WorkgroupForm) aForm;
		wgForm.setAction(UIConstants.UPDATE);

		forward = aMapping.findForward(UIConstants.UPDATE_SUCCESS);
		return forward;
	}

	// This button is clicked from WorkgroupDetails page and forward to
	// DeleteConfirmation page.
	/*public ActionForward delete(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		WorkgroupForm wgForm = (WorkgroupForm) aForm;
		wgForm.setAction(UIConstants.CONFIRM_DELETE);

		forward = aMapping.findForward(UIConstants.CONFIRM_DELETE_SUCCESS);
		return forward;
	}*/

	// This button is clicked from Create/Update/DeleteSummary page and forward
	// to Create/Update/DeleteConfirmation page.
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		WorkgroupForm wgForm = (WorkgroupForm) aForm;
		String action = wgForm.getAction();
// need to set action value from detail "view" page to correctly delete 		
		String action2 = wgForm.getSecondaryAction();
		wgForm.setSecondaryAction("");
		if (action2 != null && !action2.equals("")){
			action = action2;
		}
		
		if(action.equals(UIConstants.DELETE)){
//			 post delete event
			String workgroupId = wgForm.getWorkgroupId();
			DeleteWorkGroupEvent requestEvent = new DeleteWorkGroupEvent();
			requestEvent.setWorkGroupId(workgroupId);
			CompositeResponse compositeResponse = postRequestEvent(requestEvent);
		}else{
		    // create/update
			SaveWorkGroupEvent createEvent = new SaveWorkGroupEvent();
			createEvent.setAgencyId(wgForm.getAgencyId());
			createEvent.setWorkGroupId(wgForm.getWorkgroupId());
			createEvent.setName(wgForm.getWorkgroupName());
			createEvent.setDescription(wgForm.getWorkgroupDescription());
			createEvent.setType(wgForm.getWorkgroupTypeId());
			//set userids
			String[] userIds = new String[wgForm.getUserSelectedList().size()];
			int i = 0;
			for(Iterator iter = wgForm.getUserSelectedList().iterator(); iter.hasNext(); ){
			    WorkgroupUserBean wgUserBean = (WorkgroupUserBean)iter.next();
			    userIds[i++] = wgUserBean.getUserId();
			}
			createEvent.setUserIds(userIds);

			CompositeResponse compositeResponse = postRequestEvent(createEvent);
			
			WorkGroupResponseEvent wgre =
				(WorkGroupResponseEvent) MessageUtil.filterComposite(compositeResponse, WorkGroupResponseEvent.class);

			if (wgre != null)
			{
				wgForm.setWorkgroupId(wgre.getWorkgroupId());
			}
		}

		if (action.equals(UIConstants.CREATE)) {
			wgForm.setAction(UIConstants.CONFIRM_CREATE);
			forward = aMapping.findForward(UIConstants.CONFIRM_CREATE_SUCCESS);
		} else if (action.equals(UIConstants.UPDATE)) {
			wgForm.setAction(UIConstants.CONFIRM_UPDATE);
			forward = aMapping.findForward(UIConstants.CONFIRM_UPDATE_SUCCESS);
		} else if (action.equals(UIConstants.DELETE)) {
			wgForm.setAction(UIConstants.CONFIRM_DELETE);
			forward = aMapping.findForward(UIConstants.CONFIRM_DELETE_SUCCESS);
		} else {
			forward = aMapping.findForward(UIConstants.FAILURE);
		}
		return forward;
	}

	/**
	 * @param wgForm
	 * @return
	 */
	private CreateWorkGroupEvent getCreateRequestEvent(WorkgroupForm wgForm) {
		// TODO Auto-generated method stub
		return null;
	}

	// This button is clicked from Create/Update/DeleteConfirmation page and
	// return to ManageWorkgroups(Search)page.
	public ActionForward searchWorkgroups(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
	}

	// If this button is clicked from Create option, will return to
	// ManageWorkgroups(Search)page. If it's clicked from other options, will
	// return to ManageWorkgroupSearchResults page.
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

}
