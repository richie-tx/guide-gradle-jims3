/*
 * Created on Oct 15, 2007
 *
 */
package pd.juvenilecase.casefile.transactions;

import messaging.casefile.SaveJournalSummaryDocEvent;
import messaging.casefile.reply.CommonAppDocResponseEvent;
import messaging.casefile.reply.JournalDocResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.casefile.CommonAppDocMetadata;
import pd.juvenilecase.casefile.JournalDocMetadata;

/**
 * @author rcarter
 *
 */
public class SaveJournalSummaryDocCommand implements ICommand {
   
   /**
    * 
    */
   public SaveJournalSummaryDocCommand() 
   {
    
   }
   
   /**
    * 
    */
   public void execute(IEvent event) 
   {
	    SaveJournalSummaryDocEvent saveEvt = (SaveJournalSummaryDocEvent)event;
	    JournalDocMetadata myEntity=new JournalDocMetadata();
   		myEntity.setCasefileId(saveEvt.getCasefileId());
   		myEntity.setDocument(saveEvt.getDocument());
   		myEntity.setDocTypeCd(saveEvt.getDocTypeCd());
   		IHome home=new Home();
   		home.bind(myEntity);
   		JournalDocResponseEvent myRespEvt=myEntity.getRespEvt();
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
