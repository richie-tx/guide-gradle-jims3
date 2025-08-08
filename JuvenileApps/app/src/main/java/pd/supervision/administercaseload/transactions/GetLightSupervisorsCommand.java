package pd.supervision.administercaseload.transactions;

import naming.PDConstants;
import messaging.administercaseload.GetLightSupervisorsEvent;
import messaging.administercaseload.reply.LightSupervisorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.administercaseload.CaseAssignmentHelper;

public class GetLightSupervisorsCommand implements ICommand
{
	/**
     * @roseuid 464DAC3F0391
     */
    public GetLightSupervisorsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 464DA69202EA
     */
    public void execute(IEvent anEvent)
    {
    	GetLightSupervisorsEvent event = (GetLightSupervisorsEvent) anEvent;    	
    	if((event.getStaffPositionId() != null && !PDConstants.BLANK.equals(event.getStaffPositionId())) 
    			|| (event.getLogonId() != null && !PDConstants.BLANK.equals(event.getLogonId()))){
        	String organizationId = PDConstants.BLANK;
        	String parentPositionId = PDConstants.BLANK;
        	LightSupervisorResponseEvent resp = CaseAssignmentHelper.postLightSupervisorResponse(event);
    		if(resp != null){
    			organizationId = resp.getDivisionId();
    			parentPositionId = resp.getSupervisorPositionId();
    		}
    		
        	if(!"".equals(organizationId)){
                CaseAssignmentHelper.postSupervisorsInDivision(event, organizationId);
        	}
        		
    		if(!"".equals(parentPositionId) && event.isOfficerInfoNeeded()){
                CaseAssignmentHelper.postOfficersUnderSupervisor(parentPositionId);
    	    }
    	}else if(event.getDivisionId() != null && !PDConstants.BLANK.equals(event.getDivisionId())){
        	CaseAssignmentHelper.postSupervisorsInDivision(event, event.getDivisionId());
		}else if(event.getSupervisorId() != null && !PDConstants.BLANK.equals(event.getSupervisorId())){
			
			// RRY added to return officers and support staff
			if( event.isSupportStaffNeeded() ){
    			
    			CaseAssignmentHelper.postStaffUnderSupervisor( event.getSupervisorId() );
    		}else{
    			
    			CaseAssignmentHelper.postOfficersUnderSupervisor(event.getSupervisorId());
    		}
		}else{
    		CaseAssignmentHelper.postSupervisorsInDivision(event, null);
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
