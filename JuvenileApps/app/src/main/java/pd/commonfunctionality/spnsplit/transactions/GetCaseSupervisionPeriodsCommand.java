//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\commonfunctionality\\spnsplit\\transactions\\GetCaseSupervisionPeriodsCommand.java

package pd.commonfunctionality.spnsplit.transactions;


import messaging.spnsplit.GetCaseSupervisionPeriodsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.supervisionorder.SupervisionOrderHelper;

/**
 * @author kmurthy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCaseSupervisionPeriodsCommand implements ICommand 
{
   
   /**
    * @roseuid 4561E29D00A0
    */
   public GetCaseSupervisionPeriodsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 455E28BB03B9
    */
   public void execute(IEvent event) 
   {
   	    GetCaseSupervisionPeriodsEvent requestEvent = (GetCaseSupervisionPeriodsEvent) event;
        //this.retrievePartyInfo(requestEvent.getErroneousSpn());
        //this.retrievePartyInfo(requestEvent.getValidSpn());
  	    
        //Need to get all the Case order for the Erroneous SPN
        SupervisionOrderHelper.retrieveAllSupPeriods(requestEvent.getErroneousSpn());
   }
   
   
   /**
    * @param spn
    */
   /*public void retrievePartyInfo(String spn)
   {
       	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
       	
       	GetPartyDataEvent getPartyData = new GetPartyDataEvent();
       	
       	getPartyData.setSpn(spn);
       	getPartyData.setCurrentNameInd("Y");
       	
       	
       	Party party = Party.find(getPartyData);
       	if (party != null) {
       		PartyListResponseEvent partyInfo = PDPartyHelper.getPartyLightResponseEvent(party);
       		
       		dispatch.postEvent((IEvent) partyInfo);
       		
       	}else{
       		SpnSplitErrorResponseEvent errorEvent = new SpnSplitErrorResponseEvent();
       		errorEvent.setErroneousSpn(spn);
       		dispatch.postEvent((IEvent) errorEvent);
       	}
   }*/
   

   /**
    * @param event
    * @roseuid 455E28BB03C5
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 455E28BB03C7
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 455E28BB03C9
    */
   public void update(Object anObject) 
   {
    
   }
}
