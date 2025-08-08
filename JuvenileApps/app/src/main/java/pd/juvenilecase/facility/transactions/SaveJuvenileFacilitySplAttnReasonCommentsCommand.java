package pd.juvenilecase.facility.transactions;

import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.SaveJuvenileFacilitySplAttnReasonCommentsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.JJSSplAttnReasonComments;

public class SaveJuvenileFacilitySplAttnReasonCommentsCommand implements ICommand{

	@Override
	public void execute(IEvent event) throws Exception {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		SaveJuvenileFacilitySplAttnReasonCommentsEvent evt = (SaveJuvenileFacilitySplAttnReasonCommentsEvent)event;
		IHome home= new Home();
		
		JJSSplAttnReasonComments splAttentionComments = new JJSSplAttnReasonComments();
		splAttentionComments.setComments(evt.getComments());
		splAttentionComments.setJuvenileNum(evt.getJuvenileNum());
		splAttentionComments.setDetentionId(evt.getDetentionId());
		
		try{
			if(evt.getComments()!=null && !evt.getComments().isEmpty()){
				home.bind(splAttentionComments); //insert the comments.
			}
		}catch(Exception ex){ 
			ErrorResponseEvent errorEvt = new ErrorResponseEvent();
			errorEvt.setException(ex);
			errorEvt.setMessage("**** Exception while inserting JJSSplAttnReasonComments ****");
	 		dispatch.postEvent(evt);
		}
	}

}
