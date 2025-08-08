//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenile\\transactions\\GetJuvenileMedicalHospitalizationListCommand.java

package pd.juvenile.transactions;

import java.util.Iterator;

import messaging.juvenile.GetJuvenileMedicalHospitalizationListEvent;
import messaging.juvenile.reply.JuvenileHospitalizationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileHospitalization;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetJuvenileMedicalHospitalizationListCommand implements ICommand 
{
   
   /**
    * @roseuid 462CE21A0118
    */
   public GetJuvenileMedicalHospitalizationListCommand() 
   {
    
   }
   
   /**
	 * @param event
	 * @roseuid 45B0CB76015B
	 */
	public void execute(IEvent event) {
		GetJuvenileMedicalHospitalizationListEvent mEvent = (GetJuvenileMedicalHospitalizationListEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator hospIterator = JuvenileHospitalization.findAll(mEvent);
		while (hospIterator.hasNext()) {
			JuvenileHospitalization hospitalization = (JuvenileHospitalization) hospIterator.next();
			JuvenileHospitalizationResponseEvent hospEvent = getHospitalizationResponseEvent(hospitalization);
			dispatch.postEvent(hospEvent);
		}

	}

	/**
	 * @param hospitalization
	 * @return
	 */
	private JuvenileHospitalizationResponseEvent getHospitalizationResponseEvent(JuvenileHospitalization hospitalization) {
		
		JuvenileHospitalizationResponseEvent responseEvent = new JuvenileHospitalizationResponseEvent();
		responseEvent.setHospitalizationId(hospitalization.getHospitalizationId());
		responseEvent.setEntryDate(hospitalization.getEntryDate());
		responseEvent.setFacilityName(hospitalization.getFacilityName());
		responseEvent.setReleaseDate(hospitalization.getReleaseDate());
		responseEvent.setAdmissionDate(hospitalization.getAdmissionDate());
		return responseEvent;
		
	}

   /**
    * @param event
    * @roseuid 462CBCCC0228
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBCCC0234
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 462CBCCC0236
    */
   public void update(Object anObject) 
   {
    
   }
   
 
}
