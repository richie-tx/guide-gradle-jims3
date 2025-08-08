package pd.supervision.administerassessments;

import java.util.List;

import messaging.administerassessments.reply.AssessmentSummaryResponseEvent;
import messaging.spnsplit.UpdateSpnSplitTopicEvent;
import mojo.km.utilities.MessageUtil;
import naming.SupervisionConstants;
import pd.common.DAOHandler;
import pd.supervision.administercaseload.Supervisee;
/*
 * This DAO supports Spin Split operations for Assessment
 */
public class AssessmentDAO implements DAOHandler
{
	public void execute(Object object) {
		// TODO Auto-generated method stub		
	}

	public void get(Object object) {
		String defendantId = (String) object;	
		List list = Assessment.findAll(SupervisionConstants.DEFENDANT_ID, defendantId);
		for(int i=0;i<list.size();i++){
			Assessment assessment = (Assessment) list.get(i);
			AssessmentSummaryResponseEvent resp = assessment.getResponseEvent();
			MessageUtil.postReply(resp);			
		}
	}

	public void refresh(Object object) {
		// TODO Auto-generated method stub		
	}

	public void remove(Object object) {
		// TODO Auto-generated method stub		
	}

	public void update(Object object) {
		UpdateSpnSplitTopicEvent uEvent = (UpdateSpnSplitTopicEvent) object;	
		String[] topicIds = uEvent.getTopicIds();
		for(int i=0;i<topicIds.length;i++){
			Assessment assessment = Assessment.find(topicIds[i]);
			assessment.setDefendantId(uEvent.getValidDefendantId());	
		}
		
        Supervisee supervisee = Supervisee.findByDefendantId(uEvent.getValidDefendantId());
        if(supervisee == null){
        	supervisee = new Supervisee();
    		//supervisee.setDefendantId(uEvent.getValidDefendantId());
        }
	}
}
