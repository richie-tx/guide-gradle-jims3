package pd.productionsupport.transactions;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import messaging.productionsupport.UpdateJJSCLCourtEvent;
import messaging.productionsupport.UpdateJJSCLDetentionCourtEvent;
import messaging.productionsupport.UpdateProductionSupportDistrictCourtReferralEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.juvenilecase.referral.JJSReferral;
import pd.security.PDSecurityHelper;

public class UpdateJJSCLDetentionCourtCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateJJSCLDetentionCourtCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
       
       UpdateJJSCLDetentionCourtEvent updateEvent = (UpdateJJSCLDetentionCourtEvent) event;
       IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   
       Iterator<JJSCLDetention> crtIter = new ArrayList<JJSCLDetention>().iterator(); // empty   iterator
       crtIter = JJSCLDetention.findAll("OID",updateEvent.getDocketEventId());
	   //Iterator<JJSReferral> crtIter = JJSReferral.findAll("OID", updateEvent.getOID());
	   
	   if( crtIter.hasNext()){
	       
	       JJSCLDetention foundRec = crtIter.next();
	       foundRec.setJuvenileNumber(updateEvent.getJuvenileNumber());
	       foundRec.setBarNumber(updateEvent.getBarNumber());
	       foundRec.setAttorneyConnection(updateEvent.getAttorneyConnection());
	       foundRec.setAttorneyName(updateEvent.getAttorneyName());
	       foundRec.setCourtDate(DateUtil.stringToDate(updateEvent.getHearingDate(), DateUtil.DATE_FMT_1 ) );
	       foundRec.setHearingResult(updateEvent.getHearingResult());
	       foundRec.setHearingType(updateEvent.getHearingType());
	       foundRec.setReferralNumber(updateEvent.getReferralNumber());	       
	       foundRec.setLcDate(DateUtil.getCurrentDate());
	       foundRec.setLcTime(Calendar.getInstance().getTime());
	       foundRec.setLcUser(PDSecurityHelper.getLogonId());
	       foundRec.setSeqNumber(updateEvent.getSequenceNumber());
	       foundRec.setDetentionId(updateEvent.getDetentionId());
	       foundRec.setPetitionNumber(updateEvent.getPetitionNumber());
	       foundRec.setCourtId(updateEvent.getCourtId());
	       
	       IHome home = new Home();
	       home.bind( foundRec );
	       //System.out.println("updateProductionSupportJuvenileReferral");
	       foundRec = null;
	       
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
