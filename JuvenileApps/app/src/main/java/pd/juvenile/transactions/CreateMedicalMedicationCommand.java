//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenile\\transactions\\CreateMedicalMedicationCommand.java

package pd.juvenile.transactions;

import messaging.juvenile.CreateMedicalMedicationEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileMedication;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreateMedicalMedicationCommand implements ICommand 
{
   
   /**
    * @roseuid 462CE2180398
    */
   public CreateMedicalMedicationCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBCB70168
    */
   public void execute(IEvent event) 
   {
   	CreateMedicalMedicationEvent medEvent = (CreateMedicalMedicationEvent)event;
   	JuvenileMedication medic = new JuvenileMedication();
    if(medEvent.getMedicationListId()!=null && !medEvent.getMedicationListId().equals("")){
    	medic=JuvenileMedication.find(medEvent.getMedicationListId());
			if(medic!=null){
				medic.setJuvenileNum(medEvent.getJuvenileNum());
				medic.setEntryDate(medEvent.getEntryDate());
			   	medic.setMedicationTypeId(medEvent.getMedicationTypeId());
			   	//medic.setCurrentlyTakingMedication(medEvent.isCurrentlyTaking());
			   	medic.setDosage(medEvent.getDosage());
			   	medic.setCurrentlyTakingMedId(medEvent.getCurrentlyTakingMedId()); //added new
			   	medic.setFrequencyId(medEvent.getFrequencyId());
			   	medic.setPhysicianName(medEvent.getPhysicianName());
			   	medic.setPhysicianPhoneNum(medEvent.getPhysicianPhone());
			   	medic.setReasonForMedication(medEvent.getMedicationReason());
			   	medic.setModificationReason(medEvent.getModificationReason());
			}
    	
    }  	
    else {  	
	   	medic.setJuvenileNum(medEvent.getJuvenileNum());
	   	medic.setEntryDate(medEvent.getEntryDate());
	   	medic.setMedicationTypeId(medEvent.getMedicationTypeId());
	   	//medic.setCurrentlyTakingMedication(medEvent.isCurrentlyTaking());
	   	medic.setDosage(medEvent.getDosage());
	   	medic.setCurrentlyTakingMedId(medEvent.getCurrentlyTakingMedId()); //added new
	   	medic.setFrequencyId(medEvent.getFrequencyId());
	   	medic.setPhysicianName(medEvent.getPhysicianName());
	   	medic.setPhysicianPhoneNum(medEvent.getPhysicianPhone());
	   	medic.setReasonForMedication(medEvent.getMedicationReason());
    }
   }
   
   /**
    * @param event
    * @roseuid 462CBCB7016A
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBCB7016C
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 462CBCB7016E
    */
   public void update(Object anObject) 
   {
    
   }  
  
}
