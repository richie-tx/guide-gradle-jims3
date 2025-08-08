package pd.productionsupport.transactions;

import pd.juvenile.JuvenileCore;
import messaging.error.reply.ErrorResponseEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileSealEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.util.DateUtil;

public class UpdateProductionSupportJuvenileSealCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	// TODO Auto-generated method stub
	UpdateProductionSupportJuvenileSealEvent updateEvent = (UpdateProductionSupportJuvenileSealEvent) event;
	//Juvenile obj = Juvenile.findJCJuvenile(updateEvent.getJuvenileId());
	JuvenileCore coreObj = JuvenileCore.findCore( updateEvent.getJuvenileId() );

	/*if (coreObj != null && (updateEvent.getAction() != null && updateEvent.getAction().equalsIgnoreCase("unSealJuv")))*/ //commented for Bug 184895  
	if (coreObj != null && (updateEvent.getAction() != null && "unSealJuv".equalsIgnoreCase(updateEvent.getAction())))
	{
	    coreObj.setRecType("JUVENILE");
	    coreObj.setLastActionBy(updateEvent.getLastUpdateId());
	    new Home().bind(coreObj);
	}
	else
	    if (coreObj != null)
	    {
		coreObj.setSealComments(updateEvent.getSealComments());
		coreObj.setSealedDate(DateUtil.stringToDate(updateEvent.getSealedDate(), DateUtil.DATE_FMT_1));
		coreObj.setRecType("S.JUVENILE");
		coreObj.setLastActionBy(updateEvent.getLastUpdateId());
		new Home().bind(coreObj);
	    }
	    else
	    {
		//Post error back	    
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		ErrorResponseEvent error = new ErrorResponseEvent();
		error.setMessage("JUVENILE record not SEALED (JUVENILE NUM) with juvenileId: "
			+ updateEvent.getJuvenileId());
		dispatch.postEvent(error);
	    }

    }

}
