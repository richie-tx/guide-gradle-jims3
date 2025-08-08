//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\GetMentalHealthAFResultsCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import java.util.Iterator;

import messaging.mentalhealth.GetMentalHealthAFResultsEvent;
import messaging.mentalhealth.reply.AFTestResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileAdaptiveFunctioningResults;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetMentalHealthAFResultsCommand implements ICommand 
{
   
   /**
    * @roseuid 45D4AD0D0190
    */
   public GetMentalHealthAFResultsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45D36FDB0211
    */
   public void execute(IEvent event) 
   {
   	GetMentalHealthAFResultsEvent afEvent = (GetMentalHealthAFResultsEvent)event;
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator dsmIterator = JuvenileAdaptiveFunctioningResults.findAll(afEvent);
	while (dsmIterator.hasNext()) {
		JuvenileAdaptiveFunctioningResults juvenileAFResults = (JuvenileAdaptiveFunctioningResults) dsmIterator.next();
		AFTestResponseEvent afRespEvent = getAFTestResponseEvent(juvenileAFResults);
		dispatch.postEvent(afRespEvent);
	}
   	
   }
   
   /**
 * @param juvenileAFResults
 * @return
 */
private AFTestResponseEvent getAFTestResponseEvent(JuvenileAdaptiveFunctioningResults juvenileAFResults) {
	AFTestResponseEvent afRespEvent = new AFTestResponseEvent();
	afRespEvent.setTestDate(juvenileAFResults.getTestDate());
	afRespEvent.setTestId(juvenileAFResults.getTestId());
	afRespEvent.setTestSessId(juvenileAFResults.getTestSessId());
	afRespEvent.setProgramReferralNum(juvenileAFResults.getProgramReferralNum());
	afRespEvent.setServiceProviderName(juvenileAFResults.getServiceProviderName());
	afRespEvent.setInstructorName(juvenileAFResults.getInstrLastName()+", "+
		juvenileAFResults.getInstrFirstName()+" "+juvenileAFResults.getInstrMiddleName());
	afRespEvent.setTestName(juvenileAFResults.getTestNameId());
	afRespEvent.setStandardScore(juvenileAFResults.getStandardScore());
	afRespEvent.setServiceEventId(juvenileAFResults.getServiceEventId());
	return afRespEvent;
}

/**
    * @param event
    * @roseuid 45D36FDB0213
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45D36FDB0215
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45D36FDB0217
    */
   public void update(Object anObject) 
   {
    
   }
   
  
}
