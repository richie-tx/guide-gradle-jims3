
package ui.supervision.administercaseload.action.closecase;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.CloseCaseEvent;
import messaging.administercaseload.CloseCaseResponseEvent;
import naming.CaseloadConstants;
import naming.CloseCaseConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.AssignReassignSuperviseeHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercaseload.helper.AdminCloseCaseHelper;
import ui.supervision.managetasks.helper.AssignSuperviseeService;

public class SubmitCloseCaseAction extends JIMSBaseAction
{

	@Override
	protected void addButtonMapping(Map keyMap) 
	{
		keyMap.put("button.finish", "finish");
		keyMap.put("button.caseloadSearch", "caseloadSearch");
	}
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		String forward = UIConstants.CONFIRM_SUCCESS;
		
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm)aForm;
		
//		set the paperfile acknowledgment fields
		AdminCloseCaseHelper.setPaperfileAcknowledgement(caseAssignmentForm);
		
		CloseCaseEvent reqEvent = AdminCloseCaseHelper.getCloseCaseEvent(caseAssignmentForm);
		List closeCaseRespEventList = this.postRequestListFilter(reqEvent, CloseCaseResponseEvent.class);
		
		if((closeCaseRespEventList != null) && (closeCaseRespEventList.size() > 0))
		{
			AdminCloseCaseHelper.convertRespEvtToCaseCloseBean(caseAssignmentForm, closeCaseRespEventList);
		}
		else
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to close the case(s)");
			forward = UIConstants.FAILURE;
		}
		
		AssignReassignSuperviseeHelper reAssignSuperviseeService = AssignReassignSuperviseeHelper.getInstance();
		reAssignSuperviseeService.recalculateWorkloadCredit( caseAssignmentForm );
		if( StringUtils.isNotEmpty( caseAssignmentForm.getOfficerPositionId() ) ) {
			String superviseeUpdateType = CaseloadConstants.SUPERVISEE_ASSIGNED_TO_STAFF;
			AssignSuperviseeService assignSuperviseeService = AssignSuperviseeService.getInstance();
			assignSuperviseeService.updateSuperviseeDetails( caseAssignmentForm, superviseeUpdateType );
		}
		reAssignSuperviseeService = null;
		return aMapping.findForward(forward);
	}//end of finish()

	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward caseloadSearch(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		return aMapping.findForward(CloseCaseConstants.CASELOAD_SEARCH);
	}
}
