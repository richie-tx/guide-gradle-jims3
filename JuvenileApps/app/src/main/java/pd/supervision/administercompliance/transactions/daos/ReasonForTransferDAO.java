package pd.supervision.administercompliance.transactions.daos;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import messaging.administercompliance.GetNCResponseComponentsEvent;
import messaging.administercompliance.GetVRCommentsEvent;
import messaging.administercompliance.UpdateNCReasonForTransferEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.ViolationReportConstants;
import pd.common.DAOHandler;
import pd.supervision.administercompliance.Comment;
import pd.supervision.administercompliance.ReasonForTransfer;
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
public class ReasonForTransferDAO extends ViolationReportUtility implements DAOHandler
{
	/**
	 * 
	 * @roseuid 473B85EB0040
	 */
	public ReasonForTransferDAO()
	{
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#execute(java.lang.Object)
	 */
	public void execute(Object object) {
		postDb2Data(object.toString());
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#remove(java.lang.Object)
	 */
	public void remove(Object object) {
		Iterator iterator = ReasonForTransfer.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, object.toString()); 
		while(iterator.hasNext()){
		    ReasonForTransfer rft = (ReasonForTransfer) iterator.next();
			rft.delete();
		}
		// Delete the comments too
		GetVRCommentsEvent vEvent = new GetVRCommentsEvent();
	    vEvent.setType( ViolationReportConstants.REQUEST_REASON_FOR_TRANSFER );
	    vEvent.setNcResponseId( object.toString() );
		Iterator vIter = Comment.findAll(vEvent);
		while( vIter.hasNext() ){
			Comment c = (Comment) vIter.next();
			c.delete();		
		}
	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#update(java.lang.Object)
	 */
	public void update(Object object) {
		UpdateNCResponseEvent reqEvent = (UpdateNCResponseEvent) object;
		createViolationReport(reqEvent);
		
		Enumeration enumer = reqEvent.getRequests();
		ReasonForTransfer rft = null;
		
		// delete existing Reasons for Transfer from CSVRTRANSREASON
		Map existingMap = ReasonForTransfer.findAllByNumericParameter(ViolationReportConstants.PARAM_NCRESPONSE_ID, reqEvent.getNcResponseId());
		Iterator unwantedIter = existingMap.values().iterator();
		while(unwantedIter.hasNext()){
			rft = (ReasonForTransfer) unwantedIter.next();
			if(rft != null){
				rft.delete();
				rft.commit();
			}
		}
		
		// add selected Reasons for Transfer to CSVRTRANSREASON
		while(enumer.hasMoreElements()){
			UpdateNCReasonForTransferEvent rEvent = (UpdateNCReasonForTransferEvent) enumer.nextElement();			
			rft = new ReasonForTransfer();
			rft.setReasonForTransfer(rEvent,reqEvent.getNcResponseId());
			rft.commit();
		}
		
		setComments (ViolationReportConstants.REQUEST_REASON_FOR_TRANSFER, reqEvent.getNcResponseId(), reqEvent);
		        
        postDb2Data(reqEvent.getNcResponseId());
	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#get(java.lang.Object)
	 */
	public void get(Object object) {
		GetNCResponseComponentsEvent reqEvent = (GetNCResponseComponentsEvent) object;

		this.postDb2Data(reqEvent.getNcResponseId());
		postComments(ViolationReportConstants.REQUEST_REASON_FOR_TRANSFER,reqEvent.getNcResponseId());
	}
	
	private boolean postDb2Data(String ncResponseId){
		boolean isExist = false;
		Iterator iterator = ReasonForTransfer.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			ReasonForTransfer rft = (ReasonForTransfer) iterator.next();
			CodeResponseEvent resp = rft.getResponse();
			MessageUtil.postReply(resp);
			isExist = true;
		}
		return isExist;
	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#refresh(java.lang.Object)
	 */
	public void refresh(Object object) {
		// TODO Auto-generated method stub		
	}
}
