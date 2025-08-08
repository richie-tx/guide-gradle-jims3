/*
 * Created on Dec 18, 2007
 *
 */
package pd.supervision.legacyupdates.handlers;

import java.io.IOException;
import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;

import naming.LegacyUpdateConstants;
import naming.PDConstants;
import messaging.legacyupdates.GetLegacyUpdatesEvent;
import messaging.legacyupdates.UpdateLevelOfSupervisionDataEvent;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.km.utilities.DateUtil;
import pd.supervision.legacyupdates.ILegacyUpdateHandler;
import pd.supervision.legacyupdates.LegacyUpdateLog;
import pd.supervision.legacyupdates.LegacyUpdatesUtil;
import pd.supervision.legacyupdates.entities.LevelOfSupervision;

/**
 * @author mchowdhury
 *
 */
public class LevelOfSupervisionHandler extends LegacyUpdatesUtil implements ILegacyUpdateHandler {

    public void createLegacy(LegacyUpdateLog logger) {
		String recordData = logger.getRecordData();
		if (recordData != null) {
			LevelOfSupervision sData = new LevelOfSupervision();
			sData.setSpn(logger.getSpn());
			sData.setSeqNumber(logger.getCstsSeqNo());
			sData.setOpId(logger.getOpId());
			Element element = null;
			try {
				element = getXMLElement(recordData,
						LegacyUpdateConstants.XML_LEVELOFSUPERVISIONDATA);
			} catch (JDOMException e) {
				logger.setError("Error parsing the xml");
			}

			if (element != null) {
				String action = getAttributeVal(element,LegacyUpdateConstants.ATTRIBUTE_ACTION);
				sData.setAction(action);
				if (action == null || 
						(action.equals(LegacyUpdateConstants.CHANGE) 
								&& (logger.getCstsSeqNo() == null 
										|| logger.getCstsSeqNo().trim().equals(PDConstants.BLANK)))){
					TransactionManager.getInstance().removeUpdated(sData);
					logger.setError(LegacyUpdateConstants.SEQ_NUM_MISSING);
				} else {
				
					String transactionDate = getAttributeVal(element,
						LegacyUpdateConstants.ATTRIBUTE_TRANSACTIONDATE);
					sData.setTransactionDate(DateUtil.stringToDate(transactionDate,
						DATE_FORMAT));

					String comments = getAttributeVal(element,
						LegacyUpdateConstants.ATTRIBUTE_COMMENTS);
					sData.setComments(comments);
				
					String los = getAttributeVal(element,
						LegacyUpdateConstants.ATTRIBUTE_LOS);
					sData.setLos(los);
				
					String olos = getAttributeVal(element,
						LegacyUpdateConstants.ATTRIBUTE_OLOS);
					sData.setOlos(olos);
					String caseNum = getAttributeVal(element,
						LegacyUpdateConstants.ATTRIBUTE_CASENUM);
					sData.setCaseNum(caseNum);
					String buildT20Ind = getAttributeVal(element,
						LegacyUpdateConstants.ATTRIBUTE_BUILDT20_IND);
					sData.setBuildT20Ind(buildT20Ind);

					try {
						logger.setProcMessage(PDConstants.BLANK);
						logger.setCstsSeqNo(sData.bind());
						checkForExceptions();
						logger.bind();	
						logger.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);
					}  catch (Exception e) {
						logger.setError(e.getCause().toString());
						clearExceptions();
					}
				}
			}
		}

	}

	public void deleteLegacy(LegacyUpdateLog logger) {
		LevelOfSupervision sData = new LevelOfSupervision();
		if (logger.getCstsSeqNo() == null || logger.getCstsSeqNo().trim().equals(PDConstants.BLANK)){
			TransactionManager.getInstance().removeUpdated(sData);
			logger.setError(LegacyUpdateConstants.SEQ_NUM_MISSING);
		} else {
			sData.setSpn(logger.getSpn());
			sData.setSeqNumber(logger.getCstsSeqNo());
			if (logger.getCriminalCaseId() != null && logger.getCriminalCaseId().length() == 15){
				sData.setCaseNum(logger.getCriminalCaseId().substring(3));
			} 
			sData.setAction(LegacyUpdateConstants.DELETE);

			try {
				logger.setProcMessage(PDConstants.BLANK);
				sData.bind();
				checkForExceptions();
				logger.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);
				logger.bind();
			} catch (Exception e) {
				logger.setError(e.getCause().toString());
				clearExceptions();
			}
		}
	}

	public void updateLegacy(LegacyUpdateLog logger) {
		createLegacy(logger);
	}
	
	public String createLog(IEvent event) throws IOException {
		UpdateLevelOfSupervisionDataEvent reqEvent = (UpdateLevelOfSupervisionDataEvent) event;
		
	   	//LOS H (Pretrial Intervention, J (Transfer Within Texas) and K (Transfer Out of Texas) 
    	//are not state reported and will not have a CTS code.
    	if (reqEvent.getLos() == null || reqEvent.getLos().equals(PDConstants.BLANK)){
    		return null;
    	}

		GetLegacyUpdatesEvent gEvent = new GetLegacyUpdatesEvent();
		gEvent.setRecordType(reqEvent.getRecType());
		gEvent.setSourceOID(reqEvent.getJims2SourceId());
		gEvent.setSpn(reqEvent.getSpn());
		List list = LegacyUpdateLog.findAll(gEvent);
		LegacyUpdateLog uLog = null;
		String legacyUpdateLogId = PDConstants.BLANK;
		if (list != null && !list.isEmpty()) {
			uLog = (LegacyUpdateLog) list.get(0);
			legacyUpdateLogId = uLog.getOID();
			uLog.setProcMessage(PDConstants.BLANK);
			uLog.setStatusId(reqEvent.getAction());
			uLog.setLegacyUpdateLogger(reqEvent.getSpn(),
				reqEvent.getRecType(), 
				reqEvent.getAction(),
				reqEvent.getJims2SourceId(), 
				reqEvent.getCriminalCaseId(),
				reqEvent.getLogonId(), 
				xmlWriter(reqEvent,	LegacyUpdateConstants.TST20_UPDATE));
		} else {
			uLog = new LegacyUpdateLog();
			uLog.setLegacyUpdateLogger(reqEvent.getSpn(),
					reqEvent.getRecType(), 
					LegacyUpdateConstants.STATUS_NEW,
					reqEvent.getJims2SourceId(), 
					reqEvent.getCriminalCaseId(),
					reqEvent.getLogonId(), 
					xmlWriter(reqEvent,	LegacyUpdateConstants.TST20_UPDATE));
			legacyUpdateLogId = uLog.getOID();
		}
		return legacyUpdateLogId;		
	}

	public void deleteLog(String sourceOid, String recType, String spn){
		GetLegacyUpdatesEvent gEvent = new GetLegacyUpdatesEvent();
		gEvent.setRecordType(recType);
		gEvent.setSourceOID(sourceOid);
		gEvent.setSpn(spn);
		List<LegacyUpdateLog> list = LegacyUpdateLog.findAll(gEvent);
		if (list != null && !list.isEmpty()) {
			LegacyUpdateLog log = list.get(0);
			log.setProcMessage(PDConstants.BLANK);
			if (log.getCstsSeqNo() != null && !log.getCstsSeqNo().equals(PDConstants.BLANK)) {
				log.setStatusId(LegacyUpdateConstants.STATUS_PENDING_DELETE);
				String recordData = log.getRecordData();
				
				try {
					Element element = getXMLElement(recordData, LegacyUpdateConstants.XML_LEVELOFSUPERVISIONDATA);
					if (element != null)
				    {
						//Change action to DELETE.
						Element root = new Element(LegacyUpdateConstants.XML_LEVELOFSUPERVISION);
						Element child = new Element(LegacyUpdateConstants.XML_LEVELOFSUPERVISIONDATA);
						child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,LegacyUpdateConstants.DELETE);
						child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_LOS,getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_LOS));
						child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_OLOS, getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_OLOS));
						child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_TRANSACTIONDATE, getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_TRANSACTIONDATE));
						child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_COMMENTS, getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_COMMENTS));
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
	
	private String xmlWriter(UpdateLevelOfSupervisionDataEvent reqEvent,
			String recType) throws IOException {
		Element root = new Element(LegacyUpdateConstants.XML_LEVELOFSUPERVISION);
		Element child = new Element(LegacyUpdateConstants.XML_LEVELOFSUPERVISIONDATA);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,
				(reqEvent.getAction() == null) ? "" : reqEvent
						.getAction());

		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_LOS,
				(reqEvent.getLos() == null) ? "" : reqEvent
						.getLos());
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_OLOS,
				(reqEvent.getOlos() == null) ? "" : reqEvent
						.getOlos());
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_TRANSACTIONDATE,
				(reqEvent.getTransactionDate() == null) ? ""
						: DateUtil.dateToString(reqEvent.getTransactionDate(),
								DATE_FORMAT));
		child.setAttribute(
				LegacyUpdateConstants.ATTRIBUTE_COMMENTS,
				(reqEvent.getComments() == null) ? "":reqEvent.getComments());
		String caseNum = reqEvent.getCriminalCaseId();
		if (caseNum != null && caseNum.length() == 15){
			caseNum = caseNum.substring(3);
		}
		child.setAttribute(
				LegacyUpdateConstants.ATTRIBUTE_CASENUM,
				(caseNum == null) ? "":caseNum);
		child.setAttribute(
				LegacyUpdateConstants.ATTRIBUTE_BUILDT20_IND,
				(reqEvent.getBuildT20Ind() == null) ? "":reqEvent.getBuildT20Ind());

		root.addContent(child);
		return writeToXML(root);
	}

}
