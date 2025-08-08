//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\GetMentalHealthHospitalizationDataCommand.java

package pd.juvenilecase.mentalhealth.transactions;


import messaging.mentalhealth.GetMentalHealthHospitalizationDataEvent;
import messaging.mentalhealth.reply.HospitalizationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.mentalhealth.Hospitalization;

public class GetMentalHealthHospitalizationDataCommand implements ICommand 
{
   
   /**
    * @roseuid 45B0DEBC0182
    */
   public GetMentalHealthHospitalizationDataCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45B0CB760236
    */
   public void execute(IEvent event) {
	GetMentalHealthHospitalizationDataEvent mEvent = (GetMentalHealthHospitalizationDataEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Hospitalization hospitalizationDetails = Hospitalization.find(mEvent.getHospitalizationId());
	HospitalizationResponseEvent hospEvent = getHospitalizationResponseEvent(hospitalizationDetails);
	dispatch.postEvent(hospEvent);
	
}

  /**
 * @param hospitalization
 * @roseuid 45B0CB760237
 * @return
 */
private HospitalizationResponseEvent getHospitalizationResponseEvent(Hospitalization hospitalization) {
	
	HospitalizationResponseEvent responseEvent = new HospitalizationResponseEvent();
	responseEvent.setAdmissionDate(hospitalization.getAdmitDate());
	responseEvent.setFacilityName(hospitalization.getFacilityName());
	responseEvent.setAdmittingPhysicianName(hospitalization.getAdmitPhysicianName());
	responseEvent.setReleaseDate(hospitalization.getReleaseDate());
	responseEvent.setPhysicianPhone(hospitalization.getPhysicianPhone());
	responseEvent.setHospitalizationReason(hospitalization.getHospitalizationReason());
	responseEvent.setHospitalizationId(hospitalization.getHospitalizationId());
	responseEvent.setAdmissionType(hospitalization.getAdmissionType());
	return responseEvent;
	
}
   
   /**
    * @param event
    * @roseuid 45B0CB760238
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45B0CB760244
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45B0CB760246
    */
   public void update(Object anObject) 
   {
    
   }
 
}
