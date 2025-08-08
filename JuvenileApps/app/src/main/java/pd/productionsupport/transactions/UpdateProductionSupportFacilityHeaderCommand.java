package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.productionsupport.UpdateProductionSupportFacilityHeaderEvent;
import messaging.productionsupport.reply.ProductionSupportFacilityHeaderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import mojo.km.util.MessageUtil;
import pd.juvenile.Juvenile;
import pd.juvenilecase.JJSHeader;

public class UpdateProductionSupportFacilityHeaderCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportFacilityHeaderCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportAssignment");
	   UpdateProductionSupportFacilityHeaderEvent updateEvent = (UpdateProductionSupportFacilityHeaderEvent) event;

	   if(updateEvent.getHeaderId() != null && !(updateEvent.getHeaderId().equals(""))) {
		   
	      Iterator headerIter = JJSHeader.findAll("OID", updateEvent.getHeaderId());
		   if( headerIter.hasNext() ){
		       
		       IHome home = new Home();
		       JJSHeader headerRec = (JJSHeader)headerIter.next();
		       
		      // if(StringUtils.isNotEmpty( updateEvent.getBookingSupervisionNum() )){
			   
			   headerRec.setBookingSupervisionNum( updateEvent.getBookingSupervisionNum() ); 
		      // }
		       
		       //if(StringUtils.isNotEmpty( updateEvent.getBookingReferralNum() )){
			   
			   headerRec.setReferralNumber( updateEvent.getBookingReferralNum());
		       //}
		       
		      // if(StringUtils.isNotEmpty( updateEvent.getFacilityCode() )){
			   
			   headerRec.setFacilityCode( updateEvent.getFacilityCode());
		      // }
		      
		       //if(StringUtils.isNotEmpty( updateEvent.getFacilityStatusCode() )){
			  
			   headerRec.setFacilityStatus( updateEvent.getFacilityStatusCode());
		      // }
		      
		      // if(StringUtils.isNotEmpty( updateEvent.getFacilityCode() )){
			   
			   headerRec.setHeaderFacility( updateEvent.getFacilityCode());
		      // }
		      
		     //  if(StringUtils.isNotEmpty( updateEvent.getLastSequenceNum() )){
			   
			   headerRec.setLastSequenceNumber( updateEvent.getLastSequenceNum());
		       //}
		       
		       //if( updateEvent.getNextHearingDate()!= null ){
			   
			   headerRec.setNextHearingDate( updateEvent.getNextHearingDate());
		      // }
		       
		      // if( updateEvent.getProbableCauseHearingDate()!= null ){
			   
			   headerRec.setProbableCauseDate( updateEvent.getProbableCauseHearingDate());
		      // }
		      
			   headerRec.setJuvenileNumber(updateEvent.getJuvenileId());
		       
		       home.bind(headerRec);
		       
		   }
		 
		   Iterator iter = JJSHeader.findAll("OID", updateEvent.getHeaderId());
		   if( iter.hasNext() ){
		       
		       ProductionSupportFacilityHeaderResponseEvent response = new ProductionSupportFacilityHeaderResponseEvent();
		       JJSHeader headerRec = (JJSHeader)iter.next();
		       
		       response.setHeaderId(headerRec.getOID());
		       JuvenileProfileDetailResponseEvent juvResp = Juvenile.findDetailJuvenile(headerRec.getJuvenileNumber());
			   if(juvResp != null){
			       response.setFirstName(juvResp.getFirstName());
			       response.setMiddleName(juvResp.getMiddleName());
			       response.setLastName(juvResp.getLastName());
			       response.setSuffixName(juvResp.getNameSuffix());
			       response.setRectype(juvResp.getRecType());
			   }
		       response.setJuvenileId( headerRec.getJuvenileNumber());
		       response.setBookingReferralNum(headerRec.getReferralNumber());
		       response.setBookingSupervisionNum( headerRec.getBookingSupervisionNum());
		       response.setFacilityCode( headerRec.getFacilityCode());
		       response.setFacilityStatusCode( headerRec.getFacilityStatus());
		       response.setFacilityCode( headerRec.getFacilityCode());
		       response.setHighestSequenceNumInUse( headerRec.getHighestSeqNumberInUse());
		       response.setLastSequenceNum( headerRec.getLastSequenceNumber());
		       response.setNextHearingDate( DateUtil.dateToString(headerRec.getNextHearingDate(),DateUtil.DATE_FMT_1));
		       response.setProbableCauseHearingDate( DateUtil.dateToString(headerRec.getProbableCauseDate(),DateUtil.DATE_FMT_1));
		       response.setCreateDate( headerRec.getLcDate());
		       response.setUpdateDate( headerRec.getUpdateTimestamp());
		       
		       MessageUtil.postReply(response);
		       
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
