package ui.supervision.managesupervisioncase.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.CloseCaseResponseEvent;
import messaging.managesupervisioncase.CloseOutOfCountyCaseEvent;
import naming.CloseCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.managesupervisioncase.form.OutOfCountyCaseForm;
import ui.supervision.managesupervisioncase.helper.OutOfCountyCaseUIHelper;

public class SubmitOutOfCountyCaseCloseAction extends JIMSBaseAction
{

	@Override
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "finish");
		keyMap.put("button.newSearch", "newSearch");
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
		String forward = UIConstants.FINISH;
		
		OutOfCountyCaseForm oocCaseForm = (OutOfCountyCaseForm)aForm;
		
		CloseOutOfCountyCaseEvent closeOocCaseEvent = OutOfCountyCaseUIHelper.getCloseOutOfCountyCaseEvent(oocCaseForm);
		
		List closeCaseRespEventList = this.postRequestListFilter(closeOocCaseEvent, CloseCaseResponseEvent.class);
		
		if(oocCaseForm.getAction().equalsIgnoreCase(CloseCaseConstants.OOC_CASE_CLOSE))
		{
			if(closeCaseRespEventList != null && closeCaseRespEventList.size() > 0)
			{
				OutOfCountyCaseUIHelper.convertRespEvtToCaseCloseBean(oocCaseForm, closeCaseRespEventList);
			}
			else
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to close the case");
				return aMapping.findForward(UIConstants.FAILURE);
			}
			
			if(oocCaseForm.isCloseCaseSuccess())
			{
				aRequest.setAttribute("confirmMessage", "Case successfully closed.");
			}
		}
//		In case of Update OOC Case Closure
		else
		{
			oocCaseForm.setCloseCaseSuccess(true);
			aRequest.setAttribute("confirmMessage", "Case closure successfully updated.");
		}
		
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
	 public ActionForward newSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse)
	 {
        return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
	 }
	 
}
