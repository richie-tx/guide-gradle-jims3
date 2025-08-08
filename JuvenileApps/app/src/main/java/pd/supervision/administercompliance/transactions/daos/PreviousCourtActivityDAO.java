package pd.supervision.administercompliance.transactions.daos;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;

import messaging.administercompliance.GetNCCourtActivitiesEvent;
import messaging.administercompliance.GetNCResponseComponentsEvent;
import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administercompliance.UpdateNCCommentEvent;
import messaging.administercompliance.UpdateNCCourtActivityEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.administercompliance.reply.NCPreviousCourtActivityResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.ViolationReportConstants;
import pd.common.DAOHandler;
import pd.supervision.administercompliance.Comment;
import pd.supervision.administercompliance.PreviousCourtActivity;
import pd.supervision.administercompliance.RecommendedApprovedCourtAction;
import pd.supervision.administercompliance.ViolationReport;
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
public class PreviousCourtActivityDAO extends ViolationReportUtility implements DAOHandler
{
	/**
	 * 
	 * @roseuid 473B85EB0040
	 */
	public PreviousCourtActivityDAO()
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
		deleteJims2Data(object.toString());
	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#update(java.lang.Object)
	 */
	public void update(Object object) {
		UpdateNCResponseEvent reqEvent = (UpdateNCResponseEvent) object;
		createViolationReport(reqEvent);
		
		Enumeration enumer = reqEvent.getRequests();
		PreviousCourtActivity pca = null;
		
		Map existingMap = PreviousCourtActivity.findAllByNumericParameter(ViolationReportConstants.PARAM_NCRESPONSE_ID, reqEvent.getNcResponseId()); 

		while(enumer.hasMoreElements()){
			Object obj = (Object) enumer.nextElement();
			if(obj instanceof UpdateNCCourtActivityEvent){
				UpdateNCCourtActivityEvent cEvent = (UpdateNCCourtActivityEvent) obj;
				if(cEvent.getCourtActivityId() == null || cEvent.getCourtActivityId().equals("")){
					pca = new PreviousCourtActivity();
					pca.setPreviousCourtActivity(cEvent,reqEvent.getNcResponseId());
					pca.commit();
				}else{
					pca = PreviousCourtActivity.find(cEvent.getCourtActivityId());
					if(pca != null && existingMap.containsKey(pca.getOID())){
						existingMap.remove(pca.getOID());
					}
				}
			}else if(obj instanceof UpdateNCCommentEvent){
				UpdateNCCommentEvent comEvent = (UpdateNCCommentEvent) obj;
				setComments (reqEvent.getRequestType(), reqEvent.getNcResponseId(), comEvent.getComments(),comEvent.getCommentsType());		
			}
		}
		
        // at this point existingmap only contains unwanted stuffs
		Iterator unwantedIter = existingMap.values().iterator();
		while(unwantedIter.hasNext()){
			pca = (PreviousCourtActivity) unwantedIter.next();
			if(pca != null){
				pca.delete();
				pca.commit();
			}
		}
		
		postDb2Data(reqEvent.getNcResponseId());
	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#get(java.lang.Object)
	 */
	public void get(Object object) {
		GetNCResponseComponentsEvent reqEvent = (GetNCResponseComponentsEvent) object;
	
		ViolationReport currentVR = ViolationReport.find(reqEvent.getNcResponseId());
		if(ViolationReportConstants.CREATE_MODE.equalsIgnoreCase(reqEvent.getMode())){
			GetNCCourtActivitiesEvent gEvent = (GetNCCourtActivitiesEvent) reqEvent.getRequests().nextElement();
			// during an update, only postLegacyData if it is the first time updating (records sent is zero)
			
			postNoDupsPreviousCourtActivityLegacyData(reqEvent.getNcResponseId(), gEvent, currentVR) ;
		}else{
			this.postDb2Data(reqEvent.getNcResponseId());
			postComments(ViolationReportConstants.REQUEST_PREVIOUS_COURT_ACTIVITY,reqEvent.getNcResponseId());
		}			
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#refresh(java.lang.Object)
	 */
	public void refresh(Object object) {
		RefreshNCResponseComponentsEvent refEvent = (RefreshNCResponseComponentsEvent) object;
		
		// delete jims2 data
		ViolationReport currentVR = ViolationReport.find(refEvent.getNcResponseId());
		if(currentVR != null){
			deleteJims2Data(refEvent.getNcResponseId());
			Comment comment = getComments(ViolationReportConstants.REQUEST_PREVIOUS_COURT_ACTIVITY, refEvent.getNcResponseId());
			if(comment != null){
				comment.delete();
			}		
		}
		
		// retrieve from Legacy
		GetNCCourtActivitiesEvent gEvent = new GetNCCourtActivitiesEvent();
		gEvent.setCaseId(refEvent.getCaseId());
		gEvent.setCdi(refEvent.getCdi());
		postNoDupsPreviousCourtActivityLegacyData(refEvent.getNcResponseId(),gEvent, currentVR);
	}
	
	/**
	 * @param ncResponseId
	 * @return
	 */
	private boolean postDb2Data(String ncResponseId){
		boolean isExist = false;
		Iterator iterator = PreviousCourtActivity.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			PreviousCourtActivity pca = (PreviousCourtActivity) iterator.next();
			NCPreviousCourtActivityResponseEvent resp = pca.getCourtActivityResponseEvent();
			MessageUtil.postReply(resp);
			isExist = true;
		}
		return isExist;
	}
	
	/**
	 * @param event
	 * @param ncResponseId
	 */
	private void postAllPreviousCourtActivityLegacyData1(String ncResponseId, Object object, ViolationReport currentVR) {
		GetNCCourtActivitiesEvent gEvent = (GetNCCourtActivitiesEvent) object;
		Iterator iterator = ViolationReport.findAll(ViolationReportConstants.CASE_ID, new StringBuffer(gEvent.getCdi()).append(gEvent.getCaseId()).toString());
		Integer apv = 0;
		// create a collection of all Violation Reports (Except current one), remove ones that do not meet status criteria, and then get the previous report based on
		// this current vr
		while(iterator.hasNext()){
			ViolationReport vr = (ViolationReport) iterator.next();
			// BR: include all reports for legacy that are PRESENTED (FL status) , and special case of Case Summary in COMPLETED (CD status) - RJC
			if(ViolationReportConstants.STATUS_FILED.equalsIgnoreCase(vr.getStatusId()) || 
					ViolationReportConstants.STATUS_COMPLETED.equalsIgnoreCase(vr.getStatusId())){
				// BR: If currentVR is NULL it is new and won't exist -- only get history from other reports, not itself
				if(currentVR == null || (!currentVR.getOID().equals(vr.getOID()))){
						//BR: if current report is NEW (currentVR=null, or DRAFT (status changed date is null), or later based on status changed date
						if( (currentVR == null) || (currentVR.getStatusChangedDate() == null) ||
								(vr.getStatusChangedDate() != null && (currentVR.getStatusChangedDate().after(vr.getStatusChangedDate())) ) ){					
							Iterator iter = PreviousCourtActivity.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, vr.getOID());
							// previous manually added court activities							
							while(iter.hasNext()){ 
								
								PreviousCourtActivity pca = (PreviousCourtActivity) iter.next();
								NCPreviousCourtActivityResponseEvent resp = pca.getCourtActivityResponseEvent();
								if (resp.getPreviousCourtActivityId() != null){
									resp.setPreviousCourtActivityId(new StringBuffer("L").append(resp.getPreviousCourtActivityId()).toString());
								} else {
									apv++;
									resp.setPreviousCourtActivityId(new StringBuffer("LA").append(apv.toString()).toString());
								}	
									MessageUtil.postReply(resp);
							}
						// get historical presented court activity 				
							NCPreviousCourtActivityResponseEvent resp = vr.getCourtActivityResponseEvent(ViolationReportConstants.REQUEST_PREVIOUS_COURT_ACTIVITY, vr.getReportType());
							String TypeCourtActionComment = getTypeOdCourtActionComment(vr.getOID());
							String SummaryOfActionComment = resp.getSummaryOfCourtAction();
							boolean isValidPresentedCourtActivity = true;
							// only add previous "PRESENTED" Previous Court Actions with at one of TypeComment or SummaryComment populated.
							if (resp.getTypeOfCourtActionComment() == null || "".equals(resp.getTypeOfCourtActionComment())) {
								if( (TypeCourtActionComment != null && !TypeCourtActionComment.equalsIgnoreCase("") )
									||(SummaryOfActionComment != null && !SummaryOfActionComment.equalsIgnoreCase("") )){
											resp.setTypeOfCourtActionComment(getTypeOdCourtActionComment(vr.getOID()));
								}else{
									isValidPresentedCourtActivity = false;
								}
							}				
							resp.setNcResponseId(ncResponseId);
							// set the type of previous court activity on response - VIOLATION or OTHER, based on Violation Report type
							if (vr.getReportType() != null && ViolationReportConstants.REPORTTYPE_VIOLATION.equalsIgnoreCase(vr.getReportType())) {
								resp.setSubType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_VIOLATION.toUpperCase());
							}else{
								resp.setSubType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_OTHER.toUpperCase());
							}
							if (resp.getPreviousCourtActivityId() != null){
								resp.setPreviousCourtActivityId(new StringBuffer("L").append(resp.getPreviousCourtActivityId()).toString());
							} else {
								apv++;
								resp.setPreviousCourtActivityId(new StringBuffer("LA").append(apv.toString()).toString());
							}	
							if( isValidPresentedCourtActivity){
								MessageUtil.postReply(resp);
							}
						}
				}
			}
		}
	}
	
	/**
	 * retrieve all historical "manual" previous court activities, remove dups based on occurrence date and subtype, and to 
	 * retrieve all historical CLO court action comments
	 * @param event
	 * @param ncResponseId
	 */
	private void postNoDupsPreviousCourtActivityLegacyData(String ncResponseId, Object object, ViolationReport currentVR) {
		GetNCCourtActivitiesEvent gEvent = (GetNCCourtActivitiesEvent) object;
		Iterator iterator = ViolationReport.findAll(ViolationReportConstants.CASE_ID, new StringBuffer(gEvent.getCdi()).append(gEvent.getCaseId()).toString());
		Integer apv = 0;
		HashMap<String,PreviousCourtActivity> dupHolder = new HashMap<String,PreviousCourtActivity>();
		// create a collection of all Violation Reports (Except current one), remove ones that do not meet status criteria, and then get the previous report based on
		// this current vr
		while(iterator.hasNext()){
			ViolationReport vr = (ViolationReport) iterator.next();
			// BR: include all reports for legacy that are PRESENTED (FL status) , and special case of Case Summary in COMPLETED (CD status) - RJC
			if(ViolationReportConstants.STATUS_FILED.equalsIgnoreCase(vr.getStatusId()) || 
					ViolationReportConstants.STATUS_COMPLETED.equalsIgnoreCase(vr.getStatusId())){
				// BR: If currentVR is NULL it is new and won't exist -- only get history from other reports, not itself
				if(currentVR == null || (!currentVR.getOID().equals(vr.getOID()))){
						//BR: if current report is NEW (currentVR=null, or DRAFT (status changed date is null), or later based on status changed date
						if( (currentVR == null) || (currentVR.getStatusChangedDate() == null) ||
								(vr.getStatusChangedDate() != null && (currentVR.getStatusChangedDate().after(vr.getStatusChangedDate())) ) ){					
							Iterator iter = PreviousCourtActivity.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, vr.getOID());
							// previous manually added court activities							
							while(iter.hasNext()){ 
								
								PreviousCourtActivity pca = (PreviousCourtActivity) iter.next();
								NCPreviousCourtActivityResponseEvent resp = pca.getCourtActivityResponseEvent();
								if (resp.getPreviousCourtActivityId() != null){
									resp.setPreviousCourtActivityId(new StringBuffer("L").append(resp.getPreviousCourtActivityId()).toString());
								} else {
									apv++;
									resp.setPreviousCourtActivityId(new StringBuffer("LA").append(apv.toString()).toString());
								}	
								if(isManuallyEnteredPreviousCourtActivityRecord(pca) && !isPreviousCourtActivityManuallyEnteredDup(pca, dupHolder)){
									MessageUtil.postReply(resp);
								}
							}
						// get historical presented court activity 				
							NCPreviousCourtActivityResponseEvent resp = vr.getCourtActivityResponseEvent(ViolationReportConstants.REQUEST_PREVIOUS_COURT_ACTIVITY, vr.getReportType());
							String TypeCourtActionComment = getTypeOdCourtActionComment(vr.getOID());
							String SummaryOfActionComment = resp.getSummaryOfCourtAction();
							boolean isValidPresentedCourtActivity = true;
							// only add previous "PRESENTED" Previous Court Actions with at one of TypeComment or SummaryComment populated.
							if (resp.getTypeOfCourtActionComment() == null || "".equals(resp.getTypeOfCourtActionComment())) {
								if( (TypeCourtActionComment != null && !TypeCourtActionComment.equalsIgnoreCase("") )
									||(SummaryOfActionComment != null && !SummaryOfActionComment.equalsIgnoreCase("") )){
											resp.setTypeOfCourtActionComment(getTypeOdCourtActionComment(vr.getOID()));
								}else{
									isValidPresentedCourtActivity = false;
								}
							}				
							resp.setNcResponseId(ncResponseId);
							// set the type of previous court activity on response - VIOLATION or OTHER, based on Violation Report type
							if (vr.getReportType() != null && ViolationReportConstants.REPORTTYPE_VIOLATION.equalsIgnoreCase(vr.getReportType())) {
								resp.setSubType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_VIOLATION.toUpperCase());
							}else{
								resp.setSubType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_OTHER.toUpperCase());
							}
							if (resp.getPreviousCourtActivityId() != null){
								resp.setPreviousCourtActivityId(new StringBuffer("L").append(resp.getPreviousCourtActivityId()).toString());
							} else {
								apv++;
								resp.setPreviousCourtActivityId(new StringBuffer("LA").append(apv.toString()).toString());
							}	
							if( isValidPresentedCourtActivity){
								MessageUtil.postReply(resp);
							}
						}
				}
			}
		}
	}
	
	/**
	 * @param ncResponseId
	 */
	private void deleteJims2Data(String ncResponseId) {
		Iterator iterator = PreviousCourtActivity.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			PreviousCourtActivity pca = (PreviousCourtActivity) iterator.next();
		    pca.delete();
		}
	}

	/**
	 * @param ncResponseId
	 * return string court action descriptions
	 */
	private String getTypeOdCourtActionComment(String theOID){
		String tocaStr = "";
		int strLen = 0;
		// find recommendation records and load court actions only if type = FL
       	RecommendedApprovedCourtAction rec = null;
       	Map existingMap = RecommendedApprovedCourtAction.findAllByNumericParameter(ViolationReportConstants.PARAM_NCRESPONSE_ID, theOID);
		Iterator recIter = existingMap.values().iterator();
		
		while(recIter.hasNext()){
			rec = (RecommendedApprovedCourtAction) recIter.next();
			strLen = rec.getCourtActionDescription().length() + tocaStr.length();
			// only include court actions with type of FL (not recommended but presented by court)
			if(rec.getType() != null && rec.getType().equalsIgnoreCase(ViolationReportConstants.STATUS_FILED)){
				if (strLen > 500){
					break;
				} else {	
					tocaStr += rec.getCourtActionDescription() + ",";
				}
			}
		}
		if (tocaStr.length() > 1){
			tocaStr = tocaStr.substring(0, tocaStr.length() -1);
		}
   	    return tocaStr;
	}	
	
	/**
	 * check the collection for an entry already handled based on the date key, and then check the found value to see
	 * if the subtype is the same as the currently evaluate previousCourtActivity value. If there is a match, return true and
	 * do not add to Map. Otherwise, add to map to record the dup for future.
	 * @param pca
	 * @param dupHolder
	 * @return
	 */
	private boolean isPreviousCourtActivityManuallyEnteredDup(PreviousCourtActivity pca,HashMap<String,PreviousCourtActivity> dupHolder){
		boolean result = false;
		String pcaKey = DateFormatUtils.format(pca.getOccurenceDate().getTime(), "MM/dd/yyyy") + pca.getSubType();
		PreviousCourtActivity alreadyProcessedPca = (PreviousCourtActivity) dupHolder.get(pcaKey);
		if(alreadyProcessedPca != null){
			if(alreadyProcessedPca.getSubType().equals(pca.getSubType())){
				result = true;
			}
		}else{
			dupHolder.put(pcaKey, pca);
		}
		return result;
	}
	
	/**
	 * determine if manually entered previous court activity record is manually entered, or came from CLO comment
	 * if CLO comment return false
	 * @param pca
	 * @return
	 */
	private boolean isManuallyEnteredPreviousCourtActivityRecord(PreviousCourtActivity pca){
		boolean result = false;
		if(pca.isManualAdded()){
			result = true;
		}
		return result;
	}
}
