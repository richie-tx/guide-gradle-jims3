/**
 * 
 */
package pd.supervision.administersupervisee.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import messaging.administersupervisee.UpdateSuperviseeDetailsEvent;
import messaging.administersupervisee.query.GetSuperviseeHistoryByTypeEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.administercaseload.SuperviseeHistory;

/**
 * @author rcarter
 *
 */
public class UpdateSuperviseeDetailsCommand implements ICommand {
	
	public void execute(IEvent event) throws Exception {
		UpdateSuperviseeDetailsEvent reqEvent = (UpdateSuperviseeDetailsEvent) event;
		
		Supervisee supervisee = Supervisee.findByDefendantId(reqEvent.getSpn());

		if (supervisee == null){
			ErrorResponseEvent re = new ErrorResponseEvent();
			re.setMessage("Supervisee record not found for spn: " + reqEvent.getSpn());
			MessageUtil.postReply(re);
			return;
		}
		
		if (reqEvent.isAdd()){
			//update supervisee dna values
			this.updateSuperviseeRecordDNA(reqEvent, supervisee);
			this.createHistory(supervisee);
			
		} 
		else if (reqEvent.isUpdate()){	
			List sortedHistory = this.getSortedDNAHistory(supervisee.getOID());
			SuperviseeHistory history = (SuperviseeHistory)sortedHistory.get(0);
			// update superviseeHist record (latest based on oid)
			if (history != null){
				history.setDnaCollectedDate(reqEvent.getDnaCollectedDate());
				history.setDnaFlagInd(reqEvent.isDnaFlagInd());		
			}
			//update supervisee dna values
			this.updateSuperviseeRecordDNA(reqEvent, supervisee);	
		}else if(reqEvent.isDelete()){
			//delete supervisee dna values
			this.deleteSuperviseeRecordDNA(reqEvent, supervisee);
		}
	}
	
	
	/**
	 * update the supervisee record.
	 * @param reqEvent
	 * @param supervisee
	 */
	private void updateSuperviseeRecordDNA(UpdateSuperviseeDetailsEvent reqEvent, Supervisee supervisee){
		supervisee.setDnaCollectedDate(reqEvent.getDnaCollectedDate());
		supervisee.setDnaFlagInd(reqEvent.isDnaFlagInd());
	}
	
	/**
	 * delete the supervisee record by blanking out the dna flag and the date
	 * @param reqEvent
	 * @param supervisee
	 */
	private void deleteSuperviseeRecordDNA(UpdateSuperviseeDetailsEvent reqEvent, Supervisee supervisee){
		supervisee.setDnaCollectedDate(null);
		supervisee.setDnaFlagInd(false);
	}
	
	/**
	 * Retrieve SuperviseeHistory records and sort by OID.
	 * @param superviseeId
	 * @return
	 */
	private List getSortedDNAHistory(String superviseeId){
		GetSuperviseeHistoryByTypeEvent getDNAHistEvent = new GetSuperviseeHistoryByTypeEvent();
		getDNAHistEvent.setSuperviseeId(superviseeId);
		getDNAHistEvent.setType(CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_DNA);
		
		List histList = SuperviseeHistory.findAll(getDNAHistEvent);
		if (histList != null && histList.size() > 0){
			//Sort by OID desc 
			Collections.sort(histList, SuperviseeHistory.SuperviseeHistoryOidComparator);
		}
		return histList;
	}
	
	/**
	 * Create new history record with values from Supervisee.
	 * @param supervisee
	 */
	private SuperviseeHistory createHistory(Supervisee supervisee) {
		SuperviseeHistory history = new SuperviseeHistory();
		history.setDnaCollectedDate(supervisee.getDnaCollectedDate());
		history.setDnaFlagInd(supervisee.isDnaFlagInd());
		history.setSuperviseeId(supervisee.getOID());
		history.setType(CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_DNA);
		
		return history;
	}
	
}
