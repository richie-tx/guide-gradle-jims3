//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\manageassociate\\transactions\\GetAssociateCommand.java

package pd.supervision.manageassociate.transactions;

import pd.supervision.manageassociate.PDManageAssociateHelper;
import pd.supervision.manageassociate.SuperviseeAssociate;
import messaging.manageassociate.GetSuperviseeAssociateEvent;
import messaging.manageassociate.reply.AssociateAddressResponseEvent;
import messaging.manageassociate.reply.AssociateResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetSuperviseeAssociateCommand implements ICommand 
{
   
   /**
    * @roseuid 45E5E831001F
    */
   public GetSuperviseeAssociateCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DCB3A40236
    */
   public void execute(IEvent event) 
   {
   		String associateId = ((GetSuperviseeAssociateEvent)event).getAssociateId();
		if (associateId != null && !associateId.equals(""))
		{
			GetSuperviseeAssociateEvent supAssocEvent = (GetSuperviseeAssociateEvent)event;
			SuperviseeAssociate associate = SuperviseeAssociate.find(associateId);
			AssociateResponseEvent associateResponseEvent = PDManageAssociateHelper.getAssociateResponseEvent(associate);
			
			if (associate.getPrimaryResidenceAddress()!=null){
				AssociateAddressResponseEvent assocAddrRespEvt = PDManageAssociateHelper.getAssociateAddressResponseEvent(associate.getPrimaryResidenceAddress());
				associateResponseEvent.setPrimaryAddress(assocAddrRespEvt);
			}
			
			if (associate.getOtherAddress()!=null){
				AssociateAddressResponseEvent assocAddrRespEvt = PDManageAssociateHelper.getAssociateAddressResponseEvent(associate.getOtherAddress());
				associateResponseEvent.setOtherAddress(assocAddrRespEvt);
			}
			
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(associateResponseEvent);
		}
   }
   
   /**
    * @param event
    * @roseuid 45DCB3A40238
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DCB3A4023A
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param updateObject
    * @roseuid 45E5E831002E
    */
   public void update(Object updateObject) 
   {
    
   }
}
