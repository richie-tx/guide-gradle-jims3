package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.reply.CasefileClosingResponseEvent;
import mojo.km.util.DateUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;



/**
 * @author rcarter
 */

public class UpdateClosingQueryAction extends Action {

	private Logger log = Logger.getLogger("UpdateClosingQueryAction");
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
		
		//Log the query attempt
		log.info("Casefile Closing Query ID: " + casefileId + " LogonId: " + SecurityUIHelper.getLogonId());
		
		CasefileClosingResponseEvent respEvent = UIJuvenileCasefileClosingHelper.getCasefileClosingDetails(casefileId);
		if (respEvent==null)
		{
			regform.setMsg("No casefile closing records returned for casefileID "+casefileId);
			regform.setCasefileClosing(null);
			return mapping.findForward("error");
		}
		
		regform.setCasefileClosing(respEvent);
		regform.setClosingEndDate(DateUtil.dateToString(respEvent.getSupervisionEndDate(), DateUtil.DATE_FMT_1));
		List outcomeCodes = CodeHelper.getSupervisionOutcomeCodes();
		List outcomeDescCodes = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_OUTCOME_DESC");
		regform.setOutcomeDescCodes(new ArrayList(outcomeDescCodes));
		regform.setOutcomeCodes(new ArrayList(outcomeCodes));
		regform.setMsg("");
		return mapping.findForward("success");	
	}
}
