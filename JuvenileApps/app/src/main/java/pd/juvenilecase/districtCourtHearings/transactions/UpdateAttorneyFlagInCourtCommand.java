package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.ArrayList;
import java.util.Iterator;

import messaging.districtCourtHearings.UpdateAttorneyFlagInCourtEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;

public class UpdateAttorneyFlagInCourtCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdateAttorneyFlagInCourtEvent updateEvent = (UpdateAttorneyFlagInCourtEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	if (updateEvent.getRecType().equalsIgnoreCase("COURT"))
	{
	    Iterator<JJSCourt> objIter = new ArrayList<JJSCourt>().iterator(); // empty   iterator
	    objIter = JJSCourt.findAll("OID", updateEvent.getDocketId());
	    while (objIter.hasNext())
	    {

		JJSCourt crt = (JJSCourt) objIter.next();
		if (crt != null && crt.getRectype().equalsIgnoreCase("COURT"))
		{
		    crt.setAtyConfirmation("YES");
		    //crt.setAttorneyName(updateEvent.getAttorneyName());
		    //crt.setAttorneyConnection(updateEvent.getAttorneyConnection());
		    //crt.setBarNumber(updateEvent.getBarNumber());
		    
		    new Home().bind(crt);
		}

	    }
	}
	if (updateEvent.getRecType().equalsIgnoreCase("DETENTION"))
	{
	    Iterator<JJSCLDetention> objIter = new ArrayList<JJSCLDetention>().iterator(); // empty   iterator
	    objIter = JJSCLDetention.findAll("OID", updateEvent.getDocketId());
	    while (objIter.hasNext())
	    {

		JJSCLDetention crt = (JJSCLDetention) objIter.next();
		if (crt != null && crt.getRecType().equalsIgnoreCase("DETENTION"))
		{
		    crt.setAtyConfirmation("YES");
		    //crt.setAttorneyName(updateEvent.getAttorneyName());
		    //crt.setAttorneyConnection(updateEvent.getAttorneyConnection());
		    //crt.setBarNumber(updateEvent.getBarNumber());
		    new Home().bind(crt);
		}

	    }
	}

    }

}
