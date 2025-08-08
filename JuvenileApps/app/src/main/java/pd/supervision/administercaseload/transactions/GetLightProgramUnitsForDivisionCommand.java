package pd.supervision.administercaseload.transactions;

import java.util.Iterator;

import messaging.administercaseload.GetLightProgramUnitsForDivisionEvent;
import messaging.administercaseload.reply.LightProgramUnitResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.supervisionstaff.Organization;

public class GetLightProgramUnitsForDivisionCommand implements ICommand
{
	/**
     * @roseuid 464DAC3F0391
     */
    public GetLightProgramUnitsForDivisionCommand()
    {

    }

    /**
     * @param event
     * @roseuid 464DA69202EA
     */
    public void execute(IEvent anEvent)
    {
    	GetLightProgramUnitsForDivisionEvent event = (GetLightProgramUnitsForDivisionEvent) anEvent;
    	if(event.getDivisionId() != null && !"".equals(event.getDivisionId())){
	    	Iterator iterator = Organization.findAll("parentOrganizationId", event.getDivisionId());
	    	while(iterator.hasNext()){
	    		Organization org = (Organization) iterator.next();
	    		LightProgramUnitResponseEvent resp = new LightProgramUnitResponseEvent();
	    		resp.setDivisionId(org.getParentOrganizationId());
	    		resp.setProgramUnitId(org.getOID());
	    		resp.setProgramUnitName(org.getDescription());
	    		MessageUtil.postReply(resp);
	    	}
    	}
    }

    /**
     * @param event
     * @roseuid 464DA69202F9
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 464DA69202FB
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 464DA6920308
     */
    public void update(Object anObject)
    {

    }

}
