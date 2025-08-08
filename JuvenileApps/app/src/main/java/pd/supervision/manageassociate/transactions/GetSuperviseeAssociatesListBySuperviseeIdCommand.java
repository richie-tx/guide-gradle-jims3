//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\manageassociate\\transactions\\GetAssociatesListCommand.java

package pd.supervision.manageassociate.transactions;

import java.util.Iterator;

import pd.supervision.manageassociate.SuperviseeAssociate;


import messaging.contact.to.PhoneNumberBean;
import messaging.manageassociate.GetSuperviseeAssociatesListBySuperviseeIdEvent;
import messaging.manageassociate.reply.AssociateResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetSuperviseeAssociatesListBySuperviseeIdCommand implements ICommand 
{
   
   /**
    * @roseuid 45E5E832005D
    */
   public GetSuperviseeAssociatesListBySuperviseeIdCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DCB3A400B0
    */
   public void execute(IEvent event) 
   {
   		GetSuperviseeAssociatesListBySuperviseeIdEvent reqEvent = (GetSuperviseeAssociatesListBySuperviseeIdEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		Iterator iter = SuperviseeAssociate.findAll(reqEvent);
		while(iter.hasNext()){
			SuperviseeAssociate associate = (SuperviseeAssociate)iter.next();
			
			AssociateResponseEvent resp = new AssociateResponseEvent();
			resp.setAssociateId(associate.getAssociateId());
	//		resp.setAssocName(new Name(associate.getFirstName(), associate.getMiddleName(), associate.getLastName()));
			resp.setAssocFirstName(associate.getFirstName());
			resp.setAssocMiddleName(associate.getMiddleName());
			resp.setAssocLastName(associate.getLastName());
			resp.setAssocFormattedName(resp.getFormattedName());
			resp.setRelationshipTypeId(associate.getRelationshipTypeId());
			//resp.setRelationship(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RELATIONSHIP_JUVENILE, associate.getRelationshipTypeId()));
	//		resp.setRelationship(PDCodeTableHelper.getCode(PDCodeTableConstants.RELATIONSHIP_JUVENILE, associate.getRelationshipTypeId()).getCode());
	//		resp.setDisplayLabel(resp.getFormattedName() + " | " + resp.getRelationship());
			resp.setStatus(associate.getStatus());
			resp.setHomePhone(new PhoneNumberBean(associate.getHomePhone()));
			resp.setWorkPhone(new PhoneNumberBean(associate.getWorkPhone()));
			resp.setCellPhone(new PhoneNumberBean(associate.getCellPhone()));
			
			dispatch.postEvent(resp);
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
