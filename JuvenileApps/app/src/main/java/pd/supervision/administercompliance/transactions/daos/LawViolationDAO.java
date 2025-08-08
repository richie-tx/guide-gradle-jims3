package pd.supervision.administercompliance.transactions.daos;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import messaging.administercompliance.GetNCLawViolationsEvent;
import messaging.administercompliance.GetNCResponseComponentsEvent;
import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administercompliance.UpdateNCLawViolationEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.administercompliance.reply.NCLawViolationResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.ViolationReportConstants;
import pd.common.DAOHandler;
import pd.supervision.administercompliance.Comment;
import pd.supervision.administercompliance.LawViolation;
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
public class LawViolationDAO extends ViolationReportUtility implements DAOHandler
{
	/**
	 * 
	 * @roseuid 473B85EB0040
	 */
	public LawViolationDAO()
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
		LawViolation lv = null;

		Map existingMap = LawViolation.findAllByNumericParameter(ViolationReportConstants.PARAM_NCRESPONSE_ID, reqEvent.getNcResponseId()); 

		while(enumer.hasMoreElements()){
			UpdateNCLawViolationEvent lvEvent = (UpdateNCLawViolationEvent) enumer.nextElement();
			if(lvEvent.getLawViolationId() == null || lvEvent.getLawViolationId().equals("")){
				lv = new LawViolation();
				lv.setLawViolation(lvEvent,reqEvent.getNcResponseId());
				lv.commit();
			}else{
				lv = LawViolation.find(lvEvent.getLawViolationId());
				if(lv != null && existingMap.containsKey(lv.getOID())){
					existingMap.remove(lv.getOID());
				}
			}
		}
		setComments (ViolationReportConstants.REQUEST_LAW_VIOLATION, reqEvent.getNcResponseId(), reqEvent);

		// at this point existingmap only contains unwanted stuffs
		Iterator unwantedIter = existingMap.values().iterator();
		while(unwantedIter.hasNext()){
			lv = (LawViolation) unwantedIter.next();
			if(lv != null){
				lv.delete();
				lv.commit();
			}
		}
		
		postDb2Data(reqEvent.getNcResponseId());
	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#get(java.lang.Object)
	 */
	public void get(Object object) {
		GetNCResponseComponentsEvent reqEvent = (GetNCResponseComponentsEvent) object;

		if(ViolationReportConstants.CREATE_MODE.equalsIgnoreCase(reqEvent.getMode())){
			GetNCLawViolationsEvent gEvent = (GetNCLawViolationsEvent) reqEvent.getRequests().nextElement();
			postLegacyData(reqEvent.getNcResponseId(),gEvent);			
		}else{
			this.postDb2Data(reqEvent.getNcResponseId());
			postComments(ViolationReportConstants.REQUEST_LAW_VIOLATION,reqEvent.getNcResponseId());
		}
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#refresh(java.lang.Object)
	 */
	public void refresh(Object object) {
		RefreshNCResponseComponentsEvent refEvent = (RefreshNCResponseComponentsEvent) object;
		
		// delete jims2 data
		ViolationReport vr = ViolationReport.find(refEvent.getNcResponseId());
		if(vr != null){
			deleteJims2Data(refEvent.getNcResponseId());
			Comment comment = getComments(ViolationReportConstants.REQUEST_LAW_VIOLATION, refEvent.getNcResponseId());
			if(comment != null){
				comment.delete();
			}		
		}
		
		// retrieve from Legacy
		GetNCLawViolationsEvent gEvent = new GetNCLawViolationsEvent();
		gEvent.setDefendantId(refEvent.getDefendantId());
		gEvent.setCaseNum( refEvent.getCaseId() );
		gEvent.setActivationDate(refEvent.getActivationDate());
		postLegacyData(refEvent.getNcResponseId(),gEvent);
	}
	
	/**
	 * @param ncResponseId
	 * @return
	 */
	private boolean postDb2Data(String ncResponseId){
		boolean isExist = false;
		Iterator iterator = LawViolation.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			LawViolation lv = (LawViolation) iterator.next();
			NCLawViolationResponseEvent resp = lv.getResponse();
			MessageUtil.postReply(resp);
			isExist = true;
		}
		return isExist;
	}
	
	/**
	 * @param event
	 * @param ncResponseId
	 */
	private void postLegacyData(String ncResponseId, Object object) {
		GetNCLawViolationsEvent gEvent = (GetNCLawViolationsEvent) object;
		Iterator iterator = LawViolation.findAll(gEvent); 
		while(iterator.hasNext()){
			LawViolation lv = (LawViolation) iterator.next();
			NCLawViolationResponseEvent resp = lv.getResponse();
			resp.setNcResponseId(ncResponseId);
			resp.setLawViolationId(new StringBuffer("L").append(resp.getLawViolationId()).toString());				
			MessageUtil.postReply(resp);
		}
	}
	
	/**
	 * @param ncResponseId
	 */
	private void deleteJims2Data(String ncResponseId) {
		Iterator iterator = LawViolation.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			LawViolation lv = (LawViolation) iterator.next();
		    lv.delete();
		}
	}
}
