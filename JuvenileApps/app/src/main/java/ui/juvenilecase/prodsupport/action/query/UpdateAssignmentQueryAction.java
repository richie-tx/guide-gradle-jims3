package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.GetAssignmentsByCasefileIdEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.productionsupport.GetProductionSupportAssignmentEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 */
public class UpdateAssignmentQueryAction extends Action {

	private Logger log = Logger.getLogger("UpdateAssignmentQueryAction");
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
		String editFlag = request.getParameter("edit");
		
		// retrieve the single assignment or the available assignments, depending on which page request is from
		if(editFlag != null && editFlag.equalsIgnoreCase("Y")){
			// Clear the form
			regform.clearAllResults();
			regform.setReferralAssignmentCodes(ComplexCodeTableHelper.getRefAssmntTypeCodes());
			ArrayList assignments = retrieveSingleAssignment(regform,
					assignmentId, SecurityUIHelper.getLogonId());
			if (assignments == null){
				return mapping.findForward("error");
			}else{
				return mapping.findForward("continue");
			}
		}else{
			// Clear the form
			regform.clearAllResults();
			ArrayList assignments = populateAssignmentList(regform, casefileId,
					SecurityUIHelper.getLogonId());
			if (assignments == null || assignments.size() == 0)
				return mapping.findForward("error");
			else
				return mapping.findForward("success");
		}
	}

	/**
	 * get list of assignments based on casefileId
	 * @param regform
	 * @param casefileId
	 * @param logonid
	 * @return
	 */
	private ArrayList populateAssignmentList(ProdSupportForm regform,
			String casefileId, String logonid) {

		// Log the query attempt
		log.info("[" + new Date() + "] ProdSupport ("
				+ logonid.toUpperCase() + ") - Assignment Query Casefile ID: "	+ casefileId);
		// Search and populate the casefile records
		regform = retrieveAssignmentRecords(regform,casefileId);

		if (regform.getAssignments() == null
				|| regform.getAssignmentCount() == 0) {
			regform.setMsg("No assignment records returned for casefileId "	+ casefileId);
			log.info("Error: No assignment records returned for casefileId "	+ casefileId);
			return null;
		}
		return regform.getAssignments();
	}

	/**
	 * retrieve a unique assignment record based on assignmentId
	 * @param regform
	 * @param assignmentId
	 * @param logonid
	 * @return
	 */
	private ArrayList retrieveSingleAssignment(ProdSupportForm regform,
			String assignmentId, String logonid) {
		// Log the query attempt
		log.info("[" + new Date() + "] ProdSupport Retrieve Assignment ("
				+ logonid.toUpperCase() + ") - Assignment Query ID: "
				+ assignmentId);
		regform = retrieveAssignmentRecord(regform,assignmentId);

		if (regform.getAssignments() == null
				|| regform.getAssignmentCount() == 0) {
			regform.setMsg("No record returned for assignmentId "
					+ assignmentId);
			return null;
		}
		return regform.getAssignments();
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
	
	/**
	 * retrieve assignment record by assignmentId
	 * @param regform
	 * @param assignmentId
	 * @return
	 */
	public static ProdSupportForm retrieveAssignmentRecord(ProdSupportForm regform, String assignmentId)
	{	
		GetProductionSupportAssignmentEvent getAssignment = (GetProductionSupportAssignmentEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTASSIGNMENT);
		getAssignment.setAssignmentId(assignmentId);
		CompositeResponse assignmentResponse = MessageUtil.postRequest(getAssignment);
		ArrayList<AssignmentResponseEvent> assignmentList = (ArrayList) MessageUtil.compositeToCollection(assignmentResponse,
			AssignmentResponseEvent.class);	
		if (assignmentList != null){
			regform.setAssignmentCount(assignmentList.size());
			regform.setAssignments(assignmentList);
		}
	
		return regform;
	}
}
