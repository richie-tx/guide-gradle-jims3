//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\GetRiskAssessmentDetailsCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import messaging.riskanalysis.GetRiskAssessmentDetailsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;

public class GetRiskAssessmentDetailsCommand implements ICommand 
{
   
   /**
    * @roseuid 4342C3120011
    */
   public GetRiskAssessmentDetailsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 433C3D3E00FC
    */
   public void execute(IEvent event) 
   {
		GetRiskAssessmentDetailsEvent riskAssessDetails = (GetRiskAssessmentDetailsEvent)event;

		PDRiskAnalysisHelper.retrieveRiskAnalysisDetails(riskAssessDetails);

   }
   
   /**
    * @param event
    * @roseuid 433C3D3E0104
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 433C3D3E0106
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 433C3D3E0108
    */
   public void update(Object anObject) 
   {
    
   }

}
