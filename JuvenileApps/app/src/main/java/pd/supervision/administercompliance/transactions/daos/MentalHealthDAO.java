package pd.supervision.administercompliance.transactions.daos;

import java.util.Iterator;

import messaging.administercompliance.GetNCResponseComponentsEvent;
import messaging.administercompliance.GetVRPreviousCourtCommentsEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import naming.ViolationReportConstants;
import pd.common.DAOHandler;
import pd.supervision.administercompliance.Comment;
import pd.supervision.administercompliance.ViolationReportUtility;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * When a condition is set to non-compliant, the event(s) leading to this are
 * documented.  Event Types come from Events configured in the Condition in MSO.
 */
public class MentalHealthDAO extends ViolationReportUtility implements DAOHandler
{
	/**
	 * 
	 * @roseuid 473B85EB0040
	 */
	public MentalHealthDAO()
	{
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#execute(java.lang.Object)
	 */
	public void execute(Object object) {
		
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#remove(java.lang.Object)
	 */
	public void remove(Object object) {
		deleteJims2Data(object.toString(),ViolationReportConstants.REQUEST_MENTAL_HEALTH_DIAGNOSIS);
		deleteJims2Data(object.toString(),ViolationReportConstants.REQUEST_MENTAL_HEALTH_COMMENTS);
	}
	
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#update(java.lang.Object)
	 */
	public void update(Object object) {
		UpdateNCResponseEvent reqEvent = (UpdateNCResponseEvent) object;
		createViolationReport(reqEvent);
		
		setComments ( reqEvent.getRequestType(), reqEvent.getNcResponseId(), reqEvent);

	}
	

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#get(java.lang.Object)
	 */
	public void get(Object object) {
		
		GetNCResponseComponentsEvent reqEvent = (GetNCResponseComponentsEvent) object;
		postComments(reqEvent.getRequestType(),reqEvent.getNcResponseId());

	}

	/**
	 * @param ncResponseId
	 * @return
	 */
	private void postDb2Data(String ncResponseId, UpdateNCResponseEvent reqEvent ) {

		setComments (reqEvent.getReportType() , reqEvent.getNcResponseId(), reqEvent);
	}

		
	/**
	 * @param ncResponseId
	 */
	private void deleteJims2Data(String ncResponseId, String requestType) {
		
		GetVRPreviousCourtCommentsEvent pEvent = new GetVRPreviousCourtCommentsEvent();
	    pEvent.setRequestType(requestType);
	    pEvent.setNcResponseId(ncResponseId);
		Iterator vIter = Comment.findAll(pEvent);
		while ( vIter.hasNext() ){
			Comment c = (Comment) vIter.next();
			c.delete();
		}
	}

	@Override
	public void refresh(Object object) {
		// TODO Auto-generated method stub
		
	}
}
