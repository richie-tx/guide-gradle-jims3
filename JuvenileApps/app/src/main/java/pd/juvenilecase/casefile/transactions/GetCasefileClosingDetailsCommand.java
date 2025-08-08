//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\casefile\\transactions\\GetCasefileClosingDetailsCommand.java

package pd.juvenilecase.casefile.transactions;

import java.util.Iterator;

import messaging.casefile.GetCasefileClosingDetailsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.AttributeEvent;
import naming.PDJuvenileCaseConstants;
import pd.juvenilecase.casefile.CasefileClosingInfo;
import pd.juvenilecase.casefile.PDCasefileClosingHelper;
import pd.juvenilecase.casefile.Response;

/**
 * @author mchowdhury
*/


public class GetCasefileClosingDetailsCommand implements ICommand 
{
   
   /**
    * @roseuid 439602E4019E
    */
   public GetCasefileClosingDetailsCommand() 
   {
      
   }
   
   /**
    * @param event
    * @roseuid 4395C235016E
    */
   public void execute(IEvent event) 
   {
	   GetCasefileClosingDetailsEvent casefileEvent = (GetCasefileClosingDetailsEvent) event;
	   
	   CasefileClosingInfo casefileClosing = this.getCasefileClosing(casefileEvent); 
	
		// post CasefileClosingInfo
		PDCasefileClosingHelper.postCasefileClosingDetails(casefileClosing);
   }
   
   /**
	 * @param casefileEvent
	 * @return
	 */
	private CasefileClosingInfo getCasefileClosing(GetCasefileClosingDetailsEvent casefileEvent)
	{
		AttributeEvent attEvent = new AttributeEvent();
		attEvent.setAttributeName(PDJuvenileCaseConstants.SUPERVISION_NUMBER);
		attEvent.setAttributeValue(casefileEvent.getSupervisionNumber());
		Iterator iter = CasefileClosingInfo.findAll(attEvent);
		CasefileClosingInfo casefileClosing = null;
		while(iter.hasNext()){
			casefileClosing = (CasefileClosingInfo) iter.next();
		}
        return casefileClosing;
	}

/**
    * @param event
    * @roseuid 4395C23501A0
    */
   public void onRegister(IEvent event) 
   {
     
   }
   
   /**
    * @param event
    * @roseuid 4395C23501E6
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 4395BC250375
    */
   public void update(Object anObject) 
   {
    
   }
}
