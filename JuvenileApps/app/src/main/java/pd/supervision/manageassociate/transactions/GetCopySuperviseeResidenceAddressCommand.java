//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\manageassociate\\transactions\\GetAssociatesListCommand.java

package pd.supervision.manageassociate.transactions;

import pd.contact.party.Party;
import pd.supervision.manageassociate.PDManageAssociateHelper;

import messaging.domintf.contact.party.IParty;
import messaging.manageassociate.GetCopySuperviseeResidenceAddressEvent;
import messaging.party.GetPartyDataEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;



public class GetCopySuperviseeResidenceAddressCommand implements ICommand 
{
   
   /**
    * @roseuid 45E5E832005D
    */
   public GetCopySuperviseeResidenceAddressCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DCB3A400B0
    */
   public void execute(IEvent event) 
   {
   		GetCopySuperviseeResidenceAddressEvent reqEvent = (GetCopySuperviseeResidenceAddressEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
   		GetPartyDataEvent partyEvent = new GetPartyDataEvent();
   		partyEvent.setSpn(reqEvent.getSpn());
   				
   		Party party = null;
		
		// Use the SPN to look the party up 
		if ((partyEvent.getSpn() != null) && (!partyEvent.getSpn().equals("")))
		{
			party = Party.find(partyEvent);
		}
		
		if (party != null)
		{
			IParty partyInfo = PDManageAssociateHelper.getPartyResponseEvent(party);
			dispatch.postEvent((IEvent)partyInfo);
		}
   }
   
   /**
    * @param event
    * @roseuid 45DCB3A400BE
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DCB3A400C0
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
    /**
    * @param updateObject
    * @roseuid 45E5E832007D
    */
   public void update(Object updateObject) 
   {
    
   }
}