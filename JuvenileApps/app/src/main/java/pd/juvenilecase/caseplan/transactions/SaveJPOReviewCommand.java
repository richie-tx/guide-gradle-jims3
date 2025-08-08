//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\caseplan\\transactions\\SaveJPOReviewCommand.java

package pd.juvenilecase.caseplan.transactions;

import pd.juvenilecase.caseplan.JPOReviewDocMetadata;
import messaging.caseplan.SaveJPOReviewEvent;
import messaging.caseplan.reply.JPOReviewReportResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class SaveJPOReviewCommand implements ICommand 
{
   
   /**
    * @roseuid 4533B84402A9
    */
   public SaveJPOReviewCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 452FE430025B
    */
   public void execute(IEvent event) 
   {
   		SaveJPOReviewEvent saveEvt = (SaveJPOReviewEvent)event;
   		JPOReviewDocMetadata myEntity=new JPOReviewDocMetadata();
   		myEntity.setCasePlanId(saveEvt.getCaseplanId());
   		
   		
   		myEntity.setReviewDate(saveEvt.getReviewDate());
   		myEntity.setReviewUserId(saveEvt.getReviewUserId());
   		IHome home=new Home();
   		home.bind(myEntity);
   		myEntity.setSupLevelAppro(saveEvt.isSupLevelAppro()); 
   		myEntity.setRecomSupLevelId(saveEvt.getRecomSupLevelId());
   		myEntity.setJpoMaintainContact(saveEvt.isJpoMaintainContact());
   		myEntity.setJpoMaintainExplain(saveEvt.getJpoMaintainExplain());
   		myEntity.setReviewComments(saveEvt.getReviewComments());
   		home.bind(myEntity);
   		myEntity.setReport(saveEvt.getReport());
   		home.bind(myEntity);
   		JPOReviewReportResponseEvent myRespEvt=myEntity.getRespEvt();
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		dispatch.postEvent(myRespEvt);
   }
   
   /**
    * @param event
    * @roseuid 452FE4300263
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 452FE4300265
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 452FE430026D
    */
   public void update(Object anObject) 
   {
    
   }
   

}
