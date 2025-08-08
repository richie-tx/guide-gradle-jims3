package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.Iterator;

import messaging.administercaseload.reply.LightProgramUnitResponseEvent;
import messaging.cscdstaffposition.GetLightProgramUnitEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.supervisionstaff.Organization;

public class GetLightProgramUnitCommand implements ICommand
{
	/**
     * @roseuid 464DAC3F0391
     */
    public GetLightProgramUnitCommand()
    {

    }

    /**
     * @param event
     * @roseuid 464DA69202EA
     */
    public void execute(IEvent anEvent)
    {
    	GetLightProgramUnitEvent event = (GetLightProgramUnitEvent) anEvent;
    	
    	if( event.getOrganizationId() != null && !"".equals( event.getOrganizationId())){
	    	
    		Iterator iterator = Organization.findAll("OID", event.getOrganizationId());
	    	
    		while(iterator.hasNext()){
	    		Organization org = ( Organization ) iterator.next();
	    		LightProgramUnitResponseEvent resp = new LightProgramUnitResponseEvent();
	    		resp.setDivisionId( org.getParentOrganizationId() );
	    		resp.setProgramUnitId( org.getOID() );
	    		resp.setProgramUnitName( org.getDescription() );
	    		resp.setStateReportingCD( org.getStateReporting() );
	    		
	    		MessageUtil.postReply( resp );
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
