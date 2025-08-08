package pd.supervision.administercompliance.transactions.daos;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.administercompliance.GetNCPositiveUrinalysisEvent;
import messaging.administercompliance.GetNCResponseComponentsEvent;
import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administercompliance.UpdateNCPositiveUAEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.administercompliance.reply.NCResponseResponseEvent;
import messaging.administercompliance.reply.NonComplianceEventResponseEvent;
import messaging.supervisionorder.GetSprOrderConditionByCriminalCaseIdEvent;
import mojo.km.utilities.MessageUtil;
import naming.SupervisionOrderConditionConstants;
import naming.ViolationReportConstants;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.common.DAOHandler;
import pd.supervision.administercompliance.Comment;
import pd.supervision.administercompliance.NonComplianceEvent;
import pd.supervision.administercompliance.PositiveUA;
import pd.supervision.administercompliance.ViolationReport;
import pd.supervision.administercompliance.ViolationReportUtility;
import pd.supervision.supervisionorder.SupervisionOrderConditionRelView;

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
public class PositiveUADAO extends ViolationReportUtility implements DAOHandler
{
	/**
	 * 
	 * @roseuid 473B85EB0040
	 */
	public PositiveUADAO()
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
		PositiveUA pua = null;
		boolean isUpdate = false;

		Map existingMap = PositiveUA.findAllByNumericParameter(ViolationReportConstants.PARAM_NCRESPONSE_ID, reqEvent.getNcResponseId()); 
		
		while(enumer.hasMoreElements()){
			UpdateNCPositiveUAEvent pEvent = (UpdateNCPositiveUAEvent) enumer.nextElement();
			if(pEvent.getPositiveUAId() == null || pEvent.getPositiveUAId().equals("")){
				pua = new PositiveUA();
				pua.setPositiveUA(pEvent,reqEvent.getNcResponseId());
				pua.commit();
			}else{
				pua = PositiveUA.find(pEvent.getPositiveUAId());
				if(pua != null && existingMap.containsKey(pua.getOID())){
					existingMap.remove(pua.getOID());
				}
			}
		}
		
		ViolationReport vr = ViolationReport.find(reqEvent.getNcResponseId());
		if(vr != null){
			vr.setTotalSpecimenAnalyzed(reqEvent.getTotalSpecimenAnalyzed());
		}		
		setComments (ViolationReportConstants.REQUEST_POSITIVE_UA, reqEvent.getNcResponseId(), reqEvent);
		
        // at this point existingmap only contains unwanted stuffs
		Iterator unwantedIter = existingMap.values().iterator();
		while(unwantedIter.hasNext()){
			pua = (PositiveUA) unwantedIter.next();
			if(pua != null){
				pua.delete();
				pua.commit();
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
			GetNCPositiveUrinalysisEvent gEvent = (GetNCPositiveUrinalysisEvent) reqEvent.getRequests().nextElement();
			postLegacyData(reqEvent.getNcResponseId(),gEvent);			
		}else{
			this.postDb2Data(reqEvent.getNcResponseId());
			ViolationReport vr = ViolationReport.find(reqEvent.getNcResponseId());
			if(vr != null){
				NCResponseResponseEvent resp = vr.getPositiveUAResponse();
				resp.setTotalSpecimenAnalyzed(vr.getTotalSpecimenAnalyzed());
				Comment comment = getComments(ViolationReportConstants.REQUEST_POSITIVE_UA,reqEvent.getNcResponseId());
				if(comment != null){
					resp.setComments(comment.getComments());	
				}
				MessageUtil.postReply(resp);					
			}
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
			Comment comment = getComments(ViolationReportConstants.REQUEST_POSITIVE_UA, refEvent.getNcResponseId());
			if(comment != null){
				comment.delete();
			}
			vr.setTotalSpecimenAnalyzed("");
		}
		
		// retrieve from Legacy
		GetNCPositiveUrinalysisEvent gEvent = new GetNCPositiveUrinalysisEvent();
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
		Iterator iterator = PositiveUA.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			PositiveUA pa = (PositiveUA) iterator.next();
			NonComplianceEventResponseEvent resp = pa.getResponse();
			resp.setTopic(ViolationReportConstants.REQUEST_POSITIVE_UA);
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
		GetNCPositiveUrinalysisEvent gEvent = (GetNCPositiveUrinalysisEvent) object;
		
		GetSprOrderConditionByCriminalCaseIdEvent reqEvent = new GetSprOrderConditionByCriminalCaseIdEvent();
		reqEvent.setCriminalCaseId(new StringBuffer(gEvent.getCdi()).append(gEvent.getCaseId()).toString());
		Iterator iterator = SupervisionOrderConditionRelView.findAll(reqEvent);
//  01-13-10 defects 62992 and 62271 
//	description in codes in mixed case, should be upper case. Getter description revised from Positive UA% to just % to find all 		
		Iterator codes = PDSupervisionCodeHelper.getSupervisionCodesByValue(naming.PDCodeTableConstants.EVENT_TYPE, "%");
		
		HashMap map = new HashMap();
//  01-13-10 defects 62992 and 62271 
//	description in codes in mixed case, should be upper case so description check added
		while(codes.hasNext()){
			SupervisionCode code = (SupervisionCode) codes.next();
			if (code != null && code.getDescription()!= null){
				if (code.getDescription().toUpperCase().indexOf("POSITIVE UA") > -1){
					map.put(code.getCode(), code.getDescription());
				}
			}
		}
	
		while(iterator.hasNext()){
			SupervisionOrderConditionRelView rel = (SupervisionOrderConditionRelView) iterator.next();
			Iterator iter = NonComplianceEvent.findAllByNumericParam(SupervisionOrderConditionConstants.SUPERVISION_ORDER_CONDITION_ID, new StringBuffer("").append(rel.getSupervisionOrderConditionId()).toString());
		   	while(iter.hasNext()){
		   	    NonComplianceEvent nce = (NonComplianceEvent) iter.next();
		   	    if(nce != null){
		   	   	    NonComplianceEventResponseEvent resp = nce.getPositiveUAResponseEvent(map);
		   	   	    if(resp != null){
			   	       	resp.setNonComplianceEventId(new StringBuffer("L").append(resp.getNonComplianceEventId()).toString());
			   	       	MessageUtil.postReply(resp);
		   	   	    }
		   	    }
		   	}		    
		}
	}
	
	/**
	 * @param ncResponseId
	 */
	private void deleteJims2Data(String ncResponseId) {
		Iterator iterator = PositiveUA.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			PositiveUA pua = (PositiveUA) iterator.next();
		    pua.delete();
		}
	}
}
