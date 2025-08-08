/*
 * Created on Oct 15, 2007
 *
 */
package pd.juvenilecase.casefile.transactions;

import messaging.casefile.SaveCommonAppDocEvent;
import messaging.casefile.reply.CommonAppDocResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.casefile.CommonAppDocMetadata;

/**
 * @author awidjaja
 *
 */
public class SaveCommonAppDocCommand implements ICommand {
   
   /**
    * 
    */
   public SaveCommonAppDocCommand() 
   {
    
   }
   
   /**
    * 
    */
   public void execute(IEvent event) 
   {
   		SaveCommonAppDocEvent saveEvt = (SaveCommonAppDocEvent)event;
   		CommonAppDocMetadata myEntity=new CommonAppDocMetadata();
   		myEntity.setCasefileId(saveEvt.getCasefileId());
   		myEntity.setDocument(saveEvt.getDocument());
   		myEntity.setDocTypeCd(saveEvt.getDocTypeCd());
   		IHome home=new Home();
   		home.bind(myEntity);
   		CommonAppDocResponseEvent myRespEvt=myEntity.getRespEvt();
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		dispatch.postEvent(myRespEvt);
   }
   
   /**
    * 
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * 
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * 
    */
   public void update(Object anObject) 
   {
    
   }
   

}
