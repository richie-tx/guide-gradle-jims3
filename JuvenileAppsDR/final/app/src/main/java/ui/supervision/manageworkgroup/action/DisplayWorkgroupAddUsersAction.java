//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\manageworkgroup\\action\\DisplayWorkgroupAddUsersAction.java

package ui.supervision.manageworkgroup.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.manageworkgroup.ValidateWorkGroupEvent;
import messaging.supervisionoptions.reply.DuplicationNameErrorEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import naming.WorkGroupControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.manageworkgroup.form.WorkgroupForm;

/**
 * @author hrodriguez
 */
public class DisplayWorkgroupAddUsersAction extends JIMSBaseAction {

	/**
	 * @roseuid 45DB60F5023E
	 */
//	public DisplayWorkgroupAddUsersAction() {

//	}
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45DB5B240363
	 */

	// This button is clicked from Create/UpdateWorkgroup page and forward to
	// Create/UpdateWorkgroupAddUsers page
public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		WorkgroupForm wgForm = (WorkgroupForm) aForm;
		String action = wgForm.getAction();
		wgForm.setUserLastName("");
		wgForm.setUserFirstName("");
		wgForm.setJobTitleId("");
		wgForm.setDivisionId("");
		wgForm.setProgramUnitId("");
		wgForm.setPositionTypeId("");
		
		boolean duplicateWorkgroupName = false;
		if (!action.equals(UIConstants.UPDATE)
				|| ((action.equals(UIConstants.UPDATE)
					&& !wgForm.getWorkgroupName().equalsIgnoreCase(wgForm.getPreviousWorkgroupName()))))
			{
			duplicateWorkgroupName = this.checkForDuplicateName(wgForm.getWorkgroupName(),wgForm.getWorkgroupId(), wgForm.getAgencyId());
			}
		
			if (duplicateWorkgroupName)
			{
				this.sendToErrorPage(aRequest, "error.duplicate.workgroupName");
				forward = aMapping.findForward(UIConstants.DUPLICATES);
			}
			else
			{
				if (action.equals(UIConstants.CREATE))
				{
					forward = aMapping.findForward(UIConstants.CREATE_SUCCESS);
				}
				else
				{
					if (action.equals(UIConstants.UPDATE))
					{
						forward = aMapping.findForward(UIConstants.UPDATE_SUCCESS);
					}
					else
					{
						forward = aMapping.findForward(UIConstants.FAILURE);
					}
				}
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
	 * Determines if there is an existing Workgroup with the same name.
	 * 
	 * @param workgroupName
	 * @return
	 */
	private boolean checkForDuplicateName(String workgroupName, String workgroupId, String agencyId) {

		boolean duplicateWorkgroupName = false;
		if (workgroupName != null && !workgroupName.equals("")) {
			
			ValidateWorkGroupEvent requestEvent = (ValidateWorkGroupEvent) EventFactory
					.getInstance(WorkGroupControllerServiceNames.VALIDATEWORKGROUP);
			requestEvent.setName(workgroupName.toUpperCase());
			requestEvent.setWorkGroupId(workgroupId);
			requestEvent.setAgencyId(agencyId);
			
			// IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			// dispatch.postEvent(requestEvent);
			CompositeResponse compositeResponse = postRequestEvent(requestEvent);
		// This override the generic error
			//Object obj = MessageUtil.filterComposite(compositeResponse, WorkgroupDuplicateNameErrorEvent.class);
			Object obj = MessageUtil.filterComposite(compositeResponse, DuplicationNameErrorEvent.class);
			if (obj != null) {
				duplicateWorkgroupName = true;
			}
		}
		return duplicateWorkgroupName;
	}

}
