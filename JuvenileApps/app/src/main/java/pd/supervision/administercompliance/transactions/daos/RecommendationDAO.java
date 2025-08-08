package pd.supervision.administercompliance.transactions.daos;

import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import messaging.administercompliance.GetNCResponseComponentsEvent;
import messaging.administercompliance.UpdateNCRecommendationEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ViolationReportConstants;
import pd.common.DAOHandler;
import pd.supervision.administercompliance.RecommendedApprovedCourtAction;
import pd.supervision.administercompliance.ViolationReport;
import pd.supervision.administercompliance.ViolationReportUtility;
import pd.task.Task;

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
public class RecommendationDAO extends ViolationReportUtility implements DAOHandler
{
	/**
	 * 
	 * @roseuid 473B85EB0040
	 */
	public RecommendationDAO()
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
		Iterator iterator = RecommendedApprovedCourtAction.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, object.toString()); 
		while(iterator.hasNext()){
			RecommendedApprovedCourtAction r = (RecommendedApprovedCourtAction) iterator.next();
			r.delete();
		}
	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#update(java.lang.Object)
	 */
	public void update(Object object) {
		UpdateNCResponseEvent reqEvent = (UpdateNCResponseEvent) object;
		createViolationReport(reqEvent);
		final int MAX_MSG_LENGTH = 3500;
		
		if(reqEvent.getNcResponseId() != null && !reqEvent.getNcResponseId().equals("") && reqEvent.isFiling()){
			ViolationReport vr = ViolationReport.find(reqEvent.getNcResponseId());
			if(vr != null){
				vr.setFiledDate(reqEvent.getFilledDate());
				if(!reqEvent.isUpdatePresentedRequest()){ // only set the current date as status changed date if this is a filing / not just for an update
					vr.setStatusChangedDate(new Timestamp(DateUtil.getCurrentDate().getTime()));
				}
				vr.setPresentedBy(reqEvent.getPresentedBy());
				vr.setPresentedByPositionId(reqEvent.getPositionIdOfPresentedBy());
				vr.setSignedBy(reqEvent.getSignedBy());
				vr.setSignedByPositionId(reqEvent.getPositionIdOfSignedBy());
				vr.setStatusId(reqEvent.getStatusId());
				String text = reqEvent.getSummaryOfCourtActions();
	            if (text != null && text.length() > MAX_MSG_LENGTH){
	            	text = text.substring(0,MAX_MSG_LENGTH);
	            }
				vr.setCourtActionSummary(text);
				reqEvent.setComments(text);
			}
		}
		
		Enumeration enumer = reqEvent.getRequests();
		RecommendedApprovedCourtAction rec = null;

		Map existingMap = RecommendedApprovedCourtAction.findAllByNumericParameter(ViolationReportConstants.PARAM_NCRESPONSE_ID, reqEvent.getNcResponseId()); 
		String recommendType = "";
		while(enumer.hasMoreElements()){
			UpdateNCRecommendationEvent rEvent = (UpdateNCRecommendationEvent) enumer.nextElement();
			recommendType = rEvent.getType();
			if(rEvent.getRecommendationId() == null || rEvent.getRecommendationId().equals("")){
				rec = new RecommendedApprovedCourtAction();
				rec.setRecomendation(rEvent, reqEvent.getNcResponseId());
				rec.commit();
			}else{
				rec = RecommendedApprovedCourtAction.find(rEvent.getRecommendationId());
				if(rec != null && existingMap.containsKey(rec.getOID())){
					existingMap.remove(rec.getOID());
				}
			}
		}
		
		if(!reqEvent.isUpdatePresentedRequest()){
			setComments (ViolationReportConstants.REQUEST_RECOMMENDATION, reqEvent.getNcResponseId(), reqEvent.getComments(),reqEvent.getCommentType());
		}
       
        // at this point existingmap only contains unwanted stuffs
		Iterator unwantedIter = existingMap.values().iterator();
		while(unwantedIter.hasNext()){
			rec = (RecommendedApprovedCourtAction) unwantedIter.next();
			if(rec != null && recommendType.equalsIgnoreCase(rec.getType())){
				rec.delete();
				rec.commit();
			}
		}
		
		if(reqEvent.isFiling()){
			Task task = Task.find(reqEvent.getTaskId());
		    if(task != null){
		    	task.setStatusId("C");
		    }
		}	
		postDb2Data(reqEvent.getNcResponseId());
	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#get(java.lang.Object)
	 */
	public void get(Object object) {
		GetNCResponseComponentsEvent reqEvent = (GetNCResponseComponentsEvent) object;
		this.postDb2Data(reqEvent.getNcResponseId());
		postComments(ViolationReportConstants.REQUEST_RECOMMENDATION,reqEvent.getNcResponseId());
	}
	
	private boolean postDb2Data(String ncResponseId){
		boolean isExist = false;
		Iterator iterator = RecommendedApprovedCourtAction.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			RecommendedApprovedCourtAction r = (RecommendedApprovedCourtAction) iterator.next();
			CodeResponseEvent resp = r.getResponse();
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
