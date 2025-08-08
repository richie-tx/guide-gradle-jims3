/*
 * Created on Apr 13, 2007
 */
package ui.supervision.managetasks.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.managetasks.form.TasksSearchForm;
import ui.supervision.manageworkgroup.form.WorkgroupSearchForm;
import ui.supervision.adminstaff.form.AdminStaffSearchForm;

/**
 * @author hrodriguez
 */
public class HandleTasksTransferSelectionAction extends JIMSBaseAction
{

    protected void addButtonMapping(Map keyMap)
    {
        keyMap.put("button.next", "next");
        keyMap.put("button.back", "back");
        keyMap.put("button.backToTasks", "back");
        keyMap.put("button.cancel", "cancel");
    }


    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }
    
    public ActionForward backToTasks(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward("backToTasks");
    }

    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {

        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        
        String transferTypeId = tsForm.getTransferToTypeId();
        if(transferTypeId != null && transferTypeId.equalsIgnoreCase("workgroup")){
        	WorkgroupSearchForm wsForm = (WorkgroupSearchForm) getSessionForm(aMapping,aRequest,"workgroupSearchForm",true); 
            wsForm.setWorkgroupName("");
            wsForm.setWorkgroupTypeId("");
            return aMapping.findForward(UIConstants.TRANSFER_WORKGROUP);
            
        }else{
        	AdminStaffSearchForm asForm = (AdminStaffSearchForm) getSessionForm(aMapping,aRequest,"adminStaffSearchForm",true);  
        	asForm.clearPosSearch();
        	return aMapping.findForward(UIConstants.TRANSFER_POSITION);
        }
        
    }
}