//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\GetNonCompliantEventsCommand.java

package pd.supervision.posttrial.transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.posttrial.reply.CaseAssignmentDataControlResponseEvent;
import messaging.posttrial.GetAssignmentAndHistoryEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import pd.contact.party.Party;
import pd.contact.user.UserProfile;
import pd.criminalcase.CriminalCase;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.viewassignment.CaseAssignmentReportItem;


/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetAssignmentAndHistoryCommand implements ICommand 
{
   
   /**
    * @roseuid 473B887E0371
    */
   public GetAssignmentAndHistoryCommand() 
   {

   }
   
   /**
    * @param event
    * @roseuid 473B75560233
    */
   public void execute(IEvent event) 
   { 
	    GetAssignmentAndHistoryEvent gEvent = (GetAssignmentAndHistoryEvent) event;
	    
		Iterator iterator = CaseAssignmentReportItem.findAll(gEvent);

		SortedMap map = new TreeMap();
		CriminalCase cCase = CriminalCase.find(new StringBuffer(gEvent.getCdi()).append(gEvent.getCaseNumber()).toString());
		for (;iterator.hasNext();) {			
			CaseAssignmentReportItem item = (CaseAssignmentReportItem) iterator.next();
			ICaseAssignment caseAssignment = item.valueObject();
			
			if(cCase != null){
				caseAssignment.setDefendantNameStr(cCase.getDefendantName());
			}
			
			UserProfile user = null;
			if(caseAssignment.getAcknowledgeUserId() != null && !caseAssignment.getAcknowledgeUserId().equals("")){
				user = UserProfile.find(caseAssignment.getAcknowledgeUserId());
				if(user != null){
					caseAssignment.setAcknowledgeUserName(user.getName());
				}
			}
			
			if(item.getSupervisorUserId() != null && !item.getSupervisorUserId().equals("")){
				user = UserProfile.find(item.getSupervisorUserId());			
				if(user != null){
					caseAssignment.setSupervisorName(user.getName());
				}
			}
			
			if(item.getOfficerUserId() != null && !item.getOfficerUserId().equals("")){
				user = UserProfile.find(item.getOfficerUserId());
				if(user != null){
					caseAssignment.setOfficerName(user.getName());
				}
			}
			caseAssignment.setCaseAssignmentState(item.getCaseStatus());
			caseAssignment.setCaseAssignmentId(item.getCaseAssignmentId());
			caseAssignment.setCaseAssignmentHistId(item.getCaseAssignmentHistId());
			map.put(item.getCreateTimestamp(), caseAssignment);
		}
		
		if(!map.isEmpty()){				
			CaseAssignmentDataControlResponseEvent responseEvent = new CaseAssignmentDataControlResponseEvent();
			if(cCase != null){
				Party defendant = cCase.getDefendant();
				if(defendant != null){
					responseEvent.setDefendantId(defendant.getSpn());
					responseEvent.setDefendantName(defendant.getName());
					responseEvent.setSsn(defendant.getSsn());
					responseEvent.setSexId(defendant.getSexId());
					responseEvent.setDob(defendant.getDateOfBirth());
				}
			}			
			responseEvent.setCdi(gEvent.getCdi());
			responseEvent.setCaseNumber(gEvent.getCaseNumber());
			
			ICaseAssignment currentCaseAssignment = (ICaseAssignment) map.get(map.lastKey());
			map.remove(map.lastKey());
			
			responseEvent.setCourt(currentCaseAssignment.getCourtId());
            
			if(!CaseloadConstants.CASE_CLOSED.equalsIgnoreCase(currentCaseAssignment.getCaseStatus())){
				responseEvent.setCaseSupervised(true);
				responseEvent.setSuperviseeSupervised(true);
			}else{
				Supervisee supervisee = Supervisee.findByDefendantId(responseEvent.getDefendantId());
				if(supervisee != null){
					responseEvent.setSuperviseeSupervised(supervisee.isCurrentlySupervised());
				}
			}
			responseEvent.setCaseAssignmentHistories(new ArrayList(map.values()));
			responseEvent.setCurrentAssignment(currentCaseAssignment);			
			MessageUtil.postReply(responseEvent);
		}
   }
  
   /**
    * @param event
    * @roseuid 473B75560240
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 473B75560242
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 473B75560244
    */
   public void update(Object anObject) 
   {
    
   }
}
