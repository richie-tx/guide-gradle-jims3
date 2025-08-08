//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\managetasks\\action\\DisplayWorkgroupSearchResultsAction.java

package ui.supervision.managetasks.action;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.managetasks.form.TasksSearchForm;
import ui.supervision.manageworkgroup.form.WorkgroupSearchForm;


/**
 * @author ryoung
 */
public class DisplayTransferWorkgroupSearchResultsAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.submit", "submit");
		keyMap.put("button.next", "next");
		keyMap.put("button.cancel", "cancel");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45DB5B250219
	 */
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		WorkgroupSearchForm wgSearchForm = (WorkgroupSearchForm) aForm;
		
		GetWorkGroupsEvent requestEvent = (GetWorkGroupsEvent) EventFactory
		.getInstance(WorkGroupControllerServiceNames.GETWORKGROUPS);
		
		requestEvent.setAgencyId(wgSearchForm.getAgencyId());
		//Defect:JIMS200055214 Start
		requestEvent.setName(wgSearchForm.getWorkgroupName()+ "*");
		//Defect:JIMS200055214 End
		requestEvent.setType(wgSearchForm.getWorkgroupTypeId());

	
		CompositeResponse compositeResponse = postRequestEvent(requestEvent);
		Collection workgroupList = MessageUtil.compositeToCollection(compositeResponse, WorkGroupResponseEvent.class);
		
	
		// Clear the workgroup collection on the form    
		wgSearchForm.setWorkgroupList(new ArrayList());
	
		String forwardStr = UIConstants.SEARCH_FAILURE;
		if (workgroupList != null && workgroupList.size() > 0)
		{
		    for(Iterator iter = workgroupList.iterator(); iter.hasNext(); ){
		        WorkGroupResponseEvent wgre = (WorkGroupResponseEvent)iter.next();
			    String typeDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.WORKGROUP_TYPE, wgre.getWorkgroupTypeId()); 
		        wgre.setWorkgroupTypeDesc(typeDesc);
		    }
			wgSearchForm.setWorkgroupList(workgroupList);
			forwardStr = UIConstants.SEARCH_SUCCESS;
	        
		}
		else
		{
			this.sendToErrorPage(aRequest, "error.noWorkgroupsfound");
		}
		
		return aMapping.findForward(forwardStr);
	}

	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException{
	    
		WorkgroupSearchForm wgSearchForm = (WorkgroupSearchForm) aForm;
		Iterator wgIter =  wgSearchForm.getWorkgroupList().iterator();
		
		String wgId = wgSearchForm.getWorkgroupId();
			
		String workGroupName = "WorkGroup Name no available";
		while(wgIter.hasNext()){
		    
		    WorkGroupResponseEvent wgResponse = (WorkGroupResponseEvent)wgIter.next();
		    if(wgResponse != null){

     		    String WorkGroupId = wgResponse.getWorkgroupId();
			    if(WorkGroupId.equals(wgId)){
			        
			        workGroupName = wgResponse.getWorkgroupName();
			        break;
			    }
			}
		}
		
		TasksSearchForm  taskForm = (TasksSearchForm) this.getSessionForm(aMapping, aRequest, "tasksSearchForm" , true);
		taskForm.setTransferTo(workGroupName);
		taskForm.setTransferToId(wgId);
		
		return aMapping.findForward(UIConstants.NEXT);
	}
	
	/**
	 * 
	 */
	 public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, 
			 					 HttpServletResponse aResponse) {
	 
	       
	        return aMapping.findForward(UIConstants.CANCEL);
	 }
}