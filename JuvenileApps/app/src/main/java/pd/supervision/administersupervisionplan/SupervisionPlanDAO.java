package pd.supervision.administersupervisionplan;

import java.util.List;

import messaging.administersupervisionplan.reply.SupervisionPlanDetailsResponseEvent;
import messaging.spnsplit.UpdateSpnSplitTopicEvent;
import mojo.km.utilities.MessageUtil;
import naming.SupervisionConstants;
import pd.common.DAOHandler;
/*
 * This DAO supports Spin Split operations for Assessment
 */
public class SupervisionPlanDAO implements DAOHandler
{
	public void execute(Object object) {
		// TODO Auto-generated method stub		
	}

	public void get(Object object) {
		String defendantId = (String) object;	
		List list = SupervisionPlan.findAll(SupervisionConstants.DEFENDANT_ID,defendantId);
		for(int i=0;i<list.size();i++){
			SupervisionPlan sp = (SupervisionPlan) list.get(i);
			SupervisionPlanDetailsResponseEvent resp = new SupervisionPlanDetailsResponseEvent();
			SupervisionPlanHelper.populateSupervisionPlanDetails(resp, sp);
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
			SupervisionPlan sp = SupervisionPlan.find(topicIds[i]);
			if(sp != null){
				sp.setDefendantId(uEvent.getValidDefendantId());	
			}
		}
	}
}
