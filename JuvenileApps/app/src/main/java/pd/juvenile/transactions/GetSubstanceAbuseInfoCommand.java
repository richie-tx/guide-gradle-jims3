package pd.juvenile.transactions;

import java.util.Iterator;

import pd.juvenile.SubstanceAbuse;

import messaging.juvenile.GetSubstanceAbuseInfoEvent;
import messaging.juvenile.reply.SubstanceAbuseResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetSubstanceAbuseInfoCommand implements ICommand
{
    public GetSubstanceAbuseInfoCommand(){}
    
    public void execute(IEvent event) {
	GetSubstanceAbuseInfoEvent getSubstanceAbuseInfoEvent = (GetSubstanceAbuseInfoEvent) event;
	Iterator substanceAubseInfoIter = SubstanceAbuse.findAll("casefileId", getSubstanceAbuseInfoEvent.getCasefileId() );
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	
	while( substanceAubseInfoIter.hasNext() ) {
	    SubstanceAbuse substanceAbuse = (SubstanceAbuse) substanceAubseInfoIter.next();
	    SubstanceAbuseResponseEvent substanceAbuseInfoResp = new SubstanceAbuseResponseEvent();
	    if ( substanceAbuse != null ) {
		substanceAbuseInfoResp.setSubstanceAbuseId(substanceAbuse.getOID());
		substanceAbuseInfoResp.setJuvenileNumber( substanceAbuse.getJuvenileId() );
		substanceAbuseInfoResp.setAssociatedCasefileId( substanceAbuse.getCasefileId());
		substanceAbuseInfoResp.setReferralNumber( substanceAbuse.getReferralNum() );
		substanceAbuseInfoResp.setSubstanceAbuse( substanceAbuse.getsAbuse() );
		substanceAbuseInfoResp.setSubstanceType( substanceAbuse.getSubstanceType() );
		substanceAbuseInfoResp.setCreateDate(substanceAbuse.getCreateTimestamp());		
		substanceAbuseInfoResp.setCreateUser(substanceAbuse.getCreateUserID());
		substanceAbuseInfoResp.setCreateJims2User(substanceAbuse.getCreateJIMS2UserID());
		substanceAbuseInfoResp.setUpdateJims2User(substanceAbuse.getUpdateJIMS2UserID()); 
		substanceAbuseInfoResp.setUpdateDate(substanceAbuse.getUpdateTimestamp()); 
		substanceAbuseInfoResp.setTreatmentLocation(substanceAbuse.getTreatmentLocation());
		
		dispatch.postEvent(substanceAbuseInfoResp);
	    }
	}
    }

}
