//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\casefile\\transactions\\UpdateJuvenileCasefileCommand.java

package pd.juvenilecase.casefile.transactions;


import pd.juvenilecase.casefile.JuvenileExitReportDSMDiagnosis;
import messaging.casefile.UpdateExitReportDSMDiagnosisEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;


public class UpdateExitReportDSMDiagnosisCommand implements ICommand 
{
   
   /**
    * @roseuid 44CF77170194
    */
   public UpdateExitReportDSMDiagnosisCommand()   
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44C8E0DB02EE
    */
   public void execute(IEvent event) 
   {
   		UpdateExitReportDSMDiagnosisEvent updateEvent = (UpdateExitReportDSMDiagnosisEvent) event;
   		JuvenileExitReportDSMDiagnosis diagnosis = null;
		if(updateEvent.getDsmDiagnosisId()!= null && !updateEvent.getDsmDiagnosisId().equals(""))
		{
			diagnosis = JuvenileExitReportDSMDiagnosis.find(updateEvent.getDsmDiagnosisId());
			diagnosis.setConditionCd(updateEvent.getConditionCd());
			diagnosis.setSeverityCd(updateEvent.getSeverityCd());
		}
		else
		{
			diagnosis = new JuvenileExitReportDSMDiagnosis();
			diagnosis.setTestSessId(updateEvent.getTestSessId());
			diagnosis.setClosingInfoId(updateEvent.getCasefileCosingInfoId());
			diagnosis.setDiagnosisCd(updateEvent.getDiagnosisCd());
			diagnosis.setConditionCd(updateEvent.getConditionCd());
			diagnosis.setSeverityCd(updateEvent.getSeverityCd());
		}	
   }
   
   /**
    * @param event
    * @roseuid 44C8E0DB02F0
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44C8E0DB02FD
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 44C8E0DB02FF
    */
   public void update(Object anObject) 
   {
    
   } 
}
