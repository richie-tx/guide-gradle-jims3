package ui.supervision.administersupervisee.action.transfercase;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.SuperviseeTransferCasesInfo;
import ui.supervision.administercaseload.helper.TransferCasesService;
import ui.supervision.supervisee.form.SuperviseeForm;

public class ViewTransferCaseHistoryAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.search.setup", "setUp");
		keyMap.put("button.update", "updateTransferHistory");
	}

	public ActionForward setUp(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		
		SuperviseeTransferCasesInfo transferCaseInfo = superviseeForm.getTransferCasesInfo();
		if ((transferCaseInfo.getTransferStateId() == null || transferCaseInfo.getTransferStateId().equals("")) & 
			(transferCaseInfo.getTransferTxCountyId() == null || transferCaseInfo.getTransferTxCountyId().equals("")) ){
			superviseeForm.setConfirmMessage("");
		}
		transferCaseInfo.clear();
		String superviseeId = superviseeForm.getSuperviseeId(); 

		TransferCasesService service = TransferCasesService.getInstance();
		List harrisCountyCases = service.getHarrisCountyTransferCases(superviseeId);			
		List courtesyCases = service.getOutOfCountyTransferCases(superviseeId);
		
		transferCaseInfo.setHarrisCountyCases(harrisCountyCases);
		transferCaseInfo.setCourtesyCases(courtesyCases);
		superviseeForm.setHarrisCountyCases(harrisCountyCases);
		superviseeForm.setCourtesyCases(courtesyCases);
		
		return aMapping.findForward("initialSetup");
	}

	public ActionForward updateTransferHistory(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		return aMapping.findForward("updateTransferCaseHistory");
	}

}
