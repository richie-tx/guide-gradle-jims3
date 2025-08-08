package pd.supervision.administercompliance.transactions.daos;

import java.util.Iterator;

import messaging.administercompliance.GetNCCommunityServicesEvent;
import messaging.administercompliance.GetNCResponseComponentsEvent;
import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administercompliance.UpdateNCCommunityServiceEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.administercompliance.reply.NCCommunityServiceResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.ViolationReportConstants;
import pd.common.DAOHandler;
import pd.supervision.administercompliance.Comment;
import pd.supervision.administercompliance.CommunityService;
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
public class CommunityServiceDAO extends ViolationReportUtility implements DAOHandler
{
	/**
	 * 
	 * @roseuid 473B85EB0040
	 */
	public CommunityServiceDAO()
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
	 * @see pd.common.DAOHandler#remove(java.lang.Object)
	 */
	public void refresh(Object object) {
		RefreshNCResponseComponentsEvent refEvent = (RefreshNCResponseComponentsEvent) object;
		
		// delete jims2 data
		ViolationReport vr = ViolationReport.find(refEvent.getNcResponseId());
		if(vr != null){
			deleteJims2Data(refEvent.getNcResponseId());
			Comment comment = getComments(ViolationReportConstants.REQUEST_COMMUNITY_SERVICE, refEvent.getNcResponseId());
			if(comment != null){
				comment.delete();
			}		
		}
		
		// retrieve from Legacy
		GetNCCommunityServicesEvent gEvent = new GetNCCommunityServicesEvent();
		gEvent.setCaseId(refEvent.getCaseId());
		gEvent.setCdi(refEvent.getCdi());
		postLegacyData(refEvent.getNcResponseId(),gEvent);
	}


	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#update(java.lang.Object)
	 */
	public void update(Object object) {
		UpdateNCResponseEvent reqEvent = (UpdateNCResponseEvent) object;
		createViolationReport(reqEvent);
		
		UpdateNCCommunityServiceEvent uEvent = (UpdateNCCommunityServiceEvent) reqEvent.getRequests().nextElement();
		String communityServiceId = uEvent.getCommunityServiceId();		
				
		if(communityServiceId == null || communityServiceId.equals("")){
			CommunityService cs = new CommunityService();
			cs.setCommunityService(uEvent,reqEvent.getNcResponseId());
			cs.commit();
			setComments (ViolationReportConstants.REQUEST_COMMUNITY_SERVICE, reqEvent.getNcResponseId(), reqEvent);
			MessageUtil.postReply(cs.getResponse());
		}else{
			setComments (ViolationReportConstants.REQUEST_COMMUNITY_SERVICE, reqEvent.getNcResponseId(), reqEvent);
		}		
	}
	

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#get(java.lang.Object)
	 */
	public void get(Object object) {
		GetNCResponseComponentsEvent reqEvent = (GetNCResponseComponentsEvent) object;
		if(ViolationReportConstants.CREATE_MODE.equalsIgnoreCase(reqEvent.getMode())){
			GetNCCommunityServicesEvent gEvent = (GetNCCommunityServicesEvent) reqEvent.getRequests().nextElement();
			postLegacyData(reqEvent.getNcResponseId(),gEvent);			
		}else{
			this.postDb2Data(reqEvent.getNcResponseId());
			postComments(ViolationReportConstants.REQUEST_COMMUNITY_SERVICE,reqEvent.getNcResponseId());
		}
	}

	/**
	 * @param ncResponseId
	 * @return
	 */
	private boolean postDb2Data(String ncResponseId) {
		boolean isExist = false;
		Iterator iterator = CommunityService.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			CommunityService cs = (CommunityService) iterator.next();
			NCCommunityServiceResponseEvent resp = cs.getResponse();
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
		GetNCCommunityServicesEvent gEvent = (GetNCCommunityServicesEvent) object;
		Iterator iterator = CommunityService.findAll(gEvent); 
		while(iterator.hasNext()){
			CommunityService cs = (CommunityService) iterator.next();
			NCCommunityServiceResponseEvent resp = cs.getResponse();
			resp.setNcResponseId(ncResponseId);
			resp.setCommunityServiceId(new StringBuffer("L").append(resp.getCommunityServiceId()).toString());			
			MessageUtil.postReply(resp);
		}
	}	
	
	/**
	 * @param ncResponseId
	 */
	private void deleteJims2Data(String ncResponseId) {
		Iterator iterator = CommunityService.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			CommunityService cs = (CommunityService) iterator.next();
		    cs.delete();
		}		
	}
}
