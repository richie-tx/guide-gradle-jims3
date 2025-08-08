//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenile\\transactions\\GetMedicalMedicationDataCommand.java

package pd.juvenile.transactions;

import messaging.juvenile.GetMedicalMedicationDataEvent;
import messaging.juvenile.reply.JuvenileMedicationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileMedication;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetMedicalMedicationDataCommand implements ICommand 
{
   
   /**
    * @roseuid 462CE2200202
    */
   public GetMedicalMedicationDataCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBCCC01BC
    */
   public void execute(IEvent event) 
   {
   	GetMedicalMedicationDataEvent dataEvent = (GetMedicalMedicationDataEvent)event;
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   	JuvenileMedication medicationData =  JuvenileMedication.find(dataEvent.getMedicationId());
   	JuvenileMedicationResponseEvent medicationRespEvent = getMedicationResponseEvent(medicationData);
   	dispatch.postEvent(medicationRespEvent);
   }
   
   /**
    * @param medicationResults
    * @return
    */
   private JuvenileMedicationResponseEvent getMedicationResponseEvent(JuvenileMedication medicationResults) {
   	JuvenileMedicationResponseEvent medicationRespEvent = new JuvenileMedicationResponseEvent();
   	medicationRespEvent.setMedicationId(medicationResults.getMedicationId());
   	medicationRespEvent.setEntryDate(medicationResults.getEntryDate());
   	medicationRespEvent.setMedicationTypeId(medicationResults.getMedicationTypeId());
   	medicationRespEvent.setCurrentlyTakingMedication(medicationResults.getCurrentlyTakingMedId());
   	medicationRespEvent.setDosage(medicationResults.getDosage());
   	medicationRespEvent.setFrequencyId(medicationResults.getFrequencyId());
   	medicationRespEvent.setPhysicianName(medicationResults.getPhysicianName());
   	medicationRespEvent.setPhysicianPhoneNum(medicationResults.getPhysicianPhoneNum());
   	medicationRespEvent.setReasonForMedication(medicationResults.getReasonForMedication());
   	medicationRespEvent.setModificationReason(medicationResults.getModificationReason()); 
   	return medicationRespEvent;
   }
   
   /**
    * @param event
    * @roseuid 462CBCCC01C7
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBCCC01C9
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 462CBCCC01D6
    */
   public void update(Object anObject) 
   {
    
   }
   

}
