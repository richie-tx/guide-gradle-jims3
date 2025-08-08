package pd.juvenilecase.facility.transactions;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import messaging.facility.GetAllJuvFacSplAttnReasonCommentsEvent;
import messaging.facility.reply.JuvenileFacilitySplAttnReasonResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.PDJuvenileConstants;
import pd.juvenilecase.JJSSplAttnReasonComments;

public class GetAllJuvFacSplAttnReasonCommentsCommand implements ICommand {

	public GetAllJuvFacSplAttnReasonCommentsCommand(){
		
	}
	
	
	@Override
	public void execute(IEvent event) throws Exception {
	
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetAllJuvFacSplAttnReasonCommentsEvent evt = (GetAllJuvFacSplAttnReasonCommentsEvent)event;
		Iterator<JJSSplAttnReasonComments> splAttentiontr = JJSSplAttnReasonComments.findAll(evt);
		
		while (splAttentiontr.hasNext()) {
			JJSSplAttnReasonComments splAttnReasonComments = (JJSSplAttnReasonComments)splAttentiontr.next();
			String commentsWithoutTimestamp="";
			if(splAttnReasonComments.getComments()!=null && !splAttnReasonComments.getComments().isEmpty())
				commentsWithoutTimestamp = (StringUtils.split(splAttnReasonComments.getComments(), '[')[0]).trim();
			JuvenileFacilitySplAttnReasonResponseEvent responseEvent = new JuvenileFacilitySplAttnReasonResponseEvent();
			responseEvent.setEntryDate( DateUtil.dateToString(splAttnReasonComments.getCreateDate(),DateUtil.DEFAULT_DATE_FMT));
			responseEvent.setEntryTime(DateUtil.getHHMMSSWithColonFromDate(splAttnReasonComments.getCreateDate()));
			responseEvent.setComments(commentsWithoutTimestamp);
			responseEvent.setDetentionId(splAttnReasonComments.getDetentionId());
			responseEvent.setCreateUser(splAttnReasonComments.getCreateUserID());
			responseEvent.setTopic(PDJuvenileConstants.JUVENILE_FACILITY_TOPIC);
			dispatch.postEvent(responseEvent);
		}
	}
}
