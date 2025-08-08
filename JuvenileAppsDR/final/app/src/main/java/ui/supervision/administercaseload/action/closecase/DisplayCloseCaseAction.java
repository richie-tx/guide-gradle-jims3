package ui.supervision.administercaseload.action.closecase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;

public class DisplayCloseCaseAction extends JIMSBaseAction
{

	@Override
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.paperFileReceived", "paperFileReceived");
		keyMap.put("button.submit", "submit");
	}
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward paperFileReceived(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm)aForm;
		caseAssignmentForm.setCloseCasesList(caseAssignmentForm.getActiveCases());
		
		return aMapping.findForward(UIConstants.CLOSE_SUCCESS);
	}
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)  
	{
		String forward = UIConstants.SUBMIT;
		
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm)aForm;
		return aMapping.findForward(forward);
	}
}

