//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\commonfunctionality\\spnsplit\\transactions\\GetCaseSupervisionPeriodsCommand.java

package pd.commonfunctionality.spnsplit.transactions;


import messaging.spnsplit.UpdateSpnSplitTopicEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import naming.SpinSplitConstants;
import pd.common.CommandUtil;
import pd.common.DAOHandler;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateSpnSplitTopicCommand extends CommandUtil implements ICommand 
{
   
   /**
    * @roseuid 4561E29D00A0
    */
   public UpdateSpnSplitTopicCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 455E28BB03B9
    */
   public void execute(IEvent event) 
   {
	   UpdateSpnSplitTopicEvent sEvent = (UpdateSpnSplitTopicEvent) event;
	   DAOHandler handler = null;
	   if(SpinSplitConstants.ASSESSMENT.equalsIgnoreCase(sEvent.getTopic())){
		   handler = getHandler(SpinSplitConstants.ASSESSMENTDAOLOCATOR);
       }else if(SpinSplitConstants.ASSOCIATE.equalsIgnoreCase(sEvent.getTopic())){
		   handler = getHandler(SpinSplitConstants.ASSOCIATEDAOLOCATOR);
       }else if(SpinSplitConstants.SUPERVISION_PLAN.equalsIgnoreCase(sEvent.getTopic())){
		   handler = getHandler(SpinSplitConstants.SUPERVISIONPLANDAOLOCATOR);
       }
	   
	   if(handler == null){
  	   	   IEvent re = new ReturnException("Appropriate DAO class is absent");
  	   	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		   dispatch.postEvent(re);
	   }
	   handler.update(sEvent);
   }
   

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
