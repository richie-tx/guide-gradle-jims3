package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import ui.common.CodeHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.juvenilecase.prodsupport.helpers.Constants;
import ui.security.SecurityUIHelper;


/**
 * @author Jims2
 * 
 */

public class UpdateSchoolHistQueryAction extends Action {

	private Logger log = Logger.getLogger("UpdateSchoolHistQueryAction");
	
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

		/**
		 * Populate Drop-downs
		 */
		// Put in a code table helper... or find one that already exists!!!
		List gradeCodes = CodeHelper.getCodes("GRADE_LEVEL");
		List exittypeCodes = CodeHelper.getCodes("JUVENILE_SCHOOL_EXIT_TYPE");
		List appropGradeCodes = CodeHelper.getCodes("APPROPRIATE_GRADE_LEVEL");
		List gradesRepeatCodes = CodeHelper.getCodes("GRADE_LEVEL");

		// set dropdown collections on the form bean
		if (gradeCodes!=null && exittypeCodes!=null && appropGradeCodes!=null 
				&&gradesRepeatCodes!=null){			
			regform.setCurrentGradeCodes(new ArrayList(gradeCodes));
			regform.setExitTypeCodes(new ArrayList(exittypeCodes));
			regform.setAppropGradeCodes(new ArrayList(appropGradeCodes));
			regform.setGradesRepeatCodes(new ArrayList(gradesRepeatCodes));
		}
		else{
			log.severe("Error - Couldn't retrieve Codetables. UpdateSchoolHistQueryAction");
			regform.setMsg("Error - Couldn't retrieve Codetables. UpdateSchoolHistQueryAction");
			return mapping.findForward("error");
		}
		
		
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
			regform.setJuvenileId(resp.getJuvenileNum());
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
