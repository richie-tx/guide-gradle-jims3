package pd.productionsupport.transactions;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import messaging.productionsupport.UpdateJJSCLCourtEvent;
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
import pd.juvenilecase.referral.JJSReferral;
import pd.security.PDSecurityHelper;

public class UpdateJJSCLCourtCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateJJSCLCourtCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
       
       UpdateJJSCLCourtEvent updateEvent = (UpdateJJSCLCourtEvent) event;
       IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   
       Iterator<JJSCourt> crtIter = new ArrayList<JJSCourt>().iterator(); // empty   iterator
       crtIter = JJSCourt.findAll("OID",updateEvent.getDocketEventId());
	   //Iterator<JJSReferral> crtIter = JJSReferral.findAll("OID", updateEvent.getOID());
	   
	   if( crtIter.hasNext()){
	       
	       JJSCourt foundRec = crtIter.next();
	       foundRec.setCourtId( updateEvent.getCourtId() );
	       foundRec.setCourtDate(DateUtil.stringToDate(updateEvent.getCourtDate(), DateUtil.DATE_FMT_1 ) );
	       foundRec.setCourtTime(updateEvent.getCourtTime());
	       foundRec.setReferralNumber(updateEvent.getReferralNumber() );
	       foundRec.setSeqNumber(updateEvent.getSeqNumber());
	       foundRec.setHearingResult(updateEvent.getHearingResult());
	       foundRec.setHearingDisposition(updateEvent.getHearingDisposition());
	       foundRec.setHearingType(updateEvent.getHearingType());
	       foundRec.setResetHearingType(updateEvent.getResetHearingType());
	       
	       foundRec.setPetitionNumber(updateEvent.getPetitionNumber() );
	       foundRec.setPetitionStatus(updateEvent.getPetitionStatus());
	       foundRec.setPetitionAllegation(updateEvent.getPetitionAllegation());
	       foundRec.setFilingDate(DateUtil.stringToDate(updateEvent.getFilingDate(), DateUtil.DATE_FMT_1 ) );
	       foundRec.setPetitionAmendment(updateEvent.getPetitionAmendment());
	       foundRec.setAmendmentDate(DateUtil.stringToDate(updateEvent.getAmendmentDate(), DateUtil.DATE_FMT_1 ) );	       
	       foundRec.setBarNumber(updateEvent.getBarNumber());
	       foundRec.setAttorneyConnection(updateEvent.getAttorneyConnection());
	       foundRec.setAttorneyName(updateEvent.getAttorneyName());
	       foundRec.setPrevNotes(updateEvent.getPrevNotes());	       
	       foundRec.setLcDate(DateUtil.getCurrentDate());
	       foundRec.setLcTime(Calendar.getInstance().getTime());
	       foundRec.setLcUser(PDSecurityHelper.getLogonId());
	       foundRec.setJuvenileNumber(updateEvent.getJuvenileNumber());
	       foundRec.setTransferTo(updateEvent.getTrasferTo());
	       
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
