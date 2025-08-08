package pd.supervision.administercompliance.transactions.daos;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import messaging.administercompliance.GetNCResponseComponentsEvent;
import messaging.administercompliance.GetNCTreatmentIssuesEvent;
import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.administercompliance.UpdateNCTreatmentEvent;
import messaging.administercompliance.reply.NCTreatmentResponseEvent;
import messaging.administerprogramreferrals.reply.CSProgramReferralResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.ViolationReportConstants;
import pd.common.DAOHandler;
import pd.supervision.administercompliance.Comment;
import pd.supervision.administercompliance.Treatment;
import pd.supervision.administercompliance.ViolationReport;
import pd.supervision.administercompliance.ViolationReportUtility;
import pd.supervision.administerprogramreferrals.CSProgramReferral;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;

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
public class TreatmentDAO extends ViolationReportUtility implements DAOHandler
{
	/**
	 * 
	 * @roseuid 473B85EB0040
	 */
	public TreatmentDAO()
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
		Treatment tr = null;
		
		Map existingMap = Treatment.findAllByNumericParameter(ViolationReportConstants.PARAM_NCRESPONSE_ID, reqEvent.getNcResponseId()); 

		while(enumer.hasMoreElements()){
			UpdateNCTreatmentEvent tEvent = (UpdateNCTreatmentEvent) enumer.nextElement();
			if(tEvent.getTreatmentId() == null || tEvent.getTreatmentId().equals("")){
				tr = new Treatment();
				tr.setTreatment(tEvent, reqEvent.getNcResponseId());
				tr.commit();
			}else{
				tr = Treatment.find(tEvent.getTreatmentId());
				if(tr != null && existingMap.containsKey(tr.getOID())){
					existingMap.remove(tr.getOID());
				}
			}
		}
		
		setComments (ViolationReportConstants.REQUEST_TREATMENT, reqEvent.getNcResponseId(), reqEvent);

        // at this point existingmap only contains unwanted stuffs
		Iterator unwantedIter = existingMap.values().iterator();
		while(unwantedIter.hasNext()){
			tr = (Treatment) unwantedIter.next();
			if(tr != null){
				tr.delete();
				tr.commit();
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
			GetNCTreatmentIssuesEvent gEvent = (GetNCTreatmentIssuesEvent) reqEvent.getRequests().nextElement();
			postLegacyData(reqEvent.getNcResponseId(), gEvent);
		}else{
			this.postDb2Data(reqEvent.getNcResponseId());
			postComments(ViolationReportConstants.REQUEST_TREATMENT,reqEvent.getNcResponseId());		
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
			Comment comment = getComments(ViolationReportConstants.REQUEST_TREATMENT, refEvent.getNcResponseId());
			if(comment != null){
				comment.delete();
			}		
		}
		
		// retrieve from Legacy
		GetNCTreatmentIssuesEvent gEvent = new GetNCTreatmentIssuesEvent();
		gEvent.setCaseId(refEvent.getCaseId());
		gEvent.setCdi(refEvent.getCdi());
		postLegacyData(refEvent.getNcResponseId(),gEvent);
	}
	
	/**
	 * @param ncResponseId
	 * @return
	 */
	private boolean postDb2Data(String ncResponseId){
	    boolean isExist = false;
		Iterator iterator = Treatment.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			Treatment t = (Treatment) iterator.next();
			NCTreatmentResponseEvent resp = t.getResponse();
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
		GetNCTreatmentIssuesEvent gEvent = (GetNCTreatmentIssuesEvent) object;
        Iterator iter = CSProgramReferral.findAll(gEvent);
		while(iter.hasNext()){
			CSProgramReferral cp = (CSProgramReferral) iter.next();
			CSProgramReferralResponseEvent resp =  CSProgramReferralHelper.getProgramReferralResponse(cp);
			if(resp.getProgramLocationId() != null && !"".equals(resp.getProgramLocationId())){
				resp.setNewServiceProviderName(CSProgramReferralHelper.getServiceProviderName(cp));
			}
			resp.setProgramReferralId(new StringBuffer("L").append(resp.getProgramReferralId()).toString());
			MessageUtil.postReply(resp);
		}
	}
	
	/**
	 * @param ncResponseId
	 */
	private void deleteJims2Data(String ncResponseId) {
		Iterator iterator = Treatment.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			Treatment t = (Treatment) iterator.next();
		    t.delete();
		}
	}
}
