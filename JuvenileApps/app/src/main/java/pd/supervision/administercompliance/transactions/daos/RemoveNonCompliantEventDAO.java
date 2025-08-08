package pd.supervision.administercompliance.transactions.daos;

import java.util.Iterator;

import messaging.administercompliance.RemoveNonCompliantEventEvent;
import naming.SupervisionOrderConditionConstants;
import pd.common.DAOHandler;
import pd.supervision.administercompliance.NonComplianceEvent;
import pd.supervision.administercompliance.NonComplianceEventType;
import pd.supervision.supervisionorder.SupervisionOrderConditionRel;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * When a condition is set to non-compliant, the event(s) leading to this are
 * documented.  Event Types come from Events configured in the Condition in MSO.
 */
public class RemoveNonCompliantEventDAO extends ComplianceDAO implements DAOHandler
{
	/**
	 * 
	 * @roseuid 473B85EB0040
	 */
	public RemoveNonCompliantEventDAO()
	{
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#execute(java.lang.Object)
	 */
	public void execute(Object object) {
	    RemoveNonCompliantEventEvent uEvent = (RemoveNonCompliantEventEvent) object;
		   	   	   
	   	NonComplianceEvent nEvent = NonComplianceEvent.find(uEvent.getNonComplianceEventId());
	   	if(nEvent != null){
		    Iterator iterator = NonComplianceEventType.findAllByNumericParam(SupervisionOrderConditionConstants.NONCOMPLIANCE_EVENT_ID, uEvent.getNonComplianceEventId());
	 	    while(iterator.hasNext()){
			    NonComplianceEventType ncet = (NonComplianceEventType) iterator.next();
			    if(ncet != null){
			        ncet.delete();
			    }
	 	    }	 	       
	 	    
	 	    deleteStandAloneCasenote(uEvent.getNonComplianceEventId(), Integer.parseInt(uEvent.getSprOrderConditionId()));
	 	   	nEvent.delete();	
	 	    SupervisionOrderConditionRel rel = getSprOrderCondition(Integer.parseInt(uEvent.getSprOrderConditionId()));
   	        if(rel != null){
	 	        int ncCount = rel.getNcCount();
		   	    if(ncCount > 0){
		   	   	   rel.setNcCount(ncCount -1);
		   	   	   if(ncCount == 1){
		   	           rel.setNonCompliant(false);
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
}
