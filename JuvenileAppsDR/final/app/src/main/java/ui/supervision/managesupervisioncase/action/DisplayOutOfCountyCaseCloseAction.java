package ui.supervision.managesupervisioncase.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.utilities.DateUtil;
import naming.CloseCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.managesupervisioncase.form.OutOfCountyCaseForm;

public class DisplayOutOfCountyCaseCloseAction extends JIMSBaseAction
{
	
	@Override
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.paperFileReceived", "paperFileReceived");
		keyMap.put("button.next", "next");
	}
	
	
	public ActionForward paperFileReceived(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		return aMapping.findForward(CloseCaseConstants.CLOSE_CASE_SUCCESS);
	}
	
	
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		String forward = CloseCaseConstants.CLOSE_CASE_SUMMARY_SUCCESS;
		
		OutOfCountyCaseForm oocCaseForm = (OutOfCountyCaseForm)aForm;
		Date trananferOutDate = DateUtil.stringToDate(oocCaseForm.getTransferOutDateStr(), DateUtil.DATE_FMT_1);
		
		int result = DateUtil.compare(trananferOutDate, new Date(), DateUtil.DATE_FMT_1);
		if(result > 0)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Transfer Out Date cannot be future dated");
			forward = UIConstants.FAILURE;
		}
		return aMapping.findForward(forward);
	}
	
	
}
