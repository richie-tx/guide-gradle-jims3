package pd.productionsupport.transactions;


import java.util.Date;
import java.util.Iterator;

import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.productionsupport.GetProductionSupportFacilityHeaderEvent;
import messaging.productionsupport.reply.ProductionSupportFacilityHeaderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.DateUtil;
import pd.juvenile.Juvenile;
import pd.juvenilecase.JJSHeader;


/**
 * @author rcarter
 */

public class GetProductionSupportFacilityHeaderCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public GetProductionSupportFacilityHeaderCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   
	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   System.out.println("GetProductionSupportFacilityHeaderCommand");
	   GetProductionSupportFacilityHeaderEvent getHeaderEvent = (GetProductionSupportFacilityHeaderEvent) event;
	   Iterator<JJSHeader> facilityHeaderIter = JJSHeader.findAll("juvenileNumber",getHeaderEvent.getJuvenileId());
		   if(facilityHeaderIter != null){
			   while(facilityHeaderIter.hasNext()){
				   JJSHeader facilityHeader = facilityHeaderIter.next();
				   if(facilityHeader != null){
					   System.out.println(facilityHeader);
					   ProductionSupportFacilityHeaderResponseEvent facilityHeaderResponseEvent = new ProductionSupportFacilityHeaderResponseEvent();

					   facilityHeaderResponseEvent.setHeaderId(facilityHeader.getOID());
					   facilityHeaderResponseEvent.setJuvenileId(facilityHeader.getJuvenileNumber());
					   // get name
					   JuvenileProfileDetailResponseEvent juvResp = Juvenile.findDetailJuvenile(getHeaderEvent.getJuvenileId());
					   if(juvResp != null){
						   facilityHeaderResponseEvent.setFirstName(juvResp.getFirstName());
						   facilityHeaderResponseEvent.setMiddleName(juvResp.getMiddleName());
						   facilityHeaderResponseEvent.setLastName(juvResp.getLastName());
						   facilityHeaderResponseEvent.setSuffixName(juvResp.getNameSuffix());
						   facilityHeaderResponseEvent.setRectype(juvResp.getRecType());
					   }
					   
					   facilityHeaderResponseEvent.setBookingReferralNum(facilityHeader.getReferralNumber());
					   facilityHeaderResponseEvent.setBookingSupervisionNum(facilityHeader.getBookingSupervisionNum());
					   facilityHeaderResponseEvent.setLastSequenceNum(facilityHeader.getLastSequenceNumber());
					   facilityHeaderResponseEvent.setHighestSequenceNumInUse( facilityHeader.getHighestSeqNumberInUse());
					   facilityHeaderResponseEvent.setHeaderId( facilityHeader.getOID() );
					   facilityHeaderResponseEvent.setFacilityCode(facilityHeader.getFacilityCode());
					   facilityHeaderResponseEvent.setFacilityStatusCode(facilityHeader.getFacilityStatus());
					   facilityHeaderResponseEvent.setNextHearingDate(DateUtil.dateToString(facilityHeader.getNextHearingDate(), DateUtil.DATE_FMT_1));
					   facilityHeaderResponseEvent.setProbableCauseHearingDate(DateUtil.dateToString(facilityHeader.getProbableCauseDate(), DateUtil.DATE_FMT_1));				
					   
					   // audit data
					   if(facilityHeader.getCreateUserID() != null){
							facilityHeaderResponseEvent.setCreateUserID(facilityHeader.getCreateUserID());
						}
						if(facilityHeader.getCreateTimestamp() != null){
							facilityHeaderResponseEvent.setCreateDate(new Date(facilityHeader.getCreateTimestamp().getTime()));
						}
						if(facilityHeader.getUpdateUserID() != null){
							facilityHeaderResponseEvent.setUpdateUser(facilityHeader.getUpdateUserID());
						}
						if(facilityHeader.getUpdateTimestamp() != null){
							facilityHeaderResponseEvent.setUpdateDate(new Date(facilityHeader.getUpdateTimestamp().getTime()));
						}
						if(facilityHeader.getCreateJIMS2UserID() != null){
							facilityHeaderResponseEvent.setCreateJIMS2UserID(facilityHeader.getCreateJIMS2UserID());
						}
						if(facilityHeader.getUpdateJIMS2UserID() != null){
							facilityHeaderResponseEvent.setUpdateJIMS2UserID(facilityHeader.getUpdateJIMS2UserID());
						}
						
					dispatch.postEvent(facilityHeaderResponseEvent);
				   }
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
