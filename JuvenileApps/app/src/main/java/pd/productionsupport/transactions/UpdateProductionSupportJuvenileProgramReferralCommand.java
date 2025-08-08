package pd.productionsupport.transactions;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import messaging.productionsupport.RetrieveJuvenileProgramReferralByReferralNumberEvent;
import messaging.productionsupport.RetrieveJuvenileProgramReferralsEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileProgramReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.util.DateUtil;
import pd.common.calendar.CalendarEvent;
import pd.common.calendar.CalendarEventContext;
import pd.supervision.calendar.ServiceEvent;
import pd.supervision.programreferral.JuvenileEventReferral;
import pd.supervision.programreferral.JuvenileProgramReferral;
import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;
import ui.security.SecurityUIHelper;

public class UpdateProductionSupportJuvenileProgramReferralCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportJuvenileProgramReferralCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportJuvenileProgramReferral");
	   UpdateProductionSupportJuvenileProgramReferralEvent updateEvent = (UpdateProductionSupportJuvenileProgramReferralEvent) event;
	   RetrieveJuvenileProgramReferralsEvent retrievalEvent = new RetrieveJuvenileProgramReferralsEvent();
	   // update by casefile for merge
	   if(updateEvent.getCasefileId() != null && updateEvent.getCasefileId().length() > 0 && updateEvent.getReferralNum() == null){
		   retrievalEvent.setCasefileId(updateEvent.getCasefileId());
		   Iterator juvenileReferralsIter = JuvenileProgramReferral.findAll(retrievalEvent);
		   if(updateEvent.getCasefileId() != null && !(updateEvent.getCasefileId().equals("")) && 
					updateEvent.getMergeToCasefileId() != null && !(updateEvent.getMergeToCasefileId().equals(""))) {
			   while(juvenileReferralsIter.hasNext()){
				   JuvenileProgramReferral juvenileProgramReferral = (JuvenileProgramReferral)juvenileReferralsIter.next();
				   if(updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0){
					   juvenileProgramReferral.setCasefileId(updateEvent.getMergeToCasefileId());
				   }
			   }
		   }
		  // update referral status codes and dates
	   }else if (updateEvent.getReferralNum() != null && updateEvent.getReferralNum().length() > 0){
			RetrieveJuvenileProgramReferralByReferralNumberEvent requestEvent = new RetrieveJuvenileProgramReferralByReferralNumberEvent();
			requestEvent.setReferralNum(updateEvent.getReferralNum());
			Iterator  juvenileProgramReferralIter = JuvenileProgramReferral.findAll(requestEvent);
			JuvenileProgramReferral juvenileProgramReferralRecord = new JuvenileProgramReferral();
			while(juvenileProgramReferralIter.hasNext()){
				juvenileProgramReferralRecord = (JuvenileProgramReferral)juvenileProgramReferralIter.next();
				break;
			}
		   String prevCasefileId 		= juvenileProgramReferralRecord.getCasefileId();
		   String statusCd 			= updateEvent.getStatusCd();
		   String subStatusCd 			= updateEvent.getSubStatusCd();
		   String outcomeCd 			= updateEvent.getOutcomeCd();
		   String outcodeSubDescriptionCd 	= updateEvent.getOutcomeSubCd();
		   String casefileId 			= updateEvent.getCasefileId();
		   Date beginDate 			= updateEvent.getBeginDate();
		   Date endDate 			= updateEvent.getEndDate();
		   Date ackDate 			= updateEvent.getAckDate();
		   Date sentDate			= updateEvent.getSentDate();
		   String controllingReferral		= updateEvent.getControllingReferral();
		   String programReferralAssignmentId   = updateEvent.getProgramReferralAssignmentId();
		   String fundSource 			= updateEvent.getFundSource();
		   String provPgrmCd			= updateEvent.getProvProgramId();
		   
		   if(statusCd != null){
			   juvenileProgramReferralRecord.setReferralStatusCd(statusCd);
		   }
		   if(subStatusCd != null){
			   juvenileProgramReferralRecord.setReferralSubStatusCd(subStatusCd);
		   }
		   if(outcomeCd != null){
			   juvenileProgramReferralRecord.setProgramOutcomeCd(outcomeCd);
		   }
		   if(outcodeSubDescriptionCd != null){
			   juvenileProgramReferralRecord.setProgramOutcomeSubcategoryCd(outcodeSubDescriptionCd);
		   }
		   if(provPgrmCd != null){
			   juvenileProgramReferralRecord.setProvProgramId(provPgrmCd);
		   }
		   if(casefileId != null){
			   juvenileProgramReferralRecord.setCasefileId(casefileId);
		   }
		   if(updateEvent.isRefBeginDateChanged()){
			   juvenileProgramReferralRecord.setBeginDate(beginDate);
		   }
		   if(updateEvent.isRefEndDateChanged()){
			   juvenileProgramReferralRecord.setEndDate(endDate);
		   }
		   if(updateEvent.isRefAckDateChanged()){
		       	   juvenileProgramReferralRecord.setAcknowledgementDate(ackDate);
		   }
		   if(updateEvent.isRefSentDateChanged()){
		           juvenileProgramReferralRecord.setSentDate(sentDate);
		   }
		   if (updateEvent.isControllingReferralChanged()){
		       juvenileProgramReferralRecord.setControllingReferralNum(controllingReferral);
		   }
		   if(fundSource != null){
			   juvenileProgramReferralRecord.setFundSource(fundSource);
		   }
		   
		   
		   // also update prog ref events
		   Iterator<JuvenileEventReferral> eventReferralIter = JuvenileEventReferral.findAll("programReferralId", juvenileProgramReferralRecord.getOID());
		   while ( eventReferralIter.hasNext() ){
		       
		       JuvenileEventReferral eventRef = eventReferralIter.next();
		       CalendarEvent calEvent = ServiceEvent.find( eventRef.getServiceEventId() );
		       if( calEvent != null ){
			   
			   Iterator<CalendarEventContext> contextIter = CalendarEventContext.findAll( "calendarEventId",calEvent.getCalendarEventId());
			   
			   while(contextIter.hasNext()){
			       
			       CalendarEventContext calEventCont = contextIter.next();
			       if( contextIter != null && prevCasefileId.equals( calEventCont.getCaseFileId() ) && casefileId != null){

				   calEventCont.setCaseFileId( casefileId );
				   }
			   }			   
		       }		       
		   }
		   
		   if ( updateEvent.isProgramReferralAssignmentDateChanged()
			   && programReferralAssignmentId != null
			   && programReferralAssignmentId.length() > 0) {
		       JuvenileProgramReferralAssignmentHistory programRefAssignmentHistory =  JuvenileProgramReferralAssignmentHistory.find(programReferralAssignmentId);
		       if (programRefAssignmentHistory != null){
			  programRefAssignmentHistory.setProgramReferralAssignmentDate(updateEvent.getProgramReferralAssignmentDate());
		       }	   
		   }
	   	
		 //adding to the history task 145994
		 //Fixed bug for this task by user story 101123
		   
		   
		   if ( updateEvent.getCasefileId() != null 
			   && updateEvent.getCasefileId().length() > 0
			   && updateEvent.getMergeToCasefileId() != null
			   && updateEvent.getMergeToCasefileId().length() > 0 ) {
			JuvenileProgramReferralAssignmentHistory prAssignmentHistory = new JuvenileProgramReferralAssignmentHistory();
			prAssignmentHistory.setCasefileId(updateEvent.getCasefileId());
			prAssignmentHistory.setProgramReferralId(updateEvent.getReferralNum());
			prAssignmentHistory.setProgramReferralAssignmentDate(DateUtil.getCurrentDate());
			prAssignmentHistory.setUpdateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
			prAssignmentHistory.setUpdateUserID(SecurityUIHelper.getLogonId());
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
