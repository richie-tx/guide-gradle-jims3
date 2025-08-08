//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\action\\DisplayCaseHistoryListAction.java

package ui.supervision.posttrial.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.task.GetNextTaskActionEvent;
import messaging.task.reply.TaskNextActionResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.adminstaff.form.AdminStaffSearchForm;
import ui.supervision.posttrial.form.CSCDTaskForm;

/*
 * 
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class DisplayWorkGroupPositionAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplayWorkGroupPositionAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
        CSCDTaskForm ctForm = (CSCDTaskForm) aForm;
        String forwardStr = UIConstants.WORKGROUP_SUCCESS;
        if (ctForm.getSearchById().equalsIgnoreCase("POSITION")){
        	forwardStr = UIConstants.POSITION_SUCCESS;
    		AdminStaffSearchForm asForm=(AdminStaffSearchForm)aForm;		
    		asForm.setUserId("");
    		asForm.getName().setLastName("");
    		asForm.getName().setFirstName("");
    		asForm.getName().setMiddleName("");
    		asForm.setCjad("");
    		asForm.setPositionName("");
    		asForm.setDivisionId("");
    		asForm.setProgramUnitId("");
    		asForm.setProgramSectionId("");
    		asForm.setWorkgroupName("");
        } else { 
        	ctForm.setWorkgroupName("");
        	ctForm.setWorkgroupTypeId("");
        }
        ctForm.setTaskNextActionGroups(new ArrayList());
        ctForm.setTaskNextActions(new ArrayList());
        GetNextTaskActionEvent reqEvent = new GetNextTaskActionEvent();
        Collection col = MessageUtil.postRequestListFilter(reqEvent, TaskNextActionResponseEvent.class);
        if(col != null && !col.isEmpty()){
        	ctForm.setTaskNextActionGroups(col);
        }

        //ctForm.setTaskSeverityLevels(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.TASK_SEVERITY_LEVEL));
        ctForm.setTaskSeverityLevels(CodeHelper.getSupervisionCodes(SecurityUIHelper.getUserAgencyId() , PDCodeTableConstants.SEVERITY_LEVEL));
		return aMapping.findForward(forwardStr);
	}
}
