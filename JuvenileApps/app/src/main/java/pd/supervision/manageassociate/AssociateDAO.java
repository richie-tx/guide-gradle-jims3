package pd.supervision.manageassociate;

import java.util.Iterator;

import messaging.manageassociate.reply.AssociateResponseEvent;
import messaging.spnsplit.UpdateSpnSplitTopicEvent;
import mojo.km.utilities.MessageUtil;
import pd.common.DAOHandler;
/*
 * This DAO supports Spin Split operations for Assessment
 */
public class AssociateDAO implements DAOHandler
{
	public void execute(Object object) {
		// TODO Auto-generated method stub		
	}

	public void get(Object object) {
		String defendantId = (String) object;	
		Iterator iter = SuperviseeAssociate.findAll(defendantId);
		while(iter.hasNext()){
			SuperviseeAssociate sa = (SuperviseeAssociate) iter.next();
			AssociateResponseEvent resp = PDManageAssociateHelper.getThinAssociateResponseEvent(sa);
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
			SuperviseeAssociate sa = SuperviseeAssociate.find(topicIds[i]);
			sa.setSpn(uEvent.getValidDefendantId());	
		}
	}
}
