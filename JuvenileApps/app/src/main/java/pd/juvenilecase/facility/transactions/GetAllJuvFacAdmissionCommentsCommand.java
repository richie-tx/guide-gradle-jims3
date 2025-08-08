package pd.juvenilecase.facility.transactions;

import java.util.Iterator;

import messaging.facility.GetAllJuvFacAdmissionCommentsEvent;
import messaging.facility.reply.JuvenileFacilityAdmissionCommentsResponseEvent;
import messaging.facility.reply.JuvenileFacilitySplAttnReasonResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.PDJuvenileConstants;
import pd.juvenilecase.JJSFacilityAdmissionComments;

/**
 * //added for u.s #51737
 * @author sthyagarajan
 *
 */
public class GetAllJuvFacAdmissionCommentsCommand implements ICommand {

	public GetAllJuvFacAdmissionCommentsCommand(){
		
	}
	
	
	@Override
	public void execute(IEvent event) throws Exception {
	
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetAllJuvFacAdmissionCommentsEvent evt = (GetAllJuvFacAdmissionCommentsEvent)event;
		Iterator<JJSFacilityAdmissionComments> admissionCommentsItr = JJSFacilityAdmissionComments.findAll(evt);
		
		while (admissionCommentsItr.hasNext()) {
			JJSFacilityAdmissionComments admissionComments = (JJSFacilityAdmissionComments)admissionCommentsItr.next();					
			JuvenileFacilityAdmissionCommentsResponseEvent responseEvent = new JuvenileFacilityAdmissionCommentsResponseEvent();
			responseEvent.setEntryDate( DateUtil.dateToString(admissionComments.getCreateDate(),DateUtil.DEFAULT_DATE_FMT));
			responseEvent.setEntryTime(DateUtil.getHHMMSSWithColonFromDate(admissionComments.getCreateDate()));
			responseEvent.setComments(admissionComments.getComments());
			responseEvent.setDetentionId(admissionComments.getDetentionId());
			responseEvent.setCreateUser(admissionComments.getCreateUserID());
			responseEvent.setTopic(PDJuvenileConstants.JUVENILE_FACILITY_TOPIC);
			dispatch.postEvent(responseEvent);
		}
	}
}
