package ui.juvenilecase.prodsupport.action.update;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.productionsupport.DeleteProductionSupportCasefileClosingEvent;
import messaging.productionsupport.reply.ProductionSupportRiskAnalysisResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 * 
 */

public class PerformCaseDeleteClosingAction extends Action {

	private Logger log = Logger.getLogger("PerformCaseDeleteClosingAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		

		String casefileId = regform.getCasefileId();

		if (casefileId == null || casefileId.equals("")) {
			regform.setMsg("PerformCaseDeleteClosingAction.java  - CasefileID was null.");
			return (mapping.findForward("error"));
		}

		/** delete casefile closing record 	**/
		DeleteProductionSupportCasefileClosingEvent deleteCasefileClosingEvent = 
			(DeleteProductionSupportCasefileClosingEvent) 
		EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTCASEFILECLOSING) ;
	
		deleteCasefileClosingEvent.setCasefileId(casefileId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		log.info("DELETE CASEFILE CLOSING RECORD: " + SecurityUIHelper.getLogonId());
		dispatch.postEvent(deleteCasefileClosingEvent);	
		// check if the deletion was successful
		CasefileClosingResponseEvent respEvent = UIJuvenileCasefileClosingHelper.
				getCasefileClosingDetails(casefileId);
		
		if(respEvent != null ){
			log.info("There was an error during CASECLOSING DELETE for casefileClosingInfoId="
					+ respEvent.getCasefileClosingInfoId() +  " and Casefile: " + casefileId + " for LogonId: " + SecurityUIHelper.getLogonId());
			regform.setMsg("Error - The Risk Analysis and Risk Responses could not be deleted.");
			return mapping.findForward("error");
		}		
		
		log.info("CASEFILE CLOSING DELETED FOR CASEFILE: " + casefileId + " LOGONID: " + SecurityUIHelper.getLogonId());

		/** Log for auditing purposes **/
		log.info("Performed a CASEFILE CLOSING DELETE for casefileID= " +
				regform.getCasefileId() + " LOGONID: " + SecurityUIHelper.getLogonId());

		regform.setMsg("");
		return mapping.findForward("success");
	}

}
