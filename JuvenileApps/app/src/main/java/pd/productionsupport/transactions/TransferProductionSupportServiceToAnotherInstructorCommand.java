package pd.productionsupport.transactions;

import java.util.Iterator;

import pd.supervision.administerserviceprovider.Service;
import pd.supervision.calendar.ServiceEvent;
import pd.supervision.calendar.ServiceEventContext;
import messaging.productionsupport.TransferProductionSupportServiceToAnotherInstructorEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class TransferProductionSupportServiceToAnotherInstructorCommand implements ICommand 
{
    public void execute(IEvent event){
	TransferProductionSupportServiceToAnotherInstructorEvent transferEvent = (TransferProductionSupportServiceToAnotherInstructorEvent) event;
	
	if (transferEvent.getInstructorId() != null
		&& transferEvent.getInstructorId().length() > 0 
		&& transferEvent.getServiceEventId() != null
		&& transferEvent.getServiceEventId().length() > 0 ){
	   
	    
	    ServiceEvent serviceEvent = ServiceEvent.findByServiceEventId(transferEvent.getServiceEventId());
	    if ( serviceEvent!= null ){
		serviceEvent.setInstructorId(transferEvent.getInstructorId());
		IHome home = new Home();
		home.bind( serviceEvent );
		serviceEvent = null;
	    }
	    
	    
	}
    }

}
