//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\casefile\\transactions\\GetCasefileClosingDetailsCommand.java

package pd.juvenilecase.casefile.transactions;

import java.util.Iterator;

import messaging.casefile.GetCasefileClosingDetailsEvent;
import messaging.casefile.GetResponseEvent;
import mojo.km.config.AppProperties;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.AttributeEvent;
import naming.PDJuvenileCaseConstants;
import pd.juvenilecase.casefile.CasefileClosingInfo;
import pd.juvenilecase.casefile.PDCasefileClosingHelper;
import pd.juvenilecase.casefile.Response;
import pd.juvenilecase.casefile.ResponseGenerator;

/**
 * @author mchowdhury
*/


public class GetResponseCommand implements ICommand 
{
   
   /**
    * @roseuid 439602E4019E
    */
   public GetResponseCommand() 
   {
      
   }
   
   /**
    * @param event
    * @roseuid 4395C235016E
    */
   public void execute(IEvent event) 
   {
	   GetResponseEvent responseEvent = (GetResponseEvent) event;
       Iterator iter = Response.findAll(responseEvent);
       ResponseGenerator gen = new ResponseGenerator();
       gen.postGenaratedResponse(iter,responseEvent);
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
