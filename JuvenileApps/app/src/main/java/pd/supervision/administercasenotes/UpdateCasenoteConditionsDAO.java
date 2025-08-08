//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\GetNonCompliantEventsCommand.java

package pd.supervision.administercasenotes;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import messaging.administercasenotes.UpdateCasenoteConditionsEvent;
import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import messaging.supervisionorder.GetActiveSupervisionPeriodEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.SupervisionOrderConditionConstants;
import pd.common.DAOHandler;
import pd.supervision.supervisionorder.SupervisionPeriod;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateCasenoteConditionsDAO implements DAOHandler
{
   
   /**
    * @roseuid 473B887E0371
    */
   public UpdateCasenoteConditionsDAO() 
   {

   }
   
   /**
    * @param event
    * @roseuid 473B75560233
    */
   public void execute(Object aEvent) 
   {   
   	   UpdateCasenoteConditionsEvent event = (UpdateCasenoteConditionsEvent) aEvent;
	   Enumeration requests = event.getRequests();
	   
	   UpdateCasenoteEvent uEvent = null;
	   while (requests.hasMoreElements()){
	   	   Object obj = requests.nextElement();
	   	   if(obj instanceof UpdateCasenoteEvent){
	   	        uEvent = (UpdateCasenoteEvent) obj;
	   	   }
	   }
	   
	   if(uEvent != null){
	       SupervisionPeriod sp = this.getActiveSupervisionPeriod(uEvent.getAgencyId(), uEvent.getSuperviseeId());
   	       if(sp != null){
   	           uEvent.setSupervisionPeriodId(sp.getOID());
   	       }else{
   	       	   ReturnException re = new ReturnException("Supervision Period is missing. Please contact with the administrator.");
   	       	   EventManager.getSharedInstance(EventManager.REPLY).postEvent(re);
   	       	   return;
   	       }
   	       uEvent.setHowGeneratedId(SupervisionOrderConditionConstants.SYSTEMGENERATED);
    	   //uEvent.setContextType(SupervisionOrderConditionConstants.CONTEXTTYPE);

	   	   // First update the casenote
	   	   List casenotes = MessageUtil.postRequestListFilter(uEvent,CasenoteResponseEvent.class);
	       if(casenotes != null && !casenotes.isEmpty()){
	       	   CasenoteResponseEvent cResp = (CasenoteResponseEvent) casenotes.get(0); 
	       	   if(cResp != null){
	       	       CasenoteConditions cc = null;
	       	   	   for(int i=0;i<event.getSprOrderConditionIds().length;i++){
	       	   	       cc = new CasenoteConditions(); 
	       	   	       cc.setCasenoteId(Integer.parseInt(cResp.getCasenoteId()));
	       	   	       cc.setSprOrderConditionId(event.getSprOrderConditionIds()[i]);
	       	   	       cc.setCaseNumber(event.getCaseNumbers()[i]);
	       	   	       cc.setConditionId(event.getConditionIds()[i]);
	       	   	       cc.setOrderChainNumber(event.getOrderChainNumbers()[i]);
	       	   	   }
	       	   }
	       }else{
	       	   ReturnException re = new ReturnException("Exception occured during the saving of Casenote.");
	           EventManager.getSharedInstance(EventManager.REPLY).postEvent(re);	
	       }
	   }
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

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#remove(java.lang.Object)
	 */
	public void remove(Object object) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#cretae(java.lang.Object)
	 */
	public void cretae(Object object) {
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

	public void refresh(Object object) {
		// TODO Auto-generated method stub
		
	}
}
