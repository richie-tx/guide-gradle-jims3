package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.GetAssignmentsByCasefileIdEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.productionsupport.GetProductionSupportAssignmentEvent;
import messaging.productionsupport.UpdateProductionSupportAssignmentEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
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
 */
public class PerformUpdateAssignmentAction extends Action {

	private Logger log = Logger.getLogger("PerformUpdateAssignmentAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    	
		ProdSupportForm regform = (ProdSupportForm) form;
		String logonid = SecurityUIHelper.getLogonId(); // For readability in the logs.
//		boolean status = false;
		String casefileId = regform.getCasefileId();
		String assignmentId = regform.getAssignmentId();
		boolean isChange = false;
		
		//update event
		UpdateProductionSupportAssignmentEvent updateAssignmentEvent = (UpdateProductionSupportAssignmentEvent)
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTASSIGNMENT);
		
		// check for no assignment chosen
		updateAssignmentEvent.setAssignmentId(assignmentId);
		
		if (assignmentId == null || assignmentId.equals("")) {
			regform.setMsg("Assignment ID is required, and was null. Please choose an Assignment Record.");
			return (mapping.findForward("error"));
		}
		//set the Assignment ID
		
		AssignmentResponseEvent originalAssignmentEvent = retrieveOriginalAssignmentResponse(assignmentId);
		
		// put together the new date
		String newAssignmentDate = regform.getNewActDate();
		
		Date assignmentDate = null;
		assignmentDate = DateUtil.stringToDate( newAssignmentDate, DateUtil.DATE_FMT_1);
		
		boolean isAssignmentDateChanged = checkIfTwoValuesChanged(newAssignmentDate,DateUtil.dateToString(originalAssignmentEvent.getAssignmentDate(),DateUtil.DATE_FMT_1));
		if (isAssignmentDateChanged) {
		    isChange = true;
		    regform.setNewActDate(DateUtil.dateToString(assignmentDate, "yyyy-MMM-dd"));
		    updateAssignmentEvent.setAssignmentDate(DateUtil.stringToDate(newAssignmentDate, DateUtil.DATE_FMT_1));
    		
		} else {
			regform.setNewActDate("");
			updateAssignmentEvent.setAssignmentDate(originalAssignmentEvent.getAssignmentDate());
		}
		
		if(regform.getReferralAssmntType()!=null && !regform.getReferralAssmntType().equals("")){
		    	
		    	String newReferralAssmntType = regform.getReferralAssmntType();
        		boolean isReferralAssmntTypeChanged = checkIfTwoValuesChanged(newReferralAssmntType,originalAssignmentEvent.getAssignmentType());
        		if (isReferralAssmntTypeChanged) {
        		    isChange = true;
        		    regform.setReferralAssmntType(newReferralAssmntType);
        		    updateAssignmentEvent.setReferralAssmentType(newReferralAssmntType);
        		} 
        		else {
        			regform.setReferralAssmntType("");
        			updateAssignmentEvent.setReferralAssmentType(originalAssignmentEvent.getAssignmentType());
                	}
		}
		if(regform.getRefSeqNum()!=null && !regform.getRefSeqNum().equals("")){
		    
    			String newRefSeqNum = regform.getRefSeqNum();
        		boolean isReferralAssmntTypeChanged = checkIfTwoValuesChanged(newRefSeqNum,originalAssignmentEvent.getRefSeqNum());
        		if (isReferralAssmntTypeChanged) {
        		    isChange = true;
        		    regform.setRefSeqNum(newRefSeqNum);
        		    updateAssignmentEvent.setReferralSeqNum(newRefSeqNum);
        		} 
        		else {
        			regform.setRefSeqNum("");
        			updateAssignmentEvent.setReferralSeqNum(originalAssignmentEvent.getRefSeqNum());
                	}
		}
		// check that a value changed before updating
		if(!isChange){
		    regform.setMsg("At least one value needs to be changed.");
		    return (mapping.findForward("error"));
			}
		
		// update the date 
		log.info("START Update Assignment Record AssignmentId: " + assignmentId + ", New Assignment Date: " +  newAssignmentDate);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(updateAssignmentEvent);
		
		// retrieve updated record
		regform = retrieveAssignmentRecord(regform,assignmentId);
		
		return mapping.findForward("success");
	}
	
	/**
	 * update assignment record date
	 * @param regform
	 * @param assignmentId
	 * @return
	 */
	public static void updateAssignmentRecord(ProdSupportForm regform,
							String assignmentId,
							Date assignmentDate,
							String refAssmntType,
							String refSeqNum){	
		UpdateProductionSupportAssignmentEvent updateAssignment = (UpdateProductionSupportAssignmentEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTASSIGNMENT);
		updateAssignment.setAssignmentId(assignmentId);
		updateAssignment.setAssignmentDate(assignmentDate);
		updateAssignment.setReferralAssmentType(refAssmntType);
		updateAssignment.setReferralSeqNum(refSeqNum);
		CompositeResponse assignmentResponse = MessageUtil.postRequest(updateAssignment);	
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
	
	public static AssignmentResponseEvent retrieveOriginalAssignmentResponse(String assignmentId){
		
		GetProductionSupportAssignmentEvent getAssignment = (GetProductionSupportAssignmentEvent)
			EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTASSIGNMENT);
		getAssignment.setAssignmentId(assignmentId);
		CompositeResponse assignmentResponse = MessageUtil.postRequest(getAssignment);
		AssignmentResponseEvent originalAssignment = (AssignmentResponseEvent)MessageUtil.filterComposite(assignmentResponse,
		AssignmentResponseEvent.class);
		
		return originalAssignment;
	}
	
	/**(
	 * compare two string values and determine if they are equal
	 * @param value
	 * @param compareValue
	 * @return
	 */
	private boolean checkIfTwoValuesChanged(String newValue, String OlderValue){
		boolean result = false;
		
		if(newValue != null && OlderValue != null && !newValue.equals("")){
			if(!newValue.equals(OlderValue)){
				result = true;
			}
		}else if (newValue != null && OlderValue == null){
				result = true;
		}
		
		return result;
	}

	
}
