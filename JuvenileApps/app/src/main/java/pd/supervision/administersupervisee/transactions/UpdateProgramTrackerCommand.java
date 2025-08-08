package pd.supervision.administersupervisee.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.ujac.util.BeanComparator;

import naming.CaseloadConstants;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.administercaseload.SuperviseeHistory;
import messaging.administersupervisee.UpdateProgramTrackerEvent;
import messaging.administersupervisee.query.GetSuperviseeHistoryByTypeEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * @author dgibler
 *
 */
public class UpdateProgramTrackerCommand implements ICommand {

	public void execute(IEvent event) throws Exception {
		UpdateProgramTrackerEvent reqEvent = (UpdateProgramTrackerEvent) event;
		
		Supervisee supervisee = Supervisee.findByDefendantId(reqEvent.getSpn());

		if (supervisee == null){
			ErrorResponseEvent re = new ErrorResponseEvent();
			re.setMessage("Supervisee record not found for spn: " + reqEvent.getSpn());
			MessageUtil.postReply(re);
			return;
		}
		if (reqEvent.isAdd()){
			this.removeProgramTracker(supervisee, reqEvent);
			supervisee.setProgramTrackerEffectiveDate(reqEvent.getEffectiveDate());
			supervisee.setProgramTrackerId(reqEvent.getProgramTrackerId());
			supervisee.setProgramTrackerEndDate(null);
			this.createHistory(supervisee);
		
		} else if (reqEvent.isRemove()){
			this.removeProgramTracker(supervisee, reqEvent);
		
		} else if (reqEvent.isCorrect()){
			List sortedHistory = this.getSortedProgramTrackerHistory(supervisee.getOID());
			//Set supervisee values when correcting current history only!
			SuperviseeHistory history = this.getSuperviseeHistory(sortedHistory, reqEvent.getSuperviseeHistoryId());
			if (history != null){
				if (this.modifyingCurrentHistoryRecord(sortedHistory, reqEvent.getSuperviseeHistoryId())){
					supervisee.setProgramTrackerEffectiveDate(reqEvent.getEffectiveDate());
					supervisee.setProgramTrackerId(reqEvent.getProgramTrackerId());
					supervisee.setProgramTrackerEndDate(reqEvent.getEndDate());
					supervisee.setProgramTrackerId(reqEvent.getProgramTrackerId());
					this.updateHistory(supervisee, history);
				} else {
					history.setProgramTrackerEffectiveDate(reqEvent.getEffectiveDate());
					history.setProgramTrackerEndDate(reqEvent.getEndDate());
					history.setProgramTrackerId(reqEvent.getProgramTrackerId());
					this.updateHistory(history);
				}
				
			} else {
				ErrorResponseEvent re = new ErrorResponseEvent();
				re.setMessage("Supervisee History record not found for correction");
				MessageUtil.postReply(re);
			}
		
		} else if (reqEvent.isDelete()){
			//Reinstate Program information from prior history record if deleting current history record.
			List sortedHistory = this.getSortedProgramTrackerHistory(supervisee.getOID());
			if (this.modifyingCurrentHistoryRecord(sortedHistory, reqEvent.getSuperviseeHistoryId())){
				SuperviseeHistory priorHistory = this.getPreviousProgramTrackerInformation(supervisee, sortedHistory, reqEvent.getSuperviseeHistoryId());
				if (priorHistory != null){
					supervisee.setProgramTrackerEffectiveDate(priorHistory.getProgramTrackerEffectiveDate());
					supervisee.setProgramTrackerEndDate(priorHistory.getProgramTrackerEndDate());
					supervisee.setProgramTrackerId(priorHistory.getProgramTrackerId());
				} else {
					//Remove tracker info on supervisee if current history record was deleted.
					supervisee.setProgramTrackerEffectiveDate(null);
					supervisee.setProgramTrackerEndDate(null);
					supervisee.setProgramTrackerId(null);
				}
			}
			SuperviseeHistory history = this.getSuperviseeHistory(sortedHistory, reqEvent.getSuperviseeHistoryId());
			if (history != null){
				history.delete();
			} else {
				ErrorResponseEvent re = new ErrorResponseEvent();
				re.setMessage("Supervisee History record not found for delete");
				MessageUtil.postReply(re);
			}
		}
	}
	
	/**
	 * Retrieve history record from list.
	 * @param histList
	 * @param superviseeHistoryId
	 * @return
	 */
	private SuperviseeHistory getSuperviseeHistory(List histList, String superviseeHistoryId) {
		
		SuperviseeHistory superviseeHistory = null;
		for (int i = 0; i < histList.size(); i++) {
			superviseeHistory = (SuperviseeHistory) histList.get(i);
			if (superviseeHistory.getOID().equals(superviseeHistoryId)){
				break;
			}
		}
		return superviseeHistory;
	}

	/**
	 * Retrieve SuperviseeHistory records and sort by OID.
	 * @param superviseeId
	 * @return
	 */
	private List getSortedProgramTrackerHistory(String superviseeId){
		GetSuperviseeHistoryByTypeEvent getTrackerHistEvent = new GetSuperviseeHistoryByTypeEvent();
		getTrackerHistEvent.setSuperviseeId(superviseeId);
		getTrackerHistEvent.setType(CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_PROGRAM_TRACKER);
		
		List histList = SuperviseeHistory.findAll(getTrackerHistEvent);
		
		if (histList != null && histList.size() > 0){
			List sortedList = new ArrayList(histList);
			List sortFields = new ArrayList();
			//Sort by OID desc 
			sortFields.add(new ReverseComparator(new BeanComparator("OID")));
			ComparatorChain multiSort = new ComparatorChain(sortFields);
			Collections.sort(sortedList, multiSort);
			histList = sortedList;
		}
		return histList;
	}
	
	/**
	 * Determine if history record to be processed is the current history record.
	 * @param histList
	 * @param histToBeDeletedId
	 * @return
	 */
	private boolean modifyingCurrentHistoryRecord(List histList, String histToBeDeletedId){
		
		boolean modifyingCurrentHistoryRecord = false;
		
		if (histList != null && histList.size() > 0){
			SuperviseeHistory currentHist = (SuperviseeHistory) histList.get(0);
			if (currentHist.getOID().equals(histToBeDeletedId)){
				modifyingCurrentHistoryRecord = true;
			}
		}
		
		return modifyingCurrentHistoryRecord;
	}
	
	/**
	 * Determine if deleting history for current program and if so, return prior history information 
	 * @param supervisee
	 * @param sortedHistory
	 * @param histToBeDeletedId
	 * @return
	 */
	private SuperviseeHistory getPreviousProgramTrackerInformation(Supervisee supervisee, List sortedHistory, String histToBeDeletedId) {
		
		SuperviseeHistory nextHistoryRec = null;
		
		//If deleting most recent program information, reinstate info from previous program.
		if (sortedHistory.size() > 0){
			boolean deletingCurrentHistRec = false;
			SuperviseeHistory hist = null;
			
			for (int i = 0; i < sortedHistory.size(); i++) {
				hist = (SuperviseeHistory) sortedHistory.get(i);
				if (hist.getOID().equals(histToBeDeletedId)){
					if (i==0){
						//Only want to reinstate tracker info if deleting most recent history record.
						hist = null;
						deletingCurrentHistRec = true;
						continue;
					}
				} else if (deletingCurrentHistRec){
					nextHistoryRec = hist;
					break;
				}
			}
		}

		return nextHistoryRec;
	}
	
	/**
	 * Create new history record with values from Supervisee.
	 * @param supervisee
	 */
	private SuperviseeHistory createHistory(Supervisee supervisee) {
		SuperviseeHistory history = new SuperviseeHistory();
		history.setAssignedProgramUnitId(supervisee.getAssignedProgramUnitId());
		history.setAssignedStaffPositionId(supervisee.getAssignedProgramUnitId());
		history.setCaseloadCreditStaffPositionId(supervisee.getCaseloadCreditStaffPositionId());
		history.setCurrentlySupervised(supervisee.isCurrentlySupervised());
		history.setLosEffectiveDate(supervisee.getLosEffectiveDate());
		history.setProgramTrackerEffectiveDate(supervisee.getProgramTrackerEffectiveDate());
		history.setProgramTrackerEndDate(supervisee.getProgramTrackerEndDate());
		history.setProgramTrackerId(supervisee.getProgramTrackerId());
		history.setProgramUnitAssignmentDate(supervisee.getProgramUnitAssignmentDate());
		history.setSupervisionLevelId(supervisee.getSupervisionLevelId());
		history.setSuperviseeId(supervisee.getOID());
		history.setZipCode(supervisee.getZipCode());
		history.setType(CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_PROGRAM_TRACKER);
		
		return history;
	}
	
	/**
	 * Update history with new program tracker values and all other values from history record.
	 * @param supervisee
	 * @param history
	 */
	private void updateHistory(Supervisee supervisee, SuperviseeHistory history){
		this.updateHistory(history);
		history.setProgramTrackerEffectiveDate(supervisee.getProgramTrackerEffectiveDate());
		history.setProgramTrackerEndDate(supervisee.getProgramTrackerEndDate());
		history.setProgramTrackerId(supervisee.getProgramTrackerId());
	}
	
	/**
	 * Update history with values from history record.
	 * @param history
	 */
	private void updateHistory(SuperviseeHistory history){
		history.setAssignedProgramUnitId(history.getAssignedProgramUnitId());
		history.setAssignedStaffPositionId(history.getAssignedProgramUnitId());
		history.setCaseloadCreditStaffPositionId(history.getCaseloadCreditStaffPositionId());
		history.setCurrentlySupervised(history.isCurrentlySupervised());
		history.setLosEffectiveDate(history.getLosEffectiveDate());
		history.setProgramTrackerEffectiveDate(history.getProgramTrackerEffectiveDate());
		history.setProgramTrackerEndDate(history.getProgramTrackerEndDate());
		history.setProgramTrackerId(history.getProgramTrackerId());
		history.setProgramUnitAssignmentDate(history.getProgramUnitAssignmentDate());
		history.setSupervisionLevelId(history.getSupervisionLevelId());
		history.setSuperviseeId(history.getSuperviseeId());
		history.setZipCode(history.getZipCode());
		history.setType(CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_PROGRAM_TRACKER);
	}
	
	/**
	 * Remove program tracker information
	 * @param supervisee
	 * @param reqEvent
	 */
	private void removeProgramTracker(Supervisee supervisee, UpdateProgramTrackerEvent reqEvent){

		if (reqEvent.isAdd()){
			//end previous program if one exists and it has ended.
			if (supervisee.getProgramTrackerId() != null
					&& supervisee.getProgramTrackerEndDate() == null){
				supervisee.setProgramTrackerEndDate(reqEvent.getEffectiveDate());
				SuperviseeHistory history = this.createHistory(supervisee);
				//bind so this history record will be created before new program history record.
				history.bind();
			}
		} else {
			supervisee.setProgramTrackerEndDate(reqEvent.getEndDate());
			this.createHistory(supervisee);
		}
	}
}
