//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\manageworkgroup\\action\\SubmitWorkgroupCreateUpdateAction.java

package ui.supervision.manageworkgroup.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.manageworkgroup.GetWorkGroupEvent;
import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.manageworkgroup.UIWorkgroupHelper;
import ui.supervision.manageworkgroup.form.WorkgroupForm;
import ui.supervision.manageworkgroup.form.WorkgroupSearchForm;

/**
 * @author hrodriguez
 */
public class HandleWorkgroupSelectionAction extends JIMSBaseAction {

	/**
	 * @roseuid 45DB60FE0103
	 */
//	public HandleWorkgroupSelectionAction() {

//	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.view", "view");
		keyMap.put("button.update", "update");
		keyMap.put("button.delete", "delete");
		keyMap.put("button.link", "link");
		keyMap.put("button.details", "popup");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @throws GeneralFeedbackMessageException 
	 * @roseuid 45DB5B250140
	 */

	// This Name hyperlink from ManageWorkgroupsSearchResults page and forward
	// to WorkgroupDetails page.
	public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		WorkgroupForm wgForm = (WorkgroupForm) aForm;
		wgForm.setAction(UIConstants.VIEW);
		this.getWorkgroupDetails(wgForm);
		WorkgroupSearchForm wsForm = (WorkgroupSearchForm) getSessionForm(aMapping,aRequest,"workgroupSearchForm",true);
		return aMapping.findForward(UIConstants.VIEW_SUCCESS);
	}

	// This Name hyperlink from SearchTransferWorkgroupResults page and forward
	// to WorkgroupDetails page.
	public ActionForward popup(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		WorkgroupForm wgForm = (WorkgroupForm) aForm;
		wgForm.setAction("popup");
		this.getWorkgroupDetails(wgForm);
		
		return aMapping.findForward(UIConstants.VIEW_DETAIL);
	}	
	// This button is clicked from ManageWorkgroupsSearchResults page and
	// forward to Update Workgroup page.
	public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		WorkgroupForm wgForm = (WorkgroupForm) aForm;
		wgForm.setAction(UIConstants.UPDATE);
		String workgroupId = wgForm.getWorkgroupId();
		
		wgForm.refreshSearchUser();
		wgForm.clearSelectedUserList();
		wgForm.clearUserResultList();

		// post detail event

		GetWorkGroupEvent requestEvent = new GetWorkGroupEvent();
		requestEvent.setWorkGroupId(workgroupId);
		
		CompositeResponse compositeResponse = postRequestEvent(requestEvent);
		Collection workgroupList = MessageUtil.compositeToCollection(compositeResponse, WorkGroupResponseEvent.class);

		if (workgroupList != null)
		{
		    Iterator iter = workgroupList.iterator();
		    if(iter.hasNext()){
			    WorkGroupResponseEvent wgre = (WorkGroupResponseEvent)iter.next();
				UIWorkgroupHelper.populateFormFromResponseEvent(wgre, wgForm);
		    }
		}
		boolean grantedFeature = false;		
		try
		{
			ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
			if (securityManager != null){
				grantedFeature = securityManager.isAllowed(UIConstants.CS_WORKGRP_UPDATE_ALL);				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		forward = aMapping.findForward(UIConstants.SKIP_TO_ADD_USER);
		if(grantedFeature){
			forward = aMapping.findForward(UIConstants.UPDATE_SUCCESS);
		} 
		
		return forward;
	}

	// This button is clicked from ManageWorkgroupsSearchResults page and
	// forward to DeleteWorkgroupSummary page.
	public ActionForward delete(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		WorkgroupForm wgForm = (WorkgroupForm) aForm;
		String workgroupId = wgForm.getWorkgroupId();
		
		wgForm.refreshSearchUser();
		wgForm.clearSelectedUserList();
		wgForm.clearUserResultList();

		// post detail event

		GetWorkGroupEvent requestEvent = new GetWorkGroupEvent();
		requestEvent.setWorkGroupId(workgroupId);
		
		CompositeResponse compositeResponse = postRequestEvent(requestEvent);
		Collection workgroupList = MessageUtil.compositeToCollection(compositeResponse, WorkGroupResponseEvent.class);

		if (workgroupList != null)
		{
		    Iterator iter = workgroupList.iterator();
		    if(iter.hasNext()){
			    WorkGroupResponseEvent wgre = (WorkGroupResponseEvent)iter.next();
				UIWorkgroupHelper.populateFormFromResponseEvent(wgre, wgForm);
		    }
		}
		wgForm.setAction(UIConstants.DELETE);

		forward = aMapping.findForward(UIConstants.DELETE_SUCCESS);
		return forward;
	}

	// This hyperlink accessed from CSCD Assign Supervisee to Program Unit - Select Workgroup 
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		WorkgroupForm wgForm = (WorkgroupForm) aForm;
		this.getWorkgroupDetails(wgForm);
		
		return aMapping.findForward(UIConstants.VIEW_DETAIL);
	}

	// common data retreival for details and view
	private void getWorkgroupDetails(WorkgroupForm wgForm) {
			String workgroupId = wgForm.getWorkgroupId();
			
			wgForm.refreshSearchUser();
			wgForm.clearSelectedUserList();
			wgForm.clearUserResultList();

			GetWorkGroupEvent requestEvent = new GetWorkGroupEvent();
			requestEvent.setWorkGroupId(workgroupId);
			
			CompositeResponse compositeResponse = postRequestEvent(requestEvent);
			Collection workgroupList = MessageUtil.compositeToCollection(compositeResponse, WorkGroupResponseEvent.class);

			if (workgroupList != null)
			{
			    Iterator iter = workgroupList.iterator();
			    if(iter.hasNext()){
				    WorkGroupResponseEvent wgre = (WorkGroupResponseEvent)iter.next();
					UIWorkgroupHelper.populateFormFromResponseEvent(wgre, wgForm);
			    }
			}
	}	
}
