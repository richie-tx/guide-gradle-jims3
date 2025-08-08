package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.GetAssignmentsByCasefileIdEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;

/**
 * @author rcarter
 */
public class AssignDeleteQueryAction extends Action {

	private Logger log = Logger.getLogger("AssignDeleteQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;

		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
			regform.clearAllResults();
			regform.setMsg("");
			return mapping.findForward("error");
		}

		String casefileId = regform.getCasefileId();
		String assignmentId = regform.getAssignmentId();

		if ((assignmentId == null || assignmentId.equals(" "))
				&& (casefileId == null || casefileId.equals(" "))) {
			regform.setMsg("Must have valid Casefile and/or Assignment ID.");
			return mapping.findForward("error");
		}

		/** Check for initial load of this page **/
		ArrayList assignments = null;
		// Clear the form
		regform.clearAllResults();			
		regform = retrieveAssignmentRecords(regform, casefileId);
		assignments = regform.getAssignments();
		if (assignments == null){
			regform.setMsg("No assignment records returned for casefileId "+ casefileId);
			log.info("No assignment records returned for casefileId "+ casefileId);
			return mapping.findForward("error");
		}else{
			return mapping.findForward("success");				
		}
	}
	
	/**
	 * retrieve assignment records by casefileId
	 * @param regform
	 * @param casefileId
	 * @return
	 */
	public static ProdSupportForm retrieveAssignmentRecords(ProdSupportForm regform, String casefileId)
	{	
		GetAssignmentsByCasefileIdEvent getAssignments = (GetAssignmentsByCasefileIdEvent)
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETASSIGNMENTSBYCASEFILEID);
		getAssignments.setCasefileId(casefileId);
		CompositeResponse assignmentResponse = MessageUtil.postRequest(getAssignments);
		ArrayList<AssignmentResponseEvent> assignmentList = (ArrayList) MessageUtil.compositeToCollection(assignmentResponse,
			AssignmentResponseEvent.class);	
		Collections.sort(assignmentList);
		if (assignmentList != null){
			regform.setAssignmentCount(assignmentList.size());
			regform.setAssignments(assignmentList);
		}
	
		return regform;
	}

}
