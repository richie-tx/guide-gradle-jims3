//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\manageworkgroup\\action\\DisplayWorkgroupSearchResultsAction.java

package ui.supervision.posttrial.action;


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
import ui.security.SecurityUIHelper;
import ui.supervision.manageworkgroup.form.WorkgroupForm;
import ui.supervision.manageworkgroup.form.WorkgroupSearchForm;
import ui.supervision.posttrial.form.CSCDTaskForm;


/**
 * @author hrodriguez
 */
public class DisplayWorkGroupSearchResultsAction extends JIMSBaseAction {

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
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		CSCDTaskForm cForm = (CSCDTaskForm) aForm;
		
		GetWorkGroupsEvent requestEvent = (GetWorkGroupsEvent) EventFactory.getInstance(WorkGroupControllerServiceNames.GETWORKGROUPS);
		requestEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
		requestEvent.setName(cForm.getWorkgroupName());
		requestEvent.setType(cForm.getWorkgroupTypeId());

		CompositeResponse compositeResponse = postRequestEvent(requestEvent);
		Collection workgroupList = MessageUtil.compositeToCollection(compositeResponse, WorkGroupResponseEvent.class);
		//cForm.clearWorkgroupList();
		if (workgroupList != null && workgroupList.size() > 0)
		{
		    for(Iterator iter = workgroupList.iterator(); iter.hasNext(); ){
		        WorkGroupResponseEvent wgre = (WorkGroupResponseEvent)iter.next();
			    String typeDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.WORKGROUP_TYPE, wgre.getWorkgroupTypeId()); 
		        wgre.setWorkgroupTypeDesc(typeDesc);
		    }
			cForm.setWorkgroupList(workgroupList);
			forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
		}
		else
		{
			this.sendToErrorPage(aRequest, "error.noRecords");
			forward = aMapping.findForward(UIConstants.FAILURE);
		}
		
		return forward;
	}

	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CSCDTaskForm cForm = (CSCDTaskForm) aForm;
		cForm.setWorkgroupName("");
		cForm.setWorkgroupTypeId("");
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
