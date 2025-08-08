//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\casefile\\transactions\\GetCasefileClosingDetailsCommand.java

package pd.juvenilecase.casefile.transactions;

import java.util.Calendar;
import java.util.Iterator;

import messaging.casefile.GetCasefileClosingDetailsEvent;
import messaging.casefile.SaveCasefileClosingProbDatesFlagEvent;
import mojo.km.context.ICommand;
import mojo.km.context.multidatasource.Home;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.AttributeEvent;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import naming.PDJuvenileCaseConstants;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.casefile.CasefileClosingInfo;
import pd.juvenilecase.casefile.PDCasefileClosingHelper;
import pd.juvenilecase.casefile.Response;
import pd.security.PDSecurityHelper;

/**
 * @author anpillai
*/


public class SaveCasefileClosingProbDatesFlagCommand implements ICommand 
{
   
   /**
    * @roseuid 439602E4019E
    */
   public SaveCasefileClosingProbDatesFlagCommand() 
   {
      
   }
   
   /**
    * @param event
    * @roseuid 4395C235016E
    */
   public void execute(IEvent event) 
   {
       SaveCasefileClosingProbDatesFlagEvent casefileEvent = (SaveCasefileClosingProbDatesFlagEvent) event;
	   
       //JuvenileCasefile casefileClosing = this.getCasefileClosing(casefileEvent); 
       JuvenileCasefile casefileClosing = JuvenileCasefile.find(casefileEvent.getSupervisionNumber());
	
		//modify to get from jccasefile and remove new attribute from T_JCCASFILECLOSNG and mapping
	   if (casefileClosing != null)
		{
	       	    casefileClosing.setProbationFlag("Y");
	       	    /*casefileClosing.setLcDate(DateUtil.getCurrentDate());
	       	    casefileClosing.setLcTime(Calendar.getInstance().getTime());
	       	    casefileClosing.setLcUser(PDSecurityHelper.getLogonId());*/
		    IHome home = new Home();
		    home.bind(casefileClosing);
		}
	   
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
