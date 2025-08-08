//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\GetComplianceConditionsCommand.java

package pd.supervision.supervisionorder.transactions;

import java.util.Iterator;

import messaging.administercompliance.reply.ComplianceConditionResponseEvent;
import messaging.supervisionorder.GetSuperviseeComplianceIndicatorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.supervisionorder.SupervisionOrderConditionView;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetSuperviseeComplianceIndicatorCommand implements ICommand 
{
   
   /**
    * @roseuid 473B887D0238
    */
   public GetSuperviseeComplianceIndicatorCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 473B7555012A
    */
   public void execute(IEvent event) 
   {
   	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   	   GetSuperviseeComplianceIndicatorEvent gEvent = (GetSuperviseeComplianceIndicatorEvent) event;
	   
   	   Iterator iterator = SupervisionOrderConditionView.findAll(gEvent);
       if(iterator.hasNext()){
   	       SupervisionOrderConditionView view = (SupervisionOrderConditionView) iterator.next();
   	       ComplianceConditionResponseEvent resp = view.getResonseEvent();
   	       MessageUtil.postReply(resp);
   	   }
     }
   
   /**
    * @param event
    * @roseuid 473B7555012C
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 473B75550138
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 473B7555013A
    */
   public void update(Object anObject) 
   {
    
   }
}
