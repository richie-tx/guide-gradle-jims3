//Source file: C:\\views\\dev\\app\\src\\pd\\juvenile\\transactions\\SaveJuvenileContactCommand.java

//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\SaveJuvenileContactsCommand.java

package pd.juvenile.transactions;

import messaging.juvenile.SaveJuvenileContactEvent;
import messaging.juvenile.reply.JuvenileContactResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.juvenile.JuvenileContact;
import pd.juvenile.JuvenileHelper;

public class SaveJuvenileContactCommand implements ICommand 
{
   
   /**
    * @roseuid 42B18DC702BF
    */
   public SaveJuvenileContactCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42B1830801D6
    */
   public void execute(IEvent event) 
   {
   		SaveJuvenileContactEvent myEvent=(SaveJuvenileContactEvent)event;
   		JuvenileContact contact;
   		if(myEvent.getContactNum()==null || myEvent.getContactNum().equals("")){
			contact=JuvenileContact.create(myEvent);   	
			Home home=new Home();
			home.bind(contact);
   		}
   		else{
   			contact=JuvenileContact.find(myEvent.getContactNum());
   			if(contact!=null){
   				JuvenileContact.update(myEvent,contact);
   			}
   		}
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if(contact!=null){
			JuvenileContactResponseEvent contactRespEvent = JuvenileHelper.getJuvenileContactResponseEvent(contact);
			if (contactRespEvent != null)
			{
				dispatch.postEvent(contactRespEvent);
			}
		}
   }
   
   /**
    * @param event
    * @roseuid 42B1830801D8
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42B1830801DA
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42B1830801E4
    */
   public void update(Object anObject) 
   {
    
   }
   
}
