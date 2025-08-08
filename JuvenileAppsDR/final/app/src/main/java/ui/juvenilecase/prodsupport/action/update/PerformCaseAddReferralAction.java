package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.GetAssignmentsByCasefileIdEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.productionsupport.CreateProductionSupportAssignmentEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 * 
 *         This action performs the deletes necessary to remove a Juvenile
 *         Casefile and verifies that all records were deleted before returning
 *         the user to a summary screen.
 */

public class PerformCaseAddReferralAction extends Action {
	
	private Logger log = Logger.getLogger("PerformCaseAddReferralAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		String newReferralNum = regform.getNewReferralNum();

		if (newReferralNum == null || newReferralNum.equals("")) {
			regform.setMsg("PerformCaseAddReferralAction.java  - ReferralNumber was Null/Not provided.");
			return (mapping.findForward("error"));
		}

		String casefileId = regform.getCasefileId();

		if (casefileId == null || casefileId.equals("")) {
			regform.setMsg("PerformCaseAddReferralAction.java - CasefileID was Null/Not provided.");
			return (mapping.findForward("error"));
		}

		/** Check to see if referral already exists **/
		Iterator iter = regform.getAssignments().iterator();

		while (iter.hasNext()) {
			AssignmentResponseEvent currentAssignment = (AssignmentResponseEvent) iter.next();

			if (currentAssignment.getReferralNum().equals(newReferralNum)) {
				regform.setMsg("Referral Number " + newReferralNum
						+ " already exists for an Assignment with casefile: " + regform.getCasefileId());
				return (mapping.findForward("error"));
			}
		}

		/**
		 * Retrieve oldest JCASSIGNMENT record
		 */
		AssignmentResponseEvent oldReferralAssignment = retrieveOldestAssignmentReferral(casefileId);

		log.info("BEGIN INSERT New Assignment for CasefileId: " + casefileId + " and Referral Number: " + newReferralNum + " for LogonId: " + SecurityUIHelper.getLogonId());

		// create new assignment record
		insertNewAssignmentReferral(regform,oldReferralAssignment,newReferralNum);
		
		// check if the insert was successful
		boolean isNewAssignmentRecord = retrieveCreateAssignmentReferral(casefileId, newReferralNum);
		
		// if successful
		if(isNewAssignmentRecord){
			/** Log for auditing purposes **/
			log.info("END - Performed a CASE ADD REFERRAL for casefileID= " + casefileId + " and Referral Number: " + newReferralNum + " for LogonId: "  + SecurityUIHelper.getLogonId());

			/** Reload values for Summary page **/
			retrieveAssignmentRecords(regform, casefileId);
			regform.setMsg("");
			return mapping.findForward("success");
		}else{
		// if not successful
			log.info("END - Error Performing a CASE ADD REFERRAL for casefileID= " + casefileId  + " and Referral Number: " + newReferralNum + " for LogonId: "  + 
					SecurityUIHelper.getLogonId() + "The referral was not added. This may be due to a duplicate referral.");
			regform.setMsg("The referral was not added. This may be due to a duplicate referral.");
			return mapping.findForward("error");
			}		
	}

	/**
	 * search for assignments, and return the oldest one only
	 * @param casefileId
	 * @return
	 */
	private AssignmentResponseEvent retrieveOldestAssignmentReferral(String casefileId) {

		// retrieve assignments
		GetAssignmentsByCasefileIdEvent getAssignments = (GetAssignmentsByCasefileIdEvent)
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETASSIGNMENTSBYCASEFILEID);
		getAssignments.setCasefileId(casefileId);
		CompositeResponse assignmentResponse = MessageUtil.postRequest(getAssignments);
		ArrayList<AssignmentResponseEvent> assignmentList = (ArrayList) MessageUtil.compositeToCollection(assignmentResponse,
			AssignmentResponseEvent.class);
		AssignmentResponseEvent currentRecord = null;
		AssignmentResponseEvent oldestRecord = null;
		Iterator assignmentIter = assignmentList.iterator();
		while (assignmentIter.hasNext()) {
			currentRecord = (AssignmentResponseEvent) assignmentIter.next();
			if(currentRecord != null){
				if(oldestRecord == null){
					oldestRecord = currentRecord;
				}else{
					if(currentRecord.getCreateDate().after(oldestRecord.getCreateDate())){
						oldestRecord = currentRecord;
					}
				}			
			}
		}

		return oldestRecord;

	}
	
	/**
	 * search for assignments, and check if new assignment was successfully created
	 * @param casefileId
	 * @param newReferralNumber
	 * @return
	 */
	private boolean retrieveCreateAssignmentReferral(String casefileId, String newReferralNumber) {

		boolean isNewAssignment = false;
		// retrieve assignments
		GetAssignmentsByCasefileIdEvent getAssignments = (GetAssignmentsByCasefileIdEvent)
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETASSIGNMENTSBYCASEFILEID);
		getAssignments.setCasefileId(casefileId);
		CompositeResponse assignmentResponse = MessageUtil.postRequest(getAssignments);
		ArrayList<AssignmentResponseEvent> assignmentList = (ArrayList) MessageUtil.compositeToCollection(assignmentResponse,
			AssignmentResponseEvent.class);
		Iterator assignmentIter = assignmentList.iterator();
		while(assignmentIter.hasNext()) {
			AssignmentResponseEvent assignment = (AssignmentResponseEvent) assignmentIter.next();
			if(assignment.getReferralNum() != null && assignment.getReferralNum().equalsIgnoreCase(newReferralNumber)){
				isNewAssignment = true;
			}
		}

		return isNewAssignment;
	}
	
	/**
	 * retrieve assignment records by casefileId and sort based on referral number
	 * @param regform
	 * @param casefileId
	 * @return
	 */
	public static ProdSupportForm retrieveAssignmentRecords(ProdSupportForm regform, String casefileId){	
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
	 * insert new assignment record JCASSIGNMENT
	 * @param casefileId
	 */
	private void insertNewAssignmentReferral(ProdSupportForm regform, AssignmentResponseEvent assignmentToCopy, String newReferralNumber) {

		/*fill in everything except the
		 * referralnumber with the same information.
		 */
		CreateProductionSupportAssignmentEvent createAssignment = (CreateProductionSupportAssignmentEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.CREATEPRODUCTIONSUPPORTASSIGNMENT);
		
		createAssignment.setCasefileId(regform.getCasefileId());
		createAssignment.setReferralNumber(newReferralNumber);
		createAssignment.setServiceUnitId(assignmentToCopy.getServiceUnitId());
		createAssignment.setAssignmentLevelId(assignmentToCopy.getAssessmentLevelId());
		createAssignment.setAssignByuserId(assignmentToCopy.getJpoUserId());	
		createAssignment.setAssignmentAddDate(assignmentToCopy.getAssignmentDate());
		createAssignment.setAssignmentType(assignmentToCopy.getAssignmentType());
		createAssignment.setRefSeqNum("50");
		
		CompositeResponse assignmentResponse = MessageUtil.postRequest(createAssignment);
	}
}
