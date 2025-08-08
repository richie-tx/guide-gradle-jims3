//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\casefile\\transactions\\UpdateJuvenileCasefileCommand.java

package pd.juvenilecase.casefile.transactions;

import messaging.casefile.UpdateJuvenileCasefileEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileCasefile;

import java.sql.Timestamp;

public class UpdateJuvenileCasefileCommand implements ICommand 
{
   
   /**
    * @roseuid 44CF77170194
    */
   public UpdateJuvenileCasefileCommand()   
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44C8E0DB02EE
    */
   public void execute(IEvent event) 
   {
		UpdateJuvenileCasefileEvent updateEvent = (UpdateJuvenileCasefileEvent) event;
		JuvenileCasefile casefile = null;
		if(updateEvent.getSupervisionNumber() != null && !(updateEvent.getSupervisionNumber().equals(""))) {
			casefile = JuvenileCasefile.find(updateEvent.getSupervisionNumber());
			casefile.setSupervisionEndDate(updateEvent.getSupervisionEndDate());
			casefile.setIsMAYSINeeded(updateEvent.isMAYSINeeded);
			casefile.setIsReferralRiskNeeded(updateEvent.isReferralRiskNeeded());
			//commented out for US 14459
			//casefile.setIsInterviewRiskNeeded(updateEvent.isInterviewRiskNeeded());
			//added for prodsupport
			if(updateEvent.getActivationDate()!=null)
				casefile.setActivationDate(updateEvent.getActivationDate());
			if(updateEvent.getSupervisionTypeId()!=null && !updateEvent.getSupervisionTypeId().equals(""))
				casefile.setSupervisionTypeId(updateEvent.getSupervisionTypeId());
			if(updateEvent.getSequenceNum()!=null && !updateEvent.getSequenceNum().equals(""))
				casefile.setSequenceNumber(updateEvent.getSequenceNum());
			if(updateEvent.getControllingReferralId()!=null && !updateEvent.getControllingReferralId().equals(""))
				casefile.setCasefileControllingReferralId(updateEvent.getControllingReferralId());
			if(updateEvent.getCreateDate()!=null)
				casefile.setCreateTimestamp(new Timestamp(updateEvent.getCreateDate().getTime()));
			//added for US 180414 starts
			if(updateEvent.getJuvenileNum()!=null && !updateEvent.getJuvenileNum().equals(""))
				casefile.setJuvenileId(updateEvent.getJuvenileNum());
			//added for US 180414 ends
			casefile.setRiskNeed( updateEvent.getPactRiskNeeded() );
			casefile.setHispanic( updateEvent.getHispanicIndicatorNeeded() );
			casefile.setSchool( updateEvent.getSchoolHistoryNeeded() );
			casefile.setVop( updateEvent.getVopEntryNeeded());
			casefile.setSubabuse( updateEvent.getSubstanceAbuseNeeded() );
			
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
