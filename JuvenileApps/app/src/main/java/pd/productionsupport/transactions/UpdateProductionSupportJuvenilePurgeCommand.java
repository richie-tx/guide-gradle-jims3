package pd.productionsupport.transactions;

import java.text.SimpleDateFormat;
import java.util.Date;

import pd.juvenile.JuvenileCore;
import messaging.error.reply.ErrorResponseEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenilePurgeEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;

public class UpdateProductionSupportJuvenilePurgeCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	// TODO Auto-generated method stub
	UpdateProductionSupportJuvenilePurgeEvent updateEvent = (UpdateProductionSupportJuvenilePurgeEvent) event;
	//Juvenile obj = Juvenile.findJCJuvenile(updateEvent.getJuvenileId());
	JuvenileCore coreObj = JuvenileCore.findCore( updateEvent.getJuvenileId() );
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    Date currDate = new Date();
	    String today = sdf.format(currDate);
	    //jjsdetnCrtEvent.setCourtDate(today);
	if ( coreObj != null && updateEvent.getAction()==null)
	{
	    coreObj.setPurgeComments(updateEvent.getPurgeComments());
	    coreObj.setPurgeDate(currDate);
	    coreObj.setPurgeexecutedDate(today);
	    coreObj.setPurgeFlag("=");
	    coreObj.setPurgeSernum(updateEvent.getPurgeSeries());
	    coreObj.setPurgeBoxnum(updateEvent.getPurgeBox());
	    coreObj.setRecType("I.JUVENILE");
	    coreObj.setLastActionBy( updateEvent.getLastUpdateId() );
	    new Home().bind( coreObj );
	}
	else if ( coreObj != null && updateEvent.getAction().equalsIgnoreCase("unpurge"))
	{
	   /* coreObj.setPurgeDate(null);
	    coreObj.setPurgeexecutedDate(null);
	    coreObj.setPurgeFlag(null);*/ //uncomment if Carla wants this
	    coreObj.setPurgeComments(updateEvent.getPurgeComments());
	    coreObj.setPurgeSernum(updateEvent.getPurgeSeries());
	    coreObj.setPurgeBoxnum(updateEvent.getPurgeBox());
	    coreObj.setRecType("JUVENILE");
	    coreObj.setLastActionBy( updateEvent.getLastUpdateId() );
	    new Home().bind( coreObj );
	}
	else
	{
	    //Post error back	    
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    ErrorResponseEvent error = new ErrorResponseEvent();
	    error.setMessage("JUVENILE record not PURGED (JUVENILE NUM) with juvenileId: " + updateEvent.getJuvenileId());
	    dispatch.postEvent(error);
	}

    }

}
