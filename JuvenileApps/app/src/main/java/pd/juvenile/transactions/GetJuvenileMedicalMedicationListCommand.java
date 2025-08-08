//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenile\\transactions\\GetJuvenileMedicalMedicationListCommand.java

package pd.juvenile.transactions;

import java.util.Iterator;

import messaging.juvenile.GetJuvenileMedicalMedicationListEvent;
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
public class GetJuvenileMedicalMedicationListCommand implements ICommand 
{
   
   /**
    * @roseuid 462CE21C000E
    */
   public GetJuvenileMedicalMedicationListCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBCCC016B
    */
   public void execute(IEvent event) 
   {
   	GetJuvenileMedicalMedicationListEvent reqEvent = (GetJuvenileMedicalMedicationListEvent)event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   	Iterator medicationIterator = JuvenileMedication.findAll(reqEvent);	
	while (medicationIterator.hasNext()) {
		JuvenileMedication medicationResults = (JuvenileMedication) medicationIterator.next();
		JuvenileMedicationResponseEvent medicationRespEvent = getMedicationResponseEvent(medicationResults);
		dispatch.postEvent(medicationRespEvent);
	}
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
	medicationRespEvent.setModificationReason(medicationResults.getModificationReason()); 
	medicationRespEvent.setDosage(medicationResults.getDosage());
	return medicationRespEvent;
}

/**
    * @param event
    * @roseuid 462CBCCC016D
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBCCC0178
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 462CBCCC017A
    */
   public void update(Object anObject) 
   {
    
   }
   
  
}
