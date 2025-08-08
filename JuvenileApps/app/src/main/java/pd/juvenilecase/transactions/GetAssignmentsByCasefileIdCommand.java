/*
 * Created on Aug 22, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.transactions;

import java.util.Date;
import java.util.Iterator;
import pd.juvenilecase.Assignment;
import messaging.juvenilecase.GetAssignmentsByCasefileIdEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetAssignmentsByCasefileIdCommand implements ICommand {
	
	public GetAssignmentsByCasefileIdCommand() {}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetAssignmentsByCasefileIdEvent req = (GetAssignmentsByCasefileIdEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		Iterator iter = Assignment.findAll("caseFileId", req.getCasefileId());
			while (iter.hasNext())
			{
				Assignment assignment = (Assignment) iter.next();
				AssignmentResponseEvent resp = new AssignmentResponseEvent();
				
				resp.setAssignmentId(assignment.getOID());
				resp.setCaseFileId(assignment.getCaseFileId());
				resp.setReferralNum(assignment.getReferralNumber());
				resp.setAssignmentDate(assignment.getAssignmentAddDate());
				resp.setJpoUserId(assignment.getAssignByUserId());
				resp.setCaseloadManagerId(assignment.getCaseFileId());
				resp.setServiceUnitId(assignment.getServiceUnitId());
				resp.setAssessmentLevelId(assignment.getAssignmentLevelId());
				resp.setWasChecked(false);
				resp.setWasMigrated(false);
				resp.setIsDup(false);
				resp.setAssessmentLevelId(assignment.getAssignmentLevelId());
				resp.setAssignmentType(assignment.getAssignmentType());
				resp.setRefSeqNum(assignment.getSeqNum());
				if(assignment.getCreateUserID() != null){
					resp.setCreateUserID(assignment.getCreateUserID());
				}
				if(assignment.getCreateTimestamp() != null){
					resp.setCreateDate(new Date(assignment.getCreateTimestamp().getTime()));
				}
				if(assignment.getUpdateUserID() != null){
					resp.setUpdateUser(assignment.getUpdateUserID());
				}
				if(assignment.getUpdateTimestamp() != null){
					resp.setUpdateDate(new Date(assignment.getUpdateTimestamp().getTime()));
				}
				if(assignment.getCreateJIMS2UserID() != null){
					resp.setCreateJIMS2UserID(assignment.getCreateJIMS2UserID());
				}
				if(assignment.getUpdateJIMS2UserID() != null){
					resp.setUpdateJIMS2UserID(assignment.getUpdateJIMS2UserID());
				}
				
				dispatch.postEvent(resp);
			}
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub
		
	}

}