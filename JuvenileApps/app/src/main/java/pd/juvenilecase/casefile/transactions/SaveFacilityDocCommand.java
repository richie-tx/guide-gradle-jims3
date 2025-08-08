/*
 * Created on July 19, 2016
 *
 */
package pd.juvenilecase.casefile.transactions;

import messaging.casefile.SaveCommonAppDocEvent;
import messaging.casefile.SaveFacilityDocEvent;
import messaging.casefile.reply.CasefileDocumentsResponseEvent;
import messaging.casefile.reply.CommonAppDocResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.casefile.CommonAppDocMetadata;
import pd.juvenilecase.casefile.FacilityDocMetadata;

/**
 * @author ugopinath
 *
 */
public class SaveFacilityDocCommand implements ICommand {
   
   /**
    * 
    */
   public SaveFacilityDocCommand() 
   {
    
   }
   
   /**
    * 
    */
   public void execute(IEvent event) 
   {
	   SaveFacilityDocEvent saveEvt = (SaveFacilityDocEvent)event;
	   FacilityDocMetadata myEntity=new FacilityDocMetadata();
   		myEntity.setCasefileId(saveEvt.getCasefileId());
   		myEntity.setDocument(saveEvt.getDocument());
   		myEntity.setDocTypeCd(saveEvt.getDocTypeCd());
   		IHome home=new Home();
   		home.bind(myEntity);
   				
   		CasefileDocumentsResponseEvent myRespEvt=new CasefileDocumentsResponseEvent();
   		myRespEvt.setDocumentTypeCd(myEntity.getDocTypeCd());
   		myRespEvt.setCreateDate(myEntity.getCreationDate());
   		myRespEvt.setReport(myEntity.getDocument());
   		
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
