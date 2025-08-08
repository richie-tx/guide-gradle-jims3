/*
 * Created on Apr 8, 2008
 *
 */
package pd.supervision.legacyupdates.handlers;

import java.io.IOException;
import java.util.List;

import messaging.legacyupdates.GetLegacyUpdatesEvent;
import messaging.legacyupdates.UpdateRiskNeedScoreAssessmentDataEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.LegacyUpdateConstants;
import naming.PDConstants;

import org.jdom.Element;
import org.jdom.JDOMException;

import pd.contact.user.UserProfile;
import pd.supervision.legacyupdates.ILegacyUpdateHandler;
import pd.supervision.legacyupdates.LegacyUpdateLog;
import pd.supervision.legacyupdates.LegacyUpdatesUtil;
import pd.supervision.legacyupdates.entities.AssessmentData;

/**
 * The <code>RiskNeedsScoreHandler</code> class allows a caller to perform CRUD operations
 * for "RISK NEEDS SCORE" section of the data markup as follows -
 * <LEGACYUPDATESDATA>
 * 	...
 * 	<ASSESSMENTDATA>
 * 		...
 * 		<RISKNEEDSSCORE>
 * 			<ACTION/>
 * 			<ASSESSMENTTYPE/>
 * 			<RISKSCORE/>
 * 			<NEEDSSCORE/>
 * 			<CASENUM/>
 * 			<ASSESSMENTDATE/>
 * 		</RISKNEEDSSCORE>
 * 		...
 * 	</ASSESSMENTDATA>
 * 	...
 * </LEGACYUPDATESDATA>
 * 
 * 
 * @author mchowdhury, parumbakkam
 *
 */
public class RiskNeedsScoreHandler extends LegacyUpdatesUtil implements ILegacyUpdateHandler {

	// This would need to be changed if we ever went to a multi-threaded model
	
	
	
	/**
	 * The <code>createLog</code> helper method allows a caller to create a "Legacy update Log" record
	 * in the DB. 
	 */
	public String createLog(IEvent event) throws IOException  {
		UpdateRiskNeedScoreAssessmentDataEvent updateEvent = (UpdateRiskNeedScoreAssessmentDataEvent) event;
		GetLegacyUpdatesEvent g_Event = new GetLegacyUpdatesEvent();
		g_Event.setRecordType(updateEvent.getRecType());
		g_Event.setSourceOID(updateEvent.getJims2SourceId());
		g_Event.setSpn(updateEvent.getSpn());
		List list = LegacyUpdateLog.findAll(g_Event);
		LegacyUpdateLog uLog = null;	
		String legacyUpdateLogId = "";		
		if(list != null && !list.isEmpty()){
            uLog = (LegacyUpdateLog) list.get(0);
            legacyUpdateLogId = uLog.getOID();
            uLog.setCriminalCaseId(updateEvent.getCriminalCaseId());
			UserProfile up = UserProfile.find(updateEvent.getLogonId());
			if(up != null){
			    uLog.setOpId(up.getOperatorId());
			}
			if (uLog.getCstsSeqNo() != null && !uLog.getCstsSeqNo().trim().equals(PDConstants.BLANK)) {
				updateEvent.setAction(LegacyUpdateConstants.CHANGE);
				uLog.updateLegacyUpdateLogger(xmlWriter(updateEvent, updateEvent
						.getRecType()));
			} else {
				updateEvent.setAction(LegacyUpdateConstants.ADD);
				uLog.updateUpdateLoggerOnly(xmlWriter(updateEvent, updateEvent
						.getRecType()));
			}
		}else{
			uLog = new LegacyUpdateLog();
			updateEvent.setAction(LegacyUpdateConstants.ADD);
			uLog.setLegacyUpdateLogger(	updateEvent.getSpn(), 
										updateEvent.getRecType(), 
										LegacyUpdateConstants.STATUS_NEW, 
										updateEvent.getJims2SourceId(), 
										updateEvent.getCriminalCaseId(), 
										updateEvent.getLogonId(), 
										xmlWriter(updateEvent, LegacyUpdateConstants.TST21_UPDATE));
			legacyUpdateLogId = uLog.getOID();
		}		
		return legacyUpdateLogId;
	}

	public void deleteLog(String sourceOid, String recType, String spn) {
		GetLegacyUpdatesEvent gEvent = new GetLegacyUpdatesEvent();
		gEvent.setRecordType(recType);
		gEvent.setSourceOID(sourceOid);
		gEvent.setSpn(spn);
		List<LegacyUpdateLog> list = LegacyUpdateLog.findAll(gEvent);		
		if(list != null && !list.isEmpty()){
			LegacyUpdateLog log = list.get(0);
			if (log.getCstsSeqNo() != null && !log.getCstsSeqNo().trim().equals(PDConstants.BLANK)) {
				log.setStatusId(LegacyUpdateConstants.STATUS_PENDING_DELETE);
				String recordData = log.getRecordData();
				
				try {
					Element element = getXMLElement(recordData, LegacyUpdateConstants.XML_RISKNEEDSSCORE);
					if (element != null)
				    {
						//Change action to DELETE.
						Element root = new Element(LegacyUpdateConstants.XML_ASSESSMENTDATA);
						Element child = new Element(LegacyUpdateConstants.XML_RISKNEEDSSCORE);
						child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,LegacyUpdateConstants.DELETE);
						child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTTYPE,getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTTYPE));
						child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE, getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE));
						child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_NEEDSSCORE, getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_NEEDSSCORE));
						child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_RISKSCORE, getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_RISKSCORE));
						child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CASENUM, getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_CASENUM));
						root.addContent(child);	
						String newRecData = writeToXML(root);
						log.setRecordData(newRecData);
						log.bind();
				    }

				} catch (Exception e) {
					log.setError(e.getCause().toString());
					clearExceptions();
				}
			} else {
				log.delete();
			}
		}
	}
	
	public void createLegacy(LegacyUpdateLog logger) {
		String recordData = logger.getRecordData();
        if (recordData != null)
        {          
	       	AssessmentData rScore = new AssessmentData();
	    	rScore.setLegacyAssessmentId(logger.getSourceId());       
	    	rScore.setSpn(logger.getSpn());	    
	    	rScore.setOpId(logger.getOpId());
        
	        Element element = null;
			try {
				element = getXMLElement(recordData, LegacyUpdateConstants.XML_RISKNEEDSSCORE);
			} catch (JDOMException e) {
				logger.setProcMessage(e.getMessage());
			}
		    
			if (element != null)
		    {
				String action = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ACTION);
		        rScore.setAction(action);

				String assessmentDate = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE);
		        rScore.setAssessmentDate(DateUtil.stringToDate(assessmentDate, DATE_FORMAT));
		        
		        String assessmentType = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTTYPE);
		        rScore.setAssessmentType(assessmentType);
		        
		        String needsScore = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_NEEDSSCORE);
		        rScore.setNeedsScore(needsScore);
		        
		        String riskScore = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_RISKSCORE);
		        rScore.setRiskScore(riskScore);
		        
		        String caseNum = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_CASENUM);
		        rScore.setCaseNum(caseNum);
		        
		        try{
		        	logger.setProcMessage(PDConstants.BLANK);
		        	logger.setCstsSeqNo(rScore.bind());
		        	checkForExceptions();
		        	logger.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);			        	
					logger.bind();
		        } catch(Exception e){
  					logger.setError(e.getCause().toString());	
  					clearExceptions();
		        }
		    }
        }
    }
	
	public void updateLegacy(LegacyUpdateLog logger) {
		AssessmentData rScore = new AssessmentData();
		String recordData = logger.getRecordData();
		rScore.setSpn(logger.getSpn());
		//SCS classification not needed for Wisconsin or LSIR.
		//rScore.setScsClassification(logger.getRecordTypeId());
		rScore.setSeqNumber(logger.getCstsSeqNo());
		rScore.setOpId(logger.getOpId());
		
		if (recordData != null) {
			rScore.setLegacyAssessmentId(logger.getSourceId());

			Element element = null;
			try {
				element = getXMLElement(recordData,
						LegacyUpdateConstants.XML_RISKNEEDSSCORE);
			} catch (JDOMException e) {
				logger.setProcMessage(e.getMessage());
			}

			if (element != null) {
				String action = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ACTION);
		        rScore.setAction(action);

		        String assessmentDate = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE);
		        rScore.setAssessmentDate(DateUtil.stringToDate(assessmentDate, DATE_FORMAT));
		        
		        String assessmentType = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTTYPE);
		        rScore.setAssessmentType(assessmentType);
				
		        String needsScore = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_NEEDSSCORE);
		        rScore.setNeedsScore(needsScore);
		        
		        String riskScore = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_RISKSCORE);
		        rScore.setRiskScore(riskScore);
		        
		        String caseNum = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_CASENUM);
		        rScore.setCaseNum(caseNum);

				try {
					logger.setProcMessage(PDConstants.BLANK);
					logger.setCstsSeqNo(rScore.bind());
					checkForExceptions();
					logger.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);
					logger.bind();
				} catch (Exception e) {
					logger.setError(e.getCause().toString());
					clearExceptions();
				}
			}
		}
	}	

	public void deleteLegacy(LegacyUpdateLog logger) {
		if (logger.getCstsSeqNo() != null && !logger.getCstsSeqNo().trim().equals(PDConstants.BLANK)){
			AssessmentData rScore = new AssessmentData();
			rScore.setSpn(logger.getSpn());		
			rScore.setSeqNumber(logger.getCstsSeqNo());
			rScore.setAction(LegacyUpdateConstants.DELETE);
			String recordData = logger.getRecordData();
			Element element = null;
			try {
				element = getXMLElement(recordData,
					LegacyUpdateConstants.XML_RISKNEEDSSCORE);
				if (element != null) {
				 	String assessmentDate = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE);
			        rScore.setAssessmentDate(DateUtil.stringToDate(assessmentDate, DATE_FORMAT));
			        
			        String assessmentType = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTTYPE);
			        rScore.setAssessmentType(assessmentType);
					
			        String needsScore = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_NEEDSSCORE);
			        rScore.setNeedsScore(needsScore);
			        
			        String riskScore = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_RISKSCORE);
			        rScore.setRiskScore(riskScore);
			       
			        String caseNum = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_CASENUM);
			        rScore.setCaseNum(caseNum);
				}
			} catch (JDOMException e) {
				logger.setProcMessage(e.getMessage());
			}
			try {
				logger.setProcMessage(PDConstants.BLANK);
				rScore.bind();
				checkForExceptions();
				logger.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);
				logger.bind();
			} catch (Exception e) {
				logger.setError(e.getCause().toString());
				clearExceptions();
			}
		} else {
			logger.setError(LegacyUpdateConstants.SEQ_NUM_MISSING);
		}
	}
	
	private String xmlWriter(UpdateRiskNeedScoreAssessmentDataEvent reqEvent, String recType) throws IOException{
		Element root = new Element(LegacyUpdateConstants.XML_ASSESSMENTDATA);
		Element child = new Element(LegacyUpdateConstants.XML_RISKNEEDSSCORE);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,reqEvent.getAction());
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTTYPE,reqEvent.getAssessmentType());
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE, (reqEvent.getAssessmentDate() == null)?"":DateUtil.dateToString(reqEvent.getAssessmentDate(), DATE_FORMAT));
		StringBuffer sb = new StringBuffer(reqEvent.getNeedsScore());
		if (sb.length() < 2){
			sb.insert(0, "0");
		}
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_NEEDSSCORE, sb.toString());
		sb =  new StringBuffer(reqEvent.getRiskScore());
		if (sb.length() < 2){
			sb.insert(0, "0");
		}
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_RISKSCORE, sb.toString());
		
		String caseNum = reqEvent.getCriminalCaseId();
		if (caseNum != null && caseNum.length() == 15){
			caseNum = caseNum.substring(3);
		}
		child.setAttribute(
				LegacyUpdateConstants.ATTRIBUTE_CASENUM,
				(caseNum == null) ? "":caseNum);
		
		root.addContent(child);	
		return writeToXML(root);
	}
}
