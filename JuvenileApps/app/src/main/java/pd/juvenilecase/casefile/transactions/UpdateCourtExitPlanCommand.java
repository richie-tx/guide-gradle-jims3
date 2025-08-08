//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\casefile\\transactions\\UpdateCasefileClosingCommand.java

package pd.juvenilecase.casefile.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.casefile.UpdateCourtExitPlanEvent;
import messaging.casefile.UpdateResponseEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.casefile.CasefileClosingInfo;
import pd.juvenilecase.casefile.Response;

/**
 * @author mchowdhury
*/

public class UpdateCourtExitPlanCommand implements ICommand 
{
   
   /**
    * @roseuid 439602E60046
    */
   public UpdateCourtExitPlanCommand() 
   {
    
    
   }
   
   /**
    * @param event
    * @roseuid 4395C2370063
    */
   public void execute(IEvent event) 
   {
	   UpdateCourtExitPlanEvent updateEvent = (UpdateCourtExitPlanEvent) event;
		CasefileClosingInfo casefile = null;
		CasefileClosingResponseEvent response = new CasefileClosingResponseEvent();
	   if(updateEvent.getCasefileClosingInfoId()!=null && !updateEvent.getCasefileClosingInfoId().equals(""))
	   		casefile = CasefileClosingInfo.find(updateEvent.getCasefileClosingInfoId());
		
	   if(casefile != null){
		   casefile.setCourtExitPlanDetails(updateEvent);
	   }
	   else
	   {
		   if(updateEvent.getPrevCasefileClosingInfoId()==null || updateEvent.getPrevCasefileClosingInfoId().isEmpty()){
	   		casefile = new CasefileClosingInfo();
	   		casefile.setClosingEvaluation("");
	   		casefile.setSupervisionOutcomeId("");
	   		casefile.setSupervisionNumber(updateEvent.getSupervisionNum());
	   		casefile.setControllingReferralId( updateEvent.getControllingReferralId() );
	   		//casefile.setSupervisionEndDate(new Date()); bug fix 26708
	   		casefile.setExitPlanTemplateLocation(updateEvent.getExitPlanTemplateLocation());
	   		casefile.createOID();
			response.setCasefileClosingInfoId(casefile.getOID());
		   }else{
			   response.setCasefileClosingInfoId(updateEvent.getPrevCasefileClosingInfoId());
		   }
	   }
			// update question answer
	   	if(casefile != null)
	   		this.updateQuestionAnswers(updateEvent,casefile);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(response);
		
	}

	/**
	 * @param updateEvent
	 * @param casefile
	 */
	private void updateQuestionAnswers(UpdateCourtExitPlanEvent updateEvent, CasefileClosingInfo casefile)
	{
		if(updateEvent.getResponses() == null)
		    return;
		Iterator iter = updateEvent.getResponses().iterator();
		UpdateResponseEvent updateResponseEvent = null;
		while(iter.hasNext()){
			Response response = null;
			updateResponseEvent = (UpdateResponseEvent) iter.next();
			if(updateResponseEvent.getResponseId() != null){
				response = Response.find(updateResponseEvent.getResponseId());	   
			}
			if(response == null){
				response = new Response();
			}
			response.setResponse(updateResponseEvent);
			if(response.getOID() == null){
				casefile.insertResponses(response);
			}
		}
}

  /**
    * @param event
    * @roseuid 4395C237006C
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4395C23700B2
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 4395C23700BD
    */
   public void update(Object anObject) 
   {
    
   }

}
