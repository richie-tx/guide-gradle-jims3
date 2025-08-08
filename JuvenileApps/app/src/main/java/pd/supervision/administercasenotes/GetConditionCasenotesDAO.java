//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\GetNonCompliantEventsCommand.java

package pd.supervision.administercasenotes;

import java.util.Iterator;

import messaging.administercasenotes.GetConditionCasenotesEvent;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import naming.SupervisionOrderConditionConstants;
import pd.common.DAOHandler;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetConditionCasenotesDAO implements DAOHandler
{
   
   /**
    * @roseuid 473B887E0371
    */
   public GetConditionCasenotesDAO() 
   {

   }
   
   /**
    * @param event
    * @roseuid 473B75560233
    */
   public void execute(Object aEvent) 
   {   
   	   GetConditionCasenotesEvent gEvent = (GetConditionCasenotesEvent) aEvent;
   	   CasenoteHelper helper = CasenoteHelper.getInstance();
   	
   	   Iterator ccIter = CasenoteConditions.findAllByNumericParam(SupervisionOrderConditionConstants.SUPERVISION_ORDER_CONDITION_ID, gEvent.getSprOrderConditionId());
	   while(ccIter.hasNext()){
	   	   CasenoteConditions cc = (CasenoteConditions) ccIter.next(); 
	   	   if(cc != null){
	   	   	   Casenote c = Casenote.find(new StringBuffer("").append(cc.getCasenoteId()).toString());
	   	   	   if(c != null){
	   	   	       CasenoteResponseEvent resp = helper.getCasenoteResponse(c);	
	   	   	       helper.postCasenoteResponse(resp);
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
