package ui.supervision.managetasks.reassigncases.assignsuperviseetoprogramunit.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.transferobjects.OrganizationTO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

public class SelectWorkgroupAction extends JIMSBaseAction
{
    /**
     * @roseuid 464368F103D5
     */
    public SelectWorkgroupAction()
    {
    }

    protected void addButtonMapping(Map keyMap)
    {
		keyMap.put("assign.supervisee.setup", "displayWorkgroups");
    	keyMap.put("button.assignsuperviseetoprogramunit.selectworkgroup.workgroupSelection", "selectWorkgroup") ;
    }				
    
    public ActionForward displayWorkgroups(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		String programUnitId = caseAssignmentForm.getProgramUnitId();

		AssignSuperviseeService assignmentService = AssignSuperviseeService.getInstance();
		OrganizationTO organization = assignmentService.getProgramUnitDetails(programUnitId);

		String programUnitName = organization.getDescription();
		caseAssignmentForm.setProgramUnitId(programUnitId);
		caseAssignmentForm.setProgramUnitName(programUnitName);
		
		/*Date programUnitAllocationDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String programUnitAllocationDateAsString = dateFormat.format(programUnitAllocationDate);
		caseAssignmentForm.setProgramUnitAllocationDate(programUnitAllocationDateAsString);*/

		List workgroups = assignmentService.getWorkgroupsForAgency(SecurityUIHelper.getUserAgencyId(), "IN");
		caseAssignmentForm.setWorkGroupsList(workgroups);
		return aMapping.findForward("displaySelectWorkgroup");
    }

    public ActionForward selectWorkgroup(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
      return aMapping.findForward("addCaseNote");
    }
}
