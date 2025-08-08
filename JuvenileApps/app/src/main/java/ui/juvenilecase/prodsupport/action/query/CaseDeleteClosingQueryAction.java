package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.reply.CasefileClosingResponseEvent;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;


/**
 * @author rcarter
**/

public class CaseDeleteClosingQueryAction extends Action {

	private Logger log = Logger.getLogger("CaseDeleteClosingQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		
		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk!=null && clrChk.equalsIgnoreCase("Y"))
		{
			regform.clearAllResults();
			regform.setMsg("");
			return mapping.findForward("error");		
		}
	
		
		String casefileId = regform.getCasefileId();

		if (casefileId == null || casefileId.equals(" ")) {
			regform.setMsg("CasefileId was null.");
			return mapping.findForward("error");
		}
		
		//Clear the form		
		regform.clearAllResults();
		
		//Log the query attempt
		log.info("Casefile Closing Query ID: " + casefileId + " LogonId: " +  SecurityUIHelper.getLogonId());
		
		// Get and set Associated Casefile Closings
		CasefileClosingResponseEvent respEvent = UIJuvenileCasefileClosingHelper.
			getCasefileClosingDetails(casefileId);
		ArrayList assignmentHistoryEventsList = new ArrayList();		
		if (respEvent != null) {
			assignmentHistoryEventsList.add(respEvent);
			regform.setCasefileClosingCount(assignmentHistoryEventsList.size());
			regform.setCasefileclosings(assignmentHistoryEventsList);
		}
		
		if (regform.getCasefileClosingCount() == 0)
		{
			regform.setMsg("No casefile closing records returned for casefileID " + casefileId);
			return mapping.findForward("error");
		}
			
		regform.setMsg("");
		return mapping.findForward("success");

	}

}
