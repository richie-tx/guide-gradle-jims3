package ui.juvenilecase.prodsupport.action.update;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.juvenilecase.prodsupport.helpers.Constants;
import ui.security.SecurityUIHelper;

public class ModifyBeginDateAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		String casefileId = regform.getCasefileId();
		
		if (casefileId==null || casefileId.equals("")){
			regform.setMsg("CasefileID was null.");
			return (mapping.findForward("error"));
		}
		

		/** First determine if the casefile alone is enough for a unique query **/
		
		
		/** Run the update **/
		String update = "update jims2.csjuvprogref set begindate='2010-04-16-01.01.01.000000' where casefile_id="
			+ casefileId + ";";

//		boolean success = runUpdate(update);
		
		Constants.writeToLog(update, SecurityUIHelper.getLogonId());
		
		/**Here is where the delete gets performed. For now it's commented out.**/
		
//		if (runStatement(deleteStatement)){
		
		/** Log for auditing purposes **/
		Constants.writeToLog(" MODIFY BEGINDATE for casefileID="+regform.getCasefileId(), SecurityUIHelper.getLogonId());
		
		regform.setMsg("");
		return mapping.findForward("success");
	//	}
		
	//	regform.setMsg("Error - The casefile was not deleted. PerformDeleteAction.java (94)");
	//	return mapping.findForward("error");
		
		
	}
	
	
}
