//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenile\\transactions\\GetMedicalHospitalizationDataCommand.java

package pd.juvenile.transactions;

import messaging.juvenile.GetMedicalHospitalizationDataEvent;
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
public class GetMedicalHospitalizationDataCommand implements ICommand 
{
   
   /**
    * @roseuid 462CE21E03A8
    */
   public GetMedicalHospitalizationDataCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBCCC0284
    */
   public void execute(IEvent event) 
   {
   	GetMedicalHospitalizationDataEvent hospDataEvent = (GetMedicalHospitalizationDataEvent)event;
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   	JuvenileHospitalization hospDetails = JuvenileHospitalization.find(hospDataEvent.getHospitalizationId());
   	JuvenileHospitalizationResponseEvent respEvent = getHospitalizationResponseEvent(hospDetails);
	dispatch.postEvent(respEvent);
   }
   
   
   /**
    * @param hospitalization
    * @roseuid 45B0CB760237
    * @return
    */
   private JuvenileHospitalizationResponseEvent getHospitalizationResponseEvent(JuvenileHospitalization hospitalization) {
   	
   	JuvenileHospitalizationResponseEvent responseEvent = new JuvenileHospitalizationResponseEvent();
   	responseEvent.setEntryDate(hospitalization.getEntryDate());
   	responseEvent.setAdmissionDate(hospitalization.getAdmissionDate());
   	responseEvent.setFacilityName(hospitalization.getFacilityName());
   	responseEvent.setPhysicianName(hospitalization.getPhysicianName());
   	responseEvent.setReleaseDate(hospitalization.getReleaseDate());
   	responseEvent.setPhysicianPhoneNum(hospitalization.getPhysicianPhoneNum());
   	responseEvent.setHospitalizationReason(hospitalization.getHospitalizationReason());
   	responseEvent.setHospitalizationId(hospitalization.getHospitalizationId());
   	responseEvent.setAdmissionTypeId(hospitalization.getAdmissionTypeId());
   	return responseEvent;
   	
   }
   /**
    * @param event
    * @roseuid 462CBCCC0286
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBCCC0288
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 462CBCCC0293
    */
   public void update(Object anObject) 
   {
    
   }
   
  
}
