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
import messaging.legacyupdates.UpdateReferralExitDataEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;
import pd.supervision.legacyupdates.ILegacyUpdateHandler;
import pd.supervision.legacyupdates.LegacyUpdateLog;
import pd.supervision.legacyupdates.LegacyUpdatesUtil;
import pd.supervision.legacyupdates.entities.ReferralSubmit;

/**
 * @author Raja Veerappan
 *
 */
public class ReferralExitHandler extends LegacyUpdatesUtil implements ILegacyUpdateHandler {

   public void createLegacy(LegacyUpdateLog logger) {
		String recordData = logger.getRecordData();
		if (recordData != null) {
			ReferralSubmit referralData = new ReferralSubmit();
			referralData.setSpn(logger.getSpn());
			referralData.setAction(logger.getStatusId());
			referralData.setSeqNumber(logger.getCstsSeqNo());
			referralData.setCdi(logger.getCriminalCaseId().substring(0, 3));
			referralData.setCas(logger.getCriminalCaseId().substring(3));
			referralData.setRecordType(logger.getRecordTypeId());
			referralData.setOpId(logger.getOpId());
			
			getValueFromXML(referralData, logger);
			try {
				logger.setProcMessage(PDConstants.BLANK);
				logger.setCstsSeqNo(referralData.bind());
				checkForExceptions();
				if (referralData.getMsg() != null && !referralData.getMsg().trim().equals(PDConstants.BLANK)){
					logger.setError(referralData.getMsg());
				} else {
					logger.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);		
					logger.bind();
				}
			} catch (Exception e) {
				logger.setError(e.getCause().toString());
				clearExceptions();
			} 
		}

	}

	public void deleteLegacy(LegacyUpdateLog logger) {
		String recordData = logger.getRecordData();
		if (recordData != null) {
			ReferralSubmit referralData = new ReferralSubmit();
			referralData.setSpn(logger.getSpn());
			referralData.setAction(logger.getStatusId());
			referralData.setSeqNumber(logger.getCstsSeqNo());
			referralData.setCdi(logger.getCriminalCaseId().substring(0, 3));
			referralData.setCas(logger.getCriminalCaseId().substring(3));
			referralData.setRecordType(logger.getRecordTypeId());
			getValueFromXML(referralData, logger);

			try {
				logger.setProcMessage(PDConstants.BLANK);
				referralData.bind();
				checkForExceptions();
				logger.delete();
			}  catch (Exception e) {
				logger.setError(e.getCause().toString());
				clearExceptions();
			}
		}
	}

	public void updateLegacy(LegacyUpdateLog logger) {
		createLegacy(logger);
	}

	public String createLog(IEvent event) throws IOException {
		UpdateReferralExitDataEvent reqEvent = (UpdateReferralExitDataEvent) event;
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
			uLog.setProcMessage(PDConstants.BLANK);
			if (uLog.getCstsSeqNo() != null && !uLog.getCstsSeqNo().equals(PDConstants.BLANK)) {
				reqEvent.setAction(LegacyUpdateConstants.CHANGE);
			} else {
				reqEvent.setAction(LegacyUpdateConstants.ADD);
			}
			uLog.setLegacyUpdateLogger(reqEvent.getSpn(),
					reqEvent.getRecType(), 
					reqEvent.getAction(),
					reqEvent.getJims2SourceId(), 
					reqEvent.getCriminalCaseId(),
					reqEvent.getLogonId(), 
					xmlWriter(reqEvent,	reqEvent.getRecType()));
		} else {
			uLog = new LegacyUpdateLog();
			reqEvent.setAction(LegacyUpdateConstants.ADD);
			uLog.setLegacyUpdateLogger(reqEvent.getSpn(),
					reqEvent.getRecType(), 
					LegacyUpdateConstants.STATUS_NEW,
					reqEvent.getJims2SourceId(), 
					reqEvent.getCriminalCaseId(),
					reqEvent.getLogonId(), 
					xmlWriter(reqEvent,	LegacyUpdateConstants.TST34_UPDATE));
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
			log.setProcMessage(PDConstants.BLANK);
			if (log.getCstsSeqNo() != null) {
				UserProfile user = UserProfile.find(PDSecurityHelper.getLogonId());
				if (user != null){
					log.setOpId(user.getOperatorId());
				}
				log.setStatusId(LegacyUpdateConstants.STATUS_PENDING_DELETE);
				String newRecData = getRecData(log.getRecordData());
				log.setRecordData(newRecData);
				log.bind();
			} else {
				log.delete();
			}
		}

	}
	private  String getRecData(String recData){
		
		Element root = new Element(LegacyUpdateConstants.XML_REFERRALDATA);
		Element child = new Element(LegacyUpdateConstants.XML_REFERRALEXIT);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,LegacyUpdateConstants.DELETE);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CTSCODE,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_CTSCODE));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_PROGBEGINDATE,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_PROGBEGINDATE));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_DESIGNATOR,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_DESIGNATOR));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_PROGENDDATE,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_PROGENDDATE));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_DISCHARGEREASON,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_DISCHARGEREASON));
		root.addContent(child);	
		String newRecData = null;
		try {
			newRecData = writeToXML(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newRecData;
	}
	
	private void getValueFromXML(ReferralSubmit referralData,
			LegacyUpdateLog logger)  {
		String recordData = logger.getRecordData();
		Element element = null;
		try {
			element = getXMLElement(recordData,
					LegacyUpdateConstants.XML_REFERRALEXIT);
		} catch (JDOMException e) {
			e.printStackTrace();
			logger.setError("Error parsing the xml");
		}

		if (element != null) {
			String action = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_ACTION);
			referralData.setAction(action);

			String ctsCode = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_CTSCODE);
			referralData.setCtsCode(ctsCode);
			
			String progBeginDate = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_PROGBEGINDATE);
			referralData.setProgBeginDate( progBeginDate );

			String designator = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_DESIGNATOR);
			referralData.setDesignator(designator);

			String progEnDate = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_PROGENDDATE);
			referralData.setProgEnDate( progEnDate );

			String dischargeReason = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_DISCHARGEREASON);
			referralData.setDischargeReason(dischargeReason);
		}
	}
	private final String LEGACY_DATE_FORMAT = "MMddyyyy";

	private String xmlWriter(UpdateReferralExitDataEvent reqEvent,
			String recType) throws IOException {
		Element root = new Element(LegacyUpdateConstants.XML_REFERRALDATA);
		Element firstChild = new Element(
				LegacyUpdateConstants.XML_REFERRALEXIT);
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,
				(reqEvent.getAction() == null) ? "" : reqEvent.getAction());

		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CTSCODE,
				(reqEvent.getCtsCode() == null) ? "" : reqEvent.getCtsCode());
		firstChild
				.setAttribute(LegacyUpdateConstants.ATTRIBUTE_PROGBEGINDATE,
						(reqEvent.getProgBeginDate() == null) ? "" : DateUtil
								.dateToString(reqEvent.getProgBeginDate(),
										LEGACY_DATE_FORMAT));
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_DESIGNATOR,
				(reqEvent.getDesignator() == null) ? "" : reqEvent
						.getDesignator());
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_PROGENDDATE,
				(reqEvent.getProgEnDate() == null) ? "" : DateUtil
						.dateToString(reqEvent.getProgEnDate(),
								LEGACY_DATE_FORMAT));
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_DISCHARGEREASON,
				(reqEvent.getDischargeReason() == null) ? "" : reqEvent
						.getDischargeReason());		
		root.addContent(firstChild);
		return writeToXML(root);
	}

}
