package pd.productionsupport.transactions;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import messaging.productionsupport.UpdateJJSCLCourtEvent;
import messaging.productionsupport.UpdateProductionSupportDistrictCourtReferralEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileReferralEvent;
import messaging.productionsupport.UpdateReferralOffenseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.security.PDSecurityHelper;

public class UpdateReferralOffenseCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateReferralOffenseCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
       
       UpdateReferralOffenseEvent updateEvent = (UpdateReferralOffenseEvent) event;
       IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   
       Iterator<JJSOffense> crtIter = new ArrayList<JJSOffense>().iterator(); // empty   iterator
       crtIter = JJSOffense.findAll("OID",updateEvent.getOID());
	   //Iterator<JJSReferral> crtIter = JJSReferral.findAll("OID", updateEvent.getOID());
	   
	   if( crtIter.hasNext()){
	       
	       JJSOffense foundRec = crtIter.next();
	       foundRec.setJuvenileNum( updateEvent.getJuvenileNum() );	       
	       foundRec.setReferralNum(updateEvent.getReferralNum());
	       foundRec.setOffenseCodeId(updateEvent.getOffenseCode());
	       foundRec.setSeverity(updateEvent.getOffenseSeverity());
	       foundRec.setOffenseDate(DateUtil.stringToDate(updateEvent.getOffDate(), DateUtil.DATE_FMT_1 ) );
	       foundRec.setKeyMapLocation(updateEvent.getKeyMapLocation());
	       foundRec.setInvestigationNumber(updateEvent.getInvestigationNum());
	       foundRec.setOffenseStreetNum(updateEvent.getOffenseStreetNum());
	       foundRec.setOffenseStreetName(updateEvent.getOffenseStreetName());
	       foundRec.setOffenseAptNum(updateEvent.getOffenseAptNum());	       
	       foundRec.setOffenseCity(updateEvent.getOffenseCity() );	       
	       foundRec.setOffenseState(updateEvent.getOffenseState());	       
	       foundRec.setOffenseZip(updateEvent.getOffenseZip());
	       foundRec.setWeaponType(updateEvent.getWeaponType());
	       foundRec.setCjisNum(updateEvent.getCjisNum());	       
	       foundRec.setArrestDate(DateUtil.stringToDate(updateEvent.getArrestDate(), DateUtil.DATE_FMT_1 ) );
	       foundRec.setArrestTime(updateEvent.getArrestTime());
	       foundRec.setSequenceNum(updateEvent.getSequenceNum());
	       foundRec.setChargeSequenceNum(updateEvent.getChargeSequenceNum());
	       foundRec.setLcDate(DateUtil.getCurrentDate());
	       foundRec.setLcTime(Calendar.getInstance().getTime());
	       foundRec.setLcUser(PDSecurityHelper.getLogonId());
	       
	       //US 171045
	       foundRec.setOnCampOffense(updateEvent.getOnCampOffense());
	       foundRec.setOnCampDistrict(updateEvent.getOnCampDistrict());
	       foundRec.setOnCampSchool(updateEvent.getOnCampSchool());
	       
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
