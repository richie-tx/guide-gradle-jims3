package ui.juvenilecase.prodsupport.action.update;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.productionsupport.MoveCommonAppReportEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;

public class PerformMoveCommonAppReportAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    ProdSupportForm regform = (ProdSupportForm) form;
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    MoveCommonAppReportEvent moveEvent = (MoveCommonAppReportEvent)
			EventFactory.getInstance(ProductionSupportControllerServiceNames.MOVECOMMONAPPREPORT);
	    moveEvent.setOldCasefileId(regform.getCasefileId());
	    moveEvent.setCasefileId( regform.getNewcasefileId());
	    moveEvent.setCommonAppDocumentId(regform.getCommonAppDocument().getReportId());
	    dispatch.postEvent(moveEvent);
	    regform.clearAllResults();
	    regform.setMsg("Common App Report successfully moved.");
	    return mapping.findForward("success");
	}

}
