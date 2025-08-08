package ui.supervision.administersupervisee.action.transfercase;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.transfers.reply.TransferResponseEvent;
import mojo.km.utilities.CollectionUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.SuperviseeTransferCasesInfo;
import ui.supervision.administercaseload.helper.TransferCasesService;
import ui.supervision.supervisee.form.SuperviseeForm;

public class UpdateTransferCaseHistoryConfirmationAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("reassign.supervisee.search.setup", "setUp");
		keyMap.put("button.finish", "updateTransferHistoryComplete");
	}

	public ActionForward setUp(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward("initialSetup");
	}

	public ActionForward updateTransferHistoryComplete(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		SuperviseeTransferCasesInfo transferInfo = superviseeForm.getTransferCasesInfo();
		Collection harrisCountyCases = superviseeForm.getHarrisCountyCases();
		
		TransferCasesService service = TransferCasesService.getInstance();
		service.updateCaseHistory(transferInfo, harrisCountyCases, superviseeForm.getPrevTransferCasesInfo());
		List aList = CollectionUtil.iteratorToList(harrisCountyCases.iterator());
		TransferResponseEvent tre = (TransferResponseEvent) aList.get(0);

		superviseeForm.setTransferCasesInfo(transferInfo);
		superviseeForm.setConfirmMessage("Case Transfer Successfully Updated");
		return aMapping.findForward("updateComplete");
	}
}
