//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\CreateHospitalizationHistoryCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import pd.juvenilecase.mentalhealth.Hospitalization;
import messaging.mentalhealth.CreateHospitalizationHistoryEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreateHospitalizationHistoryCommand implements ICommand 
{
   
   /**
    * @roseuid 45AFB25D0347
    */
   public CreateHospitalizationHistoryCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45AFA6EE03DC
    */
   public void execute(IEvent event) 
   {
   	CreateHospitalizationHistoryEvent historyEvent = (CreateHospitalizationHistoryEvent)event;
   	Home home = new Home();
   	Hospitalization hospitalization = new Hospitalization();
   	hospitalization.setFacilityName(historyEvent.getFacilityName());
   	hospitalization.setAdmissionType(historyEvent.getAdmissionType());
   	hospitalization.setAdmitDate(historyEvent.getAdmissionDate());
   	hospitalization.setReleaseDate(historyEvent.getReleaseDate());
   	hospitalization.setAdmitPhysicianName(historyEvent.getPhysicianName());
   	hospitalization.setPhysicianPhone(historyEvent.getPhysicianPhone());
   	hospitalization.setHospitalizationReason(historyEvent.getHospitalizationReason());
   	hospitalization.setJuvenileNum(historyEvent.getJuvenileNum());
   	home.bind(hospitalization);
   }
   
   /**
    * @param event
    * @roseuid 45AFA6EE03DE
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45AFA6EF0003
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45AFA6EF0005
    */
   public void update(Object anObject) 
   {
    
   }
   
   
}
