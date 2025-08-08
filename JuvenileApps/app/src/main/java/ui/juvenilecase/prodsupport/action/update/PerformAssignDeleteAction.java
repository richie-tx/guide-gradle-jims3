package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.productionsupport.DeleteProductionSupportAssignmentEvent;
import messaging.productionsupport.GetProductionSupportAssignmentEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 */
public class PerformAssignDeleteAction extends Action {
	private Logger log = Logger.getLogger("PerformAssignDeleteAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		String casefileId = regform.getCasefileId();
		
		if (casefileId == null || casefileId.equals("")) {
			regform.setMsg("CasefileID was null. Please enter CasefileId");
			return (mapping.findForward("error"));
		}
		// Loop through the selected Assignment_IDs
		String[] selectedIds = regform.getSelectedAssignments();

		TreeMap<String,AssignmentResponseEvent> deletedAssignments = new TreeMap();
		TreeMap<String,AssignmentResponseEvent> failedDeletionAssignments = new TreeMap();
		ArrayList undeletedAssignmentErrorsList = new ArrayList();
		ArrayList deletedAssignmentList = new ArrayList();
		if (selectedIds != null && selectedIds.length > 0) {
			/**
			 * Casefiles MUST have at least one assignment. Disallow deletes if
			 * they try to delete them all.
			 **/
			if (selectedIds.length > 0) {
				for (int i = 0; i < selectedIds.length; i++) {
					// delete the record
					log.info("Begin ASSIGNMENT DELETE for AssignmentID=" + selectedIds[i].toString() 
							+ " For LogonId: " + SecurityUIHelper.getLogonId());
					AssignmentResponseEvent originalAssignment = retrieveAssignmentRecord(selectedIds[i].toString());
					deleteAssignmentRecord(selectedIds[i].toString());
					// check if successfully deleted
					
					AssignmentResponseEvent assignment = retrieveAssignmentRecord(selectedIds[i].toString());
					
					if(assignment != null){
						log.info("ERROR: ASSIGNMENT DELETION *FAILED* for AssignmentID=" + selectedIds[i].toString() 
								+ " For LogonId: " + SecurityUIHelper.getLogonId());
						failedDeletionAssignments.put(assignment.getAssignmentId(), assignment);						
					}else{
						log.info("ASSIGNMENT SUCCESSFULLY DELETED for AssignmentID=" + selectedIds[i].toString() 
								+ " For LogonId: " + SecurityUIHelper.getLogonId());
						deletedAssignments.put(originalAssignment.getAssignmentId(), originalAssignment);
					}
				}
			} else {
				regform.setMsg("Casefiles must have at least one assignment.");
				return mapping.findForward("error");
			}			
		}else{
			regform.setMsg("Please select one or more assignments.");
			return mapping.findForward("error");
		}

		// check maps and determine if any deleted records failed
		for (int i = 0; i < selectedIds.length; i++) {
			// check if successfully deleted
			if(deletedAssignments != null && deletedAssignments.size() > 0){
				/** Flag the assignment records that were deleted **/
				Iterator deletedAssignmentIter = deletedAssignments.values().iterator();
				while (deletedAssignmentIter.hasNext()) {
					AssignmentResponseEvent deletedAssignmentResponse = (AssignmentResponseEvent) deletedAssignmentIter.next();
					if (deletedAssignmentResponse.getAssignmentId().equals(selectedIds[i])){
						deletedAssignmentResponse.setWasChecked(true);
						deletedAssignmentList.add(deletedAssignmentResponse);
					}
				}
			}
			if(failedDeletionAssignments != null && failedDeletionAssignments.size() > 0){
				/** Flag the assignment records that were not successfully deleted **/
				Iterator undeletedAssignmentIter = failedDeletionAssignments.values().iterator();
				while (undeletedAssignmentIter.hasNext()) {
					AssignmentResponseEvent undeletedAssignmentResponse = (AssignmentResponseEvent) undeletedAssignmentIter.next();
					if (undeletedAssignmentResponse.getAssignmentId().equals(selectedIds[i])){
						undeletedAssignmentResponse.setWasChecked(false);
						undeletedAssignmentErrorsList.add(undeletedAssignmentResponse);
					}
				}
			}
		}
		
		// determine whether error condition
		if(failedDeletionAssignments != null && failedDeletionAssignments.size() > 0){	
			if(undeletedAssignmentErrorsList != null){
				regform.setAssignmentCount(undeletedAssignmentErrorsList.size());
				regform.setAssignments(undeletedAssignmentErrorsList);
			}
			regform.setMsg("Error - One or more assignments were not deleted. See list for records not deleted.");
			return mapping.findForward("error");
		}else{
			if(deletedAssignmentList != null){
				regform.setAssignmentCount(deletedAssignmentList.size());
				regform.setAssignments(deletedAssignmentList);
			}
			regform.setMsg("");
			return mapping.findForward("success");		
		}
	}
	
	/**
	 * retrieve assignment record by assignmentId
	 * @param regform
	 * @param assignmentId
	 * @return
	 */
	public static AssignmentResponseEvent retrieveAssignmentRecord(String assignmentId)
	{	
		GetProductionSupportAssignmentEvent getAssignment = (GetProductionSupportAssignmentEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTASSIGNMENT);
		getAssignment.setAssignmentId(assignmentId);
		CompositeResponse assignmentResponse = MessageUtil.postRequest(getAssignment);
		ArrayList<AssignmentResponseEvent> assignmentList = (ArrayList) MessageUtil.compositeToCollection(assignmentResponse,
			AssignmentResponseEvent.class);	
	
		if(assignmentList != null && assignmentList.size() > 0){
			return assignmentList.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * retrieve assignment record by assignmentId
	 * @param regform
	 * @param assignmentId
	 * @return
	 */
	public static void deleteAssignmentRecord(String assignmentId)
	{	
		DeleteProductionSupportAssignmentEvent getAssignment = (DeleteProductionSupportAssignmentEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTASSIGNMENT);
		getAssignment.setAssignmentId(assignmentId);
		CompositeResponse assignmentResponse = MessageUtil.postRequest(getAssignment);
	}

}
