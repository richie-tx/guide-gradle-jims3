//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenile\\transactions\\CreateMedicalHospitalizationCommand.java

package pd.juvenile.transactions;

import messaging.juvenile.CreateMedicalHospitalizationEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileHospitalization;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreateMedicalHospitalizationCommand implements ICommand 
{
   
   /**
    * @roseuid 462CE2170118
    */
   public CreateMedicalHospitalizationCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBCAE02A6
    */
   public void execute(IEvent event) 
   {
   	CreateMedicalHospitalizationEvent hospEvent = (CreateMedicalHospitalizationEvent)event;
   	JuvenileHospitalization hospitalization = new JuvenileHospitalization();
   	hospitalization.setFacilityName(hospEvent.getFacilityName());
   	hospitalization.setAdmissionTypeId(hospEvent.getAdmissionTypeId());
   	hospitalization.setAdmissionDate(hospEvent.getAdmissionDate());
   	hospitalization.setReleaseDate(hospEvent.getReleaseDate());
   	hospitalization.setEntryDate(hospEvent.getEntryDate());
   	hospitalization.setPhysicianName(hospEvent.getPhysicianName());
   	hospitalization.setPhysicianPhoneNum(hospEvent.getPhysicianPhone());
   	hospitalization.setHospitalizationReason(hospEvent.getHospitalizationReason());
   	hospitalization.setJuvenileNum(hospEvent.getJuvenileNum());
   	hospitalization.setAdmitYear(hospEvent.getAdmitYear());
   	hospitalization.setLengthOfStay(hospEvent.getLengthOfStay());
   	
   }
   
   /**
    * @param event
    * @roseuid 462CBCAE02B1
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBCAE02B3
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 462CBCAE02B5
    */
   public void update(Object anObject) 
   {
    
   }
   
  
}
