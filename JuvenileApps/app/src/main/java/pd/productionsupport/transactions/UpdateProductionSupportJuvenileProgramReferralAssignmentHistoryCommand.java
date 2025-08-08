package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.productionsupport.UpdateProductionSupportJuvenileProgramReferralAssignmentHistoryEvent;
import mojo.km.context.ICommand;
import mojo.km.context.multidatasource.Home;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import pd.common.calendar.CalendarEventContext;
import pd.juvenilecase.JuvenileCasefile;
import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;
import ui.security.SecurityUIHelper;

public class UpdateProductionSupportJuvenileProgramReferralAssignmentHistoryCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportJuvenileProgramReferralAssignmentHistoryCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportJuvenileProgramReferralAssignmentHistory");
	   UpdateProductionSupportJuvenileProgramReferralAssignmentHistoryEvent updateEvent = 
		   (UpdateProductionSupportJuvenileProgramReferralAssignmentHistoryEvent) event;
	   if( !updateEvent.isCreateNewRecord() ) {
        	   Iterator getProgramReferralAssignmentHistoryIter = JuvenileProgramReferralAssignmentHistory.
        			findAll("casefileId", updateEvent.getCasefileId());
        	   if(updateEvent.getCasefileId() != null && !(updateEvent.getCasefileId().equals("")) && 
        				updateEvent.getMergeToCasefileId() != null && !(updateEvent.getMergeToCasefileId().equals(""))) {
        		   while(getProgramReferralAssignmentHistoryIter.hasNext()){
        			   JuvenileProgramReferralAssignmentHistory assignmentHistory = (JuvenileProgramReferralAssignmentHistory)getProgramReferralAssignmentHistoryIter.next();
        			   if(updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0){
        				   assignmentHistory.setCasefileId(updateEvent.getMergeToCasefileId());
        			   }
        		   }
        	   }
	   } else {
	       JuvenileProgramReferralAssignmentHistory programRefAssignmentHist = new JuvenileProgramReferralAssignmentHistory();
	       programRefAssignmentHist.setProgramReferralId(updateEvent.getJuvProgRefId());
	       programRefAssignmentHist.setCasefileId(updateEvent.getCasefileId());
	       programRefAssignmentHist.setProgramReferralAssignmentDate(DateUtil.getCurrentDate());
	       //programRefAssignmentHist.setCreateUserID(SecurityUIHelper.getLogonId());
	       //programRefAssignmentHist.setCreateTimestamp(timestamp);
	       
	       
	       
	       IHome home= new Home();
	       home.bind(programRefAssignmentHist);
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
