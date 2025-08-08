package pd.supervision.administercaseload.transactions;

import java.util.Iterator;

import messaging.administercaseload.GetSuperviseeByDefendantIdEvent;
import messaging.administercaseload.reply.LightSuperviseeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.administercaseload.SupervisionLevelOfServiceCode;

public class GetSuperviseeByDefendantIdCommand implements ICommand
{

    /**
     * @roseuid 464360260252
     */
    public GetSuperviseeByDefendantIdCommand()
    {

    }

    /**
     * @param event
     * @roseuid 46433E1C016C
     */
    public void execute(IEvent anEvent)
    {
    	GetSuperviseeByDefendantIdEvent event = ( GetSuperviseeByDefendantIdEvent ) anEvent;
       
     	String defendantId = event.getDefendantId();
    	
    	if( defendantId != null && defendantId.length() < 8 ){
    		while( defendantId.length() < 8 ){
    			defendantId = "0" + defendantId;
    		}
    	}
 
    	Iterator iter = Supervisee.findAll("defendantId", defendantId ) ;
    	
    	while(iter.hasNext()){
    		
    		LightSuperviseeResponseEvent response = new LightSuperviseeResponseEvent();
    		
    		Supervisee superviseeInfo = (Supervisee) iter.next();
    		response.setSuperviseeId( superviseeInfo.getOID() );
    		response.setAssignedProgramUnitId( superviseeInfo.getAssignedProgramUnitId() );
    		response.setCreditStaffPositionId( superviseeInfo.getCaseloadCreditStaffPositionId() );
    		response.setCurrentlySupervised( superviseeInfo.isCurrentlySupervised() );
    		
    		SupervisionLevelOfServiceCode sprvisionLevel = superviseeInfo.getSupervisionLevel();
    		if ( sprvisionLevel != null ){
    			response.setSupervisionLevelId( sprvisionLevel.getOID() );
    		}
    		response.setLosEffectiveDate( superviseeInfo.getLosEffectiveDate() );
    		response.setDefendantId( superviseeInfo.getDefendantId() );
    		response.setProgramUnit( superviseeInfo.getAssignedProgramUnitId() );
    		response.setProgramUnitAssignmentDate( superviseeInfo.getProgramUnitAssignmentDate() );
    		
    		MessageUtil.postReply( response );
    		
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
