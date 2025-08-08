//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\criminalcase\\transactions\\UpdateSupervisoryFeeCommand.java

package pd.criminalcase.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pd.criminalcase.CriminalCase;
import pd.supervision.managetask.CSTask;
import messaging.criminalcase.GetCaseEvent;
import messaging.criminalcase.reply.GetCaseResponseEvent;
import messaging.supervisionorder.GetSupervisionOrdersEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

public class GetCaseCommand implements ICommand 
{
   
   /**
    * @roseuid 4761AB7F0108
    */
   public GetCaseCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 475D9DBB02B9
    */
   public void execute(IEvent event) 
   {
	   GetCaseEvent getCaseEvent = (GetCaseEvent) event;
	   Iterator cicsNewOrderIter = null;
	   List<CriminalCase> validCases = new ArrayList<CriminalCase>();
	   cicsNewOrderIter = CriminalCase.findAll(getCaseEvent);
	   
	   while(cicsNewOrderIter.hasNext()){
		   GetCaseResponseEvent evtResponse = new GetCaseResponseEvent();
		   CriminalCase aCase = (CriminalCase)cicsNewOrderIter.next();
           evtResponse.setCaseFilingDate(aCase.getFilingDate());
           evtResponse.setCaseNum(aCase.getCaseNum());
           evtResponse.setCaseStatusId(aCase.getCaseStatusId());
           evtResponse.setCdi(aCase.getCourtDivisionId());
           evtResponse.setCourtId(aCase.getCourtId());
           evtResponse.setCriminalCaseId(aCase.getCriminalCaseId());
           evtResponse.setDefendantId(aCase.getDefendantId());
           evtResponse.setDefendantName(aCase.getDefendantName());
           evtResponse.setDefendantStatusId(aCase.getDefendantStatusId());          
           evtResponse.setOffenseCodeId(aCase.getOffenseCodeId());
           evtResponse.setProbationOfficerId(aCase.getProbationOfficerId());
           evtResponse.setZipCode(aCase.getZipCode());
           
           MessageUtil.postReply( evtResponse );
	   }
   }
   
   /**
    * @param event
    * @roseuid 475D9DBB02BB
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 475D9DBB02C6
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 475D9DBB02C8
    */
   public void update(Object anObject) 
   {
    
   }
}
