package pd.juvenilecase.facility.transactions;

import java.util.Iterator;

import messaging.facility.GetJuvenileFacilitySplAttnReasonCommentsEvent;
import messaging.facility.reply.JuvenileFacilitySplAttnReasonResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.PDJuvenileConstants;
import pd.juvenilecase.JJSSplAttnReasonComments;


/**
 * Get all the history of comments from JJSSplAttnReasonComments.
 * @author sthyagarajan
 */
public class GetJuvenileFacilitySplAttnReasonCommentsCommand implements ICommand {
	
	public GetJuvenileFacilitySplAttnReasonCommentsCommand(){
		
	}

	@Override
	public void execute(IEvent event) throws Exception {
	
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetJuvenileFacilitySplAttnReasonCommentsEvent evt = (GetJuvenileFacilitySplAttnReasonCommentsEvent)event;
		Iterator<JJSSplAttnReasonComments> splAttentiontr = JJSSplAttnReasonComments.findAll(evt);
		
		while (splAttentiontr.hasNext()) {
			JJSSplAttnReasonComments splAttnReasonComments = (JJSSplAttnReasonComments)splAttentiontr.next();					
			JuvenileFacilitySplAttnReasonResponseEvent responseEvent = new JuvenileFacilitySplAttnReasonResponseEvent();
			responseEvent.setEntryDate( DateUtil.dateToString(splAttnReasonComments.getCreateDate(),DateUtil.DEFAULT_DATE_FMT));
			responseEvent.setEntryTime(DateUtil.getHHMMSSWithColonFromDate(splAttnReasonComments.getCreateDate()));
			responseEvent.setComments(splAttnReasonComments.getComments());
			responseEvent.setDetentionId(splAttnReasonComments.getDetentionId());
			responseEvent.setCreateUser(splAttnReasonComments.getCreateUserID());
			responseEvent.setCreateDate(splAttnReasonComments.getCreateDate());
			responseEvent.setTopic(PDJuvenileConstants.JUVENILE_FACILITY_TOPIC);
			dispatch.postEvent(responseEvent);
		}
	}
}
