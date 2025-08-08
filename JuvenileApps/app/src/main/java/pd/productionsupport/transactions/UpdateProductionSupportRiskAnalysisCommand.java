package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.productionsupport.UpdateProductionSupportRiskAnalysisEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

import org.apache.commons.lang.StringUtils;

import pd.juvenilecase.riskanalysis.RiskAnalysis;

public class UpdateProductionSupportRiskAnalysisCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportRiskAnalysisCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportRiskAnalysis");
	   UpdateProductionSupportRiskAnalysisEvent updateEvent = (UpdateProductionSupportRiskAnalysisEvent) event;
	   if(updateEvent.getCasefileId() != null && updateEvent.getCasefileId().length() > 0){	   
		   Iterator<RiskAnalysis> riskAnalysisIter = RiskAnalysis.findAllByCasefileID(updateEvent.getCasefileId());
		   if(updateEvent.getCasefileId() != null && !(updateEvent.getCasefileId().equals("")) && 
					updateEvent.getMergeToCasefileId() != null && !(updateEvent.getMergeToCasefileId().equals(""))) {
			   while(riskAnalysisIter.hasNext()){
				   RiskAnalysis riskAnalysisDocument = (RiskAnalysis)riskAnalysisIter.next();
				   if(updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0){
					   riskAnalysisDocument.setCasefileID(new Integer(updateEvent.getMergeToCasefileId()));
				   }
			   }
		   }
	   }else if(updateEvent.getRiskAnalysisId() != null && updateEvent.getRiskAnalysisId().length()> 0){
		  RiskAnalysis riskAnalysis = RiskAnalysis.find(updateEvent.getRiskAnalysisId());
		  if(updateEvent.getDateEntered() != null){
			  riskAnalysis.setEnteredDate(updateEvent.getDateEntered());
		  }
		  //Risk Answers is a view(does not have casefileId)
		  if( updateEvent.getMergeToCasefileId() != null && !StringUtils.isEmpty(updateEvent.getMergeToCasefileId()) ){
		      riskAnalysis.setCasefileID( Integer.parseInt(updateEvent.getMergeToCasefileId()));
		      
		   }
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
