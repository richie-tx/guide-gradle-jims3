//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\manageworkgroup\\action\\DisplayWorkgroupSearchResultsAction.java

package ui.supervision.manageworkgroup.action;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.manageworkgroup.GetWorkGroupsEvent;
import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import naming.WorkGroupControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.supervision.managetasks.helper.UIManagetasksHelper;
import ui.supervision.manageworkgroup.form.WorkgroupForm;
import ui.supervision.manageworkgroup.form.WorkgroupSearchForm;


/**
 * @author hrodriguez
 */
public class DisplayWorkgroupSearchResultsAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.createWorkgroup", "createWorkgroup");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45DB5B250219
	 */
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm, 
									HttpServletRequest aRequest,HttpServletResponse aResponse) {

		//long startTime = System.currentTimeMillis();
		ActionForward forward = null;
		String defaultWorkgroupTypeDescription = null;
		
		// Fetch all the work groups that match the search parameters
		WorkgroupSearchForm form = (WorkgroupSearchForm) aForm;
		GetWorkGroupsEvent requestEvent = (GetWorkGroupsEvent) EventFactory
												.getInstance(WorkGroupControllerServiceNames.GETWORKGROUPS);
		
		requestEvent.setAgencyId(form.getAgencyId());
		requestEvent.setName(form.getWorkgroupName());
		requestEvent.setType(form.getWorkgroupTypeId());

		CompositeResponse compositeResponse = postRequestEvent(requestEvent);
		Collection<WorkGroupResponseEvent> workgroupList = MessageUtil.compositeToCollection(
															compositeResponse, WorkGroupResponseEvent.class);
		Collection<WorkGroupResponseEvent> modifiedWorkGroupList = new ArrayList<WorkGroupResponseEvent>((workgroupList!=null ? workgroupList.size():0));
		
		// Fetch the Workgroup description once if the user selected a workgroup type as a part of the
		// search query
		
		if(form.getWorkgroupTypeId() != null && form.getWorkgroupTypeId().length() > 0){
			defaultWorkgroupTypeDescription = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.WORKGROUP_TYPE, form.getWorkgroupTypeId());
		} 
		
		if (workgroupList.size() > 0) {
		    for(WorkGroupResponseEvent response : workgroupList){
		    	if(defaultWorkgroupTypeDescription == null){
		    		response.setWorkgroupTypeDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.WORKGROUP_TYPE, response.getWorkgroupTypeId()));
		    	} else {
		    		response.setWorkgroupTypeDesc(defaultWorkgroupTypeDescription);
		    	}
		    	modifiedWorkGroupList.add(response);
		    }
			form.setWorkgroupList(modifiedWorkGroupList);
			forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
			
		} else {
			
			this.sendToErrorPage(aRequest, "error.noRecords");
			forward = aMapping.findForward(UIConstants.FAILURE);
		}
		
		//System.out.println("Elapsed Time= " + (System.currentTimeMillis()-startTime)/1000F);
		return forward;
	}
	
	
	

	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		WorkgroupSearchForm wgSearchForm = (WorkgroupSearchForm) aForm;
		wgSearchForm.clear();
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}
	
	public ActionForward createWorkgroup(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		
		WorkgroupForm wgForm = (WorkgroupForm) aForm;
		wgForm.clearAll();
		wgForm.setAction(UIConstants.CREATE);
				
		forward = aMapping.findForward(UIConstants.CREATE_SUCCESS);
		return forward;
	}
	
}
