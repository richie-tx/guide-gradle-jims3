package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.productionsupport.UpdateProductionSupportDistrictCourtReferralEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import pd.juvenilecase.referral.JJSReferral;

public class UpdateProductionSupportDistrictCourtReferralCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportDistrictCourtReferralCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
       
       UpdateProductionSupportDistrictCourtReferralEvent updateEvent = (UpdateProductionSupportDistrictCourtReferralEvent) event;
       IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
       
	   
	   Iterator<JJSReferral> refIter = JJSReferral.findAll("OID", updateEvent.getOID());
	   
	   if( refIter.hasNext()){
	       
	       JJSReferral foundRec = refIter.next();
	       //Iterator<JJSReferral> refIter = JJSReferral.findAll(updateEvent);      

	       foundRec.setJuvenileNum( updateEvent.getJuvenileNum() );
	       foundRec.setCourtId( updateEvent.getCourtId() );
	       foundRec.setCourtDate( DateUtil.stringToDate( updateEvent.getDispositionDate(), DateUtil.DATE_FMT_1 ) );
	       foundRec.setCourtResultId( updateEvent.getCourtResult() );
	       foundRec.setCourtDispositionId( updateEvent.getCourtDisposition() );
	       foundRec.setLcDate(DateUtil.stringToDate( updateEvent.getLcDate(), DateUtil.DATE_FMT_1 ) );
	       foundRec.setLcTime(DateUtil.stringToDate( updateEvent.getLcDate(), DateUtil.TIME24_FMT_1 ) );
	       foundRec.setLcUser(updateEvent.getLcUser());
	      
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
