//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\GetNonCompliantEventsCommand.java

package pd.supervision.administercompliance.transactions.daos;

import java.util.Iterator;

import messaging.supervisionorder.GetActiveSupervisionPeriodEvent;
import messaging.supervisionorder.GetSprOrderConditionRelByOrderConditionIdEvent;
import naming.SupervisionOrderConditionConstants;
import pd.supervision.administercasenotes.Casenote;
import pd.supervision.administercasenotes.CasenoteConditions;
import pd.supervision.administercasenotes.CasenoteSubjects;
import pd.supervision.administercompliance.NonComplianceEventCasenote;
import pd.supervision.supervisionorder.SupervisionOrderConditionRel;
import pd.supervision.supervisionorder.SupervisionPeriod;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ComplianceDAO{
   
   /**
    * @roseuid 473B887E0371
    */
   public ComplianceDAO() 
   {

   }
   
   protected SupervisionOrderConditionRel getSprOrderCondition(int sprOrderConditionId){
   	   GetSprOrderConditionRelByOrderConditionIdEvent gEvent = new GetSprOrderConditionRelByOrderConditionIdEvent();
   	   gEvent.setSprOrderConditionId(sprOrderConditionId);
 	   Iterator rels = SupervisionOrderConditionRel.findAll(gEvent);
 	   SupervisionOrderConditionRel rel = null;
 	   while(rels.hasNext()){
 	       rel = (SupervisionOrderConditionRel) rels.next();
 	   }
 	   return rel;
   }

	/**
	 * @param nonComplianceEventId
	 * @param sprOrderConditionId
	 */
	protected Casenote deleteStandAloneCasenote(String nonComplianceEventId, int sprOrderConditionId) {
	    Iterator nCasenoteIterator = NonComplianceEventCasenote.findAllByNumericParam(SupervisionOrderConditionConstants.NONCOMPLIANCE_EVENT_ID,nonComplianceEventId);
		NonComplianceEventCasenote nc = null;
	    while(nCasenoteIterator.hasNext()){
	        nc = (NonComplianceEventCasenote) nCasenoteIterator.next();
	    }
	    Casenote cnote = null;   
		if(nc != null){
		    cnote = Casenote.find(new StringBuffer("").append(nc.getCasenoteId()).toString());
		    if(cnote != null){
		        Iterator casenoteIterator = cnote.getConditions().iterator();
		        int j = 0;
		   	    CasenoteConditions cn = null;
		   	    while(casenoteIterator.hasNext()){
		           cn = (CasenoteConditions) casenoteIterator.next();
		           if(sprOrderConditionId == cn.getSprOrderConditionId()){
		           	   cn.delete();
		           }				    
		           j++;
		        }
		         
		 	    if(j==1 && (cn.getSprOrderConditionId() == sprOrderConditionId)){
			        Iterator subIterator = cnote.getSubjects().iterator();
				    while(subIterator.hasNext()){
				        CasenoteSubjects subject = (CasenoteSubjects) subIterator.next();
				        subject.delete();
				    }		        
				    nc.delete();
				    cnote.delete();
			    } 
	        }
		}
		return cnote;
   }
   
	/**
	 * @return
	 */
	public SupervisionPeriod getActiveSupervisionPeriod(String agencyId, String superviseeId) {
		GetActiveSupervisionPeriodEvent aspEvent = new GetActiveSupervisionPeriodEvent();
	    aspEvent.setAgencyId(agencyId);
	    aspEvent.setSpn(superviseeId);
	    Iterator spIter = SupervisionPeriod.findAll(aspEvent);
	    SupervisionPeriod sp = null;
	    while(spIter.hasNext()){
	        sp = (SupervisionPeriod) spIter.next();	   
	    }
	    return sp;
    }
}
