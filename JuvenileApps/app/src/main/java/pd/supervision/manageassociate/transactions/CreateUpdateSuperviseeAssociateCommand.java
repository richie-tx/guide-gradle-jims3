//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\manageassociate\\transactions\\SaveAssociateCommand.java

package pd.supervision.manageassociate.transactions;

import java.util.Collection;
import java.util.Iterator;

import naming.AssociateControllerServiceNames;

import pd.supervision.administercasenotes.CasenoteSuperviseeAssociates;
import pd.supervision.manageassociate.AssociateAddress;
import pd.supervision.manageassociate.PDManageAssociateHelper;
import pd.supervision.manageassociate.SuperviseeAssociate;
import messaging.manageassociate.AssociateAddressRequestEvent;
import messaging.manageassociate.CreateUpdateSuperviseeAssociateEvent;
import messaging.manageassociate.IsAssociateReferencedEvent;
import messaging.manageassociate.reply.DeleteAssociateErrorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

public class CreateUpdateSuperviseeAssociateCommand implements ICommand 
{
   
   /**
    * @roseuid 45E5E8330000
    */
   public CreateUpdateSuperviseeAssociateCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DCB3A3009F
    */
   public void execute(IEvent event) 
   {
   		CreateUpdateSuperviseeAssociateEvent evt = (CreateUpdateSuperviseeAssociateEvent)event;
		SuperviseeAssociate associate = null;
		String associateId =  evt.getAssociateId();
		
		if(evt.isCreate()) { //	CREATE NEW SUPERVISEE ASSOCIATE
			associate = new SuperviseeAssociate();
			associate.setSuperviseeAssociate(evt);
			this.updateAddress(evt, associate);
		
		} else if (evt.isUpdate()){ //	UPDATE EXISTING SUPERVISEE ASSOCIATE
			associate = SuperviseeAssociate.find(associateId);
			
			AssociateAddress primaryAddress = AssociateAddress.find(associate.getPrimaryResidenceAddressId());
			associate.setPrimaryResidenceAddress(primaryAddress);
			
			AssociateAddress otherAddress = AssociateAddress.find(associate.getOtherAddressId());
			associate.setOtherAddress(otherAddress);
			
			associate.setSuperviseeAssociate(evt);
			this.updateAddress(evt, associate);
		
		} else if (evt.isDelete()){  //	DELETE EXISTING SUPERVISEE ASSOCIATE
		    IsAssociateReferencedEvent isAssocRefEvt = (IsAssociateReferencedEvent) EventFactory
				.getInstance(AssociateControllerServiceNames.ISASSOCIATEREFERENCED);
			isAssocRefEvt.setAssociateId(associateId);
			
			if (!CasenoteSuperviseeAssociates.isAssociateReferencedInCasenote(isAssocRefEvt)){
				associate = SuperviseeAssociate.find(associateId);
				
				AssociateAddress primaryAddress = AssociateAddress.find(associate.getPrimaryResidenceAddressId());
				AssociateAddress otherAddress = AssociateAddress.find(associate.getOtherAddressId());
				
				if (associate != null){
					associate.delete();
				}
			}
			else{
			    DeleteAssociateErrorEvent errorEvent = new DeleteAssociateErrorEvent();		
				errorEvent.setErrorMsg("Associate error message");
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
				dispatch.postEvent(errorEvent);
			}
		}
   }
   
   /**
    * @param event
    * @roseuid 45DCB3A300A1
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DCB3A300A3
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param updateObject
    * @roseuid 45E5E833001F
    */
   public void update(Object updateObject) 
   {
    
   }
   
   private void updateAddress(CreateUpdateSuperviseeAssociateEvent evt, SuperviseeAssociate assoc)
	{
		Collection assocAddressRequests = MessageUtil.compositeToCollection(evt, AssociateAddressRequestEvent.class);
		Iterator iter = assocAddressRequests.iterator();
		while(iter.hasNext()){
			AssociateAddressRequestEvent assocAddressRequestEvent = (AssociateAddressRequestEvent) iter.next();
			if(assocAddressRequestEvent.getIsPrimaryResidenceAddress()){
				AssociateAddress primaryAddress =  new AssociateAddress();
				primaryAddress = PDManageAssociateHelper.getAssociateAddress(assocAddressRequestEvent,primaryAddress);
				assoc.setPrimaryResidenceAddress(primaryAddress);
			}else {
				AssociateAddress otherAddress = new AssociateAddress();
				otherAddress = PDManageAssociateHelper.getAssociateAddress(assocAddressRequestEvent,otherAddress);
				assoc.setOtherAddress(otherAddress);
			}
		}
	}
}
