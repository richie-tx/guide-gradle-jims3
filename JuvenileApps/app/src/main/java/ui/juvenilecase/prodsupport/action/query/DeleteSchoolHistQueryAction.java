package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetJuvenileSchoolByIDEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.juvenilecase.prodsupport.helpers.Constants;
import ui.security.SecurityUIHelper;



/**
 * @author Jims2
 * 
 * The DELETE process is divided into two actions, DeleteQueryAction and PerformDeleteAction.  
 * 
 * DeleteQueryAction gathers information on what data will be affected by the delete, and 
 * displays this information to the user on the deleteQueryResult.jsp form.
 * 
 * PeformDeleteAction actually executes the delete statement after getting confirmation 
 * from the user, and displays a summary on the deleteSummary.jsp form.
 * 
 */

public class DeleteSchoolHistQueryAction extends Action {

	private Logger log = Logger.getLogger("DeleteSchoolHistQueryAction");
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
				
		String schoolHistId = regform.getSchoolhistId();

		if (schoolHistId == null || schoolHistId.equals(" ")) {
			regform.setMsg("SchoolHistID was null.");
			return mapping.findForward("error");
		}
		
		//Clear the form
		regform.clearAllResults();
		
		//Log the query attempt
		log.info("["+new Date()+"] ProdSupport ("+SecurityUIHelper.getLogonId().toUpperCase()+") - SchoolHist Query ID: "+schoolHistId);		
		
		//Search and populate the casefile records
		GetJuvenileSchoolByIDEvent getSchoolHistory = new GetJuvenileSchoolByIDEvent();
		getSchoolHistory.setSchoolId(schoolHistId);
		JuvenileSchoolHistoryResponseEvent resp = (JuvenileSchoolHistoryResponseEvent) MessageUtil.postRequest(getSchoolHistory, JuvenileSchoolHistoryResponseEvent.class);
		ArrayList<JuvenileSchoolHistoryResponseEvent> schoolHists = new ArrayList<JuvenileSchoolHistoryResponseEvent>();
		if (resp!=null && resp.getSchoolId()!= null && resp.getSchoolId().length() > 0)
		{
			schoolHists.add(resp);
			regform.setSchoolhists(schoolHists);
			regform.setSchoolhistCount(schoolHists.size());
		}
		else
		{
			regform.setMsg("No records returned for schoolhistID "+schoolHistId);
			return mapping.findForward("error");
		}
			
		regform.setMsg("");
		return mapping.findForward("success");

	}	
}
