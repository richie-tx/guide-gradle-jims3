/*
 * Created on Apr 8, 2008
 *
 */
package pd.supervision.legacyupdates.handlers;

import java.io.IOException;
import java.util.List;

import messaging.legacyupdates.GetLegacyUpdatesEvent;

import messaging.legacyupdates.UpdateSCSAssessmentDataEvent;
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
 * @author mchowdhury
 * 
 */
public class StrategiesForCaseSupervisionHandler extends LegacyUpdatesUtil
		implements ILegacyUpdateHandler {

	public String createLog(IEvent event) throws IOException {
		UpdateSCSAssessmentDataEvent reqEvent = (UpdateSCSAssessmentDataEvent) event;
		GetLegacyUpdatesEvent gEvent = new GetLegacyUpdatesEvent();
		gEvent.setRecordType(reqEvent.getRecType());
		gEvent.setSourceOID(reqEvent.getJims2SourceId());
		gEvent.setSpn(reqEvent.getSpn());
		List list = LegacyUpdateLog.findAll(gEvent);
		LegacyUpdateLog uLog = null;
		String legacyUpdateLogId = "";
		if (list != null && !list.isEmpty()) {
			uLog = (LegacyUpdateLog) list.get(0);
			legacyUpdateLogId = uLog.getOID();
            uLog.setCriminalCaseId(reqEvent.getCriminalCaseId());
			UserProfile up = UserProfile.find(reqEvent.getLogonId());
			if(up != null){
			    uLog.setOpId(up.getOperatorId());
			}
			if (uLog.getCstsSeqNo() != null && !uLog.getCstsSeqNo().trim().equals(PDConstants.BLANK)) {
				reqEvent.setAction(LegacyUpdateConstants.CHANGE);
				uLog.updateLegacyUpdateLogger(xmlWriter(reqEvent, reqEvent
						.getRecType()));
			} else {
				reqEvent.setAction(LegacyUpdateConstants.ADD);
				uLog.updateUpdateLoggerOnly(xmlWriter(reqEvent, reqEvent
						.getRecType()));
			}
		} else {
			uLog = new LegacyUpdateLog();
			reqEvent.setAction(LegacyUpdateConstants.ADD);
			uLog.setLegacyUpdateLogger(reqEvent.getSpn(),
					reqEvent.getRecType(), 
					LegacyUpdateConstants.STATUS_NEW,
					reqEvent.getJims2SourceId(), 
					reqEvent.getCriminalCaseId(),
					reqEvent.getLogonId(), 
					xmlWriter(reqEvent,
							LegacyUpdateConstants.TST22_UPDATE));
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
		if (list != null && !list.isEmpty()) {
			LegacyUpdateLog log = list.get(0);
			if (log.getCstsSeqNo() != null && !log.getCstsSeqNo().trim().equals(PDConstants.BLANK)) {
				log.setStatusId(LegacyUpdateConstants.STATUS_PENDING_DELETE);
				String recordData = log.getRecordData();
				
				try {
					Element element = getXMLElement(recordData, LegacyUpdateConstants.XML_SCSDATA);
					if (element != null)
				    {
						//Change action to DELETE.
						Element root = new Element(LegacyUpdateConstants.XML_ASSESSMENTDATA);
						Element child = new Element(LegacyUpdateConstants.XML_SCSDATA);
						child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,LegacyUpdateConstants.DELETE);
						child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCODE,getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCODE));
						child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE, getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE));
						child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCLASSIFICATION, getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCLASSIFICATION));
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
		if (recordData != null) {
			AssessmentData sData = new AssessmentData();
			sData.setLegacyAssessmentId(logger.getSourceId());
			sData.setSpn(logger.getSpn());
			sData.setAction(logger.getStatusId());
			sData.setSeqNumber(logger.getCstsSeqNo());
			sData.setOpId(logger.getOpId());

			Element element = null;
			try {
				element = getXMLElement(recordData,
						LegacyUpdateConstants.XML_SCSDATA);
			} catch (JDOMException e) {
				logger.setError("Error parsing the xml");
			}

			if (element != null) {
				String action = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ACTION);
		        sData.setAction(action);

				String assessmentDate = getAttributeVal(element,
						LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE);
				sData.setAssessmentDate(DateUtil.stringToDate(assessmentDate,
						DATE_FORMAT));

				String assessmentCode = getAttributeVal(element,
						LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCODE);
				sData.setAssessmentType(assessmentCode);
				
				String assessmentClassification = getAttributeVal(element,
						LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCLASSIFICATION);
				sData.setScsClassification(assessmentClassification);
				
		        String caseNum = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_CASENUM);
		        sData.setCaseNum(caseNum);

				try {
					logger.setCstsSeqNo(sData.bind());
					checkForExceptions();
					logger.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);
					logger.bind();
				}  catch (Exception e) {
					logger.setError(e.getCause().toString());
					clearExceptions();
				}
			}
		}
	}

	public void updateLegacy(LegacyUpdateLog logger) {
		AssessmentData sData = new AssessmentData();
		String recordData = logger.getRecordData();
		sData.setSpn(logger.getSpn());
		sData.setScsClassification(logger.getRecordTypeId());
		sData.setAction(logger.getStatusId());
		sData.setSeqNumber(logger.getCstsSeqNo());
		sData.setOpId(logger.getOpId());
		
		if (recordData != null) {
			sData.setLegacyAssessmentId(logger.getSourceId());

			Element element = null;
			try {
				element = getXMLElement(recordData,
						LegacyUpdateConstants.XML_SCSDATA);
			} catch (JDOMException e) {
				logger.setError("Error parsing the xml");
			}

			if (element != null) {
				String action = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ACTION);
		        sData.setAction(action);

				String assessmentDate = getAttributeVal(element,
						LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE);
				sData.setAssessmentDate(DateUtil.stringToDate(assessmentDate,
						DATE_FORMAT));

				String assessmentCode = getAttributeVal(element,
						LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCODE);
				sData.setAssessmentType(assessmentCode);
				
				String assessmentClassification = getAttributeVal(element,
						LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCLASSIFICATION);
				sData.setScsClassification(assessmentClassification);
				
		        String caseNum = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_CASENUM);
		        sData.setCaseNum(caseNum);

				try {
					logger.setProcMessage(PDConstants.BLANK);
					logger.setCstsSeqNo(sData.bind());
					checkForExceptions();
					logger.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);
					logger.bind();
				}  catch (Exception e) {
					logger.setError(e.getCause().toString());
					clearExceptions();
				}
			}
		}
	}

	public void deleteLegacy(LegacyUpdateLog logger) {
		if (logger.getCstsSeqNo() != null && !logger.getCstsSeqNo().trim().equals(PDConstants.BLANK)){
			AssessmentData sData = new AssessmentData();
			sData.setSpn(logger.getSpn());
			sData.setSeqNumber(logger.getCstsSeqNo());
			sData.setAction(LegacyUpdateConstants.DELETE);
			Element element = null;
			try {
				element = getXMLElement(logger.getRecordData(),
					LegacyUpdateConstants.XML_SCSDATA);
				
				String assessmentClassification = getAttributeVal(element,
						LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCLASSIFICATION);
				sData.setScsClassification(assessmentClassification);
				logger.setProcMessage(PDConstants.BLANK);
				sData.bind();
				checkForExceptions();
				logger.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);
				logger.bind();

			} catch (JDOMException e) {
					logger.setProcMessage(e.getMessage());

			} catch (Exception e) {
				logger.setError(e.getCause().toString());
				clearExceptions();
			} 
		}else {
			logger.setError(LegacyUpdateConstants.SEQ_NUM_MISSING);
		}

	}

	private String xmlWriter(UpdateSCSAssessmentDataEvent reqEvent,
			String recType) throws IOException {
		Element root = new Element(LegacyUpdateConstants.XML_ASSESSMENTDATA);
		Element child = new Element(LegacyUpdateConstants.XML_SCSDATA);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,reqEvent.getAction());
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCODE,
				(reqEvent.getAssessmentCode() == null) ? "" : reqEvent
						.getAssessmentCode());
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTDATE,
				(reqEvent.getAssessmentDate() == null) ? ""
						: DateUtil.dateToString(reqEvent.getAssessmentDate(),
								DATE_FORMAT));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSESSMENTCLASSIFICATION,(reqEvent.getScsClassification() == null) ? "":reqEvent.getScsClassification());
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
