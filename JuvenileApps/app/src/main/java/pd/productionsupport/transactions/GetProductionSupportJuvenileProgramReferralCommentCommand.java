package pd.productionsupport.transactions;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralCommentEvent;
import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralsEvent;
import messaging.productionsupport.RetrieveJuvenileProgramReferralByReferralNumberEvent;
import messaging.productionsupport.RetrieveJuvenileProgramReferralsEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileProgramReferralResponseEvent;
import messaging.programreferral.reply.ProgramReferralCommentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import pd.supervision.programreferral.JuvenileProgramReferral;
import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;
import pd.supervision.programreferral.JuvenileProgramReferralComment;

public class GetProductionSupportJuvenileProgramReferralCommentCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportJuvenileProgramReferralCommentCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetProductionSupportJuvenileProgramReferralCommentEvent getJuvenileReferralcommentEvent = (GetProductionSupportJuvenileProgramReferralCommentEvent) event;
		if (getJuvenileReferralcommentEvent.getReferralId() != null && getJuvenileReferralcommentEvent.getReferralId().length() > 0)
		{
		
		    Iterator referralCommentIter = JuvenileProgramReferralComment.findAll("OID", getJuvenileReferralcommentEvent.getReferralId());
		    if (referralCommentIter!=null && referralCommentIter.hasNext())
		    {
			JuvenileProgramReferralComment comment = (JuvenileProgramReferralComment) referralCommentIter.next();
			ProgramReferralCommentResponseEvent referralCommentResponseEvent = new ProgramReferralCommentResponseEvent();
			
			referralCommentResponseEvent.setProgramReferralCommentId(comment.getOID());
			referralCommentResponseEvent.setProgramReferralId(comment.getProgramReferralId());
			referralCommentResponseEvent.setCommentText(comment.getCommentText());
			referralCommentResponseEvent.setCommentsDate(comment.getCommentDate());
			referralCommentResponseEvent.setUserName(comment.getUserName());
			
			//production support 
			if(comment.getCreateUserID() != null){
				referralCommentResponseEvent.setCreateUserID(comment.getCreateUserID());
			}
			if(comment.getCreateTimestamp() != null){
				referralCommentResponseEvent.setCreateDate(new Date(comment.getCreateTimestamp().getTime()));
			}
			if(comment.getUpdateUserID() != null){
				referralCommentResponseEvent.setUpdateUser(comment.getUpdateUserID());
			}
			if(comment.getUpdateTimestamp() != null){
				referralCommentResponseEvent.setUpdateDate(new Date(comment.getUpdateTimestamp().getTime()));
			}
			if(comment.getCreateJIMS2UserID() != null){
				referralCommentResponseEvent.setCreateJIMS2UserID(comment.getCreateJIMS2UserID());
			}
			if(comment.getUpdateJIMS2UserID() != null){
				referralCommentResponseEvent.setUpdateJIMS2UserID(comment.getUpdateJIMS2UserID());
			}
			dispatch.postEvent(referralCommentResponseEvent);
		    
		    }
		}
		
	}

	/**
	 * @param event
	 * @roseuid 4278C7B8034F
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80359
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4278C7B80364
	 */
	public void update(Object anObject) {

	}
}
