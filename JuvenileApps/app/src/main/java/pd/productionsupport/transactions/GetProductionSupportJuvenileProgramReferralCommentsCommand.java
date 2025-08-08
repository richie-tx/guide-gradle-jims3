package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralCommentsEvent;
import messaging.programreferral.reply.ProgramReferralCommentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.programreferral.JuvenileProgramReferralComment;

public class GetProductionSupportJuvenileProgramReferralCommentsCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportJuvenileProgramReferralCommentsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportJuvenileProgramReferralCommentsEvent getJuvenileProgramReferralsEvent = (GetProductionSupportJuvenileProgramReferralCommentsEvent) event;
		Iterator referralCommentIter = JuvenileProgramReferralComment.findAll("programReferralId", getJuvenileProgramReferralsEvent.getJuvenileProgramReferralNum());
		
		while(referralCommentIter != null && referralCommentIter.hasNext()){
			JuvenileProgramReferralComment comment = (JuvenileProgramReferralComment)referralCommentIter.next();	
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
