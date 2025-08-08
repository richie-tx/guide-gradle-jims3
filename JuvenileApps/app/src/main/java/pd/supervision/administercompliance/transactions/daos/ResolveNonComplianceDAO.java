//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\GetNonCompliantEventsCommand.java

package pd.supervision.administercompliance.transactions.daos;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.administercompliance.GetLatestNonComplianceEventEvent;
import messaging.administercompliance.ResolveNonComplianceEvent;
import messaging.administercompliance.UpdateOrderConditionEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.exception.ReturnException;
import naming.SupervisionOrderConditionConstants;
import pd.common.DAOHandler;
import pd.supervision.administercasenotes.Casenote;
import pd.supervision.administercasenotes.CasenoteConditions;
import pd.supervision.administercompliance.NonComplianceEvent;
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
public class ResolveNonComplianceDAO extends ComplianceDAO implements DAOHandler
{
   
   /**
    * @roseuid 473B887E0371
    */
   public ResolveNonComplianceDAO() 
   {

   }
   
   /**
    * @param event
    * @roseuid 473B75560233
    */
   public void execute(Object aEvent) 
   {   
   	   ResolveNonComplianceEvent sEvent = (ResolveNonComplianceEvent) aEvent; 
   	   Enumeration requests = sEvent.getRequests();
   	   
   	   UpdateCasenoteEvent uEvent = null;
   	   List orderConditions = new ArrayList();
   	   while (requests.hasMoreElements()){
	   	   Object obj = requests.nextElement();
	   	   if(obj instanceof UpdateCasenoteEvent){
	   	       uEvent = (UpdateCasenoteEvent) obj;
	   	   }else if(obj instanceof UpdateOrderConditionEvent){
	   	       orderConditions.add(obj);   
	   	   }
	   }
   	   
       // insert casenote
   	   Casenote casenote = null;
   	   int casenoteId = 0;
   	   if(uEvent != null){
   	       SupervisionPeriod sp = getActiveSupervisionPeriod(uEvent.getAgencyId(), uEvent.getSuperviseeId());
   	       if(sp != null){
	           uEvent.setSupervisionPeriodId(sp.getOID());
	       }else{
	       	   ReturnException re = new ReturnException("Supervision Period is missing. Please contact with the administrator.");
   	       	   EventManager.getSharedInstance(EventManager.REPLY).postEvent(re);
   	       	   return;
   	       }
   	       
	       uEvent.setHowGeneratedId(SupervisionOrderConditionConstants.SYSTEMGENERATED);
 	       uEvent.setContextType(SupervisionOrderConditionConstants.CONTEXTTYPE);
 	       if(uEvent.isSaveAsDraft()){
 	           uEvent.setCasenoteStatusId(SupervisionOrderConditionConstants.DRAFT);
 	       }else{
 	    	  uEvent.setCasenoteStatusId(SupervisionOrderConditionConstants.COMPLETE);
 	       }
 	       uEvent.setContextType(SupervisionOrderConditionConstants.CONTEXTTYPE);
 	       
           casenote = new Casenote();
           casenote.updateCasenote(uEvent);
           casenoteId = Integer.parseInt(casenote.getOID());
   	   }
   	   
       // insert casenote conditions
   	   for(int i=0; i < orderConditions.size(); i++){
   	       UpdateOrderConditionEvent ocEvent = (UpdateOrderConditionEvent) orderConditions.get(i);
   	   	   String complianceReasonCodeId = ocEvent.getComplianceReasonCodeId();
   	   	   int sprOrderConditionId = ocEvent.getSprOrderConditionId();
   	       CasenoteConditions cc = new CasenoteConditions();
   	       cc.setSprOrderConditionId(sprOrderConditionId);
   	       cc.setCasenoteId(casenoteId);   	
   	       String caseNumber = removeSpacefromCaseNumber(ocEvent.getCaseNumber());
	       cc.setCaseNumber(caseNumber);
   	       cc.setConditionId(ocEvent.getConditionId());
   	       cc.setOrderChainNumber(ocEvent.getOrderChainNum());   	      
   	       
   	       SupervisionOrderConditionRel rel = getSprOrderCondition(sprOrderConditionId);
   	       if(rel != null){
 	           rel.setNonCompliant(false);
 	           rel.setComplianceReasonId(ocEvent.getComplianceReasonCodeId());
 	           if(SupervisionOrderConditionConstants.VALID_EXCUSE.equalsIgnoreCase(complianceReasonCodeId)){
 	               int ncCount = rel.getNcCount();
		   	   	   if(ncCount > 0){
		   	   	   	   rel.setNcCount(ncCount -1);
		   	   	   }
		   	   	   
		   	   	   GetLatestNonComplianceEventEvent glncEvent = new GetLatestNonComplianceEventEvent();
		   	   	   glncEvent.setSprOrderConditionId(ocEvent.getSprOrderConditionId());
		   	   	   Iterator ncIterator = NonComplianceEvent.findAll(glncEvent);
		   	   	   NonComplianceEvent ncEvent = null;
		   	   	   while(ncIterator.hasNext()){
		    	   	   ncEvent = (NonComplianceEvent) ncIterator.next();  
		    	   }
		   	   	   
		   	   	   if(ncEvent != null){
		 	   	       Iterator iterator = NonComplianceEventType.findAllByNumericParam(SupervisionOrderConditionConstants.NONCOMPLIANCE_EVENT_ID, ncEvent.getNonComplianceEventId());
		  	           while(iterator.hasNext()){
		 		           NonComplianceEventType ncet = (NonComplianceEventType) iterator.next();
		 		           if(ncet != null){
		 			           ncet.delete();
		 		           }
		 	           }
			  	   	   		  	           
		  	           //deleteStandAloneCasenote(ncEvent.getOID(), ocEvent.getSprOrderConditionId());
     		 	   	   ncEvent.delete();
		   	   	   }
	  	       }
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
