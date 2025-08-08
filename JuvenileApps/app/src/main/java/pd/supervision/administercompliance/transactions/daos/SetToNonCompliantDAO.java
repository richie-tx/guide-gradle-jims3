//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\GetNonCompliantEventsCommand.java

package pd.supervision.administercompliance.transactions.daos;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.administercompliance.CreateNonCompliantEventEvent;
import messaging.administercompliance.SetToNonCompliantEvent;
import messaging.administercompliance.reply.NonComplianceEventTypeCodeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.persistence.Home;
import naming.SupervisionOrderConditionConstants;
import pd.common.DAOHandler;
import pd.supervision.administercasenotes.Casenote;
import pd.supervision.administercasenotes.CasenoteConditions;
import pd.supervision.administercompliance.NonComplianceEvent;
import pd.supervision.administercompliance.NonComplianceEventCasenote;
import pd.supervision.administercompliance.NonComplianceEventHistory;
import pd.supervision.administercompliance.NonComplianceEventType;
import pd.supervision.supervisionorder.SupervisionOrderConditionRel;
import pd.supervision.supervisionorder.SupervisionPeriod;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SetToNonCompliantDAO extends ComplianceDAO implements DAOHandler
{
   
   /**
    * @roseuid 473B887E0371
    */
   public SetToNonCompliantDAO() 
   {

   }
   
   /**
    * @param event
    * @roseuid 473B75560233
    */
   public void execute(Object aEvent) 
   {   
   	   SetToNonCompliantEvent event = (SetToNonCompliantEvent) aEvent;
   	   Enumeration requests = event.getRequests();
   	   
   	   UpdateCasenoteEvent uEvent = null;
   	   List nonCompliantEvents = new ArrayList();
   	   while (requests.hasMoreElements()){
	   	   Object obj = requests.nextElement();
	   	   if(obj instanceof UpdateCasenoteEvent){
	   	        uEvent = (UpdateCasenoteEvent) obj;
	   	   }else if(obj instanceof CreateNonCompliantEventEvent){
	   	        nonCompliantEvents.add(obj);
	   	   }
	   }
   	   
       // insert casenote
   	   Casenote casenote = null;
   	   int casenoteId = 0;
   	   if(uEvent != null){
   	       SupervisionPeriod sp = this.getActiveSupervisionPeriod(uEvent.getAgencyId(), event.getDefendantId());
   	       if(sp != null){
	           uEvent.setSupervisionPeriodId(sp.getOID());
	       }else{
	       	   ReturnException re = new ReturnException("Supervision Period is missing. Please contact with the administrator.");
   	       	   EventManager.getSharedInstance(EventManager.REPLY).postEvent(re);
   	       	   return;
   	       }
	       uEvent.setHowGeneratedId(SupervisionOrderConditionConstants.SYSTEMGENERATED);
 	       if(uEvent.isSaveAsDraft()){
	           uEvent.setCasenoteStatusId(SupervisionOrderConditionConstants.DRAFT);
	       }else{
	    	  uEvent.setCasenoteStatusId(SupervisionOrderConditionConstants.COMPLETE);
	       }
 	       uEvent.setContextType(SupervisionOrderConditionConstants.CONTEXTTYPE);
 	       
   	       casenote = new Casenote();
   	       casenote.updateCasenote(uEvent);
   	       casenote = (Casenote) new Home().bind(casenote);
           casenoteId = Integer.parseInt(casenote.getOID());
   	   }
   	   
   	   // insert noncompliantEvents
   	   NonComplianceEventCasenote nCasenote =  null;   	   
   	   for(int i=0; i < nonCompliantEvents.size(); i++){
   	       CreateNonCompliantEventEvent unceEvent = (CreateNonCompliantEventEvent) nonCompliantEvents.get(i);	
   	       NonComplianceEventHistory nh = null;
    	   
   	       if(unceEvent != null){
	   	   	   NonComplianceEvent pObj = new NonComplianceEvent();
	   	       String key = pObj.setNonComplianceEvent(unceEvent);
	   	       StringBuffer eventTypes = new StringBuffer();
     	       for(int j=0;j<unceEvent.getEventTypeCodeIds().length; j++){
	   	    	   String eventTypeCodeId = unceEvent.getEventTypeCodeIds()[j];
	   	       	   if(StringUtils.isNotEmpty(eventTypeCodeId)){
	   	       	       if(eventTypeCodeId.equalsIgnoreCase(SupervisionOrderConditionConstants.NEWEVENTTYPECODE)){
	   	       	    	   eventTypes.append(unceEvent.getNewEventType());
	   	       	    	   if(j != unceEvent.getEventTypeCodeIds().length -1){
		   	       	           eventTypes.append(",");
		   	       	       }  
	   	       	       }else{
	   	       	    	   NonComplianceEventType nType = new NonComplianceEventType(Integer.parseInt(key), eventTypeCodeId);
	   	       	    	   for(int k=0;k<unceEvent.getEventTypeList().size();k++){
	   	       	    		   NonComplianceEventTypeCodeResponseEvent eType = (NonComplianceEventTypeCodeResponseEvent) unceEvent.getEventTypeList().get(k);
	   	       	    		   if(eType != null && eType.getNonComplianceEventTypeCodeId().equals(eventTypeCodeId)){
	   	       	    			   eventTypes.append(eType.getNonComplianceEventTypeCodeDesc());
	   	       	    			   break;
	   	       	    		   }
	   	       	    	   }
	   	       	    	   if(j != unceEvent.getEventTypeCodeIds().length -1){
		   	       	           eventTypes.append(",");
		   	       	       }  
	   	       	       }	      	       	       	       
	   	       	   }
	   	       }
	   	       
               // insert NonCompliantEventCasenote
	   	   	   nCasenote =  new NonComplianceEventCasenote();
	   	   	   nCasenote.setCasenoteId(casenoteId);
	   	   	   nCasenote.setNonCompliantEventId(Integer.parseInt(key));  
	   	   	   nCasenote.setSprOrderConditionId(unceEvent.getSprOrderConditionId());
	   	   	   nCasenote.setConditionId(Integer.parseInt(unceEvent.getConditionId()));
	   	   	   
               // insert casenote conditions
	   	   	   CasenoteConditions cc = new CasenoteConditions();
	   	       cc.setSprOrderConditionId(unceEvent.getSprOrderConditionId());
	   	       cc.setCasenoteId(casenoteId); 
	   	       String caseNumber = removeSpacefromCaseNumber(unceEvent.getCaseNumber());
	   	       cc.setCaseNumber(caseNumber);
	   	       cc.setConditionId(Integer.parseInt(unceEvent.getConditionId()));
	   	       cc.setOrderChainNumber(unceEvent.getOrderChainNumber());
	   	       
               // update OrderConditionrel table
	   	       SupervisionOrderConditionRel rel = getSprOrderCondition(unceEvent.getSprOrderConditionId());
		       if(rel != null){
	 	           rel.setNcCount(rel.getNcCount() + 1);
	 	           rel.setNonCompliant(true);
	 	       }
	   	   	   	   	       
               // set the history
	   	       nh = new NonComplianceEventHistory();
	   	       nh.setCasenoteId(casenoteId);
	   	       nh.setNonComplianceEventId(Integer.parseInt(key));
	   	       String eventTypeDesc = eventTypes.toString();
	   	       if(StringUtils.isNotEmpty(eventTypeDesc)){
	   	    	   if(eventTypeDesc.endsWith(",")){
	   	    		   nh.setEventTypes(eventTypeDesc.substring(0,(eventTypeDesc.length() - 1)));	
	   	    	   }else{
	   	    		   nh.setEventTypes(eventTypeDesc);	
	   	    	   }
	   	       }
	   	       nh.setDateTime(pObj.getDateTime());
	   	       nh.setDetails(unceEvent.getDetails());
	   	       nh.setSprOrderConditionId(unceEvent.getSprOrderConditionId());
	   	       nh.setDefendentId(event.getDefendantId());
	   	   }
   	   }
   }

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#remove(java.lang.Object)
	 */
	public void remove(Object object) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#update(java.lang.Object)
	 */
	public void update(Object object) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#get(java.lang.Object)
	 */
	public void get(Object object) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#refresh(java.lang.Object)
	 */
	public void refresh(Object object) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @param caseNumber
	 * @return String
	 */
	public static String removeSpacefromCaseNumber(String caseNumber){
		StringBuffer trimCaseNum = new StringBuffer();
		if (StringUtils.isNotEmpty(caseNumber)){
			trimCaseNum.append(caseNumber.substring(0, 3));
			trimCaseNum.append(caseNumber.substring(4, caseNumber.length()));
		}
		return trimCaseNum.toString();
	}
}
