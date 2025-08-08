package pd.supervision.administercaseload.transactions;

import java.util.Iterator;

import messaging.administercaseload.GetCaseAssignmentsByDefendantIdEvent;
import messaging.administercaseload.GetCaseloadByDefendantEvent;
import messaging.administercaseload.reply.CaseloadInfoResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.administercaseload.CaseAssignmentOrder;

public class GetCaseAssignmentsByDefendantIdCommand implements ICommand
{

    /**
     * @roseuid 464360260252
     */
    public GetCaseAssignmentsByDefendantIdCommand()
    {

    }

    /**
     * @param event
     * @roseuid 46433E1C016C
     */
    public void execute(IEvent anEvent)
    {
    	GetCaseAssignmentsByDefendantIdEvent event = (GetCaseAssignmentsByDefendantIdEvent) anEvent;
       
    	GetCaseloadByDefendantEvent defEvent = new GetCaseloadByDefendantEvent();
    	String defendantId = event.getDefendantId();
    	
    	if( defendantId != null && defendantId.length() < 8 ){
    		while( defendantId.length() < 8 ){
    			defendantId = "0" + defendantId;
    		}
    	}
    	defEvent.setDefendantId( defendantId );
    	Iterator iter = CaseAssignmentOrder.findAllByOfficerEvent(defEvent);
    	
    	while(iter.hasNext()){
    		
    		CaseloadInfoResponseEvent response = new CaseloadInfoResponseEvent();
    		CaseAssignmentOrder co = (CaseAssignmentOrder) iter.next();
    		response.setAcknowledgeDate( co.getAcknowledgeDate());
    		response.setAcknowledgePositionId( co.getAcknowledgePositionId());
    		response.setAcknowledgeRoleId( co.getAcknowledgeRoleId());
    		response.setAcknowledgeStatusId( co.getAcknowledgeStatusId());
    		response.setAcknowledgeUserId( co.getAcknowledgeUserId());
    		response.setAllocatedStaffPositionId( co.getAllocatedStaffPositionId() );
    		response.setAssignedStaffPositionId( co.getAssignedStaffPositionId() );
    		response.setCaseAssignId( co.getCaseAssignId() );
    		response.setCaseState( co.getCaseState() );
    		response.setCaseStatusId( co.getCaseStatusId() );
    		response.setCourtId( co.getCourtId() );
    		response.setCriminalCaseId( co.getCriminalCaseId() );
    		response.setDefendantId( co.getDefendantId());
    		response.setDefendantName( co.getDefendantName());
    		response.setDefendantStatusId( co.getDefendantStatusId());
    		response.setJailInd( co.getJailInd());
    		response.setOffenseCode( co.getOffenseCode());
    		response.setOfficerAssignDate( co.getOfficerAssignDate());
    		response.setPmKeyCls( co.getPmKeyCls());
    		response.setProgramUnitId( co.getProgramUnitId());
    		response.setSupervisionBeginDate( co.getSupervisionBeginDate());
    		response.setSupervisionEndDate( co.getSupervisionEndDate());
    		response.setSupervisionOrderId( co.getSupervisionOrderId());
    		response.setSupervisionStyleId( co.getSupervisionStyleId());
    		response.setSupervisorAllocationDate( co.getSupervisorAllocationDate());
    		response.setTerminationDate( co.getTerminationDate());
    		response.setWarrantId( co.getWarrantInd());
    		
    		MessageUtil.postReply( response );
    		
    		response = null;
    		event    = null;
    		defEvent = null;
    		iter = null;
    	}
        
    }

     /**
     * @param event
     * @roseuid 46433E1C016E
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 46433E1C0170
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 46433E1C017C
     */
    public void update(Object anObject)
    {

    }
}
