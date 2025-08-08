//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\casefile\\transactions\\UpdateCasefileClosingCommand.java

package pd.juvenilecase.casefile.transactions;

import java.util.Iterator;

import messaging.casefile.UpdateResidentialExitPlanEvent;
import messaging.casefile.UpdateResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.casefile.CasefileClosingInfo;
import pd.juvenilecase.casefile.Response;

/**
 * @author mchowdhury
*/

public class UpdateResidentialExitPlanCommand implements ICommand 
{
   
   /**
    * @roseuid 439602E60046
    */
   public UpdateResidentialExitPlanCommand() 
   {
    
    
   }
   
   /**
    * @param event
    * @roseuid 4395C2370063
    */
   public void execute(IEvent event) 
   {
	   UpdateResidentialExitPlanEvent updateEvent = (UpdateResidentialExitPlanEvent) event;
       CasefileClosingInfo casefile = CasefileClosingInfo.find(updateEvent.getCasefileClosingInfoId());
		
		if(casefile != null){
	   	    casefile.setResidentialExitPlanDetails(updateEvent);
		}
		// update question answer
		this.updateQuestionAnswers(updateEvent,casefile);
	}

	/**
	 * @param updateEvent
	 * @param casefile
	 */
	private void updateQuestionAnswers(UpdateResidentialExitPlanEvent updateEvent, CasefileClosingInfo casefile)
	{
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
