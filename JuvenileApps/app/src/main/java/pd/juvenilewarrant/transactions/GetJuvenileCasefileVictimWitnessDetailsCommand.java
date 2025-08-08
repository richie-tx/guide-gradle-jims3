//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\referral\\transactions\\GetJuvenileCasefileVictimWitnessDetailsCommand.java

package pd.juvenilewarrant.transactions;

import java.util.Iterator;

import naming.PDJuvenileWarrantConstants;

import pd.juvenilewarrant.JuvenileOffenderTrackingComplainant;
import messaging.contact.to.PhoneNumberBean;
import messaging.juvenilewarrant.GetJuvenileCasefileVictimWitnessDetailsEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingComplainantResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileCasefileVictimWitnessDetailsCommand implements ICommand 
{
   
   /**
    * @roseuid 467FB26A0236
    */
   public GetJuvenileCasefileVictimWitnessDetailsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 467FAF3A0053
    */
   public void execute(IEvent event) 
   {
   		GetJuvenileCasefileVictimWitnessDetailsEvent evt = (GetJuvenileCasefileVictimWitnessDetailsEvent)event;
   		Iterator iter = JuvenileOffenderTrackingComplainant.findAll(evt);
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		if(iter.hasNext())
   		{
   			JuvenileOffenderTrackingComplainant comp = (JuvenileOffenderTrackingComplainant)iter.next();
   			JuvenileOffenderTrackingComplainantResponseEvent resp = getComplainantResp(comp);
   			dispatch.postEvent(resp);
   			
   		}
   }
   private JuvenileOffenderTrackingComplainantResponseEvent getComplainantResp(JuvenileOffenderTrackingComplainant comp)
   {
   		JuvenileOffenderTrackingComplainantResponseEvent resp = new JuvenileOffenderTrackingComplainantResponseEvent();
   		resp.setName(comp.getName());
   		if(comp.getComplainantType().equalsIgnoreCase("C"))
   			resp.setAssociationType(PDJuvenileWarrantConstants.DM_ASSOCIATION_TYPE_C);
   		else if(comp.getComplainantType().equalsIgnoreCase("W"))
   			resp.setAssociationType(PDJuvenileWarrantConstants.DM_ASSOCIATION_TYPE_W);
   		resp.setSequenceNum(comp.getSequenceNum());
   		resp.setRelationshipToJuvenile(comp.getRelationshipToJuvenile());
   		resp.setTransactionNum(comp.getTransactionNum());
   		resp.setDaLogNum(comp.getDaLogNum());
   		resp.setDateOfBirth(comp.getDateOfBirth());
   		resp.setAge(comp.getAge());
   		resp.setDlNumber(comp.getDriversLicenseNum());
   		if(comp.getDriversLicenseStateId()!=null && !comp.getDriversLicenseStateId().equals(""))
   			resp.setDlState(comp.getDriversLicenseState().getDescription());   		
   		resp.setSsn(comp.getSocialSecurityNum());
   		if(comp.getStateComplainantInd().equalsIgnoreCase("Y"))
   			resp.setTheStateTheComplainant(true);
   		resp.setOtherIDNumbers(comp.getOtherIdNumbers());
   		resp.setStreetNum(comp.getStreetNum());
   		resp.setStreetName(comp.getStreetName());
   		resp.setAptNum(comp.getAptNum());
   		resp.setCity(comp.getCity());
   		if(comp.getStateId()!=null && !comp.getStateId().equals(""))
   			resp.setState(comp.getState().getDescription());
   		if(comp.getZip()!=null && !comp.getZip().equals(""))
   		{
	   		StringBuffer zip = new StringBuffer(comp.getZip());
	   		zip.insert(5, "-"); 
	   		resp.setZip(zip.toString());
   		}
   		if(comp.getPhone()!=null && !comp.getPhone().equals(""))
   			resp.setPhone(new PhoneNumberBean(comp.getPhone()));
   		resp.setEmployer(comp.getEmployer());
   		resp.setOccupation(comp.getOccupation());
   		resp.setOtherInd(comp.getOtherInd());
   		resp.setOtherStreetName(comp.getOtherStreetName());
   		resp.setOtherStreetNumber(comp.getOtherStreetNumber());
   		resp.setOtherAptNum(comp.getOtherAptNum());
   		resp.setOtherCity(comp.getOtherCity());
   		if(comp.getOtherStateId()!=null && !comp.getOtherStateId().equals(""))
   			resp.setOtherState(comp.getOtherState().getDescription());
   		if(comp.getOtherZip()!=null && !comp.getOtherZip().equals(""))
   		{
	   		StringBuffer zip = new StringBuffer(comp.getOtherZip());
	   		zip.insert(5, "-"); 
	   		resp.setOtherZip(zip.toString());
   		}
   		if(comp.getOtherPhone()!=null && !comp.getOtherPhone().equals(""))
   			 resp.setOtherPhone(new PhoneNumberBean(comp.getOtherPhone()));
   		return resp;
   }
   /**
    * @param event
    * @roseuid 467FAF3A0071
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 467FAF3A0073
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 467FAF3A0075
    */
   public void update(Object anObject) 
   {
    
   }  
  
}
