package pd.productionsupport.transactions;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.productionsupport.GetProductionSupportPetitionDetailsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilewarrant.JJSPetition;
public class GetProductionSupportPetitionDetailsCommand implements ICommand
{
    public GetProductionSupportPetitionDetailsCommand(){}
    public void execute(IEvent event) {
	GetProductionSupportPetitionDetailsEvent petitionDetailsEvent  = (GetProductionSupportPetitionDetailsEvent) event;
	Iterator<JJSPetition> petitionIter = null;
	if (petitionDetailsEvent.getJuvenileNum()!= null 
		&& petitionDetailsEvent.getReferralNum()!= null ) {
	    petitionIter = JJSPetition.findAll(petitionDetailsEvent);
	}
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	while( petitionIter != null
		&& petitionIter.hasNext() ){
	    JJSPetition petition = (JJSPetition) petitionIter.next();
	    if ( petition != null ) {
		PetitionResponseEvent responseEvent = new PetitionResponseEvent();
		responseEvent.setJuvenileNum(petition.getJuvenileNum());
		responseEvent.setReferralNum(petition.getReferralNum());
		responseEvent.setAmend(petition.getAmend());
		responseEvent.setPetitionStatus(petition.getStatus());
		responseEvent.setSeverity(petition.getSeverity());
		responseEvent.setPetitionType(petition.getType());
		responseEvent.setPetitionDate(petition.getPetitionDate());
		responseEvent.setPetition_Date(formatDate(petition.getPetitionDate()));
		responseEvent.setLastChangeDate(petition.getLcDate());
		responseEvent.setLast_Change_Date(formatDate(petition.getLcDate()));
		responseEvent.setLast_Change_Time(toTime(petition.getLcTime()));
		responseEvent.setLastChangeUser(petition.getLcUser());
		responseEvent.setPetitionAllegation(petition.getPetitionAllegation());
		responseEvent.setPetitionNum(petition.getPetitionNum());
		responseEvent.setSequenceNum(petition.getSequenceNum());
		responseEvent.setSequenceNumber(petition.getSequenceNumber());
		responseEvent.setTerminationDate(petition.getTerminationDate());
		responseEvent.setTermination_Date(formatDate(petition.getTerminationDate()));
		responseEvent.setTerminationCreateDate(petition.getTerminationCreateDate());
		responseEvent.setTermination_CreateDate(formatDate(petition.getTerminationCreateDate()));
		responseEvent.setOID(petition.getOID());
		responseEvent.setPetCJISNum(petition.getCJISNumber());
		responseEvent.setDPSCode(petition.getDPSCode());
		dispatch.postEvent(responseEvent);
		
		
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
	    }
	}
    }
    
    public String formatDate(Date date){
   	return ( date != null ) ?  new SimpleDateFormat("MM/dd/yyyy").format(date) : "";
    }
    
    public String toTime(Date time){
  	return ( time != null )? new SimpleDateFormat("HH:mm:ss").format(time) : "";
      }
      
}
