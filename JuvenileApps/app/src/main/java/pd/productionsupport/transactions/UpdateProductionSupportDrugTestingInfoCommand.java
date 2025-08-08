package pd.productionsupport.transactions;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import messaging.productionsupport.UpdateProductionSupportDrugTestingInfoEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import pd.codetable.criminal.JuvenileActivityTypeCode;
import pd.juvenile.DrugTesting;
import pd.juvenilecase.casefile.Activity;

public class UpdateProductionSupportDrugTestingInfoCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportDrugTestingInfoCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportDrugTesting");
	   UpdateProductionSupportDrugTestingInfoEvent updateEvent = (UpdateProductionSupportDrugTestingInfoEvent) event;
	   if ( updateEvent.getDrugTestingId() != null){
	       DrugTesting drugTesting = DrugTesting.find(updateEvent.getDrugTestingId());
	       if ( drugTesting != null ){
		   drugTesting.setAssociateCasefile (updateEvent.getMergeToCasefileId() );
		   drugTesting.setTestDate( DateUtil.stringToDate( updateEvent.getTestDate(), DateUtil.DATE_FMT_1 ) );
		   drugTesting.setTestTime( DateUtil.stringToDate(updateEvent.getTestTime(), DateUtil.TIME24_FMT_1) );
		   IHome home = new Home();
		   home.bind(drugTesting);
	       }
	   } else {
        	   Iterator drugInfoIter= DrugTesting.findAll("associateCasefile", updateEvent.getCasefileId());
        	   if(updateEvent.getCasefileId() != null && !(updateEvent.getCasefileId().equals("")) && 
        			updateEvent.getMergeToCasefileId() != null && !(updateEvent.getMergeToCasefileId().equals(""))) {
                	   while(drugInfoIter.hasNext()){
                	       DrugTesting drugTesting = (DrugTesting)drugInfoIter.next();
                		   if(updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0){
                		       drugTesting.setAssociateCasefile(updateEvent.getMergeToCasefileId());
                		       IHome home = new Home();
                		       home.bind(drugTesting);
                		   }
                	   }
        	   }
	   }
	  updateActivity( updateEvent );
   }
   
   
   /**
    * Update associated activity(s)
    * @param oldCasefileId
    * @param newCasefileId
    */
   private void updateActivity( UpdateProductionSupportDrugTestingInfoEvent updateEvent  ) {
       
       ArrayList<String> codes = new ArrayList<String>();
       String oldCasefileId = updateEvent.getCasefileId();
       String newCasefileId = updateEvent.getMergeToCasefileId();
       
       Iterator<JuvenileActivityTypeCode> codeIter = JuvenileActivityTypeCode.findAll("typeId", "DRG");
       while( codeIter.hasNext() ) {
	   
	   JuvenileActivityTypeCode code = codeIter.next();
	   if("A".equalsIgnoreCase( code.getStatus())) {
	       // Add to array
	       codes.add( code.getCode() );
	       
	   }
       }
       
       
       Iterator<Activity> iter = Activity.findAll("supervisionNumber", oldCasefileId);
       while( iter.hasNext() ) {
	   
	   Activity activity = iter.next();
	   if( codes.contains( activity.getActivityCodeId() ) ){
	       
	       activity.setSupervisionNumber( newCasefileId );
	       activity.setActivityDate( DateUtil.stringToDate( updateEvent.getTestDate(), DateUtil.DATE_FMT_1 ) );
	       activity.setActivityTime( updateEvent.getTestTime() );
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
