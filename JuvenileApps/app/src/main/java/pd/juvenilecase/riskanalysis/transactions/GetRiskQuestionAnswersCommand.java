//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\GetRiskQuestionAnswersCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import messaging.riskanalysis.GetRiskQuestionAnswersEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;

public class GetRiskQuestionAnswersCommand implements ICommand 
{
   
   /**
    * @roseuid 4342C31E01F9
    */
   public GetRiskQuestionAnswersCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 433C3D3D0032
    */
   public void execute(IEvent event) 
   {
		GetRiskQuestionAnswersEvent riskQuesAnsEvent = (GetRiskQuestionAnswersEvent)event;
		PDRiskAnalysisHelper.retrieveRiskQuestionsByFormulaId(riskQuesAnsEvent.getFormulaId());
   }
   
   /**
    * @param event
    * @roseuid 433C3D3D0034
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 433C3D3D0036
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 433C3D3D0038
    */
   public void update(Object anObject) 
   {
    
   }
}
