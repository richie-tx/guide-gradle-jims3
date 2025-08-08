//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\GetMentalHealthAchievementResultsCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import java.util.Iterator;

import messaging.mentalhealth.GetMentalHealthAchievementResultsEvent;
import messaging.mentalhealth.reply.ATTestResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.JuvenileAchievementResults;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetMentalHealthAchievementResultsCommand implements ICommand 
{
   
   /**
    * @roseuid 45D4AD0C000A
    */
   public GetMentalHealthAchievementResultsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45D36FDA02DE
    */
   public void execute(IEvent event) 
   {
   	GetMentalHealthAchievementResultsEvent achEvent = (GetMentalHealthAchievementResultsEvent)event;
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   	Iterator achIterator =	JuvenileAchievementResults.findAll(achEvent);
   	while (achIterator.hasNext()) {
   		JuvenileAchievementResults juvenileAchievementResults = (JuvenileAchievementResults) achIterator.next();
		ATTestResponseEvent attRespEvent = getATTestResponseEvent(juvenileAchievementResults);
		dispatch.postEvent(attRespEvent);
	}
   }
   
   /**
 * @param juvenileAchievementResults
 * @return
 */
private ATTestResponseEvent getATTestResponseEvent(JuvenileAchievementResults juvenileAchievementResults) {
	ATTestResponseEvent attRespEvent = new ATTestResponseEvent();
	attRespEvent.setTestDate(juvenileAchievementResults.getTestDate());
	attRespEvent.setProgramReferralNum(juvenileAchievementResults.getProgramReferralNum());
	attRespEvent.setServiceProviderName(juvenileAchievementResults.getServiceProviderName());
	attRespEvent.setInstructorName(juvenileAchievementResults.getInstrLastName()+", "+
			juvenileAchievementResults.getInstrFirstName()+" "+juvenileAchievementResults.getInstrMiddleName());
	attRespEvent.setTestname(juvenileAchievementResults.getTestNameId());
	attRespEvent.setTestSessId(juvenileAchievementResults.getTestSessId());
	attRespEvent.setTestId(juvenileAchievementResults.getTestId());
	attRespEvent.setServiceEventId(juvenileAchievementResults.getServiceEventId());
	return attRespEvent;
}

/**
    * @param event
    * @roseuid 45D36FDA02E0
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45D36FDA02EC
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45D36FDA02EE
    */
   public void update(Object anObject) 
   {
    
   }
   
 
}
