package pd.productionsupport.transactions;

import java.util.Iterator;
import java.util.List;

import naming.PDCodeTableConstants;

import pd.juvenile.SubstanceAbuse;
import pd.juvenilecase.JJSJuvenile;
import ui.common.CodeHelper;
import messaging.productionsupport.GetProductionSupportSubstanceAbuseInfoEvent;
import messaging.productionsupport.reply.ProductionSupportSubstanceAbuseInfoResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetProductionSupportSubstanceAbuseInfoCommand implements ICommand
{
    public void execute(IEvent event) {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetProductionSupportSubstanceAbuseInfoEvent getSubstanceAbuseEvent = (GetProductionSupportSubstanceAbuseInfoEvent) event;
	if ( getSubstanceAbuseEvent.getSubstanceAbuseId() != null
		&& getSubstanceAbuseEvent.getSubstanceAbuseId().length() > 0  ) {
	    SubstanceAbuse substanceAbuse = SubstanceAbuse.find(getSubstanceAbuseEvent.getSubstanceAbuseId());
	    if ( substanceAbuse != null ) {
		ProductionSupportSubstanceAbuseInfoResponseEvent substanceAbuseInfo = getSubstanceAbuseInfo(substanceAbuse);
		dispatch.postEvent(substanceAbuseInfo);
	    }
	} else {
	    Iterator<SubstanceAbuse> substanceAbuseIter = SubstanceAbuse.findAll("juvenileId", getSubstanceAbuseEvent.getJuvenileId());
	    while( substanceAbuseIter.hasNext() ){
		SubstanceAbuse substanceAbuse = (SubstanceAbuse) substanceAbuseIter.next();
		ProductionSupportSubstanceAbuseInfoResponseEvent substanceAbuseInfo = getSubstanceAbuseInfo(substanceAbuse);
		dispatch.postEvent(substanceAbuseInfo);
	    }
	}
    }
    
    
    private ProductionSupportSubstanceAbuseInfoResponseEvent getSubstanceAbuseInfo( SubstanceAbuse substanceAbuse ){
	ProductionSupportSubstanceAbuseInfoResponseEvent resp = new ProductionSupportSubstanceAbuseInfoResponseEvent();
	
	JJSJuvenile juvenileInfo = JJSJuvenile.find( substanceAbuse.getJuvenileId() );
	
	if ( juvenileInfo != null ){
	    String fullName = juvenileInfo.getLastName() + ", " + juvenileInfo.getFirstName();
	    resp.setFullName(fullName);
	    resp.setJuvenileId(juvenileInfo.getJuvenileNum());
	}
	resp.setSubstanceAbuseId(substanceAbuse.getOID()  );
	resp.setJuvenileId( substanceAbuse.getJuvenileId() );
	resp.setCasefileId( substanceAbuse.getCasefileId() );
	resp.setReferralNum( substanceAbuse.getReferralNum() );
	if ( substanceAbuse.getsAbuse() != null
		&& substanceAbuse.getsAbuse().length() > 0 ) {
	    resp.setsAbuseCode( substanceAbuse.getsAbuse() );
	}
	
	if ( substanceAbuse.getSubstanceType() != null
		&& substanceAbuse.getSubstanceType().length() > 0  ){
	    String[] substanceTypes = substanceAbuse.getSubstanceType().replace(" ", ""). split(",");	    
	    resp.setSubstanceTypeCode(substanceTypes);
	}
	
	resp.setTreatmentLocation( substanceAbuse.getTreatmentLocation() );
	
	return resp;
    }

}