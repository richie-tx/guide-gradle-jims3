//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionorder\\transactions\\ReinstateSupervisionOrderCommand.java

package pd.supervision.supervisionorder.transactions;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.supervisionorder.ReinstateSupervisionOrderEvent;
import messaging.supervisionorder.reply.ReinstateSupervisionOrderErrorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import naming.CaseloadConstants;
import naming.PDCodeTableConstants;
import pd.security.PDSecurityHelper;
import pd.supervision.administercaseload.CaseAssignment;
import pd.supervision.administercaseload.CaseAssignmentHelper;
import pd.supervision.administercaseload.CaseAssignmentHistory;
import pd.supervision.administercasenotes.Casenote;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import pd.supervision.supervisionorder.SupervisionOrderReinstatement;
import pd.supervision.supervisionorder.SupervisionPeriod;
import pd.supervision.supervisionorder.SupervisionPeriodRedirect;

/**
 * @author dgibler
 *  
 */
public class ReinstateSupervisionOrderCommand implements ICommand {

	/**
	 * @roseuid 4421699C0387
	 */
	public ReinstateSupervisionOrderCommand() {

	}

	/**
	 * @param event
	 * @roseuid 442071B6015F
	 */
	public void execute(IEvent event) {
		ReinstateSupervisionOrderEvent requestEvent = (ReinstateSupervisionOrderEvent) event;
		SupervisionOrder order = SupervisionOrder.find(requestEvent
				.getSupervisionOrderId());
		if (order != null) {
			boolean success = true;
			if (SupervisionOrderHelper.isOutOfCountyCase(order.getCriminalCaseId())){
				success = setSupervisionPeriodForOOCCase(order);
			} else {
				success = setSupervisionPeriodForHCCase(order);
			}
			if (success) { //set attribute only if successful
				order.setOrderStatusId(PDCodeTableConstants.STATUS_ACTIVE_ID);
				order.setOrderFiledDate(requestEvent.getReinstatementDate());
				order.setInactivationDate(null);
				SupervisionOrderReinstatement reinstatement = SupervisionOrderReinstatement
						.create(requestEvent);
				order.insertReinstatements(reinstatement);
				//Bind order to prepare for update of caseload credit.
				order.bind();
				String agencyId=PDSecurityHelper.getUserAgencyId();
				if(agencyId!=null && agencyId.equalsIgnoreCase(PDCodeTableConstants.CSCD_AGENCY)){
					// blank out SupervisionEndDate in the Case Assignment record which restores the 'workload credit officer' to the prior position. 
					Iterator iterAssign=CaseAssignment.findAll("criminalCaseId", order.getCriminalCaseId()); // Get case assingment record if any of order
			    	if(iterAssign!=null && iterAssign.hasNext()){
			    		CaseAssignment myAssignment=(CaseAssignment)iterAssign.next();
			    		myAssignment.setTerminationDate(null);
			    		if (myAssignment.getAssignedStaffPositionId() != null){
			    			myAssignment.setCaseState(CaseloadConstants.OFFICER_ASSIGNED);
			    		} else if (myAssignment.getProgramUnitId() != null 
			    				&& myAssignment.getAllocatedStaffPositionId() != null){
			    			myAssignment.setCaseState(CaseloadConstants.SUPERVISOR_ALLOCATED);
			    		} else if (myAssignment.getProgramUnitId() !=  null){
			    			myAssignment.setCaseState(CaseloadConstants.PROGRAM_UNIT_ASSIGNED);
			    		}
			    		//bind case assignment to prepare for update of caseload credit.
			    		myAssignment.bind();
			    		CaseAssignmentHelper.createLegacyRecordsForReinstatement(myAssignment, requestEvent.getReinstatementDate());
			    		//Create history record recording the reinstatement of assignment info.
			            CaseAssignmentHistory history = new CaseAssignmentHistory();
			            history.setCaseAssignmentId(myAssignment.getOID());
			            history.setHistoryType(myAssignment.getCaseState());
			    		
			            history.setTerminationDate(myAssignment.getTerminationDate());
			            history.setProgramUnitId(myAssignment.getProgramUnitId());
			            history.setProgramUnitAssignDate(myAssignment.getProgramUnitAssignDate());
			            history.setAssignedStaffPositionId(myAssignment.getAssignedStaffPositionId());
			            history.setOfficerAssignDate(myAssignment.getOfficerAssignDate());
			            history.setAllocatedStaffPositionId(myAssignment.getAllocatedStaffPositionId());
			            history.setAcknowledgePositionId(myAssignment.getAcknowledgePositionId());
			    		history.setAcknowledgeUserId(myAssignment.getAcknowledgeUserId());
			    		history.setSupervisionStyleId(myAssignment.getSupervisionStyleId());
			    		history.setAcknowledgeStatusId(myAssignment.getAcknowledgeStatusId());
			    		history.setAcknowledgeDate(myAssignment.getAcknowledgeDate());
			    		history.setSupervisorAllocationDate(myAssignment.getSupervisorAllocationDate());

			    	}
					
				}
				CaseAssignmentHelper.reactivateSupervisee(order.getDefendantId());
				// Create casenote
				Casenote.createOrderCasenote(order.getAgencyId(), order
						.getOID(), order.getDefendantId(), order
						.getSupervisionPeriodId(), requestEvent.getNotes());
				//order.insertCasenote(casenote);
				
			}
		}
	}

	private boolean setSupervisionPeriodForOOCCase(SupervisionOrder order) {
		/* An OOC case is reinstated when the defendant returns to Harris County.  
		 * It is possible for a new HC case to have been activated and a new supervision period started
		 * while the OOC case was transferred out.  In this situation, the supervision period for
		 * the newly reinstated OOC case is set to that of the current/open supervision period.
		 */
		boolean result = true;
		// get SupervisionPeriod from Order
		SupervisionPeriod thisOrderSupPeriod = order.getSupervisionPeriod();

		Date transferInDate = SupervisionOrderHelper.getOutOfCountyTransferInDate(order.getDefendantId(), order.getCriminalCaseId());

		if (thisOrderSupPeriod!=null && thisOrderSupPeriod.getSupervisionEndDate() != null) { 
			//There may have been a new order activated on a spn that had a closed
			//supervision period, i.e. There was not an active supervision period at the
			//time the order was activated.  Older orders would be pointing to a prior(closed)
			//supervision period.
			SupervisionPeriod activeSupPeriod = SupervisionPeriod
				.findActiveSupervisionPeriod(order.getDefendantId(), order.getAgencyId());

			if (activeSupPeriod == null){
				//There is no active supervision period for this defendant.
				//Create new supervision period using transferInDate as begin date.
				if (transferInDate != null){ 
					SupervisionPeriod newSupPeriod = SupervisionPeriod.create(order);
					order.setSupervisionPeriodId(newSupPeriod.getOID());
					newSupPeriod.setSupervisionBeginDate(transferInDate);
				} else {
					ReinstateSupervisionOrderErrorEvent errorEvent = new ReinstateSupervisionOrderErrorEvent();
					errorEvent
							.setErrorMessage("Order cannot be reinstated. TransferInDate not found for new supervision period");
					EventManager.getSharedInstance(EventManager.REPLY)
							.postEvent(errorEvent);
					result = false;
				}
			} else {
				order.setSupervisionPeriodId(activeSupPeriod.getOID());
				if (transferInDate != null 
						&& transferInDate.before(activeSupPeriod.getSupervisionBeginDate())){
					activeSupPeriod.setSupervisionBeginDate(transferInDate);
				} else if (transferInDate != null
						&& order.getActivationDate().after(activeSupPeriod.getSupervisionBeginDate())){
					//Read through active orders to determine if supervisionPeriodBeginDate should
					//be changed to later date.
					this.checkForEarlierCaseSupervisionBeginDate(order, activeSupPeriod);
				}
			}
		} else if (thisOrderSupPeriod != null){//Defendant has active supervision period
			if (transferInDate != null 
					&& transferInDate.before(thisOrderSupPeriod.getSupervisionBeginDate())){
				thisOrderSupPeriod.setSupervisionBeginDate(transferInDate);
			} else if (transferInDate != null
					&& order.getActivationDate().after(thisOrderSupPeriod.getSupervisionBeginDate())){
				//Read through active orders to determine if supervisionPeriodBeginDate should
				//be changed to later date.
				this.checkForEarlierCaseSupervisionBeginDate(order, thisOrderSupPeriod);
			}
		} else {//Supervision Period was not found
			ReinstateSupervisionOrderErrorEvent errorEvent = new ReinstateSupervisionOrderErrorEvent();
			errorEvent
					.setErrorMessage("Order cannot be reinstated. SupervisionPeriod not found");
			EventManager.getSharedInstance(EventManager.REPLY)
					.postEvent(errorEvent);
			result = false;

		}
		if (result){//No errors encountered on supervision period.
			order.setTransferInDate(transferInDate);
		}
		return result;
	}

	private void checkForEarlierCaseSupervisionBeginDate(SupervisionOrder order,
			SupervisionPeriod supPeriod) {
		List activeOrders = SupervisionOrderHelper.getActiveSupervisionOrders(order.getDefendantId(), order.getAgencyId());
		SupervisionOrder activeOrder = null;
		boolean isNoEarlierOrder = true;
		for (int i = 0; i < activeOrders.size(); i++) {
			activeOrder = (SupervisionOrder) activeOrders.get(i);
			if (activeOrder.getActivationDate().before(order.getActivationDate())){
				//There is another order with an earlier activation date.
				isNoEarlierOrder = false;
				break;
			} 
		}
		if (isNoEarlierOrder){
			//Change sp begin date to later date when there are no orders activated 
			//prior to the order being activated.
			supPeriod.setSupervisionBeginDate(order.getActivationDate());
		}
	}

	private boolean setSupervisionPeriodForHCCase(SupervisionOrder order) {
		/* A harris county case is always reinstated due to closing the case in error.
		 * In most cases, the reinstatment will occur very soon after closing the case in
		 * error.  It is unlikely that an OOC case would have been reinstated and a new
		 * supervision period created in that short amount of time.
		 */

		boolean result = true;
		// get SupervisionPeriod from Order
		SupervisionPeriod thisOrderSupPeriod = order.getSupervisionPeriod();
		SupervisionPeriod latestPeriod = SupervisionPeriod
				.findLatestSupervisionPeriod(order.getDefendantId(), order
						.getAgencyId());

		if (thisOrderSupPeriod!=null && thisOrderSupPeriod.getSupervisionEndDate() != null) { 
			//The supervision period on this order has ended.
			if (thisOrderSupPeriod.getOID().equals(latestPeriod.getOID())) {
				// if this is the latest SupervisionPeriod, reactivate this one
				thisOrderSupPeriod.setSupervisionEndDate(null);
			} else { // there is a new SupervisionPeriod
				// check to see if its previous is the SupervisionOrder's
				// SupervisionPeriod
				if (latestPeriod.getPreviousSupervisionPeriodId() != null && 
						latestPeriod.getPreviousSupervisionPeriodId().equals(
						thisOrderSupPeriod.getOID())) {
					SupervisionPeriodRedirect periodRedirect = SupervisionPeriodRedirect
							.findBySourcePeriod(latestPeriod.getOID());
					if (periodRedirect != null) {
						// update the target to previous SupervisionPeriod
						periodRedirect.setTargetSupervisionPeriodId(thisOrderSupPeriod
								.getOID());
						// set prevSupPeriod.endDate to null to
						// reactivate/reopen it
						thisOrderSupPeriod.setSupervisionEndDate(null);
						// delete the current supervisionPeriod
						latestPeriod.delete();
					}
				} else {
					// if its not the previous one, it can't be reinstated
					ReinstateSupervisionOrderErrorEvent errorEvent = new ReinstateSupervisionOrderErrorEvent();
					errorEvent
							.setErrorMessage("Order can be reinstated for the current or previous SupervisionPeriod");
					EventManager.getSharedInstance(EventManager.REPLY)
							.postEvent(errorEvent);
					result = false;
				}

			} 
		} else if (thisOrderSupPeriod == null){//Supervision Period was not found
			ReinstateSupervisionOrderErrorEvent errorEvent = new ReinstateSupervisionOrderErrorEvent();
			errorEvent
					.setErrorMessage("Order cannot be reinstated. SupervisionPeriod not found");
			EventManager.getSharedInstance(EventManager.REPLY)
					.postEvent(errorEvent);
			result = false;
		}
		if (result == true){
			if (order.getActivationDate().before(thisOrderSupPeriod.getSupervisionBeginDate())){
				thisOrderSupPeriod.setSupervisionBeginDate(order.getActivationDate());
			} else if (order.getActivationDate().after(thisOrderSupPeriod.getSupervisionBeginDate())){
				this.checkForEarlierCaseSupervisionBeginDate(order, thisOrderSupPeriod);			}
		}
		return result;
		// get the latest SupervisionPeriod
	}

	/**
	 * @param event
	 * @roseuid 442071B60161
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 442071B60163
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param updateObject
	 * @roseuid 4421699C0397
	 */
	public void update(Object updateObject) {

	}
}
