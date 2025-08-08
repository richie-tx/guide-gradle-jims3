//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\CheckResidentialPreConditionsCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import messaging.juvenilecase.reply.CheckRiskAnalysisOneHourResponseEvent;
import messaging.riskanalysis.CheckRiskAnalysisOneHourEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.util.MessageUtil;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;

public class CheckRiskAnalysisOneHourCommand implements ICommand 
{
   
   /**
    * @roseuid 4357DD180205
    */
   public CheckRiskAnalysisOneHourCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4357D9AF0229
    */
   public void execute(IEvent event) 
   {
	   	CheckRiskAnalysisOneHourEvent preCondEvent = (CheckRiskAnalysisOneHourEvent) event;
		JuvenileCasefile juvCaseFile = JuvenileCasefile.find(preCondEvent.getCasefileID());
				
		boolean riskAnalysisExist = PDRiskAnalysisHelper.doesRiskAnalysisExistWithinTheHour(juvCaseFile.getOID(), preCondEvent.getAssessmentType());
		CheckRiskAnalysisOneHourResponseEvent preCondFailedEvent = new CheckRiskAnalysisOneHourResponseEvent();
		preCondFailedEvent.setMessage(riskAnalysisExist);
		MessageUtil.postReply(preCondFailedEvent);
   }
   
   /**
    * @param event
    * @roseuid 4357D9AF022B
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4357D9AF022D
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 4357D9AF0233
    */
   public void update(Object anObject) 
   {
    
   }
}
