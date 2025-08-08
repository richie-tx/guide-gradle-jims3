package pd.productionsupport.transactions;

import pd.juvenilecase.family.FamilyConstellation;
import messaging.error.reply.ErrorResponseEvent;
import messaging.productionsupport.DeleteFamilyConstellationEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;

public class DeleteFamilyConstellationCommand implements ICommand
{
    public DeleteFamilyConstellationCommand(){}
    
    public void execute(IEvent event) {
	DeleteFamilyConstellationEvent deleteEvent = (DeleteFamilyConstellationEvent) event;
	FamilyConstellation familyConstellation  = null;
	if (deleteEvent.getConstellationId() != null 
		&& deleteEvent.getConstellationId().length() > 0  ) {
	    	familyConstellation = FamilyConstellation.find(deleteEvent.constellationId);
	    if ( familyConstellation != null ) {
		familyConstellation.delete();
		new Home().bind(familyConstellation);
	    }
	    
	    familyConstellation = FamilyConstellation.find(deleteEvent.constellationId);
	    if(familyConstellation != null){
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		ErrorResponseEvent error = new ErrorResponseEvent();
		error.setMessage("Family Constellation record not deleted with constellation Id: " + deleteEvent.constellationId);
		dispatch.postEvent(error);
		return;
	}
	    
            
	}
	
    }

}
